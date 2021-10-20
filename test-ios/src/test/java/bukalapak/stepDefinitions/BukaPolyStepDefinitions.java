package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import io.cucumber.java8.En;

public class BukaPolyStepDefinitions extends TestInstrument implements En {
    public BukaPolyStepDefinitions() {

        And("user is on BukaPoly landing page", () -> {
            bukalapak.bukaPolyWebPage().userIsOnBukaPolyPage();
        });

        When("user tap kepingan gratis on bukapoly", () -> {
            bukalapak.bukaPolyWebPage().checkKepinganGratisBadge();
            bukalapak.bukaPolyWebPage().tapOnKepinganGratisButton();
        });
    }
}
