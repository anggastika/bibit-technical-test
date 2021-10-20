package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class RecurringTransactionDetailStepDefinitions extends TestInstrument implements En {

    public RecurringTransactionDetailStepDefinitions() {

        Then("user is on recurring transaction detail page", () -> {
            bukalapak.recurringTransactionDetailPage().userIsOnRecurringTransactionDetailPage();
        });

        Then("user verify that detail recurring payment BPJS is correct on detail transaction page", () -> {
            bukalapak.recurringTransactionDetailPage().tapOnLanjutButton();
            bukalapak.recurringTransactionDetailPage().userVerifyDetailRecurringTransaction();
        });

        When("user tap on switch button", () -> {
            bukalapak.recurringTransactionDetailPage().tapOnSwitch();
        });

        When("user tap back button form detail transaction page", () -> {
            bukalapak.iOSBasePage().tapBackButton();
        });

    }

}
