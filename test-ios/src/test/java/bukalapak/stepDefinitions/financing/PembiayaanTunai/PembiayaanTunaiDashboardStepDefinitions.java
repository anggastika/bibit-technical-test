package bukalapak.stepDefinitions.financing.PembiayaanTunai;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembiayaanTunaiDashboardStepDefinitions extends TestInstrument implements En {
    public PembiayaanTunaiDashboardStepDefinitions() {

        Then("^user is in dashboard page", () -> {
            bukalapak.pembiayaanTunaiDashboardPage().validateInDashboardPage();
            bukalapak.pembiayaanTunaiDashboardPage().goToHomePage();
        });

        And("user tap on Ajukan Pembiayaan button", () -> {
            bukalapak.pembiayaanTunaiDashboardPage().tapOnAjukanPembiayaanButton();
        });
    }
}
