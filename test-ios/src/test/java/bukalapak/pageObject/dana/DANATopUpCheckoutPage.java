package bukalapak.pageObject.dana;

import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by DANA squad
 */

public class DANATopUpCheckoutPage extends BasePage {

    public DANATopUpCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        verifyElementDisplayed("dana_topup_checkout_header_text");
        verifyElementDisplayed("dana_topup_checkout_detail_pembelian_text");
        verifyElementDisplayed("dana_topup_checkout_metode_pembayaran_text");
        verifyElementDisplayed("dana_topup_checkout_rincian_harga_text");
        verifyElementDisplayed("dana_topup_checkout_nominal_topup_text");
        verifyElementDisplayed("dana_topup_checkout_nomor_telepon_text");
    }

    public void validateMetodePembayaranDisplayed() {
        verifyElementDisplayed("dana_topup_checkout_metode_pembayaran_text");
    }

    public void tapMetodePembayaran() {
        tapElement("dana_topup_checkout_metode_pembayaran_text", 10);
    }

    public void validateBayarButtonDisplayed() {
        verifyElementDisplayed("dana_topup_checkout_bayar_button_text");
    }

    public void tapBayarButton() {
        tapElement("dana_topup_checkout_bayar_button_text", 10);
    }

    public void validateDANAPaymentMethodNotDisplayed() {
        validateNotDisplayed("checkout_non_marketplace_dana_choosen");
    }
}
