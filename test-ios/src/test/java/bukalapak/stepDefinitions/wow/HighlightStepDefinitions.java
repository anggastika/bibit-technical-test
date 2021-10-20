package bukalapak.stepDefinitions.wow;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class HighlightStepDefinitions extends TestInstrument implements En {
    public HighlightStepDefinitions() {
        Given("user is in \"highlight\" page", () -> {
            bukalapak.highlightPage().isOnHighlightPage();
        });

        Then("user should see merchant page or brand page", () -> {
            bukalapak.highlightPage().verifyMerchantOrBrandPage();
        });

        Then("user should see selected product contain 10 items", () -> {
            bukalapak.highlightPage().verifySelectedProducts();
        });

        Then("user should see end of list highlight page", () -> {
            bukalapak.highlightPage().verifyHighlightEndOfList();
        });

        When("user tap on specific lihat semua on end of seller product list", () -> {
            bukalapak.highlightPage().tapLihatSemuaOnSpecificSeller();
        });

        When("user tap on specific seller product", () -> {
            bukalapak.highlightPage().tapOnSpecificSellerProduct();
        });
    }
}
