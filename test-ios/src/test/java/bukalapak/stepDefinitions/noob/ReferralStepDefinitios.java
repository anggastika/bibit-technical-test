package bukalapak.stepDefinitions.noob;
import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ReferralStepDefinitios extends TestInstrument implements En {

    public ReferralStepDefinitios(){
        And("user click referral link", () -> {
            bukalapak.iOSBasePage().openDeepLink("https://bl.app.link/XiwaupB3t2");
            bukalapak.referralPage().validateGaBisaAmbil();
        });

        Then("user should see modal kado tidak bisa diambil", () -> {
            bukalapak.referralPage().validateGaBisaAmbil();
            bukalapak.referralPage().ajakTeman();
        });

        Then("user see verification button in referral dashboard", () -> {
            bukalapak.referralPage().validateVerificationButton();
        });

        Then("user see ajak teman button in referral dashboard", () -> {
            bukalapak.referralPage().validateEligibleButton();
        });

        Then("user see belanja button in referral dashboard", () -> {
            bukalapak.referralPage().validateBelanjaButton();
        });

        And("user click referral spin and win link", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("REFERRAL_SPINWIN_DEEPLINK"));
        });

        Then("user is on referral serbu seru webview page", () -> {
            bukalapak.referralPage().userIsOnSerbuSeruGratisWebviewPage();
        });

        Then("user can get serbu gratis after share", () -> {
            bukalapak.referralPage().tapOnSerbuGratis();
            bukalapak.referralPage().shareSerbuGratis();
            bukalapak.referralPage().tapAfterSerbuGratis();
            bukalapak.referralPage().verifyOnPostSerbuPage();
        });

        //referral 3.0
        And("user redirected to referee teman cuan landing page", () -> {
            bukalapak.referralPage().userIsOnRefereePage();
        });

        And("user redirected to referrer teman cuan landing page", () -> {
            bukalapak.referralPage().verifyReferralCuanPage();
        });

        And("user tap on Undang teman button", () -> {
            bukalapak.referralPage().tapOnUndangTemanBtn();
        });

        Then("referee claim rewards", () -> {
            bukalapak.referralPage().tapAmbilCreditsBtn();
        });

        Then("referee should see verify phone sheet", () -> {
            bukalapak.referralPage().verifyPhoneSheet();
        });

        And("referrer go to list referee page", () -> {
            bukalapak.referralPage().tapOnRefereeListIcon();
        });

        And("referrer verify on list referee page", () -> {
            bukalapak.referralPage().isOnListRefereePage();
        });

        Then("referrer verify empty referee", () -> {
            bukalapak.referralPage().verifyEmptyReferee();
        });

        And("^share pop up is shown", () -> {
            bukalapak.referralPage().validateShareModal();
        });

        And("^user see pop up only for new user$", () -> {
            bukalapak.referralPage().validateApologizeModal();
        });

        And("user should see new user zone section on referee page", () -> {
            bukalapak.referralPage().validateNuzSectionReferee();
        });

    }
}
