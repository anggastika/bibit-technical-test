package bukalapak.stepDefinitions.bee.BukaMobil;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaMobilChatStepDefinitions extends TestInstrument implements En {
    public BukaMobilChatStepDefinitions() {
        Then("user redirect to live chat", () -> {
            bukalapak.bukaMobilChatPage().validateOnBukaMobilChat();
        });
    }
}
