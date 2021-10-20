package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.HomeData;
import bukalapak.data.UserData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

/**
 * Created by sekarayukarindra on 01/10/18.
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */
public class LoginPage extends BasePage {

    public LoginPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    //region Navigation

    public void goToLoginPage() {
        allowPopup();
        waitForVisibilityOf("home_akun_tab", 15);

        int loop = 3;

        // click Akun menu up to 3 times while Account page not displayed
        while (isElementExist("search_on_search_page", 5) &&
                isElementExist("home_blhome_tab", 5) &&
                loop > 0) {
            tapElement("home_akun_tab");

            loop --;
        }

        waitForElementClickable("akun_onboarding_login_button", 5);
        tapElement("akun_onboarding_login_button");
        HelperData.setLastActionPage(new LoginPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    //endregion Navigation


    //region Simple action

    public void typeOnUsernameEditText(String input) {
        if (isElementVisible("username_revamp_textfield", 10)) {
            // revamp
            typeAndEnterValue("username_revamp_textfield", input);
        } else if (isElementVisible("login_email_text_field", 10)) {
            // control
            typeAndEnterValue("login_email_text_field", input);
        } else {
            Assert.fail("There is no phone number/email/username text field displayed");
        }
    }

    public void typeOnPasswordEditText(String password) {
        if (isLoginChooserDisplayed()) {
            chooseLoginVerificationMethod("PASSWORD");
        }
        waitForVisibilityOf("login_password_pwd_field", 40);
        typeAndEnterValue("login_password_pwd_field", password);
    }

    private void allowLogout() {
        autoAcceptAlert("logout_alert_ya_button");
    }

    public void clickContinueButton() {
        if (isElementVisible("lanjut_revamp_button")) {
            tapElement("lanjut_revamp_button");
        } else {
            LogUtil.info("Lanjut button didn't displayed in login page");
        }
    }

    public void clickLoginDenganKodeRahasia() {
        waitForVisibilityOf("login_dengan_kode_rahasia_label", 5);
        tapElement("login_dengan_kode_rahasia_label");
    }

    // @method String only handle SMS or PASSWORD
    public void chooseLoginVerificationMethod(String method) {
        switch (method) {
            case "SMS":
                tapElement("passwordless_option_sms");
                break;
            case "PASSWORD":
                tapElement("passwordless_option_password");
                break;
            default:
                Assert.fail("Invalid verification method");
                break;
        }
    }

    public boolean isLoginChooserDisplayed() {
        return isElementVisible("passwordless_verify_login_option_title", 5);
    }

    public void clickLoginButton() {
        verifyElementDisplayed("login_login_button");
        tapElement("login_login_button");
    }

    //endregion Simple action


    //region E2E action

    public void validateLoginOnBoardingDisplayed() {
        if (isElementVisible("onboarding_login_page", 3)) {
            validateExist("onboarding_login_page_menu_lain");
            validateExist("onboarding_login_page_login_btn");
            validateExist("onboarding_login_page_daftar_btn");
            HelperData.setLastActionPage(new LoginPage(iosDriver));
        }
    }

    public void tapOnBoardingLoginBtn() {
        tapElement("akun_onboarding_login_button");
    }

    /**
     * This login method is used to simplify login process across different user type. The user type
     * must be declared in src/main/resources/env/data.env file. The param userType will be converted
     * as uppercase and combined for a search value. Then it will be used to get the expected value
     * from env file.
     *
     * @param userType
     */
    public void loginAs(String userType) {
        String tmpUserType = userType.replaceAll(" ", "_").toUpperCase();
        OTPPage otpPage = new OTPPage(iosDriver);

        try {
            // variant revamp
            if (isElementVisible("login_username_revamp_title")) {
                typeOnUsernameEditText(TestInstrument.dotenv.get(tmpUserType + "_USERNAME"));
                clickContinueButton();
                waitMethodLoginRelated("lanjut_revamp_button", 5);
                if (isLoginChooserDisplayed()) {
                    chooseLoginVerificationMethod("PASSWORD");
                }
                typeOnPasswordEditText(TestInstrument.dotenv.get(tmpUserType + "_PASSWORD"));
                tapElement("login_login_button");
                waitMethodLoginRelated("login_login_button", 5);
            } else {
                typeOnUsernameEditText(TestInstrument.dotenv.get(tmpUserType + "_USERNAME"));
                typeOnPasswordEditText(TestInstrument.dotenv.get(tmpUserType + "_PASSWORD"));
                tapElement("login_login_button");
                waitMethodLoginRelated("login_login_button", 5);

                // TFA active
                if (UserData.getTfaStatus()) {
                    otpPage.proceedOTP();
                }
            }

            allowPopup();
            UserData.setLoggedIn(true);
            HelperData.setLastActionPage(new HomePage(iosDriver));
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error("Type of " + tmpUserType + " is not listed in data.env file.");
            Assert.fail("Unable login caused by : " + e.getMessage());
        }
    }

    public void logout() {
        swipeUpToElement("pengaturan_akun_logout_option");
        tapElement("pengaturan_akun_logout_option");
        allowLogout();
        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }

    public void resetPassword(String credential, String newPassword) {
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(iosDriver);
        if (isElementVisible("login_reset_password_link_text")) {
            // control
            tapElement("login_reset_password_link_text");
            resetPasswordPage.typeFormResetPassword(TestInstrument.dotenv.get(credential));
            tapElement("login_send_reset_password");
            resetPasswordPage.typeNewAndConfirmationPassword(TestInstrument.dotenv.get(newPassword));
        } else if (isElementVisible("username_revamp_textfield")) {
            // revamp
            typeOnUsernameEditText(TestInstrument.dotenv.get(credential));
            clickContinueButton();

            if (!UserData.getTfaStatus()) {
                tapElement("login_reset_password_link_revamp_text");
                resetPasswordPage.typeFormResetPassword(TestInstrument.dotenv.get(credential));
                tapElement("login_send_reset_password");
                resetPasswordPage.typeNewAndConfirmationPassword(TestInstrument.dotenv.get(newPassword));
            } else {
                LogUtil.info("User activated TFA, please proceed OTP verification first before reset password");
            }
        } else {
            Assert.fail("Reset password link didn't displayed");
        }
    }

    public void loginAfterLogoutAll(String password) {
        waitForVisibilityOf("akun_onboarding_login_button", 5);
        tapElement("akun_onboarding_login_button");
        tapElement("lanjut_revamp_button");
        waitForVisibilityOf("login_password_pwd_field", 10);
        typeOnPasswordEditText(TestInstrument.dotenv.get(password+"_PASSWORD"));
        tapElement("login_login_button");
        waitMethodLoginRelated("login_login_button", 5);
    }

    //endregion E2E action


    //region Validation

    public void userOnLoginPage() {
        // give time to load if enter login screen from deeplink
        delay(10);
        if (!isElementVisible("login_email_text_field")) {
            assertTrue(isElementVisible("login_login_button"));
        } else {
            assertTrue(isElementVisible("login_email_text_field"));
        }

        HelperData.setLastActionPage(new LoginPage(iosDriver));
    }

    public void validateInvalidLogin(String type) {
        assertTrue(isElementVisible("invalid_" + type + "_login"));
    }

    public void validateEmptyPasswordErrorMessage(){
        verifyElementDisplayed("passwordless_empty_password");
    }

    public void validateInvalidPasswordErrorMessage(){
        verifyElementDisplayed("passwordless_invalid_password");
    }

    //endregion Validation


    //region Other

    /**
     * Static waiting method for waiting credential verification (login) on server,
     * because there is no specific time on credential verification process on server.
     *
     * Using specific element on login page as an indicator,
     * if the element still visible (at specific time) then it indicates that credential verification is fail,
     * otherwise credential verification is success
     * @param locator String
     * @param iteration int
     */
    public void waitMethodLoginRelated(String locator, int iteration) {
        int interationCount = 0;

        while (isElementVisible(locator) && interationCount < iteration) {
            waitFor(5);
            interationCount++;

            if (isElementVisible(locator) && interationCount == iteration) {
                // 8 = (isElementVisible() = ~3s) + waitFor(5)
                Assert.fail("Validation credential proceed take more than " + 8 * iteration + "second");
            }
        }
    }

    //endregion Other
}
