package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by Ayu Musfita on 23/01/20.
 */
public class CheckoutNonMarketplaceConfirmationPage extends BasePage {

    public CheckoutNonMarketplaceConfirmationPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        verifyElementExist("CHECKOUT_NONMARKETPLACE_NOMOR_PESANAN_TEXT");
        HelperData.setLastActionPage(new CheckoutNonMarketplaceConfirmationPage(iosDriver));
    }

    public void setOnDataTransaction() {
        TransactionData.setTotalPayment(getIntegerFromValueElement("CHECKOUT_NONMARKETPLACE_TOTAL_BAYAR_PESANAN_TEXT"));
        TransactionData.setInvoiceNo(getElementValue("CHECKOUT_NONMARKETPLACE_NOMOR_PESANAN_TEXT").replaceAll("#", ""));
        HelperData.setLastActionPage(new CheckoutNonMarketplaceConfirmationPage(iosDriver));
    }

    public void tapOnButtonLihatTagihanPembayaran() {
        swipeUpToElement("CHECKOUT_NONMARKETPLACE_LIHAT_PESANAN_BUTTON");
        tapElement("CHECKOUT_NONMARKETPLACE_LIHAT_PESANAN_BUTTON");
        HelperData.setLastActionPage(new CheckoutNonMarketplaceConfirmationPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
