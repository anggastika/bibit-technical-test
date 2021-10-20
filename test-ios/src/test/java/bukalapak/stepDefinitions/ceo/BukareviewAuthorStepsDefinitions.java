package bukalapak.stepDefinitions.ceo;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukareviewAuthorStepsDefinitions extends TestInstrument implements En {
    public BukareviewAuthorStepsDefinitions() {
        Then("user in author bukareview page", () -> {
            bukalapak.bukareviewAuthorPage().userOnAuthorPage();
        });

        And("user can see author section", () -> {
            bukalapak.bukareviewAuthorPage().verifyAuthorName();
            bukalapak.bukareviewAuthorPage().verifyAuthorRole();
            bukalapak.bukareviewAuthorPage().verifyAuthorDesc();
            bukalapak.bukareviewAuthorPage().verifyAuthorAmountArticle();
        });

        And("user can see article list in author page", () -> {
            bukalapak.bukareviewAuthorPage().verifyListArticle();
        });

        When("user click one of article", () -> {
            bukalapak.bukareviewAuthorPage().tapArticleTitle();
        });

        When("user tap back button from article detail", () -> {
            bukalapak.bukareviewArticleDetailPage().tapBackButton();
        });

        And("user scroll down the page for verify author section", () -> {
            bukalapak.bukareviewAuthorPage().verifyAuthorSectionAfterScroll();
        });
    }
}
