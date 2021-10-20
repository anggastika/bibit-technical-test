package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

@SuppressWarnings("PMD.UnnecessaryConstructor")
public class PromotedLoanDetailStepDefinitions extends TestInstrument implements En {

    public PromotedLoanDetailStepDefinitions() {
        //commented due to duplicate step with PromotedPushLoanDetailStepDefinitions
        /*When("^user check Loan detail$", () -> {
            bukalapak.promotedLoanDetailPage().checkLoanDetail();
        });*/
    }
}
