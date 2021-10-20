package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */

public class VerifyPhoneNumberPage extends BasePage {
    public VerifyPhoneNumberPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    //region Navigation

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    //endregion Navigation


    //region Simple action

    public void typeOnPhoneNumberField(String phoneNumber) {
        typeAndEnterValue("verify_phone_number_phone_number_field", phoneNumber);
    }

    public void typeOnPasswordField(String password) {
        typeAndEnterValue("verify_phone_number_password_field", password);
    }

    //endregion Simple action


    //region Validation

    public void onVerifyPhoneNumberPage() {
        waitForVisibilityOf("verification_phone_desc", 5);
        validateElementVisible("verification_phone_desc");
        HelperData.setLastActionPage(new VerifyPhoneNumberPage(iosDriver));
    }

    public void verifyPhoneNumberFieldErrorMessage(String errorMessage) {
        waitForVisibilityOf(constructLocator("verify_phone_number_phone_number_error_message_text", errorMessage), 5);
        validateElementContainsText(constructLocator("verify_phone_number_phone_number_error_message_text", errorMessage), errorMessage);
    }

    public void verifyPasswordFieldErrorMessage(String errorMessage) {
        waitForVisibilityOf(constructLocator("verify_phone_number_password_error_message_text", errorMessage), 5);
        validateElementContainsText(constructLocator("verify_phone_number_password_error_message_text", errorMessage), errorMessage);
        HelperData.setLastActionPage(new VerifyPhoneNumberPage(iosDriver));
    }

    public void onSuccessVerifyPhoneNumberPage(){
        if (isElementExist("success_verify_phone_credit_label", 5)) {
            tapElement("success_verify_phone_credit_label");
        }
        if (isElementClickable("new_user_zone_entrypoint_btn_label")) {
            tapElement("new_user_zone_entrypoint_btn_label");
        } else if (isElementClickable("base_back_button")) {
            tapElement("base_back_button");
        }
        HelperData.setLastActionPage(new VerifyPhoneNumberPage(iosDriver));
    }

    public void tapKirimOnVerifyPhonePage() {
        tapElement("verify_phone_kirim_button");
    }
    //endregion Validation
}
