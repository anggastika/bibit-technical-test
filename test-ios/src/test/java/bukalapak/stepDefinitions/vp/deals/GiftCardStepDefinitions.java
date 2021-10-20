package bukalapak.stepDefinitions.vp.deals;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * @Author: Ayu Musfita
 * @Date: 20/04/20, Mon
 **/
public class GiftCardStepDefinitions extends TestInstrument implements En {

    public GiftCardStepDefinitions() {

        And("the Gift Card landing page will have displayed", () -> {
            bukalapak.giftCardLandingPage().validateOnPage();
        });

        Then("error message to repeat after 10 minutes will be displayed", () -> {
            bukalapak.giftCardLandingPage().validateTryLaterText();
            bukalapak.giftCardLandingPage().tapOnOkeButton();
        });

        When("user redeem with three failed hit", () -> {
            bukalapak.giftCardLandingPage().tapOnExchangeVoucherButton();
            bukalapak.giftCardLandingPage().inputInvalidCode(3);
        });

        When("user redeem with invalid the voucher code", () -> {
            bukalapak.giftCardLandingPage().tapOnExchangeVoucherButton();
            bukalapak.giftCardLandingPage().inputInvalidCode(1);
        });

        Then("error message voucher code not registered will have displayed", () -> {
            bukalapak.giftCardLandingPage().validateInvalidVoucherCodeText();
        });

        Then("before 10 minutes the user cannot redeem again", () -> {
            bukalapak.giftCardLandingPage().tapOnExchangeVoucherButton();
            bukalapak.giftCardLandingPage().inputInvalidCode(1);
            bukalapak.giftCardLandingPage().validateTryLaterText();
        });
    }
}
