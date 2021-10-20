package bukalapak.pageObject.vp.tix.subscription;

import bukalapak.data.HelperData;
import bukalapak.data.SubscriptionData;
import bukalapak.data.TransactionData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelSubscriptionTransactionDetailPage extends VpBasePage {

    public TravelSubscriptionTransactionDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateSubscriptionData() {
        String payment = TransactionData.getPaymentMethod();

        if (payment.contains("VA")) {
            payment = "Virtual Account";
        }
        assertTextContains(payment,  getText("SUBSCRIPTION_TRANSACTION_DETAIL_PAYMENT_METHOD"));
        assertEquals(SubscriptionData.getPackageSkuName(),
                getText("SUBSCRIPTION_TRANSACTION_DETAIL_SKU_NAME").replaceAll("1x ", ""));
        swipeUpToElement("SUBSCRIPTION_TRANSACTION_DETAIL_INFO_PARTNER");

        HelperData.setLastActionPage(new TravelSubscriptionTransactionDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
