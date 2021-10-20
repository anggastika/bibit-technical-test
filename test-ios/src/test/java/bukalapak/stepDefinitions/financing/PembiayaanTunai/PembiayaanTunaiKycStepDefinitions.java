package bukalapak.stepDefinitions.financing.PembiayaanTunai;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembiayaanTunaiKycStepDefinitions extends TestInstrument implements En {
    public PembiayaanTunaiKycStepDefinitions() {

        Then("^user is in kyc step \"([^\"]*)\" page", (String step) -> {
                bukalapak.pembiayaanTunaiKycPage().validateInKycStep(step);
                bukalapak.pembiayaanTunaiKycPage().goToHomePage();
        });

        When("^user tap on Selanjutnya Button", () -> {
                bukalapak.pembiayaanTunaiKycPage().tapOnSelanjutnyaKycButton();
        });
    }
}