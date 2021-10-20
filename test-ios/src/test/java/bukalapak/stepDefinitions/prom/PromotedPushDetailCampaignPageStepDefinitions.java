package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushDetailCampaignPageStepDefinitions extends TestInstrument implements En {
    public PromotedPushDetailCampaignPageStepDefinitions() {
        And("^user go to promoted push campaign page with title \"([^\"]*)\"$", (String pageTitle) -> {
            bukalapak.promotedPushDetailCampaignPage().goToPromotedPushCampaignPage(pageTitle);
        });

        And("^user is in Promoted Push Campaign Page with title \"([^\"]*)\"$", (String pageTitle) -> {
            bukalapak.promotedPushDetailCampaignPage().verifyPromotedPushCampaignTitlePage(pageTitle);
        });

        And("^user is in Promoted Push Campaign Page with products contain \"([^\"]*)\"$", (String productName) -> {
            bukalapak.promotedPushDetailCampaignPage().verifyPromotedPushCampaignProducts(productName);
        });

        And("user deletes the Promoted Grup campaign", () -> {
            bukalapak.promotedPushDetailCampaignPage().deletePromotedCampaign();
        });

        When("^user switches toggle to \"([^\"]*)\" on detail promoted grup campaign$", (String state) -> {
            bukalapak.promotedPushDetailCampaignPage().switchesToggle(state);
        });

        And("^user tap on back button from detail promoted grup campaign$", () -> {
            bukalapak.promotedPushDetailCampaignPage().tabBackButtonFromDetailCampaign();
        });
    }
}
