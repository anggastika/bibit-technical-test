package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PremiAsuransiStepDefinitions extends TestInstrument implements En {

    public PremiAsuransiStepDefinitions() {

        And("user pays Premi Asuransi product with invalid data", () -> {
            bukalapak.premiAsuransiLandingPage().choosePolisProvider();
            bukalapak.premiAsuransiLandingPage().inputPolisNumber("invalid");
        });

        Then("the error message will have displayed on Premi Asuransi landing page", () -> {
            bukalapak.premiAsuransiLandingPage().validatePolisFormatIsInvalid();
        });
    }
}
