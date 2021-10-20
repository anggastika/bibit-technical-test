package bukalapak.stepDefinitions.vp.insurance.asuransi_bepergian;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AsuransiBepergianRegistrationPageStepDefinitions extends TestInstrument implements En {

  public AsuransiBepergianRegistrationPageStepDefinitions() {

    And("^user inputs a valid (individu|family) data on info pemegang polis form the Asuransi Bepergian", (String packageType) -> {
      bukalapak.asuransiBepergianRegistrationPage().tapBeliSekarangButton();
      bukalapak.asuransiBepergianRegistrationPage().tapLanjutIsiFormulirButton();
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanFullname();
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanEmail();
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanPhone();
      bukalapak.asuransiBepergianRegistrationPage().tapFormulirPengajuanTertanggung(1);
      bukalapak.asuransiBepergianRegistrationPage().validateOnDetailTertanggungPage();
      bukalapak.asuransiBepergianRegistrationPage().inputDetailTertanggungFullname();
      bukalapak.asuransiBepergianRegistrationPage().inputDetailTertanggungBirthdate();
      bukalapak.asuransiBepergianRegistrationPage().tapSimpanButton();
      if (packageType.equals("family")) {
        bukalapak.asuransiBepergianRegistrationPage().tapFormulirPengajuanTertanggung(2);
        bukalapak.asuransiBepergianRegistrationPage().validateOnDetailTertanggungPage();
        bukalapak.asuransiBepergianRegistrationPage().inputDetailTertanggungFullname();
        bukalapak.asuransiBepergianRegistrationPage().inputDetailTertanggungBirthdate();
        bukalapak.asuransiBepergianRegistrationPage().tapSimpanButton();
      }
      bukalapak.asuransiBepergianRegistrationPage().tapLanjutButton();
    });

    And("user inputs a valid data on the Asuransi Bepergian info tempat tinggal form", () -> {
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanAddress();
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanStreetName();
      bukalapak.asuransiBepergianRegistrationPage().chooseFormulirPengajuanCity();
      bukalapak.asuransiBepergianRegistrationPage().chooseFormulirPengajuanKecamatan();
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanPostalCode();
      bukalapak.asuransiBepergianRegistrationPage().tapLanjutButton();
      bukalapak.asuransiBepergianDetailPurchasedPage().tapTnCCheckbox();
      bukalapak.asuransiBepergianDetailPurchasedPage().tapLanjutKePembayaranButton();
    });

    And("user inputs empty field on info pemegang polis form the Asuransi Bepergian", () -> {
      bukalapak.asuransiBepergianRegistrationPage().tapBeliSekarangButton();
      bukalapak.asuransiBepergianRegistrationPage().tapLanjutIsiFormulirButton();
      bukalapak.asuransiBepergianRegistrationPage().tapLanjutButton();
    });

    Then("error message for empty pemegang polis form field the Asuransi Bepergian will have displayed", () -> {
      bukalapak.asuransiBepergianRegistrationPage().validateBepergianInsuranceEmptyInfoPemegangPolisInput();
    });

    And("user inputs empty field on info tertanggung form the Asuransi Bepergian", () -> {
      bukalapak.asuransiBepergianRegistrationPage().tapBeliSekarangButton();
      bukalapak.asuransiBepergianRegistrationPage().tapLanjutIsiFormulirButton();
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanFullname();
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanEmail();
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanPhone();
      bukalapak.asuransiBepergianRegistrationPage().tapFormulirPengajuanTertanggung(1);
      bukalapak.asuransiBepergianRegistrationPage().tapSimpanButton();
    });

    Then("error message for empty tertanggung form field the Asuransi Bepergian will have displayed", () -> {
      bukalapak.asuransiBepergianRegistrationPage().validateBepergianInsuranceEmptyInfoTertanggungInput();
    });

    And("user inputs empty field on info tempat tinggal form the Asuransi Bepergian", () -> {
      bukalapak.asuransiBepergianRegistrationPage().tapBeliSekarangButton();
      bukalapak.asuransiBepergianRegistrationPage().tapLanjutIsiFormulirButton();
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanFullname();
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanEmail();
      bukalapak.asuransiBepergianRegistrationPage().inputFormulirPengajuanPhone();
      bukalapak.asuransiBepergianRegistrationPage().tapFormulirPengajuanTertanggung(1);
      bukalapak.asuransiBepergianRegistrationPage().validateOnDetailTertanggungPage();
      bukalapak.asuransiBepergianRegistrationPage().inputDetailTertanggungFullname();
      bukalapak.asuransiBepergianRegistrationPage().inputDetailTertanggungBirthdate();
      bukalapak.asuransiBepergianRegistrationPage().tapSimpanButton();
      bukalapak.asuransiBepergianRegistrationPage().tapLanjutButton();
      bukalapak.asuransiBepergianRegistrationPage().tapLanjutButton();
    });

    Then("error message for empty tempat tinggal form field the Asuransi Bepergian will have displayed", () -> {
      bukalapak.asuransiBepergianRegistrationPage().validateBepergianInsuranceEmptyInfoTempatTinggalInput();
    });

    And("user inputs invalid \"([^\"]*)\" on info pemegang polis form the Asuransi Bepergian", (String field) -> {
      bukalapak.asuransiBepergianRegistrationPage().tapBeliSekarangButton();
      bukalapak.asuransiBepergianRegistrationPage().tapLanjutIsiFormulirButton();
      bukalapak.asuransiBepergianRegistrationPage().inputInvalidFieldInput(field);
      bukalapak.asuransiBepergianRegistrationPage().tapLanjutButton();
    });

    Then("error message on \"([^\"]*)\" field the Asuransi Bepergian will have displayed", (String message) -> {
      bukalapak.asuransiBepergianRegistrationPage().validateBepergianInsuranceErrorMessage(message);
    });
  }
}
