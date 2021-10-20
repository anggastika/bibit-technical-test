package bukalapak.pageObject.vp.tix.subscription;

import bukalapak.data.HelperData;
import bukalapak.data.SubscriptionData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import static bukalapak.TestInstrument.dotenv;

public class TravelSubscriptionLandingPage extends VpBasePage {
    public TravelSubscriptionLandingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        delay(30000);
        verifyElementDisplayed("SUBSCRIPTION_LANDING_PAGE_PAKET_LANGGANAN");
        verifyElementDisplayed("SUBSCRIPTION_LANDING_PAGE_PAKET_BISA_DIBELI");
        verifyElementDisplayed("SUBSCRIPTION_LANDING_PAGE_VOUCHER_PRICE");
        verifyElementDisplayed("SUBSCRIPTION_LANDING_PAGE_VOUCHER_NAME");
    }

    public void tapOnPackage() {
        String sku_index = dotenv.get("SUBSCRIPTION_SKU_INDEX");

        Assert.assertNotNull(sku_index);
        tapElement(constructLocator("SUBSCRIPTION_LANDING_PAGE_PACKAGE", Integer.parseInt(sku_index)));
        HelperData.setLastActionPage(new TravelSubscriptionLandingPage(iosDriver));
    }

    public void swipeToPackage() {
        String sku_index = dotenv.get("SUBSCRIPTION_SKU_INDEX");

        Assert.assertNotNull(sku_index);
        swipeUpToElement(constructLocator("SUBSCRIPTION_LANDING_PAGE_PACKAGE", Integer.parseInt(sku_index)));
    }

    public void setPackageName() {
        SubscriptionData.setPackageDealsName(getTextFromElement(constructLocator("SUBSCRIPTION_LANDING_PAGE_PACKAGE_DEALS_NAME", dotenv.get("SUBSCRIPTION_SKU_INDEX"))));
        SubscriptionData.setPackageSkuName(getTextFromElement(constructLocator("SUBSCRIPTION_LANDING_PAGE_PACKAGE_SKU_NAME", dotenv.get("SUBSCRIPTION_SKU_INDEX"))));
    }

    public void setExpiryTime() {
        String sku_index = dotenv.get("SUBSCRIPTION_SKU_INDEX");

        Assert.assertNotNull(sku_index);
        SubscriptionData.setExpiryTime(
                getTextFromElement("SUBSCRIPTION_PACKAGE_EXPIRY_TIME_TEXT", Integer.parseInt(sku_index) - 1).toLowerCase());
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
