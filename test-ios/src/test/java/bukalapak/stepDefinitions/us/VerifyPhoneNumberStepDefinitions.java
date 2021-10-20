package bukalapak.stepDefinitions.us;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */

public class VerifyPhoneNumberStepDefinitions extends TestInstrument implements En {
    public VerifyPhoneNumberStepDefinitions () {
        When("user is in \"verify_phone_number\" page", () -> {
            bukalapak.verifyPhoneNumberPage().onVerifyPhoneNumberPage();
        });

        When("user input \"([^\"]*)\" into phone number field on verify phone number page", (String phoneNumber) -> {
            bukalapak.verifyPhoneNumberPage().typeOnPhoneNumberField(dotenv.get(phoneNumber));
        });

        When("user input \"([^\"]*)\" into password field on verify phone number page", (String password) -> {
            bukalapak.verifyPhoneNumberPage().typeOnPasswordField(dotenv.get(password));
        });

        When("phone number field on verify phone number page displays error message \"([^\"]*)\"", (String errorMessage) -> {
            bukalapak.verifyPhoneNumberPage().verifyPhoneNumberFieldErrorMessage(errorMessage);
        });

        When("password field on verify phone number page displays error message \"([^\"]*)\"", (String errorMessage) -> {
            bukalapak.verifyPhoneNumberPage().verifyPasswordFieldErrorMessage(errorMessage);
        });

        Then("user successfully verify phone number", () -> {
            bukalapak.verifyPhoneNumberPage().onSuccessVerifyPhoneNumberPage();
        });

        And("user verify phone number on account page successfully$", () -> {
            bukalapak.akunPage().clickAkunMenu();
            bukalapak.akunPage().userOnAkunPage();
            bukalapak.iOSBasePage().tapElement("account_phone_number_verification_button");
            bukalapak.otpPage().proceedOTP();
            bukalapak.verifyPhoneNumberPage().onSuccessVerifyPhoneNumberPage();
        });

        Then("user tap kirim button on verify phone page", () -> {
            bukalapak.verifyPhoneNumberPage().tapKirimOnVerifyPhonePage();
        });
    }
}
