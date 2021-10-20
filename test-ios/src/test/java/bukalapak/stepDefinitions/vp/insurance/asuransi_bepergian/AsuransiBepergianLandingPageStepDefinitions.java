package bukalapak.stepDefinitions.vp.insurance.asuransi_bepergian;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AsuransiBepergianLandingPageStepDefinitions extends TestInstrument implements En {

  public AsuransiBepergianLandingPageStepDefinitions() {

    When("^user chooses an Asuransi Bepergian (individu|family) schedule$", (String packageType) -> {
      bukalapak.asuransiBepergianLandingPage().tapTanggalField("pergi");
      bukalapak.asuransiBepergianLandingPage().chooseCalendarDate(1);
      bukalapak.asuransiBepergianLandingPage().tapPilihTanggalButton();
      bukalapak.asuransiBepergianLandingPage().tapTanggalField("pulang");
      bukalapak.asuransiBepergianLandingPage().chooseCalendarDate(3);
      bukalapak.asuransiBepergianLandingPage().tapPilihTanggalButton();
      bukalapak.asuransiBepergianLandingPage().tapPackageType(packageType);
      bukalapak.asuransiBepergianLandingPage().tapLanjutkanButton();
    });

    Then("the Asuransi Bepergian landing page will have displayed", () -> {
      bukalapak.asuransiBepergianLandingPage().validateOnPage();
    });

    When("user taps on pelajari button the Asuransi Bepergian", () -> {
      bukalapak.asuransiBepergianLandingPage().tapPelajariButton();
    });

    Then("Asuransi Bepergian pelajari section will have displayed", () -> {
      bukalapak.asuransiBepergianLandingPage().validatePelajariSection();
    });

    Then("^Asuransi Bepergian (individu|family) section will have displayed$", (String packageType) -> {
      bukalapak.asuransiBepergianLandingPage().validatePackageType(packageType);
      bukalapak.insuranceProductDetailPage().tapOnManfaatTab();
      bukalapak.insuranceProductDetailPage().tapOnCaraBeliTab();
      bukalapak.insuranceProductDetailPage().tapOnKetentuanTab();
      bukalapak.insuranceProductDetailPage().tapOnCaraKlaimTab();
      bukalapak.insuranceProductDetailPage().tapOnTanyaJawabTab();
      bukalapak.insuranceProductDetailPage().tapOnKontakTab();
    });
  }
}
