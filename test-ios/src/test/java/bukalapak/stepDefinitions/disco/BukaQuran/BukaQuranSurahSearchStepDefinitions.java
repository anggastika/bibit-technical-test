package bukalapak.stepDefinitions.disco.BukaQuran;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaQuranSurahSearchStepDefinitions extends TestInstrument implements En {

    public BukaQuranSurahSearchStepDefinitions() {
        Then("user search An-Nisa surah", () -> {
            bukalapak.bukaQuranSurahSearchPage().inputSurahOnSearchField();
        });
    }
}
