package bukalapak.stepDefinitions.bee.BukaSend.WebView;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaSendWebViewLandingStepDefinitions extends TestInstrument implements En {
    public BukaSendWebViewLandingStepDefinitions() {
        And("user send package", () -> {
            bukalapak.bukaSendWebViewLandingPage().validateLandingPage();
            bukalapak.bukaSendWebViewLandingPage().clickSendSinglePackageButton();
        });
    }
}
