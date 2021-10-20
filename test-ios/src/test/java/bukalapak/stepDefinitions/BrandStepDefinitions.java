package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BrandStepDefinitions extends TestInstrument implements En {

    public BrandStepDefinitions() {
        And("user input bukamall search field alchemy with \"([^\"]*)\" text", (String arg0) -> {
            bukalapak.iOSBasePage().typeAndEnterValueWithTimeOut("bukamall_alchemy_search_text_field", arg0 + "\n");
        });

        When("^the user swipe the alchemy category tab$", () -> {
            bukalapak.bukamallAlchemyPage().swipeOnCategoryTabAlchemy();
        });

        And("user click bukamall brand page beli element", () -> {
            bukalapak.bukamallBrandPage().clickBeli();
        });

        And("user input brand page search field with \"([^\"]*)\" text", (String arg0) -> {
            bukalapak.iOSBasePage().typeAndEnterValueWithTimeOut("brand_page_search_text_field", arg0 + "\n");
        });

        And("user input brand page search result page search field with \"([^\"]*)\" text", (String arg0) -> {
            bukalapak.iOSBasePage().typeAndEnterValueWithTimeOut("brandpage_search_result_search_text_field", arg0 + "\n");
        });

        Then("^user should be redirect to BukaMall page$", () -> {
            bukalapak.bukamallAlchemyPage().isBukaMallPageExist();
        });

        Given("user is in \"brandpage\" page", () -> {
            bukalapak.bukamallBrandPage().userOnBukamallBrandPage();
        });

        Given("user is in \"brand_search_result\" page", () -> {
            bukalapak.bukamallBrandSearchResultPage().userOnBukamallBrandSearchResultPage();
        });

        Given("user is in \"brandproduct_search_result\" page", () -> {
            bukalapak.bukamallBrandProductSearchResultPage().userOnBukamallBrandProductSearchResultPage();
        });

        Given("user is in \"product_details\" page", () -> {
            bukalapak.bukamallBrandPDPPage().userOnBrandPDP();
        });

        Given("user is in \"pdp_alchemy\" page", () -> {
            bukalapak.bukamallBrandPDPPage().userOnBrandPDPAlchemy();
        });

        Given("user is in \"brandcategory_alchemy\" page", () -> {
            bukalapak.bukamallCategoryPageAlchemyPage().userOnBukamallCategoryPageAlchemy();
        });

        Given("user is in \"bukamall_category_alchemy\" page", () -> {
            bukalapak.bukamallCategoryPageAlchemyPage().userOnBukamallCategoryPageAlchemy();
        });

        Given("user is in \"bukamall_produk_pilihan\" page", () -> {
            bukalapak.bukamallProdukPilihanPage().userOnProdukPilihanPage();
        });

        Given("user is in \"bukamall_brand_terbaru_alchemy\" page", () -> {
            bukalapak.bukamallBrandTerbaruAlchemyPage().userOnBukamallBrandTerbaruAlchemyPage();
        });

        Given("user is in \"bukamall_alchemy\" page", () -> {
            bukalapak.bukamallAlchemyPage().userOnBukamallPageAlchemy();
        });

        Given("user is in \"brandcatalog\" page", () -> {
            bukalapak.bukamallCatalogPage().userOnBukamallCatalogPage();
        });

        Given("user is in \"brandpage_info\" page", () -> {
            bukalapak.bukamallBrandPage().userOnTabInformasi();
        });

        Given("user is in \"feedback_pelapak\" page", () -> {
            bukalapak.bukamallBrandPage().userOnFeedbackPelapakPage();
        });

        Then("^user should be redirect to BukaMall landing page$", () -> {
            bukalapak.bukamallBrandPage().verifyOnBukaMallLanding();
        });

        Then("^user should see chat icon on BukaMall page$", () -> {
            bukalapak.bukamallBrandPage().verifyChatOnBukaMall();
        });

        And("^user should see BukaMart on BukaMall landing page$", () -> {
            bukalapak.bukamallBrandPage().verifyBukaMartOnBukaMall();
        });

        And("^user should see voucher on BukaMall landing page$", () -> {
            bukalapak.bukamallBrandPage().verifyVoucherOnBukaMall();
        });

        Then("^user should be redirect to voucher page$", () -> {
            bukalapak.bukamallBrandPage().verifyVoucherPage();
        });

        And("^user should see cart icon on BukaMall landing page$", () -> {
            bukalapak.bukamallBrandPage().verifyCartIconOnBukaMall();
        });

        And("^user should see Produk Pilihan Shortcut on BukaMall page$", () -> {
            bukalapak.bukamallBrandPage().verifyProdukPilihanShortcut();
        });

        Then("^user should be redirect to Produk Pilihan page$", () -> {
            bukalapak.bukamallBrandPage().verifyProdukPilihanPage();
        });

        And("^user should see Brand Terbaru icon on BukaMall landing page$", () -> {
            bukalapak.bukamallBrandPage().verifyBrandTerbaruShortcut();
        });

        Then("^user should be redirect to Brand Terbaru page$", () -> {
            bukalapak.bukamallBrandPage().verifyBrandTerbaruPage();
        });

        And("^user should see Brand Pilihan title$", () -> {
            bukalapak.bukamallBrandPage().verifyBrandPilihanTitle();
        });

        And("^user should see Brand Dalam Negri title$", () -> {
            bukalapak.bukamallBrandPage().verifyBrandDalanNegriTitle();
        });

        Then("^user should be redirect to Brand Dalam Negri page$", () -> {
            bukalapak.bukamallBrandPage().verifyBrandDalamNegriPage();
        });

        And("^user should see product listing from Category Tab$", () -> {
            bukalapak.bukamallBrandPage().verifyProductListingBukaMall();
        });

        Then("user should see bukaMall notif icon", () -> {
            bukalapak.bukamallBrandPage().verifyNotifIcon();
        });

        Then("^user should be redirect to Brand Resmi page$", () -> {
            bukalapak.bukamallBrandPage().verifyBrandResmiPage();
        });

        Then("^user should be redirect to brand page$", () -> {
            bukalapak.bukamallBrandPage().verifyBukaMallBrandPage();
        });

        And("^user should see Product Terbaru title on BukaMall landing page$", () -> {
            bukalapak.bukamallBrandPage().verifyNewestProductTitle();
        });

        Then("^user should be redirect to Produk Terbaru page$", () -> {
            bukalapak.bukamallBrandPage().verifyProdukTerbaruPage();
        });

        And("^user should see Popular Section on BukaMall landing page$", () -> {
            bukalapak.bukamallBrandPage().verifyPopularSectionBukaMall();
        });

        And("user should see bukaMall icon", () -> {
            bukalapak.bukamallBrandPage().verifyBukaMallIcon();
        });

        And("user tap BukaMart on BukaMall landing page", () -> {
            bukalapak.bukamallBrandPage().tapBukaMartOnBukaMall();
        });

        And("user validate category reco title on bukaMall landing page", () -> {
            bukalapak.bukamallAlchemyPage().validateRecoTitle();
        });

        And("user tap recommnedation product on bukaMall reco product", () -> {
            bukalapak.bukamallAlchemyPage().tapRecommendationProduct();
        });

        And("user should see infinite recommendation on bukaMall landing page", () -> {
            bukalapak.bukamallAlchemyPage().validateInfiniteProductReco();
        });

        And("^user should see element \"([^\"]*)\" on bukaMall reco product", (String info) -> {
            bukalapak.bukamallAlchemyPage().validateInfoProductReco(info);
        });

        Then("the Bukalapak Merch store page will have displayed", () -> {
            bukalapak.bukamallBrandPage().verifyOnMerchandiseOfficial();
        });
    }
}
