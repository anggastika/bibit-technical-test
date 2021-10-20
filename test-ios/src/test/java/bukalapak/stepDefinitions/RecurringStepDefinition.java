package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class RecurringStepDefinition extends TestInstrument implements En {
    public RecurringStepDefinition() {

        When("user choose \"([^\"]*)\" as recurring vp product", (String product)-> {
            bukalapak.recurringPage().chooseProduct(product);
        });

        And("user tap on recurring mulai button", () -> {
            bukalapak.recurringPage().tapOnMulaiButton();
        });

        And("user is on recurring transaction page", () -> {
            bukalapak.recurringPage().userOnRecurringPage();
        });

        When("user go to recurring page via deeplink", ()-> {
            bukalapak.iOSBasePage().openDeepLink("/transaksi-rutin");
        });

        When("user tap on latest recurring transaction", () -> {
            bukalapak.recurringPage().tapOnLatestTransaction();
        });

        Then("user successfully daftar transaksi rutin", ()-> {
            bukalapak.recurringPage().tapOnLastTransactionCellButton();
        });

        When("user open detail last transaction on list of transaction", ()-> {
            bukalapak.recurringPage().tapOnLastTransactionCellButton();
        });

        Then("user should see latest transaction is now inactive", () -> {
            bukalapak.recurringPage().validateInactiveTransaction();
        });

    }
}
