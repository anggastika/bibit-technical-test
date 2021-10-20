package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedKeywordStepDefinition extends TestInstrument implements En {

    public PromotedKeywordStepDefinition() {

        Given("user is in \"Promoted Keyword\" page", () -> {
            bukalapak.promotedKeywordPage().userOnPromotedKeywordPage();
        });

        And("^user tap icon onboarding promoted keyword$", () -> {
            bukalapak.promotedKeywordPage().tapIconOnboarding();
        });

        And("^user is on Onboarding Promoted Keyword \"([^\"]*)\" page$", (String page) -> {
            bukalapak.promotedKeywordPage().userOnBannerPromotedkeyword(page);
        });

        And("^on Onboarding Promoted Keyword page, user tap on next button$", () -> {
            bukalapak.promotedKeywordPage().tapOnNextButton();
        });

        And("^on Onboarding Promoted Keyword page, user tap on prev button$", () -> {
            bukalapak.promotedKeywordPage().tapOnPrevButton();
        });

        And("^on Onboarding Promoted Keyword page, user tap on finish button$", () -> {
            bukalapak.promotedKeywordPage().tapOnFinishButton();
        });

        Given("user is in \"Bayar Pinjaman\" page", () -> {
            bukalapak.promotedKeywordPage().userOnPromotedKeywordLoanPaymentPage();
        });

        When("^user validate loan payment is visible (true|false)", (String state) -> {
            bukalapak.promotedKeywordPage().loanPayment(state);
        });

        When("^user scrolling down looking for \"([^\"]*)\" menus", (String elementName) -> {
            bukalapak.promotedKeywordPage().goToMenu(elementName);
        });

        When("^user will redirect to saldo page", () -> {
            bukalapak.promotedKeywordPage().saldoBukalapakPage();
        });

        When("^user see \"([^\"]*)\" on Promoted Keyword page$", (String nominalType) -> {
            bukalapak.promotedKeywordPage().setPromotedValue(nominalType);
        });

        Then("^user validate Sisa Budget \"([^\"]*)\" on Promoted Keyword page$", (String field) -> {
            bukalapak.promotedKeywordPage().userValidateNominal(field);
        });

        Then("^user search product as \"([^\"]*)\" on Promoted Keyword page$", (String product) -> {
            bukalapak.promBasePage().searchProduct(product);
        });

        Then("^user validate Promoted Keyword Budget Harian Status as (.*)$", (String state) -> {
            bukalapak.promotedKeywordPage().validatePromotedKeywordBudgetHarian(state);
        });

        Then("^user see Promoted Keyword Budget Saldo Pinjaman (Tidak )?Aktif$", (String state) -> {
            bukalapak.promotedKeywordPage().validatePromotedKeywordBudgetHarianLoan(state);
        });

        Then("^user should see \"([^\"]*)\" with status (Tidak )?Aktif$", (String productName, String state) -> {
            bukalapak.promotedKeywordPage().checkPromotedKeywordProductStatus(productName, state != null);
        });

        Then("^user click on (Aktifkan|Atur) button for \"([^\"]*)\"$", (String buttonName, String productName) -> {
            bukalapak.promotedKeywordPage().clickPromotedKeywordButton(buttonName, productName);
        });

        When("^user tap on (Riwayat Klik|Daftar Barang) tab on Promoted Keyword page$", (String tab) -> {
            bukalapak.promotedKeywordPage().tapPromotedKeywordTab(tab);
        });

        When("^user search product by inputting (.*)$", (String productName) -> {
            bukalapak.promotedKeywordPage().searchProduct(productName);
        });

        Then("^user see the mutation of \"([^\"]*)\" with keyword as \"([^\"]*)\" with bid value as \"([^\"]*)\"$", (String productName, String keyword, String bid) -> {
            bukalapak.promotedKeywordPage().checkMutationPromotedKeyword(productName, keyword, bid);
        });
    }
}
