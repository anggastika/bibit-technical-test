package bukalapak.stepDefinitions.stripe;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BelumDikirimStepDefinitions extends TestInstrument implements En {

    public BelumDikirimStepDefinitions() {
        And("user tap on transactions in Belum Dikirim page", () -> {
            bukalapak.belumDikirimPage().clickOnBelumDikirim();
        });

        Then("Product match with user choosed", () -> {
            bukalapak.belumDikirimPage().validationProductName();
        });

        And("user tap on Proses", () -> {
            bukalapak.belumDikirimPage().clickProses();
        });

        And("Resi Number is invalid", () -> {
            bukalapak.belumDikirimPage().validationInvalidResiNumber();
        });

    }
}
