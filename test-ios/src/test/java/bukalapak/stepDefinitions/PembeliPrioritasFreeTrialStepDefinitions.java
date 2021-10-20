package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembeliPrioritasFreeTrialStepDefinitions extends TestInstrument implements En {

    public PembeliPrioritasFreeTrialStepDefinitions() {

        Given("user is in \"Pembeli Prioritas Free Trial\" page", () -> {
            bukalapak.pembeliPrioritasFreeTrialPage().userOnPriorityBuyerFreeTrialPage();
        });
    }
}
