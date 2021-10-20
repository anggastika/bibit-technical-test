package bukalapak.pageObject.vp.prepaid.paketdata;

import bukalapak.data.TransactionData;
import bukalapak.data.vp.prepaid.PaketDataData;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PaketDataInvoiceDetailsPage extends VpInvoiceDetailsPage {

    public PaketDataInvoiceDetailsPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    private void validateSectionInformasiTransaksi() {
        validateElementWithText("vp_invoice_details_informasi_transaksi_text_produk", "Paket Data");

        String phoneNumber = getText("vp_invoice_details_informasi_transaksi_text_no_handphone").replaceAll("-", "");

        validateValue().equals(PaketDataData.getPhoneNumber(), phoneNumber);
        validateElementWithText("vp_invoice_details_informasi_transaksi_text_jenis_paket", PaketDataData.getDenominationName());
    }

    public void validateTransactionData() {
        validateElementWithText("vp_invoice_details_informasi_pesanan_text_details_paket_data", PaketDataData.getDenominationPrice());
        swipeToBottom();
        validateSectionInformasiTransaksi();
    }

    public void validateTransactionDataFromHistory() {
        if (!TransactionData.getPaymentStatus().equals("Menunggu Pembayaran"))
            tapElement("vp_invoice_details_section_informasi_pesanan");
        validateTransactionStatus(TransactionData.getPaymentStatus());
        validateElementWithText("vp_invoice_details_text_total_amount", TransactionData.getTotalPrice());
        swipeToBottom();
        validateSectionInformasiTransaksi();
    }
}
