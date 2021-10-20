package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import bukalapak.data.APIData;
import bukalapak.data.HelperData;
import bukalapak.data.UserData;
import com.bukalapak.salad.util.LogUtil;
import cucumber.api.java8.En;

/**
 * @author Irfan Mauludin, 01/11/18
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */
public class LoginTestStepDefinitions extends TestInstrument implements En {

    public LoginTestStepDefinitions() {
        //region Simple action

        When("user input Email field with \"([^\"]*)\" text", (String input) -> {
            bukalapak.loginPage().typeOnUsernameEditText(dotenv.get(input));
        });

        Then("login page will have displayed", () -> {
            bukalapak.loginPage().userOnLoginPage();
        });

        When("user click Lanjut button in login page", () -> {
            bukalapak.loginPage().clickContinueButton();
        });

        When("user input Password field with \"([^\"]*)\" text", (String input) -> {
            bukalapak.loginPage().typeOnPasswordEditText(dotenv.get(input));
        });

        And("user tap Hubungkan DANA button in Homepage", () -> {
            bukalapak.loginPage().tapElement("old_dana_hubungkan_text");
        });

        And("user tap Hubungkan Sekarang button in Hubungkan page", () -> {
            bukalapak.loginPage().tapElement("old_dana_hubungkan_sekarang_text");
        });

        Then("user verify login onboarding displayed", () -> {
            bukalapak.loginPage().validateLoginOnBoardingDisplayed();
        });

        When("^user click Login dengan Kode Rahasia label$", () -> {
            bukalapak.loginPage().clickLoginDenganKodeRahasia();
        });

        When("^user wait for login credential verification$", () -> {
            bukalapak.loginPage().waitMethodLoginRelated("login_login_button", 2);
        });

        //endregion Simple action

        /*
             * New Login step with simple logic and efficiency time
             * Validate first-time application load then doing login step
             * Just validating home page components when a user using the same account
         */
        Given("user login to app using \"([^\"]*)\" credential", (String arg0) -> {
            bukalapak.homePage().isOnHomePage();

            // Get registered username
            String username = dotenv.get(arg0.replaceAll(" ", "_").toUpperCase() + "_USERNAME");
            String password = dotenv.get(arg0.replaceAll(" ", "_").toUpperCase() + "_PASSWORD");

            if (!UserData.getUsername().equals(username) && UserData.isLoggedIn()) {
                LogUtil.info("Logout from the app");
                bukalapak.iOSBasePage().logOutFromApp();
            }

            if (!UserData.isLoggedIn()) {
                bukalapak.homePage().selectNavigationTab("Akun");
                bukalapak.loginPage().validateLoginOnBoardingDisplayed();
                bukalapak.loginPage().tapOnBoardingLoginBtn();
                bukalapak.loginPage().userOnLoginPage();
                bukalapak.loginPage().loginAs(arg0);
                bukalapak.homePage().isOnHomePage();

                UserData.setUsername(username);
                UserData.setPassword(password);
            }
        });

        Given("user login to app using \"([^\"]*)\" credential without close the inapp", (String arg0) -> {
            bukalapak.homePage().isOnHomePageWithoutCloseTheInApp();

            // Get registered username
            String username = dotenv.get(arg0.replaceAll(" ", "_").toUpperCase() + "_USERNAME");
            String password = dotenv.get(arg0.replaceAll(" ", "_").toUpperCase() + "_PASSWORD");

            if (!UserData.getUsername().equals(username) && UserData.isLoggedIn()) {
                LogUtil.info("Logout from the app");
                bukalapak.iOSBasePage().logOutFromApp();
            }

            if (!UserData.isLoggedIn()) {
                bukalapak.homePage().selectNavigationTab("Akun");
                bukalapak.loginPage().validateLoginOnBoardingDisplayed();
                bukalapak.loginPage().tapOnBoardingLoginBtn();
                bukalapak.loginPage().userOnLoginPage();
                bukalapak.loginPage().loginAs(arg0);
                bukalapak.homePage().isOnHomePageWithoutCloseTheInApp();

                UserData.setUsername(username);
                UserData.setPassword(password);
            }
        });

        //region E2E action

        /*
          Login using credential that will convert as prefix,
          then find related to prefix_USERNAME and prefix_PASSWORD in ENV
          ie. credential_USERNAME and credential_PASSWORD
          @credential type String
         */
        And("^user login using \"([^\"]*)\" credential$", (String arg0) -> {
            bukalapak.homePage().allowAccessATTPopUp();
            bukalapak.homePage().dismissCampaignPopUp();
            bukalapak.homePage().selectNavigationTab("Akun");
            if (bukalapak.iOSBasePage().isElementVisible("akun_pengaturan_akun_title")){
                LogUtil.info("pengaturan info");
                bukalapak.iOSBasePage().tapElement("alchemy_navbar_back_button");
            }
            String username = dotenv.get(arg0.replaceAll(" ", "_").toUpperCase() + "_USERNAME");
            APIData.setAPIUsername(arg0.replaceAll(" ", "_").toUpperCase());

            if (!UserData.getUsername().equals(username) && !bukalapak.iOSBasePage().isElementVisible("akun_onboarding_login_button")) {
                bukalapak.akunPage().userOnAkunPage();
                bukalapak.akunPage().tapPengaturanAkunOption();
                bukalapak.loginPage().logout();
                bukalapak.iOSBasePage().waitForVisibilityOf("akun_onboarding_login_button", 30);
                bukalapak.iOSBasePage().tapElement("akun_onboarding_login_button");
                bukalapak.loginPage().userOnLoginPage();
                bukalapak.loginPage().loginAs(arg0);
                UserData.setUsername(username);
            } else if (bukalapak.iOSBasePage().isElementVisible("akun_onboarding_login_button")){
                bukalapak.iOSBasePage().tapElement("akun_onboarding_login_button");
                bukalapak.loginPage().userOnLoginPage();
                bukalapak.loginPage().loginAs(arg0);
                UserData.setUsername(username);
            } else {
                bukalapak.akunPage().closePromotedPushOnboarding();
                bukalapak.iOSBasePage().tapElement("home_blhome_tab");
            }
            // Check for campaign banner pop up after logged in / visit homepage
            bukalapak.homePage().dismissCampaignPopUp();

            bukalapak.iOSBasePage().waitForVisibilityOf("home_blhome_tab", 30);
        });

        /*
         * Login using OTP for untrusted device, and using password if device trusted
         * Login using credential that will convert as prefix,
         * then find related to prefix_USERNAME and prefix_PASSWORD in ENV
         * ie. credential_USERNAME and credential_PASSWORD
         * @credential type String
         */
        And("^user login using \"([^\"]*)\" credential using OTP$", (String arg0) -> {
            bukalapak.homePage().dismissCampaignPopUp();
            String username = dotenv.get(arg0.replaceAll(" ", "_").toUpperCase() + "_USERNAME");
            String password = dotenv.get(arg0.replaceAll(" ", "_").toUpperCase() + "_PASSWORD");
            APIData.setAPIUsername(arg0.replaceAll(" ", "_").toUpperCase());

            if (!UserData.getUsername().equals(username) && UserData.isLoggedIn()) {
                LogUtil.info("Logout from the app");
                bukalapak.iOSBasePage().logOutFromApp();
            }

            if (!UserData.isLoggedIn()){
                bukalapak.homePage().selectNavigationTab("Akun");
                bukalapak.loginPage().validateLoginOnBoardingDisplayed();
                bukalapak.loginPage().tapOnBoardingLoginBtn();
                bukalapak.loginPage().userOnLoginPage();
                bukalapak.loginPage().typeOnUsernameEditText(username);
                bukalapak.loginPage().clickContinueButton();
                bukalapak.loginPage().waitMethodLoginRelated("lanjut_revamp_button", 5);
                if (bukalapak.otpPage().isInputOtpDisplayed()) {
                    // passwordless - untrusted
                    bukalapak.otpPage().tapNewOTP();
                    bukalapak.otpPage().waitOTPValidation();
                } else if (bukalapak.loginPage().isLoginChooserDisplayed()) {
                    // passwordless - trusted
                    bukalapak.loginPage().chooseLoginVerificationMethod("PASSWORD");
                    bukalapak.loginPage().typeOnPasswordEditText(password);
                    bukalapak.loginPage().tapElement("login_login_button");
                    bukalapak.loginPage().waitMethodLoginRelated("login_login_button", 5);
                } else if (bukalapak.otpPage().isRequestOTPDisplayed()) {
                    // normal - untrusted
                    bukalapak.otpPage().tapElement("kirim_sms_button");;
                    bukalapak.otpPage().tapNewOTP();
                    bukalapak.otpPage().waitOTPValidation();
                } else {
                    // normal - trusted
                    bukalapak.loginPage().typeOnPasswordEditText(password);
                    bukalapak.loginPage().tapElement("login_login_button");
                    bukalapak.loginPage().waitMethodLoginRelated("login_login_button", 5);
                }
                UserData.setLoggedIn(true);
                UserData.setUsername(username);
            } else {
                bukalapak.akunPage().closePromotedPushOnboarding();
                bukalapak.iOSBasePage().tapElement("home_blhome_tab");
            }
            // check for campaign pop up for logged in user
            bukalapak.homePage().dismissCampaignPopUp();

            bukalapak.iOSBasePage().waitForVisibilityOf("home_blhome_tab", 30);
        });

        /*
          Same function as above login steps, but user click from login screen, not home screen.
         */
        And("^user login using \"([^\"]*)\" credential from (deeplink|onboarding login page|login page)$", (String arg0, String arg1) -> {
            String username = dotenv.get(arg0.replaceAll(" ", "_").toUpperCase() + "_USERNAME");
            if (!arg1.equals("login page")) {
                bukalapak.iOSBasePage().waitForVisibilityOf("akun_onboarding_login_button", 30);
                bukalapak.iOSBasePage().tapElement("akun_onboarding_login_button");
            } // else is from onboarding login page
            bukalapak.loginPage().userOnLoginPage();
            bukalapak.loginPage().loginAs(arg0);
            UserData.setUsername(username);
            UserData.setLoggedIn(true);
        });

        When("user reset password using \"([^\"]*)\" with new password \"([^\"]*)\"", (String credential, String newPassword) -> {
            bukalapak.loginPage().goToLoginPage();
            bukalapak.loginPage().resetPassword(credential, newPassword);
        });

        //endregion E2E action


        //region Logout action

        Then("user perform logout", () -> {
            bukalapak.loginPage().logout();
            // to make sure user goes to homepage
            bukalapak.iOSBasePage().tapBackButton();
            HelperData.setLastActionPage(bukalapak.homePage());
        });

        When("user logout from bukalapak", () -> {
            bukalapak.iOSBasePage().resetApp();
        });

        When("user logout from deeplink", () -> {
            bukalapak.iOSBasePage().logOutFromApp();
            bukalapak.homePage().isOnHomePage();
        });

        //endregion Logout action


        //region Validation

        Then("user successfully login as \"([^\"]*)\"", (String username) -> {
            bukalapak.homePage().allowNotificationPopup();
            bukalapak.akunPage().clickAkunMenu();
            bukalapak.akunPage().validateUsername(username);
        });

        Then("user must see invalid \"([^\"]*)\" login error message", (String typeLogin) -> {
            bukalapak.loginPage().validateInvalidLogin(typeLogin);
        });

        Then("the user success reset password", () -> {
            bukalapak.resetPasswordPage().validateSuccessMessage();
        });

        And("user is on Binding Page", () -> {
            bukalapak.iOSBasePage().tapElement("old_dana_hubungkan_text");
        });

        //endregion Validation


        //region Hooks

        Given("user has been logged out",()->{
            if(UserData.getUsername() != null && !UserData.getUsername().isEmpty()) {
                bukalapak.iOSBasePage().tapElement("home_akun_tab");
                if(!bukalapak.iOSBasePage().isElementVisible("akun_onboarding_login_button")) {
                    bukalapak.akunPage().userOnAkunPage();
                    bukalapak.akunPage().tapPengaturanAkunOption();
                    bukalapak.loginPage().logout();
                    bukalapak.iOSBasePage().waitForVisibilityOf("akun_onboarding_login_button", 30);
                    bukalapak.iOSBasePage().tapElement("akun_onboarding_login_button");
                    bukalapak.loginPage().userOnLoginPage();
                }
                else {
                    bukalapak.iOSBasePage().tapElement("base_back_button");
                }
            }
        });

        Given("user logout via deeplink", () -> {
            bukalapak.iOSBasePage().openDeepLink("/logout");
            UserData.setUsername("");
            UserData.setPassword("");
            UserData.setLoggedIn(false);
        });

        Before(new String[]{"@tfa-active"}, () -> {
            UserData.setTfaStatus(true);
        });

        After(new String[]{"@tfa-active"}, () -> {
            UserData.setTfaStatus(false);
        });

        After(new String[]{"@disable-login-revamp"}, () -> {
            bukalapak.iOSBasePage().backToHomePageViaDeeplink();
            bukalapak.homePage().navigateToSuperSecretNinja();
            bukalapak.akunPage().forceSplitter("US-1513_IOS_login_revamp", "normal");
            bukalapak.akunPage().setNeoToggle("DISABLE", "login-revamp");
            bukalapak.iOSBasePage().backToHomePageViaDeeplink();
            HelperData.setLastActionPage(bukalapak.homePage());
        });

        //endregion Hooks

        Given("user is in \"login\" page", () -> {
            bukalapak.loginPage().userOnLoginPage();
        });

        When("user navigate to \"login\" page", () -> {
            bukalapak.loginPage().goToLoginPage();
        });

        Given("^user has not signed in to Bukalapak app$", () -> {
            if (UserData.isLoggedIn()) {
                LogUtil.info("Need to signing out first.");
                bukalapak.iOSBasePage().logOutFromApp();
            }
            bukalapak.homePage().isOnHomePage();
        });

        And("user relaunch application", () -> {
            bukalapak.basePage().relaunchApp();
        });

        Then("user will get error message for empty password", () -> {
            bukalapak.loginPage().validateEmptyPasswordErrorMessage();
        });

        And("user click Login button in login page", () -> {
            bukalapak.loginPage().clickLoginButton();
        });

        Then("user will get error message for invalid password", () -> {
            bukalapak.loginPage().validateInvalidPasswordErrorMessage();
        });
    }
}
