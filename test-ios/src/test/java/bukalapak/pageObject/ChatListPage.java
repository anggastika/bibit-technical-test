package bukalapak.pageObject;

import bukalapak.data.CHATData;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import static bukalapak.TestInstrument.dotenv;

/**
 * Created by dickyedgardo on 23/10/18.
 */
public class ChatListPage extends BasePage {

    public ChatListPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void clickChatMenu() {
        tapCenterOfElement("chat_icon");
        HelperData.setLastActionPage(new ChatListPage(iosDriver));
    }

    public void userOnChatList() {
        if (isElementVisible("chat_list_button_oke", 20)) {
            tapElement("chat_list_button_oke");
        }
        waitForVisibilityOf("chat_list_title", 20);
        HelperData.setLastActionPage(new ChatListPage(iosDriver));
    }

    public void switchChatStaffMode(String mode) {
        tapElement("chat_list_settings_button");
        if (mode.equals("staff") && isElementVisible("chat_list_aktifkan_chat_karyawan_button")) {
            tapElement("chat_list_aktifkan_chat_karyawan_button");
            validateDisplayed("chat_karyawan_title");
        } else if (mode.equals("user") && isElementVisible("chat_list_nonaktifkan_chat_karyawan_button")) {
            tapElement("chat_list_nonaktifkan_chat_karyawan_button");
            userOnChatList();
        } else {
            tapElement("chat_list_dismiss_setting_section");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void assertChatListSettings() {
        verifyElementExist("chat_list_asisten_chat_button");
        verifyElementExist("chat_list_template_pesan_button");
    }

    public void clickTab(String tabTitle){
        String tabTitleElement = constructLocator("chat_list_tab_title", tabTitle);
        tapElement(tabTitleElement);
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void assertPartnerNotPresent(String partnerName){
        String tmpPartnerName = dotenv.get(partnerName);
        String elm_partnerName = constructLocator("chat_list_elm_partnerName", tmpPartnerName);
        assertFalse(isElementVisible(elm_partnerName), "Partner " + tmpPartnerName + " is not present on chat list");
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void checkChatPage(){
        assertTrue(isElementVisible("chat_list_name_chat"), "Tidak Berhasil Redirect ke Halaman Chat");
    }

    private String getLastMessageOnChatListText() {
        return getText("chat_list_last_message_text");
    }

    public void verifyLastMessage(String type) {
        switch (type) {
            case "black keyword":
                assertEquals(CHATData.blackKeyword + " - " + CHATData.validMessage, getLastMessageOnChatListText());
                break;
            case "grey keyword":
                assertEquals(CHATData.greyKeyword + " - " + CHATData.validMessage, getLastMessageOnChatListText());
                break;
            default:
                Assert.fail("Invalid message type : " + type);
                break;
        }
    }

    public void verifyBlackKeyword() {
        assertEquals(CHATData.blackKeywordWarningMessage, getLastMessageOnChatListText());
    }

    public void verifyMessageIsNotPresent(String type) {
        if ("grey keyword".equals(type)) {
            assertFalse(getLastMessageOnChatListText().equals(CHATData.greyKeyword + " - " + CHATData.validMessage), "Grey keyword is present");
        } else {
            Assert.fail("Invalid type : " + type);
        }
    }

    public void verifySpinAndWinChat() {
        isElementVisible("chat_spin_and_win_text");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyOnBoardingSebarPromosi() {
        if (isElementVisible("chat_room_sebar_promosi_onboarding", 5)) {
            validateDisplayed("chat_room_sebar_promosi_onboarding");
            tapElement("chat_room_sebar_promosi_oke_btn");
        } else {
            validateNotExist("chat_room_sebar_promosi_onboarding", 3, "One Time sebar promosi on boarding not display");
        }
        HelperData.setLastActionPage(new ChatListPage(iosDriver));
    }

    public void verifyIconSebarPromosi(String state) {
        if (state == null) {
            verifyOnBoardingSebarPromosi();
            waitForVisibilityOf("chat_room_not_sebar_promosi_icon_btn", 10);
            validateDisplayed("chat_room_sebar_promosi_icon_btn");
        } else {
            validateValue().equalsFalse(isElementExist("chat_room_not_sebar_promosi_icon_btn"));
        }
        HelperData.setLastActionPage(new ChatListPage(iosDriver));
    }

    public void tapIconSebarPromosi() {
        tapElement("chat_room_sebar_promosi_icon_btn");
    }

    public void openChatSetting() {
        validateDisplayed("chat_list_setting_button");
        int size = getElements("chat_list_setting_button").size();
        tapElements("chat_list_setting_button", size-1);
    }

    public void chooseSettingOption(String option) {
        validateDisplayed(constructLocator("chat_list_setting_option_button", option));
        tapElement(constructLocator("chat_list_setting_option_button", option));
        HelperData.setLastActionPage(new ChatAssistantPage(iosDriver));
    }

    public void verifyOnBlockedUserList(String user) {
        String username = dotenv.get(user + "_USERNAME");
        waitForVisibilityOf(constructLocator("chat_list_blocked_user_list", username), 10);
    }

    public void unblockUserFromBlockedUserList(String user) {
        String username = dotenv.get(user + "_USERNAME");
        tapElement(constructLocator("chat_list_blocked_user_list", username));
    }

    public void searchMessage(String message) {
        waitForVisibilityOf("chat_list_search_button", 10);
        typeValue("chat_list_search_button", message);
        hideKeyboard("Search");
    }

    public void verifySearchResultMatchApiResponse() {
        String content = CHATData.getDynamicChatData();
        String username = CHATData.getSecondDynamicChatData();
        waitForVisibilityOf(constructLocator("chat_list_search_result_message", content, username), 10);
        HelperData.setLastActionPage(new ChatListPage(iosDriver));
    }

    public void openSearchResult(String type) {
        String username = CHATData.getSecondDynamicChatData();
        waitForVisibilityOf(constructLocator("chat_list_search_result_message", type, username));
        tapElement(constructLocator("chat_list_search_result_message", type, username));
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void tapBatalkanButton() {
        waitForVisibilityOf("chat_list_batalkan_button", 10);
        tapElement("chat_list_batalkan_button");
    }
}
