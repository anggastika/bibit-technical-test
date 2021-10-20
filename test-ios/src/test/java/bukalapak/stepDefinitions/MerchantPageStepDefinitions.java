package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class MerchantPageStepDefinitions extends TestInstrument implements En {

    public MerchantPageStepDefinitions() {
        Given("user is in \"merchant page\" page", () -> {
            bukalapak.merchantPage().userOnMerchantPage();
        });

        Given("user is in merchant page", () -> {
            bukalapak.merchantPage().userOnMerchantPage();
        });

        Given("user is in merchant page with deeplink \"([^\"]*)\"", (String deeplink) -> {
            bukalapak.merchantPage().userOnMerchantPageWithDeeplink(deeplink);
        });

        Then("user should see modal feedback in merchant page", () -> {
            bukalapak.merchantPage().isModalFeedbackShow();
        });

        Then("user should see modal waktu proses in merchant page", () -> {
            bukalapak.merchantPage().isModalProcessTimeShow();
        });

        Then("user should see modal detail info lapak in merchant page", () -> {
            bukalapak.merchantPage().isModalDetailInfoLapakShow();
        });

        Then("user is on filter merchant page", () -> {
            bukalapak.merchantPage().isOnFilterMerchantPage();
        });

        Then("user should see modal pelapak terbaik in merchant page", () -> {
            bukalapak.merchantPage().isModalPelapakTerbaikShow();
        });

        Then("user should see modal pelapak premium in merchant page", () -> {
            bukalapak.merchantPage().isModalPelapakPremiumShow();
        });

        Then("user should see modal etalase in merchant page", () -> {
            bukalapak.merchantPage().isModalEtalaseShow();
        });

        Then("user should see pop up waktu proses in merchant page", () -> {
            bukalapak.merchantPage().isPopUpWaktuProsesShow();
        });

        Then("user should see merchant feedback", () -> {
            bukalapak.merchantPage().isOnMerchantFeedback();
        });

        Then("user should see langganan premium", () -> {
            bukalapak.merchantPage().isOnMerchantPremium();
        });

        Then("^user is shown Lapak Terbaik badge on product list merchant page$", () -> {
            bukalapak.merchantPage().verifyBadgeLapakTerbaikOnProducts();
        });

        Then("^user is shown Bekas badge on product list merchant page$", () -> {
            bukalapak.merchantPage().verifyBadgeBekasOnProducts();
        });

        Then("^user is shown Super Seller badge on product list merchant page$", () -> {
            bukalapak.merchantPage().verifyBadgeSuperSellerOnProducts();
        });

        Then("^user is (not shown|shown) merchant voucher section$", (String onVoucher) -> {
            bukalapak.merchantPage().verifyVoucherLapakSection(onVoucher.equals("shown"));
        });

        Then("^user is shown BukaMall badge on product list merchant page$", () -> {
            bukalapak.merchantPage().verifyBadgeBukaMall();
        });

        Then("^user taps on Voucher Lapak untukmu$", () -> {
            bukalapak.merchantPage().tapVoucherLapakUntukmu();
        });

        Then("^user is shown slider Voucher Untukmu$", () -> {
            bukalapak.merchantPage().verifySliderVoucherDetail();
        });

        Then("^user is shown voucher on position (\\d+) with etalase \"([^\"]*)\"$", (Integer index, String etalaseType) -> {
            bukalapak.merchantPage().verifyVoucherLapakEtalase(index - 1, etalaseType);
        });

        Then("^user close slider Voucher Untukmu in merchant page$", () -> {
            bukalapak.merchantPage().closeSliderVoucherLapak();
        });

        Then("^user is (not shown|shown) voucher on position (\\d+) with \"([^\"]*)\"$", (String isShown, Integer index, String voucherDiskonAmount) -> {
            bukalapak.merchantPage().verifyVoucherLapakDiskon(isShown,index - 1, voucherDiskonAmount);
        });

        Then("^user is shown voucher on position (\\d+) with minimum transaction \"([^\"]*)\"$", (Integer index, String minTrx) -> {
            bukalapak.merchantPage().verifyVoucherLapakMinTrx(index - 1, minTrx);
        });

        Then("^user is shown voucher on position (\\d+) with code \"([^\"]*)\"$", (Integer index, String voucherCode) -> {
            bukalapak.merchantPage().verifyVoucherLapakCode(index - 1, voucherCode);
        });

        And("^user should see search field on merchant page$", () -> {
            bukalapak.merchantPage().verifySearchFieldMerchant();
        });

        When("^user search \"([^\"]*)\" on merchant page$", (String keywords) -> {
            bukalapak.merchantPage().searchProductMerchant(keywords);
        });

        And("^user should see product on product listing merchant$", () -> {
            bukalapak.merchantPage().verifyOnProductListingMerchant();
        });

        And("^user should be redirect to merchant page$", () -> {
            bukalapak.merchantPage().verifyIsOnMerchantPage();
        });

        And("^user should be redirect to Atur Barang Unggulan page$", () -> {
            bukalapak.merchantPage().verifyOnAturUnggulanPage();
        });

        When("^user click on (Belum Tampil|Ditampilkan)? label on Atur Barang Unggulan page$", (String state) -> {
            bukalapak.merchantPage().selectBarangUnggulanSection(state);
        });

        Then("^user should be redirect to Barang Unggulan that not active yet$", () -> {
            bukalapak.merchantPage().validateDraggableBarangUnggulanModal();
        });

        Then("^user should see products on Barang Unggulan$", () -> {
            bukalapak.merchantPage().verifyProductsBarangUnggulan();
        });

        And("^user should see status changes into (Ditampilkan|Disembunyikan)?$", (String state) -> {
            bukalapak.merchantPage().validateUpdateState(state);
        });

        And("^user should be redirect to Barang Unggulan that active$", () -> {
            bukalapak.merchantPage().verifyBarangUnggulanActive();
        });

        And("^user should see status changes into Belum Tampil$", () -> {
            bukalapak.merchantPage().verifyCountOfActiveButton();
        });

        Then("^user should \"([^\"]*)\" on merchant Page$", (String barangUnggulanType) -> {
            bukalapak.merchantPage().verifyBarangUnggulan(barangUnggulanType);
        });

        And("^user should see button atur barang unggulan on merchant page$", () -> {
            bukalapak.merchantPage().verifyButtonOnMerchantPage();
        });

        And("^user scoll down to \"([^\"]*)\" element$", (String arg0) -> {
            bukalapak.iOSBasePage().swipeUpToElement(arg0);
        });

        And("^user should see products on merchant page$", () -> {
            bukalapak.merchantPage().verifyProductMerchant();
        });

        And("^user should see products name$", () -> {
            bukalapak.merchantPage().verifyProductName();
        });

        When("^user click one product from list products$", () -> {
            bukalapak.merchantPage().clickProductOnMerchantPage();
        });

        And("^user should see product name on product detail merchant$", () -> {
            bukalapak.merchantPage().checkProductMerchantName();
        });

        And("^user should see \"([^\"]*)\" on merchant page$", (String arg0) -> {
            bukalapak.merchantPage().verifyWidgetsOnMerchantPage(arg0);
        });

        Then("^user should be redirect to product listing merchant$", () -> {
            bukalapak.merchantPage().verifyContentOnProductListingMerchant();
        });

        And("^user should be redirect to collection page$", () -> {
            bukalapak.merchantPage().verifyCollectionPage();
        });

        And("^user should be redirect to best seller page$", () -> {
            bukalapak.merchantPage().verifyBestSellerPage();
        });

        And("^user should see \"([^\"]*)\" element$", (String arg0) -> {
            bukalapak.iOSBasePage().verifyElementExist(arg0);
        });

        And("^user should be redirect to seller page$", () -> {
            bukalapak.merchantPage().verifyOnSellerPage();
        });

        And("^user should see chat button on merchant page$", () -> {
            bukalapak.merchantPage().clickOnChatButtonMerchant();
        });

        And("^user should see Statistik information$", () -> {
            bukalapak.merchantPage().verifyStatistikTitle();
        });

        And("^user should should see waktu kirim$", () -> {
            bukalapak.merchantPage().checkWaktuKirim();
        });

        And("^user should see count of pesanan diterima$", () -> {
            bukalapak.merchantPage().countOfPesananDiterima();
        });

        And("^user should see count of pelanggan$", () -> {
            bukalapak.merchantPage().countOfPelanggan();
        });

        And("^user should see Feedback$", () -> {
            bukalapak.merchantPage().checkFeedbackTitle();
        });

        And("^user click show all Feedback$", () -> {
            bukalapak.merchantPage().clickTampilkanFeedback();
        });

        And("^user should be redirect to feedback page$", () -> {
            bukalapak.merchantPage().verifyFeedbackPage();
        });

        And("^user should see catatan lapak$", () -> {
            bukalapak.merchantPage().verifyCatatanPelapak();
        });

        And("^user should see pop up waktu kirim info$", () -> {
            bukalapak.merchantPage().verifyPopUpWaktuKirimInfo();
        });

        Then("^user should see button on merchant page$", () -> {
            bukalapak.merchantPage().verifyButtonMerchantPage();
        });

        Then("^user click back button on merchant page$", () -> {
            bukalapak.merchantPage().clickBackButton();
        });

        Then("user should see copy etalase change to \"([^\"]*)\"", (String etalase) -> {
            bukalapak.merchantPage().validateCopyEtalaseMenu(etalase);
        });

        Then("user should see copy urutkan change to \"([^\"]*)\"", (String urutkan) -> {
            bukalapak.merchantPage().validateCopyUrutkanMenu(urutkan);
        });

        Then("user should see search box change to \"([^\"]*)\"", (String keyword) -> {
            bukalapak.merchantPage().validateCopyOnSearchBox(keyword);
        });

        Then("user should see search box change to value \"([^\"]*)\"", (String keyword) -> {
            bukalapak.merchantPage().validateCopyOnSearchBoxValue(keyword);
        });

        Then("user tap on product highlight merchant page", () -> {
            bukalapak.merchantPage().tapOnProductHighlightMerchantPage();
        });

        Then("user should see product highlight merchant page", () -> {
            bukalapak.merchantPage().isProductHighlightShow();
        });

        When("user reports a user from merchant page", () -> {
            bukalapak.reportPage().closePage();
            bukalapak.merchantPage().userOnMerchantPage();
            bukalapak.merchantPage().tapThreeDotsButton();
            bukalapak.merchantPage().tapLaporkan();
        });

        And("user click tab Barang on merchant page", () -> {
            bukalapak.merchantPage().tapOnBarangTab();
        });

        And("user should be redirect to Tab Barang page", () -> {
            bukalapak.merchantPage().verifyOnTabBarangPage();
        });

        And("user click Etalase Tab on merchant page", () -> {
            bukalapak.merchantPage().tapOnEtalaseMerchatPage();
        });

        And("user should be redirect to pop up Etalase", () -> {
            bukalapak.merchantPage().verifyEtalasePopUp();
        });

        And("user click etalase list", () -> {
            bukalapak.merchantPage().tapEtalaseList();
        });

        And("user should see etalase is actived", () -> {
            bukalapak.merchantPage().verifyEtalaseActived();
        });

        And("^user should see urutkan (.*) is actived$", (String urutkanType) -> {
            bukalapak.merchantPage().verifyUrutkanMerchantPage(urutkanType);
        });

        And("user click Tab Urutkan that actived on Merchant Page", () -> {
            bukalapak.merchantPage().tapOnUrutkanTabMerchantPage();
        });

        And("^user click Urutkan (.*)$", (String urutkanName) -> {
            bukalapak.merchantPage().tabOnUrutkanNameMerchantPage(urutkanName);
        });

        And("user click tab Profil on merchant page", () -> {
            bukalapak.merchantPage().tapOnProfilTabMerchantPage();
        });

        And("user should see Reputasi information merchant", () -> {
            bukalapak.merchantPage().verifyReputasiInfoMerchant();
        });

        Then("user go to flash deal tab", () -> {
            bukalapak.merchantPage().tapFlashDealTab();
        });

        Then("user verify flash deal for Non Participated user message exist", () -> {
            bukalapak.merchantPage().validateFDNonParticipatedUser();
        });

        And("user click subscribe on merchant page", () -> {
            bukalapak.merchantPage().tapOnSubscribeButton();
        });

        And("user should see button subscribe will be changes", () -> {
            bukalapak.merchantPage().verifySubscribeButton();
        });

        And("user filtering that contains product condition", () -> {
            bukalapak.merchantPage().tapOnFilter();
            bukalapak.merchantPage().tapKondisiBarangFilter();
            bukalapak.merchantPage().tapBarangBekas();
            bukalapak.merchantPage().tapOnFilterTerapkanButton();
        });

        And("user verify flash deal merchant entry point exist", () -> {
            bukalapak.merchantPage().validateMerchantFDEntryPoint();
        });

        And("user go to Flash Deal Merchant Onboarding page", () -> {
            bukalapak.merchantPage().goToFDMerchantPage();
        });

        And("user redirect to landing page Flash Deal Lapak", () -> {
            bukalapak.merchantPage().validateFDOnboardingPageDisplayed();
        });

        And("user click tab Filter on merchant page", () -> {
            bukalapak.merchantPage().tapOnFilterTabBarang();
        });

        And("user should see Barang Bekas filter is active", () -> {
            bukalapak.merchantPage().verifyBarangBekasFilterActive();
        });

        And("user should see product listing after filter", () -> {
            bukalapak.merchantPage().verifyProductAfterFilterApplied();
        });

        And("user click on Kondisi Barang Filter", () -> {
            bukalapak.merchantPage().tapOnKondisiBarangFilter();
        });

        And("user verify Non Super Seller/ Non Bukamall entry point exist", () -> {
            bukalapak.merchantPage().validateNonSsFDEntryPoint();
        });

        And("user click Aktifkan Super Seller button", () -> {
            bukalapak.merchantPage().tapAktifkanSuperSeller();
        });

        And("user click first product on merchant page", () -> {
            bukalapak.merchantPage().tapFirstProduct();
        });

        Then("user should see icon no ongkir in product list", () -> {
            bukalapak.merchantPage().validateNoOngkirInProductCard();
        });

        Then("user should be redirect to merchant page with pop up age restriction", () -> {
            bukalapak.merchantPage().validatePopUpAgeRestriction();
        });

        And("user click Ya, saya berusia 18+ button on pop up age restriction", () -> {
            bukalapak.merchantPage().tapYaButton();
        });

        And("user click Tidak button on pop up age restriction", () -> {
            bukalapak.merchantPage().tapTidakButton();
        });
    }
}
