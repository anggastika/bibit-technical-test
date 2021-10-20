package bukalapak.pageObject.vp.tix.tiketKereta;

import bukalapak.data.HelperData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class TravelTrainBuyerContactPage extends VpBasePage {
    public TravelTrainBuyerContactPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void inputFullname(boolean isNameValid) {
        String fullname = isNameValid ? fakerFullName("TIX") : fakerPhoneNumber("0812", 5);

        typeAndEnterValue("train_buyer_contact_form_fullname_field", fullname);
    }

    public void clearFullname() {
        typeAndEnterValue("train_buyer_contact_form_fullname_field", "");
    }

    public void inputEmail(boolean isEmailValid) {
        String email = isEmailValid ? fakerEmail() : fakerFullName("TIX");

        typeAndEnterValue("train_buyer_contact_form_email_field", email);
    }

    public void clearEmail() {
        typeAndEnterValue("train_buyer_contact_form_email_field", "");
    }

    public void inputPhone(boolean isPhoneValid) {
        String phone = isPhoneValid ? fakerPhoneNumber("0812", 8) : fakerEmail();

        typeAndEnterValue("train_buyer_contact_form_phone_field", phone);
    }

    public void clearPhone() {
        typeAndEnterValue("train_buyer_contact_form_phone_field", "");
    }

    public void tapSimpanButton() {
        tapElement("train_buyer_contact_form_simpan_button");
    }

    public void validateFullNameErrorMessage(String errorType) {
        if (errorType.equals("invalid")) {
            validateDisplayed("train_buyer_contact_form_fullname_error");
        } else if (errorType.equals("empty")) {
            validateDisplayed("train_buyer_contact_form_fullname_error_empty");
        } else {
            Assert.fail("Error type " + errorType + " is not implemented");
        }
    }

    public void validateEmailErrorMessage(String errorType) {
        if (errorType.equals("invalid")) {
            validateDisplayed("train_buyer_contact_form_email_error");
        } else if (errorType.equals("empty")) {
            validateDisplayed("train_buyer_contact_form_email_error_empty");
        } else {
            Assert.fail("Error type " + errorType + " is not implemented");
        }
    }

    public void validatePhoneNumberErrorMessage(String errorType) {
        if (errorType.equals("invalid")) {
            validateDisplayed("train_buyer_contact_form_phone_error");
        } else if (errorType.equals("empty")) {
            validateDisplayed("train_buyer_contact_form_phone_error_empty");
        } else {
            Assert.fail("Error type " + errorType + " is not implemented");
        }

        HelperData.setLastActionPage(new TravelTrainBuyerContactPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
