package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SuperSellerBonusStepDefinitions extends TestInstrument implements En {
    public SuperSellerBonusStepDefinitions() {
        And("user is in \"Super Seller Bonus\" page", () -> {
            bukalapak.superSellerBonusPage().userOnSuperSellerBonus();
        });

        Then("user is shown Bonus Diskon info", () -> {
            bukalapak.superSellerBonusPage().userShownBonusDiskonInfo();
        });

        And("user is shown Bonus Fitur info", () -> {
            bukalapak.superSellerBonusPage().userShownBonusFiturInfo();
        });

    }
}
