package bukalapak.stepDefinitions.dana;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;


/**
 * Created by Ihsan Hasanudin on 19/11/2020.
 */

public class DANAAccountStepDefinitions extends TestInstrument implements En {

    public DANAAccountStepDefinitions() {

        Then("user redirect to DANA account info page", () -> {
            bukalapak.danaAccountPage().onDANAAccountPage();
        });
    }
}
