package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PemasukanStepDefinitions extends TestInstrument implements En {
    public PemasukanStepDefinitions() {
        Then("^user is in \"Pemasukan\" page$", () -> {
            bukalapak.pemasukanPage().verifyPage();
        });

        And("^user is shown income summary on Pemasukan \"([^\"]*)\" Remit page$", (String state) -> {
            bukalapak.pemasukanPage().verifyPemasukanPage(state);
        });
    }
}
