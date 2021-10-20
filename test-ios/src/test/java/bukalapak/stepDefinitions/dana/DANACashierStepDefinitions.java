package bukalapak.stepDefinitions.dana;

import bukalapak.TestInstrument;
import bukalapak.data.DANAData;
import cucumber.api.java8.En;

/**
 * Created by Ihsan Hasanudin on 04/02/21.
 */
public class DANACashierStepDefinitions extends TestInstrument implements En  {

    public DANACashierStepDefinitions() {

        Then("user redirect to DANA cashier page", () -> {
            bukalapak.danaCashierPage().userOnDANACashierPage();
        });

        Then("user redirect to DANA cashier page with credits", () -> {
            bukalapak.danaCashierPage().userOnDANACashierPageWithCredits();
        });

    }

}
