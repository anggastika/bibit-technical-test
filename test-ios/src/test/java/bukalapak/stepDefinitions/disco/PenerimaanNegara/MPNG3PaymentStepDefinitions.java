package bukalapak.stepDefinitions.disco.PenerimaanNegara;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class MPNG3PaymentStepDefinitions extends TestInstrument implements En {

    public MPNG3PaymentStepDefinitions() {

        Then("^user validate MPNG3 checkout page using Transfer method$", () -> {
            bukalapak.mpng3PaymentPage().validateBillCode();
            bukalapak.mpng3PaymentPage().selectTransferMethod();
            bukalapak.mpng3PaymentPage().validateNominalPayment();
        });
    }
}
