package bukalapak.stepDefinitions.wow;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * @author Andi Maryono
 */

public class CatalogReviewListingPageStepDefinitions extends TestInstrument implements En {
    public CatalogReviewListingPageStepDefinitions() {
        Given("user is in \"catalog_review_listing\" page", () -> {
            bukalapak.catalogReviewListingPage().userOnCatalogReviewListingPage();
        });

        When("catalog review listing filtered by \"([^\"]*)\"$", (String filter) -> {
            bukalapak.catalogReviewListingPage().verifyFilteredContent(filter);
        });

        When("user filtering catalog review listing by \"([^\"]*)\"$", (String filter) -> {
            bukalapak.catalogReviewListingPage().filterReviewListing(filter);
        });
    }
}
