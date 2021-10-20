package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BuatSebarPromosiStepDefinitions extends TestInstrument implements En {
    public BuatSebarPromosiStepDefinitions() {
        Given("^user is in \"Buat sebar promosi\" page$", () -> {
            bukalapak.buatSebarPromosiPage().verifyBuatSebarPromosiPageDisplayed();
        });

        And("^user input sebar promosi message \"([^\"]*)\" text", (String typeMessage) -> {
            bukalapak.buatSebarPromosiPage().typeMessage(dotenv.get(typeMessage));
        });

        And("^user click kirim pesan button$", () -> {
            bukalapak.buatSebarPromosiPage().tapKirimPesanButton();
        });

        And("^user verify pop up pesan siap dikirim display$", () -> {
            bukalapak.buatSebarPromosiPage().verifyPopUpPesanSiapDikirim();
        });

        And("^user click Kirim Pesan button in pop pesan siap dikirim$", () -> {
            bukalapak.buatSebarPromosiPage().tapKirimPesanPopUpButton();
        });

        And("^user see error message less than 50 character$", () -> {
            bukalapak.buatSebarPromosiPage().verifyInvalidMessageInfo();
        });

        And("^user tap info icon in buat sebar promosi page$", () -> {
            bukalapak.buatSebarPromosiPage().tapIconInfoBuatSebarPromosi();
        });

        And("^user see info popup about sebar promosi feature$", () -> {
            bukalapak.buatSebarPromosiPage().verifyPopUpInfoSebarPromosi();
        });

        And("^user see popup keluar dari halaman$", () -> {
            bukalapak.buatSebarPromosiPage().verifyPopUpKeluarDariHalaman();
        });

        And("^user see empty text field in buat sebar promosi page$", () -> {
            bukalapak.buatSebarPromosiPage().verifyEmptyTextFieldSebarPromosi();
        });

        And("^user tap amount of people info$", () -> {
            bukalapak.buatSebarPromosiPage().tapAmountOfPeople();
        });

        And("^user verify daftar penerima pesan$", () -> {
            bukalapak.buatSebarPromosiPage().verifyDaftarPenerimaPesanPopUp();
        });

        And("^user see (not have favorite froduct|not have product sale) info in sebar promosi page", (String typeSebarPromosi) -> {
            bukalapak.buatSebarPromosiPage().verifyEmptyFavOrEmptyItems(typeSebarPromosi);
        });

        And("^user tap (lihat tips|upload barang) button in (?:not have favorite product|not have product sale) info in sebar promosi page", (String typeBtnSebarPromosi) -> {
            bukalapak.buatSebarPromosiPage().tapLihatTipsOrUploadItems(typeBtnSebarPromosi);
        });

        // Managemen Pesan Promosi
        /// Onboarding
        Given("^user is in \"CRM - Buat sebar promosi\" page$", () -> {
            bukalapak.buatSebarPromosiPage().verifyBuatPesanCRMPageDisplayed();
        });

        Then("^Product Selection section is (custom|automatic)$", (String state) -> {
            bukalapak.buatSebarPromosiPage().verifyProductSelectionSection(state);
        });

        And("user tap Lihat Contoh on Buat Sebar Promosi page", () -> {
            bukalapak.buatSebarPromosiPage().tapLihatContoh();
        });

        Then("user will see on boarding Sebar Promosi", () -> {
            bukalapak.buatSebarPromosiPage().verifyOnboardingCRM();
            bukalapak.buatSebarPromosiPage().closeLihatContoh();
        });

        And("^Product Selection button is \"([^\"]*)\"$", (String type) -> {
            bukalapak.buatSebarPromosiPage().verifyProductSelectionButton(type);
        });

        And("user exits from CRM Buat Pesan page", () -> {
            bukalapak.buatSebarPromosiPage().tapBackFromCRMBuatPesanButton();
        });

        And("user taps attach product for CRM message", () -> {
            bukalapak.buatSebarPromosiPage().tapProductSelectionField();
        });

        Then("^user successfully add (\\d+) product on product selection field$", (Integer totalSelectedProduct) -> {
            bukalapak.buatSebarPromosiPage().validateSelectedProduct(totalSelectedProduct);
        });

        And("user is in \"CRM - Pilih Barang\" page", () -> {
            bukalapak.buatSebarPromosiPage().validateProductSelectionPage();
        });

        And("^user selects (\\d+) product from product selection CRM$", (Integer totalSelectedProduct) -> {
            bukalapak.buatSebarPromosiPage().tapProductCheckbox(totalSelectedProduct);
            bukalapak.buatSebarPromosiPage().tapSimpanButton();
        });

        And("the fourth product is not selected as CRM product", () -> {
            bukalapak.buatSebarPromosiPage().validateCRMProductMaxSelection();
        });

        When("^user removes (first|second) selected CRM product$", (String selectedProductIndex) -> {
            bukalapak.buatSebarPromosiPage().removeSelectedProductOnIndex(selectedProductIndex);
        });

        Then("^(first|second) selected product is successfully removed$", (String selectedProductIndex) -> {
            bukalapak.buatSebarPromosiPage().validateSelectedProductOnIndex(selectedProductIndex);
        });
        // End region Managemen Pesan Promosi
    }
}
