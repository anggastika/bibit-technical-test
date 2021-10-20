package bukalapak.stepDefinitions.noob;
import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ClaimRewardsOutsideAppsSteps extends TestInstrument implements En {

    public ClaimRewardsOutsideAppsSteps() {
        When("user open rewards partner deeplink", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("NEW_PARTNER_REWARDS_LINK"));
        });

        Given("user should see voucher sheet", () -> {
            bukalapak.newUserPartnerRewardsPage().verifyHasClaimed();
            bukalapak.newUserPartnerRewardsPage().tapOnLihatHadiah();
            bukalapak.newUserPartnerRewardsPage().verifyOnPromoPage();
        });
    }
}
