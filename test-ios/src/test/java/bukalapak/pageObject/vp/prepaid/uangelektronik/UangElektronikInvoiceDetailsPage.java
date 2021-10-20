package bukalapak.pageObject.vp.prepaid.uangelektronik;

import bukalapak.data.TransactionData;
import bukalapak.data.vp.prepaid.UangElektronikData;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class UangElektronikInvoiceDetailsPage extends VpInvoiceDetailsPage {

    public UangElektronikInvoiceDetailsPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionData() {
        validateElementWithText("vp_invoice_details_informasi_pesanan_text_details_uang_elektronik", UangElektronikData.getDenominationPrice());
        swipeToBottom();
        validateElementWithText("vp_invoice_details_informasi_transaksi_text_nomor_kartu", UangElektronikData.getCardNumber());
    }

    public void validateTransactionDataFromHistory() {
        if (!TransactionData.getPaymentStatus().equals("Menunggu Pembayaran"))
            tapElement("vp_invoice_details_section_informasi_pesanan");
        validateTransactionStatus(TransactionData.getPaymentStatus());
        swipeToBottom();
        validateElementWithText("vp_invoice_details_informasi_transaksi_text_nomor_kartu", UangElektronikData.getCardNumber());
    }

    public void tapOnButtonCaraUpdateSaldo() {
        tapElement("vp_invoice_details_informasi_transaksi_button_cara_update_saldo");
    }

    public void validateRedirectedToWebview() {
        waitForVisibilityOf("uang_elektronik_webview_text_title", LONG_TIMEOUT);
        validateToolbarTitle("Cara Update Saldo");
    }
}
