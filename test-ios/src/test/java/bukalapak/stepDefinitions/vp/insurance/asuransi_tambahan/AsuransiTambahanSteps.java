package bukalapak.stepDefinitions.vp.insurance.asuransi_tambahan;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AsuransiTambahanSteps extends TestInstrument implements En {

    public AsuransiTambahanSteps() {

        When("user chooses a Asuransi Tambahan transaction", () -> {
            bukalapak.asuransiTambahanPage().tapOnCheckbox();
        });

        And("user buys a Asuransi Tambahan",() -> {
            bukalapak.asuransiTambahanPage().tapOnLindungiSekarangButton();
            bukalapak.asuransiTambahanPage().tapOnSnkButton();
        });

        When("user taps on the Asuransi Tambahan banner",() -> {
            bukalapak.asuransiTambahanPage().tapOnBanner();
        });

        Then("^the Asuransi Tambahan manfaat asuransi \"([^\"]*)\" section will have displayed$",(String verification) -> {
            bukalapak.asuransiTambahanPage().validateManfaatSection(verification.equals("barang"));
        });

        When("user taps on the Asuransi Tambahan cara klaim button",() -> {
            bukalapak.asuransiTambahanPage().tapOnCaraKlaimButton();
        });

        Then("^the Asuransi Tambahan cara klaim \"([^\"]*)\" section will have displayed$",(String verification) -> {
            bukalapak.asuransiTambahanPage().validateCaraKlaimSection(verification.equals("barang"));
        });

        When("user taps on the Asuransi Tambahan syarat dan ketentuan button",() -> {
            bukalapak.asuransiTambahanPage().tapOnTncButton();
        });

        Then("^the Asuransi Tambahan syarat dan ketentuan \"([^\"]*)\" section will have displayed$",(String verification) -> {
            bukalapak.asuransiTambahanPage().validateTncSection(verification.equals("barang"));
        });
    }
}
