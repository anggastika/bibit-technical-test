package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushEndFreeTrialStepDefinitions extends TestInstrument implements En {

    public PromotedPushEndFreeTrialStepDefinitions() {
        Then("user is on Promoted Push End Free Trial page", () -> {
            bukalapak.promotedPushEndFreeTrialPage().validateEndFreeTrialPage();
        });

        When("user tap on Atur Budget Harian button", () -> {
            bukalapak.promotedPushEndFreeTrialPage().tapAturBudgetButton();
        });

        When("user tap on Cek Promosi button", () -> {
            bukalapak.promotedPushEndFreeTrialPage().tapCekPromosiButton();
        });
    }
}
