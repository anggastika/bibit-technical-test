package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SuperSellerLandingStepDefinitions extends TestInstrument implements En {
    public SuperSellerLandingStepDefinitions() {
        Given("user is in \"Super Seller Landing\" page", () -> {
            bukalapak.superSellerLandingPage().userOnSuperSellerLanding();
        });

        When("^user verify Aktifkan Sekarang button display$", () -> {
            bukalapak.superSellerLandingPage().verifyAktifkanSekarangButton();
        });
    }
}
