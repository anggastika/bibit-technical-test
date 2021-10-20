package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class FunRoomStepDefinitions extends TestInstrument implements En {

    public FunRoomStepDefinitions() {

        Given("user is in \"funroom\" page", () -> {
            bukalapak.funroomPage().userOnFunroomPage();
        });
    }
}
