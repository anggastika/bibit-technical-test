package bukalapak.pageObject.vp.tix.subscription;

import bukalapak.data.HelperData;
import bukalapak.data.SubscriptionData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 04/06/20, Thu
 **/
public class TravelSubscriptionDetailPage extends VpBasePage {

    public TravelSubscriptionDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        verifyElementDisplayed("SUBSCRIPTION_DETAIL_PAGE_TITLE_TEXT");
        verifyElementDisplayed("SUBSCRIPTION_PACKAGE_EXPIRY_TIME_TEXT");
        verifyElementDisplayed("SUBSCRIPTION_DETAIL_PAGE_PACKAGE_NAME_TEXT");
        verifyElementDisplayed("SUBSCRIPTION_DETAIL_PAGE_HOW_TO_USE_VOUCHER_TEXT");
        verifyElementDisplayed("SUBSCRIPTION_DETAIL_PAGE_SNK_TEXT");
        verifyElementDisplayed("SUBSCRIPTION_DETAIL_PAGE_PACKAGE_PRICE_TEXT");
        verifyElementDisplayed("SUBSCRIPTION_DETAIL_PAGE_BUY_BUTTON");
    }
    
    public void setPackagePrice() {
        SubscriptionData.setPackagePrice(getIntegerFromTextElement("SUBSCRIPTION_DETAIL_PAGE_PACKAGE_PRICE_TEXT"));
    }

    public void validatePackageName() {
        validateValue().equals(SubscriptionData.getPackageSkuName(),
                getTextFromElement("SUBSCRIPTION_DETAIL_PAGE_PACKAGE_NAME_TEXT"));
        HelperData.setLastActionPage(new TravelSubscriptionDetailPage(iosDriver));
    }

    public void validatePackageExpiryTime() {
        validateValue().equals(SubscriptionData.getExpiryTime(),
                getTextFromElement("SUBSCRIPTION_PACKAGE_EXPIRY_TIME_TEXT").toLowerCase());
        HelperData.setLastActionPage(new TravelSubscriptionDetailPage(iosDriver));
    }

    public void tapOnSyaratDanKetentuan() {
        waitForVisibilityOf("SUBSCRIPTION_DETAIL_PAGE_SNK_TEXT", 10);
        tapElement("SUBSCRIPTION_DETAIL_PAGE_SNK_TEXT");
    }

    public void tapOnHowToBuySubscriptionPackage() {
        waitForVisibilityOf("SUBSCRIPTION_DETAIL_PAGE_HOW_TO_USE_VOUCHER_TEXT", 10);
        tapElement("SUBSCRIPTION_DETAIL_PAGE_HOW_TO_USE_VOUCHER_TEXT");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapOnBuyButton() {
        tapElement("SUBSCRIPTION_DETAIL_PAGE_BUY_BUTTON");
    }
}
