package bukalapak.stepDefinitions.vp.prepaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class HappyFreshStepDefinitions extends TestInstrument implements En {
    public HappyFreshStepDefinitions() {

        Then("^the HappyFresh landing page will( not)? have displayed$", (String flag) -> {
            bukalapak.happyFreshPage().validateOnPage(flag);
        });
    }
}
