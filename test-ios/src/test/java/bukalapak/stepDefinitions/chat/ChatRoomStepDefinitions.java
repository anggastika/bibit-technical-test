package bukalapak.stepDefinitions.chat;

import bukalapak.TestInstrument;
import bukalapak.data.CHATData;
import cucumber.api.java8.En;

public class ChatRoomStepDefinitions extends TestInstrument implements En {

    public ChatRoomStepDefinitions(){

        And("user verify last chat has value \"([^\"]*)\"", (String type) -> {
            switch (type) {
                case "blog bukalapak link":
                    bukalapak.chatRoomPage().verifyBlogBukalapakLink(CHATData.blogBukalapakLink);
                    break;
                case "the sent":
                    bukalapak.chatRoomPage().verifyLastChat(CHATData.validMessage);
                    break;
                case "product link":
                    bukalapak.chatRoomPage().verifyLastChat(CHATData.productLink + " - " + CHATData.validMessage);
                    break;
                case "invalid link":
                    bukalapak.chatRoomPage().verifyLastChat(CHATData.invalidDeeplinkMessage + " - " + CHATData.validMessage);
                    break;
                case "black keyword":
                    bukalapak.chatRoomPage().verifyLastChat(CHATData.blackKeyword + " - " + CHATData.validMessage);
                    break;
                case "deeplink router in chat":
                    bukalapak.chatRoomPage().verifyDeeplinkRouterInChat(CHATData.deeplinkRouterInChat + " - " + CHATData.validMessage);
                    break;
                case "grey keyword":
                    bukalapak.chatRoomPage().verifyLastChat(CHATData.greyKeyword + " - " + CHATData.validMessage);
                    break;
                default:
                    break;
            }
        });

        When("user open chat room page with \"([^\"]*)\"", (String arg0) -> {
            bukalapak.chatRoomPage().clickChatRoom(arg0);
        });

        When("user input chat text with \"([^\"]*)\" text", (String messageType) -> {
            switch (messageType) {
                case "blog bukalapak link":
                    bukalapak.chatRoomPage().sendChatWithText(CHATData.blogBukalapakLink);
                    break;
                case "product link":
                    bukalapak.chatRoomPage().sendChatWithText(CHATData.productLink);
                    break;
                case "grey keyword":
                    bukalapak.chatRoomPage().sendChatWithText(CHATData.greyKeyword);
                    break;
                case "black keyword":
                    bukalapak.chatRoomPage().sendChatWithText(CHATData.blackKeyword);
                    break;
                case "deeplink router in chat":
                    bukalapak.chatRoomPage().sendChatWithText(CHATData.deeplinkRouterInChat);
                    break;
                case "test chat as staff":
                    bukalapak.chatRoomPage().sendChatWithText(messageType + " " + CHATData.getDateStamp());
                    break;
                case "normal":
                    bukalapak.chatRoomPage().sendChatWithText(CHATData.validMessage);
                    break;
                default:
                    bukalapak.chatRoomPage().sendChatWithText(messageType);
            }
            bukalapak.chatRoomPage().tapSendIcon();
        });

        When("user click \"([^\"]*)\" link on the chat", (String blidLink) -> {
            bukalapak.chatRoomPage().clickBlidLink(blidLink);
        });

        When("user send ([^\"]*) message", (String type) -> {
            if (type.equals("phising link")) {
                bukalapak.chatRoomPage().sendChatWithText(CHATData.phisingLink);
            } else bukalapak.chatRoomPage().sendChatWithText(type);
            bukalapak.chatRoomPage().tapSendIcon();
        });

        When("user go to chat list", () -> {
            bukalapak.chatRoomPage().goToChatListPage();
        });

        Then("^user is (not )?shown last message with \"([^\"]*)\" and sender as staff of user \"([^\"]*)\"$", (String isNotShown, String message, String username) -> {
            bukalapak.chatRoomPage().verifyStaffChat(message + " " + CHATData.getDateStamp(), username, isNotShown);
        });

        Then("^user is shown last message with sender not as user \"([^\"]*)\"$", (String username) -> {
            bukalapak.chatRoomPage().verifyLastMessageSender(username, false);
        });

        When("receiver should see black keyword warning message", () -> {
            bukalapak.chatRoomPage().receiverVerifyBlackKeyword();
        });

        When("message \"([^\"]*)\" should not present on chat room", (String messageType) -> {
            bukalapak.chatRoomPage().verifyMessageIsNotPresent(messageType);
        });

        Given("user is in \"chat room\" page", () -> {
            bukalapak.chatRoomPage().userOnChatRoom();
        });

        And("user tap on latest valid deeplink message",() -> {
            bukalapak.chatRoomPage().tapOnLatestDeeplinkTextMessage();
        });

        And("^user can see Warning modal displayed", () -> {
            bukalapak.chatRoomPage().isWarningModalDisplayed();
        });

        Given("user is in chat page with merchant with deeplink \"([^\"]*)\"", (String merchantDeeplink) -> {
            bukalapak.merchantPage().userOnMerchantPageWithDeeplink(merchantDeeplink);
            bukalapak.merchantPage().clickOnChatButtonMerchant();
            bukalapak.chatRoomPage().userOnChatRoom();
        });

        When("user reports a user from chat page", () -> {
            bukalapak.reportPage().closePage();
            bukalapak.chatRoomPage().userOnChatRoom();
            bukalapak.chatRoomPage().tapInfoIcon();
            bukalapak.chatRoomPage().tapLaporkan();
        });

        When("user send message with unsold keyword", () -> {
            bukalapak.chatRoomPage().sendUnsoldKeyword();
            bukalapak.chatRoomPage().tapSendIcon();
        });

        Then("user should see set product unsold button", () -> {
            bukalapak.chatRoomPage().validateSetUnsoldButton();
        });

        When("user clicks the set product unsold button in chat room", () -> {
            bukalapak.chatRoomPage().tapSetUnsoldButton();
        });

        When("user skip user badge onboarding", () -> {
            bukalapak.chatRoomPage().skipUserBadgeOnboarding();
        });

        When("user see product attachment preview on chat room", () -> {
            bukalapak.chatRoomPage().verifyProductAttachmentPreview();
        });

        When("user verify last message is \"([^\"]*)\" message", (String type) -> {
            bukalapak.chatRoomPage().verifyLastMessage(type);
        });

        When("user open message template setting", () -> {
            bukalapak.chatRoomPage().openMessageTemplate();
        });

        When("user tap new template message from chat room", () -> {
            bukalapak.chatRoomPage().sendTemplateMessage(CHATData.template);
        });

        When("user verify \"([^\"]*)\" message is sent", (String type) -> {
            bukalapak.chatRoomPage().verifyLastMessage(type);
        });

        When("user verify message template not exist on chat room", () -> {
            bukalapak.chatRoomPage().messageTemplateNotExist(CHATData.template);
        });

        When("user save and tap first message template", () -> {
            bukalapak.chatRoomPage().saveAndTapFirstMessageTemplate();
        });

        When("user click scroll button in chat room page", () -> {
            bukalapak.chatRoomPage().clickScrollButtonChatRoom();
        });

        When("user open user \"([^\"]*)\" account information sheet", (String name) -> {
            bukalapak.chatRoomPage().openAccountInformationSheet(name);
        });

        When("user tap \"([^\"]*)\" on account information sheet", (String option) -> {
            bukalapak.chatRoomPage().tapOptionOnAccountInformation(option);
        });

        When("user switch \"([^\"]*)\" on blokir account sheet", (String option) -> {
            bukalapak.chatRoomPage().switchBlokirOption(option);
        });

        When("user close blokir account sheet", () -> {
            bukalapak.chatRoomPage().closeBlokirSheet();
        });

        When("user verify chat room is blocked", () -> {
            bukalapak.chatRoomPage().verifyChatRoomIsBlocked();
        });

        Then("^(seller|buyer) should see the product link attachment$", (String userType) -> {
            bukalapak.chatRoomPage().verifyProductLinkAttachment(userType.toLowerCase());
        });

        When("user tap \"([^\"]*)\" in produt link attachment", (String buttonName) -> {
            bukalapak.chatRoomPage().tapButtonInChatBubble(buttonName);
        });

        When("user verify ([^\"]*) message on chat room", (String messageType) -> {
            bukalapak.chatRoomPage().verifyMessage(messageType);
        });
    }
}
