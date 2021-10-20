package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TravelSubscriptionTnCSteps extends TestInstrument implements En {

    public TravelSubscriptionTnCSteps() {
        Then("the Subscription tnc page will have displayed", () -> {
            bukalapak.travelSubscriptionTnCPage().validateOnPage();
        });
    }
}
