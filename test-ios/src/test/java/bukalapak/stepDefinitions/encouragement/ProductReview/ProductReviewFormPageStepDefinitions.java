package bukalapak.stepDefinitions.encouragement.ProductReview;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ProductReviewFormPageStepDefinitions extends TestInstrument implements En {

    public ProductReviewFormPageStepDefinitions() {

        Then("^user is on review form page$", () -> {
            bukalapak.productReviewFormPage().assertReviewForm();
        });

        When("^user tap review rating$", () -> {
            bukalapak.productReviewFormPage().tapRating();
        });

        And("^user clear review description text field$", () -> {
            bukalapak.productReviewFormPage().clearTextField();
        });

        And("^user choose \"([^\"]*)\" as a review topic$", (String topic) -> {
            bukalapak.productReviewFormPage().chooseReviewTopic(topic);
        });

        Then("^user should see keyword selected appear on textfield$", () -> {
            bukalapak.productReviewFormPage().assertTopicSelected();
        });

        When("^user upload review image$", () -> {
            bukalapak.productReviewFormPage().uploadImage();
        });

        And("^user delete uploaded image$", () -> {
            bukalapak.productReviewFormPage().deleteImage();
        });

        And("^user tap on anonymous check box$", () -> {
            bukalapak.productReviewFormPage().tapOnAnonymousCheckBox();
        });

        And("^user tap on Kirim button$", () -> {
            bukalapak.productReviewFormPage().tapOnKirimButton();
        });
    }
}
