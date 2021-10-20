package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BRICeriaStepDefinitions extends TestInstrument implements En {

    public BRICeriaStepDefinitions() {
        Given("user is in \"bri_ceria\" page", () -> {
            bukalapak.briCeriaPage().userOnBRICeriaPage();
        });

        Then("user should see alert unbinded BRI ceria on cicilan tanpa kartu kredit page", () -> {
            bukalapak.briCeriaPage().verifyUnbindedAlertShowing();
        });
    }
}