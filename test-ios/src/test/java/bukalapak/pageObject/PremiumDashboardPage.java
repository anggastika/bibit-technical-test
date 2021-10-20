package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.pageObject.vouchers.VoucherDetailKaryawanLapakPage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.NotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PremiumDashboardPage extends BasePage {

    public PremiumDashboardPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPremiumDashboardPage() {
        waitForVisibilityOf("premium_dashboard_title_text");
        verifyElementExist("base_back_button");
        HelperData.setLastActionPage(new PremiumDashboardPage(iosDriver));
    }

    public void verifyLockedPremiumFeature() {
        verifyElementExist("premium_dashboard_fitur_super_seller_text");
        verifyElementExist("premium_dashboard_fitur_super_seller_desc_text");
        verifyElementExist("premium_dashboard_langganan_super_seller_button");
    }

    public void clickMenuSuperSeller(String menuSuperSeller) {
        waitForVisibilityOf(constructLocator("super_seller_dashboard_general_txt", menuSuperSeller), 30);
        tapElement(constructLocator("super_seller_dashboard_general_txt", menuSuperSeller), 10);
    }

    public void entryPointInspirasiPenjualanDashboard() {
        waitForVisibilityOf("super_seller_entrypoint_inspirasi_penjualan_text", 5);
        waitForVisibilityOf("super_seller_entrypoint_inspirasi_penjualan_kunjungi_btn", 5);
    }

    // Performa Lapak

    public void verifySkorPelapak() {
        waitForVisibilityOf("performa_lapak_skor_point", 25);
        verifyElementExist("performa_lapak_tingkatkan_skor_txt");
    }

    public void verifyMenuSkorPelapak() {
        validateEnabled("perfoma_lapak_menu_type");
        verifyElementExist("performa_lapak_left_tap");
        verifyElementExist("performa_lapak_right_tap");
        HelperData.setLastActionPage(new PremiumDashboardPage(iosDriver));
    }

    // Statistik dan Pendapatan Lapak
    public void verifyPeriodTime(String periodTime) {
        waitForVisibilityOf("statistik_pendapatan_lapak_" + periodTime + "_hari_txt", 20);
    }

    public void clickPeriodePendapatanLapak(String periodTime) {
        String locator = "statistik_pendapatan_lapak_" + periodTime + "_hari_txt";
        waitForVisibilityOf(locator, 20);
        tapElement(locator);
        waitForVisibilityOf(locator, 20);
        // if period active, element has enabled=false
        validateDisabled(locator);
    }

    public void clickMenuStatistikPendapatanLapak(String menuStatistikPendapatanLapak) {
        waitForVisibilityOf(constructLocator("super_seller_dashboard_general_txt", menuStatistikPendapatanLapak), 20);
        tapElement(constructLocator("super_seller_dashboard_general_txt", menuStatistikPendapatanLapak));
    }

    public void verifyJumlahTransaksiMenu() {
        validateDisplayed("statistik_jumlah_transaksi_txt");
        validateDisplayed("statistik_jumlah_total_push_txt");
        validateDisplayed("statistik_jumlah_total_klik_txt");
        HelperData.setLastActionPage(new PremiumDashboardPage(iosDriver));
    }

    public void verifyTotalNilaiTransaksiMenu() {
        validateDisplayed("statistik_total_nilai_transaksi_chart_text");
        validateDisplayed("statistik_total_biaya_promoted_push_chart_text");
        HelperData.setLastActionPage(new PremiumDashboardPage(iosDriver));
    }

    public void verifyChartStatistik() {
        waitForVisibilityOf("statistik_pendapatan_lapak_chart_omzet_txt", 20);
        validateDisplayed("statistik_pendapatan_lapak_chart_rp_txt");
    }

    public void verifyInfoRincianPromosi() {
        validateDisplayed("statistik_pendapatan_lapak_lihat_total_klik_push");
    }

    // Rincian Promosi
    public void verifyProductDisplay(int jumlahBarang) {
        waitForVisibilityOf("rincian_promosi_product_name", 20);
        validateValue().equals(jumlahBarang, getElements("rincian_promosi_product_name").size());
        validateValue().equals(jumlahBarang, getElements("rincian_promosi_product_biaya_produksi").size());
        validateValue().equals(jumlahBarang, getElements("rincian_promosi_product_total_penjualan").size());
        validateValue().equals(jumlahBarang, getElements("rincian_promosi_product_percentage").size());
    }

    public void verifyDatePeriode(int period, String rincianPromosiPage) {
        //need time for retrieve data
        waitFor(1);
        DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("id", "ID"));
        String startDate = LocalDate.now().minusDays(period == 7 ? period - 1 : period).format(fullDateFormatter);
        String todayDate = LocalDate.now().format(fullDateFormatter);
        switch (rincianPromosiPage) {
            case "Rincian Promosi Dashboad":
                validateValue().equals(startDate + " - " + todayDate, getElementValue("rincian_promosi_periode_date"));
                break;
            case "Rincian Promosi Detail":
                validateValue().equals(startDate + " - " + todayDate, getElementValue("rincian_promosi_detail_periode"));
                break;
            case "Rincian Promosi List":
                validateValue().equals(startDate + " - " + todayDate, getElementValue("rincian_promosi_periode_list"));
                break;
            default:
                LogUtil.info("page tidak ditemukan");
                break;
        }
        HelperData.setLastActionPage(new PremiumDashboardPage(iosDriver));
    }

    public void clickItemsRincianPromosi(String nameProduct) {
        tapElement(constructLocator("rincian_promosi_product_name_click", nameProduct));
    }

    public void clickLihatSelengkapnya() {
        validateDisplayed("rincian_promosi_lihat_selengkapnya");
        tapElement("rincian_promosi_lihat_selengkapnya");
    }

    // Inventaris Barang Lapakmu
    public void verifyDate() {
        waitForVisibilityOf("inventaris_date", 10);
    }

    public void verifyBarangTerjualStokTitle() {
        waitForVisibilityOf("super_seller_dashboard_nama_txt", 20);
        validateDisplayed("super_seller_dashboard_terjual_txt");
        validateDisplayed("super_seller_dashboard_stok_txt");
    }

    public void verifyBarangTerjualStokValue(int posisiInventaris) {
        waitForVisibilityOf("inventaris_lapak_column_value_nama", 20);
        validateValue().equalsTrue(getElements("inventaris_lapak_column_value_nama").get(0).isDisplayed());
        validateValue().equals(posisiInventaris, getElements("inventaris_lapak_column_value_nama").size());
        validateValue().equals(posisiInventaris, getElements("inventaris_lapak_column_value_stok").size());
        validateValue().equals(posisiInventaris, getElements("inventaris_lapak_column_value_terjual").size());
    }

    public void clickInventarisTab(String tabName) {
        tapElement(constructLocator("super_seller_dashboard_general_txt", tabName));
        //wait until tab is switched
        waitFor(5);
    }

    // Barang Terfavorit
    public void verifySebarPromosiEntryPoint() {
        waitForVisibilityOf("barang_terfavorit_sebar_promosi_title", 15);
        validateExist("barang_terfavorit_sebar_promosi_desc");
        validateExist("barang_terfavorit_sebar_promosi_button");
    }

    public void tapSebarPromosiButton() {
        tapElement("barang_terfavorit_sebar_promosi_button");
    }

    // Pantau Saingan

    public void verifyDescPantauBarangSaingan() {
        assertTrue(getElement("pantau_saingan_desc").getAttribute("value").contains("Pantau Barang Saingan kini punya tampilan baru."));
    }

    // Voucher Lapak

    public void removeSuperSellerVoucher() {
        while (!isElementVisible("voucher_lapak_empty_text")) {
            VoucherDetailKaryawanLapakPage voucherDetailKaryawanLapakPage = new VoucherDetailKaryawanLapakPage(iosDriver);
            tapOnListVoucher(0);
            voucherDetailKaryawanLapakPage.tapOnHentikanVoucher();
        }
        HelperData.setLastActionPage(new PremiumDashboardPage(iosDriver));
    }

    public void validateListVoucherLapakVoucherCode(String voucherCode) {
        waitForVisibilityOf("voucher_lapak_kode_voucher_txt");
        verifyElementExist(constructLocator("voucher_lapak_list_code_text", voucherCode));
    }

    public void validateListVoucherLapakStatus(String status) {
        verifyElementExist(constructLocator("voucher_lapak_list_status_text", status));
    }

    public void tapOnListVoucher(int index) {
        waitForVisibilityOf("voucher_lapak_kode_voucher_txt", 15);
        getElements("voucher_lapak_kode_voucher_txt").get(index).click();
    }

    public void verifyEmptyStateVoucherLapak() {
        waitForVisibilityOf("voucher_lapak_empty_text");
        verifyElementExist("voucher_lapak_empty_subtitle_text");
        verifyElementExist("voucher_lapak_empty_buat_voucher_button");
    }

    public void checkMaxCreateVoucher() {
        assertFalse(isElementVisible("voucher_lapak_buat_voucher_button", 1));
    }

    public void tapRiwayatVoucher() {
        tapElement("voucher_lapak_detail_lihat_riwayat_voucher");
    }

    public void clickPromosiOtomatis() {
        swipeDownToElement("promosi_otomatis", 10);
        tapElement("promosi_otomatis");
    }

    public void userOnPromosiOtomatisSection() {
        verifyElementExist("premium_dashboard_promosi_otomatis_push_tab");
        verifyElementExist("premium_dashboard_promosi_otomatis_promoted_tab");
        verifyElementExist("premium_dashboard_tambah_promosi_button");
        HelperData.setLastActionPage(new SuperSellerLandingPage(iosDriver));
    }

    //voucher lapak subsidi

    public void verifyLabelSubsidi(String voucherName) {
        swipeUpToElement(constructLocator("voucher_lapak_subsidi_tag", voucherName), 20);
        verifyElementExist(constructLocator("voucher_lapak_subsidi_tag", voucherName));
        HelperData.setLastActionPage(new SuperSellerLandingPage(iosDriver));
    }

    public void userTapToggleVoucher(String toggleStatus, String voucherName) {
        verifyLabelSubsidi(voucherName);
        switch (toggleStatus) {
            case "hide":
                if (isElementExist(constructLocator("voucher_lapak_toggle_on", voucherName), 5)) {
                    tapElement(constructLocator("voucher_lapak_toggle_on", voucherName), 5);
                }
                break;
            case "unhide":
                if (isElementExist(constructLocator("voucher_lapak_toggle_off", voucherName), 5)) {
                    tapElement(constructLocator("voucher_lapak_toggle_off", voucherName), 5);
                }
                break;
            default:
                LogUtil.error("Cannot find elements!", new NotFoundException());
                break;
        }
    }

    public void verifyAturKurirVoucher(String courierEligible, String voucherName) {
        switch (courierEligible) {
            case "ineligible":
                swipeUpToElement(constructLocator("voucher_lapak_atur_kurir", voucherName));
                verifyElementExist(constructLocator("voucher_lapak_atur_kurir", voucherName));
                tapElement(constructLocator("voucher_lapak_atur_kurir", voucherName), 5);
                break;
            case "eligible":
                waitFor(5);
                verifyElementNotExist(constructLocator("voucher_lapak_atur_kurir", voucherName));
                break;
            default:
                LogUtil.error("Cannot find elements!", new NotFoundException());
                break;
        }
        HelperData.setLastActionPage(new SuperSellerLandingPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapCreateVoucherLapak() {
        swipeUpToElement("voucher_lapak_buat_voucher_button");
        tapElement("voucher_lapak_buat_voucher_button");
    }

    public void tapOnVoucherSection() {
        swipeUpToElement("voucher_lapak_title_tab");
        tapElement("voucher_lapak_title_tab");
    }

    public void tapOnDownloadVoucherSection() {
        verifyElementExist("voucher_lapak_download_voucher_button");
        tapElement("voucher_lapak_download_voucher_button");
        waitForVisibilityOf("create_product_allow_access", 10);
        tapElement("create_product_allow_access");
        verifyElementExist("voucher_lapak_download_voucher_sheet");
    }

    public void tapOnDownloadAllVoucher() {
        tapElement("voucher_lapak_download_all_voucher_button");
    }

    public void validateSuccessDownload() {
        verifyElementExist("voucher_lapak_download_all_success");
    }

    public void tapOnShareVoucherSection() {
        verifyElementExist("voucher_lapak_share_voucher_button");
        tapElement("voucher_lapak_share_voucher_button");
        verifyElementExist("voucher_lapak_share_sheet");
        verifyElementExist("voucher_lapak_confirm_copy_voucher");
    }
}
