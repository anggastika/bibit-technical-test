package bukalapak.stepDefinitions.px;

import bukalapak.data.PXData;
import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ProductForSellStepDefinitions extends TestInstrument implements En {

    public ProductForSellStepDefinitions() {

        Given("user is in product for sell page with deeplink \"([^\"]*)\"", (String deeplink) -> {
            bukalapak.productForSellPage().userOnPageWithDeeplink(deeplink);
        });

        When("user set product not for sell", () -> {
            bukalapak.productForSellPage().searchProduct(PXData.getProductName());
            bukalapak.productForSellPage().openProductMenu();
            bukalapak.productForSellPage().setProductNotForSell();
        });

        Then("product should be in for sell page", () -> {
            bukalapak.productForSellPage().searchProduct(PXData.getProductName());
            bukalapak.productForSellPage().verifyStock(1);
        });

        And("validate discount applied with badge \"([^\"]*)\"", (String discountbadge) -> {
            bukalapak.productForSellPage().validateDiscount(discountbadge);
        });

        When("user sets bulk product not for sell", () -> {
            bukalapak.productNotForSellPage().tapOnBulkProductCheckboxes();
            bukalapak.productForSellPage().tapOnSellProductLainnyaButton();
            bukalapak.productForSellPage().setProductNotForSell();
        });

        When("user deletes bulk product", () -> {
            bukalapak.productNotForSellPage().tapOnBulkProductCheckboxes();
            bukalapak.productForSellPage().tapOnSellProductLainnyaButton();
            bukalapak.productForSellPage().tapOnDeleteProductButton();
        });

        Then("products should be in for sell page", () -> {
            bukalapak.productForSellPage().verifyStock(1);
        });
    }
}
