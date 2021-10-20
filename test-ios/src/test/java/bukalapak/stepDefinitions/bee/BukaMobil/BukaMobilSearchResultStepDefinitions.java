package bukalapak.stepDefinitions.bee.BukaMobil;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaMobilSearchResultStepDefinitions extends TestInstrument implements En {
    public BukaMobilSearchResultStepDefinitions() {
        Then("user will redirect to search result page", () -> {
            bukalapak.bukaMobilSearchResultPage().validateSearchResult();
        });

        When("user select car on classification page", () -> {
            bukalapak.bukaMobilSearchResultPage().selectCar();
        });

    }
}
