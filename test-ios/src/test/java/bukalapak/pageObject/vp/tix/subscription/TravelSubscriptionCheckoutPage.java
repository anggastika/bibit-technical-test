package bukalapak.pageObject.vp.tix.subscription;

import bukalapak.data.HelperData;
import bukalapak.data.SubscriptionData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelSubscriptionCheckoutPage extends VpBasePage {
    public TravelSubscriptionCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateCheckoutDetail() {
        waitForVisibilityOf("SUBSCRIPTION_CHECKOUT_PAGE_PACKAGE_NAME", 15);

        validateValue().equals(SubscriptionData.getPackageSkuName(),
                getText("SUBSCRIPTION_CHECKOUT_PAGE_PACKAGE_NAME"));
        validateValue().equals(SubscriptionData.getExpiryTime(),
                getText("SUBSCRIPTION_CHECKOUT_PAGE_EXPIRY_TIME").toLowerCase());
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
