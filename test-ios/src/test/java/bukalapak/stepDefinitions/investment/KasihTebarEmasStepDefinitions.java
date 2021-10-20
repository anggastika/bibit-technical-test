package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class KasihTebarEmasStepDefinitions extends TestInstrument implements En {

    public KasihTebarEmasStepDefinitions() {

        And("^user can see \"([^\"]*)\" sharing page$", (String option) -> {
            bukalapak.kasihTebarEmasPage().verifyBukaEmasSharingPage(option);
        });

        And("^user tap on Cara Nebar link$", () -> {
            bukalapak.kasihTebarEmasPage().tapOnCaraNebarBtn();
        });

        Then("^user should see Cara Nebar page$", () -> {
            bukalapak.kasihTebarEmasPage().verifyCaraNebarPage();
        });

        And("^user tap on transfer Kasih Emas with link$", () -> {
            bukalapak.kasihTebarEmasPage().tapOnTransferWithLink();
        });

        And("^user input share value using \"([^\"]*)\"$", (String method) -> {
            bukalapak.kasihTebarEmasPage().validateReceiverUsername();
            bukalapak.kasihTebarEmasPage().inputKasihEmasValue(method);
        });

        And("^user choose card and input message$", () -> {
            bukalapak.kasihTebarEmasPage().verifyKasihEmasCardPage();
            bukalapak.kasihTebarEmasPage().createKasihEmasCard();
        });

        And("^user tap on Share with link button$", () -> {
            bukalapak.kasihTebarEmasPage().validateShareWithLinkModal();
            bukalapak.kasihTebarEmasPage().tapOnShareWithLinkButton();
        });

        And("^user can see Sharing Emas share link page$", () -> {
            bukalapak.kasihTebarEmasPage().verifyShareWithLinkPage();
        });

        And("^user tap back button from share link page$", () -> {
            bukalapak.kasihTebarEmasPage().tapOnSharePageBackButton();
        });

        And("^user can see belum mau bagikan link modal$", () -> {
            bukalapak.kasihTebarEmasPage().verifyShareLaterModal();
        });

        And("^user tap on Sharing Emas share later button$", () -> {
            bukalapak.kasihTebarEmasPage().tapOnShareLaterButton();
        });

        And("^user tap on Kasih Emas receiver search bar$", () -> {
            bukalapak.kasihTebarEmasPage().tapKasihEmasSearchBar();
        });

        And("^user can see Kasih Emas receiver search page$", () -> {
            bukalapak.kasihTebarEmasPage().verifyKasihEmasSearchPage();
        });

        And("^user go back to Kasih Emas page$", () -> {
           bukalapak.kasihTebarEmasPage().tapOnSearchPageBackBtn();
        });

        And("^user search and choose receiver using \"([^\"]*)\"$", (String method) -> {
            bukalapak.kasihTebarEmasPage().searchKasihReceiver(method);
        });

        And("^user tap on receiver from Kasih Emas history section$", () -> {
            bukalapak.kasihTebarEmasPage().tapOnRecentTransferReceiver();
        });

        Then("^user should see \"([^\"]*)\" Share Emas error$", (String errorType) -> {
            bukalapak.kasihTebarEmasPage().verifyShareEmasValueError(errorType);
        });

        And("^user tap on Lanjutkan button in Transfer Emas amount page$", () -> {
            bukalapak.kasihTebarEmasPage().tapOnLanjutkanBton();
        });

        Then("^user should see \"([^\"]*)\" error and share link button$", (String errorType) -> {
            bukalapak.kasihTebarEmasPage().verifyKasihEmasSearchError(errorType);
        });

        And("^user input Tebar Emas transfer value using \"([^\"]*)\"$", (String method) -> {
            bukalapak.kasihTebarEmasPage().inputTebarEmasValue(method);
        });

        And("^user get amount of Tebar Emas receivers$", () -> {
            bukalapak.kasihTebarEmasPage().getTebarEmasReceiverAmount();
        });

        And("^user tap on Mulai sebarin button$", () -> {
            bukalapak.kasihTebarEmasPage().tapOnMulaiSebarinBtn();
        });

        And("^user should see \"([^\"]*)\" Tebar Emas Leaderboard page$", (String state) -> {
            bukalapak.kasihTebarEmasPage().verifyTebarEmasLeaderboardPage(state);
        });

        And("^user tap on back button to go back to transaction detail page$", () -> {
            bukalapak.kasihTebarEmasPage().tapOnLeaderboardBackBtn();
        });
    }
}
