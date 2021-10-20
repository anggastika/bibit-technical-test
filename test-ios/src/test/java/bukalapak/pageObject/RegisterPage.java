package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;
import java.util.Random;

/**
 * Created by Aris Nugraha 03/01/2019
 */

public class RegisterPage extends BasePage {

    private static boolean newRegister = false;

    private final static Logger LOGGER = LogManager.getLogger(RegisterPage.class);

    public RegisterPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public boolean getNewRegister() {
        return newRegister;
    }

    private void setNewRegister(boolean newRegister) {
        RegisterPage.newRegister = newRegister;
    }

    public void onRegisterPage() {
        if (isElementVisible("new_registration_phone_email_textfield")) {
            verifyElementExist("new_registration_phone_email_textfield");
            setNewRegister(true);
            LOGGER.info("Get New Registration Page");
        } else if (isElementVisible("new_registration_phone_email_textfield_alt") ||
                isElementVisible("new_registration_fullname_textfield")) {
            setNewRegister(true);
            LOGGER.info("Get New Registration Page");
        } else {
            verifyElementExist("daftar_emailphone_text_field");
            setNewRegister(false);
            LOGGER.info("Get Old Register Page");
        }

        HelperData.setLastActionPage(new RegisterPage(iosDriver));
    }

    public void onDetailRegisterPage() {
        if (newRegister) {
            verifyElementExist("fullname_revamp_edittext");
        } else {
            verifyElementExist("nama_lengkap");
        }

        HelperData.setLastActionPage(new RegisterPage(iosDriver));
    }

    public void typeOnFullname(String fullname) {
        if (newRegister) {
            typeValue("new_registration_fullname_textfield", fullname);
        } else {
            typeAndEnterValueWithTimeOut("nama_lengkap", fullname);
        }
    }

    public void typeOnEmailOrPhoneEditText(String emailPhone) {
        if (newRegister) {
            if (isElementExist("new_registration_phone_email_textfield_alt")) {
                typeAndEnterValueWithTimeOut("new_registration_phone_email_textfield_alt", emailPhone);
            } else {
                typeAndEnterValueWithTimeOut("new_registration_phone_email_textfield", emailPhone);
            }
        } else {
            typeAndEnterValueWithTimeOut("daftar_emailphone_text_field", emailPhone);
        }
    }

    public void inputRegistrationDetail(String fullname, String password){
        onRegisterPage();
        typeOnFullname(fullname);
        if (isElementVisible("new_registration_password_textfield")){
            typeOnPasswordEditText(password);
        }
        tapRegisterButton();
    }

    public void typeEmailOrPhone(String input){
        typeOnEmailOrPhoneEditText(input);
        tapElement("new_registration_daftar_button");
        tapElement("new_registration_yes_request_otp_button");
    }

    public String randomEmailWithRandomNumberSufix(String email) {
        Random randomNumber = new Random();
        String randomNum = String.valueOf(randomNumber.nextInt((110000 - 100000) + 1) + 100000);
        String[] emails = email.split("@");
        return emails[0] + randomNum + "@" + emails[emails.length-1];
    }

    public void typeOnUsernameEditText(String username) {
        typeAndEnterValueWithTimeOut("register_username_field", username);
    }

    public void typeOnPasswordEditText(String input) {
        if (newRegister) {
            typeValue("new_registration_password_textfield", input);
        } else {
            tapElement("password_bukalapak", 10);
            typeAndEnterValueWithTimeOut("daftar_password_text_field", input);
        }
    }

    public void typeOnPasswordConfirmationEditText(String input) {
        if (newRegister) {
            typeAndEnterValue("password_confirmation_revamp_edittext", input);
        } else {
            typeAndEnterValueWithTimeOut("daftar_confirm_password_text_field", input);
        }
    }

    public void verifyEmailPhoneFieldErrorMessage(String errorMessage) {
        if (newRegister) {
            validateElementContainsText("email_phone_number_error_message_revamp_text", errorMessage);
        } else {
            swipeDownToElement(constructLocator("email_phone_number_error_message_text", errorMessage));
            validateElementContainsText(constructLocator("email_phone_number_error_message_text", errorMessage), errorMessage);
        }
    }

    public void validateUsedPhoneNumber() {
        assertTrue(isElementVisible("used_phonenumber"), "Error Message used phone is not exist");
        HelperData.setLastActionPage(new DaftarPage(iosDriver));
    }

    public void validateUserProfile() {
        waitForVisibilityOf("username_button", 20);
        assertTrue(isElementVisible("username_button"));
    }

    //generate random email
    public String generateRandomEmail(String provider) {
        String s, email;
        int randomNum = RandomUtils.nextInt(100000, 999999);
        String randomize = String.valueOf(randomNum);
        String[] names = {"Pinilih", "Arisnugraha", "Maulidri", "Mahran", "Andika", "febrianyeremi", "Setiadi", "Pratama", "Soedjono", "Mirza", "Pramudy", "Andreas", "Wibowo", "Ananta", "Almabruri", "Maulana", "Krisna", "Nugraha", "Manan", "Praharsana", "Baskara", "Andrian", "Arifiandi", "Haries", "Noordian", "Maryono", "Kurnia", "Candra", "Muhammad"};
        Random dice = new Random();
        int n = dice.nextInt(6);
        s = names[n];

        email = s + randomize + "@" + provider + ".com";

        return email.toLowerCase();
    }

    public void checklistAgreement() {
        tapElement("term_condition");
    }

    public void tapRegisterButton() {
        if (newRegister) {
            verifyElementExist("new_registration_simpan_button");
            tapElement("new_registration_simpan_button");
        } else {
            tapElement("daftar_button");
        }
    }

    public void tapMulaiBelanja() {
        allowPopup();
        waitForVisibilityOf("success_registration_mulai_belanja_button", 10);
        tapElement("success_registration_mulai_belanja_button");
    }

    /**
     * filling form in old registration page
     * and also proceed OTP
     *
     * use OTP token that stored in ENV variable named : VALID_OTP_CODE
     *
     * @param userData datatable that accept :
     *                 FULLNAME
     *                 EMAIL_OR_PHONE_NUMBER
     *                 PASSWORD
     *                 PASSWORD_CONFIRMATION
     *                 ACCEPT_BL_AGREEMNENT (optional) : String YES or NO
     */
    public void fillFormOldRegister(DataTable userData) {
        Map<String, String> userRegistrationData = userData.asMap(String.class, String.class);

        typeOnFullname(userRegistrationData.get("FULLNAME"));
        swipeDownToElement("daftar_password_text_field");
        typeOnPasswordEditText(userRegistrationData.get("PASSWORD"));
        typeOnPasswordConfirmationEditText(userRegistrationData.get("PASSWORD_CONFIRMATION"));
        typeOnEmailOrPhoneEditText(userRegistrationData.get("EMAIL_OR_PHONE_NUMBER"));

        if (userRegistrationData.containsKey("ACCEPT_BL_AGREEMNENT")) {
            if (userRegistrationData.get("ACCEPT_BL_AGREEMNENT").equals("YES")) {
                swipeDownToElement("term_condition");
                checklistAgreement();
            }
        }
    }

    public void validateUsedEmail() {
        swipeUpToElement("daftar_emailphone_text_field");
        if (newRegister) {
            verifyElementExist("used_email_revamp_text");
        } else {
            verifyElementExist("invalid_used_email");
        }
        HelperData.setLastActionPage(new DaftarPage(iosDriver));
    }

    public void validateUsedPhone() {
        swipeUpToElement("daftar_emailphone_text_field");
        if (newRegister) {
            verifyElementExist("used_phone_revamp_text");
        } else {
            verifyElementExist("used_phonenumber");
        }
        HelperData.setLastActionPage(new DaftarPage(iosDriver));
    }

    public void exitRegisterPage() {
        if (newRegister) {
            tapElement("exit_button_in_register");
        } else {
            tapElement("exit_old_register");
        }
    }

    public void tapOnGenderIcon(String gender) {
        swipeUpToElement("daftar_akun_bukalapak_revamp_button");

        switch (gender) {
            case "MALE":
                tapElement("gender_male_revamp_radiobutton");
                break;
            case "FEMALE":
                tapElement("gender_female_revamp_radiobutton");
                break;
            default:
                LOGGER.info("Invalid gender");
                break;
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
