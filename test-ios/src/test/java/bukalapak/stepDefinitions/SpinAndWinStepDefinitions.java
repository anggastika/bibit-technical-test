package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import com.bukalapak.salad.util.LogUtil;
import cucumber.api.java8.En;
import bukalapak.data.games.SpinAndWinData;

public class SpinAndWinStepDefinitions extends TestInstrument implements En {

    public SpinAndWinStepDefinitions() {

        Given("user is in \"spin_and_win\" page", () -> {
            bukalapak.spinAndWinPage().userIsOnSpinAndWinPage();
        });

        And("user tap spin and win TnC button", () -> {
            bukalapak.spinAndWinPage().tapSpinAndWinTnCButton();
        });

        Then("user is on spin and win TnC popup view", () -> {
            bukalapak.spinAndWinPage().validateSpinAndWinTnCPopup();
        });

        And("user tap spin and win History button", () -> {
            bukalapak.spinAndWinPage().tapSpinAndWinHistoryButton();
        });

        Then("user is on spin and win History popup view", () -> {
            bukalapak.spinAndWinPage().validateSpinAndWinHistoryPopup();
        });

        And("user tap spin and win Check Life button", () -> {
            bukalapak.spinAndWinPage().tapSpinAndWinLifeButton();
        });

        Then("user is on spin and win Check Life popup view", () -> {
            bukalapak.spinAndWinPage().validateSpinAndWinLifePopup();
        });

        And("user tap spin and win play button", () -> {
            bukalapak.spinAndWinPage().tapSpinAndWinPlayButton();
        });

        And("user is on spin and win rewards popup view", () -> {
            bukalapak.spinAndWinPage().validateSpinAndWinRewardsPopup();
        });

        And("user tap spin and win check rewards button", () -> {
            bukalapak.spinAndWinPage().tapSpinAndWinCheckRewardsButton();
        });

        And("user will be redirected to spin and win rewards page", () -> {
            String spinRewardType = SpinAndWinData.getSpinRewardType();

            switch (spinRewardType) {
                case "voucher":
                    bukalapak.voucherkuDetailPage().validateVoucherkuRevampDetail();
                    break;
                case "credits":
                    bukalapak.saldoBukalapakPage().validateUserIsInSaldoPage();
                    break;
                case "zonk":
                    LogUtil.info("User got Zonk from Spin and Win reward.");
                    break;
                default:
                    LogUtil.info("Please define spinRewardType value.");
                    break;
            }
        });
    }
}
