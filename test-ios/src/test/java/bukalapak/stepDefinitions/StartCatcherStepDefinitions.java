package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class StartCatcherStepDefinitions extends TestInstrument implements En {

    public StartCatcherStepDefinitions() {

        Given("user is in \"star_catcher\" page", () -> {
            bukalapak.starCatcherPage().userOnStarCatcherPage();
        });
    }
}
