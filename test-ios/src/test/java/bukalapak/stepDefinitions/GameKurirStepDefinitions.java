package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class GameKurirStepDefinitions extends TestInstrument implements En {

    public GameKurirStepDefinitions() {

        Given("user is in \"game_kurir\" page", () -> {
            bukalapak.gameKurirPage().userOnGameKurirPage();
        });
    }
}
