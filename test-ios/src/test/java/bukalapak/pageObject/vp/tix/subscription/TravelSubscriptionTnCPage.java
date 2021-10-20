package bukalapak.pageObject.vp.tix.subscription;

import bukalapak.data.HelperData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelSubscriptionTnCPage extends VpBasePage {
    public TravelSubscriptionTnCPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        validateDisplayed("SUBSCRIPTION_TNC_HEADER");
        HelperData.setLastActionPage(new TravelSubscriptionTnCPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
