package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.STRIPEData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class CetakPesananPenjualanPage extends BasePage {

    public CetakPesananPenjualanPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validationNoteOnDetailPenjualan() {
        String elementCatatanPembeli = getTextFromElement("cetak_pesanan_penjualan_catatan_pembeli_text");
        String catatanPembeli = elementCatatanPembeli.replaceAll("\n", "");
        assertEquals(catatanPembeli, STRIPEData.getCatatanPembeli());
    }

    private String getNamaPenerimaTextView() {
        String elementNamaPenerima = getTextFromElement("cetak_pesanan_penjualan_nama_penerima_text");
        int namaPenerima = elementNamaPenerima.indexOf("-");
        return elementNamaPenerima.substring(0, namaPenerima);
    }

    public void validationReceiverNameOnDetailPenjualan() {
        assertEquals(getNamaPenerimaTextView(), STRIPEData.getNamaPenerima());
    }

    public void validationCourierOnDetailPenjualan() {
        String elementNamaKurir = getTextFromElement("cetak_pesanan_penjualan_jasa_pengiriman_text");
        String deleteEnterNamaKurir = elementNamaKurir.replaceAll("\n", "");
        String getJasaPengirim = deleteEnterNamaKurir.replaceAll("Jasa Pengiriman : ", "");
        assertEquals(getJasaPengirim, STRIPEData.getJasaPengiriman());
        HelperData.setLastActionPage(new CetakPesananPenjualanPage(iosDriver));
    }

    public void validationTransactionNumberOnDetailPenjualan() {
        String elementTransactionNumber = getTextFromElement("cetak_pesanan_penjualan_nomor_trasaksi_text");
        String getElementTransactionNumber = elementTransactionNumber.substring(elementTransactionNumber.indexOf("1"));
        String deleteEnterTransactionNumber = getElementTransactionNumber.replaceAll("\n", "");
        assertEquals(deleteEnterTransactionNumber, STRIPEData.getNomorTransaksi());
    }

    public void clickOnTampilkanButton() {
        waitForVisibilityOf("penjualan_button_tampilan_button", 10);
        tapElement("penjualan_button_tampilan_button");
        waitForVisibilityOf("cetak_pesanan_penjualan_nama_penerima_text", 15);
    }

    public void clickCheckboxCetakPesanan() {
        tapElement("penjualan_checkbox_detail_item_checkbox");
    }
}
