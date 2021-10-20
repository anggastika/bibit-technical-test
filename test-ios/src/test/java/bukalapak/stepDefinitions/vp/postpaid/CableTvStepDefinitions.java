package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import bukalapak.data.PostpaidData;
import cucumber.api.java8.En;
import org.openqa.selenium.TimeoutException;

public class CableTvStepDefinitions extends TestInstrument implements En {

    public CableTvStepDefinitions() {

        When("user pays cable tv", () -> {
            bukalapak.cableTvPage().goToPage();
            bukalapak.cableTvPage().validateonpage();
            bukalapak.cableTvPage().pickBiller(PostpaidData.getBiller());
            bukalapak.cableTvPage().typeCustomerNumber(PostpaidData.getCustomerNumber());
            bukalapak.cableTvPage().tapOnBayarButton();
        });

        When("user input Tv Kabel provider", () -> {
            bukalapak.cableTvPage().pickBiller(dotenv.get("TV_KABEL_BILLER"));
        });

        When("user input Tv Kabel customer number", () -> {
            bukalapak.cableTvPage().typeCustomerNumber("valid");
        });

        When("Tv Kabel transaction will have continued", () -> {
            bukalapak.cableTvPage().tapOnBayarButton();
            bukalapak.apiCall().sendLogInfoToSlack(dotenv.get("WEBHOOK_CHANNEL"));
            try {
                bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            } catch (TimeoutException e) {
                bukalapak.cableTvPage().validateonpage();
            }
        });

        When("user open Tv Kabel history transaction with status \"([^\"]*)\"", (String state) -> {
            try {
                bukalapak.paymentConfirmationPage().isOnPaymentConfirmationPage();
                bukalapak.paymentConfirmationPage().verifyPage();
                bukalapak.paymentConfirmationPage().openInvoiceDetail();
                bukalapak.invoiceDetailNonMarketplacePage().userOnInvoiceDetailPage();
                bukalapak.invoiceDetailNonMarketplacePage().verifyInvoiceStateIsMatch(state);
            } catch (TimeoutException e) {
                bukalapak.cableTvPage().verifyTvKabelNumberHasBeenPaid();
            }
        });

        Then("contract number field on the Tv Kabel will have prefilled", () -> {
            bukalapak.cableTvPage().verifyCustomerNumberField();
        });

        Then("user pays Tv Kabel product with invalid data", () -> {
            bukalapak.cableTvPage().pickBiller(dotenv.get("TV_KABEL_BILLER"));
            bukalapak.cableTvPage().typeCustomerNumber("invalid");
        });

        Then("the error message will have displayed on Tv Kabel landing page", () -> {
            bukalapak.cableTvPage().validateErrorMessage();
        });

        Then("Tv Kabel transaction histories will have displayed", () -> {
            bukalapak.cableTvPage().validateHistoryPage();
            bukalapak.cableTvPage().goToHomePage();
        });
    }
}
