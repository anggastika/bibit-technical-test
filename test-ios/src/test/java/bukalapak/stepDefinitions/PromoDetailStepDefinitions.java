package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromoDetailStepDefinitions extends TestInstrument implements En {

    public PromoDetailStepDefinitions(){

        Then("user is on promo detail page", () -> {
            bukalapak.promoDetailPage().isOnPromoDetailPage();
        });
    }
}
