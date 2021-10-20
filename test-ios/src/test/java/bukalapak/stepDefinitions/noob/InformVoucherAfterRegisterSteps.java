package bukalapak.stepDefinitions.noob;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class InformVoucherAfterRegisterSteps extends TestInstrument implements En {

    public InformVoucherAfterRegisterSteps() {
        And("ruea user should see voucher sheet", () -> {
            bukalapak.informVoucherAfterRegisterPage().verifyVoucherSheet();
        });

        And("ruea user should verify phone from voucher sheet", () -> {
            bukalapak.informVoucherAfterRegisterPage().tapOnVerifyPhone();
        });

        And("user should see voucher sheet reward", () -> {
           bukalapak.informVoucherAfterRegisterPage().verifyCekVoucherButton();
        });

        And("user can validate voucher rewards", () -> {
           bukalapak.informVoucherAfterRegisterPage().tapOnCekVoucher();
           bukalapak.newUserZonePage().validateOnNewUserZone();
        });

        And("user close voucher sheet", () -> {
           bukalapak.informVoucherAfterRegisterPage().closeVoucherSheet();
        });

        And("user dismiss dana pop up", () -> {
            bukalapak.informVoucherAfterRegisterPage().closeDanaPopUp();
        });

        And("user tap back on binding DANA page after register", () -> {
            bukalapak.informVoucherAfterRegisterPage().isOnDanaPageAfterRegister();
            bukalapak.informVoucherAfterRegisterPage().cancelBindingDanaAfterRegister();
        });
    }
}
