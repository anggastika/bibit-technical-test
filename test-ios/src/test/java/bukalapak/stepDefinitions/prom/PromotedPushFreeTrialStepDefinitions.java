package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushFreeTrialStepDefinitions extends TestInstrument implements En {

    public PromotedPushFreeTrialStepDefinitions() {
        Then("user is on Promoted Push Free Trial page", () -> {
            bukalapak.promotedPushFreeTrialPage().validateFreeTrialPage();
        });

        When("user taps on Coba Free Trial button", () -> {
            bukalapak.promotedPushFreeTrialPage().tapCobaButton();
        });

        Then("snakcbar info about user not eligible to join free trial is displayed", () -> {
            bukalapak.promotedPushFreeTrialPage().validateFailedClaimFreeTrialSnackbar();
        });

        When("user taps on info Promoted Push link", () -> {
            bukalapak.promotedPushFreeTrialPage().tapPromotedPushInfoLink();
        });

        Then("webview about Promoted Push is displayed", () -> {
            bukalapak.promotedPushFreeTrialPage().validatePromotedPushInfoWebView();
        });

        When("user taps on TNC Promoted Push free trial link", () -> {
            bukalapak.promotedPushFreeTrialPage().tapTNCLink();
        });

        Then("TNC modal about Promoted Push free trial is displayed", () -> {
            bukalapak.promotedPushFreeTrialPage().validateTNCModal();
            bukalapak.promotedPushFreeTrialPage().tapCloseTNCButton();
        });
    }
}
