package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MetodePembayaranPage extends BasePage {

    public MetodePembayaranPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnMetodePembayaranPage() {
        waitForVisibilityOf("metode_pembayaran_page_title", 10);
        HelperData.setLastActionPage(new MetodePembayaranPage(iosDriver));
    }

    public void selectPaymentMethod(String paymentMethod) {
        swipeUpToElement(paymentMethod);
        tapElement(paymentMethod);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void selectPaymentMethodRecurringPayment(String paymentMethod) {
        switch (paymentMethod) {
            case "Kartu Debit Atau Kredit":
                tapElement("checkout_metode_bayar_text");
                waitForVisibilityOf("metode_pembayaran_page_title", 10);
                tapElement("metode_pembayaran_cc_text");
                break;
            default:
                break;
        }
    }

    public void verifySaldoFreeze() {
        swipeUpToElement("metode_pembayaran_saldo_text");
        verifyElementDisplayed("metode_pembayaran_saldo_freeze");
    }
}
