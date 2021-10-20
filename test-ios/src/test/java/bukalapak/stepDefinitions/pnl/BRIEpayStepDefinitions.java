package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BRIEpayStepDefinitions extends TestInstrument implements En {

    public BRIEpayStepDefinitions() {

        Given("user is in \"bri_epay\" page", () -> {
            bukalapak.briEpayPage().userOnBRIEpayPage();
        });
    }
}
