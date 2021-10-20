package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class EsamsatStepDefinitions extends TestInstrument implements En {

    public EsamsatStepDefinitions() {

        When("user validate Esamsat information data", () -> {
            bukalapak.esamsatPage().validateDetailInfo();
        });

        And("user inputs invalid vehicle number on the Esamsat landing page", () -> {
            bukalapak.esamsatPage().typeVehicleNumber("invalid");
        });

        And("user inputs KTP number on the Esamsat landing page", () -> {
            bukalapak.esamsatPage().typeNIKNumber(dotenv.get("ESAMSAT_KTP"));
        });

        And("user click next button on the Esamsat landing page", () -> {
            bukalapak.esamsatPage().submitDataEsamsat();
        });

        And("user click Bayar on Esamsat landing page", () -> {
            bukalapak.esamsatPage().clickBayarButton();
        });

        Then("the user will redirected to checkout page", () -> {
            bukalapak.esamsatPage().validateOnCheckoutPage();
        });

        When("user continue to TnC Esamsat via widget", () -> {
            bukalapak.esamsatPage().goToWidget();
        });

        When("user continue to TnC Esamsat via link", () -> {
            bukalapak.esamsatPage().goToLink();
        });

        When("the TnC on important information page will have displayed", () -> {
            bukalapak.esamsatPage().validateTnCPage();
        });

        Then("the tax information will not have showed", () -> {
            bukalapak.esamsatPage().validateErrorPage();
        });
    }
}
