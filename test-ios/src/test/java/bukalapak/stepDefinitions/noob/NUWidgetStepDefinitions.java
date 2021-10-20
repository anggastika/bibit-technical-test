package bukalapak.stepDefinitions.noob;
import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * @author Aris Nugraha 22/10/2019
 */

public class NUWidgetStepDefinitions extends TestInstrument implements En {

    public NUWidgetStepDefinitions() {
        Given("user is in \"phone_verification\" page", () -> {
            bukalapak.newUserWidgetPage().validateVerificationPhonePage();
        });

        When("user scroll down to bonus pengguna baru section", () -> {
            bukalapak.newUserWidgetPage().validateOnNUWidget();
        });

        Then("user see bonus new voucher section", () -> {
            bukalapak.newUserWidgetPage().validateBonusVoucherNewUser();
        });

        Then("user redirect to verification phone screen", () -> {
            bukalapak.newUserWidgetPage().validateVerificationPhonePage();
        });
    }
}
