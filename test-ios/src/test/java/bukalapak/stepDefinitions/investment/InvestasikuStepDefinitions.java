package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class InvestasikuStepDefinitions extends TestInstrument implements En {

    public InvestasikuStepDefinitions() {

        Then("user should be able to see Investasiku page displayed", () -> {
            bukalapak.investasikuPage().verifyInvestasikuPageDisplayed();
            bukalapak.investasikuPage().verifyProductCardDisplayed();
        });

        When("user tap on BukaReksa product in Investasiku page", () -> {
            bukalapak.investasikuPage().tapProductCard();
        });

        When("user tap on \"([^\"]*)\" button in Investasiku page", (String button) -> {
            bukalapak.investasikuPage().tapOnTransactionBtn(button);
        });

        Then("user should be able to see registration due date displayed", () -> {
            bukalapak.investasikuPage().verifyBwrInfoDisplayed();
        });

        When("user tap on Mulai daftar button in Investasiku page", () -> {
            bukalapak.investasikuPage().tapOnMulaiDaftarBtn();
        });

        Then("user should be able to see registration progress displayed", () -> {
            bukalapak.investasikuPage().verifyRegisrationProgressDisplayed();
        });

    }
}
