package bukalapak.stepDefinitions.vp.insurance.asuransi_kesehatan;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AsuransiKesehatanPageStepDefinitions extends TestInstrument implements En {

    public AsuransiKesehatanPageStepDefinitions() {

        When("user taps on lihat pilihan produk button the Asuransi Kesehatan", () -> {
           bukalapak.asuransiKesehatanPage().tapOnListOfProductButton();
        });

        When("user had chosen a Asuransi Kesehatan product", () -> {
            bukalapak.asuransiKesehatanPage().validateListProduct();
            bukalapak.asuransiKesehatanPage().tapOnProduct();
            bukalapak.asuransiKesehatanPage().validateButtonAjukanIsExist();
            bukalapak.asuransiKesehatanPage().tapOnButtonAjukan();
        });

        Then("^popup verification \"([^\"]*)\" Asuransi Kesehatan will have displayed$", (String validate) -> {
           bukalapak.asuransiKesehatanPage().validatePopUpInfo(validate);
        });

        And("user chooses yearly periods in the form register Asuransi Kesehatan step one", () -> {
           bukalapak.asuransiKesehatanPage().validateOnPengajuanPage();
           bukalapak.asuransiKesehatanPage().tapOnButtonSelanjutnya();
        });

        And("user inputs valid data in the form register Asuransi Kesehatan step two", () -> {
           bukalapak.asuransiKesehatanPage().typeOnFieldName("edit");
           bukalapak.asuransiKesehatanPage().setOnBirthDate();
           bukalapak.asuransiKesehatanPage().typeOnFieldIdentity("edit");
           bukalapak.asuransiKesehatanPage().setOnGender();
           bukalapak.asuransiKesehatanPage().typeOnFieldAddress();
           bukalapak.asuransiKesehatanPage().setOnProvince();
           bukalapak.asuransiKesehatanPage().setOnCity();
           bukalapak.asuransiKesehatanPage().typeOnPostalCode();
           bukalapak.asuransiKesehatanPage().typeOnPhoneNumber();
           bukalapak.asuransiKesehatanPage().tapOnButtonSelanjutnya();
        });

        And("^user inputs (valid|invalid) data in the form register Asuransi Kesehatan step three$", (String flag) -> {
           bukalapak.asuransiKesehatanPage().validateOnBankAccountPage();
           bukalapak.asuransiKesehatanPage().setOnBankAccount();
           bukalapak.asuransiKesehatanPage().typeOnAccountNumber(flag);
           bukalapak.asuransiKesehatanPage().typeOnAccountName(flag);
           bukalapak.asuransiKesehatanPage().tapOnButtonSelanjutnya();
        });

        And("user inputs valid data in the form register Asuransi Kesehatan step four", () -> {
            bukalapak.asuransiKesehatanPage().validateOnIdentityPage();
        });

        Then("user had tapped on history policy button", () -> {
           bukalapak.asuransiKesehatanPage().validateOnPage();
           bukalapak.asuransiKesehatanPage().tapOnHistoryButton();
        });

        And("list of policy had not displayed", () -> {
           bukalapak.asuransiKesehatanPage().validateOnHistoryPage();
        });

        And("product list page Asuransi Kesehatan will have displayed", () -> {
           bukalapak.asuransiKesehatanPage().validateListProduct();
        });

        Then("^error message on \"([^\"]*)\" field will have displayed required$", (String message) -> {
            bukalapak.asuransiKesehatanPage().validateOnErrorField(message);
        });

        When( "^user (?:navigates to|had been on) the Asuransi Kesehatan product list page$", () -> {
            bukalapak.asuransiKesehatanPage().tapOnButtonLihatPilihanProduct();
            bukalapak.asuransiKesehatanPage().validateProductListPage(true);
        });

        When("user taps on button detail product Asuransi Kesehatan", () -> {
            bukalapak.asuransiKesehatanPage().tapOnButtonLihatDetailProduct();
        });

        Then("the Asuransi Kesehatan product detail page will have displayed", () -> {
            bukalapak.asuransiKesehatanPage().validateProductDetailPageSection();
            bukalapak.asuransiKesehatanPage().validateDeskripsiProdukSection();
            bukalapak.asuransiKesehatanPage().validateSyaratKeikutsertaanSection();
            bukalapak.asuransiKesehatanPage().validateMasaTungguSection();
            bukalapak.asuransiKesehatanPage().validateKlaimSection();
            bukalapak.asuransiKesehatanPage().validatePengecualianProteksiSection();
            bukalapak.asuransiKesehatanPage().validateKewajibanPemegangPolisSection();
            bukalapak.asuransiKesehatanPage().validateCatatanPentingSection();
        });

        When("user taps on button lihat pilihan product Asuransi Kesehatan", () -> {
            bukalapak.asuransiKesehatanPage().tapOnButtonLihatPilihanProduct();
        });

        Then("^the \"([^\"]*)\" section Asuransi Kesehatan landing page will have displayed$", (String section) -> {
            bukalapak.asuransiKesehatanPage().validateOnPage();
            bukalapak.asuransiKesehatanPage().validateSectionPage(section);
        });
    }
}
