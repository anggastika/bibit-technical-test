package bukalapak.stepDefinitions.vp.insurance.bukatabungan;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaTabunganLandingPageStepDefinitions extends TestInstrument implements En {

    public BukaTabunganLandingPageStepDefinitions() {

        When("^the BukaTabungan (non )?registered user landing page will have displayed$", (String flag) -> {
            bukalapak.bukatabunganLandingPage().validateOnPage();
            bukalapak.bukatabunganLandingPage().validateRegisterSection(flag == null);
            bukalapak.bukatabunganLandingPage().validateProductSection();
            bukalapak.bukatabunganLandingPage().validateReferralSection(flag == null);
            bukalapak.bukatabunganLandingPage().validateFooterSection();
        });
    }
}
