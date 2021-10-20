package bukalapak.stepDefinitions.vp.insurance.asuransi_covid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AsuransiCovidLandingPageStepDefinitions extends TestInstrument implements En {
  public AsuransiCovidLandingPageStepDefinitions() {

    Then("user validate Asuransi Covid landing page", () -> {
      bukalapak.asuransiCovidLandingPage().validateProductOne();
      bukalapak.asuransiCovidLandingPage().validateProductTwo();
      bukalapak.asuransiCovidLandingPage().validateKeySellingSection();
      bukalapak.asuransiCovidLandingPage().validatePromoSection();
      bukalapak.asuransiCovidLandingPage().validateFooter();
    });
  }
}
