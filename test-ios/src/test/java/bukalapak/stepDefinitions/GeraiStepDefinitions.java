package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class GeraiStepDefinitions extends TestInstrument implements En {

    public GeraiStepDefinitions() {

        Given("user is in \"gerai\" page", () -> {
            bukalapak.geraiPage().userOnGeraiPage();
        });
    }
}
