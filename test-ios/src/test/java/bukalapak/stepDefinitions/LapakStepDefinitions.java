package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class LapakStepDefinitions extends TestInstrument implements En {

    public LapakStepDefinitions() {

        Given("user is in \"lapakmu\" page", () -> {
            bukalapak.lapakmuPage().userOnLapakmuPage();
        });
    }
}
