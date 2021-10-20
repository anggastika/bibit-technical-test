package bukalapak.stepDefinitions.pcv;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class VoucherkuDetailStepDefinitions extends TestInstrument implements En {

    public VoucherkuDetailStepDefinitions() {

        Then("user should see Voucherku detail revamp", () -> {
          bukalapak.voucherkuDetailPage().validateVoucherkuRevampDetail();
        });
  
        Then("user should see voucher code on usage info", () -> {
          bukalapak.voucherkuDetailPage().validateVoucherCodeOnUsageInfo();
        });
  
        When("user tap on terms and usage info", () -> {
          bukalapak.voucherkuDetailPage().tapOnTermsAndUsageInfo();
        });
  
        Then("user should see terms and usage info on voucherku detail", () -> {
          bukalapak.voucherkuDetailPage().validateVoucherkuInformation();
        });
  
        When("user tap use voucher on voucherku detail", () -> {
          bukalapak.voucherkuDetailPage().tapOnUseVoucherButton();
        });
  
        When("user close voucher terms and usage info", () -> {
          bukalapak.voucherkuDetailPage().closeVoucherInformationDraggable();
        });
  
        Then("user should see voucher code is successfully copied to clipboard", () -> {
          bukalapak.voucherkuDetailPage().validateCopiedVoucherCode();
        });
  
        Then("user should see voucher voucher coming soon on voucher detail", () -> {
          bukalapak.voucherkuDetailPage().validateVoucherComingSoon();
        });

        And("user should see terms and usage info before claim", () -> {
            bukalapak.voucherkuDetailPage().validateVoucherkuBeforeClaim();
        });

        And("user should see product list on voucher detail", () -> {
            bukalapak.voucherkuDetailPage().validateProductList();
        });

        When("user tap on product list on voucher detail", () -> {
            bukalapak.voucherkuDetailPage().tapOnProductList();
        });

        Then("user should see voucher applied on pdp", () -> {
            bukalapak.voucherkuDetailPage().validateSuccessApplied();
        });
      }
}
