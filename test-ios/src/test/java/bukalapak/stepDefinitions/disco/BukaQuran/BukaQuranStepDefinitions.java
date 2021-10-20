package bukalapak.stepDefinitions.disco.BukaQuran;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaQuranStepDefinitions extends TestInstrument implements En {

    public BukaQuranStepDefinitions() {
        When("user click \"([^\"]*)\" (?:surah|juz|bookmark)", (String surah) -> {
            bukalapak.bukaQuranPage().tapOnSurah(surah);
        });

        Then("user must not see surah in bookmark tab", () -> {
            bukalapak.bukaQuranPage().validateSurahIsNotBookmarked();
        });
      
        Then("user must see the last reading surah", () -> {
            bukalapak.bukaQuranPage().validateLastSeenSurah();
        });
    }
}
