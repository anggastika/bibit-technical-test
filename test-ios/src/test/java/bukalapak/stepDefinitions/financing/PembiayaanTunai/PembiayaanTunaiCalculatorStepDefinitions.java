package bukalapak.stepDefinitions.financing.PembiayaanTunai;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembiayaanTunaiCalculatorStepDefinitions extends TestInstrument implements En {
    public PembiayaanTunaiCalculatorStepDefinitions() {

        Then("^user is in Product Calculator page", () -> {
            bukalapak.pembiayaanTunaiCalculatorPage().validateInCalculatorPage();
            bukalapak.pembiayaanTunaiCalculatorPage().goToHomePage();
        });

        And("user tap on Ajukan Sekarang button", () -> {
            bukalapak.pembiayaanTunaiCalculatorPage().tapOnAjukanSekarangButton();
        });
    }
}
