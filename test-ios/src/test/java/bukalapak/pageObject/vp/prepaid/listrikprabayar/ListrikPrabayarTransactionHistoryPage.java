package bukalapak.pageObject.vp.prepaid.listrikprabayar;

import bukalapak.data.TransactionData;
import bukalapak.data.vp.prepaid.ListrikPrabayarData;
import bukalapak.data.vp.prepaid.PaketDataData;
import bukalapak.pageObject.vp.base.VpTransactionHistoryPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ListrikPrabayarTransactionHistoryPage extends VpTransactionHistoryPage {

    public ListrikPrabayarTransactionHistoryPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void setTransactionData() {
        TransactionData.setTotalPrice(getText("vp_transaction_history_item_text_product_price"));
        ListrikPrabayarData.setFullName(getText("vp_transaction_history_item_text_product_sub_title"));
        ListrikPrabayarData.setCustomerId(getText("vp_transaction_history_item_text_product_sub_description"));
        TransactionData.setPaymentStatus(getText("vp_transaction_history_item_text_product_payment_status"));
    }
}
