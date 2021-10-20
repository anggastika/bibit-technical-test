package bukalapak.pageObject;

import bukalapak.TestInstrument;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class ResetPasswordPage extends BasePage {

    public ResetPasswordPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void typeFormResetPassword(String credential) {
        typeAndEnterValueWithTimeOut("login_email_text_field", credential);
    }

    public void validateSuccessMessage() {
        isElementVisible("login_reset_password_success_message");
    }

    public void typeNewAndConfirmationPassword(String newPassword) {
        typeAndEnterValueWithTimeOut("new_password_field", newPassword);
        typeAndEnterValueWithTimeOut("new_password_confirmation_field", newPassword);
        tapElement("change_password_btn");
    }

    public void typeNewPassword(String newPassword) {
        validateExist("new_password_field", 10);
        typeAndEnterValue("new_password_field", TestInstrument.dotenv.get(newPassword));
    }

    public void typeConfirmationPassword(String confirmationPassword) {
        typeAndEnterValue("new_password_confirmation_field", TestInstrument.dotenv.get(confirmationPassword));
    }

    public void clickUbahPasswordButton() {
        int interationCount = 0;
        int iteration = 6;

        tapElement("change_password_btn");

        while (isElementVisible("reset_password_loading_icon") && interationCount < iteration) {
            waitFor(5);
            interationCount++;

            if (isElementVisible("reset_password_loading_icon") && interationCount == iteration) {
                // 8 = (isElementVisible() = ~3s) + waitFor(5)
                Assert.fail("Change email validation proceed take more than " + 8 * iteration + "second");
            }
        }

        if (isElementVisible("reset_password_ok_button", 5)) {
            tapElement("reset_password_ok_button");
        }
    }
}
