package bukalapak.stepDefinitions.lsf;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class LiveStreamingWebPageStepDefinitions extends TestInstrument implements En {
    public LiveStreamingWebPageStepDefinitions() {

        Then("user is on live streaming page", () -> {
            bukalapak.liveStreamingWebPage().validateLiveStreamLandingPage();
        });

        Then("user validate toast message broadcast not availale", () -> {
            bukalapak.liveStreamingWebPage().validateErrorMessageBroadcast();
        });

        When("user select streaming card on landing page", () -> {
            bukalapak.liveStreamingWebPage().tapOnStreamCard();
        });

        Then("user \"([^\"]*)\" should see video on streaming player", (String user) -> {
            bukalapak.liveStreamingWebPage().validateStreamPlayer(user);
        });

        And("user click deeplink on pinned message live stream$", () -> {
            bukalapak.liveStreamingWebPage().tapOnLinkPinnedMessage();
        });

        When("^user click profile picture on live stream$", () -> {
            bukalapak.liveStreamingWebPage().tapProfilePicture();
        });

        When("^user click close button on streaming player$", () -> {
            bukalapak.liveStreamingWebPage().tapCloseButton();
        });

    }
}
