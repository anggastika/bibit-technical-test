package bukalapak.stepDefinitions.prio;

import bukalapak.TestInstrument;
import bukalapak.stepDefinitions.BaseStepDefinitions;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UlasanInstanBeliPakaiBukaDompetStepDefinitions extends TestInstrument implements En {

    public UlasanInstanBeliPakaiBukaDompetStepDefinitions() {
        And("^user is in Beli Pakai BukaDompet page$", () -> {
            bukalapak.ulasanInstanBeliPakaiBukaDompetPage().verifyBeliPakaiBukaDompetPage();
        });

        And("^user click Bayar Sekarang Button$", () -> {
            bukalapak.ulasanInstanBeliPakaiBukaDompetPage().clickBayarSekarangButton();
        });
    }
}
