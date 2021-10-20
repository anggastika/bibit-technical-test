package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PilihNomorKartuStepDefinitions extends TestInstrument implements En {

    public PilihNomorKartuStepDefinitions() {

        Given("user is in \"Pilih Nomor Kartu Kredit\" page", () -> {
            bukalapak.pilihNomorKartuPage().userOnPilihNomorKartuPage();
        });

        When("user select credit card with 4 first digits as (.*)", (String creditCardNumber) -> {
            bukalapak.pilihNomorKartuPage().selectCreditCard(creditCardNumber);
        });
    }
}
