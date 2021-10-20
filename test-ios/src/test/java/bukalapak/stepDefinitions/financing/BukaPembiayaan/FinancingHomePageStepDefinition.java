package bukalapak.stepDefinitions.financing.BukaPembiayaan;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class FinancingHomePageStepDefinition extends TestInstrument implements En {
    public FinancingHomePageStepDefinition() {

        When("^user tap on \"([^\"]*)\" button in financing", (String product) -> {
            bukalapak.financingHomePage().selectProduct(product);
        });

        Then("^user is in financing homepage", () -> {
            bukalapak.financingHomePage().isInFinancingHomePage();
        });

        When("^user shown pop up to verify the account", () -> {
            bukalapak.financingHomePage().verifyAccountPopUpShown();
            bukalapak.financingHomePage().goToHomePage();
        });
    }
}
