package bukalapak.stepDefinitions.mtx;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TransactionListRevampStepDefinition extends TestInstrument implements En {

    public TransactionListRevampStepDefinition() {

        And("user go to list transaction", () -> {
            bukalapak.transactionListRevampPage().goToListTransaction();
        });

        Then("user is on transaction list page", () -> {
            bukalapak.transactionListRevampPage().validateOnTransactionList();
        });

        When("^user search invoice number( using with keyword \"([^\"]*)\")?$", (String invoiceNumber) -> {
            bukalapak.transactionListRevampPage().searchInvoiceNumber(invoiceNumber);
        });

        And("^user validate label state is \"([^\"]*)\"$", (String state) -> {
            bukalapak.transactionListRevampPage().verifyLabelState(state);
        });

        And("^user (can|cant) see \"([^\"]*)\" section$", (String condition, String section) -> {
            bukalapak.transactionListRevampPage().validateSection(condition, section);
        });

        And("user tap cara bayar button", () -> {
            bukalapak.transactionListRevampPage().tapCaraBayarBtn();
        });

        And("user is on instruction payment \"([^\"]*)\"", (String paymentMethod) -> {
            bukalapak.transactionListRevampPage().validateInstructionPaymentPage(paymentMethod);
        });

        And("^user (can|cant) see Ganti Metode Pembayaran button$", (String condition) -> {
            bukalapak.transactionListRevampPage().validateChangePaymentButton(condition);
        });

        And("user change payment method to \"([^\"]*)\" on instruction payment page", (String paymentMethod) -> {
            bukalapak.transactionListRevampPage().changePaymentMethod(paymentMethod);
        });

        And("^user back to transaction list from payment confirmation( transfer bank)? page$", (String paymentMethod) -> {
            bukalapak.transactionListRevampPage().backToTransactionList(paymentMethod);
        });
    }
}
