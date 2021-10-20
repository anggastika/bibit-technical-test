package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import bukalapak.data.LuckyDealsData;
import cucumber.api.java8.En;

public class SerbuSeruStepDefinitions extends TestInstrument implements En {

    public SerbuSeruStepDefinitions() {
        And("^user tap Serbu button on serbu seru screen$", () -> {
            bukalapak.serbuSeruPage().tapSerbuButton();
        });

        And("user is on serbu seru main page", () -> {
            bukalapak.serbuSeruPage().isOnSerbuSeruPage();
        });

        When("^user switch remind me icon to (on|off)$", (String state) -> {
            bukalapak.serbuSeruPage().swicthRemindMeIcon(state);
        });

        Then("^user verify remind me icon is (on|off)$", (String state) -> {
            bukalapak.serbuSeruPage().validateSnacbarRemindMe(state);
        });

        And("^user verify filter serbuseru button with label \"([^\"]*)\"$", (String filter) -> {
            bukalapak.serbuSeruPage().validateFilterSerbuSeruButton(filter);
        });

        When("user tap on history icon", () -> {
            bukalapak.serbuSeruPage().tapHistoryIcon();
        });

        Then("^user verify missed history on history page$", () -> {
            bukalapak.serbuSeruPage().validateMelesetOnHistory();
        });

        Given("user on product page which is eligible for VA", () -> {
            bukalapak.serbuSeruPage().isOnSerbuSeruPage();
            bukalapak.serbuSeruPage().tapOnEligibleProductForVA();
        });

        When("user serbu product on product detail page using VA", () -> {
            bukalapak.serbuSeruPage().serbuProductOnProductDetailPageUsingVA();
        });

        And("serbu has succeeded", () -> {
            bukalapak.serbuSeruPage().serbuHasSucceeded();
        });

        And("user tap Lihat Detail Tagihan button", () -> {
            bukalapak.serbuSeruPage().tapLihatDetailTagihan();
        });

        Then("user is on transaction detail page", () -> {
            bukalapak.serbuSeruPage().isOnSerbuSeruTransactionDetailPage();
        });

        When("^user tap filter \"([^\"]*)\" on serbuseru$", (String filter) -> {
            bukalapak.serbuSeruPage().tapFilterRefund(filter);
        });

        Then("^user verify information refund to donasi$", () -> {
            bukalapak.serbuSeruPage().validateInfoRefundToDonasi();
        });

        Then("^user verify information refund to DANA/Saldo", () -> {
            bukalapak.serbuSeruPage().validateInfoRefundToDANASaldo();
        });

        When("^user go to section serbu seru and login using \"([^\"]*)\" credential$", (String credential) -> {
            bukalapak.homePage().tapSerbuSeruShowAll();
            bukalapak.loginPage().userOnLoginPage();
            bukalapak.loginPage().loginAs(credential);
            bukalapak.serbuSeruPage().isOnSerbuSeruPage();
        });

        Then("^user verify information section serbu seru in product detail$", () -> {
            bukalapak.serbuSeruPage().validateInformationProductPdp();
        });

        Then("^user verify faq serbu seru popup$", () -> {
            bukalapak.serbuSeruPage().validateFaqSerbuSeru();
        });

        Then("^user get DANA Freeze Information", () -> {
            bukalapak.serbuSeruPage().selectPaymentMethod();
            bukalapak.serbuSeruPage().tapAktifkanButton();
            bukalapak.serbuSeruPage().validateInfoDANAFreeze();

        });

        When("^user go to product detail serbu seru active event via deeplink$", () -> {
            bukalapak.iOSBasePage().openDeepLink("/serbu-seru?product_id="+ LuckyDealsData.getProductIdLuckyDeals());
        });

        When("^user tap Serbu button on pdp serbu seru$", () -> {
            bukalapak.serbuSeruPage().tapSerbuButtonOnPDP();
        });

        When("^user input (\\d) bid serbuseru$", (String bid) -> {
            bukalapak.serbuSeruPage().typeBidSerbuSeru(bid);
        });

        Then("^user can see the Consent pop-up donation option$", () -> {
            bukalapak.serbuSeruPage().validateConsentPopupDonasi();
        });

        Then("^user verify option for refund Saldo/DANA (enabled|disabled)$", (String option) -> {
            bukalapak.serbuSeruPage().validateOptionSaldoDana(option);
        });

        And("user tap Serbu Gratis Referral button", () -> {
            bukalapak.serbuSeruPage().tapSerbuGratisReferralButton();
        });

        Then("^user select top up DANA button", () -> {
            bukalapak.serbuSeruPage().selectPaymentMethod();
            bukalapak.serbuSeruPage().tapTopUpDANAButton();
        });

        Then("^user verify menunggu pembayaran history on history page$", () -> {
            bukalapak.serbuSeruPage().validatePendingOnHistory();
        });

        And("user tap Setuju and Lanjutkan button on pdp serbu seru", () -> {
           bukalapak.serbuSeruPage().tapSetujuAndLanjutkanButton();
        });

        And("user verify successful payment using DANA for serbu seru", () -> {
            bukalapak.serbuSeruPage().verifySuccessfulPaidSerbuSeruDANA();
        });
    }
}
