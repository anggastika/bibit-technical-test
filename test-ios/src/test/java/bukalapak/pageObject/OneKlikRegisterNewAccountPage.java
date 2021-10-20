package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class OneKlikRegisterNewAccountPage extends BasePage {
    public OneKlikRegisterNewAccountPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnOneKlikRegisterNewAccountPage() {
        verifyElementExist("oneklik_register_new_account_page_title");
        HelperData.setLastActionPage(new OneKlikRegisterNewAccountPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
