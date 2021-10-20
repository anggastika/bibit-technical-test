package bukalapak.stepDefinitions.coreapps;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class DeeplinkTesterStepDefinitions extends TestInstrument implements En {

    public DeeplinkTesterStepDefinitions() {
        When("user fill \"([^\"]*)\" in Deeplink Tester field", (String deeplinkTesterURL) -> {
            bukalapak.deeplinkTesterPage().fillDeeplinkTester(deeplinkTesterURL);
        });
    }
}
