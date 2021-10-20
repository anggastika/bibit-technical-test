package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by Ihsan Hasanudin on 7/11/2020.
 */

public class CreditsListStepDefinitions extends TestInstrument implements En  {

    public CreditsListStepDefinitions() {
        Then("user redirect to Credits List mutation page", () -> {
            bukalapak.creditsListPage().onCreditsListPage();
        });
    }
}
