package bukalapak.stepDefinitions.ceo;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukareviewArticleDetailStepDefinitions extends TestInstrument implements En {
    public BukareviewArticleDetailStepDefinitions() {

        Then("user redirect to article detail page", () -> {
            bukalapak.bukareviewArticleDetailPage().userOnBukaReviewArticleDetail();
            bukalapak.bukareviewArticleDetailPage().verifyArticleTitleDisplayAsExpected();
        });

        And("user can see author article", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyAuthorDisplay();
        });

        And("user can see category article", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyCategoryDisplay();
        });

        And("user can see article image", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyArticleImageDisplay();
        });

        And("user can see recommendation product", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyRecommendationProductDisplay();
        });

        And("user can see tag section in product detail", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyTagDisplay();
        });

        When("user can tap tag button", () -> {
            bukalapak.bukareviewArticleDetailPage().tapTagsButton();
        });

        And("user can see review recommendation section", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyReviewRecommendationDisplay();
        });

        // for article indepth
        And("user redirect to article indepth page", () -> {
            bukalapak.bukareviewArticleDetailPage().userOnBukaReviewArticleDetail();
            bukalapak.bukareviewArticleDetailPage().verifyArticleTitleDisplayAsExpected();
            bukalapak.bukareviewArticleDetailPage().verifyProductTitleAndPriceDisplay();
        });

        And("check title and price of article indepth", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyProductTitleAndPriceDisplay();
        });

        And("check review of article indepth", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyRatingStarDisplay();
            bukalapak.bukareviewArticleDetailPage().verifyRatingDetailDisplay();
        });

        And("check button Lihat Barang", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyLihatBarangButtonDisplay();
        });

        And("User click Lihat Barang button", () -> {
            bukalapak.bukareviewArticleDetailPage().tapLihatBarangButton();
        });

        And("check pros and cont", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyProsDisplay();
            bukalapak.bukareviewArticleDetailPage().verifyContDisplay();
        });

        // for article interactive
        And("user redirect to interactive page article", () -> {
            bukalapak.bukareviewArticleDetailPage().userOnBukaReviewArticleDetail();
            bukalapak.bukareviewArticleDetailPage().verifyArticleTitleDisplayAsExpected();
            bukalapak.bukareviewArticleDetailPage().verifyTagInteractiveDisplay();
        });

        And("Click interactive icon on image", () -> {
            bukalapak.bukareviewArticleDetailPage().tapTagInteractiveIcon();
        });

        When("user click one of interactive product", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyProductListInteractiveDisplay();
            bukalapak.bukareviewArticleDetailPage().tapProductListInteractive();
        });

        Then("redirect to Detail Product page", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyProductDetailInteractiveDisplay();
        });

        When("user click Lihat Barang", () -> {
            bukalapak.bukareviewArticleDetailPage().tapLihatBarangInteractiveButton();
        });

        Then("user navigate to product detail page", () -> {
            bukalapak.bukareviewArticleDetailPage().verifyProductDetailDisplay();
        });

        And("user back to article interactive", () -> {
            bukalapak.productDetailsPage().tapBackButton();
            bukalapak.bukareviewArticleDetailPage().tapBackButton();
        });

    }
}
