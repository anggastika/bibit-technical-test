package bukalapak.stepDefinitions.bee.BukaMobil;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaMobilBrandStepDefinitions extends TestInstrument implements En {
    public BukaMobilBrandStepDefinitions() {
        Then("user redirect to mobil pilihan page", () -> {
            bukalapak.bukaMobilCarSelectionPage().validateOnCarSelectionPage();
        });
    }
}
