package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */

public class VerifyEmailPage extends BasePage {
    public VerifyEmailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }
    //region Navigation

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    //endregion Navigation

    //region Simple action

    public void typeOnEmailField(String email) {
        typeAndEnterValue("verify_email_email_field", email);
    }

    public void typeOnPasswordField(String password) {
        typeAndEnterValue("verify_email_password_field", password);
    }

    //endregion Simple action

    //region Validation

    public void onVerifyEmailPage() {
        waitForVisibilityOf("verification_email_title", 5);
        validateElementVisible("verification_email_title");
        HelperData.setLastActionPage(new VerifyEmailPage(iosDriver));
    }

    public void verifyEmailFieldErrorMessage() {
        isElementVisible("invalid_email_error_alert", 10);
        validateElementVisible("verification_email_title");
        HelperData.setLastActionPage(new VerifyEmailPage(iosDriver));
    }

    public void verifyPasswordFieldErrorMessage() {
        isElementVisible("invalid_password_error_alert", 10);
        validateElementVisible("verification_email_title");
        HelperData.setLastActionPage(new VerifyEmailPage(iosDriver));
    }

    //endregion Validation
}
