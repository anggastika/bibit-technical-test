package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushSuccessPaymentStepDefinitions extends TestInstrument implements En {

    public PromotedPushSuccessPaymentStepDefinitions() {

        Given("user is in \"Promoted Push - Add Budget Success Invoice\" page", () -> {
            bukalapak.promotedPushSuccessPaymentPage().userOnSuccessPaymentPage();
        });
    }
}
