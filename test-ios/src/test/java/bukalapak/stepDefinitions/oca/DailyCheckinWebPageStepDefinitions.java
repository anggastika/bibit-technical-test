package bukalapak.stepDefinitions.oca;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class DailyCheckinWebPageStepDefinitions extends TestInstrument implements En {
    public DailyCheckinWebPageStepDefinitions() {

        And("user should land on daily checkin page", () -> {
            bukalapak.dailycheckinWebPage().isOnDailyCheckinPage();
        });

        And("user access How to page", () -> {
            bukalapak.dailycheckinWebPage().tapOnHowtoPage();
            bukalapak.dailycheckinWebPage().isOnHowtoPage();
        });

        When("user claim today reward", () -> {
            bukalapak.dailycheckinWebPage().claimReward();
        });

        And("user should successfully claim reward", () -> {
            bukalapak.dailycheckinWebPage().validateClaimStatus();
        });

        And("user should see future reward", () -> {
            bukalapak.dailycheckinWebPage().tapOnNextClaimed();
        });

        When("user claim reward for tomorrow", () -> {
            bukalapak.dailycheckinWebPage().tapOnNextClaimed();
        });

        And("user should validate pop up claim tomorrow", () -> {
            bukalapak.dailycheckinWebPage().validatePopUpClaimTomorrow();
        });

        And("user access history rewards", () -> {
            bukalapak.dailycheckinWebPage().isOnHistoryGiftPage();
        });

        Then("^user should see reward \"([^\"]*)\" on history$", (String reward) -> {
            bukalapak.dailycheckinWebPage().validateReward(reward);
        });

        When("^user should be able to click cek sekarang on reward voucher \"([^\"]*)\"$", (String reward) -> {
            bukalapak.dailycheckinWebPage().tapOnPakaiVoucherReward(reward);
        });

        And("user validate reward is empty", () -> {
            bukalapak.dailycheckinWebPage().validateEmptyHistory();
        });

        When("user click toggle reminder on landing page", () -> {
            bukalapak.dailycheckinWebPage().tapOnToggleReminder();
        });

        Then("user should see message success to change reminder status", () -> {
            bukalapak.dailycheckinWebPage().validateReminderMessage();
        });

        And("user click toggle reminder me on pop up claim tomorrow$", () -> {
            bukalapak.dailycheckinWebPage().validatePopUpClaimTomorrow();
            bukalapak.dailycheckinWebPage().tapOnToggleReminder();
        });

        And("user validate entry point to bukapoly",() -> {
            bukalapak.dailycheckinWebPage().validateBukapolyEntryPoint();
            bukalapak.dailycheckinWebPage().tapBukapolyEntryPoint();;
        });
    }
}
