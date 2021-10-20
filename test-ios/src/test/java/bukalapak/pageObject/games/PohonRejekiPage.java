package bukalapak.pageObject.games;

import bukalapak.data.HelperData;
import bukalapak.data.PohonRejekiData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PohonRejekiPage extends BasePage {

    public PohonRejekiPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userIsOnPohonRejekiPage() {
        changeContext().toWebview();
        verifyElementExist("POHON_REJEKI_GAME_NAME", 25, "Game name is not exist");
        verifyElementExist("POHON_REJEKI_TNC_BUTTON", 10, "TnC button is not exist");
        verifyElementExist("POHON_REJEKI_HISTORY_BUTTON", 10, "History button is not exist");
        verifyElementExist("POHON_REJEKI_BACK_BUTTON", 10, "Back button is not exist");
        verifyElementExist("POHON_REJEKI_PLAYER_NAME", 10, "Player name is not exist");
        verifyElementExist("POHON_REJEKI_TREE", 10, "Tree is not exist");
        verifyElementExist("POHON_REJEKI_SHARE_BUTTON", 10, "Share button is not exist");
        verifyElementExist("POHON_REJEKI_REFERRAL_COUNT_TXT", 10, "Referral count is not exist");
        verifyElementExist("POHON_REJEKI_WATERING_BUTTON", 10, "Watering button is not exist");
        verifyElementExist("POHON_REJEKI_WATERDROP_COUNT_TXT", 10, "Waterdrop count is not exist");
        verifyElementExist("POHON_REJEKI_PROGRESS_BAR", 10, "Progress bar is not exist");
        verifyElementExist("POHON_REJEKI_CURRENT_USER_WATERDROP_TXT", 10, "Current waterdrop is not exist");
        verifyElementExist("POHON_REJEKI_TOTAL_WATERDROP_FOR_NEXT_STAGE_TXT", 10, "Element is not exist");
        verifyElementExist("POHON_REJEKI_LEVEL_UP_INFO", 10, "Level up info is not exist");
        verifyElementExist("POHON_REJEKI_HADIAH_KAMU_TXT", 10, "Hadiah kamu is not exist");
        verifyElementExist("POHON_REJEKI_VOUCHER_IMAGE", 10, "Voucher image is not exist");
        verifyElementExist("POHON_REJEKI_VOUCHER_NAME", 10, "Voucher name is not exist");
        verifyElementExist("POHON_REJEKI_VOUCHER_DESC", 10, "Voucher desc is not exist");
        HelperData.setLastActionPage(new PohonRejekiPage(iosDriver));
    }

    public void validateUserWaterdrop() {
        int currentUserWaterdrop = Integer.parseInt(getTextFromElement("POHON_REJEKI_CURRENT_USER_WATERDROP_TXT"));
        int nextStageWaterdrop = Integer.parseInt(getTextFromElement("POHON_REJEKI_TOTAL_WATERDROP_FOR_NEXT_STAGE_TXT"));
        int totalWaterdropInPot = Integer.parseInt(getTextFromElement("POHON_REJEKI_WATERDROP_COUNT_TXT"));
        PohonRejekiData.setCurrentUserWaterdrop(currentUserWaterdrop);
        PohonRejekiData.setNextStageWaterdrop(nextStageWaterdrop);
        PohonRejekiData.setTotalWaterdropInPot(totalWaterdropInPot);
        int levelUpTotalCount = nextStageWaterdrop - currentUserWaterdrop;
        validateValue().equals(getTextFromElement("POHON_REJEKI_LEVEL_UP_INFO"), "Siram " + levelUpTotalCount + " air untuk ke tahap selanjutnya");
        PohonRejekiData.setLevelUpTotalCount(levelUpTotalCount);
    }

    public void tapOnPohonRejekiWateringPotButton() {
        tapElement("POHON_REJEKI_WATERING_BUTTON");
    }

    public void validatePohonRejekiPopup(String popupName) {
        if (popupName.equals("LevelUp")) {
            verifyElementExist("POHON_REJEKI_LEVEL_UP_POPUP", 5, "Popup is not exist");
            verifyElementExist("POHON_REJEKI_LEVEL_UP_POPUP_TXT");
            verifyElementExist("POHON_REJEKI_LEVEL_UP_POPUP_IMAGE");
            verifyElementExist("POHON_REJEKI_LEVEL_UP_POPUP_STAGE_PROGRESS");
            verifyElementExist("POHON_REJEKI_LEVEL_UP_POPUP_STAGE_ITEM_SELECTED");
            verifyElementExist("POHON_REJEKI_LEVEL_UP_POPUP_CONTINUE_BUTTON");
        } else if (popupName.equals("Harvest")) {
            verifyElementExist("POHON_REJEKI_HARVEST_REWARD_POPUP", 5, "Popup is not exist");
            verifyElementExist("POHON_REJEKI_HARVEST_REWARD_POPUP_IMAGE");
            verifyElementExist("POHON_REJEKI_HARVEST_REWARD_POPUP_TXT");
            verifyElementExist("POHON_REJEKI_HARVEST_REWARD_POPUP_REWARD_IMAGE");
            verifyElementExist("POHON_REJEKI_HARVEST_REWARD_POPUP_CLAIM_REWARD_BUTTON");
            verifyElementExist("POHON_REJEKI_HARVEST_REWARD_POPUP_SAVE_REWARD_BUTTON");
        } else if (popupName.equals("NewTree")) {
            verifyElementExist("POHON_REJEKI_NEW_TREE_POPUP", 5, "Popup is not exist");
            verifyElementExist("POHON_REJEKI_NEW_TREE_POPUP_TXT");
            verifyElementExist("POHON_REJEKI_NEW_TREE_POPUP_REWARD_IMAGE");
            verifyElementExist("POHON_REJEKI_NEW_TREE_POPUP_REWARD_NAME");
            verifyElementExist("POHON_REJEKI_NEW_TREE_POPUP_START_BUTTON");
        }
    }

    public void tapOnPohonRejekiPopupButton(String popupName) {
        if (popupName.equals("LevelUpContinue")) {
            tapElement("POHON_REJEKI_LEVEL_UP_POPUP_CONTINUE_BUTTON");
        } else if (popupName.equals("ClaimHarvestReward")) {
            tapElement("POHON_REJEKI_HARVEST_REWARD_POPUP_CLAIM_REWARD_BUTTON");
        } else if (popupName.equals("SaveHarvestReward")) {
            tapElement("POHON_REJEKI_HARVEST_REWARD_POPUP_SAVE_REWARD_BUTTON");
        } else if (popupName.equals("PlantNewTree")) {
            tapElement("POHON_REJEKI_NEW_TREE_POPUP_START_BUTTON");
        }
    }

    public void checkUserLevelAndWaterdropAfterWatering(String isIncreased) {
        int userWaterdropBeforeWatering = PohonRejekiData.getCurrentUserWaterdrop();
        int nextStageWaterdrop = PohonRejekiData.getNextStageWaterdrop();
        int totalWaterdropInPot = PohonRejekiData.getTotalWaterdropInPot();
        int levelUpTotalCountBeforeWatering = PohonRejekiData.getLevelUpTotalCount();
        delay(5000); //wait until progress bar & waterdrop's value is updated
        int currentWaterdropDisplayed = Integer.parseInt(getTextFromElement("POHON_REJEKI_CURRENT_USER_WATERDROP_TXT"));

        if (isIncreased == null) {
            if (levelUpTotalCountBeforeWatering >= totalWaterdropInPot) {
                // Stay at the same level
                int currentWaterdrop = userWaterdropBeforeWatering + totalWaterdropInPot;
                PohonRejekiData.setCurrentUserWaterdrop(currentWaterdrop);
                int levelUpTotalCountAfterWatering = nextStageWaterdrop - currentWaterdrop;
                validateValue().equals(currentWaterdropDisplayed, currentWaterdrop);
                validateValue().equals(getTextFromElement("POHON_REJEKI_LEVEL_UP_INFO"), "Siram " + levelUpTotalCountAfterWatering + " air untuk ke tahap selanjutnya");
            } else if (isElementExist("POHON_REJEKI_LEVEL_UP_POPUP", 10)) {
                // Level Up and continue
                validatePohonRejekiPopup("LevelUp");
                tapOnPohonRejekiPopupButton("LevelUpContinue");
            } else {
                // Harvest and plant a new tree
                validatePohonRejekiPopup("Harvest");
                tapOnPohonRejekiPopupButton("SaveHarvestReward");
                validatePohonRejekiPopup("NewTree");
                tapOnPohonRejekiPopupButton("PlantNewTree");
            }
        } else {
            int levelUpTotalCount = nextStageWaterdrop - userWaterdropBeforeWatering;
            validateValue().equals(currentWaterdropDisplayed, userWaterdropBeforeWatering);
            validateValue().equals(getTextFromElement("POHON_REJEKI_LEVEL_UP_INFO"), "Siram " + levelUpTotalCount + " air untuk ke tahap selanjutnya");
        }
    }

    public void validateZeroWaterdropInPot() {
        validateValue().equals(getTextFromElement("POHON_REJEKI_WATERDROP_COUNT_TXT"), "0");
    }

    public void validateWateringCountdown() {
        verifyElementExist("POHON_REJEKI_WATERING_COUNT_DOWN");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
