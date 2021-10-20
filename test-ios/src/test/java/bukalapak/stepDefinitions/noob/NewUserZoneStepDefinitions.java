package bukalapak.stepDefinitions.noob;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;


public class NewUserZoneStepDefinitions extends TestInstrument implements En {

    public NewUserZoneStepDefinitions(){

        Then("unverified user in new user zone page$", () -> {
            bukalapak.newUserZonePage().validateOnNewUserZone();
            bukalapak.newUserZonePage().validateUnverifiedUser();
            bukalapak.newUserZonePage().tapOnVerifikasiBtn();
            bukalapak.newUserZonePage().validateOnVerifPhonePage();
        });

        And("unverified user claim Barang Gratis", () -> {
            bukalapak.newUserZonePage().validateOnNewUserZone();
            bukalapak.newUserZonePage().tapOnFreeGiftTab();
            bukalapak.newUserZonePage().validateBarangGratisSection();
            bukalapak.newUserZonePage().tapOnAmbilBtn();
            bukalapak.newUserZonePage().validateOnVerifPhonePage();
        });

        Then("logout user in new user zone page$", () -> {
            bukalapak.newUserZonePage().validateOnNewUserZone();
            bukalapak.newUserZonePage().validateLogoutUser();
            bukalapak.newUserZonePage().tapOnDaftarBtn();
            bukalapak.newUserZonePage().validateOnRegisterPage();
        });

        And("logout user want to claim Barang Gratis$", () -> {
            bukalapak.newUserZonePage().validateOnNewUserZone();
            bukalapak.newUserZonePage().tapOnFreeGiftTab();
            bukalapak.newUserZonePage().validateBarangGratisSection();
            bukalapak.newUserZonePage().tapOnAmbilBtn();
            bukalapak.newUserZonePage().validateOnRegisterPage();
        });

        Then("verified user in new user zone page", () -> {
            bukalapak.newUserZonePage().validateOnNewUserZone();
            bukalapak.newUserZonePage().validateVerifiedUser();
            bukalapak.newUserZonePage().tapOnTopUpBtn();
            bukalapak.newUserZonePage().validateOnDanaPage();
        });

        And("verified user claim Barang Gratis", () -> {
            bukalapak.newUserZonePage().validateOnNewUserZone();
            bukalapak.newUserZonePage().tapOnFreeGiftTab();
            bukalapak.newUserZonePage().validateBarangGratisSection();
            bukalapak.newUserZonePage().tapOnAmbilBtn();
            bukalapak.newUserZonePage().validateOnCheckoutFreeGiftPage();
        });

        Then("user can't see new user banner in homepage", () -> {
            bukalapak.newUserZonePage().validateNotShowNuzBanner();
        });

        Then("user can see udah belanja page", () -> {
            bukalapak.newUserZonePage().verifyUdahBelanja();
        });

        And("user go to new user zone banner", () -> {
            bukalapak.iOSBasePage().openDeepLink("/zona-pengguna-baru");
            bukalapak.newUserZonePage().validateOnNewUserZone();
        });

        And("eligible user can click ambil button", () -> {
            bukalapak.newUserZonePage().tapOnAmbilBtn();
        });

        And("^user click cek total pembayaran button", () -> {
            bukalapak.newUserZonePage().clickButtonCheck();
            bukalapak.newUserZonePage().clickDismissBindDana();
        });

        And("user pay free gift using Virtual Account", () -> {
            bukalapak.newUserZonePage().clickBuyButton();
        });

        And("user redirect to virtual account page", () -> {
            bukalapak.newUserZonePage().validateOnVirtualAccountPage();
        });

        When("user click buy button on virtual account page", () -> {
            bukalapak.newUserZonePage().clickPayWithVAButton();
        });

        Then("user redirect to menunggu pembayaran page", () -> {
            bukalapak.newUserZonePage().validateOnMenungguPembayaranPage();
        });

    }
}
