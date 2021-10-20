package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import bukalapak.data.InvestmentData;
import cucumber.api.java8.En;
import org.junit.Assert;

public class BukaReksaProductStepDefinitions extends TestInstrument implements En {

    public BukaReksaProductStepDefinitions() {

        //region Product List
        And("^user validate promoted product displayed", () -> {
            bukalapak.bukaReksaProductPage().validatePromotedProductDisplayed();
        });

        When("^user tap on \"([^\"]*)\" as Quick Filter options", (String filterName) -> {
            bukalapak.bukaReksaProductPage().selectQuickFilter(filterName);
            InvestmentData.setQuickFilter(filterName);
        });

        Then("user verify filter result in BukaReksa product", () -> {
            String quickFilter = InvestmentData.getQuickFilter();
            bukalapak.bukaReksaProductPage().verifyQuickFilter(quickFilter);
        });

        When("^user should be able to see product risk section displayed", () -> {
            bukalapak.bukaReksaProductPage().isProductRiskDisplayed();
        });

        When("^user search product \"([^\"]*)\" as product name", (String productName) -> {
            bukalapak.bukaReksaProductPage().searchProduct(productName);
        });

        Then("^user tap on BukaReksa product filter button", () -> {
            bukalapak.bukaReksaProductPage().tapOnProductFilterButton();
        });

        Then("^user should be able to see Filter Product page displayed", () -> {
            bukalapak.bukaReksaProductPage().isFilterPageDisplayed();
        });

        Then("^user select Bukareksa filtering by product \"([^\"]*)\"", (String filterOption) -> {
            bukalapak.bukaReksaProductPage().selectProductFilter(filterOption);
        });

        Then("^user tap on Terapkan Filter BukaReksa button", () -> {
            bukalapak.bukaReksaProductPage().tapOnApplyFilterBtn();
        });

        Then("^user tap on Reset Filter BukaReksa button", () -> {
            bukalapak.bukaReksaProductPage().tapOnResetFilterBtn();
        });

        Then("^user should be able to see Filter button selected", () -> {
            bukalapak.bukaReksaProductPage().checkProductFilterButtonSelected();
        });

        Then("^user should be able to see result of filtered product by \"([^\"]*)\"", (String method) -> {
            switch (method.toUpperCase()) {
                case "NAME":
                    bukalapak.bukaReksaProductPage().checkFilteredProductByProductName();
                    break;
                case "CATEGORY":
                    bukalapak.bukaReksaProductPage().checkFilteredProductByCategory();
                    break;
                default:
                    Assert.fail(method.toUpperCase() + " isn't a method name");
            }
        });

        When("^user select BukaReksa product", () -> {
            bukalapak.bukaReksaProductPage().selectProductList();
        });

        When("^user should be able to see Bukareksa product listing displayed", () -> {
            bukalapak.bukaReksaProductPage().isProductListingDisplayed();
        });

        When("^user should be able to see no favorite product listing", () -> {
            bukalapak.bukaReksaProductPage().checkProductFavouriteRemoved();
        });

        Then("^user should be able to see product listing based on risk filters", () -> {
            bukalapak.bukaReksaProductPage().selectProductList();
            bukalapak.bukaReksaProductPage().checkProductDetailDisplayed();
            bukalapak.bukaReksaProductPage().checkProductRiskValue();
        });

        Then("^user should be able to see product listing based on investment manager filters", () -> {
            bukalapak.bukaReksaProductPage().selectProductList();
            bukalapak.bukaReksaProductPage().checkProductDetailDisplayed();
            bukalapak.bukaReksaProductPage().tapOnProductGrowthDropdown();
            bukalapak.bukaReksaProductPage().verifyProductGrowthDropdownDismiss();
            bukalapak.bukaReksaProductPage().verifyProductInfoDropdownDismiss();
            bukalapak.bukaReksaProductPage().tapOnProductInfoDropdown();
            bukalapak.bukaReksaProductPage().verifyProductInfoComponent();
            bukalapak.bukaReksaProductPage().checkProductInvestmentManagerValue();
        });

        Then("user verify \"([^\"]*)\" tab is selected", (String quickFilter) -> {
            bukalapak.bukaReksaProductPage().verifyQuickFilter(quickFilter);
        });

        Then("user check whether product is exist in Favoritku page", () -> {
            bukalapak.bukaReksaProductPage().checkFavoriteProductExist();
        });

        And("user add favorite product if there is no product in Favoritku page", () -> {
            if(!InvestmentData.getFavoriteProductExist()) {
                bukalapak.bukaReksaProductPage().selectQuickFilter("semua produk");
                InvestmentData.setQuickFilter("semua produk");
                bukalapak.bukaReksaProductPage().isProductRiskDisplayed();
                bukalapak.bukaReksaProductPage().searchProduct(dotenv.get("REXA_PORTFOLIO_INSTANT_PRODUCT"));
                bukalapak.bukaReksaProductPage().checkFilteredProductByProductName();
                bukalapak.bukaReksaProductPage().selectProductList();
                bukalapak.bukaReksaProductPage().checkProductDetailDisplayed();
                bukalapak.bukaReksaProductPage().editFavorite("add favorite");
                bukalapak.iOSBasePage().backToHomePage();
                bukalapak.iOSBasePage().openDeepLink("/bukareksa");
                bukalapak.bukaReksaHomePage().isBerandaPageDisplayed();
                bukalapak.bukaReksaPage().selectNavigation("Produk");
                bukalapak.bukaReksaProductPage().selectQuickFilter("favoritku");
                InvestmentData.setQuickFilter("favoritku");
            }
        });

        When("^user tap on product sorting button", () -> {
            bukalapak.bukaReksaProductPage().tapOnProductSortingBtn();
        });

        When("^user should be able to see product sorting option displayed", () -> {
            bukalapak.bukaReksaProductPage().isProductSortingOptionDisplayed();
        });

        When("^user should be able to see risk option as default sort selection", () -> {
            bukalapak.bukaReksaProductPage().verifyRiskOptionAsDefaultSortSelection();
        });

        When("^user tap on product sorting close button", () -> {
            bukalapak.bukaReksaProductPage().tapOnProductSortingCloseBtn();
        });

        When("^user should be able to see the product sorting button selected", () -> {
            bukalapak.bukaReksaProductPage().checkProductSortingButtonSelected();
        });

        When("^user should be able to see the product listing sort by risk", () -> {
            bukalapak.bukaReksaProductPage().verifyProductListingSortByRisk();
        });

        When("^user select \"([^\"]*)\" as sort option", (String option) -> {
            bukalapak.bukaReksaProductPage().selectProductSortingList(option);
        });

        When("^user should be able to see the selected sort selection", () -> {
            bukalapak.bukaReksaProductPage().verifySelectedSortSelection();
        });

        Then("^user should be able to see the product listing sort by \"([^\"]*)\" NAB", (String method) -> {
            bukalapak.bukaReksaProductPage().verifyProductListingSortByNAB(method);
        });

        Then("^user should be able to see the product listing sort by \"([^\"]*)\" return", (String method) -> {
            bukalapak.bukaReksaProductPage().verifyProductListingSortByReturn(method);
        });
        //end region Product List

        // region Product Detail
        When("^user should be able to see product detail page displayed", () -> {
            bukalapak.bukaReksaProductPage().checkProductDetailDisplayed();
        });

        When("^user tap on Beli button in Product Detail page", () -> {
            bukalapak.bukaReksaProductPage().tapOnBuyProductBtn();
        });

        When("^user should be able to see product growth section displayed", () -> {
            bukalapak.bukaReksaProductPage().isProductGrowthSectionDisplayed();
        });

        When("^user select \"([^\"]*)\" as profit history product option", (String option) -> {
            bukalapak.bukaReksaProductPage().selectProfitHistoryProduct(option);
        });

        When("^user tap on Product Growth dropdown", () -> {
            bukalapak.bukaReksaProductPage().tapOnProductGrowthDropdown();
        });

        When("^user should be able to see product growth dropdown dismiss", () -> {
            bukalapak.bukaReksaProductPage().verifyProductGrowthDropdownDismiss();
        });

        When("^user should be able to see product information dropdown dismiss", () -> {
            bukalapak.bukaReksaProductPage().verifyProductInfoDropdownDismiss();
        });

        When("^user tap on Product Information dropdown", () -> {
            bukalapak.bukaReksaProductPage().tapOnProductInfoDropdown();
        });

        When("^user should be able to see product info components displayed", () -> {
            bukalapak.bukaReksaProductPage().verifyProductInfoComponent();
        });

        When("^user \"([^\"]*)\" product in `favoritku` listing", (String method) -> {
            bukalapak.bukaReksaProductPage().editFavorite(method);
        });
        // endregion Product Detail
    }
}
