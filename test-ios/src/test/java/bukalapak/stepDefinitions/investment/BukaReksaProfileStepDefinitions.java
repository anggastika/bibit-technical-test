package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaReksaProfileStepDefinitions extends TestInstrument implements En {

    public BukaReksaProfileStepDefinitions() {

        // region Profile page
        Then("^processing user should be able to see BukaReksa Profile page displayed", () -> {
            bukalapak.bukaReksaProfilePage().checkProcessingProfilePageDisplayed();
        });
        //endregion
    }
}
