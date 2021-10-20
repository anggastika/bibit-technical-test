package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import cucumber.api.java8.En;

public class AccountStepDefinitions extends TestInstrument implements En {

    public AccountStepDefinitions() {
        Given("user is in \"akun\" page", () -> {
            bukalapak.akunPage().userOnAkunPage();
        });

        Given("user is in \"akun_onboarding\" page", () -> {
            bukalapak.akunPage().userOnAkunOnboarding();
        });

        Given("user is in \"pengaturan_akun\" page", () -> {
            bukalapak.pengaturanAkunPage().userOnPengaturanAkunPage();
        });

        When("user tap \"([^\"]*)\" button in Akun page", (String arg0) -> {
            bukalapak.akunPage().tapElement(arg0 + "_button");
        });

        Then("email verification reminder pop up is displayed", () -> {
            bukalapak.akunPage().emailVerificationPopUpShouldDisplayed();
        });

        And("user tap on Waktu Proses Pesanan menu", () -> {
            bukalapak.akunPage().clickWaktuProses();
        });

        And("user tap on Barang Draf menu", () -> {
            bukalapak.akunPage().clickDraftMenu();
        });

        Given("user force splitter for experiment \"([^\"]*)\" with variant \"([^\"]*)\"", (String experiment, String variant) -> {
            bukalapak.akunPage().forceSplitter(experiment, variant);
        });

        And("user go back to production environment", () -> {
            bukalapak.akunPage().goBackToProduction();
        });

        And("user force \"([^\"]*)\" neo toggle \"([^\"]*)\"", (String status, String toggleName) -> {
            bukalapak.akunPage().setNeoToggle(status, toggleName);
        });

        And("user set user agent with \"([^\"]*)\"", (String userAgent) -> {
            bukalapak.akunPage().setUserAgent(userAgent);
        });

        When("^user change environment to \"([^\"]*)\"", (String environment) -> {
            bukalapak.akunPage().changeStagingEnv(environment);
        });

        When("user navigate to \"akun\" page", () -> {
            bukalapak.akunPage().clickAkunMenu();
        });

        When("user navigate to \"pengaturan_akun\" page", () -> {
            bukalapak.akunPage().tapPengaturanAkunOption();
        });

        When("user navigate to \"pengaturan_email\" page", () -> {
            bukalapak.pengaturanAkunPage().tapPengaturanEmail();
        });

        When("user navigate to \"pengaturan_password\" page", () -> {
            bukalapak.pengaturanAkunPage().tapPengaturanPassword();
        });

        When("user navigate to \"pengaturan_telepon\" page", () -> {
            bukalapak.pengaturanAkunPage().tapPengaturanTelepon();
        });

        When("user navigate to pengaturan_pembayaran page", () -> {
            bukalapak.pengaturanAkunPage().tapPengaturanPembayaran();
        });

        When("user navigate to \"Bukareview\" page", () -> {
            bukalapak.akunPage().goToBukareviewPage();
        });

        And("^user click menu ulasan kamu section$", () -> {
            bukalapak.akunPage().clickUlasanKamuText();
        });

        When("^user scroll and click Teman Cuan menu$", () -> {
            bukalapak.akunPage().clickTemanCuanMenu();
        });

        When("^user scroll down to Ulasan Kamu section$", () -> {
            bukalapak.akunPage().ulasanKamuSection();
        });

        And("^user should see notification bubble$", () -> {
            bukalapak.akunPage().verifyNotificationUlasanKamuMenuAkun();
        });

        When("^user scroll and click Barang Dijual section$", () -> {
            bukalapak.akunPage().clickBarangDijual();
        });

        When("^user scroll and click (Tentang Super Seller|Status Program|Dashboard Super Seller) section$", (String menuSuperSeller) -> {
            bukalapak.akunPage().clickTentangSuperSeller(menuSuperSeller);
        });

        When("^user tap on Transaksi Rutin menu", () -> {
            bukalapak.akunPage().tapOnTransaksiRutinMenu();
        });

        And("user click etalase label element", () -> {
            bukalapak.akunPage().clickEtalase();
        });

        And("user scroll down and click Feedback", () -> {
            bukalapak.akunPage().scrollAndClickFeedback();
        });

        And("user scroll down and click BukaBantuan", () -> {
            bukalapak.akunPage().scrollAndClickBukaBantuan();
        });

        And("user scroll down and click Komplain", () -> {
            bukalapak.akunPage().scrollAndClickKomplain();
        });

        And("user scroll down and click ajak-ajak berhadiah", () -> {
            bukalapak.akunPage().scrollAndClickReferral();
        });

        When("^user scroll and click Inspirasi Penjualan menu$", () -> {
            bukalapak.akunPage().scrollAndClickInspirasiPenjualan();
        });

        When("user scroll and click Pendapatan menu", () -> {
            bukalapak.akunPage().scrollAndClickPendapatan();
        });

        When("^user scroll and click Link Jual Beli menu$", () -> {
            bukalapak.akunPage().scrollAndClickLinkJualBeli();
        });

        When("^user scroll and click Karyawan Lapak menu$", () -> {
            bukalapak.akunPage().clickKaryawanLapakMenu();
        });

        And("user close Promoted Push onboarding on Akun page", () -> {
            bukalapak.akunPage().closePromotedPushOnboarding();
        });

        And("user see Promoted Push onboarding on Akun page", () -> {
            bukalapak.akunPage().verifyPromotedPushOnboardingDisplayed();
        });

        Then("promoted push onboarding is disappeared on Akun page", () -> {
            bukalapak.akunPage().verifyPromotedPushOnboardingDisappeared();
        });

        And("user redirected to Akun page with updated DANA balance", () -> {
            bukalapak.akunPage().userOnAkunPage();
            bukalapak.akunPage().verifyDANABoundAkunPage();
        });

        After(new String[]{"@should-logout"}, () -> {
            bukalapak.iOSBasePage().backToHomePageViaDeeplink();
            bukalapak.homePage().clickHomePage();
            bukalapak.akunPage().clickAkunMenu();
            bukalapak.akunPage().tapPengaturanAkunOption();
            bukalapak.pengaturanAkunPage().tapLogout();
            bukalapak.iOSBasePage().tapBackButton();
            bukalapak.homePage().clickHomePage();
            HelperData.setLastActionPage(bukalapak.homePage());
        });

        And("^user clicks button Beli$", () -> {
            bukalapak.akunPage().tapButtonBeli();
        });

        When("^user scrolls to recommendation section$", () -> {
            bukalapak.akunPage().scrollToRecommendation();
        });

        Then("^user see pop up add to cart on my account page$", () -> {
            bukalapak.akunPage().verifyAddToCart();
        });

        When("^user clicks recommendation product$", () -> {
            bukalapak.akunPage().clickRecommendationProduct();
        });

        Then("^user should not see recommendation on my account$", () -> {
            bukalapak.akunPage().verifyRecoNotOnMyAccount();
        });

        And("user tap on resurrection zone", () -> {
            bukalapak.akunPage().tapResurrectionZoneBanner();
        });

        When("^user click Riwayat Perangkat on pengaturan page$", () -> {
            bukalapak.pengaturanAkunPage().tapRiwayatPerangkat();
        });

        When("^user tap pelapak menu$", () -> {
            bukalapak.akunPage().tapPelapakTab();
        });

        And("user tap on Jasa Pengiriman menu", () -> {
            bukalapak.akunPage().clickJasaPengiriman();
        });

        And("user click tap Buat Voucher Lapak button", () -> {
            bukalapak.akunPage().tapBuatVoucherLapak();
        });

        And("^user verify status program menu( not)? have badge update status program$", (String state) -> {
            bukalapak.akunPage().verifyBadgeAdaUpdateStatusProgram(state);
        });

        When("user go to Pembekuan Lapak page", () -> {
            bukalapak.homePage().selectNavigationTab("akun");
            bukalapak.akunPage().userOnAkunPage();
            bukalapak.akunPage().tapPelapakTab();
            bukalapak.akunPage().clickPembekuanLapak();
        });

        Then("user validate freeze warning banner not shown on Pelapak Tab", () -> {
            bukalapak.akunPage().verifyFreezeWarningAkunPelapakNotDisplayed();
        });

        When("user tap verification phone number button", () -> {
            bukalapak.akunPage().tapVerificationPhoneNumber();
        });

        When("user go to Performa Lapak page", () -> {
            bukalapak.homePage().selectNavigationTab("akun");
            bukalapak.akunPage().userOnAkunPage();
            bukalapak.akunPage().tapPelapakTab();
            bukalapak.akunPage().clickPerformaLapak();
        });

        And("user verify show pop up verification phone number on Akun page", () -> {
            bukalapak.akunPage().verifyPopUpVerificationPhoneAkun();
        });

        Then("user swipe left on account page to verified phone", () -> {
            bukalapak.akunPage().swipeToPhoneVerificationCard();
            bukalapak.iOSBasePage().tapElement("account_phone_number_verification_button");
        });

        Then("^user validate freeze \"([^\"]*)\" banner shown on Pelapak Tab$", (String freezeContext) -> {
            bukalapak.akunPage().verifyFreezeBannerDisplayed(freezeContext);
        });

        Then("user click hyperlink on the freeze banner", () -> {
            bukalapak.akunPage().clickHyperlinkFreezeBanner();
        });

        And("^user should see section carousel on my account$", () -> {
            bukalapak.akunPage().verifyRecommendationCarousel();
        });

        And("^user verify product card on carousel my account$", () -> {
            bukalapak.akunPage().verifyProductCardCarousel();
        });

        And("^user can swipe left and swipe right product reco$", () -> {
            bukalapak.akunPage().swipeCarouselProduct();
        });

        When("^user tap recommendation carousel on my account$", () -> {
            bukalapak.akunPage().tapRecommendationCarousel();
        });

        Then("^user should not see section carousel on my account$", () -> {
            bukalapak.akunPage().verifyRecommendationCarouselNotExist();
        });

        When("^user select belive env \"([^\"]*)\" from devOps menu", (String env) -> {
            bukalapak.akunPage().setBeliveEnvironment(env);
        });

        When("user go to Jual Barang page", () -> {
            bukalapak.homePage().selectNavigationTab("akun");
            bukalapak.akunPage().userOnAkunPage();
            bukalapak.akunPage().tapPelapakTab();
            bukalapak.akunPage().clickJualBarang();
        });

        When("^user tap edit profile icon on Akun page$", () -> {
            bukalapak.akunPage().tapEditProfilIcon();
        });

        When("^seller banner on Akun page is( not)? displayed$", (String state) -> {
            bukalapak.akunPage().verifySellerBanner(state);
        });

        When("user taps on sembunyikan button", () -> {
            bukalapak.akunPage().tapSellerBannerSembunyikanButton();
        });

        When("user tap alamat pengiriman on pengaturan akun", () -> {
            bukalapak.pengaturanAkunPage().tapAlamatPengiriman();
        });

        When("user tap \"([^\"]*)\" button in navigation bar Akun page", (String navbar) -> {
            bukalapak.akunPage().tapNavigationBar(navbar);
        });
    }
}
