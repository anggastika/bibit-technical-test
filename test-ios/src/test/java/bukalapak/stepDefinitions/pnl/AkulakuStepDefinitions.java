package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by NurdianSetyawan on 10/12/18.
 */
public class AkulakuStepDefinitions extends TestInstrument implements En {
    public AkulakuStepDefinitions() {

        Given("user is in \"akulaku\" page", () -> {
            bukalapak.akulakuPage().userOnAkulakuPage();
        });

        When("user verify total payment is match with the one memorized before", () -> {
            bukalapak.akulakuPage().verifyTotalPaymentIsMatch();
        });

        When ("user allow location services alert", () -> {
            bukalapak.akulakuPage().allowLocationServicesAlert();
        });

        When ("user authorize location services alert", () -> {
            bukalapak.akulakuPage().authorizeLocationServicesAlert();
        });
    }

}