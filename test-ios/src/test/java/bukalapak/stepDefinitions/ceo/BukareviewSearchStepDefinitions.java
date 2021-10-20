package bukalapak.stepDefinitions.ceo;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukareviewSearchStepDefinitions extends TestInstrument implements En {
    public BukareviewSearchStepDefinitions() {
        Then("user in bukareview search result page", () -> {
            bukalapak.bukareviewSearchPage().userOnBukareviewSearchPage();
        });

        And("user can see article search result display", () -> {
            bukalapak.bukareviewSearchPage().verifyResultSeachArticledisplay();
            bukalapak.bukareviewSearchPage().verifyImageArticleDisplay();
            bukalapak.bukareviewSearchPage().verifyAuthorArticleDisplay();
        });

        When("user tap one of article in search page", () -> {
            bukalapak.bukareviewSearchPage().tapOneOfArticleSearchResult();
        });
    }
}
