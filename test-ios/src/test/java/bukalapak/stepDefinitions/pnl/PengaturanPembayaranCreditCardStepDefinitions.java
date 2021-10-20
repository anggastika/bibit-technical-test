package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PengaturanPembayaranCreditCardStepDefinitions extends TestInstrument implements En {

    public PengaturanPembayaranCreditCardStepDefinitions() {

        Then("user is in \"pengaturan pembayaran credit card\" page", () -> {
            bukalapak.pengaturanPembayaranCreditCardPage().userOnPengaturanPembayaranCreditCardPage();
        });

        When("^user remove credit card with number (.*)$", (String number) -> {
            bukalapak.pengaturanPembayaranCreditCardPage().tapOnHapusButton(number);
        });

        Then("^user should (not )?see cc with number (.*)$", (String condition, String number) -> {
            bukalapak.pengaturanPembayaranCreditCardPage().checkCreditCard(condition != null, number);
        });

        When("user set second card as primary card", () -> {
            bukalapak.pengaturanPembayaranCreditCardPage().tapOnSetUtamaSecondCCButton();
        });

        Then("user should see second card successfully become primary card", () -> {
            bukalapak.pengaturanPembayaranCreditCardPage().validateKartuUtama();
        });

        When("user tap \"tambah kartu\" button", () -> {
            bukalapak.pengaturanPembayaranCreditCardPage().tapOnTambahKartuButton();
        });

    }
}
