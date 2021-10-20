package bukalapak.stepDefinitions.vp;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by sekarayukarindra on 24/02/20.
 */
public class CheckoutNonMarketplaceConfirmationStepDefinitions extends TestInstrument implements En {
    public CheckoutNonMarketplaceConfirmationStepDefinitions(){

        Then("^user navigates to invoice details from the confirmation check out non marketplace page$", () -> {
            bukalapak.checkoutNonMarketplaceConfirmationPage().validateOnPage();
            bukalapak.checkoutNonMarketplaceConfirmationPage().setOnDataTransaction();
            bukalapak.checkoutNonMarketplaceConfirmationPage().tapOnButtonLihatTagihanPembayaran();
        });

        Then("^user skip the confirmation check out non marketplace page$", () -> {
            bukalapak.checkoutNonMarketplaceConfirmationPage().tapOnButtonLihatTagihanPembayaran();
        });

    }
}
