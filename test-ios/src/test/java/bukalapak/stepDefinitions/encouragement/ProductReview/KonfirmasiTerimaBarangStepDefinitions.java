package bukalapak.stepDefinitions.encouragement.ProductReview;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by sekarayukarindra on 24/02/20.
 */
public class KonfirmasiTerimaBarangStepDefinitions extends TestInstrument implements En {
    public KonfirmasiTerimaBarangStepDefinitions(){

        And("^User tap on Next button in konfirmasi terima barang page$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userTapNextBtn();
        });

        And("^User tap on OK button in konfirmasi terima barang page$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userTapOkBtn();
        });

        Then("^user is on Konfirmasi Terima Barang page$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userOnKTBPage();
        });

        When("^user tap \"([^\"]*)\" feedback button$", (String feedback) -> {
            bukalapak.konfirmasiTerimaBarangPage().userTapFeedbackBtn(feedback);
        });

        And("^user fill comment \"([^\"]*)\" for pelapak$", (String comment) -> {
            bukalapak.konfirmasiTerimaBarangPage().userInputComment(comment);
        });

        Then("^user will see successful submit feedback message$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().verifySuccesMessage();
        });

        And("^user tap back button icon$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userTapBackBtn();
        });

        And("^user tap close pop up rating$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userTapCloseModalBtn();
        });

        Then("^user is on Ulas Barang page$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().verifyUlasBarangPage();
        });

        When("^user tap ulas button$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userTapUlasBtn();
        });

        And("^user input product rating$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userInputRating();
        });

        And("^user tap simpan button$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userTapSimpanButton();
        });

        And("^user tap ulasan barang rate$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userInputUlasBarangRate();
        });

        When("^user tap product rate multiple product$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userTapProductRate();
        });

        And("^user tap isi rating & teruskan uang button$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userTapRatingAndTeruskanBtn();
        });

        Then("^user should see submitted review on Konfirmasi Terima Barang page$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().assertReviewOnKtbPage();
        });

        And("^user tap lengkapi ulasan button$", () -> {
            bukalapak.konfirmasiTerimaBarangPage().userTapLengkapiUlasanBtn();
        });
        
    }
}
