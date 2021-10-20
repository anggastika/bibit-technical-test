package bukalapak.stepDefinitions.px;

import bukalapak.data.PXData;
import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ProductNotForSellStepDefinitions extends TestInstrument implements En {

    public ProductNotForSellStepDefinitions() {

        Given("user is in product not for sell page with deeplink \"([^\"]*)\"", (String deeplink) -> {
            bukalapak.productNotForSellPage().userOnPageWithDeeplink(deeplink);
        });

        When("user set product for sell", () -> {
            bukalapak.productNotForSellPage().searchProduct(PXData.getProductName());
            bukalapak.productNotForSellPage().openProductMenu();
            bukalapak.productNotForSellPage().setProductForSell();
        });

        Then("product should be in not for sell page", () -> {
            bukalapak.productNotForSellPage().searchProduct(PXData.getProductName());
            bukalapak.productNotForSellPage().verifyStock(1);
        });

        When("user sets bulk product for sell", () -> {
            bukalapak.productNotForSellPage().tapOnBulkProductCheckboxes();
            bukalapak.productNotForSellPage().setProductForSell(); 
        });

        Then("products should be in not for sell page", () -> {
            bukalapak.productNotForSellPage().verifyStock(1);
        });
    }
}
