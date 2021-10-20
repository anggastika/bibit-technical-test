package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SaldoBukalapakStepDefinitions extends TestInstrument implements En {

    public SaldoBukalapakStepDefinitions() {
        Given("user is in \"saldo_bukalapak\" page", () -> {
            bukalapak.saldoBukalapakPage().validateUserIsInSaldoPage();
        });

        When("user choose BUKA DANA for top up", () -> {
            bukalapak.saldoBukalapakPage().tapBukaDanaTopUp();
        });

        Then("user go to Saldo page via deeplink", () -> {
            bukalapak.homePage().openDeepLink("/dompet");
        });

        When("user must redirected to Saldo Bukalapak page", () -> {
            bukalapak.saldoBukalapakPage().validateUserIsInSaldoPage();
        });

        Then("user see list cashbacks from Pembeli Prioritas", () -> {
            bukalapak.saldoBukalapakPage().checkCashbackPriorityBuyer();
        });

        And("^user verify saldo BukaDompet (before|after) \"([^\"]*)\" transaction$", (String state, String transactionType) -> {
            bukalapak.akunPage().clickLihatDetailText();
            bukalapak.saldoBukalapakPage().checkSaldoBukaDompet(state, transactionType, bukalapak.saldoBukalapakPage().getSaldoBukaDompet());
        });

        And("^user verify saldo BukaDompet (before|after) \"([^\"]*)\" transaction from deeplink$", (String state, String transactionType) -> {
            bukalapak.saldoBukalapakPage().checkSaldoBukaDompet(state, transactionType, bukalapak.saldoBukalapakPage().getSaldoBukaDompet());
        });

        When("user tap Lihat Detail button", () -> {
            bukalapak.akunPage().clickLihatDetailText();
        });

        And("user verify BD balance is frozen", () -> {
            bukalapak.saldoBukalapakPage().verifyBDFrozen();
        });

        And("user tap Pindahkan Saldo button", () -> {
            bukalapak.saldoBukalapakPage().tapPindahkanSaldo();
        });

        Then("^user can not top up DANA via Saldo BukaDompet", () -> {
            bukalapak.saldoBukalapakPage().disabledTopup();
        });

        And("user tap Saldo BukaDompet tab", () -> {
            bukalapak.saldoBukalapakPage().tapSaldoBukaDompetTab();
        });

        And("user swipe to Saldo BukaDompet tab", () -> {
            bukalapak.saldoBukalapakPage().swipeToSaldoBukaDompetTab();
        });

        And("bound user input top up amount Rp(.*) using Saldo BukaDompet", (String amount) -> {
            bukalapak.saldoBukalapakPage().inputTopupBDAmount(amount);
        });

        And("user tap Pindahin ke DANA button", () -> {
            bukalapak.saldoBukalapakPage().tapConfirmTopUpBD();
        });

        Then("user see a frozen BukaDompet error message", () -> {
            bukalapak.saldoBukalapakPage().verifyCannotTopUpFrozenBD();
        });

        Then("user get \"([^\"]*)\" top up DANA error message", (String message) -> {
           bukalapak.saldoBukalapakPage().validateTopupDANAErrorMessage(message);
        });

        When("user navigate back to akun page", () -> {
            bukalapak.saldoBukalapakPage().tapBackToAkunPage();
        });

        When("user tap on Investasi button in Saldo Bukalapak page", () -> {
            bukalapak.saldoBukalapakPage().tapOnInvestasiBtn();
        });

        When("user tap on Tambah button in Saldo Bukalapak page", () -> {
            bukalapak.saldoBukalapakPage().tapTambahButton();
        });

        When("user select DANA on Tambah Pop Up", () -> {
            bukalapak.saldoBukalapakPage().tapTambahButton();
            bukalapak.saldoBukalapakPage().tapTambahDanaButton();
        });

        And("user tap super seller mutation link", () -> {
            bukalapak.saldoBukalapakPage().tapSuperSellerMutationLink();
        });

        Then("user should be able to see Investasiku displayed", () -> {
            bukalapak.saldoBukalapakPage().verifyInvestasikuSectionDisplayed();
        });

        When("user tap on Investasiku section in Saldo Bukalapak page", () -> {
            bukalapak.saldoBukalapakPage().tapOnInvestasikuSection();
        });

        And("user directed to Saldo Bukalapak page via \"([^\"]*)\" page", (String tabName) -> {
            bukalapak.homePage().selectNavigationTab("akun");
            bukalapak.akunPage().clickLihatDetailText();
            bukalapak.saldoBukalapakPage().validateUserIsInSaldoPage();
        });

        And("user verify DANA different phone nubmer popup confirmation", () -> {
            bukalapak.saldoBukalapakPage().verifyDANADifferentPNPopupDispayed();
        });

        And("user tap Ganti akun DANA button", () -> {
            bukalapak.saldoBukalapakPage().tapChangeDANABtn();
        });

        And("user tap Putuskan Akun button and get unbind info", () -> {
            bukalapak.saldoBukalapakPage().verifyChangeDANAPage();
            bukalapak.saldoBukalapakPage().tapPutuskanAkunBtn();
            bukalapak.saldoBukalapakPage().verifyPutuskanAkunPopup();
            bukalapak.saldoBukalapakPage().tapBatalBtn();
        });

        And("user verify there is no Pindahkan Saldo button", () -> {
            bukalapak.saldoBukalapakPage().verifyNoPindahkanSaldoBtn();
        });

        And("user validated DANA balance frozen", () -> {
            bukalapak.saldoBukalapakPage().validateDANABalanceFrozen();
        });

        And("user validated DANA frozen from \"([^\"]*)\" button", (String type) -> {
            bukalapak.saldoBukalapakPage().validateDANAFrozen(type);
        });

        When("user tap Detail Credits Mutation button", () -> {
            bukalapak.saldoBukalapakPage().tapDetailButton();
        });

        Then("user validate show Hubungkan DANA credits and not show Detail button", () -> {
            bukalapak.saldoBukalapakPage().validateDetailButtonNotShow();
            bukalapak.saldoBukalapakPage().validateDANABindingAndCreditsTabShow();
            bukalapak.saldoBukalapakPage().validateDANABindingCreditsShow();
        });

        Then("user validate not show Detail button", () -> {
            bukalapak.saldoBukalapakPage().validateDetailButtonNotShow();
        });

        Then("user validate not show Hubungkan DANA credits and Detail button", () -> {
            bukalapak.saldoBukalapakPage().validateDetailButtonNotShow();
            bukalapak.saldoBukalapakPage().validateDANABindingCreditsNotShow();
            bukalapak.saldoBukalapakPage().validateDANABindingAndCreditsTabShow();
        });

        When("user tap Mini DANA button", () -> {
            bukalapak.saldoBukalapakPage().tapMiniDANAButton();
        });

        Then("user redirect to DANA tab on Saldo Bukalapak page", () -> {
            bukalapak.saldoBukalapakPage().verifyOnDANATab();
        });

        Then("user redirect to BukaDompet tab on Saldo Bukalapak page", () -> {
            bukalapak.saldoBukalapakPage().verifyOnBukaDompetTab();
        });

        Then("user verify BukaDompet mutation is match with the one memorized before", () -> {
            bukalapak.saldoBukalapakPage().verifyBukaDompetMutation();
        });

        Then("user verify show pop up verification phone number on Saldo Bukalapak page", () -> {
            bukalapak.saldoBukalapakPage().verifyPopUpVerificationPhoneSaldoBukalapak();
        });


        Then("user tap DANA cashout", () -> {
            bukalapak.saldoBukalapakPage().tapDanaCashout();
        });

        Then("user should be able to see Upgrade Akun DANA popup displayed", () -> {
            bukalapak.saldoBukalapakPage().verifyUpgradeAkunDANApopup();
        });

        Then("user should be able to see Buka Aplikasi DANA popup displayed", () -> {
            bukalapak.saldoBukalapakPage().verifyBukaAplikasiDANApopup();
        });
    }
}

