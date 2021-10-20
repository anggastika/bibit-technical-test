package bukalapak.stepDefinitions.vp.insurance.asuransi_marketplace;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class InsuranceInvoiceDetailPageStepDefinitions extends TestInstrument implements En {

  public InsuranceInvoiceDetailPageStepDefinitions() {

    And("^the insurance fee for that product on invoice details marketplace will( not | )have displayed$", (String display) -> {
      if (display.trim().isEmpty()) {
        bukalapak.invoiceDetailPage().validateInsuranceFee();
      } else {
        bukalapak.invoiceDetailPage().validateInsuranceFeeIsNotExist();
      }
    });
  }
}
