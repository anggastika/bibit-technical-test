package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import bukalapak.data.InvestmentData;
import cucumber.api.java8.En;

public class AutoInvestStepDefinitions extends TestInstrument implements En {

    public AutoInvestStepDefinitions() {

        When("user scroll to autoinvest section", () -> {
           bukalapak.autoInvestPage().isAutoinvestSectionDisplayed();
        });

        Then("user reset autoinvest toggle to active", () -> {
           bukalapak.autoInvestPage().setAutoinvestToggleActive();
        });

        When("user \"([^\"]*)\" autoinvest toggle in account page", (String method) -> {
            bukalapak.autoInvestPage().changeAutoinvestToggleState(method);
        });

        Then("user should be able to see autoinvest \"([^\"]*)\"", (String state) -> {
           bukalapak.autoInvestPage().checkAutoinvestStatus(state);
        });

        When("user tap on Atur Jumlahnya button", () -> {
            bukalapak.autoInvestPage().tapAturJumlahnyaBtn();
        });

        Then("user should be able to see autoinvest settings page", () -> {
            bukalapak.autoInvestPage().isAutoinvestSettingsPageDisplayed();
        });

        When("user \"([^\"]*)\" autoinvest \"([^\"]*)\" from settings page", (String method, String type) -> {
           bukalapak.autoInvestPage().tapAutoinvestCheckbox(method, type);
        });

        When("user input \"([^\"]*)\" as autoinvest buyer amount", (String amount) -> {
            bukalapak.autoInvestPage().inputAutoinvestBuyerAmount(amount);
        });

        When("user input \"([^\"]*)\" as autoinvest seller amount", (String amount) -> {
            bukalapak.autoInvestPage().inputAutoinvestSellerAmount(amount);
        });

        When("user input random number as autoinvest \"([^\"]*)\" amount", (String type) -> {
            bukalapak.autoInvestPage().inputAutoinvestRandomAmount(type);
        });

        Then("user tap on Simpan button", () -> {
            bukalapak.autoInvestPage().tapOnSimpanBtn();
        });

        Then("user should be able to see autoinvest success message", () -> {
            bukalapak.autoInvestPage().verifySuccessSnackbarDisplayed();
        });

        Then("user should be able to see autoinvest error message", () -> {
            bukalapak.autoInvestPage().verifyFailedSnackbarDisplayed();
        });

        Then("user should be able to see minimum autoinvest inline error message", () -> {
           bukalapak.autoInvestPage().verifyInlineErrorMessageDisplayed();
        });

        Then("user validate autoinvest buyer amount", () -> {
            bukalapak.autoInvestPage().validateAutoinvestBuyerAmount();
        });

        Then("user validate autoinvest seller amount", () -> {
            bukalapak.autoInvestPage().validateAutoinvestSellerAmount();
        });

        Then("user should be able to see autoinvest \"([^\"]*)\" settings \"([^\"]*)\"", (String type, String state) -> {
            bukalapak.autoInvestPage().checkAutoinvestStateinSettingsPage(type, state);
        });

        Then("user should be able to deactivate autoinvest", () -> {
            bukalapak.autoInvestPage().deactiveAutoinvest();
        });

        Then("user see autoinvest buyer amount", () -> {
            bukalapak.autoInvestPage().setAutoInvestBuyerAmount();
        });
    }
}
