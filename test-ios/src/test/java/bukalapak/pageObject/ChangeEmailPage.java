package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;

import static bukalapak.TestInstrument.dotenv;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */

public class ChangeEmailPage extends BasePage {
    public ChangeEmailPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    // input email field with specific email (from ENV as prefix email)
    // within additional random number after prefix email
    public void inputUnregisteredEmail() {
        int randomNum = RandomUtils.nextInt(1000, 9999);

        verifyElementExist("change_email_email_field");
        typeAndEnterValue("change_email_email_field",
                dotenv.get("CHANGE_EMAIL_PREFIX_EMAIL") + randomNum + dotenv.get("CHANGE_EMAIL_EMAIL_PROVIDER"));
    }

    public void typeOnPasswordField(String password) {
        typeAndEnterValue("change_email_password_field", dotenv.get(password));
    }

    public void clickSimpanButton() {
        int interationCount = 0;
        int iteration = 6;

        tapElement("change_email_simpan_button");

        while (isElementVisible("change_email_loading_icon") && interationCount < iteration) {
            waitFor(5);
            interationCount++;

            if (isElementVisible("change_email_loading_icon") && interationCount == iteration) {
                // 8 = (isElementVisible() = ~3s) + waitFor(5)
                Assert.fail("Change email validation proceed take more than " + 8 * iteration + "second");
            }
        }
    }
}
