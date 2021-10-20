package bukalapak.stepDefinitions.games;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PohonRejekiStepDefinitions extends TestInstrument implements En {

    public PohonRejekiStepDefinitions(){

        And("user is on Pohon Rejeki home page", () -> {
            bukalapak.pohonRejekiPage().userIsOnPohonRejekiPage();
        });

        And("user can see the value of waterdrop in progress bar", () -> {
            bukalapak.pohonRejekiPage().validateUserWaterdrop();
        });

        And("user tap Pohon Rejeki Watering Pot button", () -> {
            bukalapak.pohonRejekiPage().tapOnPohonRejekiWateringPotButton();
        });

        And("^the value of waterdrop in progress bar is (not )?increased$", (String isIncreased) -> {
            bukalapak.pohonRejekiPage().checkUserLevelAndWaterdropAfterWatering(isIncreased);
        });

        And("total waterdrop in pot should reset to 0", () -> {
            bukalapak.pohonRejekiPage().validateZeroWaterdropInPot();
        });

        And("user can see timer countdown when the pot is being filled with waterdrop", () -> {
            bukalapak.pohonRejekiPage().validateWateringCountdown();
        });
    }
}
