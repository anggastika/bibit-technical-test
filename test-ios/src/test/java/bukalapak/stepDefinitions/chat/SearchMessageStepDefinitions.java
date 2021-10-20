package bukalapak.stepDefinitions.chat;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SearchMessageStepDefinitions extends TestInstrument implements En {

    public SearchMessageStepDefinitions() {

        Then("user verify search result contains user \"([^\"]*)\" and text \"([^\"]*)\"", (String username, String message) -> {
            bukalapak.searchMessagePage().verifySearchResult(username, message);
        });

        When("user click search result with message \"([^\"]*)\"", (String message) -> {
            bukalapak.searchMessagePage().clickSearchResultByMessage(message);
        });

        When("user search message with \"([^\"]*)\" text", (String message) -> {
            bukalapak.searchMessagePage().typeAndEnterValue("chat_list_search_field", message);
        });
    }
}
