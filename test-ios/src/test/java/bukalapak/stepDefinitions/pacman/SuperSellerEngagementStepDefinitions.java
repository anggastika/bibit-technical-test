package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SuperSellerEngagementStepDefinitions extends TestInstrument implements En {
    public SuperSellerEngagementStepDefinitions() {
        And("^user is shown \"([^\"]*)\" info$", (String info) -> {
            bukalapak.superSellerEngagementPage().userShownInfo(info);
        });
    }
}
