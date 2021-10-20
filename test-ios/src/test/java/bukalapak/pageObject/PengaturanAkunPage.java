package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


/**
 * @author Irfan Mauludin, 01/11/18
 */
public class PengaturanAkunPage extends BasePage {

    public PengaturanAkunPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPengaturanAkunPage() {
        assertTrue(isElementVisible("pengaturan_akun_page_title"));
        HelperData.setLastActionPage(new PengaturanAkunPage(iosDriver));
    }

    public void tapLogout() {
        boolean autoallow = getAutoAcceptAlert();
        swipeUpToElement("pengaturan_akun_logout_option");
        tapElement("pengaturan_akun_logout_option");
        if (isElementVisible("logout_alert_ya_button") && !autoallow) {
            tapElement("logout_alert_ya_button");
        }
    }

    public void tapPengaturanEmail() {
        swipeDownToElement("pengaturan_akun_email");
        tapElement("pengaturan_akun_email");
        HelperData.setLastActionPage(new ChangeEmailPage(iosDriver));
    }

    public void tapPengaturanPassword() {
        swipeDownToElement("pengaturan_akun_password");
        tapElement("pengaturan_akun_password");
    }

    public void tapPengaturanTelepon() {
        verifyElementExist("pengaturan_akun_telepon");
        tapElement("pengaturan_akun_telepon", 10);
    }

    public void tapPengaturanPembayaran() {
        swipeUpToElement("pengaturan_akun_pembayaran");
        tapElement("pengaturan_akun_pembayaran");
    }

    public void tapTFAToggle() {
        swipeUpToElement("pengaturan_akun_tfa_toggle");
        tapElement("toggle_tfa");
    }

    public void goToHomePage() {
        tapElement("pengaturan_akun_back_button");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapRiwayatPerangkat() {
        swipeDownToElement("pengaturan_akun_riwayat_perangkat_text");
        tapElement("pengaturan_akun_riwayat_perangkat_text", 15);
    }

    public void tapAlamatPengiriman() {
        swipeDownToElement("akun_alamat_pengiriman_button");
        tapElement("akun_alamat_pengiriman_button");
    }
}
