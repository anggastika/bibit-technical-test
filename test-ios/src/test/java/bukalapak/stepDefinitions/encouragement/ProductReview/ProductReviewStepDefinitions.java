package bukalapak.stepDefinitions.encouragement.ProductReview;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ProductReviewStepDefinitions extends TestInstrument implements En {

    public ProductReviewStepDefinitions() {

        When("^user tap on Dengan Foto filter$", () -> {
            bukalapak.productReviewPage().filterImage();
        });

        When("^user tap on Dengan Deskripsi review filter$", () -> {
            bukalapak.productReviewPage().filterDesc();
        });

        When("^user tap on rating review filter$", () -> {
            bukalapak.productReviewPage().filterRating();
        });

        When("^user tap on Semua review filter$", () -> {
            bukalapak.productReviewPage().allFilter();
        });

        Then("^user should see \"([^\"]*)\"$", (String filtername) -> {
            bukalapak.productReviewPage().verifyReviewFilterName(filtername);
        });

        When("^user filtering by \"([^\"]*)\" that contain review from user$", (String filter) -> {
            bukalapak.productReviewPage().selectFilterReview(filter);
        });

        Then("^user should \"([^\"]*)\" content of review$", (String expected) -> {
            bukalapak.productReviewPage().verifyContentReviewFilter(expected);
        });

        Then("^user verify total likes in product review updated$", () -> {
            bukalapak.productReviewPage().validateTotalLikesIsChanged();
        });

        Then("^user verify total dislikes in product review updated$", () -> {
            bukalapak.productReviewPage().validateTotalDislikesIsChanged();
        });

        When("^user check current likes value$", () -> {
            bukalapak.productReviewPage().getCurrentLikes();
        });

        When("^user check current dislikes value$", () -> {
            bukalapak.productReviewPage().getCurrentDislikes();
        });

        And("^user click like icon$", () -> {
            bukalapak.productReviewPage().clickLikeIcon();
        });

        And("^user click dislike icon$", () -> {
            bukalapak.productReviewPage().clickDislikeIcon();
        });

        And("^user tap on reviewer username$", () -> {
            bukalapak.productReviewPage().clickOnReviewerName();
        });

        Then("^user is in Product Review page$", () -> {
            bukalapak.productReviewPage().userOnProductReviewPage();
        });
    }
}
