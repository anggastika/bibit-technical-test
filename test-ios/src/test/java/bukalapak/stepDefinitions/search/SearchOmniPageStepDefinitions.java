package bukalapak.stepDefinitions.search;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SearchOmniPageStepDefinitions extends TestInstrument implements En {

    public SearchOmniPageStepDefinitions() {

        Then("^user click on Omnisearch field and search \"([^\"]*)\"$", (String keywordType) -> {
            bukalapak.searchOmniPage().searchProduct(keywordType);
        });

        Then("^user click on Omnisearch field$", () -> {
            bukalapak.searchOmniPage().clickOnOmniSearchField();
        });

        And("^user select from keyword \"([^\"]*)\"$", (String keywordType) -> {
            bukalapak.searchOmniPage().selectKeywordFromOmniPage(keywordType);
        });

        Then("^user click on Omnisearch field and type \"([^\"]*)\"$", (String keywords) -> {
            bukalapak.searchOmniPage().typeProductName(keywords);
        });

        And("^user select from \"([^\"]*)\"$", (String keywordType) -> {
            bukalapak.searchOmniPage().selectKeywordFromOmniPage(keywordType);
        });

        And("user click on lihat history selengkapnya", () -> {
            bukalapak.searchOmniPage().clickLihatSelengkapnya();
        });

        And("^user type search \"([^\"]*)\" without enter$", (String searchKeyword) -> {
            bukalapak.searchOmniPage().typeProductNameWithoutEnter(searchKeyword);
        });

        And("^user click lihat semua text link", () -> {
            bukalapak.searchOmniPage().clickLihatSemua();
        });

        And("^user should be redirect to product listing$", () -> {
            bukalapak.searchOmniPage().verifyOnProductListingPage();
        });

        When("^user type \"([^\"]*)\" on Omnisearch$", (String keyword) -> {
            bukalapak.searchOmniPage().typeKeyword(keyword);
        });

        Then("^user verify \"([^\"]*)\" suggestion is on the list$", (String product) -> {
            bukalapak.searchOmniPage().verifySuggestion(product);
        });

        And("^user tap the first item on omnisearch suggestion$", () -> {
            bukalapak.searchOmniPage().tapDopeSuggestion();
        });

        And("^user tap item with category on omnisearch suggestion$", () -> {
            bukalapak.searchOmniPage().tapItemWithCategorySuggestion();
        });

        And("^user tap \"([^\"]*)\" popular position \"([^\"]*)\"$", (String type, Integer index) -> {
            bukalapak.searchOmniPage().tapPopularSuggestion(type, index);
        });

        And("^user should see catalog suggestions$", () -> {
            bukalapak.searchOmniPage().verifyCatalogSuggestions();
        });

        And("^user should see Riwayat Pencarian on omnisearch$", () -> {
            bukalapak.searchOmniPage().verifyHistory();
        });

        And("^user should see \"([^\"]*)\" in Riwayat Pencarian$", (String keyword) -> {
            bukalapak.searchOmniPage().verifyHistoryExists(keyword);
        });

        When("^user tap( and hold)? keyword history position \"([^\"]*)\"$", (String hold, Integer index) -> {
            bukalapak.searchOmniPage().tapHistory(hold != null, index);
        });

        When("^user tap Hapus Semua on history section$", () -> {
            bukalapak.searchOmniPage().tapRemoveAllHistory();
        });

        And("^user see remove( all)? history confirmation modal$", (String all) -> {
            bukalapak.searchOmniPage().verifyModalRemoveHistory(all != null);
        });

        And("^user confirm removing search histor(y|ies)$", (String condition) -> {
            bukalapak.searchOmniPage().removeHistory();
        });

        And("^user should not see removed keyword in keyword history$", () -> {
            bukalapak.searchOmniPage().validateHistory(false);
        });

        And("^user should not see Riwayat Pencarian", () -> {
            bukalapak.searchOmniPage().validateHistory(true);
        });

        When("^user tap Lihat Semua seller$", () -> {
            bukalapak.searchOmniPage().tapViewAllSeller();
        });

        When("^user tap campaign in placeholder suggestion$", () -> {
            bukalapak.searchOmniPage().tapCampaignSuggestion();
        });

        And("^user tap continuation icon on keyword suggestion position \"([^\"]*)\" and press enter on keyboard$", (Integer position) -> {
            bukalapak.searchOmniPage().tapKeywordContinuationAndSearch(position);
        });

        And("^user should be redirected away from omnisearch page$", () -> {
            bukalapak.searchOmniPage().verifyNotInOmnipage();
        });
    }
}
