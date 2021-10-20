package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedKeywordAturPromosiStepDefinition extends TestInstrument implements En {

    public PromotedKeywordAturPromosiStepDefinition() {

        Given("user is in \"Promoted Keyword Atur Promosi\" page", () -> {
            bukalapak.promotedKeywordAturPromosiPage().userOnPromotedKeywordAturPromosiPage();
        });

        When("^user removes all saved keywords", () -> {
            bukalapak.promotedKeywordAturPromosiPage().removeAllKeywords();
        });

        When("^user see (.*) as selected keyword$", (String keyword) -> {
            bukalapak.promotedKeywordAturPromosiPage().checkSelectedKeyword(keyword);
        });

        When("^user input recommendation bid for first keyword$", () -> {
            bukalapak.promotedKeywordAturPromosiPage().inputBidValue();
        });

        When("^user select \"([^\"]*)\" as Batas Budget$", (String option) -> {
            bukalapak.promotedKeywordAturPromosiPage().selectBudgetLimit(option);
        });

        And("^user input \"([^\"]*)\" as Batas Budget$", (String budget) -> {
            bukalapak.promotedKeywordAturPromosiPage().inputBudgetLimit(budget);
        });

        And("^user see the budget has been set as limited$", () -> {
            bukalapak.promotedKeywordAturPromosiPage().checkBudgetLimit();
        });

        When("^user select \"([^\"]*)\" as Periode Promosi$", (String option) -> {
            bukalapak.promotedKeywordAturPromosiPage().selectPeriodeLimit(option);
        });

        And("^user see the periode has been set as limited$", () -> {
            bukalapak.promotedKeywordAturPromosiPage().checkPeriodeLimit();
        });

        When("^user turn (on|off) Aktifkan Promosi toggle button$", (String status) -> {
            bukalapak.promotedKeywordAturPromosiPage().clickToggleButton(status);
        });

        And("^user input bid as \"([^\"]*)\" for \"([^\"]*)\"$", (String bid, String keyword) -> {
            bukalapak.promotedKeywordAturPromosiPage().inputSpecificBid(bid, keyword);
        });

        And("^user see bid for \"([^\"]*)\" has been changed to \"([^\"]*)\"$", (String keyword, String bid) -> {
            bukalapak.promotedKeywordAturPromosiPage().checkBidValue(keyword, bid);
        });

        And("^user see bid for \"([^\"]*)\" has been changed$", (String keyword) -> {
            bukalapak.promotedKeywordAturPromosiPage().checkKeywordBidField(keyword);
        });

        When("^user remove \"([^\"]*)\" from selected keywords$", (String keyword) -> {
            bukalapak.promotedKeywordAturPromosiPage().removeSpecificKeyword(keyword);
        });

        When("^user verify (.*) has been removed$", (String keyword) -> {
            bukalapak.promotedKeywordAturPromosiPage().checkRemovedKeyword(keyword);
        });

        And("^\"([^\"]*)\" sets \"([^\"]*)\" to be an (true|false) promoted keyword product with bid \"([^\"]*)\" and keyword \"([^\"]*)\"$", (String credentialUser, String productName, Boolean state, String bid, String keyword) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().patchPromotedKeywords(productName);
            bukalapak.apiCall().setPromotedKeywordProduct(productName, state, bid, keyword);
        });

        When("\"([^\"]*)\" get \"([^\"]*)\" is (true|false) promoted keyword product with bid value \"([^\"]*)\" and keyword \"([^\"]*)\"$", (String credentialUser, String productName, Boolean state, String bid, String keyword) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().patchPromotedKeywords(productName);
            bukalapak.apiCall().getPromotedKeywordProduct(productName, state, bid, keyword);
        });

        When("user taps Simpan Pengaturan button to set Promoted Keyword", () -> {
            bukalapak.promotedKeywordAturPromosiPage().tapSimpanPengaturanButton();
        });
    }
}
