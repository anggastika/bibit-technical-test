package bukalapak.stepDefinitions.vp.prepaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class GramediaDigitalStepDefinitions extends TestInstrument implements En {
    public GramediaDigitalStepDefinitions() {

        Then("^the Gramedia Digital landing page will( not)? have displayed$", (String flag) -> {
            bukalapak.gramediaDigitalPage().validateOnPage(flag);
        });
    }
}
