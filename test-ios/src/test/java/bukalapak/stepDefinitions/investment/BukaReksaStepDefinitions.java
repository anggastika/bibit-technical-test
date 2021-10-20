package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import bukalapak.data.TransactionData;
import cucumber.api.java8.En;
import org.junit.Assert;

public class BukaReksaStepDefinitions extends TestInstrument implements En {

    public BukaReksaStepDefinitions() {

        //General steps in Bukareksa feature
        When("user tap on back button in BukaReksa page", () -> {
            bukalapak.bukaReksaPage().tapOnBackButton();
        });

        When("user back to BukaReksa Homepage", () -> {
            bukalapak.bukaReksaPage().backToBukaReksaHomePage();
        });

        When("^user tap on \"([^\"]*)\" navigation tab", (String tabName) -> {
            bukalapak.bukaReksaPage().selectNavigation(tabName);
        });

        When("^user should be able to see BukaReksa \"([^\"]*)\" page displayed", (String pageName) -> {
            switch (pageName.toUpperCase()) {
                case "BERANDA":
                    bukalapak.bukaReksaHomePage().isBerandaPageDisplayed();
                    break;
                case "PRODUK":
                    bukalapak.bukaReksaProductPage().isProductPageDisplayed();
                    break;
                case "PORTOFOLIO":
                    bukalapak.bukaReksaPortofolioPage().isPortfolioPageDisplayed();
                    break;
                case "PROFILE":
                    bukalapak.bukaReksaProfilePage().isProfilePageDisplayed();
                    break;
                default:
                    Assert.fail(pageName.toUpperCase() + " isn't a page name");
            }
        });

        Then("user check whether BukaReksa onboarding is displayed", () -> {
            bukalapak.bukaReksaHomePage().validateBukaReksaOnboarding();
        });

        When("^user go back to home page from BukaReksa$", () -> {
            bukalapak.bukaReksaPage().goToHomePage();
        });

        //ini sama dengan user tap on back button in BukaReksa page
        When("user tap BukaReksa back button", () -> {
            bukalapak.bukaReksaPage().tapOnRexaBackBtn();
        });

        //region Non Investor
        Then("^user should see non investor registration popup", () -> {
            bukalapak.bukaReksaPage().verifyBwrPopupDisplayed();
        });

        When("^user taps on Daftar BukaReksa button in registration popup", () -> {
            bukalapak.bukaReksaPage().tapOnBwrRegisterButton();
        });

        Then("^user should be able to see bwr subscribe modal", () -> {
            bukalapak.bukaReksaPage().verifyBWRProductDetailPopupDisplayed();
        });

        And("^user tap on Langsung Beli button", () -> {
            bukalapak.bukaReksaPage().tapOnLangsungBeliButton();
        });
        //end region

        //region Registration Page
        Then("^user should be in BukaReksa registration page", () -> {
            bukalapak.bukaReksaPage().verifyBukaReksaRegistrationPage();
        });
        //endregion
      
        //region bukareksa checkout page
        And("^user tap on Bayar button in payment page$", () -> {
            bukalapak.bukaReksaPage().tapOnBayarButton();
        });

        And("user select \"([^\"]*)\" as bukareksa payment method", (String paymentMethod) -> {
            bukalapak.bukaReksaPage().chooseBukareksaPaymentMethod(paymentMethod);
            TransactionData.setPaymentMethod(paymentMethod);
        });

        And("^user type bukareksa voucher code \"([^\"]*)\"$", (String voucherCode) -> {
            bukalapak.bukaReksaPage().typeBukareksaVoucherCode(voucherCode);
        });

        Then("user should not be able to see checkout payment page", () -> {
           bukalapak.bukaReksaPage().checkPaymentPageNotDisplayed();
        });
        //end region
    }
}

