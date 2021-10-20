package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DailyCheckinWebPage extends BasePage {

    public DailyCheckinWebPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isOnDailyCheckinPage() {
        changeContext().toWebview();
        verifyElementExist("DAILY_CHECKIN_TIME_PERIOD");
        verifyElementExist("DAILY_CHECKIN_DOORPRIZE");
        verifyElementExist("DAILY_CHECKIN_HOWTO");
        HelperData.setLastActionPage(new DailyCheckinWebPage(iosDriver));
    }

    public void tapOnHowtoPage() {
        verifyElementExist("DAILY_CHECKIN_HOWTO");
        tapElement("DAILY_CHECKIN_HOWTO", 5);
    }

    public void isOnHowtoPage() {
        verifyElementExist("DAILY_CHECKIN_HOWTO_PAGE");
        HelperData.setLastActionPage(new DailyCheckinWebPage(iosDriver));
    }

    public void isOnHistoryGiftPage() {
        verifyElementExist("DAILY_CHECKIN_DOORPRIZE");
        tapElement("DAILY_CHECKIN_DOORPRIZE", 5);
    }

    public void validateReward(String reward) {
        verifyElementExist("DAILY_CHECKIN_HISTORY_REWARD");
        verifyElementExist("DAILY_CHECKIN_REWARD_DATE");
        verifyElementExist(constructLocator("DAILY_CHECKIN_REWARD", reward));
        verifyElementExist(constructLocator("DAILY_CHECKIN_EXPIRY_DATE", reward));
        HelperData.setLastActionPage(new DailyCheckinWebPage(iosDriver));
    }

    public void tapOnPakaiVoucherReward(String reward) {
        verifyElementExist(constructLocator("DAILY_CHECKIN_EXPIRY_DATE", reward));
        String status = getText(constructLocator("DAILY_CHECKIN_EXPIRY_DATE", reward));

        if (!status.equals("Kadaluwarsa")) {
            tapElements(constructLocator("DAILY_CHECKIN_VOUCHER_PAKAI_BUTTON", reward), 0, 5);
            changeContext().toNative();
            verifyElementExist("voucherku_card");
        }
    }

    public void claimReward() {
        if (isElementExist("DAILY_CHECKIN_BOX_ACTIVE")) {
            tapElement("DAILY_CHECKIN_BOX_ACTIVE");
        } else if (isElementExist("DAILY_CHECKIN_BOX_TOMORROW")) {
            tapElement("DAILY_CHECKIN_BOX_TOMORROW");
        }
    }

    public void validatePopUpClaimTomorrow() {
        if (isElementExist("DAILY_CHECKIN_POPUP_TOMORROW")) {
            verifyElementExist("DAILY_CHECKIN_POPUP_BUTTON_OKE");
            tapElement("DAILY_CHECKIN_POPUP_BUTTON_OKE");
        }
        HelperData.setLastActionPage(new DailyCheckinWebPage(iosDriver));
    }

    public void validateClaimStatus() {
        if (isElementExist("DAILY_CHECKIN_SUCCESS_CLAIMED")) {
            validateSuccessClaimed();
        } else if (isElementExist("DAILY_CHECKIN_POPUP_DAY7")) {
            validatePopUpDay7();
        } else if (isElementExist("DAILY_CHECKIN_BOX_TOMORROW")) {
            validatePopUpClaimTomorrow();
        } else {
            validateBukaPolyPopUp();
        }
    }

    private void validateSuccessClaimed(){
        verifyElementExist("DAILY_CHECKIN_SUCCESS_CLAIMED");
        verifyElementExist("DAILY_CHECKIN_CLAIMED_REWARD");
    }

    private void validatePopUpDay7(){
        verifyElementExist("DAILY_CHECKIN_BTN_CHECK_DAY7");
        verifyElementExist("DAILY_CHECKIN_BTN_CHECK_LATER");
        tapElement("DAILY_CHECKIN_BTN_CHECK_DAY7");
    }

    private void validateBukaPolyPopUp() {
        verifyElementExist("DAILY_CHECKIN_DAY_1_POP_UP");
        verifyElementExist("DAILY_CHECKIN_DAY_1_POP_UP_BTN_MAIN_SEKARANG");
        verifyElementExist("DAILY_CHECKIN_DAY_1_POP_UP_BTN_NANTI_AJA");
        tapElement("DAILY_CHECKIN_DAY_1_POP_UP_CLOSE_BUTTON");
    }

    public void validateBukapolyEntryPoint() {
        verifyElementExist("DAILY_CHECKIN_ENTRY_POINT_TO_BUKAPOLY");
    }

    public void tapBukapolyEntryPoint() {
        tapElement("DAILY_CHECKIN_ENTRY_POINT_TO_BUKAPOLY");
    }

    public void tapOnNextClaimed() {
        if (isElementExist("DAILY_CHECKIN_BOX_TOMORROW")) {
            validateDisplayed("DAILY_CHECKIN_BOX_TOMORROW");
            tapElement("DAILY_CHECKIN_BOX_TOMORROW", 5);
        }
        HelperData.setLastActionPage(new DailyCheckinWebPage(iosDriver));
    }

    public void validateEmptyHistory() {
        verifyElementExist("DAILY_CHECKIN_EMPTY_REWARD");
        verifyElementExist("DAILY_CHECKIN_EMPTY_REWARD_BTN");
        tapElement("DAILY_CHECKIN_EMPTY_REWARD_BTN");
        isOnDailyCheckinPage();
    }

    public void tapOnToggleReminder() {
        tapElement("DAILY_CHECKIN_TOGGLE_BUTTON", 5);
    }

    public void validateReminderMessage() {
        validateExist("DAILY_CHECKIN_REMINDER_TEXT", 5);
        HelperData.setLastActionPage(new DailyCheckinWebPage(iosDriver));
    }


    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
