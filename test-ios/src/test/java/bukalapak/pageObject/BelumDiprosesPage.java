package bukalapak.pageObject;

import bukalapak.data.STRIPEData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BelumDiprosesPage extends BasePage {

    public BelumDiprosesPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void chooseCheckbox() {
        isElementVisible("transaction_checkbox", 10);
        tapElement("transaction_checkbox");
    }

    public void tapOnProsesPesanan() {
        tapElement("ubah_status_sekaligus_proses_button");
    }

    public void tapOnConfirmProses() {
        tapElement("proses_kirim_sekaligus_confirm_proses_button");
        waitFor(5);
    }

    public void getProductName() {
        waitFor(10);
       STRIPEData.setProductName(getElementValue("product_name_text"));
    }

    public void tapOnTolakPesananButton() {
        tapElement("proses_kirim_sekaligus_tolak_pesanan_button");
        tapElement("penjualan_tolak_pesanan_alasan_button");
        tapElement("penjualan_tolak_pesanan_button");
    }

    public void goToHomePage() {
        backToHomePage();
    }

    public void clickTolakPesananSingleTransaction() {
        swipeDownToElement("proses_kirim_sekaligus_tolak_pesanan_button");
        tapElement("proses_kirim_sekaligus_tolak_pesanan_button");
        tapElement("penjualan_tolak_pesanan_reason_on_single");
        tapElement("penjualan_simpan_reason_tolak_pesanan");
        waitForVisibilityOf("penjualan_detail_transaksi_status_dikembalikan_button", 15);
    }

    public void backToSellPage() {
        // tried another waitfor recomended but doesn't works
        waitFor(10);
        tapElement("base_back_button");
    }

}
