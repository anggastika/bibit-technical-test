package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by NurdianSetyawan on 9/1/19.
 */
public class CreditCardStepDefinitions extends TestInstrument implements En {
    public CreditCardStepDefinitions() {
        Given("user is in \"credit_card\" page", () -> {
            bukalapak.creditCardPage().userOnCreditCardPage();
        });

        Given("user is in \"lanjut_pembayaran_cc\" page", () -> {
            bukalapak.continueCreditCardPage().userOnContinueCreditCardPage();
        });

        When("user verify total payment in credit card page is match with the one memorized before", () -> {
            bukalapak.creditCardPage().verifyTotalPaymentIsMatch();
        });

        Then("user verify total payment in master card page is match with the one memorized before", () -> {
            bukalapak.creditCardPage().verifyMasterCardPaymentIsMatch();
        });
    }
}
