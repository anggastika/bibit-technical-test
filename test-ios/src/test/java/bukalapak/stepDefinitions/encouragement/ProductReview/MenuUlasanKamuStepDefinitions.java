package bukalapak.stepDefinitions.encouragement.ProductReview;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;


public class MenuUlasanKamuStepDefinitions extends TestInstrument implements En {

    public MenuUlasanKamuStepDefinitions() {
        And("^user should see notification bubble of product belum diulas$", () -> {
            bukalapak.menuUlasanKamuPage().verifyNotificationBubbleMenuUlasanKamu();
        });

        And("^user verify the number of notification bubble matching with notification bubble on akun page$", () -> {
            bukalapak.menuUlasanKamuPage().validateTotalProductBelumDiulasMatch();
        });

        And("^user should see list of product on Belum Diulas Tab$", () -> {
            bukalapak.menuUlasanKamuPage().verifyProductReviewInfo();
        });

        And("^user verify total notification bubble matching with total product belum diulas$", () -> {
            bukalapak.menuUlasanKamuPage().validateTotalNotificationmatchwithTotalProduct();
        });

        When("^user click Sudah Diulas Tab$", () -> {
            bukalapak.menuUlasanKamuPage().tabSudahDiulas();
        });

        And("^user verify ulasan already updated$", () -> {
            bukalapak.menuUlasanKamuPage().verifyUlasanUpdated();
        });

        Then("^user should see empty state barang Sudah diulas$", () -> {
            bukalapak.menuUlasanKamuPage().verifyEmptystateSudahDiulas();
        });

        And("^user is in Menu Ulasan Kamu page$", () -> {
            bukalapak.menuUlasanKamuPage().userOnMenuUlasanKamuPage();
        });

        And("^user tap on Ubah Ulasan button on Menu Ulasan Kamu page$", () -> {
            bukalapak.menuUlasanKamuPage().clickUbahUlasanButton();
        });

        And("^user click rating ulasan product$", () -> {
            bukalapak.menuUlasanKamuPage().clickRating();
        });

        And("^user should see lengkapi ulasan button$", () -> {
            bukalapak.menuUlasanKamuPage().verifyLengkapiUlasanButton();
        });

        And("^user click lengkapi ulasan button$", () -> {
            bukalapak.menuUlasanKamuPage().clickLengkapiUlasan();
        });

        And("^user verify product which has been reviewed move to Sudah Diulas tab$", () -> {
            bukalapak.menuUlasanKamuPage().verifyUlasanMovedtoSudahDiulasTab();
        });

        And("^user input review description with random description$", () -> {
            bukalapak.productReviewFormPage().typeOnReviewDescEditText(bukalapak.productReviewFormPage().generateRandomDesc());
        });

        Then("^user should see empty state in Belum diulas Tab$", () -> {
            bukalapak.menuUlasanKamuPage().verifyEmptystateBelumDiulas();
        });

    }
}
