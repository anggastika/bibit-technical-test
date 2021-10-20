package bukalapak.stepDefinitions.us;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.Map;

/**
 * Created by Aris Nugraha on 03/01/2019
 */


public class RegisterStepDefinitions extends TestInstrument implements En {
    public RegisterStepDefinitions() {

        When("user input \"([^\"]*)\" into email or phone number at registration page", (String emailphone) -> {
            bukalapak.registerPage().typeOnEmailOrPhoneEditText(dotenv.get(emailphone));
        });

        And("user input \"([^\"]*)\" into password field at registration page", (String password) -> {
            bukalapak.registerPage().typeOnPasswordEditText(dotenv.get(password));
        });

        And("user input Email or Phone field with valid random email", () -> {
            bukalapak.registerPage().typeOnEmailOrPhoneEditText(bukalapak.registerPage().generateRandomEmail("gmail"));
        });

        And("user input \"([^\"]*)\" into password confirmation field at registration page", (String input) -> {
            bukalapak.registerPage().typeOnPasswordConfirmationEditText(dotenv.get(input));
        });

        Then("email or phone number field displays error message \"([^\"]*)\"", (String errorMessage) -> {
            bukalapak.registerPage().verifyEmailPhoneFieldErrorMessage(errorMessage);
        });

        Then("user see error message used phone number on register page", () -> {
            bukalapak.iOSBasePage().swipeUpToElement("daftar_emailphone_text_field");
            bukalapak.registerPage().validateUsedPhoneNumber();
        });

        Then("user successfully registered to bukalapak", () -> {
            bukalapak.homePage().allowNotificationPopup();
            bukalapak.akunPage().clickAkunMenu();
            bukalapak.akunPage().userOnAkunPage();
        });

        And("user input \"([^\"]*)\" with random number sufix into email at registration page", (String email) -> {
            bukalapak.registerPage().typeOnEmailOrPhoneEditText(bukalapak.registerPage().randomEmailWithRandomNumberSufix(dotenv.get(email)));
        });

        When("user choose \"([^\"]*)\" at gender option at registration page", (String gender) -> {
            bukalapak.registerPage().tapOnGenderIcon(gender);
        });

        And("user click button daftar", () -> {
            bukalapak.registerPage().tapRegisterButton();
        });

        Then("user see error message used email in register page", () -> {
            bukalapak.registerPage().validateUsedEmail();
        });

        Then("user see error message used phone in register page", () -> {
            bukalapak.registerPage().validateUsedPhone();
        });

        And("user exit register page", () -> {
            bukalapak.registerPage().exitRegisterPage();
        });

        /**
         * Step for filling phone number/email, fullname and password on new and old registration page until success
         * only proceed OTP after input phone number/email for new registration
         *
         * use OTP token that stored in ENV variable named : VALID_OTP_CODE
         *
         * @param userData datatable with that accept :
         *                 | FULLNAME                | raw or ENV |
         *                 | EMAIL_OR_PHONE_NUMBER   | raw or ENV |
         *                 | PASSWORD                | raw or ENV |
         *                 | PASSWORD_CONFIRMATION   | raw or ENV |
         *                 | ACCEPT_BL_AGREEMNENT    | raw or ENV |
         *
         *                 ACCEPT_BL_AGREEMNENT is optional
         */
        When("^user complete registration process using user data:$", (DataTable userData) -> {
            Map<String, String> userRegistrationData = userData.asMap(String.class, String.class);
            bukalapak.registerPage().getNewRegister();
            bukalapak.registerPage().typeEmailOrPhone(userRegistrationData.get("EMAIL_OR_PHONE_NUMBER"));
            bukalapak.otpPage().tapNewOTP();
            bukalapak.otpPage().waitOTPValidation();
            bukalapak.registerPage().inputRegistrationDetail(userRegistrationData.get("FULLNAME"), userRegistrationData.get("PASSWORD"));
            bukalapak.registerPage().tapMulaiBelanja();

        });

        Given("user is in \"registration\" page", () -> {
            bukalapak.registerPage().onRegisterPage();
        });

        When("user is in \"registration_detail\" page", () -> {
            bukalapak.registerPage().onDetailRegisterPage();
        });

        When("user navigate to \"Registration\" page", () -> {
            bukalapak.akunPage().clickAkunMenu();
            bukalapak.akunPage().tapDaftarAkun();
        });
    }
}
