package bukalapak.stepDefinitions.vp.insurance.lead_generator;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * @Author: Ayu Musfita
 * @Date: 16/07/20, Thu
 **/
public class LeadGeneratorStepDefinitions extends TestInstrument implements En {

    public LeadGeneratorStepDefinitions() {

        When("^user register a Lead Generator (.*) package$", (String product) -> {
            bukalapak.leadGeneratorLandingPage().fillContactData(product);
            bukalapak.leadGeneratorLandingPage().tickOnSNKCheckbox();
            bukalapak.leadGeneratorLandingPage().tapOnRegisterButton();
        });

        Then("the success registration page will have displayed", () -> {
            bukalapak.leadGeneratorSuccessRegistrationPage().validateOnPage();
        });

        When("user taps on lihat detail button the Lead Generator", () -> {
            bukalapak.leadGeneratorLandingPage().tapOnDetailButton();
        });

        Then("the Lead Generator section will have displayed", () -> {
            bukalapak.leadGeneratorDetailPage().validateOnPage();
            bukalapak.leadGeneratorDetailPage().validateSectionDisplayed();
            bukalapak.leadGeneratorDetailPage().tapOnRegisterButton();
        });

        Then("the Lead Generator product list will have displayed", () -> {
            bukalapak.leadGeneratorLandingPage().validateOnPage("Asuransi Mobil");
        });

        When("user navigates to the Lead Generator history page", () -> {
            bukalapak.leadGeneratorLandingPage().tapOnHistoryButton();
        });

        When("the Lead Generator details histories will have displayed", () -> {
            bukalapak.leadGeneratorLandingPage().validateOnHistoryPage();
        });

        When("user does not input data on the Lead Generator form", () -> {
            bukalapak.leadGeneratorLandingPage().tickOnSNKCheckbox();
            bukalapak.leadGeneratorLandingPage().tapOnRegisterButton();
        });

        Then("^error message on \"([^\"]*)\" field the Lead Generator info form will have displayed required$", (String field) -> {
            bukalapak.leadGeneratorLandingPage().validateErrorMessageRequiredInput(field);
        });
    }
}
