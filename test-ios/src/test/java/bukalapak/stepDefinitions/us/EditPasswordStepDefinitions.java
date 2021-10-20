package bukalapak.stepDefinitions.us;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */

public class EditPasswordStepDefinitions extends TestInstrument implements En {
    public EditPasswordStepDefinitions() {
        Then("^user is in edit password page$", () -> {
           bukalapak.editPasswordPage().userOnEditPasswordPage();
        });

        When("user navigate to \"edit_password\" page$", () -> {
            bukalapak.akunPage().clickAkunMenu();
            bukalapak.akunPage().tapPengaturanAkunOption();
            bukalapak.pengaturanAkunPage().tapPengaturanPassword();
        });

        Then("^user will logout to onboarding page$", () -> {
            bukalapak.editPasswordPage().userOnOnboardingPage();
        });

        When("^user update password from \"([^\"]*)\" to \"([^\"]*)\" successfully$", (String currentpass, String newpass) -> {
            bukalapak.editPasswordPage().typeOnCurrentPasswordText(dotenv.get(currentpass));
            bukalapak.editPasswordPage().typeOnNewPasswordText(dotenv.get(newpass));
            bukalapak.editPasswordPage().typeOnConfirmPasswordText(dotenv.get(newpass));
            bukalapak.iOSBasePage().tapElement("pengaturan_akun_simpan_button");
        });

        When("^user logged in with \"new_credential\"$", () -> {
            bukalapak.iOSBasePage().tapElement("akun_onboarding_login_button");
            bukalapak.loginPage().typeOnUsernameEditText(dotenv.get("CHANGE_PASSWORD_OLD_USERNAME"));
            bukalapak.loginPage().clickContinueButton();
            bukalapak.loginPage().typeOnPasswordEditText(dotenv.get("CHANGE_PASSWORD_NEW_PASSWORD"));
            bukalapak.iOSBasePage().tapElement("login_login_button");
        });
    }
}
