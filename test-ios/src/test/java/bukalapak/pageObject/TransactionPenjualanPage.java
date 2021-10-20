package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.STRIPEData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class TransactionPenjualanPage extends BasePage {

    public TransactionPenjualanPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void selectFilterDibayar() {
        clickFilterIcon();
        tapElement("penjualan_dibayar_button");
    }

    public void selectFilterDiProses() {
        clickFilterIcon();
        tapElement("penjualan_diProses_button");
    }

    public void clickFilterIcon() {
        if (isElementClickable("penjualan_filter_icon_name_button")) {
            tapElement("penjualan_filter_icon_name_button");
        } else if (isElementVisible("penjualan_filter_icon_label_button")) {
            tapElement("penjualan_filter_icon_label_button");
        } else {
            tapElement("penjualan_filter_icon_xpath_button");
        }
    }

    public void selectTerapkan() {
        tapElement("penjualan_terapkan_button");
    }

    public void selectFirstTransaction() {
        tapElement("penjualan_first_transaction_text");
    }

    public void setTransactionNumber() {
        STRIPEData.setNomorTransaksi(getElementValue("penjualan_transaction_number"));
    }

    public void clickResetButton() {
        clickFilterIcon();
        waitForVisibilityOf("penjualan_reset_filter_button");
        tapElement("penjualan_reset_filter_button");
    }

    public void clickDibayarButton() {
        tapElement("penjualan_reset_filter_button");
    }

    public void typeOnSearchButton() {
        String numberTransaction = STRIPEData.getNomorTransaksi();
        typeAndEnterValueWithTimeOut("cari_tagihan_field", numberTransaction);
        tapElement("penjualan_click_first_transaction_text");
    }

    public void validationProductNotFound() {
        assertEquals(getTextFromElement("penjualan_detail_transaksi_status_dikembalikan_button"), "DIKEMBALIKAN");
        HelperData.setLastActionPage(new BelumDiprosesPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
    }

    public void typeCourierName(String jasaPengiriman) {
        typeAndEnterValueWithTimeOut("penjualan_input_nama_jasa_pengiriman", jasaPengiriman);
        STRIPEData.setJasaPengiriman(jasaPengiriman);
    }

    public void getCourierName() {
        String getCourierName = getElementValue("penjualan_jasa_pengiriman");
        waitForVisibilityOf("konfirmasi_kirim_button", 15);
        tapElement("konfirmasi_kirim_button");
        waitForVisibilityOf("penjualan_jasa_pengiriman_before_change");
        String assertCourier = getTextFromElement("penjualan_jasa_pengiriman_before_change").replaceAll("Jasa pengiriman pilihan pembeli:\n", "");
        assertEquals(getCourierName, assertCourier);
    }

    public void getCourierNameOnDetail() {
        STRIPEData.setJasaPengiriman(getElementValue("penjualan_jasa_pengiriman"));
    }
}
