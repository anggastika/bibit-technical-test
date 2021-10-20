package bukalapak.stepDefinitions.oca;

import bukalapak.TestInstrument;
import bukalapak.data.OCAData;
import cucumber.api.java8.En;

public class KumpulinkWebPageStepDefinitions extends TestInstrument implements En {
    public KumpulinkWebPageStepDefinitions() {

        And("user should land on buyer kumpulink page", () -> {
            bukalapak.kumpulinkWebPage().isOnKumpulinkBuyerPage();
        });

        Then("user verify buyer kumpulink page", () -> {
            bukalapak.kumpulinkWebPage().verifyKumpulinkBuyerPage();
        });

        And("user tap copy voucher code", () -> {
            bukalapak.kumpulinkWebPage().tapOnVoucherCode();
        });

        Then("user should be success to copy voucher", () -> {
            bukalapak.kumpulinkWebPage().successCopyVoucher();
        });

        And("user should see kumpulink landing page", () -> {
            bukalapak.kumpulinkWebPage().isOnKumpulinkPage();
            OCAData.setPelapakUsername(dotenv.get("SUPERSELLER_USERNAME"));
        });

        And("user should see bukalapak as primary link", () -> {
            bukalapak.kumpulinkWebPage().verifyBukalapakAsPrimaryLink();
        });

        When("user click on add link button", () -> {
            bukalapak.kumpulinkWebPage().tapOnAddLink();
            bukalapak.kumpulinkWebPage().isOnTambahLinkPage();
        });

        And("user input \"([^\"]*)\" on link title", (String titleLink) -> {
            bukalapak.kumpulinkWebPage().inputLinkTitle(titleLink);
        });

        And("user input \"([^\"]*)\" on link URL", (String linkURL) -> {
            bukalapak.kumpulinkWebPage().inputLinkURL(linkURL);
        });

        And("user validate message error invalid link", () -> {
            bukalapak.kumpulinkWebPage().validateNonWhitelistedLink();
        });

        And("user click save button", () -> {
            bukalapak.kumpulinkWebPage().tapOnCheckButton();
            bukalapak.kumpulinkWebPage().tapOnSaveButton();
        });

        Then("user success to add new kumpulink", () -> {
            bukalapak.kumpulinkWebPage().validateLink();
        });

        And("user success to update kumpulink", () -> {
            bukalapak.kumpulinkWebPage().tapOnSaveButton();
            bukalapak.kumpulinkWebPage().validateLinkUpdated();
        });

        And("user click delete kumpulink", () -> {
            bukalapak.kumpulinkWebPage().tapOnDeleteButton();
        });

        And("user success to delete kumpulink", () -> {
            bukalapak.kumpulinkWebPage().validateDeleteLinkSuccess();
        });

        Then("user click pasang kumpulink button", () -> {
            bukalapak.kumpulinkWebPage().tapPasangKumpulinkButton();
        });

        And("user click salin link button", () -> {
            bukalapak.kumpulinkWebPage().validatePasangKumpulinkSheet();
            bukalapak.kumpulinkWebPage().tapOnCopyButton();
        });

        And("user should see success to copy message", () -> {
            bukalapak.kumpulinkWebPage().validateCopyLinkSnackBar();
        });

        And("user click preview link button", () -> {
            bukalapak.kumpulinkWebPage().tapOnPreviewButton();
        });

        And("user should see kumpulink preview page", () -> {
            bukalapak.kumpulinkWebPage().isOnPreviewPage();
        });

        And("user should be able click bukalapak link", () -> {
            bukalapak.kumpulinkWebPage().tapOnBukalapaLink();
        });

        And("user click toggle kumpulink", () -> {
            bukalapak.kumpulinkWebPage().tapToggleKumpulink();
        });

        And("user should see toggle kumpulink change", () -> {
            bukalapak.kumpulinkWebPage().validateKumpulinkStatusChange();
        });

        And("user click how to use on landing page", () -> {
            bukalapak.kumpulinkWebPage().tapOnHowtoEntryPoint();
        });

        And("user is on how to use page", () -> {
            bukalapak.kumpulinkWebPage().isOnHowtoPage();
        });

        When("user click salin link button on preview page", () -> {
            bukalapak.kumpulinkWebPage().validateCopyLinkButton();
            bukalapak.kumpulinkWebPage().tapOnCopyLinkButton();
        });

        Then("user verify success to copy message on preview page", () -> {
            bukalapak.kumpulinkWebPage().validateCopyLinkSnackBar();
        });

        When("user click ubah profile on preview page", () -> {
            bukalapak.kumpulinkWebPage().validateUbahProfile();
            bukalapak.kumpulinkWebPage().tapOnUbahProfile();
        });

        When("user is on edit profile page", () -> {
            bukalapak.kumpulinkWebPage().validateEditProfilePage();
        });

        When("^user edit nama toko to \"([^\"]*)\"$", (String tokoName) -> {
            bukalapak.kumpulinkWebPage().inputTokoName(tokoName);
        });

        When("^user edit description to \"([^\"]*)\"$", (String desciption) -> {
            bukalapak.kumpulinkWebPage().inputDescription(desciption);
        });

        When("user edit profile theme", () -> {
            bukalapak.kumpulinkWebPage().chooseTheme();
        });

        When("user click simpan on profile page", () -> {
            bukalapak.kumpulinkWebPage().validateSimpanProfile();
            bukalapak.kumpulinkWebPage().tapOnSimpanProfile();
        });

        Then("user verify success edit profile message", () -> {
            bukalapak.kumpulinkWebPage().validateSuccessEditProfileSnackBar();
        });

        Then("user verify profile has been updated", () -> {
            bukalapak.kumpulinkWebPage().validateProfileSuccessEdited();
        });

        Then("user verify product catalog section", () -> {
            bukalapak.kumpulinkWebPage().validateProductList();
        });

        When("user tap button tambah barang", () -> {
            bukalapak.kumpulinkWebPage().tapButtonTambahBarang();
        });

        When("user verify tambah barang section", () -> {
            bukalapak.kumpulinkWebPage().verifyPopupAddCatalog();
        });

        When("user create catalog barang section", () -> {
            bukalapak.kumpulinkWebPage().inputCatalogTitle();
            bukalapak.kumpulinkWebPage().tapSaveCatalogTitle();
        });

        When("user select product on product list", () -> {
            bukalapak.kumpulinkWebPage().tapProductCheckbox();
            bukalapak.kumpulinkWebPage().tapSubmitProductButton();
        });

        When("user verify manage product catalog", () -> {
            bukalapak.kumpulinkWebPage().verifyManageProductPage();
        });

        When("user tap on Selesai button", () -> {
            bukalapak.kumpulinkWebPage().tapSelesaiButton();
        });

        Then("user verify product catalog successfully added", () -> {
            bukalapak.kumpulinkWebPage().verifyCatalogSuccesfullyAdded();
        });

        When("user tap edit catalog icon kumpulink", () -> {
            bukalapak.kumpulinkWebPage().tapEditIconCatalog();
        });

        When("user tap on hapus catalog button", () -> {
            bukalapak.kumpulinkWebPage().tapHapusCatalogButton();
        });

        When("user tap yes on hapus confirmation popup", () -> {
            bukalapak.kumpulinkWebPage().tapYesDeletedCatalogPopup();
        });

        Then("user verify product catalog successfully deleted", () -> {
            bukalapak.kumpulinkWebPage().verifyCatalogDeleteSuccesfully();
        });

        Then("user change product highlight title", () -> {
            bukalapak.kumpulinkWebPage().inputEditSectionTitle();
        });

        When("user verify product catalog successfully edited", () -> {
            bukalapak.kumpulinkWebPage().validateCatalogEditSuccess();
        });

        When("user tap atur link on manage product catalog", () -> {
            bukalapak.kumpulinkWebPage().tapAturLink();
        });

        When("user verify atur link product page", () -> {
            bukalapak.kumpulinkWebPage().validateAturLinkPage();
        });

        When("user tap tambah link barang button", () -> {
            bukalapak.kumpulinkWebPage().tapTambahLinkBarangButton();
        });

        When("^user fill nama link field with \"([^\"]*)\"$", (String linkName) -> {
            bukalapak.kumpulinkWebPage().inputLinkNameBarang(linkName);
        });

        When("^user fill alamat link field with \"([^\"]*)\"$", (String linkUrl) -> {
            bukalapak.kumpulinkWebPage().inputLinkUrlBarang(linkUrl);
        });

        When("user tap simpan link barang", () -> {
            bukalapak.kumpulinkWebPage().tapSimpanLinkBarang();
        });

        When("user tap hapus link barang icon", () -> {
            bukalapak.kumpulinkWebPage().tapDeleteLinkIcon();
        });

        When("user confirm hapus link barang icon", () -> {
            bukalapak.kumpulinkWebPage().tapConfirmDeleteLink();
        });

        Then("user verify link barang icon successfully deleted", () -> {
            bukalapak.kumpulinkWebPage().validateLinkProductSuccessDeleted();
        });

        Then("^user should see empty state for manage barang$", () -> {
            bukalapak.kumpulinkWebPage().validateEmptyState();
        });

        When("^user tap on pengaturan barang link$", () -> {
            bukalapak.kumpulinkWebPage().tapAturBarangLinkIcon();
        });

        When("user tap urutan tab option", () -> {
            bukalapak.kumpulinkWebPage().tapOnUrutanTab();
        });

        When("^user tap sort by \"([^\"]*)\"$", (String option) -> {
            bukalapak.kumpulinkWebPage().tapOnOrderOption(option);
        });

        Then("^user verify sorting from \"([^\"]*)\"$", (String option) -> {
            bukalapak.kumpulinkWebPage().validateSortByOption(option);
        });

        When("user tap etalase tab option", () -> {
            bukalapak.kumpulinkWebPage().tapOnEtalaseTab();
        });

        When("^user tap etalase by \"([^\"]*)\"$", (String option) -> {
            bukalapak.kumpulinkWebPage().tapOnEtalaseOption(option);
        });

        Then("^user verify filter etalase by \"([^\"]*)\"$", (String option) -> {
            bukalapak.kumpulinkWebPage().validateEtalaseByOption(option);
        });

        When("^user tap setting order button$", () -> {
            bukalapak.kumpulinkWebPage().tapOnSettingOrderButton();
        });
        Then("^user verify setting order page$", () -> {
            bukalapak.kumpulinkWebPage().validateSettingOrderPage();
        });

        And("^user select a link \"([^\"]*)\" on landing page$", (String link) -> {
            bukalapak.kumpulinkWebPage().tapOnLInk(link);
            bukalapak.kumpulinkWebPage().isOnTambahLinkPage();
        });
    }
}
