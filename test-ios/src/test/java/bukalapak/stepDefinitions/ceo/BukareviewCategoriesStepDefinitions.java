package bukalapak.stepDefinitions.ceo;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukareviewCategoriesStepDefinitions extends TestInstrument implements En {
    public BukareviewCategoriesStepDefinitions() {
        Given("user is in \"Bukareview Categories\" page", () -> {
            bukalapak.bukareviewCategoriesPage().userOnBukareviewCategoriesPage();
        });

        And("GADGET sub category is displayed", () -> {
            bukalapak.bukareviewCategoriesPage().verifySubCategoryDisplay();
        });

        When("user tap one of sub category", () -> {
            bukalapak.bukareviewCategoriesPage().tapSubCategoryButton();
        });

        And("GADGET REVIEW is displayed", () -> {
            bukalapak.bukareviewCategoriesPage().verifyGadgetReviewDisplay();
        });

        And("user can tap one of article in gadget category", () -> {
            bukalapak.bukareviewCategoriesPage().tapOneArticleGadgetReview();
        });

        And("GADGET TIPS is displayed", () -> {
            bukalapak.bukareviewCategoriesPage().verifyGadgetTipsDisplay();
        });

        And("GADGET RECOMMENDATION is displayed", () -> {
            bukalapak.bukareviewCategoriesPage().verifyGadgetRecommendationsDisplay();
        });

        And("GADGET HOW TO is displayed", () -> {
            bukalapak.bukareviewCategoriesPage().verifyGadgetHowToDisplay();
        });

        And("GADGET INFO is displayed", () -> {
            bukalapak.bukareviewCategoriesPage().verifyGadgetInfoDisplay();
        });
        And("GADGET INFOGRAPHIC is displayed", () -> {
            bukalapak.bukareviewCategoriesPage().verifyGadgetInfographicDisplay();
        });
    }
}

