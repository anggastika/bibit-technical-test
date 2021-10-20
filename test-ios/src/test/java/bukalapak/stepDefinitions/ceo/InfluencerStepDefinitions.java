package bukalapak.stepDefinitions.ceo;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class InfluencerStepDefinitions extends TestInstrument implements En {

    public InfluencerStepDefinitions() {
        Given("user is in \"influencer\" page", () -> {
            bukalapak.influencerPage().userOnInfluencerPage();
        });

        When("message \"([^\"]*)\" in Influencer Page should be display", (String message) -> {
            bukalapak.influencerPage().assertTrue(bukalapak.iOSBasePage().isElementVisible("profil_tidak_ditemukan_label"));
        });

        When("^navbar contain valid influencer name \"([^\"]*)\"$", (String influencerName) -> {
            bukalapak.influencerPage().assertEquals(influencerName, bukalapak.iOSBasePage().getTextFromElement("influencer_page_navbar"));
        });

        When("^influencer name contain valid influencer name \"([^\"]*)\"$", (String influencerName) -> {
            bukalapak.influencerPage().assertEquals(influencerName, bukalapak.iOSBasePage().getTextFromElement("influencer_name_text"));
        });

        When("^navbar is sticky and contain valid influencer name \"([^\"]*)\"$", (String influencerName) -> {
            bukalapak.iOSBasePage().swipeDownToElement("inspiration_name_text");
            bukalapak.influencerPage().assertEquals(influencerName, bukalapak.iOSBasePage().getTextFromElement("influencer_page_navbar"));
        });

        When("user get total like of influencer", () -> {
            bukalapak.influencerPage().setTotalLike();
        });

        When("user like at (\\d+) inspiration post on influencer page", (Integer index) -> {
            bukalapak.iOSBasePage().swipeDownToElement("inspiration_name_text");
            bukalapak.inspirationPage().likeAtPost(index);
        });

        When("total like of influencer not increase automatically", () -> {
            bukalapak.iOSBasePage().swipeUpToElement("influencer_total_like_text");
            //Assert.assertNotEquals(InfluencerPage.getTotalLike() + 1, InfluencerPage.getTotalLike());
        });

        When("user see valid influencer description", () -> {
            bukalapak.influencerPage().checkInfluencerDescription();
        });

        When("influencer has (\\d+) valid total posts", (Integer totalPost) -> {
            bukalapak.influencerPage().checkTotalPosts(totalPost);
        });

        When("influencer has valid posts related to influencer", (DataTable arg0) -> {
            bukalapak.influencerPage().checkPostContent(arg0);
        });

        When("empty post message for influencer \"([^\"]*)\" should be displayed", (String influencerName) -> {
            bukalapak.influencerPage().checkEmptyPostMessage(influencerName);
        });

        When("related influencer total should more than 3 and less than 10", () -> {
            bukalapak.influencerPage().checkTotalRelatedInfluencer();
        });

        When("user click at related influencer", () -> {
            bukalapak.iOSBasePage().tapElement("related_influencer_name_label");
        });

        // hooks
        After(new String[]{"@unlike-first-post"}, () -> {
            bukalapak.iOSBasePage().swipeDownToElement("inspiration_name_text");
            bukalapak.inspirationPage().likeAtPost(1);
        });

        After(new String[]{"@delete-created-post-53"}, () -> {
            bukalapak.apiCall().deleteCreatedInspirationPosts("53");
        });

        After(new String[]{"@back-to-production"}, () -> {
            bukalapak.iOSBasePage().backToHomePage();
            bukalapak.akunPage().changeStagingEnv("production");
        });
    }
}
