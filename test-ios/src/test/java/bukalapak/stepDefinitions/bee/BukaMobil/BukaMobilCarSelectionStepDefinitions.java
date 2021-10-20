package bukalapak.stepDefinitions.bee.BukaMobil;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaMobilCarSelectionStepDefinitions extends TestInstrument implements En {
    public BukaMobilCarSelectionStepDefinitions() {
        Then("user redirect to brand page", () -> {
            bukalapak.bukaMobilBrandPage().validateOnBrandPage();
        });
    }
}
