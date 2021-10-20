package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PaymentStatusPage extends BasePage {

    public PaymentStatusPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void validateDANAPaymentIsSuccess() {
        verifyElementExist("DANA_payment_success");
    }

    public void validateBalanceIsNotEnough() {
        verifyElementExist("DANA_balance_not_enough");
        HelperData.setLastActionPage(new PaymentStatusPage(iosDriver));
    }

    public void validateUserIsNotBinding() {
        verifyElementExist("DANA_user_not_binding");
        HelperData.setLastActionPage(new PaymentStatusPage(iosDriver));
    }

    public void tapSeePaymentDetailButton() {
        swipeUpToElement("payment_detail_button");
        tapElement("payment_detail_button");
    }

    public void tapSeePaymentDetailOrder() {
        swipeUpToElement("payment_detail_order_button");
        tapElement("payment_detail_order_button");
    }

    public void tapSeePaymentDetailOrderMP() {
        swipeUpToElement("payment_detail_recomendation_button");
        tapElement("payment_detail_order_button_mp");
    }

    public void closePaymentStatus() {
        waitForVisibilityOf("payment_confirmation_batas_waktu_text",10);
        tapElement("checkout_shipment_back_button");
    }

    public void tapTransactionDetail() {
        if (isElementVisible("pajak_daerah_pay_button")) {
            LogUtil.info("Selesaikan dulu 5 transaksi sebelumnya");
        } else {
            tapElement("payment_transaction_detail");
        }
    }

    public void validatePaymentMethod(String paymentMethod) {
        if (paymentMethod.equalsIgnoreCase("Transfer Bank")) {
            verifyElementExist("INVOICE_MENUNGGU_PEMBAYARAN_TEXT");
            verifyElementExist("INVOICE_TRANSFER_AS_METODE_PEMBAYARAN_TEXT");
        }
    }

    public void validateCCPaymentIsSuccess() {
        waitForVisibilityOf("cc_payment_success", 40);
        verifyElementExist("cc_payment_success");
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
