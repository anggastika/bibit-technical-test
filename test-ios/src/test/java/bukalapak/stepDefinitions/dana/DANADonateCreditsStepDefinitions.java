package bukalapak.stepDefinitions.dana;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class DANADonateCreditsStepDefinitions extends TestInstrument implements En {

    public DANADonateCreditsStepDefinitions() {
        //Auto Donate
        When("user verify auto donate page", () -> {
            bukalapak.danaDonateCreditsPage().verifyAutoDonatePage();
        });

        When("user verify auto donate banner", () -> {
            bukalapak.danaDonateCreditsPage().verifyAutoDonateBanner();
        });

        When("^user \"([^\"]*)\" set auto donate to (accepted|rejected) via API$", (String credentialUser, String state) -> {
            bukalapak.apiCall().setAutoDonate(credentialUser, state);
        });

        When("user verify auto donate unregister popup", () -> {
            bukalapak.danaDonateCreditsPage().verifyAutoDonateUnregPopup();
        });

        When("user verify auto donate cancel popup", () -> {
            bukalapak.danaDonateCreditsPage().verifyAutoDonateCancelPopup();
        });

        //TODO this step is temporary and will be deleted if bug has been fixed by iOS dev
        When("user relaunch app for auto donate case", () -> {
            bukalapak.danaDonateCreditsPage().relaunchApp();
        });

        //Donate Credits (BukaDonasi)
        When("^user access DANA credits donation with amount Rp([^\"]*) via deeplink$", (String amount) -> {
            bukalapak.danaDonateCreditsPage().accessDonateCreditsLink(amount);
        });

        Then("user verify DANA credits donation page", () -> {
            bukalapak.danaDonateCreditsPage().verifyDonateCreditsPage();
        });

        And("^user (succeeds|fails) in transaction donate credits$", (String state) -> {
            bukalapak.danaDonateCreditsPage().verifyDonateCreditsTrx(state);
        });
    }
}
