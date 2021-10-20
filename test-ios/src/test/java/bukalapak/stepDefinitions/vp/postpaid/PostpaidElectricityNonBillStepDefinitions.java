package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PostpaidElectricityNonBillStepDefinitions extends TestInstrument implements En {

    public PostpaidElectricityNonBillStepDefinitions() {

        When("^user pays Non Taglis with invalid data$", () -> {
            bukalapak.postpaidElectricityNonBillPage().typeCustomerNumber("invalid");
        });

        Then("^error message will displayed in Non Taglis landing page$", () -> {
            bukalapak.postpaidElectricityNonBillPage().validateErrorMessage();
        });

        Then("^the Non Taglis transaction histories will have displayed$", () -> {
            bukalapak.postpaidElectricityNonBillPage().verifyTransactionHistoyPage();
        });
    }

}
