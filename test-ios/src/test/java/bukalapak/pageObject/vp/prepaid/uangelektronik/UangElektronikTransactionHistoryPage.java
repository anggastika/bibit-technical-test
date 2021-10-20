package bukalapak.pageObject.vp.prepaid.uangelektronik;

import bukalapak.data.TransactionData;
import bukalapak.data.vp.prepaid.ListrikPrabayarData;
import bukalapak.data.vp.prepaid.UangElektronikData;
import bukalapak.pageObject.vp.base.VpTransactionHistoryPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class UangElektronikTransactionHistoryPage extends VpTransactionHistoryPage {

    public UangElektronikTransactionHistoryPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void setTransactionData() {
        TransactionData.setTotalPrice(getText("vp_transaction_history_item_text_product_price"));
        UangElektronikData.setCardNumber(getText("vp_transaction_history_item_text_product_sub_description"));
        TransactionData.setPaymentStatus(getText("vp_transaction_history_item_text_product_payment_status"));
    }
}
