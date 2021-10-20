package bukalapak.stepDefinitions.financing.PembiayaanTunai;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembiayaanTunaiPartnerWebviewStepDefinitions extends TestInstrument implements En {
    public PembiayaanTunaiPartnerWebviewStepDefinitions() {

        When("^user is in partner webview page", () -> {
            bukalapak.pembiayaanTunaiPartnerWebviewPage().validateInPartnerWebviewPage();
            bukalapak.pembiayaanTunaiKycPreviewPage().goToHomePage();
        });

        And("user tap on back button in partner webview page", () -> {
            bukalapak.pembiayaanTunaiPartnerWebviewPage().tapOnBackButton();
        });


    }
}
