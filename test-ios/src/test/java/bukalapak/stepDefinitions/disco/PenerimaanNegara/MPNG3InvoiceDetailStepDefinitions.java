package bukalapak.stepDefinitions.disco.PenerimaanNegara;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class MPNG3InvoiceDetailStepDefinitions extends TestInstrument implements En {

    public MPNG3InvoiceDetailStepDefinitions() {

            Then("^user validate MPNG3 invoice detail$", () -> {
                bukalapak.mpng3InvoiceDetailPage().validateBillCode();
                bukalapak.mpng3InvoiceDetailPage().validateNominalPayment();
            });
    }
}
