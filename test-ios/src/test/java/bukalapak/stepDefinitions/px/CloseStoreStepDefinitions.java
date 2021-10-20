package bukalapak.stepDefinitions.px;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class CloseStoreStepDefinitions extends TestInstrument implements En {

    public CloseStoreStepDefinitions() {

        Given("user is in close store page with deeplink \"([^\"]*)\"", (String deeplink) -> {
            bukalapak.closeStorePage().userOnCloseStorePageWithDeeplink(deeplink);
        });

        When("user close store", () -> {
            bukalapak.closeStorePage().fillFormWithDefaultValue();
        });

        When("user open store", () -> {
            bukalapak.closeStorePage().openStore();
        });

        Then("close store should succeed", () -> {
            bukalapak.closeStorePage().validateCloseStoreSuccess();
        });

        Then("open store should succeed", () -> {
            bukalapak.closeStorePage().validateOpenStoreSuccess();
        });
    }
}
