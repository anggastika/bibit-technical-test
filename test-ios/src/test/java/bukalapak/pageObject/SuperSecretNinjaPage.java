package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SuperSecretNinjaPage extends BasePage {

    public SuperSecretNinjaPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnSuperSecretNinjaPage() {
        assertTrue(isElementVisible("secretninja_navigation_bar"));
        HelperData.setLastActionPage(new SuperSecretNinjaPage(iosDriver));
    }

    public void openDeepLink(String deeplinkURL) {
        swipeDownToClickableElement("deeplink_tester_label");
        tapElement("deeplink_tester_label");
        typeAndEnterValueWithTimeOut("deeplink_tester_field", deeplinkURL);
        tapElement("ok_deeplink_label");
        HelperData.setLastActionPage(new InfluencerPage(iosDriver));
    }

    public void goToHomePage() {
        if (isElementVisible("secretninja_navigation_bar")) {
            tapElement("secretninja_back_button");
        }

        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
