package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AddOnIndihomePageStepDefinitions extends TestInstrument implements En {

    public AddOnIndihomePageStepDefinitions() {

        When("user buys a Addon indihome product with a invalid data", () -> {
            bukalapak.addonIndihomePage().selectService(dotenv.get("ADDON_INDIHOME_SERVICE"));
            bukalapak.addonIndihomePage().inputInvalidNumber();
        });

        When("error message will displayed in Addon Indihome landing page", () -> {
            bukalapak.addonIndihomePage().showAlertMessage();
        });

        Then("^the Addon Indihome transaction histories will( not)? have displayed$", (String flag) -> {
            bukalapak.addonIndihomePage().validateHistoryPage();
            bukalapak.addonIndihomePage().validateItemLoaded(flag);
        });
    }
}
