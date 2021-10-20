package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */

public class EditPasswordPage extends BasePage {
    public EditPasswordPage(IOSDriver<IOSElement> iosDriver) { super(iosDriver); }

    //region Navigation
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    //endregion Navigation

    //region Simple action
    public void typeOnCurrentPasswordText(String currentpass) {
        typeAndEnterValue("password_sekarang_field", currentpass);
    }

    public void typeOnNewPasswordText(String newpass) {
        typeAndEnterValue("password_baru_field", newpass);
    }

    public void typeOnConfirmPasswordText(String newpass) {
        typeAndEnterValue("confirmation_password_baru_field", newpass);
    }

    //endregion Simple action

    //region Validation
    public void userOnEditPasswordPage() {
        verifyElementExist("password_page_title",5,"element is not exist");
        HelperData.setLastActionPage(new EditPasswordPage(iosDriver));
    }

    public void userOnOnboardingPage() {
        verifyElementExist("onboarding_login_page",10,"element is not exist");
        HelperData.setLastActionPage(new EditPasswordPage(iosDriver));
    }

    //endregion Validation
}
