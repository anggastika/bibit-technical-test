package bukalapak.stepDefinitions.dana;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class DANABindingStepDefinitions extends TestInstrument implements En {

    public DANABindingStepDefinitions() {
        Given("user \"([^\"]*)\" self unbind using API", (String credentialUser) -> {
            bukalapak.apiCall().unbindDANA(credentialUser);
        });

        And("user binding DANA with \"([^\"]*)\"", (String pinFromENV) -> {
            bukalapak.danaBindingPage().inputPINDANA(pinFromENV,false);
        });

        And("user binding DANA with \"([^\"]*)\" on H5", (String pinFromENV) -> {
            bukalapak.danaBindingPage().inputPINDANA(pinFromENV,true);
        });

        Then("user tap back on DANA binding page", () -> {
            bukalapak.danaBindingPage().cancelDANABinding();
        });

        Then("user tap back on DANA page after register", () -> {
            bukalapak.danaBindingPage().cancelDanaAfterRegister();
        });

        //Binding Entrypoint
        When("user tap binding DANA on \"([^\"]*)\" page", (String page) -> {
            bukalapak.hubungkanDANAPage().tapBindingEntrypoint(page);
        });

        //After Binding
        Then("^user redirected to \"([^\"]*)\" page with updated DANA balance", (String page) -> {
            switch (page) {
                case "Alchemy Marketplace Checkout":
                    bukalapak.danaPaymentPage().verifyDANAPaymentPage();
                    bukalapak.danaPaymentPage().verifyDANAPaymentSelectionScreen();
                    bukalapak.danaPaymentPage().verifyDANABoundAlchemyMPCheckoutPage();
                    break;
                case "Alchemy VP Checkout":
                    bukalapak.danaPaymentPage().verifyDANABoundAlchemyVPCheckoutPage();
                    break;
                case "Serbu Seru Checkout":
                    bukalapak.danaPaymentPage().verifyDANABoundSerbuSeruCheckoutPage();
                default:
            }
            bukalapak.hubungkanDANAPage().verifyAfterBinding(page);
        });

        And("user cancel binding process", () -> {
            bukalapak.hubungkanDANAPage().cancelBinding();
        });

        //Rebind
        Then("user validate rebind popup", () -> {
            bukalapak.hubungkanDANAPage().validateRebindPopup();
        });

        Then("^user select (Hubungkan|Lain Kali|BukaBantuan) at rebind popup$", (String option) -> {
            bukalapak.hubungkanDANAPage().selectRebindOption(option);
        });

        And("user validate rebind BukaBantuan", () -> {
            bukalapak.hubungkanDANAPage().rebindBukaBantuan();
        });

        And("user close binding popup", () -> {
            bukalapak.hubungkanDANAPage().closeBindingPopup();
        });
    }
}
