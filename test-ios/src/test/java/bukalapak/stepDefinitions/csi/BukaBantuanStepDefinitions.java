package bukalapak.stepDefinitions.csi;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaBantuanStepDefinitions extends TestInstrument implements En {

    public BukaBantuanStepDefinitions() {
        Given("user is in \"BukaBantuan\" page", () -> {
            bukalapak.bukaBantuanWebviewPage().userOnBukaBantuanPage();
        });

        When("user search transaction with \"([^\"]*)\" text in BukaBantuan", (String keyword) -> {
            bukalapak.bukaBantuanWebviewPage().searchTransaction(keyword);
        });

        When("user tap sought transaction with \"([^\"]*)\" status in BukaBantuan", (String keyword) -> {
            bukalapak.bukaBantuanPage().tapSoughtTransaction(keyword);
        });

        Then("user is on Pilih Masalah page", () -> {
            bukalapak.bukaBantuanWebviewPage().userOnPilihMasalahPage();
        });

        When("user choose \"([^\"]*)\" problem in BukaBantuan", (String keyword) -> {
            bukalapak.bukaBantuanPage().tapProblem(keyword);
        });

        Then("user is on \"([^\"]*)\" problem detail page", (String keyword) -> {
            bukalapak.bukaBantuanPage().userOnDetailMasalahPage(keyword);
        });

        Then("user is on Form Bantuan page", () -> {
            bukalapak.bukaBantuanWebviewPage().userOnFormBantuanPage();
        });

        Then("user is on Success Submit page", () -> {
            bukalapak.bukaBantuanWebviewPage().userOnSuccessSubmitPage();
        });

        When("user go to BukaBantuan from akun page", () -> {
            bukalapak.akunPage().clickAkunMenu();
            bukalapak.akunPage().scrollAndClickBukaBantuan();
        });

        When("^user choose transaction with status \"([^\"]*)\" in BukaBantuan$", (String keyword) -> {
            bukalapak.bukaBantuanWebviewPage().goToTransaction();
            bukalapak.bukaBantuanWebviewPage().searchTransaction("noTransaksi");
            bukalapak.bukaBantuanWebviewPage().tapSoughtTransaction(keyword);
        });

        When("^user choose \"([^\"]*)\" form in BukaBantuan$", (String keyword) -> {
            bukalapak.bukaBantuanWebviewPage().tapProblem(keyword);
            bukalapak.bukaBantuanWebviewPage().tapFormBantuan();
        });

        And("^user submit complaint form$", () -> {
            bukalapak.iOSBasePage().swipeUpToElement("bukabantuan_kirim_button");
            bukalapak.iOSBasePage().tapElement("bukabantuan_form_tnc_checkbox");
            bukalapak.iOSBasePage().tapElement("bukabantuan_kirim_button");
        });

        And("^user go to \"BukaBantuan\" page$", () -> {
            bukalapak.homePage().selectNavigationTab("Akun");
            bukalapak.akunPage().scrollAndClickBukaBantuan();
            bukalapak.bukaBantuanPage().userOnBukaBantuanPage();
        });

        And("^user choose category \"([^\"]*)\"$", (String keyword) -> {
            bukalapak.bukaBantuanWebviewPage().scrollAndClickCategory(keyword);
        });

        And("^user choose sub-category \"([^\"]*)\"$", (String keyword) -> {
            bukalapak.bukaBantuanWebviewPage().tapSubCategory(keyword);
        });

        And("^user make complaint form with problem \"([^\"]*)\"$", (String keyword) -> {
            bukalapak.bukaBantuanWebviewPage().searchTransaction("noTransaksi");
            bukalapak.bukaBantuanWebviewPage().tapSoughtTransaction("BELUM DIBAYAR");
            bukalapak.bukaBantuanWebviewPage().tapFormBantuan();
            bukalapak.bukaBantuanWebviewPage().userOnFormBantuanPage();
            bukalapak.bukaBantuanWebviewPage().makeComplaintForm(keyword);
        });

        And("^user search (FAQ|BukaBantuan) article \"([^\"]*)\" in BukaBantuan search$", (String category, String keyword) -> {
            bukalapak.bukaBantuanPage().searchArticle(keyword);
            bukalapak.bukaBantuanPage().tapFirstArticleSuggestion(category);
        });

        Then("^display \"([^\"]*)\" article in FAQ page$", (String title) -> {
            bukalapak.faqPage().validateArticle(title);
        });

        Then("^display \"([^\"]*)\" article in BukaBantuan page$", (String keywords) -> {
            bukalapak.bukaBantuanPage().validateArticle(keywords);
        });

        And("^user is in \"transaction\" BukaBantuan page$", () -> {
            bukalapak.bukaBantuanWebviewPage().goToTransaction();
            bukalapak.bukaBantuanWebviewPage().validateTransactionCard();
        });

        Then("^diplay transactions with \"([^\"]*)\" text in BukaBantuan$", (String keywords) -> {
            bukalapak.bukaBantuanWebviewPage().validateFirstTransaction(keywords);
        });

        And("^user filter transaction state with \"([^\"]*)\" option in BukaBantuan page$", (String keywords) -> {
            bukalapak.bukaBantuanWebviewPage().filterTransaction(keywords);
        });

        Then("^diplay transactions with \"([^\"]*)\" filter in BukaBantuan$", (String keywords) -> {
            bukalapak.bukaBantuanWebviewPage().validateFilterTransaction(keywords);
        });

        And("^user make complaint \"([^\"]*)\" form with problem \"([^\"]*)\" in BukaBantuan$", (String category, String problem) -> {
            bukalapak.bukaBantuanWebviewPage().tapProblem(category);
            bukalapak.bukaBantuanWebviewPage().tapFormBantuan();
            bukalapak.bukaBantuanWebviewPage().makeComplaintForm(problem);
        });

        And("^user fill problems with \"([^\"]*)\"$", (String keyword) -> {
            bukalapak.bukaBantuanWebviewPage().chooseProblemForm(keyword);
        });

        And("^user go to \"BukaBantuan\" webview page$", () -> {
            bukalapak.homePage().selectNavigationTab("Akun");
            bukalapak.akunPage().scrollAndClickBukaBantuan();
            bukalapak.bukaBantuanWebviewPage().userOnBukaBantuanPage();
        });

        And("^user open \"([^\"]*)\" category BukaBantuan$", (String category) -> {
            bukalapak.bukaBantuanWebviewPage().expandCategory(category);
        });

        And("^user choose \"([^\"]*)\" subcategory BukaBantuan$", (String subcategory) -> {
            bukalapak.bukaBantuanWebviewPage().chooseSubCategory(subcategory);
        });

        And("^user choose first transaction BukaBantuan$", () -> {
            bukalapak.bukaBantuanWebviewPage().tapFirstTransaction();
        });

        Then("^user is on \"([^\"]*)\" article page$", (String title) -> {
            bukalapak.bukaBantuanWebviewPage().validateArticlePage(title);
        });

        When("^user create livechat complaint$", () -> {
            bukalapak.bukaBantuanWebviewPage().submitLivechat();
        });

        Then("^user is in livechat chatbot session$", () -> {
            bukalapak.bukaBantuanWebviewPage().validateChatbotSession();
        });

        When("^user choose chatbot \"([^\"]*)\" option$", (String option) -> {
            bukalapak.bukaBantuanWebviewPage().chooseChatbotOption(option);
        });

        Then("^chatbot gave \"([^\"]*)\" response message$", (String message) -> {
            bukalapak.bukaBantuanWebviewPage().validateLivechatResponse(message);
        });

        When("^user ends chatbot session from chatbot option$", () -> {
            bukalapak.bukaBantuanWebviewPage().endChatbotSession();
        });

        Then("^livechat session is ended$", () -> {
            bukalapak.bukaBantuanWebviewPage().validateScoringLivechatButton();
        });

        And("^user choose last created transaction BukaBantuan$", () -> {
            bukalapak.bukaBantuanWebviewPage().searchTransaction("lastTransaction");
            bukalapak.bukaBantuanWebviewPage().tapLastTransaction();
        });

        Then("^user is in livechat regular session$", () -> {
            bukalapak.bukaBantuanWebviewPage().validateLivechatSession();
        });

        When("^user send message \"([^\"]*)\" in livechat BukaBantuan$", (String message) -> {
            bukalapak.bukaBantuanWebviewPage().sendMessageLivechat(message);
        });
    }
}
