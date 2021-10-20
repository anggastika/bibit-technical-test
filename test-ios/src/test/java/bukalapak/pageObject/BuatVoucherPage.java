package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BuatVoucherPage extends BasePage {

    public BuatVoucherPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBuatVoucherPage() {
        waitForVisibilityOf("buat_voucher_kode_voucher_header_text", 30);
        verifyElementDisplayed("buat_voucher_title_text");
        verifyElementExist("buat_voucher_kode_voucher_header_text");
        HelperData.setLastActionPage(new BuatVoucherPage(iosDriver));
    }

    public void createVoucherLapak() {
        checkAgreementCheckbox();
        swipeUpToElement("buat_voucher_simpan_button", 15);
        tapElement("buat_voucher_simpan_button");
        tapElement("buat_voucher__lanjut_button", 10);
    }

    public void selectJenisVoucher(String jenisVoucher) {
        switch (jenisVoucher) {
            case "Diskon":
                tapElement("buat_voucher_diskon_radio_button");
                break;
            case "Potongan Ongkos Kirim":
                tapElement("buat_voucher_potongan_ongkos_kirim_radio_button");
                break;
            default:
                tapElement("buat_voucher_potongan_harga_radio_button");
                break;
        }
    }

    public void inputKodeVoucher(String voucherCode) {
        waitForVisibilityOf("buat_voucher_kode_voucher_field");
        typeValue("buat_voucher_kode_voucher_field", "");
        clearField("buat_voucher_kode_voucher_field", 7);
        typeAndEnterValue("buat_voucher_kode_voucher_field", voucherCode);
    }

    private void clearField(String locator, int tapCount) {
        tapElement(locator);
        for (int i = 0; i < tapCount; i++) {
            if (isElementVisible("keyboard_number")) {
                tapElement("delete_keyboard_number");
            } else {
                tapElement("delete_keyboard_keyword");
            }
        }
        hideKeyboard();
    }

    public void inputPotonganHarga(String amount) {
        swipeUpToElement("buat_voucher_potongan_harga_field", 5);
        typeValue("buat_voucher_potongan_harga_field", "");
        clearField("buat_voucher_potongan_harga_field", 7);
        typeAndEnterValueWithTimeOut("buat_voucher_potongan_harga_field", amount);
    }

    public void inputMinimumTrx(String amount) {
        swipeUpToElement("buat_voucher_jumlah_voucher_field", 5);
        clearField("buat_voucher_minimum_trx_field", 7);
        typeAndEnterValueWithTimeOut("buat_voucher_minimum_trx_field", amount);
    }

    public void selectEtalaseBarangTertentu() {
        swipeUpToElement("buat_voucher_etalase_barang_tertentu_radio_button", 3);
        tapElement("buat_voucher_etalase_barang_tertentu_radio_button");
    }

    public void selectEtalaseNumberOne() {
        waitForVisibilityOf("buat_voucher_etalase_dropdown");
        tapElement("buat_voucher_etalase_dropdown");
        validateDisplayed("buat_voucher_etalase_dropdown_value");
        tapElement("buat_voucher_etalase_dropdown_value");
    }

    public void inputJumlahVoucher(String qty) {
        swipeUpToElement("buat_voucher_jumlah_voucher_field", 5);
        typeValue("buat_voucher_jumlah_voucher_field", "");
        clearField("buat_voucher_jumlah_voucher_field", 5);
        typeAndEnterValueWithTimeOut("buat_voucher_jumlah_voucher_field", qty);
    }

    public void tapPengaturanTambahan() {
        swipeUpToElement("buat_voucher_pengaturan_tambahan_txt", 3);
        tapElement("buat_voucher_pengaturan_tambahan_txt");
    }

    public void inputMaksPerHari(String qty) {
        waitForVisibilityOf("buat_voucher_maks_penggunaan_perhari_txtfield");
        typeAndEnterValueWithTimeOut("buat_voucher_maks_penggunaan_perhari_txtfield", qty);
    }

    public void inputMaksPerPeriode(String qty) {
        waitForVisibilityOf("buat_voucher_maks_penggunaan_perperiode_txtfield");
        typeAndEnterValueWithTimeOut("buat_voucher_maks_penggunaan_perperiode_txtfield", qty);
    }

    public void checkAgreementCheckbox() {
        swipeUpToElement("buat_voucher_agreement_checkbox", 15);
        tapElement("buat_voucher_agreement_checkbox");
    }

    public void tapSimpanButton() {
        swipeUpToElement("buat_voucher_simpan_button");
        tapElement("buat_voucher_simpan_button");
        if (isElementVisible("buat_voucher__lanjut_button", 10)) {
            tapElement("buat_voucher__lanjut_button");
        }
    }

    // for voucher diskon
    public void inputBesarDiskon(String percent) {
        waitForVisibilityOf("buat_voucher_diskon_besar_diskon_field");
        clearField("buat_voucher_diskon_besar_diskon_field", 3);
        typeAndEnterValueWithTimeOut("buat_voucher_diskon_besar_diskon_field", percent);
    }

    public void inputMaksPotonganHarga(String amount) {
        swipeUpToElement("buat_voucher_diskon_besar_max_harga_field");
        clearField("buat_voucher_diskon_besar_max_harga_field", 7);
        typeAndEnterValueWithTimeOut("buat_voucher_diskon_besar_max_harga_field", amount);
    }

    // for voucher potongan ongkos kirim
    public void besarPotonganongkir(String amount) {
        waitForVisibilityOf("buat_voucher_potongan_ongkir_besar_potongan_field");
        clearField("buat_voucher_potongan_ongkir_besar_potongan_field", 7);
        typeAndEnterValueWithTimeOut("buat_voucher_potongan_ongkir_besar_potongan_field", amount);
    }

    public void tapJenisKurirDropdDown() {
        tapElement("buat_voucher_potongan_ongkir_jenis_kurir_dropddown");
    }

    public void selectJenisKurir() {
        waitForVisibilityOf("buat_voucher_potongan_ongkir_select_kurir");
        tapElement("buat_voucher_potongan_ongkir_select_kurir");
    }

    @Override
    public void goToHomePage() {
        tapElement("base_back_button");
        tapElement("buat_voucher_keluar_button");
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
