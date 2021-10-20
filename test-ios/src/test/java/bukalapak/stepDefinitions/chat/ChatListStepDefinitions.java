package bukalapak.stepDefinitions.chat;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ChatListStepDefinitions extends TestInstrument implements En {

    public ChatListStepDefinitions() {
        When("user verifies chat list settings menu element", () -> {
            bukalapak.chatListPage().assertChatListSettings();
        });

        When("user go to tab \"([^\"]*)\"", (String tabTitle) -> {
            bukalapak.chatListPage().clickTab(tabTitle);
        });

        Then("^partner \"([^\"]*)\" does not exist on chat list$", (String partnerName) -> {
            bukalapak.chatListPage().assertPartnerNotPresent(partnerName);
        });
          
        Then("^user should see chat page$", () -> {
            bukalapak.chatListPage().checkChatPage();
        });

        When("^user switches chat mode to (staff|user)$", (String mode) -> {
            bukalapak.chatListPage().switchChatStaffMode(mode);
        });

        When("user verify last chat in chat list contains \"([^\"]*)\" message", (String type) -> {
            bukalapak.chatListPage().verifyLastMessage(type);
        });

        When("black keyword warning should be shown in chat list", () -> {
            bukalapak.chatListPage().verifyBlackKeyword();
        });

        When("message \"([^\"]*)\" should not present on chat list", (String type) -> {
            bukalapak.chatListPage().verifyMessageIsNotPresent(type);
        });

        Then("^user should be redirect to chat page$", () -> {
            bukalapak.chatListPage().userOnChatList();
        });

        Given("user is in \"chat list\" page", () -> {
            bukalapak.chatListPage().userOnChatList();
        });

        When("user navigate to \"chat list\" page", () -> {
            bukalapak.chatListPage().clickChatMenu();
        });

        Then("user see spin and win chat in top of the chat list", () -> {
            bukalapak.chatListPage().verifySpinAndWinChat();
        });

        And("^user can see one time onboarding sebar promosi", () -> {
            bukalapak.chatListPage().verifyOnBoardingSebarPromosi();
        });

        And("^user( not)? see icon sebar promosi in chat page$", (String state) -> {
            bukalapak.chatListPage().verifyIconSebarPromosi(state);
        });

        When("^user can tap icon sebar promosi", () -> {
            bukalapak.chatListPage().tapIconSebarPromosi();
        });

        When("user open chat setting", () -> {
            bukalapak.chatListPage().openChatSetting();
        });

        When("user choose \"([^\"]*)\" on chat option sheet", (String option) -> {
            bukalapak.chatListPage().chooseSettingOption(option);
        });

        When("user verify \"([^\"]*)\" exist on blocked user list", (String user) -> {
            bukalapak.chatListPage().verifyOnBlockedUserList(user);
        });

        When("user unblock user \"([^\"]*)\" from blocked user list", (String user) -> {
            bukalapak.chatListPage().unblockUserFromBlockedUserList(user);
        });

        When("user search ([^\"]*) message", (String type) -> {
            bukalapak.chatListPage().searchMessage(bukalapak.chatRoomPage().getMessageByType(type));
        });

        When("user \"([^\"]*)\" verify ([^\"]*) message shown on search result", (String user, String type) -> {
            bukalapak.apiCall().setUserAuthv4(user);
            bukalapak.apiCall().getSpecificMessage(bukalapak.chatRoomPage().getMessageByType(type));
            bukalapak.chatListPage().verifySearchResultMatchApiResponse();
        });

        When("user open ([^\"]*) message shown on search result", (String type) -> {
            bukalapak.chatListPage().openSearchResult(bukalapak.chatRoomPage().getMessageByType(type));
        });

        When("user tap batalkan button on chat result", () -> {
            bukalapak.chatListPage().tapBatalkanButton();
        });
    }
}
