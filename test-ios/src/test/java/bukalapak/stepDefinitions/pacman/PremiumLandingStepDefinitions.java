package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PremiumLandingStepDefinitions extends TestInstrument implements En {

    public PremiumLandingStepDefinitions() {

        When("user swipe to \"([^\"]*)\" package", (String packageName) -> {
            bukalapak.premiumLandingPage().swipeToPackage(packageName);
        });

        Given("user is in \"Premium Landing Alchemy\" page", () -> {
            bukalapak.premiumLandingAlchemyPage().userOnPremiumLandingPage();
        });

        Given("user is in \"Premium Landing\" page", () -> {
            bukalapak.premiumLandingPage().userOnPremiumLandingPage();
        });

        Given("user is in \"Daftar Pembelian Premium\" page", () -> {
            bukalapak.daftarPembelianPremiumPage().userOnDaftarPembelianPremiumPage();
        });
    }
}
