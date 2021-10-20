package bukalapak.stepDefinitions.bee.BukaMobil;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaMobilClassificationStepDefinitions extends TestInstrument implements En {
    public BukaMobilClassificationStepDefinitions() {
        Then("user redirect to classification page$", () -> {
            bukalapak.bukaMobilClassificationPage().validateOnClassificationPage();
        });
    }
}
