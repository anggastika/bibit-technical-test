package bukalapak.stepDefinitions.vp.insurance.asuransi_retur;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AsuransiReturCheckoutSteps extends TestInstrument implements En {

    public AsuransiReturCheckoutSteps() {
        When("^user ticks Asuransi Retur checkbox( on seller two)?$", (String seller) -> {
            bukalapak.asuransiReturCheckoutPage().tapOnCheckbox(seller != null);
        });

        Then("^the Asuransi Retur checkbox( on seller two)? has been (checked|unchecked)$", (String seller, String status) -> {
            bukalapak.asuransiReturCheckoutPage().validateCheckboxAsuransiRetur(seller != null, status.equals("checked"));
        });

        Then("^the Asuransi Retur section( with two seller)? will have displayed$", (String seller) -> {
            bukalapak.asuransiReturCheckoutPage().validateAsuransiReturSection();
            bukalapak.asuransiReturCheckoutPage().setReturAsuransiFee(seller != null, false);
        });

        Then("^the Asuransi Retur fee on checkout marketplace page will have( not)? displayed$", (String flag) -> {
                bukalapak.asuransiReturCheckoutPage().validateCheckoutAsuransiReturFee(flag == null);
        });

        Then("^the Asuransi Retur fee on invoice details marketplace will have( not)? displayed$", (String flag) -> {
            bukalapak.asuransiReturCheckoutPage().validateInvoiceDetailAsuransiReturFee(flag == null);
        });

        Then("^user reset Asuransi Retur fee$", () -> {
            bukalapak.asuransiReturCheckoutPage().resetAsuransiReturFee();
        });

        Then("^user validate Asuransi Retur fee after change product quantity$", () -> {
            bukalapak.asuransiReturCheckoutPage().setReturAsuransiFee(false, true);
            bukalapak.asuransiReturCheckoutPage().validateTotalFeeOnAsuransiReturSection();
        });
    }
}
