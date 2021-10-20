package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class LapakTerlarisStepDefinitions extends TestInstrument implements En {

    public LapakTerlarisStepDefinitions() {
        Then("^user is in \"lapak terlaris\" page$", () -> {
            bukalapak.lapakTerlarisPage().userOnLapakTerlarisPage();
        });

        Then("^user see (\\d+) Lapak Terlaris in lapak terlaris page$", (Integer totalLapak) -> {
            bukalapak.lapakTerlarisPage().verifyListLapakTerlaris(totalLapak);
        });

        When("^user click on lapak terlaris rank (\\d+)$", (Integer rank) -> {
            bukalapak.lapakTerlarisPage().tapLapakTerlarisRank(rank);
        });

        Then("^user is shown category \"(.+)\" on lapak terlaris page$", (String categoryName) -> {
            bukalapak.lapakTerlarisPage().verifyLapakTerlarisCategory(categoryName);
        });
    }

}
