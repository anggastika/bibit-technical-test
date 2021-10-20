package bukalapak.stepDefinitions.cde;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AskRatingAppsStepDefinitions extends TestInstrument implements En {

    public AskRatingAppsStepDefinitions() {

        And("user back to home page from invoices", () -> {
            bukalapak.askRatingAppPage().backToHomePage();
        });

        Then("user can see all component on sheet rating apps", () -> {
            bukalapak.askRatingAppPage().isOnAskRatingAppsSheet();
        });

        And("user validate default star is 5", () -> {
            bukalapak.askRatingAppPage().checkDefaultStar();
        });

        And("user dismiss the rating apps sheet in home", () -> {
            bukalapak.askRatingAppPage().dismissRatingAppsSheet();
        });

    }
}
