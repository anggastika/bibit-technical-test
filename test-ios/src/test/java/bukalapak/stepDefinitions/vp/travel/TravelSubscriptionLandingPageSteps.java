package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TravelSubscriptionLandingPageSteps extends TestInstrument implements En {

    public TravelSubscriptionLandingPageSteps() {

        And("the Subscription landing page will have displayed", () -> {
            bukalapak.travelSubscriptionLandingPage().validateOnPage();
        });

        When("^user choose one of the subscription package$", () -> {
            bukalapak.travelSubscriptionLandingPage().swipeToPackage();
            bukalapak.travelSubscriptionLandingPage().setPackageName();
            bukalapak.travelSubscriptionLandingPage().setExpiryTime();
            bukalapak.travelSubscriptionLandingPage().tapOnPackage();
        });

        Then("^the detail of subscription package will have displayed$", () -> {
            bukalapak.travelSubscriptionDetailPage().validateOnPage();
            bukalapak.travelSubscriptionDetailPage().setPackagePrice();
            bukalapak.travelSubscriptionDetailPage().validatePackageName();
            bukalapak.travelSubscriptionDetailPage().validatePackageExpiryTime();
        });

        When("^user buys the subscription package$", () -> {
            bukalapak.travelSubscriptionDetailPage().tapOnBuyButton();
        });
    }
}
