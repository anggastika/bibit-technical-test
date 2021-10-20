package bukalapak.stepDefinitions.financing.PembiayaanTunai;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembiayaanTunaiOnBoardingStepDefinitions extends TestInstrument implements En {
    public PembiayaanTunaiOnBoardingStepDefinitions() {

        Then("^user is in onboarding page", () -> {
            bukalapak.pembiayaanTunaiOnBoardingPage().validateInOnboardingPage();
            bukalapak.pembiayaanTunaiOnBoardingPage().goToHomePage();
        });

        And("user click tap Coba Pembiayaan Tunai button", () -> {
            bukalapak.pembiayaanTunaiOnBoardingPage().tapOnCobaPembiayaanButton();
        });
    }
}
