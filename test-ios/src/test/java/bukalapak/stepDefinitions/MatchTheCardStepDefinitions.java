package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class MatchTheCardStepDefinitions extends TestInstrument implements En {

    public MatchTheCardStepDefinitions() {

        Given("user is in \"match_the_card\" page", () -> {
            bukalapak.matchTheCardPage().userOnMatchTheCardPage();
        });
    }
}
