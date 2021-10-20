package bukalapak.stepDefinitions.us;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ChangeEmailStepDefinitions extends TestInstrument implements En {
    public ChangeEmailStepDefinitions() {
        When("^user input unregistered email into new email field on change email page$", () -> {
            bukalapak.changeEmailPage().inputUnregisteredEmail();
        });

        // @password use ENV
        When("^user input \"([^\"]*)\" into password field on change email page$", (String password) -> {
            bukalapak.changeEmailPage().typeOnPasswordField(password);
        });

        When("^user click simpan button on change email page$", () -> {
            bukalapak.changeEmailPage().clickSimpanButton();
        });
    }
}
