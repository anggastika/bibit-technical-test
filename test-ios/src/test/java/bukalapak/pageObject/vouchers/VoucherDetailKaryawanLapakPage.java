package bukalapak.pageObject.vouchers;

import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import bukalapak.data.HelperData;


public class VoucherDetailKaryawanLapakPage extends BasePage {

    public VoucherDetailKaryawanLapakPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnVoucherDetailKaryawanLapak() {
        waitForVisibilityOf("voucher_lapak_detail_title");
        HelperData.setLastActionPage(new VoucherDetailKaryawanLapakPage(iosDriver));
    }

    public void verifyVoucherCode(String voucherCode) {
        verifyElementExist(constructLocator("voucher_lapak_detail_general_text", voucherCode));
        HelperData.setLastActionPage(new VoucherDetailKaryawanLapakPage(iosDriver));
    }

    public void verifyVoucherStatus(String status) {
        verifyElementExist(constructLocator("voucher_lapak_detail_general_text", status));
    }

    public void verifyVoucherType(String typeVoucher) {
        verifyElementExist(constructLocator("voucher_lapak_detail_general", typeVoucher));
    }

    public void verifyVoucherMinTrx(String amount) {
        verifyElementExist(constructLocator("voucher_lapak_detail_general", amount));
    }

    public void verifyVoucherPotonganHarga(String amount) {
        verifyElementExist(constructLocator("voucher_lapak_detail_general", amount));
    }

    public void tapRincianVoucher() {
        waitForVisibilityOf("voucher_lapak_detail_voucher_rincian_text");
        tapElement("voucher_lapak_detail_voucher_rincian_text");
    }

    public void verifyVoucherEtalase(String etalaseName) {
        verifyElementExist(constructLocator("voucher_lapak_detail_general", etalaseName));
    }

    public void verifyVoucherQuantity(String qty) {
        verifyElementExist(constructLocator("voucher_lapak_detail_general", qty));
    }

    public void tapDetailPengeluaran() {
        tapElement("voucher_lapak_detail_voucher_pengeluaran_text");
    }

    public void verifyPenggunaanPerHari(String qty) {
        assertEquals(qty, getTextFromElement("voucher_lapak_penggunaan_perhari"));
    }
    public void verifyPenggunaanPerPeriode(String qty) {
        assertEquals(qty, getTextFromElement("voucher_lapak_penggunaan_perperiode"));
        HelperData.setLastActionPage(new VoucherDetailKaryawanLapakPage(iosDriver));
    }

    public void verifyPotonganVoucher(String priceAmount) {
        verifyElementExist(constructLocator("voucher_lapak_detail_voucher_potongan_voucher", priceAmount));
    }

    public void verifyVoucherTerpakai(String qty) {
        verifyElementExist(constructLocator("voucher_lapak_detail_voucher_terpakai", qty));
    }

    public void tapVoucherToggle() {
        tapElement("voucher_lapak_detail_toggle_publish_switch");
    }

    public void verifyVoucherToggle(String isOn) {
        String status = isOn.substring(0, 1).toUpperCase() + isOn.substring(1).toLowerCase();

        verifyElementExist(constructLocator("voucher_lapak_detail_toggle_publish_switch_status", status));
    }

    public void tapOnHentikanVoucher(){
        waitForVisibilityOf("voucher_lapak_hentikan_voucher_txt");
        tapElement("voucher_lapak_hentikan_voucher_txt");
        waitForVisibilityOf("voucher_lapak_hentikan_txt");
        tapElement("voucher_lapak_hentikan_txt");
    }

    // Voucher Diskon
    public void besarDiskon(String amountDiskon) {
        verifyElementExist(constructLocator("voucher_lapak_detail_general", amountDiskon));
    }

    // Voucher Potongan Ongkos Kirim
    public void besarPotonganOngKir(String amountDiskon) {
        verifyElementExist(constructLocator("voucher_lapak_detail_general", amountDiskon));
    }

    // Riwayat Voucher
    public void verifyRiwayatVoucherDisplay() {
        waitForVisibilityOf("voucher_lapak_riwayat_voucher_title");
        waitForVisibilityOf("voucher_lapak_riwayat_voucher_filter");
    }

    public void verifyVoucherCodeRiwayatVoucher(String voucherCode) {
        assertEquals(voucherCode, getTextFromElement("voucher_lapak_riwayat_voucher_code", 0));
        verifyElementExist("voucher_lapak_riwayat_voucher_dihentikan");
    }

    public void tapVoucherListRiwayatVoucher() {
        tapElement("voucher_lapak_riwayat_voucher_dihentikan");
    }

    public void tapOnEditOnVoucher() {
        waitForVisibilityOf("voucher_lapak_detail_edit_button");
        tapElement("voucher_lapak_detail_edit_button");
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
