package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.OCAData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DailyTaskWebPage extends BasePage {

    protected static final int MEDIUM_TIMEOUT = 15;

    public DailyTaskWebPage(IOSDriver<IOSElement> iosDriver) { super (iosDriver); }

    public void userIsOnDailyTaskPage() {
        changeContext().toWebview();
        validateExist("DAILY_TASK_HEADER", MEDIUM_TIMEOUT, "Event Not Active!");
        checkOnRewardPopUp();
        validateExist("DAILY_TASK_TITLE", MEDIUM_TIMEOUT);
        validateExist("DAILY_TASK_MISSION_ACTIVE", MEDIUM_TIMEOUT);
        validateExist("DAILY_TASK_COUNTDOWN_TIMER");
        validateExist("DAILY_TASK_MISSION_TITLE");
        validateExist("DAILY_TASK_MISSION_DESCRIPTION");
        HelperData.setLastActionPage(new DailyTaskWebPage(iosDriver));
    }

    private void checkOnRewardPopUp() {
        if(isElementExist("DAILY_TASK_REWARD_POP_UP")) {
            validateExist("DAILY_TASK_REWARD_POP_UP_TITLE");
            validateExist("DAILY_TASK_REWARD");
            validateExist("DAILY_TASK_REWARD_BONUS_LAGI");
            validateExist("DAILY_TASK_REWARD_LIHAT_KOLEKSI");
            tapElement("DAILY_TASK_REWARD_BONUS_LAGI");
        }
    }

    public void tapOnLanjutMission() {
        if(isElementExist("DAILY_TASK_LANJUT_BUTTON")) {
            validateExist("DAILY_TASK_SUBTITLE");
            OCAData.setCategoryMission(getText("DAILY_TASK_SUBTITLE"));
            tapElement("DAILY_TASK_LANJUT_BUTTON");
        } else {
            // no next mission
            validateExist("DAILY_TASK_SELESAI_BUTTON+");
        }
    }

    public void userGoToValidPage() {
        changeContext().toNative();
        String category = OCAData.getCategoryMission();

        if(category.contains("Kategori")) {
            verifyElementDisplayed("category_page_navbar_title");
            verifyElementDisplayed("alchemy_navbar_back_button");
            verifyElementDisplayed("category_page_category_list");
        } else {
            if (isElementVisible("merchant_page_flash_deal_onboarding_button", MEDIUM_TIMEOUT)) {
                tapElement("merchant_page_flash_deal_onboarding_button");
            }
            verifyElementDisplayed("merchant_page_chat_button");
        }
        HelperData.setLastActionPage(new DailyTaskWebPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
