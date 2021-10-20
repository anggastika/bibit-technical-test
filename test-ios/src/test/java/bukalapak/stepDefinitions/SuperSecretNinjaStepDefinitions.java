package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SuperSecretNinjaStepDefinitions extends TestInstrument implements En {

    public SuperSecretNinjaStepDefinitions() {
        Given("user is in \"super_secret_ninja\" page", () -> {
            bukalapak.superSecretNinjaPage().userOnSuperSecretNinjaPage();
        });

        When("^user open deeplink \"([^\"]*)\"", (String deeplinkURL) -> {
            bukalapak.superSecretNinjaPage().openDeepLink(deeplinkURL);
        });
    }
}
