package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PremiumDashboardStepDefinitions extends TestInstrument implements En {
    public PremiumDashboardStepDefinitions() {
        Given("^user is in \"(?:Super Seller Dashboard|Premium Dashboard)\" page$", () -> {
            bukalapak.premiumDashboardPage().userOnPremiumDashboardPage();
        });

        When("^user goes to (?:Super Seller|Premium Dashboard) page$", () -> {
            bukalapak.iOSBasePage().openDeepLink("/premium/dashboard");
        });

        Then("^user is shown locked (?:Super Seller|Premium) feature on (?:Premium|Super Seller) Dashboard$", () -> {
            bukalapak.premiumDashboardPage().verifyLockedPremiumFeature();
        });

        When("^user click \"([^\"]*)\" menu in super seller dashboard$", (String menuSuperSeller) -> {
            bukalapak.premiumDashboardPage().clickMenuSuperSeller(menuSuperSeller);
        });

        When("^user see entry point inspirasi penjualan$", () -> {
            bukalapak.premiumDashboardPage().entryPointInspirasiPenjualanDashboard();
        });

        // Performa Lapak
        Then("^user is shown Skor Pelapak$", () -> {
            bukalapak.premiumDashboardPage().verifySkorPelapak();
        });

        Then("^user is shown menu of Skor Pelapak$", () -> {
            bukalapak.premiumDashboardPage().verifyMenuSkorPelapak();
        });

        // Statistik dan Pendapatan Lapak
        Then("^user is shown statistic period for \"([^\"]*)\" days$", (String period) -> {
            bukalapak.premiumDashboardPage().verifyPeriodTime(period);
        });

        Then("^user is shown Jumlah Transaksi chart$", () -> {
            bukalapak.premiumDashboardPage().verifyChartStatistik();
            bukalapak.premiumDashboardPage().verifyJumlahTransaksiMenu();
        });

        Then("^user taps on \"([^\"]*)\" option period$", (String period) -> {
            bukalapak.premiumDashboardPage().clickPeriodePendapatanLapak(period);
        });

        Then("^user taps on \"([^\"]*)\" menu in Statistik dan Pendapatan Lapak$", (String menuStatisikDanPendapatanLapak) -> {
            bukalapak.premiumDashboardPage().clickMenuStatistikPendapatanLapak(menuStatisikDanPendapatanLapak);
        });

        Then("^user is shown Total Nilai Transaksi chart$", () -> {
            bukalapak.premiumDashboardPage().verifyChartStatistik();
            bukalapak.premiumDashboardPage().verifyTotalNilaiTransaksiMenu();
        });

        When("user see info Lihat total klik Push & Promoted Push di menu Rincian Promosi in Statistik dan Pendapatan Lapak", () -> {
            bukalapak.premiumDashboardPage().verifyInfoRincianPromosi();
        });

        //Rincian Promosi
        Then("^user is shown (\\d+) products on Rincian Promosi$", (Integer jumlahBarang) -> {
            bukalapak.premiumDashboardPage().verifyProductDisplay(jumlahBarang);
        });

        Then("^user is shown (\\d+) days period date in (Rincian Promosi Dashboad|Rincian Promosi Detail|Rincian Promosi List) page$", (Integer period, String rincianPromosiPage) -> {
            bukalapak.premiumDashboardPage().verifyDatePeriode(period, rincianPromosiPage);
        });

        When("^user click \"([^\"]*)\" products on Rincian Promosi$", (String nameProduct) -> {
            bukalapak.premiumDashboardPage().clickItemsRincianPromosi(nameProduct);
        });

        When("^user click lihat selengkapnya button", () -> {
            bukalapak.premiumDashboardPage().clickLihatSelengkapnya();
        });

        // Inventaris Barang Lapakmu
        Then("^user verify date display in Inventaris Barang Lapakmu$", () -> {
            bukalapak.premiumDashboardPage().verifyDate();
        });

        Then("^user is shown Nama, Terjual, and Stok title$", () -> {
            bukalapak.premiumDashboardPage().verifyBarangTerjualStokTitle();
        });

        Then("^user is shown (\\d+) products on Inventaris Barang Lapakmu$", (Integer jumlahBarang) -> {
            bukalapak.premiumDashboardPage().verifyBarangTerjualStokValue(jumlahBarang);
        });

        When("^user click on Inventaris tab (Terlaris|Kurang Laris)$", (String tabName) -> {
            bukalapak.premiumDashboardPage().clickInventarisTab(tabName);
        });

        // Barang Terfavorit

        Then("^user see sebar promosi entry point onboarding$", () -> {
            bukalapak.premiumDashboardPage().verifySebarPromosiEntryPoint();
        });

        Then("^user tap coba sekarang button in sebar promosi entry point$", () -> {
            bukalapak.premiumDashboardPage().tapSebarPromosiButton();
        });

        // Pantau Saingan
        Then("^user see description pantau barang saingan$", () -> {
            bukalapak.premiumDashboardPage().verifyDescPantauBarangSaingan();
        });

        // Voucher Lapak

        When("^user (?:Professional|Super Seller) remove active seller vouchers$", () -> {
            bukalapak.premiumDashboardPage().removeSuperSellerVoucher();
        });

        When("^user taps on voucher lapak at position (\\d+)$", (Integer index) -> {
            bukalapak.premiumDashboardPage().tapOnListVoucher(index - 1);
        });

        Then("^user is shown empty state Voucher Lapak$", () -> {
            bukalapak.premiumDashboardPage().verifyEmptyStateVoucherLapak();
        });

        Then("^user is shown voucher at position 1 with code \"([^\"]*)\" on super seller dashboard$", (String voucherCode) -> {
            bukalapak.premiumDashboardPage().validateListVoucherLapakVoucherCode(voucherCode);
        });

        Then("^user is shown voucher at position 1 with status (Aktif|Belum Aktif) on super seller dashboard$", (String status) -> {
            bukalapak.premiumDashboardPage().validateListVoucherLapakStatus(status);
        });

        When("^user tap back button$", () -> {
            bukalapak.premiumDashboardPage().tapBackButton();
        });

        Then("^user is not shown Buat Voucher button$", () -> {
            bukalapak.premiumDashboardPage().checkMaxCreateVoucher();
        });

        When("^user go to (?:merchant|product detail) page using deeplink \"([^\"]*)\"$", (String url) -> {
            bukalapak.iOSBasePage().openDeepLink(url);
        });

        When("^user tap lihat riwayat voucher lapak$", () -> {
            bukalapak.premiumDashboardPage().tapRiwayatVoucher();
        });

        Then("^user click Promosi Otomatis$", () -> {
            bukalapak.premiumDashboardPage().clickPromosiOtomatis();
        });

        Then("^user verify Promosi Otomatis section in Premium Dashboard page$", () -> {
            bukalapak.premiumDashboardPage().userOnPromosiOtomatisSection();
        });

        And("user tap Voucher section", () -> {
            bukalapak.premiumDashboardPage().tapOnVoucherSection();
        });

        //Voucher lapak subsidized
        And("^user verify voucher \"([^\"]*)\" as a subsidi$", (String voucherName) -> {
            bukalapak.premiumDashboardPage().verifyLabelSubsidi(voucherName);
        });

        And("^user do (hide|unhide) voucher lapak \"([^\"]*)\"$", (String toggleStatus, String voucherName) -> {
            bukalapak.premiumDashboardPage().userTapToggleVoucher(toggleStatus, voucherName);
        });

        And("^user see (eligible|ineligible) courier voucher lapak \"([^\"]*)\"$", (String eligibleCourier, String voucherName) -> {
            bukalapak.premiumDashboardPage().verifyAturKurirVoucher(eligibleCourier, voucherName);
        });

        And("user tap download button", () -> {
            bukalapak.premiumDashboardPage().tapOnDownloadVoucherSection();
        });

        And("user tap download all button", () -> {
            bukalapak.premiumDashboardPage().tapOnDownloadAllVoucher();
        });

        And("user tap share button", () -> {
            bukalapak.premiumDashboardPage().tapOnShareVoucherSection();
        });
    }
}
