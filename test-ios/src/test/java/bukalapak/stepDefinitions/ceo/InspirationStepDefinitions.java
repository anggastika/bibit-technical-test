package bukalapak.stepDefinitions.ceo;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;


public class InspirationStepDefinitions extends TestInstrument implements En {
    public InspirationStepDefinitions() {
        Given("user is in \"inspiration\" page", () -> {
            bukalapak.inspirationPage().userOnInspirationPage();
        });

        When("user click inspiration name at (\\d+) inspiration post", (Integer index) -> {
            bukalapak.inspirationPage().clickInspirationNameAtIndex(index);
        });

        When("admin publish an inspiration post contain for specific influencer", (DataTable arg0) -> {
            bukalapak.inspirationPage().publishAnInspiration(arg0);
        });

        When("user click tag (\\d+) on inspiration post", (Integer tagIndex) -> {
            bukalapak.inspirationPage().clickTagDetail(tagIndex);
        });

        When("user able to open and hide inspiration post description", () -> {
            bukalapak.inspirationPage().checkExpandableButton();
        });

        When("user filtering inspiration post by specific category", () -> {
            bukalapak.inspirationPage().filteringByCategoryInspirationPost();
        });

        When("inspiration posts on inspiration page contains valid posts by specific category", () -> {
            bukalapak.inspirationPage().checkInspirationPostFilterdByCategory();
        });

        When("inspiration posts on inspiration page sorted by Terpopuler", () -> {
            bukalapak.inspirationPage().checkSortingInspirationPost();
        });

        When("user like at (\\d+) inspiration post on inspiration page", (Integer index) -> {
            bukalapak.iOSBasePage().swipeDownToElement("inspiration_name_text");
            bukalapak.inspirationPage().likeAtPost(index);
        });

        When("user get total like count on (\\d+) inspiration post", (Integer index) -> {
            bukalapak.inspirationPage().getTotalLikeCountOnSpecificPost(index);
        });

        When("total like at (\\d+) inspiration posts on inspiration page is \"([^\"]*)\"", (Integer index, String status) -> {
            bukalapak.inspirationPage().checkTotalLikeCount(index, status);
        });

        When("user is on product detail page or brand page", () -> {
            bukalapak.inspirationPage().checkRelatedPageToInspirationPost();
        });

        When("inspiration page contains end of list", () -> {
            bukalapak.inspirationPage().assertTrue(bukalapak.iOSBasePage().isElementVisible("inspiration_end_of_line_text"), "Inspiration page not contains end of list");
        });
    }
}
