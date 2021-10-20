package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class PaymentPage extends BasePage {

    public PaymentPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPaymentPage() {
        verifyElementExist("payment_buka_dana_radio_button");
        HelperData.setLastActionPage(new PaymentPage(iosDriver));
    }

    public void choosePayment(String element) {
        swipeDownToElement(element);
        tapElement(element);
    }

    public void clickOnBayarButton() {
        swipeUpToElement("payment_bayar_button");
        tapElement("payment_bayar_button");
    }

    public void checkIsPageBayarAjaExist() {
        assertTrue(isElementVisible("bayar_aja_page"), "Tidak berhasil Redirect ke Page QR");
    }

    public void selectPayment() {
        swipeUpToElement("payment_credit_card");
        tapElement("payment_credit_card", 5);
        tapElement("payment_use_this_method", 5);
    }

    public void userTapOnInputCC() {
        if (isElementExist("payment_isi_detail_kartu_button", 15)) {
            tapElement("payment_isi_detail_kartu_button");
        } else {
            tapElement("payment_isi_detail_kartu_id", 10);
        }
    }

    public void typeCardNumber(String card_number) {
        if (isElementExist("payment_input_card_number", 10)) {
            typeAndEnterValue("payment_input_card_number", card_number);
        } else {
            waitForVisibilityOf("payment_input_card_number_name", 10);
            typeAndEnterValue("payment_input_card_number_name", card_number);
        }
    }

    public void fillDetailCreditCard() {
        if (isElementExist("payment_cc_month_dropdown", 5)) {
            tapElement("payment_cc_month_dropdown", 5);
            tapElement("payment_cc_month_value", 5);
        } else {
            tapElement("payment_cc_month_navigation", 5);
            tapElement("payment_cc_month_value", 5);
        }
        tapElement("payment_cc_year_dropdown", 5);
        tapElement("payment_cc_year_value", 5);
        typeAndEnterValue("payment_cc_input_cvv_field","123");
        tapElement("payment_cc_detail_tipe_pembayaran_dropdown", 5);
        tapElement("payment_cc_detail_pembayaran_penuh_option", 5);
    }
}
