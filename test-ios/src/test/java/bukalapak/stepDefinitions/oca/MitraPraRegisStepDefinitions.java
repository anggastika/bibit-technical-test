package bukalapak.stepDefinitions.oca;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class MitraPraRegisStepDefinitions extends TestInstrument implements En {
    public MitraPraRegisStepDefinitions() {
        Then("user is on pre regist page", () -> {
            bukalapak.praRegisPage().userOnPraRegisPage();
        });

        When("^user enter (invalid|registered|valid) praregis phone number$", (String phoneNumber) -> {
            bukalapak.praRegisPage().enterPhoneNumber(phoneNumber);
        });

        Then("^user see warning message empty phone number$", () -> {
            bukalapak.praRegisPage().validateEmptyPhoneNumber();
        });

        Then("^user see warning invalid phone number$", () -> {
            bukalapak.praRegisPage().validateInvalidPhoneNumber();
        });

        Then("^user see warning wrong phone number$", () -> {
            bukalapak.praRegisPage().validateWrongPhoneNumber();
        });

        Then("^user is landed on otp page$", () -> {
            bukalapak.otpPage().userOntRequestOTPPage();
        });
    }
}
