package bukalapak.stepDefinitions.pcv;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class VoucherPDPStepDefinition extends TestInstrument implements En {
    public VoucherPDPStepDefinition() {
        And("^user back to PDP page$", () -> {
            bukalapak.voucherkuPromoPage().backToPDPPage();
        });

        And("^user select voucher \"([^\"]*)\" on PDP$", (String voucherName) -> {
            bukalapak.voucherkuPromoPage().selectVoucherPDP(voucherName);
        });

        And("^user copy voucher code on PDP$", () -> {
            bukalapak.voucherkuPromoPage().copyVoucherCodePDP();
        });

        And("^user (should|should not) see \"([^\"]*)\" voucher pelapak subsidi on PDP$", (String appear, String voucherName) -> {
            bukalapak.voucherkuPromoPage().validateVoucherPelapakonPDP(appear, voucherName);
        });

    }
}
