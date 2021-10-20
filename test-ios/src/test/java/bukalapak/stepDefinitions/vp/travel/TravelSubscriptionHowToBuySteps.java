package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TravelSubscriptionHowToBuySteps extends TestInstrument implements En {

    public TravelSubscriptionHowToBuySteps() {
        Then("the Subscription guidance page will have displayed", () -> {
            bukalapak.travelSubscriptionHowToBuyPage().validatePageContent();
        });
    }
}
