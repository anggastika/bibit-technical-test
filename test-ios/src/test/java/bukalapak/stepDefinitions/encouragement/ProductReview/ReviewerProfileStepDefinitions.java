package bukalapak.stepDefinitions.encouragement.ProductReview;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ReviewerProfileStepDefinitions extends TestInstrument implements En {

    public ReviewerProfileStepDefinitions() {

        Then("^user should see reviewer profile page$", () -> {
            bukalapak.reviewerProfilePage().assertReviewerPage();
        });

        And("^user should be on semua ulasan page$", () -> {
            bukalapak.reviewerProfilePage().assertSemuaUlasanPage();
        });

        And("^user back to reviewer profile page$", () -> {
            bukalapak.reviewerProfilePage().backToProfilePage();
        });

        Then("^user should be on pelapak page$", () -> {
            bukalapak.reviewerProfilePage().assertPelapakPage();
        });

        And("^user tap on Lihat Semua Ulasan link$", () -> {
            bukalapak.reviewerProfilePage().lihatSemuaLink();
        });

        And("^user tap on Kunjungi Lapaknya button$", () -> {
            bukalapak.reviewerProfilePage().tapOnKunjungiLapaknyaBtn();
        });

        And("^user open product detail page on reviewed product$", () -> {
            bukalapak.reviewerProfilePage().tapProductDetailCard();
        });
    }
}
