package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ManajemenPelangganStepDefinitions extends TestInstrument implements En {

    public ManajemenPelangganStepDefinitions() {
        And("user is in \"Manajemen Pelanggan\" page", () -> {
            bukalapak.manajemenPelangganPage().userOnManajemenPelangganDashboard();
        });

        Then("user can see sebarkan promosi button are enabled", () -> {
            bukalapak.manajemenPelangganPage().validateSebarPromosiEnabled();
        });

        When("^user taps on Sebarkan Promosi button on \"([^\"]*)\" category$", (String category) -> {
            bukalapak.manajemenPelangganPage().tapSebarPromosiButtonOnCategory(category);
        });
    }
}

