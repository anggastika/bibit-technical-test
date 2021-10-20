package bukalapak.stepDefinitions.bee.BukaMobil;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaMobilOnboardingStepDefinitions extends TestInstrument implements En  {
    public BukaMobilOnboardingStepDefinitions() {
        And("user check onboarding BukaMobil page", () -> {
            bukalapak.bukaMobilOnboardingPage().skipOnboarding();
        });

        Then("^user select (covered|uncovered) location", (String flag) -> {
            bukalapak.bukaMobilOnboardingPage().selectCoveredLocation(flag);
        });
    }
}
