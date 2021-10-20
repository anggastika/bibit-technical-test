package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class PengaturanPembayaranTambahKartuPage extends BasePage {

    public PengaturanPembayaranTambahKartuPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPengaturanPembayaranTambahKartuPage() {
        waitForVisibilityOf("pengaturan_pembayaran_tambah_kartu_title_text",15);
    }

    public void typeOnCardInfoField(String number, String cvv) {
        typeAndEnterValueWithTimeOut("pengaturan_pembayaran_tambah_kartu_number_text_field", number);
        nativeTap(100,315);
        tapElement("pengaturan_pembayaran_tambah_kartu_bulan_text");
        nativeTap(213,315);
        tapElement("pengaturan_pembayaran_tambah_kartu_tahun_text");
        typeAndEnterValueWithTimeOut("pengaturan_pembayaran_tambah_kartu_cvv_text_field", cvv);
    }

    public void tapOnPrimaryCardCheckboxButton() {
        tapElement("pengaturan_pembayaran_tambah_kartu_primary_card_checkbox_button");
    }

    public void tapOnSimpanButton() {
        tapElement("pengaturan_pembayaran_tambah_kartu_simpan_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateNotificationTambahKartu(String status) {
        if (status.equals("berhasil ditambah"))
            waitForVisibilityOf("pengaturan_pembayaran_credit_card_successfully_tambah_kartu_text");
        else if (status.equals("tidak bisa di proses")) {
            waitForVisibilityOf("pengaturan_pembayaran_credit_card_tambah_kartu_tidak_bisa_diproses_text");
        } else {
            Assert.fail("Kartu tidak berhasil ditambah");
        }
    }
}
