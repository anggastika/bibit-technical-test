package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class CODStepDefinitions extends TestInstrument implements En {

    public CODStepDefinitions() {

        Given("user is in \"cod\" page", () -> {
            bukalapak.codPage().userOnCODPage();
        });

    }

}
