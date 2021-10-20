package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class FeedbackStepDefinitions extends TestInstrument implements En {
    public FeedbackStepDefinitions() {
        Given("user is in \"Feedback\" page", () -> {
            bukalapak.feedbackPage().userOnFeedbackPage();
        });

        Then("^offering to subscribe Super Seller is shown on Feedback page$", () -> {
            bukalapak.feedbackPage().verifyOfferingSuperSeller();
        });

        Then("^user switches to \"([^\"]*)\"$", (String menuFilter) -> {
            bukalapak.feedbackPage().clickFilterOptions(menuFilter);
        });
    }
}
