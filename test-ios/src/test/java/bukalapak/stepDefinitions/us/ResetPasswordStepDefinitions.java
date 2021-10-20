package bukalapak.stepDefinitions.us;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ResetPasswordStepDefinitions extends TestInstrument implements En {
    public ResetPasswordStepDefinitions() {

        // @newPasssword use ENV
        When("^user input \"([^\"]*)\" into new password field on reset password page$", (String newPassword) -> {
            bukalapak.resetPasswordPage().typeNewPassword(newPassword);
        });

        // @newPasssword use ENV
        When("^user input \"([^\"]*)\" into confirmation password field on reset password page$", (String confirmationPassword) -> {
            bukalapak.resetPasswordPage().typeConfirmationPassword(confirmationPassword);
        });

        When("^user click Ubah Password button on reset password page$", () -> {
            bukalapak.resetPasswordPage().clickUbahPasswordButton();
        });
    }
}
