package bukalapak.stepDefinitions.martech;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ReferralLinkStepDefinitions extends TestInstrument implements En {

    public ReferralLinkStepDefinitions() {
        Given("user is in \"Ajak-Ajak Berhadiah\" page", () -> {
            bukalapak.referralLinkPage().userOnAjakAjakBerhadiahPage();
        });

        Given("user is in \"Teman Cuan\" page", () -> {
            bukalapak.referralLinkPage().userInTemanCuanPage();
        });

        When("user share referral link ajak - ajak berhadiah to \"([^\"]*)\"", (String type) -> {
            bukalapak.referralLinkPage().clickTambahHadiahButton();
            bukalapak.referralLinkPage().clickShareToSocMed(type);
        });

        When("user share referral link teman cuan to \"([^\"]*)\"", (String type) -> {
            bukalapak.referralLinkPage().clickUndangTemanButton();
            bukalapak.referralLinkPage().clickShareTemanCuanToSocMed(type);
        });

        Then("user should see notification can't share the link", () -> {
            bukalapak.referralLinkPage().verifyElementExist("referral_notif_instagram_app_not_found");
        });

        And("user paste and send referral link", () -> {
            String branchLink = bukalapak.referralLinkPage().getBranchLinkFromReferralLink();
            bukalapak.chatRoomPage().sendChatWithText(branchLink);
            bukalapak.chatRoomPage().tapSendIcon();
        });

        Then("user should be redirected to ajak ajak berhadiah screen", () -> {
            bukalapak.referralLinkPage().validateOpenAjakAjakScreen();
        });

        Then("user is not in \"Teman Cuan\" page", () -> {
            bukalapak.referralLinkPage().userNotInTemanCuanPage();
        });
    }
}
