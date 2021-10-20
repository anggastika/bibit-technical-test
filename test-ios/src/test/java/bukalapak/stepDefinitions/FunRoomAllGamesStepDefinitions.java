package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class FunRoomAllGamesStepDefinitions extends TestInstrument implements En {

    public FunRoomAllGamesStepDefinitions() {

        Given("user is in \"funroom_all_games\" page", () -> {
            bukalapak.funroomAllGamesPage().userOnFunroomAllGamesPage();
        });
    }
}
