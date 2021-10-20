package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PelangganPelapakStepDefinitions extends TestInstrument implements En {

    public PelangganPelapakStepDefinitions() {

        Given("user is in \"pelanggan pelapak\" page", () -> {
            bukalapak.pelangganPelapakPage().userOnPelangganPelapakPage();
        });
    }
}
