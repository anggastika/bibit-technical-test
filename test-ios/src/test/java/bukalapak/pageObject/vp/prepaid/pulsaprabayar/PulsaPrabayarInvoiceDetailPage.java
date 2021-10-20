package bukalapak.pageObject.vp.prepaid.pulsaprabayar;

import bukalapak.data.vp.prepaid.PulsaPrabayarData;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PulsaPrabayarInvoiceDetailPage extends VpInvoiceDetailsPage {
    public PulsaPrabayarInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionData () {
        validateElementWithText("vp_invoice_details_informasi_pesanan_text_details_pulsa_prabayar", PulsaPrabayarData.getPackagePrice());
        swipeToBottom();
        validateElementWithText("vp_invoice_details_informasi_transaksi_text_produk", "Pulsa Prabayar");

        String actualPhoneNumber = getText("vp_invoice_details_informasi_transaksi_text_no_handphone");

        validateValue().equals(actualPhoneNumber.replaceAll("-", ""), PulsaPrabayarData.getPhoneNumber());
        validateElementWithText("vp_invoice_details_informasi_transaksi_text_pulsa", PulsaPrabayarData.getPackageName());
    }
}
