package bukalapak.stepDefinitions.vp.insurance.asuransi_marketplace;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

import static org.junit.Assert.fail;

public class InsuranceCheckoutPageStepDefinitions extends TestInstrument implements En {

  public InsuranceCheckoutPageStepDefinitions() {

      And("^user (ticks|unticks) the (.*) checkbox with (one|two) seller$", (String tick, String product, String seller) -> {
          bukalapak.checkoutMarketplacePage().tickInsuranceCheckbox(product, tick, 1);

          if (seller.equals("two")) {
              bukalapak.checkoutMarketplacePage().tickInsuranceCheckbox(product, tick, 2);
          }
      });

      And("the insurance fee for that product on checkout marketplace page will have updated", () -> {
          bukalapak.checkoutMarketplacePage().sumInsuranceFee(1);
          bukalapak.checkoutMarketplacePage().validateInsuranceFee();
      });
  }
}
