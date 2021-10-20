package bukalapak.stepDefinitions.prom;


import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushInstantStepDefinition extends TestInstrument implements En {

    public PromotedPushInstantStepDefinition() {
        Then("^user is on Promoted push instant page", () -> {
            bukalapak.promotedPushInstantPage().userIsOnPromotedInstant();
        });

        Then("^user verify tnc promoted push instant", () -> {
            bukalapak.promotedPushInstantPage().verifyTNCPromotedInstant();
        });

        Then("^user is on Promoted Push instant mweb page", () -> {
            bukalapak.promotedPushInstantPage().userIsOnPromotedInstantMweb();
        });

        Then("^user verify tnc promoted push instant mweb", () -> {
            bukalapak.promotedPushInstantPage().tncPromotedInstantMweb();
        });

        And("^user search product as \"([^\"]*)\" on Promoted Push instant page$", (String productName) -> {
            bukalapak.promotedPushInstantPage().searchProduct(productName);
        });

        And("^user click checkbox \"([^\"]*)\" on Promoted Push Instant page$", (String productSearch) -> {
            bukalapak.promotedPushInstantPage().clickProduct(productSearch);
        });

        When("^user tap on promosikan instant sekarang button with budget promosi is \"([^\"]*)\"$", (String budgetPromoted) -> {
            bukalapak.promotedPushInstantPage().clickPromosikanSekarang(budgetPromoted);
        });

        And("^user tap on bayar promoted instant button$", () -> {
            bukalapak.promotedPushInstantPage().tapBayarPromotedInstant();
        });
    }
}
