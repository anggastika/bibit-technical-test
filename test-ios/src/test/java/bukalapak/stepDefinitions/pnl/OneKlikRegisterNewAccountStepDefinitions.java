package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class OneKlikRegisterNewAccountStepDefinitions extends TestInstrument implements En {

    public OneKlikRegisterNewAccountStepDefinitions() {

        Given("user is in \"oneklik_register_new_account\" page", () -> {
            bukalapak.oneKlikRegisterNewAccountPage().userOnOneKlikRegisterNewAccountPage();
        });

        And("user verify that Oneklik register form is existed", () -> {
            try {
                bukalapak.iOSBasePage().waitForVisibilityOf("oneklik_register_form", 15);
            } catch (Exception e) {
                bukalapak.iOSBasePage().waitForVisibilityOf("oneklik_register_limit_text", 15);
            }
        });

        When("user click back button on Oneklik register new account page", () -> {
            bukalapak.iOSBasePage().tapElement("oneklik_register_back_navbar_button");
        });
    }
}
