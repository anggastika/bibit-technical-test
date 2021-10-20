package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TeleponStepDefinitions extends TestInstrument implements En {

    public TeleponStepDefinitions() {
        When("user input \"([^\"]*)\" for reset password", (String input) -> {
            bukalapak.resetPasswordPage().typeFormResetPassword(input);
        });
    }
}
