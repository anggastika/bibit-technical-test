package bukalapak.stepDefinitions.ceo;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaReviewTagTypeSubCategoryPageStepsDefinitions extends TestInstrument implements En {
    public BukaReviewTagTypeSubCategoryPageStepsDefinitions() {
        Then("user is in tag page", () -> {
            bukalapak.bukaReviewTagTypeSubCategoryPage().userOnBukaReviewTagTypeSubCategoryPage();
            bukalapak.bukaReviewTagTypeSubCategoryPage().verifyTitleTagDisplayAsExpected();
        });

        Then("user is in type page", () -> {
            bukalapak.bukaReviewTagTypeSubCategoryPage().userOnBukaReviewTagTypeSubCategoryPage();
            bukalapak.bukaReviewTagTypeSubCategoryPage().verifyTitleTypeDisplayAsExpected();
        });

        Then("user is in subcategory page", () -> {
            bukalapak.bukaReviewTagTypeSubCategoryPage().userOnBukaReviewTagTypeSubCategoryPage();
            bukalapak.bukaReviewTagTypeSubCategoryPage().verifyTitleSubCategoryDisplayAsExpected();
        });

        When("user tap one article in tag type or subcategory page", () -> {
            bukalapak.bukaReviewTagTypeSubCategoryPage().tapOneOfArticle();
        });
    }
}
