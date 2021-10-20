package bukalapak.pageObject.vp.base;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by ferawati h.p on 15/07/2020.
 */
public class VpTransactionHistoryPage extends VpBasePage {
    public VpTransactionHistoryPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        validateToolbarTitle("Riwayat Transaksi");
        HelperData.setLastActionPage(new VpTransactionHistoryPage(iosDriver));
    }

    public void setLatestTransactionStatus() {
        TransactionData.setPaymentStatus(getText("vp_transaction_history_item_text_product_payment_status"));
    }

    public void validateEmptyTransaction() {
        waitForVisibilityOf("vp_transaction_history_image_belum_ada_transaksi");
        verifyElementDisplayed("vp_transaction_history_text_belum_ada_transaksi_title");
        verifyElementDisplayed("vp_transaction_history_text_belum_ada_transaksi_description");
    }

    public void validateItemTransactionHistory(boolean isDisplayed) {
        if (isDisplayed) {
            waitForVisibilityOf("vp_transaction_history_item_text_product_title");
            verifyElementDisplayed("vp_transaction_history_item_text_product_sub_title");
            verifyElementDisplayed("vp_transaction_history_item_text_product_sub_description");
            verifyElementDisplayed("vp_transaction_history_item_text_product_price");
        } else {
            waitForVisibilityOf("vp_transaction_history_image_belum_ada_transaksi");
            verifyElementDisplayed("vp_transaction_history_text_belum_ada_transaksi_title");
            verifyElementDisplayed("vp_transaction_history_text_belum_ada_transaksi_description");
        }
    }

    public void tapOnFilterStatus(String status) {
        waitForVisibilityOf("vp_transaction_history_button_filter_semua");
        tapElement("vp_transaction_history_button_filter_" + status.toLowerCase().replaceAll(" ", "_"));
    }

    public void validateStatus(String status) {
        waitForVisibilityOf("vp_transaction_history_item_text_product_payment_status");
        if (!status.equals("Menunggu Pembayaran")) verifyElementDisplayed("vp_transaction_history_item_button_detail");
        else verifyElementDisplayed("vp_transaction_history_item_button_bayar");
    }

    public void tapOnItemTransactionHistory() {
        tapElement("vp_transaction_history_item_button");
    }
}
