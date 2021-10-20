package bukalapak.stepDefinitions.prio;

import bukalapak.TestInstrument;
import bukalapak.stepDefinitions.BaseStepDefinitions;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UlasanInstanOnboardingStepDefinitions extends TestInstrument implements En {

    public UlasanInstanOnboardingStepDefinitions() {
        Given("user is in \"Ulasan Instan Blog\" page", () -> {
            bukalapak.ulasanInstanOnboardingPage().userOnUlasanInstanBlog();
        });

        Then("^(non-)?subscribed user is in Ulasan Instan Onboarding page$", (String status) -> {
            bukalapak.ulasanInstanOnboardingPage().userOnOnboardingPage(status != null);
        });

        When("^user click Ulasan Instan promo banner element$", () -> {
            bukalapak.ulasanInstanOnboardingPage().clickBanner();
        });

        And("^user verify Sedang Berlangganan text in package (.*)$", (String automaticPackage) -> {
            bukalapak.ulasanInstanOnboardingPage().checkSedangBerlanggananText(automaticPackage);
        });
    }
}
