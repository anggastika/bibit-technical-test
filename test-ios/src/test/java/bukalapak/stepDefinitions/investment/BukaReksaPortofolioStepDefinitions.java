package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import org.junit.Assert;

public class BukaReksaPortofolioStepDefinitions extends TestInstrument implements En {

    public BukaReksaPortofolioStepDefinitions() {

        When("user verify total portfolio should be same in Homepage", () -> {
            bukalapak.bukaReksaPortofolioPage().validatePortfolioTotal();
        });

        When("^user tap on see transaction button in portfolio page", () -> {
            bukalapak.bukaReksaPortofolioPage().tapOnSeeTransactionBtn();
        });

        And("user verify total product portofolio value in Portofolio page", () -> {
            bukalapak.bukaReksaPortofolioPage().verifyProductPortofolio();
        });

        When("^user tap on subscription button in portfolio card list$", () -> {
            bukalapak.bukaReksaPortofolioPage().tapOnSubscriptionBtn();
        });

        Then("^user should be able to see the subscription option displayed$", () -> {
            bukalapak.bukaReksaPortofolioPage().checkSubscriptionOptionDisplayed();
        });

        When("^user choose \"([^\"]*)\" in subscription option$", (String option) -> {
            switch (option.toUpperCase()) {
                case "INVESTASI LAGI":
                    bukalapak.bukaReksaPortofolioPage().tapInvestasiLagi();
                    break;
                case "TRANSAKSI RUTIN":
                    bukalapak.bukaReksaPortofolioPage().tapTransaksiRutin();
                    break;
                default:
                    Assert.fail(option.toUpperCase() + " isn't a method name");
            }
        });

        When("^user tap on redeem button in portfolio card list", () -> {
            bukalapak.bukaReksaPortofolioPage().tapOnRedemptionBtn();
        });

        When("user tap on redeem button in \"([^\"]*)\" portofolio card", (String productType) -> {
            bukalapak.bukaReksaPortofolioPage().tapOnProductRedemptionBtn(productType);
        });

        //region Subscription Form
        Then("^user should not be able to see the subscription form displayed", () -> {
            bukalapak.bukaReksaPortofolioPage().checkSubscriptionFormNotDisplayed();
        });

        When("^user should be able to see the subscription form displayed", () -> {
            bukalapak.bukaReksaPortofolioPage().checkSubscriptionFormDisplayed();
        });

        When("^user input \"([^\"]*)\" into subscription field", (String nominal) -> {
            bukalapak.bukaReksaPortofolioPage().inputNominalPurchase(nominal);
        });

        When("^user check terms and conditions checkbox", () -> {
            bukalapak.bukaReksaPortofolioPage().tapOnTncDisclaimerCheckBox();
        });

        When("^user tap on continue button at subscription form", () -> {
            bukalapak.bukaReksaPortofolioPage().tapOnContinueBtn();
        });

        //end of region Subscription Form

        //region Redemption Form
        And("user verify redemption confirmation modal displayed", () -> {
            bukalapak.bukaReksaPortofolioPage().validateRedemptionConfirmationModal();
        });

        And("user tap on Jual produk investasi button", () -> {
            bukalapak.bukaReksaPortofolioPage().tapJualProdukInvestasiBtn();
        });

        And("user process product redemption with amount of \"([^\"]*)\"", (String amount) -> {
            bukalapak.bukaReksaPortofolioPage().processPortfolioRedemption(amount);
        });

        Then("user should see portfolio redemption modal", () -> {
            bukalapak.bukaReksaPortofolioPage().verifyPortofolioRedemptionForm();
        });

        When("user confirms instant product redemption process", () -> {
            bukalapak.bukaReksaPortofolioPage().confirmRedemptionForm();
        });

        Then("user validate voucher warning on redemption", () -> {
            bukalapak.bukaReksaPortofolioPage().validateVoucherCancellationWarning();
        });

        And("user verify important sales info page displayed", () -> {
            bukalapak.bukaReksaPortofolioPage().verifyImportantSalesInfoPageDisplayed();
        });

        //end of region Redemption Form

        //region strong auth page Bukareksa
        Then("user verify bukareksa strong auth page displayed", () -> {
            bukalapak.bukaReksaPortofolioPage().verifyRedemptionStrongAuthPage();
        });

        When("user input \"([^\"]*)\" password", (String passwordType) -> {
            bukalapak.bukaReksaPortofolioPage().selectPasswordforRedemption(passwordType);
        });

        And("user tap on strong auth Verifikasi button", () -> {
            bukalapak.bukaReksaPortofolioPage().tapVerifikasiButton();
        });
        //end of region strong auth page Bukareksa

        // Digital Widget
        And("user should see portfolio balance similar to digital widget balance", () -> {
            bukalapak.bukaReksaPage().validatePortfolioFromDigitalWidget();
        });
    }
}
