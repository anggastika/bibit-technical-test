package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SaldoRevampStepDefinitions extends TestInstrument implements En {

    public SaldoRevampStepDefinitions() {
        // General
        Given("user is in Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().validateUserIsInSaldoRevampPage();
        });

        When("user go to Saldo Revamp Page via deeplink", () -> {
            bukalapak.homePage().openDeepLink("/dompet");
        });

        And("^user go to Saldo Revamp Page via (DANA|Saldo|Credits) card at Akun Page$", (String card) -> {
            bukalapak.saldoRevampPage().selectCard(card);
        });

        Then("^user is in (DANA|Saldo|Credits) card at Saldo Revamp Page$", (String card) -> {
            bukalapak.saldoRevampPage().verifyOnCard(card);
        });

        When("user go to Revamp Saldo Page via Lihat Detail button", () -> {
            bukalapak.akunPage().clickLihatDetailText();
        });

        When("user navigate back to akun page at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().tapBackToAkunPage();
        });

        And("^user directed to Saldo Revamp page via \"([^\"]*)\" page$", (String tabName) -> {
            bukalapak.homePage().selectNavigationTab(tabName);
            bukalapak.akunPage().clickLihatDetailText();
            bukalapak.saldoRevampPage().validateUserIsInSaldoRevampPage();
        });

        And("user tap Dompet tab at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().tapDompetTab();
        });

        And("user tap super seller mutation link at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().tapSuperSellerMutationLink();
        });

        Then("user see list cashbacks from Pembeli Prioritas at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().checkCashbackPriorityBuyer();
        });

        Then("user verify show pop up verification phone number at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().verifyPopUpVerificationPhoneSaldoBukalapak();
        });

        // DANA
        When("user tap Mini DANA button at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().tapMiniDANAButton();
        });

        And("user tap DANA topup at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().selectCard("dana");
            bukalapak.saldoRevampPage().tapDanaTopUp();
        });

        And("user tap Pindahkan Saldo ke DANA button", () -> {
            bukalapak.saldoRevampPage().selectCard("saldo");
            bukalapak.saldoRevampPage().tapPindahkanSaldoToDana();
        });

        And("user can not top up DANA via Saldo BukaDompet at Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().selectCard("saldo");
            bukalapak.saldoRevampPage().disabledTopup();
        });

        And("^bound user input top up amount Rp(.*) using Saldo BukaDompet at Revamp Saldo Page$", (String amount) -> {
            bukalapak.saldoRevampPage().inputTopupBDAmount(amount);
        });

        And("user tap Pindahin ke DANA button at Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().tapConfirmTopUpBD();
        });

        And("^user get \"([^\"]*)\" top up DANA error message at Revamp Saldo Page$", (String message) -> {
            bukalapak.saldoRevampPage().validateTopupDANAErrorMessage(message);
        });

        And("user verify DANA different phone nubmer popup confirmation at Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().verifyDANADifferentPNPopupDispayed();
        });

        And("user tap Ganti akun DANA button at Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().tapChangeDANABtn();
        });

        And("user tap Putuskan Akun button and get unbind info at Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().verifyChangeDANAPage();
            bukalapak.saldoRevampPage().tapPutuskanAkunBtn();
            bukalapak.saldoRevampPage().verifyPutuskanAkunPopup();
            bukalapak.saldoRevampPage().tapBatalBtn();
        });

        And("user validated DANA balance frozen at Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().selectCard("dana");
            bukalapak.saldoRevampPage().validateDANABalanceFrozen();
        });

        Then("user tap DANA cashout at Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().tapDanaCashout();
        });

        Then("user should be able to see Upgrade Akun DANA popup displayed at Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().verifyUpgradeAkunDANApopup();
            bukalapak.saldoRevampPage().tapUpgradeAkunDANApopup();
        });

        Then("user should be able to see Buka Aplikasi DANA popup displayed at Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().verifyBukaAplikasiDANApopup();
            bukalapak.saldoRevampPage().tapBukaAplikasiDANApopup();
        });

        And("user tap Binding DANA at DANA Mutation Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().selectCard("dana");
            bukalapak.saldoRevampPage().tapBindingAtDanaMutation();
        });

        // Saldo BukaDompet
        And("^user verify saldo BukaDompet (before|after) \"([^\"]*)\" transaction at Saldo Revamp Page$", (String state, String transactionType) -> {
            bukalapak.akunPage().clickLihatDetailText();
            bukalapak.saldoRevampPage().selectCard("saldo");
            bukalapak.saldoRevampPage().checkSaldoBukaDompet(state, transactionType);
        });

        And("^user verify saldo BukaDompet (before|after) \"([^\"]*)\" transaction from Saldo Revamp deeplink$", (String state, String transactionType) -> {
            bukalapak.saldoRevampPage().selectCard("saldo");
            bukalapak.saldoRevampPage().checkSaldoBukaDompet(state, transactionType);
        });

        And("user verify BD balance is frozen at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().selectCard("dana");
            bukalapak.saldoRevampPage().verifyBDFrozen();
        });

        Then("user verify BukaDompet mutation at Revamp Saldo Page is match with the one memorized before", () -> {
            bukalapak.saldoRevampPage().verifyBukaDompetMutation();
        });

        And("user see a frozen BukaDompet error message at Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().selectCard("saldo");
            bukalapak.saldoRevampPage().verifyCannotTopUpFrozenBD();
        });

        // Credits
        When("user tap Detail Credits Mutation button at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().selectCard("credits");
            bukalapak.saldoRevampPage().tapDetailButton();
        });

        Then("^validate user at Saldo Revamp Page is (bound|unbound) DANA and (blacklisted|whitelisted) credits$", (String dana, String credits) -> {
            bukalapak.saldoRevampPage().selectCard("credits");
            bukalapak.saldoRevampPage().validateDANABindingAndCreditsState(dana, credits);
        });

        And("user tap Binding DANA at Credits Mutation Revamp Saldo Page", () -> {
            bukalapak.saldoRevampPage().selectCard("credits");
            bukalapak.saldoRevampPage().tapBindingAtCreditsMutation();
        });

        And("user validate belanjakan button at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().selectCard("credits");
            bukalapak.saldoRevampPage().validateBelanjakanBtn();
        });

        And("^user \"([^\"]*)\" validate credits balance at Saldo Revamp Page$", (String credUsr) -> {
            bukalapak.saldoRevampPage().selectCard("credits");
            bukalapak.saldoRevampPage().validateCreditsBalance(bukalapak.apiCall().getCreditsBalance(credUsr));
        });

        // Investment
        And("user tap Investasi banner at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().tapInvestasiBanner();
        });

        And("user tap Investasi tab at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().tapInvestasiTab();
        });

        And("user is in Investasi tab at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().validateUserIsInInvestasiTab();
        });

        And("validate user is Investor at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().validateUserIsInvestor();
        });

        And("validate user is Non Investor at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().validateUserIsNonInvestor();
        });

        And("user tap Beli Investasi Button at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().tapInvestasiBeliButton();
        });

        And("user tap Jual Instan Investasi Button at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().tapInvestasiJualButton();
        });

        And("user tap Register Investasi Button at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().tapInvestasiRegButton();
        });

        And("user tap Register Now Investasi Button at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().tapInvestasiRegNowButton();
        });

        And("user tap Pending Investasi Amount at Saldo Revamp Page", () -> {
            bukalapak.saldoRevampPage().tapPendingInvestasiAmount();
        });
    }
}

