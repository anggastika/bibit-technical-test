package bukalapak.stepDefinitions.pcv;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class VoucherkuPromoStepDefinitions extends TestInstrument implements En {

    public VoucherkuPromoStepDefinitions() {
        Then("^voucherku list will shows voucher (empty|list)$", (String status) -> {
            if (status.equals("empty")) {
                bukalapak.voucherkuPromoPage().assertVoucherEmptyPage();
            } else {
                bukalapak.voucherkuPromoPage().assertVoucherkuCard();
            }
        });

        Then("^user filter by \"([^\"]*)\"$", (String categoryName) -> {
            bukalapak.voucherkuPromoPage().selectFilterVoucherku(categoryName);
        });

        Then("^user validate \"([^\"]*)\" voucher is listed$", (String categoryName) -> {
            bukalapak.voucherkuPromoPage().verifyFilterVoucherku(categoryName);
        });

        And("^user copy voucher on list voucherku page$", () -> {
            bukalapak.voucherkuPromoPage().tapOnCTAListVoucher();
        });

        And("^user go to voucherku detail page$", () -> {
            bukalapak.voucherkuPromoPage().tapOnVoucherkuCard();
            bukalapak.voucherkuPromoPage().validateDetailVoucherku();
        });

        And("^user copy voucher on detail voucherku page$", () -> {
            bukalapak.voucherkuPromoPage().tapOnCTADetailVoucher();
        });

        And("^user validate success copy voucher code$", () -> {
            bukalapak.voucherkuPromoPage().validateSuccessCopy();
        });

        When("^user select card \"([^\"]*)\" on voucherku page$", (String voucherName) -> {
            bukalapak.voucherkuPromoPage().selectVoucherkuPage(voucherName);
        });

        And("^user use voucherku \"([^\"]*)\"$", (String voucherName) -> {
            bukalapak.voucherkuPromoPage().tapUseVoucherku(voucherName);
        });

        And("^user dismiss use voucherku$", () -> {
            bukalapak.voucherkuPromoPage().dismissUseVoucherku();
        });

        And("^user should be redirect \"([^\"]*)\" voucherku detail page$", (String voucherName) -> {
            bukalapak.voucherkuPromoPage().validateVoucherkuDetailPage(voucherName);
            bukalapak.voucherkuPromoPage().validateDetailVoucherku();
        });

        Then("^user should see voucher pelapak section$", () -> {
            bukalapak.voucherkuPromoPage().validateVoucherSellerSection();
        });

        Then("user should not see voucher claim card on promo page", () -> {
            bukalapak.voucherkuPromoPage().valideteNoVoucherClaim();
        });

        And("user should see voucherku history", () -> {
            bukalapak.voucherkuPromoPage().validateVoucherHistory();
            bukalapak.voucherkuPromoPage().userIsOnVoucherHistory();
        });

        And("user should see voucher on promo page", () -> {
            bukalapak.voucherkuPromoPage().verifyVoucherOnPromoPage();
        });
    }
}
