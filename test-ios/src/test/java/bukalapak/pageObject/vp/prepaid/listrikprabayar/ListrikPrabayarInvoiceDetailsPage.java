package bukalapak.pageObject.vp.prepaid.listrikprabayar;

import bukalapak.data.TransactionData;
import bukalapak.data.vp.prepaid.ListrikPrabayarData;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ListrikPrabayarInvoiceDetailsPage extends VpInvoiceDetailsPage {

    public ListrikPrabayarInvoiceDetailsPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    private void validateSectionInformasiTransaksi() {
        validateElementWithText("vp_invoice_details_informasi_transaksi_text_produk", "Listrik Prabayar");
        validateElementWithText("vp_invoice_details_informasi_transaksi_text_id_pelanggan", ListrikPrabayarData.getCustomerId());
        validateElementWithText("vp_invoice_details_informasi_transaksi_text_nama_pelanggan", ListrikPrabayarData.getFullName());
    }

    public void validateTransactionData() {
        validateElementWithText("vp_invoice_details_informasi_pesanan_text_details_listrik_prabayar", ListrikPrabayarData.getDenominationPrice());
        swipeToBottom();
        validateSectionInformasiTransaksi();
        validateElementWithText("vp_invoice_details_informasi_transaksi_text_tarif_daya", ListrikPrabayarData.getPower());
    }

    public void validateTransactionDataFromHistory() {
        if (!TransactionData.getPaymentStatus().equals("Menunggu Pembayaran"))
            tapElement("vp_invoice_details_section_informasi_pesanan");
        validateTransactionStatus(TransactionData.getPaymentStatus());
        swipeToBottom();
        validateSectionInformasiTransaksi();
    }
}
