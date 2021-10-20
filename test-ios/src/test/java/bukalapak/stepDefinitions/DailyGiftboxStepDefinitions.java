package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class DailyGiftboxStepDefinitions extends TestInstrument implements En {

    public DailyGiftboxStepDefinitions() {

        Given("user is in \"daily_giftbox\" page", () -> {
            bukalapak.dailyGiftboxPage().userOnDailyGiftboxPage();
        });
    }
}
