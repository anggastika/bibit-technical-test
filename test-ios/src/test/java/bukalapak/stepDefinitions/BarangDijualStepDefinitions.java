package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BarangDijualStepDefinitions extends TestInstrument implements En {

    public BarangDijualStepDefinitions() {

        Given("user is in \"barang_dijual\" page", () -> {
            bukalapak.barangDijualPage().userOnBarangDijualPage();
        });

        Given("user go to Barang Dijual page using deeplink", () -> {
            bukalapak.barangDijualPage().goToBarangDijualWithDeeplink();
            bukalapak.barangDijualPage().userOnBarangDijualPage();
        });

        Then("^user see access to Super Seller modal", () -> {
            bukalapak.barangDijualPage().verifySuperSellerOffering();
        });

        Then("user tap on filter icon", () -> {
            bukalapak.barangDijualPage().filterBarangDijual();
        });

        And("user filter active discount", () -> {
            bukalapak.barangDijualPage().filterActiveDiscount();
        });

        And("user search product on sell product listing after filtered", () -> {
            bukalapak.barangDijualPage().searchProdActiveDiscount();
        });

        Then("validate data product after delete discount", () -> {
            bukalapak.barangDijualPage().verifyProdDiscNotFound();
        });

        And("^user search \"([^\"]*)\" in product for sale page$", (String productName) -> {
            bukalapak.barangDijualPage().searchProduct(productName);
        });

        When("^user click on \"([^\"]*)\" three dots button$", (String namaBarang) -> {
            bukalapak.barangDijualPage().clickOnBarangDijualThreeDotsButton(namaBarang);
        });

        And("^user see Successful Upload Product modal$", () -> {
            bukalapak.barangDijualPage().checkUploadModal();
        });

        When("^user click sort by barang terlaris on barang dijual$", () -> {
            bukalapak.barangDijualPage().clickSortByTerlaris();
        });

        // PROM region
        And("^user click on \"([^\"]*)\" option in Barang Dijual page$", (String option) -> {
            bukalapak.barangDijualPage().tapOption(option);
        });

        When("^user click push checkbox for (.*)$", (String productName) -> {
            bukalapak.barangDijualPage().selectProducts(productName);
        });

        When("^user click Push button for first product$", () -> {
            bukalapak.barangDijualPage().clickFirstPushButton();
        });

        Then("^user validate popup confirmation push with bukadompet$", () -> {
            bukalapak.barangDijualPage().validatePopupConfirmation();
        });

        And("^user click button tidak to close popup$", () -> {
            bukalapak.barangDijualPage().tapButtonTidak();
        });

        When("user navigate to \"Push\" page", () -> {
            bukalapak.barangDijualPage().navigateToPushPage();
        });

        And("^user click on \"([^\"]*)\" promoted push button$", (String productName) -> {
            bukalapak.barangDijualPage().clickOnPromotedPushButton(productName);
        });

        Then("user will see push using BukaDompet modal", () -> {
            bukalapak.barangDijualPage().validateBulkPushUsingBukaDompetModal();
        });

        When("user tap Batal button on Push using BukaDompet modal", () -> {
            bukalapak.barangDijualPage().tapBatalPushButton();
        });

        When("^user tap on bulk promoted push button$", () -> {
            bukalapak.barangDijualPage().tapBulkPromotedPush();
        });

        Then("^user will see snackbar promoted (satuan|grup) on Barang Dijual page$", (String type) -> {
            bukalapak.barangDijualPage().verifyPromotedPushSnackbar(type);
        });
        // End PROM region

        And("^user see menu lainnya pop up in barang dijual page$", () -> {
            bukalapak.barangDijualPage().verifyPopUpMenuLainnya();
        });

        When("^user click button \"([^\"]*)\" in menu lainnya pop up$", (String menuBtn) -> {
            bukalapak.barangDijualPage().tapBtnInMenuLainnyaPopUp(menuBtn);
        });

        And("^user see pop up atur label barang in barang di jual page$", () -> {
            bukalapak.barangDijualPage().verifyPopUpAturLabelBarang();
        });

        When("^user click button tambah label barang in atur label barang pop up$", () -> {
            bukalapak.barangDijualPage().tapTambahLabelBaruBtn();
        });

        And("^user see pop up offering super seller$", () -> {
            bukalapak.barangDijualPage().verifyOfferingSuperSeller();
        });

        When("^user click button pelajari super seller in offering super seller pop up$", () -> {
            bukalapak.barangDijualPage().tapPelajariSuperSeller();
        });

        When("user tap on more option button", () -> {
            bukalapak.barangDijualPage().tapOnMenuLainnyaButton();
        });

        Then("Link Langsung Bayar modal is displayed", () -> {
            bukalapak.barangDijualPage().validateLinkLangsungBayarModal();
        });

        When("user tap on Link Langsung Bayar share button", () -> {
            bukalapak.barangDijualPage().tapOnShareLinkLangsungBayar();
        });

        Then("Link Langsung Bayar share options modal are displayed", () -> {
            bukalapak.barangDijualPage().validateShareLinkLangsungBayarModal();
        });

        Then("user scroll down to delete product button", () -> {
            bukalapak.barangDijualPage().tapOnDeleteBtn();
        });

        And("user taps all products checkbox", () -> {
            bukalapak.barangDijualPage().selectProducts("first");
            bukalapak.barangDijualPage().selectProducts("all");
        });

        And("user taps on Promoted Push option on Barang Dijual page", () -> {
            bukalapak.barangDijualPage().tapPromotedPushOption();
        });
    }
}
