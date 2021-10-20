package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MitraBukalapakPage extends BasePage {

    public MitraBukalapakPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnMitraBukalapakPage() {
        verifyElementExist("mitra_bukalapak_webview_header", 20, "Mitra Bukalapak header text should be exist");
        validateEnabled("mitra_bukalapak_webview_grosir_menu", "Grosir menu should be enabled");
        validateEnabled("mitra_bukalapak_webview_transaksi_menu", "Transaksi menu should be enabled");
        validateEnabled("mitra_bukalapak_webview_bantuan_menu", "Bantuan menu should be enabled");
        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }

    public void validateCannotUseGrosirFeature() {
        verifyElementExist("mitra_bukalapak_webview_grosir_popup_title", 10, "Mau beli stok warung title text should be exist");
        validateEnabled("mitra_bukalapak_webview_grosir_popup_download_button", "Download aplikasi mitra button should be enabled");
        validateEnabled("mitra_bukalapak_webview_grosir_popup_nanti_button", "Nanti aja button should be enabled");
        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }

    public void userOnMitraBukalapakAktifkanKembaliPage() {
        verifyElementExist("mitra_bukalapak_webview_aktifkan_title", 20, "Status Mitra kamu telah dinonaktifkan title text should be exist");
        validateEnabled("mitra_bukalapak_webview_aktifkan_button", "Aktifkan kembali button should be enabled");
        validateEnabled("mitra_bukalapak_webview_hubungi_button", "Hubungi Bukalapak button should be enabled");
        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }

    public void userOnGrosirPage() {
        verifyElementExist("mitra_bukalapak_webview_grosir_search_bar", 10, "Grosir search bar should be exist");
        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }

    public void userOnTransaksiPage() {
        verifyElementExist("mitra_bukalapak_webview_transaksi_total_saldo_txt", 10, "Total Saldo should be exist");
        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }

    public void userOnBantuanPage() {
        verifyElementExist("mitra_bukalapak_webview_bantuan_panduan_txt", 10, "Total Saldo should be exist");
        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }
}
