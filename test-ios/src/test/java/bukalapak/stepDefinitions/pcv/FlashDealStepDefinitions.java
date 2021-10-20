package bukalapak.stepDefinitions.pcv;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class FlashDealStepDefinitions extends TestInstrument implements En {

    public FlashDealStepDefinitions() {
        And("^user should see product list flash deal$", () -> {
            bukalapak.flashDealProductListPage().userOnProductListFlashDeal();
        });

        Given("tap product at order (\\d+) in current flash deal", (Integer position) -> {
            bukalapak.flashDealProductListPage().tapOnCurrentFlashDealTab();
            bukalapak.flashDealProductListPage().tapOnFlashDealProduct(position - 1);
        });

        Then("user click flash deal product with rating more than four", () -> {
            bukalapak.flashDealProductListPage().userOnProductListFlashDeal();
            bukalapak.flashDealProductListPage().tapProductRatingFourPlus();
        });

        Given("user go to flash deal page using deeplink", () -> {
            bukalapak.flashDealProductListPage().goToFlashDealWithDeeplink();
            bukalapak.flashDealProductListPage().userOnProductListFlashDeal();
        });

        Then("user verify banner flash deal is displayed", () -> {
            bukalapak.flashDealProductListPage().validateBannerFlashDeal();
        });

        Then("user verify tab \"([^\"]*)\" is active", (String tabName) -> {
            bukalapak.flashDealProductListPage().validateTabActive(tabName);
        });

        Then("user verify timer flash deal exist", () -> {
            bukalapak.flashDealProductListPage().validateFlashDealTimer();
        });

        Then("user verify there is only (\\d+) flash deal tab available", (Integer tabNumber) -> {
            bukalapak.flashDealProductListPage().validateNumberOfTabs(tabNumber);
        });

        When("user tap Share flash deal icon", () -> {
            bukalapak.flashDealProductListPage().tapOnShareFlashDealButton();
        });

        Then("user is on share option modal", () -> {
            bukalapak.flashDealProductListPage().validateShareFlashDealModal();
        });

        Then("user tap back button from flash deal page", () ->{
            bukalapak.flashDealProductListPage().tapFDBackButton();
        });

        When("^user tap (ongoing|upcoming) flash deal tab$", (String flashState) -> {
            bukalapak.flashDealProductListPage().selectFlashDealTab(flashState);
            bukalapak.flashDealProductListPage().userOnProductListFlashDeal();
        });

        Then("^user verify flash deal (ongoing|upcoming) timer is exist$", (String timerState) -> {
            bukalapak.flashDealProductListPage().validateTimer(timerState);
        });

        Then("^(masked|unmasked) product price is displayed$", (String priceState) -> {
            bukalapak.flashDealProductListPage().validatePrice(priceState);
        });

        Then("user click back from Flash Deal List", () -> {
            bukalapak.productDetailFlashDealPage().tapBackButton();
        });

        Then("user tap a product flash deal on current tab", () -> {
            bukalapak.flashDealProductListPage().tapOnFlashDealProduct(1);
        });

        When("^user sort flash deal with option \"([^\"]*)\"$", (String sortOption) -> {
            bukalapak.flashDealProductListPage().sortFlashDealProduct(sortOption);
        });

        Then("^user verify flash deal product list are sorted by \"([^\"]*)\"$", (String sortOption) -> {
            bukalapak.flashDealProductListPage().validateFlashDealProductSorted(sortOption);
        });

        When("user tap on upcoming flash deal tab", () -> {
            bukalapak.flashDealProductListPage().tapOnUpcomingFlashDealTab();
        });

        Then("user verify the sort button flash deal is not exist", () -> {
            bukalapak.flashDealProductListPage().validateSortButtonNotExist();
        });

        When("user tap a ready stock flash deal product", () -> {
            bukalapak.flashDealProductListPage().tapOnFlashDealProduct("available");
        });

        When("user tap an out of stock flash deal product", () -> {
            bukalapak.flashDealProductListPage().tapOnFlashDealProduct("unavailable");
        });

        When("user swipe to any out of stock product flash deal", () -> {
            bukalapak.flashDealProductListPage().swipeToOutOfStockProduct();
        });

        Then("user verify the product mask with Stok habis", () -> {
            bukalapak.flashDealProductListPage().validateOutOfStockLabel();
        });

        Then("upcoming price is displayed properly", () -> {
            bukalapak.flashDealProductListPage().validateUpcomingPrice();
        });

        And("user should be redirect to flash deal page", () -> {
            bukalapak.flashDealProductListPage().userOnProductListFlashDeal();
        });

        And("user validate Buat Kamu filter does not exist", () -> {
            bukalapak.flashDealProductListPage().userOnProductListFlashDeal();
            bukalapak.flashDealProductListPage().validateRecomendationFilterNotExist();
        });

        And("user validate Buat Kamu filter exist", () -> {
            bukalapak.flashDealProductListPage().userOnProductListFlashDeal();
            bukalapak.flashDealProductListPage().validateRecomendationFilterExist();
        });

        And("user tap Buat Kamu filter", () -> {
            bukalapak.flashDealProductListPage().tapRecomendationFilter();
        });
    }
}
