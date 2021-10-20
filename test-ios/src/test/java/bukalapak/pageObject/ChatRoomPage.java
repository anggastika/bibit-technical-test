package bukalapak.pageObject;

import bukalapak.data.CHATData;
import bukalapak.data.HelperData;
import bukalapak.data.ProductDetailData;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

import static bukalapak.TestInstrument.dotenv;

/**
 * Created by dickyedgardo on 23/10/18.
 */
public class ChatRoomPage extends BasePage {

    private static final String[] UNSOLD_KEYWORD = {"habis", "kosong", "hbs", "hbis", "habs", "ksong", "Habis", "Kosong", "Hbs", "Hbis", "Habs", "Ksong"};

    public ChatRoomPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void clickChatRoom(String username) {
        String chatroom = constructLocator("chat_room_elm_chatroom", dotenv.get(username));
        waitForElementClickable(chatroom, 10);
        tapElement(chatroom);
        clickPopUpOnBoarding();
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void userOnChatRoom() {
        clickPopUpOnBoarding();

        if (isElementVisible("chat_room_text_area_field", 10)) {
            verifyElementExist("chat_room_text_area_field");
            verifyElementExist("chat_room_attachment_menu_button");
        } else {
            // revamp
            verifyElementExist("chat_room_text_area_revamp_field");
            verifyElementExist("chat_room_attachment_menu_revamp_button");
        }
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void verifyLastChat(String expectedContent) {
        waitForVisibilityOf("value_" + expectedContent);
        String elm_lastchat = constructLocator("chat_room_last_chat_content", expectedContent);
        assertTrue(isElementVisible(elm_lastchat), expectedContent + " is not present on last chat bubble");
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void sendChatWithText(String message) {
        typeAndEnterValueWithTimeOut("chat_room_text_area_field", message);
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void tapSendIcon() {
        tapElement("chat_room_send_chat_button");
        waitFor(1);
    }

    private void clickPopUpOnBoarding() {
        if (isElementVisible("chat_room_onboarding_label")) {
            tapElement("chat_room_onboarding_label");
        }

        if (isElementVisible("chat_room_onboarding_lanjut_label")) {
            tapElement("chat_room_onboarding_lanjut_label");
            if(isElementVisible("chat_room_onboarding_kembali_label", 5)) {
                tapElement("chat_room_onboarding_lanjut_label");
            }
            if (isElementVisible("chat_room_onboarding_button", 10)) {
                tapElement("chat_room_onboarding_button");
            }
        }
    }

    public void verifyStaffChat(String message, String username, String isNotShown) {
        String chatStaffLocator = constructLocator("chat_room_staffchat", dotenv.get(username) + " Karyawan Chat", message);
        if (isNotShown != null) {
            validateNotDisplayed(chatStaffLocator);
        } else {
            validateDisplayed(chatStaffLocator);
        }
    }

    public void verifyLastMessageSender(String username, boolean isShown) {
        assertEquals(isShown, isElementVisible(constructLocator("chat_room_lastmsg_user", username)));
    }

    public void goToChatListPage() {
        openDeepLink("https://www.bukalapak.com/messages");
        HelperData.setLastActionPage(new ChatListPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void receiverVerifyBlackKeyword() {
        Pattern pattern = Pattern.compile(".*" + CHATData.blackKeywordWarningMessage + ".*");
        Matcher matcher = pattern.matcher(getElementValue("chat_room_last_message_text"));
        assertTrue(matcher.find(), "Last message not containt black keyword warning message");
    }

    public void verifyMessageIsNotPresent(String messageType) {
        switch (messageType) {
            case "grey keyword":
                assertFalse(getTextFromElement("chat_room_last_message_text").equals(CHATData.greyKeyword + " - " + CHATData.validMessage), "Grey keyword is present");
                break;
            default:
                Assert.fail("Invalid message type : " + messageType);
                break;
        }
    }

    public void tapOnLatestDeeplinkTextMessage() {
        waitForVisibilityOf("chat_room_last_message_link", 15);
        tapElement("chat_room_last_message_link");
    }

    public void isWarningModalDisplayed() {
        verifyElementExist("chat_room_warning_modal_title");
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void verifyDeeplinkRouterInChat(String expectedContent) {
        waitForVisibilityOf("chat_room_last_message_link",15);
        String elm_deeplinkrouterlastchat = constructLocator("chat_room_last_message_text", expectedContent);
        assertTrue(isElementVisible(elm_deeplinkrouterlastchat), expectedContent + " is not present on last chat bubble");
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void verifyBlogBukalapakLink(String expectedContent) {
        waitForVisibilityOf("chat_room_last_message_link", 15);
        String elm_blogbukalapaklastchat = constructLocator("chat_room_last_message_text", expectedContent);
        assertTrue(isElementVisible(elm_blogbukalapaklastchat), expectedContent + " is not present on last chat bubble");
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void tapInfoIcon() {
        tapElement("chat_room_info_icon");
    }

    public void tapLaporkan() {
        validateDisplayed("chat_room_report_menu");
        tapElement("chat_room_report_menu");
    }

    public void clickBlidLink(String blidLink) {
        String link = constructLocator("chat_room_blid_link", blidLink);
        tapElement(link);
    }

    public void sendUnsoldKeyword() {
        int rand = new Random().nextInt(UNSOLD_KEYWORD.length);
        String message = "lagi " + UNSOLD_KEYWORD[rand] + " gan";
        sendChatWithText(message);
    }

    public void validateSetUnsoldButton() {
        if (!isElementClickable("chat_room_unsold_text")) {
            swipeToDirection(Direction.UP);
        }
        validateDisplayed("chat_room_unsold_text");
    }

    public void tapSetUnsoldButton() {
        waitForVisibilityOf("chat_room_unsold_text", 5);
        tapElement("chat_room_unsold_text");
    }

    public void skipUserBadgeOnboarding() {
        if (isElementVisible("chat_room_user_badge_onboarding_first")) {
            tapElement("chat_room_user_badge_onboarding_lanjut_button");
            waitForVisibilityOf("chat_room_user_badge_onboarding_second", 5);
            tapElement("chat_room_user_badge_onboarding_lanjut_button");
            waitForVisibilityOf("chat_room_user_badge_onboarding_third", 5);
            tapElement("chat_room_onboarding_button");
        }
    }

    public void verifyProductAttachmentPreview() {
        validateDisplayed(constructLocator("chat_room_elm_chatroom", ProductDetailData.getProductName()));
        validateDisplayed(constructLocator("chat_room_elm_chatroom", ProductDetailData.getProductPrice()));
    }

    public void verifyLastMessage(String type) {
        switch (type) {
            case "product":
                validateDisplayed(constructLocator("chat_room_last_message_product", CHATData.validMessage, ProductDetailData.getProductName()));
                validateDisplayed(constructLocator("chat_room_last_message_product", CHATData.validMessage, ProductDetailData.getProductPrice()));
                break;
            case "new template":
                validateDisplayed(constructLocator("chat_room_last_message_text_contains", CHATData.template));
                break;
            case "normal":
                waitForVisibilityOf(constructLocator("chat_room_last_message_text_contains", CHATData.validMessage), 10);
                break;
            case "saved":
                waitForVisibilityOf(constructLocator("chat_room_last_message_text_contains", CHATData.getDynamicChatData()), 10);
                break;
            case "grey keyword warning":
                validateDisplayed(constructLocator("chat_room_last_message_text_contains", CHATData.greyKeywordWarningMessage));
                break;
            case "black keyword warning":
                validateDisplayed(constructLocator("chat_room_last_message_text_contains", CHATData.blackKeywordWarningMessage));
                openBlackKeywordBubble();
                break;
            case "black keyword":
                waitForVisibilityOf(constructLocator("chat_room_last_message_text_contains", CHATData.blackKeyword), 10);
                break;
            default:
                validateDisplayed(constructLocator("chat_room_last_message_text_contains", CHATData.validMessage, ProductDetailData.getProductName()));
        }
    }

    public void openMessageTemplate() {
        validateDisplayed("chat_room_message_template_setting");
        tapElement("chat_room_message_template_setting");
        HelperData.setLastActionPage(new MessageTemplatePage(iosDriver));
    }

    public void sendTemplateMessage(String template) {
        validateDisplayed(constructLocator("chat_room_message_template", template));
        tapElement(constructLocator("chat_room_message_template", template));
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void messageTemplateNotExist(String template) {
        validateNotDisplayed(constructLocator("chat_room_message_template", template));
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void saveAndTapFirstMessageTemplate() {
        validateDisplayed("chat_room_message_first_message_template");
        String messageTemplate = getTextFromElement("chat_room_message_first_message_template");
        CHATData.setDynamicChatData(messageTemplate);
        tapElement("chat_room_message_first_message_template");
    }

    public void openBlackKeywordBubble() {
        validateDisplayed(constructLocator("chat_room_last_message_black_kw_button", "Tampilkan Pesan"));
        tapElement(constructLocator("chat_room_last_message_black_kw_button", "Tampilkan Pesan"));
        validateDisplayed(constructLocator("chat_room_last_message_text_contains", CHATData.blackKeyword));
        validateDisplayed(constructLocator("chat_room_last_message_black_kw_button", "Laporkan"));
        validateDisplayed(constructLocator("chat_room_last_message_black_kw_button", "Batalkan"));
        tapElement(constructLocator("chat_room_last_message_black_kw_button", "Batalkan"));
        validateNotDisplayed(constructLocator("chat_room_last_message_text_contains", CHATData.blackKeyword));
    }

    public void clickScrollButtonChatRoom() {
        if (isElementVisible("chat_room_scroll_button", 10)) {
            tapElement("chat_room_scroll_button");
        }
    }

    public void openAccountInformationSheet(String name) {
        String username = dotenv.get(name+"_USERNAME");
        validateDisplayed(constructLocator("chat_room_account_information_sheet", username));
        tapElement(constructLocator("chat_room_account_information_sheet", username));
    }

    public void tapOptionOnAccountInformation(String option) {
        validateDisplayed(constructLocator("chat_room_account_information_sheet_option", option));
        tapElement(constructLocator("chat_room_account_information_sheet_option", option));
    }

    public void switchBlokirOption(String option) {
        validateDisplayed(constructLocator("chat_room_blokir_switch", option));
        tapElement(constructLocator("chat_room_blokir_switch", option));
    }

    public void closeBlokirSheet() {
        waitForVisibilityOf("chat_room_blokir_sheet_close_button");
        tapElement("chat_room_blokir_sheet_close_button");
    }

    public void verifyChatRoomIsBlocked() {
        validateDisplayed("chat_room_block_input_view");
        validateValue().equalsFalse(isElementVisible("chat_room_text_area_revamp_field"));
    }

    public String getMessageByType(String type) {
        String message = "";
        switch (type) {
            case "text":
                message = CHATData.validMessage;
                break;
            case "invalid link":
                message = CHATData.invalidDeeplinkMessage;
                break;
            case "outside link wording":
                message = CHATData.linkDihapusWording;
                break;
            case "product link":
                message = CHATData.productLink;
                break;
            case "blog bukalapak link":
                message = CHATData.blogBukalapakLink;
                break;
            case "black keyword":
                message = CHATData.blackKeyword;
                break;
            case "black keyword warning":
                message = CHATData.blackKeywordWarningReply;
                break;
            case "black keyword warning text":
                message = CHATData.blackKeywordWarningMessage;
                break;
            case "grey keyword":
                message = CHATData.greyKeyword;
                break;
            case "test staff chat":
                message = type + " " + CHATData.getDateStamp();
                break;
            case "test user chat":
                message = type + " " + CHATData.getDateStamp();
                break;
            case "valid deeplink":
                message = CHATData.validDeeplinkMessage;
                break;
            case "phising link":
                message = CHATData.phisingLink;
                break;
            case "replied":
                message = CHATData.repliedMessage;
                break;
            case "default template":
                message = "Apakah stok masih ada";
                break;
            default:
                message = CHATData.validMessage;
        }
        return message;
    }

    public void verifyProductLinkAttachment(String userType) {
        validateDisplayed("chat_room_product_image");
        validateDisplayed("chat_room_product_name");
        validateDisplayed("chat_room_product_price");

        if (userType.equals("buyer")) {
            validateDisplayed("chat_room_product_card_buy_btn");
        }
    }

    public void tapButtonInChatBubble(String buttonName) {
        switch (buttonName.toLowerCase()) {
            case "product image" :
                tapElement("chat_room_product_image");
                break;
            case "beli" :
                tapElement("chat_room_product_card_buy_btn");
                break;
            default:
                LogUtil.error("Please input the right button name");
        }
    }

    public void verifyMessage(String messageType) {
        waitForVisibilityOf(constructLocator("chat_room_message_text_contains", getMessageByType(messageType)), 10);
    }
}
