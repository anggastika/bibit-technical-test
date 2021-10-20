package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import static bukalapak.TestInstrument.dotenv;

/**
 * @author Ferawati
 *
 * maintained by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */
public class OTPPage extends BasePage {

    private static boolean newOTP = false;

    public boolean getnewOTP() {
        return newOTP;
    }

    private static void setnewOTP(boolean newOTP) {
        OTPPage.newOTP = newOTP;
    }

    public OTPPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    //region Navigation

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    //endregion Navigation


    //region E2E action

    //method for type OTP because keyboard on isiOTP page will auto disappear after filled OTP,
    //if using method element will return error "No visible keyboard"
    public void tapOTP(String elementLocator, String text) {
        IOSElement iosElement = iosDriver.findElement(getLocator(elementLocator));
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(text);
    }

    public void tapNewOTP() {
        String otpToken = dotenv.get("VALID_OTP_CODE");
        setnewOTP(true);

        if (otpToken == null) {
            Assert.fail("There is no valid OTP token in env, please define VALID_OTP_CODE");
        } else if (isElementExist("new_otp_input_otp_1_textfield", 5)) {
            typeValue("new_otp_input_otp_1_textfield", String.valueOf(otpToken.charAt(0)));
            typeValue("new_otp_input_otp_2_textfield", String.valueOf(otpToken.charAt(1)));
            typeValue("new_otp_input_otp_3_textfield", String.valueOf(otpToken.charAt(2)));
            typeValue("new_otp_input_otp_4_textfield", String.valueOf(otpToken.charAt(3)));
            typeValue("new_otp_input_otp_5_textfield", String.valueOf(otpToken.charAt(4)));
        } else {
            for (int i = 0; i < otpToken.length(); i++) {
                tapElement(constructLocator("new_otp_input_otp_by_key_button",
                        String.valueOf(otpToken.charAt(i))));
                waitFor(1);
            }
        }
    }

    public void inputInvalidOTP() {
        String otpToken = dotenv.get("INVALID_OTP");

        if (isElementExist("kirim_sms_button", 10)) {
            tapElement("kirim_sms_button");
        }

        if (isElementExist("otp_code", 3)) {
            tapOTP("otp_code", otpToken);
        } else if (isElementExist("otp_code_new", 3)) {
            typeValue("new_otp_input_otp_2_textfield", String.valueOf(otpToken.charAt(0)));
            typeValue("new_otp_input_otp_3_textfield", String.valueOf(otpToken.charAt(1)));
            typeValue("new_otp_input_otp_4_textfield", String.valueOf(otpToken.charAt(2)));
            typeValue("new_otp_input_otp_5_textfield", String.valueOf(otpToken.charAt(3)));
            typeValue("new_otp_input_otp_1_textfield", String.valueOf(otpToken.charAt(4)));
        }
    }

    public void proceedOTP() {
        // request OTP token
        if (isElementVisible("kirim_sms_button")) {
            waitFor(10);
            userOntRequestOTPPage();
            tapElement("kirim_sms_button");
            if (isElementVisible("otp_code", 10)) {
                tapOTP("otp_code", dotenv.get("VALID_OTP_CODE"));
            } else {
                tapNewOTP();
            }
            waitOTPValidation();
        } else {
            LogUtil.info("Device is already trusted");
        }
    }

    /**
     * Static waiting method for waiting OTP code verification on server
     * because there is no specific time on OTP verification process on server.
     *
     * Using specific element on OTP verification page as an indicator,
     * if the element still visible (at specific time) then it indicates that OTP verification is fail,
     * otherwise OTP code verification is success.
     */
    public void waitOTPValidation() {
        String selector_indicator = "otp_code";
        int interationCount = 0;

        if (newOTP) {
            selector_indicator = "new_otp_header";
        }

        while (isElementVisible(selector_indicator) && interationCount < 5) {
            waitFor(5);
            interationCount++;

            if (isElementVisible(selector_indicator) && interationCount == 5) {
                // 40 = ((isElementVisible() = ~3s) + waitFor(5)) * (iterationCount = 5)
                Assert.fail("Validation credential proceed take more than 40 second");
            }
        }
    }

    //endregion E2E action

    //region Validation

    public void userOntRequestOTPPage() {
        assertTrue(isElementVisible("kirim_sms_button", 5));
        HelperData.setLastActionPage(new OTPPage(iosDriver));
    }

    public void userOnOTPConfirmPage() {
        assertTrue(isElementVisible("otp_confirm_page_title"));
        HelperData.setLastActionPage(new OTPPage(iosDriver));
    }

    public void userOnOTPConfirmationPage() {
        if (isElementVisible("kirim_sms_button", 10)) {
            tapElement("kirim_sms_button");
        }
        if (isElementExist("otp_code_new", 3)) {
            assertTrue(isElementVisible("new_otp_input_otp_2_textfield"));
            assertTrue(isElementVisible("otp_description_new"));
        } else {
            assertTrue(isElementVisible("otp_code"));
        }
        HelperData.setLastActionPage(new OTPPage(iosDriver));
    }

    public void userOnInvalidOTPPage() {
        if (isElementExist("otp_code_new")) {
            validateExist("invalid_otp_message_new", 5);
        } else {
            validateExist("invalid_otp_message");
        }
        HelperData.setLastActionPage(new OTPPage(iosDriver));
    }

    public boolean isRequestOTPDisplayed() {
        return isElementVisible("kirim_sms_button", 10);
    }

    public boolean isInputOtpDisplayed() {
        return isElementVisible("new_otp_input_otp_1_textfield", 10);
    }

    //endregion Validation

    // OTP Verification in Webview

    public void verifyWebviewVerificationPageDisplayed() {
        verifyElementExist("WEBVIEW_OTP_PAGE");
        validateExist("WEBVIEW_OTP_PAGE_TITLE");
        validateExist("WEBVIEW_OTP_INPUT_FIELD");
        validateExist("WEBVIEW_OTP_RESET_BTN");
    }

    public void inputPasswordinWebview(String user) {
        tapElement("WEBVIEW_OTP_INPUT_FIELD");
        getElementPresent("WEBVIEW_OTP_INPUT_FIELD").sendKeys(dotenv.get(user+"_PASSWORD"));
    }

    public void tapOnVerificationWebviewBtn() {
        tapElement("WEBVIEW_OTP_VERIFICATION_BTN");
    }

    public void tapOnRequestViaSMSBtn() {
        verifyElementExist("WEBVIEW_OTP_SMS_VERIFICATION_BTN");
        tapElement("WEBVIEW_OTP_SMS_VERIFICATION_BTN");
    }

    public void verifyWebviewOTPPageDisplayed() {
        validateExist("WEBVIEW_OTP_VERIFICATION_PAGE");
        HelperData.setLastActionPage(new OTPPage(iosDriver));
    }
}
