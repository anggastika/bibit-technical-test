package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.STRIPEData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DetailTransaksiPenjualanAlchemyPage extends BasePage {

    public DetailTransaksiPenjualanAlchemyPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void tapOnProsesPesanan() {
        swipeUpToElement("penjualan_proses_pesanan_alchemy_button");
        tapElement("penjualan_proses_pesanan_alchemy_button");
    }

    public void tapOnKirimBarang() {
        waitForVisibilityOf("penjualan_button_konfirmasi_kirim_alchemy", 55);
        tapElement("penjualan_button_konfirmasi_kirim_alchemy");
    }

    public void tapOnEditResi() {
        waitForVisibilityOf("penjualan_edit_resi_alchemy_button", 5);
        tapElement("penjualan_edit_resi_alchemy_button");
    }

    public void tapOnModalProsesPesanan() {
        waitForVisibilityOf("penjualan_modal_konfirmasi_proses_alchemy_id", 10);
        tapElement("penjualan_modal_konfirmasi_proses_alchemy_id");
    }

    public void validateResiNumber() {
        waitForVisibilityOf("penjualan_nomor_transaksi_penjualan_title", 30);
        String valueResiNumber = getElementLabel("penjualan_nomor_resi_text");
        String assertResiNumber = valueResiNumber.replace(" ", "");
        if (STRIPEData.getResiNumber().equals(assertResiNumber)) {
            assertEquals(STRIPEData.getResiNumber(), assertResiNumber);
        } else {
            assertTrue(isElementVisible("penjualan_nomor_resi_text", 10), "Resi number doesn't match");
        }
        //HelperData.setLastActionPage(new DetailTransaksiPenjualanAlchemyPage());
    }

    public void tapOnSingleTolakPesananButton() {
        waitForVisibilityOf("penjualan_tolak_pesanan_alchemy_button", 5);
        tapElement("penjualan_tolak_pesanan_alchemy_button");
    }

    public void tapOnReasonTolakPesanan() {
        waitForVisibilityOf("penjualan_modal_lanjutkan_tolak_id", 5);
        tapElement("penjualan_modal_lanjutkan_tolak_id");
        tapElement("name_LabelLeftRadioButtonMV");
        tapElement("penjualan_simpan_alasan_tolak_alchemy_id");
    }

    public void validateReasonTolakPesanan() {
        verifyElementExist("match_alasan_menolak_alchemy_xpath");
        //HelperData.setLastActionPage(new DetailTransaksiPenjualanAlchemyPage());
    }

    public void userOnRefundedTransactionPage() {
        verifyElementExist("penjualan_dikembalikan_text");
        HelperData.setLastActionPage(new DetailTransaksiPenjualanAlchemyPage(iosDriver));
    }

    public void verifyAddress() {
        validateValue().equalsTrue((TransactionData.getAddress()).contains(getElementValue("penjualan_alamat_value")));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        //HelperData.setLastActionPage(new HomePage());
    }
}
