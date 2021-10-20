package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class OneKlikAccountCheckoutPage extends BasePage {
    public OneKlikAccountCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnOneKlikAccountCheckoutPage() {
        verifyElementExist("oneklik_account_checkout_page_title");
        HelperData.setLastActionPage(new OneKlikAccountCheckoutPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
