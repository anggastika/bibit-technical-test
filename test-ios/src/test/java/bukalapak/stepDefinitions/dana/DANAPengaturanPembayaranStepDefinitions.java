package bukalapak.stepDefinitions.dana;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class DANAPengaturanPembayaranStepDefinitions extends TestInstrument implements En {

    public DANAPengaturanPembayaranStepDefinitions() {

        Then("user is in Pengaturan DANA page", () -> {
            bukalapak.danaPengaturanPembayaranPage().userOnDANAPengaturanPembayaranPage();
        });

        When("user tap on Putuskan Akun button", () -> {
            bukalapak.danaPengaturanPembayaranPage().tapUnbindButton();
        });

        Then("user verify cannot unbind directly", () -> {
            bukalapak.danaPengaturanPembayaranPage().verifyCannotUnbind();
        });

        When("user tap on Hubungi BukaBantuan button", () -> {
            bukalapak.danaPengaturanPembayaranPage().tapBukaBantuanButton();
        });

        When("user tap My DANA button", () -> {
            bukalapak.danaPengaturanPembayaranPage().tapMyDANAButton();
        });

        Then("user will be directed to BukaBantuan page and verify still bound on homepage", () -> {
            bukalapak.danaPengaturanPembayaranPage().userOnBukaBantuanPage();
            bukalapak.danaPengaturanPembayaranPage().goToHomePage();
            bukalapak.homePage().verifyDANA();
        });

        Then("user will be directed to BukaBantuan page about unbinding", () -> {
            bukalapak.danaPengaturanPembayaranPage().userOnBukaBantuanPage();
        });

        Then("user verify DANA confirmation popup on change phone number page", () -> {
            bukalapak.danaPengaturanPembayaranPage().verifyDanaConfirmChangePN();
        });

        Then("user verify DANA confirmation popup is not appear", () -> {
            bukalapak.danaPengaturanPembayaranPage().verifyDanaConfirmChangePNnotAppear();
        });

        And("user tap on Ubah Nomor Telepon button", () -> {
            bukalapak.danaPengaturanPembayaranPage().tapOnChangePNButton();
        });

        And("user is in Ubah Nomor Telepon landing page", () -> {
            bukalapak.danaPengaturanPembayaranPage().validateChangePNLandingPage();
        });

        And("user tap on Lanjut button to change phone number", () -> {
            bukalapak.danaPengaturanPembayaranPage().tapOnNextBtn();
        });

        And("user tap on Batal button", () -> {
            bukalapak.danaPengaturanPembayaranPage().tapOnCancelBtn();
        });
    }
}
