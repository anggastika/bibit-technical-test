package bukalapak.stepDefinitions.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TopupDanaStepDefinitions extends TestInstrument implements En {
    public TopupDanaStepDefinitions(){
        When("user topup dana from landing page", () -> {
            bukalapak.topupDanaPage().isOnTopupDanaPage();
            bukalapak.topupDanaPage().setNominalTopup();
            bukalapak.topupDanaPage().tapOnTopupButton();
            bukalapak.topupDanaPage().verifyCheckoutTopupDana();
        });

        Then("activated reminder pop up is displayed", () -> {
            bukalapak.topupDanaPage().activatedPopUpDisplayed();
        });

        And("user verify last transaction topup dana", () -> {
            bukalapak.topupDanaPage().tapOnTransactionListButton();
            bukalapak.topupDanaPage().verifyLastTopupDana();
        });

        When("user repurchase topup dana from transaction list topup dana", () -> {
            bukalapak.topupDanaPage().tapOnTransactionListButton();
            bukalapak.topupDanaPage().repurchaseTopupDana();
        });

        Then ("user see success binding message on Top Up DANA VP page", () -> {
            bukalapak.topupDanaPage().verifySuccessBindTopUpDANAVPPage();
            bukalapak.topupDanaPage().isOnTopupDanaPage();
        });

        And("user is on Top Up Dana Page", () -> {
            bukalapak.topupDanaPage().isOnTopupDanaPage();
        });

        And("user choose nominal \"([^\"]*)\" for top up DANA", (String amount) -> {
            bukalapak.topupDanaPage().chooseTopupAmount(amount);
        });

        And("user input nominal Rp (.*) for top up DANA", (String amount) -> {
            bukalapak.topupDanaPage().inputTopupAmount(amount);
        });

        Then("user validate exceeded DANA Topup amount", () -> {
            bukalapak.topupDanaPage().validateExceededDANATopupDisable();
        });

        Then("user validate minimum DANA Topup amount", () -> {
            bukalapak.topupDanaPage().validateMinimumDANATopupDisable();
        });

        And("user tap Bayar button at Top Up DANA VP", () -> {
            bukalapak.topupDanaPage().tapBayarTopUpDANAVp();
        });

        And("virtual account number should be \"([^\"]*)\"", (String number) -> {
           bukalapak.topupDanaPage().validateVANumber(number);
        });

        And("user bind dana from top up DANA page", () -> {
            bukalapak.topupDanaPage().tapAktifkanSekarang();
        });

        And("user verify there is no history topup", () -> {
            bukalapak.topupDanaPage().noHistoryTopup();
        });

        // New checkout section

        And("user is on DANA Topup checkout page", () -> {
            bukalapak.danaTopUpCheckoutPage().validateOnPage();
        });

        When("^user \"([^\"]*)\" topup DANA VP Rp(\\d+) consecutively (\\d+) times using VA \"([^\"]*)\" via API$", (String usr, Integer amount, Integer times, String va) -> {
            bukalapak.apiCall().setTopupBreaker(usr, amount, times, va);
        });

        And("user validate DANA topup breaker popup", () -> {
            bukalapak.topupDanaPage().validateTopupBreakerPopup();
        });

        And("user close DANA topup breaker popup", () -> {
            bukalapak.topupDanaPage().closeTopupBreakerPopup();
        });

        //Top Up DANA for freeze BukaDompet
        And("user verify BukaDompet freeze Top Up Breaker", () -> {
            bukalapak.topupDanaPage().verifySaldoFreezeTopupBreaker();
        });

        And("^user tap (Hubungi BukaBantuan|Kembali) at breaker modal", (String button) -> {
            if (button.equals("Hubungi BukaBantuan")) {
                bukalapak.topupDanaPage().tapHubungiBukabantuanSaldoFreezeBreaker();
            } else {
                bukalapak.topupDanaPage().tapKembaliSaldoFreezeBreaker();
                bukalapak.saldoBukalapakPage().validateUserIsInSaldoPage();
            }
        });
    }
}
