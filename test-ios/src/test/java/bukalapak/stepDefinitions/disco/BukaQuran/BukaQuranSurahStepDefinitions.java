package bukalapak.stepDefinitions.disco.BukaQuran;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaQuranSurahStepDefinitions extends TestInstrument implements En {

    public BukaQuranSurahStepDefinitions() {
        Then("^user must see (?:surah|juz) is open", () -> {
            bukalapak.bukaQuranSurahPage().validateSurahIsOpen();
        });
    }
}
