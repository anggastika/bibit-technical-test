package bukalapak.stepDefinitions.disco.PenerimaanNegara;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class MPNG3InquiryStepDefinitions extends TestInstrument implements En {

    public MPNG3InquiryStepDefinitions() {

        Then("^user validate MPNG3 inquiry page$", () -> {
            bukalapak.mpng3InquiryPage().validateBillCode();
            bukalapak.mpng3InquiryPage().setNominalTransaction();
            bukalapak.mpng3InquiryPage().validateTotalPayment();
            bukalapak.mpng3InquiryPage().tapConfirmInquiryMPNG3();
        });
    }
}
