package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SuperSellerOptOutSurveyStepDefinitions extends TestInstrument implements En {
    public SuperSellerOptOutSurveyStepDefinitions() {
        And("user is in \"Opt Out Survey\" page", () -> {
            bukalapak.superSellerOptOutSurveyPage().verifyOptOutSurvey();
        });
    }
}
