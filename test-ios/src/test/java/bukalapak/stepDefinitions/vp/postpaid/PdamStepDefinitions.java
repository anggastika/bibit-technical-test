package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import bukalapak.data.PostpaidData;
import cucumber.api.java8.En;
import org.openqa.selenium.TimeoutException;

public class PdamStepDefinitions extends TestInstrument implements En {

    public PdamStepDefinitions() {

        When("user pays pdam", () -> {
            bukalapak.pdamPage().goToPage();
            bukalapak.pdamPage().isDisplayed();
            bukalapak.pdamPage().typeCustomerNumber(PostpaidData.getCustomerNumber());
            bukalapak.pdamPage().pickArea(PostpaidData.getArea());
            bukalapak.pdamPage().tapOnBayarButton();
        });

        When("^user inputs (valid|invalid) customer number in the customer number field on the PDAM landing page$", (String numberType) -> {
            bukalapak.pdamPage().typeCustomerNumber(numberType);
            bukalapak.pdamPage().pickArea(dotenv.get("PDAM_AREA"));
        });

        Then("the error message will have displayed on PDAM landing page", () -> {
            bukalapak.pdamPage().validateErrorMessage();
        });

        Then("^the PDAM transaction histories will( not)? have displayed$", (String flag) -> {
            bukalapak.pdamPage().validateHistoryPage();
            bukalapak.pdamPage().validateItemLoaded(flag == null );
        });

        Then("PDAM transaction will have continued", () -> {
            bukalapak.pdamPage().tapOnBayarButton();
            bukalapak.apiCall().sendLogInfoToSlack(dotenv.get("WEBHOOK_CHANNEL"));
            try {
                bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            } catch (TimeoutException e) {
                bukalapak.pdamPage().isDisplayed();
            }
        });
    }
}
