package bukalapak.stepDefinitions.oca;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class DailyTaskWebPageStepDefinitions extends TestInstrument implements En {
    public DailyTaskWebPageStepDefinitions() {

        Then("user should land on daily task page", () -> {
            bukalapak.dailyTaskWebPage().userIsOnDailyTaskPage();
        });

        When("user tap on lanjut button on mission task", () -> {
            bukalapak.dailyTaskWebPage().tapOnLanjutMission();
        });

        Then("user should go to valid page", () -> {
            bukalapak.dailyTaskWebPage().userGoToValidPage();
        });
    }
}
