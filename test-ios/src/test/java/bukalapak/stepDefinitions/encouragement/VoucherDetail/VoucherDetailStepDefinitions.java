package bukalapak.stepDefinitions.encouragement.VoucherDetail;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class VoucherDetailStepDefinitions extends TestInstrument implements En {
    public VoucherDetailStepDefinitions() {

        Then("^user should see voucher detail page$", () -> {
            bukalapak.voucherDetailPage().assertVoucherDetailPage();
        });

        And("^user should see voucher \"([^\"]*)\"", (String voucherInfo) -> {
            bukalapak.voucherDetailPage().assertVoucherInfo(voucherInfo);
        });

        And("^user should see voucher code$", () -> {
            bukalapak.voucherDetailPage().assertVoucherCode();
        });

        And("^user should see voucher copy button$", () -> {
            bukalapak.voucherDetailPage().assertVoucherCopyButton();
        });

        And("^user tap copy button in voucher detail$", () -> {
            bukalapak.voucherDetailPage().tapVoucherCopyButton();
        });

        Then("^user should see snackbar message \"([^\"]*)\"$", (String message) -> {
            bukalapak.voucherDetailPage().snackbarMessage(message);
        });

        And("^user should see header image$", () -> {
            bukalapak.voucherDetailPage().assertHeaderImage();
        });
    }
}
