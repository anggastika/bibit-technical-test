package bukalapak.stepDefinitions.us;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */

public class EditPhoneStepDefinitions extends TestInstrument implements En {
    public EditPhoneStepDefinitions() {
        When("user navigate to \"edit-phone-number\" page$", () -> {
            bukalapak.akunPage().clickAkunMenu();
            bukalapak.akunPage().tapPengaturanAkunOption();
            bukalapak.pengaturanAkunPage().tapPengaturanTelepon();
        });

        And("user is in edit phone number page$", () -> {
            bukalapak.editPhonePage().userOnEditPhonePage();
        });

        And("user update current phone to \"([^\"]*)\" with password \"([^\"]*)\"$", (String newphone, String pass) -> {
            bukalapak.editPhonePage().updateCurrentPhoneNumber(dotenv.get(newphone), dotenv.get(pass));
        });

        Then("user successfully update phone number$", () -> {
            bukalapak.editPhonePage().userOnSuccessEditPhonePage();
        });

        And("user DANA update current phone to \"([^\"]*)\" with password \"([^\"]*)\"$", (String newphone, String pass) -> {
            bukalapak.editPhonePage().updateCurrentDANAPhoneNumber(dotenv.get(newphone), dotenv.get(pass));
        });

        When("^user is in update phone number page$", () -> {
            bukalapak.editPhonePage().verifyPhoneNumberSettingPage();
        });

        And("^user back from update phone number page$", () -> {
            bukalapak.editPhonePage().tapBackButton();
        });
    }
}
