package bukalapak.stepDefinitions.us;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */

public class VerifyEmailStepDefinitions extends TestInstrument implements En {
    public VerifyEmailStepDefinitions() {
        When("^user is in \"verify_email\" page$", () -> {
            bukalapak.verifyEmailPage().onVerifyEmailPage();
        });
        And("user input \"([^\"]*)\" into email field on verify email page", (String email) -> {
            bukalapak.verifyEmailPage().typeOnEmailField(dotenv.get(email));
        });
        And("^user input \"([^\"]*)\" into password field on verify email page$", (String password) -> {
            bukalapak.verifyEmailPage().typeOnPasswordField(dotenv.get(password));
        });
        Then("^user get error \"invalid_password\" notification on verify email page$", () -> {
            bukalapak.verifyEmailPage().verifyEmailFieldErrorMessage();
        });
        Then("^user get error \"invalid_email\" notification on verify email page$", () -> {
            bukalapak.verifyEmailPage().verifyPasswordFieldErrorMessage();
        });
    }
}
