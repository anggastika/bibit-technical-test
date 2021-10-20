package bukalapak.pageObject.vp.tix.subscription;

import bukalapak.data.HelperData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelSubscriptionHowToBuyPage extends VpBasePage {
    public TravelSubscriptionHowToBuyPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validatePageContent() {
        validateDisplayed("SUBSCRIPTION_HOW_TO_BUY_CONTENT_1");
        validateExist("SUBSCRIPTION_HOW_TO_BUY_CONTENT_2");
        validateDisplayed("SUBSCRIPTION_HOW_TO_BUY_CONTENT_3");
        validateDisplayed("SUBSCRIPTION_HOW_TO_BUY_CONTENT_4");
        HelperData.setLastActionPage(new TravelSubscriptionHowToBuyPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
