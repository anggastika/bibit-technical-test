package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by NurdianSetyawan on 17/12/18.
 */
public class PilihCicilanKredivoStepDefinitions extends TestInstrument implements En {
    public PilihCicilanKredivoStepDefinitions() {
        Given("user is in \"pilih_cicilan_kredivo\" page", () -> {
            bukalapak.pilihCicilanKredivoPage().userOnPilihCicilanKredivoPage();
        });

        When("user verify total payment in Pilih Cicilan Page is match with the one memorized before", () -> {
            bukalapak.pilihCicilanKredivoPage().verifyTotalPaymentIsMatch();
        });

        And("user choose \"([^\"]*)\" as payment type", (String arg0) -> {
            bukalapak.pilihCicilanKredivoPage().choosePaymentMethod(arg0);
        });

        When("user click yes on kredivo prompted dialog", () -> {
            bukalapak.pilihCicilanKredivoPage().clickOnYaPunyaDialogButton();
        });
    }
}
