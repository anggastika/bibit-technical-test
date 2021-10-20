package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class CreditCardBillStepDefinitions extends TestInstrument implements En {

    public CreditCardBillStepDefinitions() {

        When("user input Kartu Kredit invalid customer number", () -> {
            bukalapak.creditCardBillPage().typeCustomerNumber(dotenv.get("KARTU_KREDIT_INVALID_NUMBER"));
        });

        Then("user navigates to Tagihan Kartu Kredit transaction history page", () -> {
            bukalapak.creditCardBillPage().tapOnIconTransactionHistory();
        });

        Then("^the Tagihan Kartu Kredit transaction histories will( not)? have displayed$", (String type) -> {
            bukalapak.creditCardBillPage().validateTransactionHistoryPage();
            bukalapak.creditCardBillPage().validateItemLoaded(type);
        });

        Then("the error message will have displayed on Kartu Kredit landing page", () -> {
            bukalapak.creditCardBillPage().validateErrorMessage();
        });

        And("user buys a kartu kredit product and copy promo code", () -> {
            bukalapak.creditCardBillPage().typeNominalPayment("100000");
            bukalapak.creditCardBillPage().typeCustomerNumber(dotenv.get("KARTU_KREDIT_CIMB_NUMBER"));
            bukalapak.creditCardBillPage().pickBank("CIMB NIAGA");
        });

        And("user copies promo code on the Credit Card landing page", () -> {
            bukalapak.creditCardBillPage().tapOnPromoTerbaru();
            bukalapak.creditCardBillPage().tapOnSalinButton();
        });

        Then("the selected promo code for kartu kredit will have copied", () -> {
            bukalapak.creditCardBillPage().validatePromoCopied();
        });
    }
}
