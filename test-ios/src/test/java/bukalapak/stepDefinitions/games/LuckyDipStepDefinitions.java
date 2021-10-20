package bukalapak.stepDefinitions.games;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class LuckyDipStepDefinitions extends TestInstrument implements En {

    public LuckyDipStepDefinitions() {

        And("user is on lucky dip Home page", () -> {
            bukalapak.luckyDipPage().userIsOnLuckyDipHomePage();
        });

        When("user scratch on coupon scratch area", () -> {
            bukalapak.luckyDipPage().scratchOnCouponScratchArea();
        });

        Then("snack bar notification should be shown", () -> {
            bukalapak.luckyDipPage().verifySnackBarNotificationIsVisible();
        });

        And("coupon code should be shown", () -> {
            bukalapak.luckyDipPage().verifyCouponCodeIsVisible();
        });
    }
}
