package bukalapak.stepDefinitions.chat;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ChatAssistantStepDefinitions extends TestInstrument implements En {

    public ChatAssistantStepDefinitions() {

        When("user is in chat assistant page", () -> {
            bukalapak.chatAssistantPage().isOnChatAssistantPage();
        });

        When("user toggle chat assistant ([^\"]*)", (String type) -> {
            bukalapak.chatAssistantPage().toggleChatAssitant(type);
        });

        When("user edit ([^\"]*) assistant text", (String type) -> {
            bukalapak.chatAssistantPage().editAssistantText(type);
        });

        Then("user verify ([^\"]*) chat assistant text should be updated", (String type) -> {
            bukalapak.chatAssistantPage().assertAssistantText(type);
        });
    }
}
