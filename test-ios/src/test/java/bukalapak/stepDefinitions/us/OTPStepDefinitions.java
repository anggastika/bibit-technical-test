package bukalapak.stepDefinitions.us;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */
public class OTPStepDefinitions extends TestInstrument implements En {

    public OTPStepDefinitions() {
        //region Simple action

        And("user request OTP", () -> {
            bukalapak.otpPage().userOntRequestOTPPage();
            bukalapak.iOSBasePage().tapElement("kirim_sms_button");
        });

        And("user input invalid otp", () -> {
            bukalapak.otpPage().inputInvalidOTP();
        });

        And("user input valid OTP", () -> {
            bukalapak.otpPage().tapNewOTP();
        });

        //endregion Simple action


        //region E2E action

        When("^user proceed OTP verification", () -> {
            bukalapak.otpPage().proceedOTP();
        });

        //endregion E2E action


        //region Validation

        Then("User see OTP page", () -> {
            bukalapak.otpPage().userOnOTPConfirmationPage();
        });

        Given("user is in \"otp_confirm\" page", () -> {
            bukalapak.otpPage().userOnOTPConfirmPage();
        });

        Given("user is in \"invalid_otp\" page", () -> {
            bukalapak.otpPage().userOnInvalidOTPPage();
        });

        When("^user is on \"request_otp\" page$", () -> {
            bukalapak.otpPage().userOntRequestOTPPage();
        });

        //endregion Validation

        // region OTP Webview

        When("user input \"([^\"]*)\" correct password in webview", (String user) -> {
            bukalapak.otpPage().verifyWebviewVerificationPageDisplayed();
            bukalapak.otpPage().inputPasswordinWebview(user);
        });

        When("user tap on webview verification button", () -> {
            bukalapak.otpPage().tapOnVerificationWebviewBtn();
        });

        When("user tap on request SMS button from webview page", () -> {
            bukalapak.otpPage().tapOnRequestViaSMSBtn();
        });

        Then("user verify webview OTP Page displayed", () -> {
            bukalapak.otpPage().verifyWebviewOTPPageDisplayed();
        });

    }
}
