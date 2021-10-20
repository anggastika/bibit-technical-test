package bukalapak.stepDefinitions.homeandcat;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class CategoryStepDefinitions extends TestInstrument implements En {

    public CategoryStepDefinitions() {

        And("^user should see navbar on CM page$", () -> {
            bukalapak.categoryPage().checkIconNavbarCategory();
        });

        When("^user click icon \"([^\"]*)\" on navbar$", (String iconName) -> {
            bukalapak.categoryPage().checkIconSearchNavbar(iconName);
        });

        And("^user should see category on landing page$", () -> {
            bukalapak.categoryPage().checkListCategoryCM();
        });

        When("^user click lihat selengkapnya kategori$", () -> {
            bukalapak.categoryPage().clickShowAllCategoryCM();
        });

        Then("^all category will be showed$", () -> {
            bukalapak.categoryPage().checkAllCategoryCM();
        });

        When("^user click \"([^\"]*)\" on landing page$", (String contentType) -> {
            bukalapak.categoryPage().clickCategoryOnCM(contentType);
        });

        And("^user should see BukaMall on landing page$", () -> {
            bukalapak.categoryPage().checkBukaMallOnCM();
        });

        When("^user click one from list BukaMall$", () -> {
            bukalapak.categoryPage().clickBukaMallOnCM();
        });

        And("^user should see Top Picks on landing page$", () -> {
            bukalapak.categoryPage().checkTopPicksOnCM();
        });

        And("^user should see list of Top Picks$", () -> {
            bukalapak.categoryPage().isContentTopPicksExist();
        });

        And("^user should be redirect to non CM landing page$", () -> {
            bukalapak.categoryPage().checkIsNonCMPageExist();
        });

        And("^user should see Trending Products on Non CM Page$", () -> {
            bukalapak.categoryPage().checkIsTrendingProductNonCMExist();
        });

        When("^user click on Trending Product Non CM$", () -> {
            bukalapak.categoryPage().clickTrendingProductNonCM();
        });

        Then("^user should be redirect to \"([^\"]*)\" Category Landing Page$", (String kategori) -> {
            bukalapak.categoryPage().checkKesehatanLandingPage(kategori);
        });

        And("^user check is page redirect to category non cm page$", () -> {
            bukalapak.categoryPage().isRedirectToNonCMPage();
        });

        And("^user back from landing page$", () -> {
            bukalapak.categoryPage().backFromCategoryLandingPage();
        });

        And("^user check is page redirect to category cm page$", () -> {
            bukalapak.categoryPage().isRedirectToCMPage();
        });

        When("^user scroll to popular section on landing page$", () -> {
            bukalapak.categoryPage().goToPopularSection();
        });

        And("^user should see popular section$", () -> {
            bukalapak.categoryPage().checkAmountOfPopularSection();
        });

        When("^user click on popular section card$", () -> {
            bukalapak.categoryPage().clickPopularCardProduct();
        });

        Then("^user should see product recommendation on landing page$", () -> {
            bukalapak.categoryPage().verifyProductRecommendation();
        });

        When("^user click on product recommendation card$", () -> {
            bukalapak.categoryPage().clickPorductRecommendationCard();
        });

        When("^user scroll down to recommendation product element$", () -> {
            bukalapak.categoryPage().goToRecommendationSection();
        });

        Then("^user should be redirected to Kategori Populer landing page$", () -> {
            bukalapak.categoryPage().verifyOnCategoryLandingPage();
        });

        Then("^user should be redirect to Product Terlaris Category page$", () -> {
            bukalapak.categoryPage().verifyOnProductTerlarisPage();
        });

        And("^user scroll to Terlaris Section on listing category$", () -> {
            bukalapak.categoryPage().scrollToTerlarisCategoryListing();
        });

        And("^user should be redirect to category landing page$", () -> {
            bukalapak.categoryPage().verifyIsRedirectToCategoryPage();
        });

        And("^user scroll to Rekomendasi on category landing page$", () -> {
            bukalapak.categoryPage().scrollToRekomendasiCategory();
        });

        And("^user click subcategory section on category landing page$", () -> {
            bukalapak.categoryPage().clickSubCategoryOnCategoryLanding();
        });

        And("^user should see popular product on category landing page$", () -> {
            bukalapak.categoryPage().verifyPopularProductCategory();
        });

        And("^user click lihat selengkapnya subcategory$", () -> {
            bukalapak.categoryPage().clickSubCategoryLihatSemua();
        });

        And("user click category image icon", () -> {
            bukalapak.categoryPage().clickCategoryImage();
        });

        And("user should see category name same with category image", () -> {
            bukalapak.categoryPage().verifyCategoryNameLandingPage();
        });

        And("user click search field on Category Navigation", () -> {
            bukalapak.categoryPage().clickSearchFieldCategoryNavigation();
        });

        And("user should see Category that searched is showed", () -> {
            bukalapak.categoryPage().verifyCategorySearchedIsShowed();
        });

        And("user search \"([^\"]*)\" on search field Category", (String categoryName) -> {
            bukalapak.categoryPage().searchKeywordCategory(categoryName);
        });

        And("user should be redirect to category navigation page", () -> {
            bukalapak.categoryPage().verifyOnCategoryNavigationPage();
        });

        And("user scroll to Popular Brand Section on category landing page", () -> {
            bukalapak.categoryPage().scrollToBrandSection();
        });

        Then("user should see popular brand products on category landing page", () -> {
            bukalapak.categoryPage().verifyPopularBrandProducts();
        });

        And("user swipe on Popular Brand product on category landing page", () -> {
            bukalapak.categoryPage().swipeLeftOnPupularBrandproduct();
        });

        And("user should see Lihat semua Popular brand card", () -> {
            bukalapak.categoryPage().verifyLihatSemuaPopularBrand();
        });

        And("user should see category keyword suggestion", () -> {
            bukalapak.categoryPage().verifyCategoryKewordSuggestion();
        });

        And("user should be on category tab landing page", () -> {
            bukalapak.categoryPage().isOnCategoryTabScreen();
        });

        And("user tap the category keyword suggestion", () -> {
            bukalapak.categoryPage().typeCategoryKeywordSuggestion();
        });

        And("user validate category reco title", () -> {
            bukalapak.categoryPage().validateCategoryRecoTitle();
        });

        And("^user should see element \"([^\"]*)\" on category reco product", (String info) -> {
            bukalapak.categoryPage().validateCategoryRecoSection(info);
        });

        And("user validate category reco product maximal product appears 18 and minimal product appears 6", () -> {
            bukalapak.categoryPage().validateProductOnCategoryRecoSection();
        });

        When("user tap product on category reco section", () -> {
            bukalapak.categoryPage().tapProductOnCategoryRecoSection();
        });

        And("user click lihat semua terlaris on listing category", () -> {
            bukalapak.categoryPage().tapOnLihatSemuaTerlarisCategory();
        });

        And("user click from product terlaris on listing category", () -> {
            bukalapak.categoryPage().tapOnProductTerlarisCategory();
        });

        Then("user should see popular products on category landing page", () -> {
            bukalapak.categoryPage().validatePopularProducts();
        });

        And("user should see icon no ongkir in category landing page", () -> {
            bukalapak.categoryPage().validateNoOngkirInProductCard();
        });

        And("user should see icon no ongkir in product card reco", () -> {
            bukalapak.categoryPage().validateNoOngkirProductReco();
        });

        And("user click subcategory \"([^\"]*)\" on category landing page", (String subcategoryName) -> {
            bukalapak.categoryPage().clickSpecificSubCategory(subcategoryName);
        });
    }
}
