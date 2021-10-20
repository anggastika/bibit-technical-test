package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;


public class PengaturanPembayaranStepDefinitions extends TestInstrument implements En {

    public PengaturanPembayaranStepDefinitions() {

        When("^user remove cc with number (.*)$", (String numberCC) -> {
            bukalapak.pengaturanPembayaranPage().removeCC(numberCC);
        });

        And("user navigate to Pengaturan DANA page", () -> {
            bukalapak.pengaturanPembayaranPage().tapPengaturanDANA();
        });

        Then("user is in \"Pengaturan Pembayaran\" page", () -> {
            bukalapak.pengaturanPembayaranPage().userOnPengaturanPembayaranPage();
        });

        When("user tap on credit card section", () -> {
            bukalapak.pengaturanPembayaranPage().tapOnCreditCardButton();
        });

        And("user go to \"Pengaturan Pembayaran\" page", () -> {
            bukalapak.homePage().selectNavigationTab("Akun");
            bukalapak.akunPage().tapPengaturanAkunOption();
            bukalapak.pengaturanAkunPage().tapPengaturanPembayaran();
        });
    }
}
