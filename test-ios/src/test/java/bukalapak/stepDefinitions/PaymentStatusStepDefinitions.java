package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PaymentStatusStepDefinitions extends TestInstrument implements En {

    public PaymentStatusStepDefinitions() {
        Then("DANA success payment must displaying", () -> {
            bukalapak.paymentStatusPage().validateDANAPaymentIsSuccess();
        });

        When("user tap Lihat Tagihan Pembayaran button", () -> {
            bukalapak.paymentStatusPage().tapSeePaymentDetailButton();
        });

        When("user tap Lihat Detail Pesanan button", () -> {
            bukalapak.paymentStatusPage().tapSeePaymentDetailOrder();
        });

        Then("not enough Buka DANA balance message must displaying", () -> {
            bukalapak.paymentStatusPage().validateBalanceIsNotEnough();
        });

        Then("not binding Buka DANA message must displaying", () -> {
            bukalapak.paymentStatusPage().validateUserIsNotBinding();
        });

        And("user go to invoice detail", () -> {
            bukalapak.paymentStatusPage().tapTransactionDetail();
        });

        And("user should see \"([^\"]*)\" on invoice detail page", (String paymentMethod) -> {
            bukalapak.paymentStatusPage().validatePaymentMethod(paymentMethod);
        });

        Then("CC success payment must displaying", () -> {
            bukalapak.paymentStatusPage().validateCCPaymentIsSuccess();
        });
    }
}
