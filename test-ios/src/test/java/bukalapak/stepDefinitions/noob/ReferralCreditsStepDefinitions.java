package bukalapak.stepDefinitions.noob;
import bukalapak.TestInstrument;
import cucumber.api.java8.En;


public class ReferralCreditsStepDefinitions extends TestInstrument implements En {

    public ReferralCreditsStepDefinitions(){
        And("user scroll down to ajak-ajak berhadiah", () -> {
            bukalapak.referralCreditsPage().scrollDownToAjakBerhadiah();
        });

        Then("user should see verification button in referral dashboard", () -> {
            bukalapak.referralCreditsPage().dismissModalReferral();
            bukalapak.referralCreditsPage().verifyOnReferralDashboard();
            bukalapak.referralCreditsPage().validatePhoneVerificationButton();
        });

        Then("user click referral credits link", () -> {
            bukalapak.iOSBasePage().openDeepLink("https://bl.app.link/d1vlc4hPp9");
        });

        Then("user should see maaf modal", () -> {
            bukalapak.referralCreditsPage().validateModalMaaf();
            bukalapak.referralCreditsPage().verifyOnReferralDashboard();
            bukalapak.referralCreditsPage().goToHomePage();
        });

        Then("user should see belanja dulu button in referral dashboard", () -> {
            bukalapak.referralCreditsPage().dismissModalReferral();
            bukalapak.referralCreditsPage().verifyOnReferralDashboard();
            bukalapak.referralCreditsPage().validateBelanjaDulu();
        });

        Then("user should see max downline in referral dashboard", () -> {
            bukalapak.referralCreditsPage().dismissModalReferral();
            bukalapak.referralCreditsPage().verifyOnReferralDashboard();
            bukalapak.referralCreditsPage().validateMaxDownline();
        });

        Then("user should see empty downline on referral rewards page", () -> {
            bukalapak.referralCreditsPage().dismissModalReferral();
            bukalapak.referralCreditsPage().verifyOnReferralDashboard();
            bukalapak.referralCreditsPage().emptyStateDownline();
            bukalapak.referralCreditsPage().verifyOnReferralRewards();
            bukalapak.referralCreditsPage().verifyEmptyBerlangsungDownline();
            bukalapak.referralCreditsPage().verifyEmptySelesaiDownline();
        });

        Then("user should see gift notif", () -> {
            bukalapak.referralCreditsPage().dismissModalReferral();
            bukalapak.referralCreditsPage().verifyOnReferralDashboard();
            bukalapak.referralCreditsPage().referralVerifyGiftNotif();
            bukalapak.referralCreditsPage().verifyOnReferralRewards();
            bukalapak.referralCreditsPage().referralVerifyHaveGift();
        });

        Then("user can see verified downline", () -> {
            bukalapak.referralCreditsPage().dismissModalReferral();
            bukalapak.referralCreditsPage().verifyOnReferralDashboard();
            bukalapak.referralCreditsPage().emptyStateDownline();
            bukalapak.referralCreditsPage().verifyOnReferralRewards();
            bukalapak.referralCreditsPage().validateVerifiedDownline();
        });

        And("user can see unverified downline", () -> {
            bukalapak.referralCreditsPage().validateUnverifiedDownline();
        });

        And("user can see berhasil downline", () -> {
            bukalapak.referralCreditsPage().tapOnSelesaiTab();
            bukalapak.referralCreditsPage().validateFinishedDownline();
        });

        And("user can see gagal downline", () -> {
            bukalapak.referralCreditsPage().validateFailedDownline();
        });

        And("user can cek credits rewards", () -> {
            bukalapak.referralCreditsPage().validateCekCreditsLink();
        });

        And("open deeplink referral serbu seru", () -> {
            bukalapak.iOSBasePage().openDeepLink("https://bl.app.link/EEe8VcVE09");
        });


        And("user input fullname at registration page", () -> {
            bukalapak.registerPage().typeOnFullname(dotenv.get("NCA_REFERRAL_FULLNAME"));
        });

        And("user tap checklist agreement", () -> {
            bukalapak.registerPage().checklistAgreement();
        });

        And("referral user should see new user zone", () -> {
            bukalapak.registerPage().allowPopup();
            bukalapak.newUserZonePage().validateOnNewUserZone();
            bukalapak.newUserZonePage().validateUnverifiedUser();
            bukalapak.referralCreditsPage().tapBackAfterRegisterReferral();
        });

        And("referral user click referral link", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("REFERRAL_LINK"));
        });
    }
}
