package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushTambahBudgetPromosiStepDefinitions extends TestInstrument implements En {

    public PromotedPushTambahBudgetPromosiStepDefinitions() {
        Given("user is in \"Promoted Push - Tambah Budget Promosi\" page", () -> {
            bukalapak.promotedTambahBudgetPromosiPage().userOnTambahBudgetPromosiPage();
        });

        When("^user input (.*) as Promoted Push budget$", (String budget) -> {
            bukalapak.promotedTambahBudgetPromosiPage().inputBudget(budget);
        });
    }
}
