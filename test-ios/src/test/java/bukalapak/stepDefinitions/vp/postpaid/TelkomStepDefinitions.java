package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TelkomStepDefinitions extends TestInstrument implements En {

    public TelkomStepDefinitions() {

        When("^user (?:navigates to|had been on) Telkom page(?: via URL)?$", () -> {
            bukalapak.telkomPage().goToPage();
            bukalapak.telkomPage().isDisplayed();
        });


        Then("^user navigates to Telkom transaction history page$", () -> {
            bukalapak.telkomPage().tapOnIconTransactionHistory();
        });

        Then("^the Telkom transaction histories will( not)? have displayed$", (String flag) -> {
            bukalapak.telkomPage().validateTransactionHistoryPage();
            bukalapak.telkomPage().validateItemLoaded(flag == null);
        });

        Then("^the customer number field on the Telkom landing page will have filled with \"([^\"]*)\" number", (String lastTransaction) -> {
            bukalapak.telkomPage().autoFillCustomerNumber(lastTransaction);
        });

        And("^user input telkom invalid customer number$", () -> {
            bukalapak.telkomPage().typeCustomerNumber(dotenv.get("TELKOM_INVALID_NUMBER"));
        });

        Then("^the error message will have displayed on Telkom landing page$", () -> {
            bukalapak.telkomPage().validateErrorMessage();
        });
    }
}
