package bukalapak.stepDefinitions.search;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;


public class ProductListingPageStepDefinitions extends TestInstrument implements En {

    public ProductListingPageStepDefinitions() {

        Then("^user should see( and verify)? product on product listing$", (String verify) -> {
            bukalapak.productListingPage().checkProductOnProductListing(verify);
        });

        And("^user should be redirect to pop up add to cart$", () -> {
            bukalapak.productListingPage().checkIsRedirectToPopUpAddToCart();
        });

        And("^user go to product listing page using deeplink \"([^\"]*)\"$", (String deeplink) -> {
            bukalapak.productListingPage().viaDeeplink(deeplink);
        });

        And("^user should be redirect to product detail$", () -> {
            bukalapak.productListingPage().checkIsOnProductDetail();
        });

        And("^user click on tab Filter$", () -> {
            bukalapak.productListingPage().clickOnTabFilter();
        });

        And("^user should be able to click product from listing$", () -> {
            bukalapak.productListingPage().clickProductOnListing();
        });

        And("^user should see BukaMall products on listing$", () -> {
            bukalapak.productListingPage().checkIsBukaMallProductsExist();
        });

        And("^user should see discounted products on listing$", () -> {
            bukalapak.productListingPage().checkIsDiscountedProductsExist();
        });

        And("^user back to home from product listing$", () -> {
            bukalapak.productListingPage().backToHome();
        });

        And("^user get product name$", () -> {
            bukalapak.productListingPage().getTextProductName();
        });

        And("^user should be able to click product$", () -> {
            bukalapak.productListingPage().clickProductFromListing();
        });

        And("^user should be able to click buy from \"([^\"]*)\"$", (String buyFrom) -> {
            bukalapak.productListingPage().clickBuy(buyFrom);
        });

        And("^user buy product \"([^\"]*)\" from product listing$", (String productName) -> {
            bukalapak.productListingPage().buyFromProductListing(productName);
        });

        And("^user buy product \"([^\"]*)\" and buy again other product$", (String productName) -> {
            bukalapak.productListingPage().buyAgainProductListing(productName);
        });

        And("^user select \"([^\"]*)\" mode listing$", (String listingMode) -> {
            switch (listingMode) {
                case "capabilities/grid":
                    bukalapak.productListingPage().selectGridMode();
                    break;
                case "list":
                    bukalapak.productListingPage().selectListMode();
                    break;
                default:
                    break;
            }
        });

        And("^user click on tab Urutkan$", () -> {
            bukalapak.productListingPage().clickTabUrutkan();
        });

        And("^user select urutkan \"([^\"]*)\"$", (String urutkanType) -> {
            bukalapak.productListingPage().selectUrutkanTab(urutkanType);
        });

        And("^user should see urutkan terbaru active$", () -> {
            bukalapak.productListingPage().checkUrutkanTerbaruIsActive();
        });

        And("^user should see urutkan termurah active$", () -> {
            bukalapak.productListingPage().checkUrutkanTermurahIsActive();
        });

        And("^user scroll to non promoted product$", () -> {
            bukalapak.productListingPage().scrollToNonPromotedProduct();
        });

        Then("^user should be able to click product from listing with index \"([^\"]*)\"$", (String index) -> {
            bukalapak.productListingPage().clickProductAtIndex(index);
        });

        And("^user should see urutkan termahal active$", () -> {
            bukalapak.productListingPage().checkUrutkanTermahalIsActive();
        });

        And("^user should see urutkan terlaris active$", () -> {
            bukalapak.productListingPage().checkUrutkanTerlarisIsActive();
        });

        And("^user should see urutkan relevansi active$", () -> {
            bukalapak.productListingPage().checkUrutkanRelevansiIsActive();
        });

        And("^user should see kategori on info barang active$", () -> {
            bukalapak.productListingPage().checkCategoryOnListing();
        });

        And("^user scroll to product that has rating$", () -> {
            bukalapak.productListingPage().scrollToProductRating();
        });

        And("^user should be able to click product's rating from listing$", () -> {
            bukalapak.productListingPage().clickProductsRating();
        });

        When("user Search barang on Barang Dijual page", () -> {
            bukalapak.productListingPage().searchProduct("testing regression");
        });

        And("^user back to home from pop up add to cart$", () -> {
            bukalapak.productListingPage().backToHomeFromATCPopUp();
        });

        And("^user click icon delete from pop up add to cart$", () -> {
            bukalapak.productListingPage().clickDeleteFromPopUp();
        });

        When("^user tap \"([^\"]*)\" tab on search result page$", (String tabName) -> {
            bukalapak.productListingPage().selectTab(tabName);
        });

        When("^product catalog cards displays related catalog based on keyword \"([^\"]*)\"$", (String keyword) -> {
            bukalapak.productListingPage().verifyCatalogCardProductName(keyword);
        });

        When("^product listing displays catalog tab that contains catalog listing$", () -> {
            bukalapak.productListingPage().verifyCatalogTabContent();
        });

        // catalog card
        When("^product listing page displays product catalog card onboarding$", () -> {
            bukalapak.productListingPage().assertTrue(bukalapak.iOSBasePage().isElementVisible("product_catalog_card_onb"), "Product listing page not displays product catalog card onboarding");
        });

        When("^user able to complete product catalog card onboarding$", () -> {
            bukalapak.productListingPage().isAbleToCompleteCatalogCardOnb();
        });

        When("^product catalog cards displays max 5 product catalog cards$", () -> {
            bukalapak.productListingPage().verifyCatalogCardAmount();
        });

        When("^user swipe to end of product catalog card$", () -> {
            bukalapak.productListingPage().swipeLeftToLihatSemuaCatalogCard();
        });

        When("^user filtering by \"([^\"]*)\" that contains product catalog$", (String filter) -> {
            bukalapak.productListingPage().filterCatalogBySpecificFilter(filter);
        });

        When("^product catalog card not displays Lihat Semua button$", () -> {
            bukalapak.productListingPage().assertFalse(bukalapak.iOSBasePage().isElementVisible("product_catalog_card_lihat_semua"), "Catalog card displays Lihat Semua button");
        });

        When("^product catalog card contain valid content$", (DataTable arg0) -> {
            bukalapak.productListingPage().verifyCatalogCardContent(arg0);
        });

        When("^catalog listing displays related catalog based on keyword \"([^\"]*)\"$", (String keyword) -> {
            bukalapak.productListingPage().verifyCatalogListingProductName(keyword);
        });

        When("^user tap on cart icon$", () -> {
            bukalapak.productListingPage().tapCartIcon();
        });

        And("^user should see category active on product listing$", () -> {
            bukalapak.productListingPage().checkCategoryName();
        });

        And("^user click back from product listing revamp$", () -> {
            bukalapak.productListingPage().clickBackFromPLRevamp();
        });

        Then("^user should be redirect to product listing with category applied$", () -> {
            bukalapak.productListingPage().checkCategoryOnProductListing();
        });

        And("^user should see category \"([^\"]*)\" is active on product listing$", (String categoryName) -> {
            bukalapak.productListingPage().isCategoryApplied(categoryName);
        });
      
        And("^user tap \"([^\"]*)\" quick filter$", (String filter) -> {
            bukalapak.productListingPage().clickOnTheQuickFilter(filter);
        });

        Then("^user should see the \"([^\"]*)\" filter move to the first$", (String firstFilter) -> {
            bukalapak.productListingPage().verifyFirstActiveQuickFilter(firstFilter, "select");
        });

        When("^user click on the \"([^\"]*)\" active quick filter$", (String filter) -> {
            bukalapak.productListingPage().clickOnTheQuickFilter(filter);
        });

        Then("^user verify quick filter \"([^\"]*)\" is active$", (String filter) -> {
            bukalapak.productListingPage().verifyQuickFilterActive(filter);
        });

        Then("^user should see the quick filter \"([^\"]*)\" change into unselected$", (String filter) -> {
            bukalapak.productListingPage().verifyFirstActiveQuickFilter(filter, "unselect");
        });

        When("user scroll down to catalog card in search listing$", () -> {
            bukalapak.productListingPage().scrollDownToCatalogCard();
        });

        And("^user verify product recommendation info displays related info based on \"([^\"]*)\"$", (String keyword) -> {
            bukalapak.productListingPage().verifyCatalogTag(keyword);
        });

        And("^user verify catalog listing page contain valid content$", () -> {
            bukalapak.productListingPage().verifyCatalogListing();
        });

        When("^user verify catalog card info contain valid content$", () -> {
            bukalapak.productListingPage().verifyCatalogCard();
        });

        Then("^user should be redirect to own product detail$", () -> {
            bukalapak.productListingPage().checkIsOnOwnProductDetail();
        });

        And("^user verify favorite icon on product card position \"([^\"]*)\" is (active|not active)$", (Integer index, String state) -> {
            bukalapak.productListingPage().verifyIsFavorited(index, state);
        });

        And("^user tap favorite button on product card position \"([^\"]*)\"$", (Integer index) -> {
            bukalapak.productListingPage().tapFavoriteButton(index);
        });

        When("^user sort result by \"([^\"]*)\"$", (String by) -> {
            bukalapak.productListingPage().sort(by);
        });

        Then("^user should see sort by \"([^\"]*)\" is active$", (String sortType) -> {
            bukalapak.productListingPage().verifySortTabActive(sortType);
        });

        Then("^user verify price on product card position \"([^\"]*)\" is (greater than|less than|greater than or equal|less than or equal) \"([^\"]*)\"$", (Integer index, String state, Float price) -> {
            bukalapak.productListingPage().verifyProductPrice(index, state, price);
        });

        Then("^user is shown pantau saingan section on search product$", () -> {
            bukalapak.productListingPage().verifyPantauSainganSection();
        });

        And("^user verify product card at position \"([^\"]*)\" (has|has no) \"([^\"]*)\" (tag|badge|label)$", (Integer index, String state, String component, String type) -> {
            bukalapak.productListingPage().verifyCardComponent(index, state, component, type);
        });

        And("^user verify search result is changed$", () -> {
            bukalapak.productListingPage().verifyOrderChange();
        });

        And("^user verify product card at position \"([^\"]*)\" has label Terjual$", (Integer index) -> {
            bukalapak.productListingPage().verifyLabel(index);
        });

        Then("^user should see product listing with selected category$", () -> {
            bukalapak.productListingPage().validateCategoryApplied();
        });

        When("^user tap x button on omnisearch in result page$", () -> {
            bukalapak.productListingPage().clearOmnisearch();
        });

        Then("^user tap product card position \"([^\"]*)\"$", (String index) -> {
            bukalapak.productListingPage().clickProductAtIndex(index);
        });

        And("^user see Pilih Alamat menu$", () -> {
            bukalapak.productListingPage().verifySelectAddressMenu();
        });

        And("^user choose Lokasi Sekarang$", () -> {
            bukalapak.productListingPage().selectAddress(1);
        });

        And("^user choose address option position \"([^\"]*)\"$", (Integer index) -> {
            bukalapak.productListingPage().selectAddress(index + 1);
        });

        And("^user should see category applied on Filter$", () -> {
            bukalapak.productListingPage().verifyCategoryFilter();
        });

        And("user verify tab Filter shows no active filter", () -> {
            bukalapak.productListingPage().verifyNoFilter();
        });

        And("user verify product at position \"([^\"]*)\" is sent from \"([^\"]*)\"", (Integer index, String location) -> {
            bukalapak.productListingPage().verifySendFrom(index, location);
        });

        And("^user verify (continuation|nudge|games|best selling) card is in product listing$", (String type) -> {
            bukalapak.productListingPage().verifySpecialCardPosition(type);
        });

        When("^user tap( keyword suggestion on)? (continuation|nudge|best selling) card$", (String flag, String type) -> {
            bukalapak.productListingPage().tapOnSpecialCardOption(flag, type);
        });

        Then("^user should see search result \"([^\"]*)\" message$", (String type) -> {
            bukalapak.productListingPage().verifyResultMessage(type);
        });

        And("^user should( not)? see product recomendations$", (String state) -> {
            bukalapak.productListingPage().verifyRecommendation(state == null);
        });

        And("^user should see related keywords$", () -> {
            bukalapak.productListingPage().verifyRelatedKeywords();
        });

        And("^user tap related keyword suggestion$", () -> {
            bukalapak.productListingPage().tapRelatedKeyword();
        });

        And("^user tap button \"([^\"]*)\" in product listing", (String label) -> {
            bukalapak.productListingPage().tapButtonOnWarningPage(label);
        });

        And("^user back from subcategory page$", () -> {
            bukalapak.productListingPage().backFromSubCategoryPage();
        });

        Then("^user verify is in best selling page$", () -> {
            bukalapak.productListingPage().verifyInBestSellingPage();
        });

        And("^user tap product card position \"([^\"]*)\" in best selling page$", (Integer position) -> {
            bukalapak.productListingPage().tapcardInBestSelling(position);
        });

        When("^user go to product listing page for keyword \"([^\"]*)\" using deeplink for active campaign$", (String keyword) -> {
            bukalapak.productListingPage().redirectCampaignDeeplink(keyword);
        });

        And("^user verify product card position \"([^\"]*)\" has campaign banner$", (Integer position) -> {
            bukalapak.productListingPage().verifyCampaignBannerProductCard(position);
        });

        And("^user tap campaign filter position \"([^\"]*)\"$", (Integer position) -> {
            bukalapak.productListingPage().tapCampaignFilter(position);
        });

        Then("^user verify search result is different with previous one$", () -> {
            bukalapak.productListingPage().verifyProductChange();
        });
    }
}
