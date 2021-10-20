package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class CicilanTanpaKartuKreditStepDefinitions extends TestInstrument implements En {

    public CicilanTanpaKartuKreditStepDefinitions() {

        Given("user is in \"cicilan_tanpa_kartu_kredit\" page", () -> {
            bukalapak.cicilanTanpaKartuKreditPage().userOnCicilanTanpaKartuKreditPage();
        });
    }
}
