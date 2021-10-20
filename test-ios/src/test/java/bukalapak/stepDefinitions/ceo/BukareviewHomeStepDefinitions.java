package bukalapak.stepDefinitions.ceo;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukareviewHomeStepDefinitions extends TestInstrument implements En {
    private static String articleDefault = "3 Monopod Terbaik dari Fotopro";
    private static String articleIndepth = "Review HP Joy 2";
    private static String articleInteractive = "Simak Model Celana Jeans yang Tepat";

    public BukareviewHomeStepDefinitions() {

        Given("user is in \"Bukareview\" page", () -> {
            bukalapak.bukareviewHomePage().userOnBukareviewHomePage();
        });

        When("user search article default", () -> {
            bukalapak.bukareviewHomePage().enterSearchTextField(articleDefault);
        });

        When("user search article indepth", () -> {
            bukalapak.bukareviewHomePage().enterSearchTextField(articleIndepth);
        });

        When("user search article interactive", () -> {
            bukalapak.bukareviewHomePage().enterSearchTextField(articleInteractive);
        });

        And("user can check Top Stories section", () -> {
            bukalapak.bukareviewHomePage().verifyTopStoriesDisplay();
        });

        When("user can click author in Top Stories section", () -> {
            bukalapak.bukareviewHomePage().verifyTopStoriesDisplay();
            bukalapak.bukareviewHomePage().tapAuthor();
        });

        When("user can tap one of article", () -> {
            bukalapak.bukareviewHomePage().tapOneArticle();
        });

        And("user tap back button from article page", () -> {
            bukalapak.bukareviewArticleDetailPage().tapBackButton();
        });

        When("user tap Gadget category in home bukareview", () -> {
            bukalapak.bukareviewHomePage().tapGadgetCategory();
        });

        And("user can check Latest Stories section", () -> {
            bukalapak.bukareviewHomePage().verifyLatestStoriesDisplay();
        });

        When("user tap view more in latest stories", () -> {
            bukalapak.bukareviewHomePage().tapLatestStoriesViewMore();
        });

        And("user can check Top Review section", () -> {
            bukalapak.bukareviewHomePage().verifyTopReviewDisplay();
        });

        And("user can check Top Tips section", () -> {
            bukalapak.bukareviewHomePage().verifyTopTipsDisplay();
        });

        And("user can check Top Recommendation", () -> {
            bukalapak.bukareviewHomePage().verifyTopRecommendationDisplay();
        });

        And("user can check Top How To section", () -> {
            bukalapak.bukareviewHomePage().verifyTopHowToDisplay();
        });

        And("user can check Top Info section", () -> {
            bukalapak.bukareviewHomePage().verifyTopInfoDisplay();
        });

        And("user can check social media in footer", () -> {
            bukalapak.bukareviewHomePage().footerBukaReviewLogoDisplay();
            bukalapak.bukareviewHomePage().verifySocialMediaFooterDisplay();
        });
    }
}
