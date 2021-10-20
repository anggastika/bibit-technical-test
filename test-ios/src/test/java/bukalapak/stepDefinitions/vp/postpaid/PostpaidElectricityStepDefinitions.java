package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import org.openqa.selenium.TimeoutException;

public class PostpaidElectricityStepDefinitions extends TestInstrument implements En {

    public PostpaidElectricityStepDefinitions() {

        Then("the Listrik Pascabayar landing page will have displayed", () -> {
            bukalapak.postpaidElectricityPage().validateOnPage();
        });

        When("^user inputs (valid|invalid) customer number in the customer number field on the Listrik Pascabayar landing page$", (String numberType) -> {
            bukalapak.postpaidElectricityPage().typeCustomerNumber(numberType);
        });

        Then("^the Listrik Pascabayar transaction histories will( not)? have displayed$", (String flag) -> {
            bukalapak.postpaidElectricityPage().validateHistoryPage();
            bukalapak.postpaidElectricityPage().validateItemLoaded(flag == null );
        });

        Then("customer number field on the Listrik Pascabayar will have prefilled", () -> {
            bukalapak.postpaidElectricityPage().verifyCustomerNumberField();
        });

        Then("Listrik Pascabayar transaction will have continued", () -> {
            bukalapak.apiCall().sendLogInfoToSlack(dotenv.get("WEBHOOK_CHANNEL"));
            try {
                bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            } catch (TimeoutException e) {
                bukalapak.postpaidElectricityPage().validateLandingPage();
            }
        });

        Then("the error message will have displayed on Listrik Pascabayar landing page", () -> {
            bukalapak.postpaidElectricityPage().validateErrorMessage();
            bukalapak.postpaidElectricityPage().goToHomePage();
        });

    }
}
