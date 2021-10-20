package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by NurdianSetyawan on 17/12/18.
 */
public class KredivoStepDefinitions extends TestInstrument implements En {
    public KredivoStepDefinitions() {
        Given("user is in \"kredivo\" page", () -> {
            bukalapak.kredivoPage().userOnKredivoPage();
        });

        When("user verify total payment without kredivo fee in Kredivo Page is match with the one memorized before", () -> {
            bukalapak.kredivoPage().verifyTotalPaymentKredivoIsMatch();
        });
    }
}
