package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class RecurringTransactionFormStepDefinitions extends TestInstrument implements En {

    public RecurringTransactionFormStepDefinitions(){
        Then("user is on recurring transaction form page", () -> {
            bukalapak.recurringTransactionFormPage().userOnRecurringTransactionFormPage();
        });

        When("user fills recurring transaction data for \"([^\"]*)\" with date \"([^\"]*)\"", (String product, String date) -> {
            bukalapak.recurringTransactionFormPage().typeOnVANumber(product);
            bukalapak.recurringTransactionFormPage().tapOnDate(date);
            bukalapak.recurringTransactionFormPage().tapOnTerapkanButton();
        });

        When("user tap on daftar button", () -> {
            bukalapak.recurringTransactionFormPage().tapOnTnCCheckbox();
            bukalapak.recurringTransactionFormPage().tapOnDaftarButton();
        });

        When("user tap on Metode Pembayaran", () -> {
            bukalapak.recurringTransactionFormPage().tapOnMetodePembayaran();
        });

    }

}
