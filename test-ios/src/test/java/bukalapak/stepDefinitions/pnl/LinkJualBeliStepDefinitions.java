package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class LinkJualBeliStepDefinitions extends TestInstrument implements En {

    public LinkJualBeliStepDefinitions() {

        Then("user is in \"ljb_dashboard\" page", () -> {
            bukalapak.linkJualBeliPage().verifyDashboardWebview();
        });

        Then("user is in \"ljb_onboarding\" page", () -> {
            bukalapak.linkJualBeliPage().verifyOnboardingWebview();
        });

    }

}
