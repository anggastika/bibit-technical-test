package bukalapak.stepDefinitions.csi;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class RiwayatKomplainStepDefinitions extends TestInstrument implements En {
    public RiwayatKomplainStepDefinitions() {
        Then("^user is in \"Riwayat Komplain\" page$", () -> {
            bukalapak.riwayatKomplainPage().userOnRiwayatKomplainPage();
        });

        When("^user tap BukaBantuan button in Riwayat Komplain page$", () -> {
            bukalapak.riwayatKomplainPage().tapOnBukaBantuanButton();
        });

        When("^user tap komplain item in Riwayat Komplain page$", () -> {
            bukalapak.riwayatKomplainPage().tapOnFirstKomplain();
        });

        And("^user should see (\\d+) komplain in Riwayat Komplain page$", (Integer total) -> {
            bukalapak.riwayatKomplainPage().validateTotalKomplain(total);
        });
    }
}
