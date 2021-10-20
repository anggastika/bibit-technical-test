package bukalapak.stepDefinitions.financing.PembiayaanTunai;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembiayaanTunaiStepDefinition extends TestInstrument implements En {
    public PembiayaanTunaiStepDefinition() {

        When("^user tap \"([^\"]*)\" application", (String state) -> {
            bukalapak.pembiayaanTunaiPage().selectApplication(state);
        });

        When("^user is on detail activity state \"([^\"]*)\" application", (String state) -> {
            bukalapak.pembiayaanTunaiDetailPage().validateApplicationStates(state);
            bukalapak.pembiayaanTunaiDetailPage().goToHomePage();
        });
    }
}
