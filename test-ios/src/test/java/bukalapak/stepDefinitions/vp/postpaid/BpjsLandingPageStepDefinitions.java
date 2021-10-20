package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import org.openqa.selenium.TimeoutException;

public class BpjsLandingPageStepDefinitions extends TestInstrument implements En {

    public BpjsLandingPageStepDefinitions() {

        When("the BPJS Kesehatan landing page will have displayed", () -> {
            bukalapak.bpjsKesehatanPage().verifyPageDisplayed();
        });

        When("user go to BPJS Kesehatan page via URL", () -> {
            bukalapak.bpjsKesehatanPage().goToPage();
        });

        When("the BPJS Kesehatan transaction histories will have displayed", () -> {
            bukalapak.bpjsKesehatanPage().validateTransactionHistoryPage();
        });

        When("user buys a BPJS Kesehatan with invalid data", () -> {
            bukalapak.bpjsKesehatanPage().typeCustomerNumber("invalid");
            bukalapak.bpjsKesehatanPage().chooseMonth();
        });

        When("error message will displayed in BPJS Kesehatan landing page", () -> {
            bukalapak.bpjsKesehatanPage().showAlertMessage();
        });

        When("the customer number field on the Bpjs Kesehatan will have prefilled", () -> {
            bukalapak.bpjsKesehatanPage().validateCustomerNumber();
        });

        Then("BPJS Kesehatan transaction will have continued", () -> {
            bukalapak.apiCall().sendLogInfoToSlack(dotenv.get("WEBHOOK_CHANNEL"));

            try {
                bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            } catch (TimeoutException e) {
                bukalapak.bpjsKesehatanPage().verifyPageDisplayed();
            }
        });

    }
}
