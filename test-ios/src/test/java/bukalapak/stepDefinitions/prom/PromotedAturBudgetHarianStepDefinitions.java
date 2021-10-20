package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedAturBudgetHarianStepDefinitions extends TestInstrument implements En {

    public PromotedAturBudgetHarianStepDefinitions() {
        Given("^user is in \"Atur Budget Harian\" page$", () -> {
            bukalapak.promotedAturBudgetHarianPage().userOnAturBudgetHarianPage();
        });

        When("^user turn (on|off) Budget Harian toggle button$", (String state) -> {
            bukalapak.promotedAturBudgetHarianPage().setDailyBudgetToggle(state);
        });

        Then("^user input Promoted (Push|Keyword) budget as (.*)$", (String type, String bid) -> {
            bukalapak.promotedAturBudgetHarianPage().inputBidValue(bid);
        });

        Then("^user (disable|enable)? Promoted (Push|Keyword) Pinjaman Saldo checkbox$", (String state, String type) -> {
            bukalapak.promotedAturBudgetHarianPage().setLoanCheckbox(state);
        });
    }
}
