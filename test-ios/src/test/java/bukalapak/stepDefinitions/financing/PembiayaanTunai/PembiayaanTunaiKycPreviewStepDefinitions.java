package bukalapak.stepDefinitions.financing.PembiayaanTunai;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembiayaanTunaiKycPreviewStepDefinitions extends TestInstrument implements En {
    public PembiayaanTunaiKycPreviewStepDefinitions() {

        Then("^user is kyc preview page", () -> {
            bukalapak.pembiayaanTunaiKycPreviewPage().validateInKycPreviewPage();
            bukalapak.pembiayaanTunaiKycPreviewPage().goToHomePage();
        });

        And("user tap on Selanjutnya button in summary page", () -> {
            bukalapak.pembiayaanTunaiKycPreviewPage().tapOnSelanjutnyaButton();
        });

        When("^user is shown pop up confirmation ya kirim", () -> {
            bukalapak.pembiayaanTunaiKycPage().validateIsShownPopUpConfirmation();
            bukalapak.pembiayaanTunaiKycPreviewPage().goToHomePage();
        });

    }
}
