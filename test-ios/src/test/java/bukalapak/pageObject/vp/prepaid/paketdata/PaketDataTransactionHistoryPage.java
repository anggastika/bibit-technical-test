package bukalapak.pageObject.vp.prepaid.paketdata;

import bukalapak.data.TransactionData;
import bukalapak.data.vp.prepaid.PaketDataData;
import bukalapak.pageObject.vp.base.VpTransactionHistoryPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PaketDataTransactionHistoryPage extends VpTransactionHistoryPage {

    public PaketDataTransactionHistoryPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void setTransactionData() {
        PaketDataData.setDenominationName(getText("vp_transaction_history_item_text_product_title"));
        TransactionData.setTotalPrice(getText("vp_transaction_history_item_text_product_price"));
        PaketDataData.setPhoneNumber(getText("vp_transaction_history_item_text_product_sub_description"));
        TransactionData.setPaymentStatus(getText("vp_transaction_history_item_text_product_payment_status"));
    }
}
