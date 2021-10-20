package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PasswordCc3DsWebviewStepDefinitions extends TestInstrument implements En {

    public PasswordCc3DsWebviewStepDefinitions() {

        Then("user is in \"password cc 3ds webview\" page", () -> {
            bukalapak.passwordCc3DsWebviewPage().userOnPasswordCc3DsWebviewPage();
        });

        When("user input cc password", () -> {
            bukalapak.passwordCc3DsWebviewPage().inputCCPasswordField(dotenv.get("CC_PASSWORD"));
        });

    }
}
