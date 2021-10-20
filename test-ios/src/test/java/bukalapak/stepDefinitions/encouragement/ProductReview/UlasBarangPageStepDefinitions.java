package bukalapak.stepDefinitions.encouragement.ProductReview;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class UlasBarangPageStepDefinitions extends TestInstrument implements En {

    public UlasBarangPageStepDefinitions() {

        Then("^user is on review list page$", () -> {
            bukalapak.productReviewFormPage().reviewListPage();
        });

        When("^user click on Ubah Ulasan$", () -> {
            bukalapak.productReviewFormPage().clickUbahUlasan();
        });

        And("^user should see submitted review on review list page$", () -> {
            bukalapak.productReviewFormPage().reviewListPage();
        });
    }
}

