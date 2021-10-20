package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PostpaidPhoneCreditStepDefinitions extends TestInstrument implements En {

    public PostpaidPhoneCreditStepDefinitions() {

        When("^user pays Pulsa Pascabayar product with invalid data$", () -> {
            bukalapak.postpaidPhoneCreditPage().isDisplayed();
            bukalapak.postpaidPhoneCreditPage().typeCustomerNumber("invalid");
        });

        Then("^the error message will have displayed on Pulsa Pascabayar landing page$", () -> {
            bukalapak.postpaidPhoneCreditPage().validateErrorMessage();
            bukalapak.postpaidPhoneCreditPage().goToHomePage();
        });

        Then("phone number field on the Pulsa Pascabayar will have prefilled", () -> {
            bukalapak.postpaidPhoneCreditPage().verifyCustomerNumberField();
        });

        Then("^Pulsa Pascabayar transaction histories will( not)? have displayed$", (String flag) -> {
            bukalapak.postpaidPhoneCreditPage().validateHistoryPage();
            bukalapak.postpaidPhoneCreditPage().validateItemLoaded(flag == null);
            bukalapak.postpaidPhoneCreditPage().goToHomePage();
        });
    }
}
