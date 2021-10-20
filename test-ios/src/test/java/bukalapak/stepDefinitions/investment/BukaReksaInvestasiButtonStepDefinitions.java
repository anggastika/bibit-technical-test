package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaReksaInvestasiButtonStepDefinitions extends TestInstrument implements En {

    public BukaReksaInvestasiButtonStepDefinitions() {

        //region BukaReksa from button Investasi in Saldo Bukalapak page
        Then("user should be able to see BukaReksa invest page", () -> {
            bukalapak.bukaReksaInvestasiButtonPage().verifyInvestasiDiBukareksaPage();
        });

        And("user choose \"([^\"]*)\" as bukareksa payment method", (String paymentMethod) -> {
            bukalapak.bukaReksaInvestasiButtonPage().tapBayarDenganChevron();
            bukalapak.bukaReksaInvestasiButtonPage().choosePaymentMethod(paymentMethod);
        });

        Then("user should see error toast for product subscription", () -> {
            bukalapak.bukaReksaInvestasiButtonPage().verifyAppliedInvestorSubscribeErrorDisplayed();
        });

        Then("user should be able to see bwr error message", () -> {
            bukalapak.bukaReksaInvestasiButtonPage().verifyBWRInvestorSubscribeErrorDisplayed();
        });

        And("user see total product portofolio value in Investasi di BukaReksa page", () -> {
            bukalapak.bukaReksaInvestasiButtonPage().cekTotalProductPortofolioValue();
        });

        When("user tap on total product portfolio hyperlink", () -> {
            bukalapak.bukaReksaInvestasiButtonPage().tapProductPortofolioValue();
        });

        When("user tap on Bayar button", () -> {
            bukalapak.bukaReksaInvestasiButtonPage().tapBayarBtn();
        });

        When("user choose \"([^\"]*)\" product card", (String productName) -> {
            bukalapak.bukaReksaInvestasiButtonPage().selectProductItemInstant(productName);
        });

        When("user tap on Detail button", () -> {
            bukalapak.bukaReksaInvestasiButtonPage().tapOnDetailButton();
        });

        Then("user should be able to see product detail from Investasi di BukaReksa", () -> {
            bukalapak.bukaReksaProductPage().checkProductDetailInstantDisplayed();
        });

        Then("user should be able to see DANA option is Inactive", () -> {
            bukalapak.bukaReksaInvestasiButtonPage().verifyDANAOptionInactive();
        });

        Then("user tap DANA Topup on select payment method Popup", () -> {
            bukalapak.bukaReksaInvestasiButtonPage().tapTopupDanaButton();
        });
        //endregion BukaReksa from button Investasi in Saldo Bukalapak page
    }
}
