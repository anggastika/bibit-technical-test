package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TravelSubscriptionDetailPaketSteps extends TestInstrument implements En {

    public TravelSubscriptionDetailPaketSteps() {
        When("users check tnc the subscription package", () -> {
            bukalapak.travelSubscriptionDetailPage().tapOnSyaratDanKetentuan();
        });

        When("users check how to buy a subscription package", () -> {
            bukalapak.travelSubscriptionDetailPage().tapOnHowToBuySubscriptionPackage();
        });
    }
}
