package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushLoanDetailStepDefinitions extends TestInstrument implements En {

    public PromotedPushLoanDetailStepDefinitions() {
        Given("user is in \"Konfirmasi Pinjaman\" page", () -> {
            bukalapak.promotedPushLoanDetailPage().userOnLoanDetailPage();
        });

        When("^user check Loan detail$", () -> {
            bukalapak.promotedPushLoanDetailPage().checkLoanDetail();
        });
    }
}
