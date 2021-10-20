package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PerformaLapakStepDefinitions extends TestInstrument implements En {
    public PerformaLapakStepDefinitions() {
        Given("user is in \"Performa Lapak\" page", () -> {
            bukalapak.performaLapakPage().userOnPerformaLapakPage();
        });
    }
}
