package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.games.SpinAndWinData;
import bukalapak.utils.Constants;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SpinAndWinPage extends BasePage {

    public SpinAndWinPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userIsOnSpinAndWinPage() {
        if (isElementExist("spin_and_win_back_btn", Constants.THIRTY_SECS_TIMEOUT)) {
            verifyElementExist("spin_and_win_tnc_btn");
            validateEnabled("spin_and_win_title");
        } else if (isElementExist("spin_and_win_empty_rewards_popup_title", Constants.FIVE_SECS_TIMEOUT)) {
            validateSpinAndWinEmptyRewardsPopup();
            tapSpinAndWinEmptyRewardPopupOkBtn();
            LogUtil.info("Spin and Win reward is empty.");
        }
        HelperData.setLastActionPage(new SpinAndWinPage(iosDriver));
    }

    public void validateSpinAndWinEmptyRewardsPopup() {
        String emptyRewardsPopupText = getTextFromElement("spin_and_win_empty_rewards_popup_text");

        validateValue().contains("Hadiah sudah habis untuk hari ini",
                emptyRewardsPopupText);
    }

    public void tapSpinAndWinEmptyRewardPopupOkBtn() {
        tapElement("spin_and_win_empty_rewards_ok_btn");
    }

    public void tapSpinAndWinTnCButton() {
        tapElement("spin_and_win_tnc_btn");
    }

    public void validateSpinAndWinTnCPopup() {
        verifyElementExist("spin_and_win_tnc_title", Constants.FIVE_SECS_TIMEOUT, "Element is not exist");
        verifyElementExist("spin_and_win_tnc_content");
        verifyElementExist("spin_and_win_tnc_back_btn");
    }

    public void tapSpinAndWinHistoryButton() {
        tapElement("spin_and_win_history_btn");
    }

    public void validateSpinAndWinHistoryPopup() {
        verifyElementExist("spin_and_win_history_title", Constants.FIVE_SECS_TIMEOUT, "Element is not exist");
        verifyElementExist("spin_and_win_history_content");
        verifyElementExist("spin_and_win_history_back_btn");
    }

    public void tapSpinAndWinLifeButton() {
        tapElement("spin_and_win_life_btn");
    }

    public void validateSpinAndWinLifePopup() {
        verifyElementExist("spin_and_win_check_life_title", Constants.FIVE_SECS_TIMEOUT, "Element is not exist");
        verifyElementExist("spin_and_win_check_life_content");
        verifyElementExist("spin_and_win_check_life_ok_btn");
    }

    public void tapSpinAndWinPlayButton() {
        tapElement("spin_and_win_play_btn");
    }

    public void validateSpinAndWinCreditsPopup() {
        String creditRewardValue = getTextFromElement("spin_and_win_credits_popup_title");

        validateValue().contains("Kamu mendapatkan Credits " + creditRewardValue,
                getTextFromElement("spin_and_win_credits_popup_body_text"));
    }

    public void validateSpinAndWinZonkPopup() {
        String zonkPopupTitleText = getTextFromElement("spin_and_win_zonk_popup_title_text");
        String zonkPopupBodyText = getTextFromElement("spin_and_win_zonk_popup_body_text");

        validateValue().contains("Sayang Sekali", zonkPopupTitleText);
        validateValue().contains("Kamu belum beruntung nih kali ini", zonkPopupBodyText);
    }

    public void validateSpinAndWinRewardsPopup() {
        if (isElementExist("spin_and_win_voucher_popup_title", 10)) {
            verifyElementExist("spin_and_win_voucher_popup_title_text");
            SpinAndWinData.setSpinRewardType("voucher");
        } else if (isElementExist("spin_and_win_credits_popup_title")) {
            validateSpinAndWinCreditsPopup();
            verifyElementExist("spin_and_win_credits_popup_title_text");
            SpinAndWinData.setSpinRewardType("credits");
        } else if (isElementExist("spin_and_win_zonk_popup_title")) {
            validateSpinAndWinZonkPopup();
            SpinAndWinData.setSpinRewardType("zonk");
        }
    }

    public void tapSpinAndWinCheckRewardsButton() {
        tapElement("spin_and_win_rewards_check_btn");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
