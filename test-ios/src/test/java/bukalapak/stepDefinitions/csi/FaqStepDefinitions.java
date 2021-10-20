package bukalapak.stepDefinitions.csi;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class FaqStepDefinitions extends TestInstrument implements En {

    public FaqStepDefinitions() {
        Given("user navigate to faq page from Akun page", () -> {
            bukalapak.homePage().goToFaq();
        });

        Then("^user is on FAQ page$", () -> {
            bukalapak.faqPage().userOnFaqPage();
        });

        And("^user fill in \"([^\"]*)\" in the FAQ search field$", (String keywords) -> {
            bukalapak.faqPage().searchArticle(keywords);
        });
        Then("^display FAQ article auto suggest$", () -> {
            bukalapak.faqPage().validateAutoSuggest();
        });

        When("^click one of FAQ article in autosuggest$", () -> {
            bukalapak.faqPage().tapAutoSuggest();
        });

        Then("^display \"([^\"]*)\" article page$", (String keywords) -> {
            bukalapak.faqPage().validateArticle(keywords);
        });

        When("^user tap category \"([^\"]*)\" in topik bantuan$", (String category) -> {
            bukalapak.faqPage().tapOnCategory(category);
        });

        Then("^user is on \"([^\"]*)\" category FAQ$", (String category) -> {
            bukalapak.faqPage().validateCategoryPage(category);
        });
    }
}
