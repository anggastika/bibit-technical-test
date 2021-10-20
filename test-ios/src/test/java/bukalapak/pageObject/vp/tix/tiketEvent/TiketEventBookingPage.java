package bukalapak.pageObject.vp.tix.tiketEvent;

import bukalapak.data.HelperData;
import bukalapak.pageObject.InvoiceDetailNonMarketplacePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

/**
 * @Author: Fixco Amrizal Candra
 **/

public class TiketEventBookingPage  extends InvoiceDetailNonMarketplacePage {

    public TiketEventBookingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage(boolean isDisplayed) {
        if (isDisplayed) {
            validateDisplayed("TICKET_EVENT_NAMA_LENGKAP_TEXT_FIELD");
            validateDisplayed("TICKET_EVENT_EMAIL_TEXT_FIELD");
            validateDisplayed("TICKET_EVENT_IDENTITY_TEXT_FIELD");
            validateDisplayed("TICKET_EVENT_PHONE_NUMBER_TEXT_FIELD");
        } else {
            validateNotDisplayed("TICKET_EVENT_NAMA_LENGKAP_TEXT_FIELD");
        }

        HelperData.setLastActionPage(new TiketEventBookingPage(iosDriver));
    }

    public void tapOnLanjutButton() {
        swipeToLocator("TICKET_EVENT_LANJUT_BUTTON");
        tapElement("TICKET_EVENT_LANJUT_BUTTON");
        waitFor(10); // adjust time untuk popup muncul yang sudah klik otomatis
    }

    public void tapCobaLagiButton() {
        if (isElementExist("TICKET_EVENT_COBA_LAGI_BUTTON", 5)) {
            tapElement("TICKET_EVENT_COBA_LAGI_BUTTON");
            waitForVisibilityOf("TICKET_EVENT_LANJUT_BUTTON");
            tapElement("TICKET_EVENT_LANJUT_BUTTON");
        }
    }

    public void inputNamaLengkap(String type) {
        String fullname = type.equals("valid") ? fakerFirstName("TIX") : fakerEmail();

        typeAndEnterValue("TICKET_EVENT_NAMA_LENGKAP_TEXT_FIELD", fullname);
    }

    public void clearFullname() {
        typeAndEnterValue("TICKET_EVENT_NAMA_LENGKAP_TEXT_FIELD", " ");
    }

    public void inputEmail(String type) {
        String email = type.equals("valid") ? fakerEmail() : fakerPhoneNumber("0812", 5);

        typeAndEnterValue("TICKET_EVENT_EMAIL_TEXT_FIELD", email);
    }

    public void clearEmail() {
        typeAndEnterValue("TICKET_EVENT_EMAIL_TEXT_FIELD", " ");
    }

    public void inputNIK(String type) {
        String id = type.equals("valid") ? fakerID() : fakerFirstName("tix");

        typeAndEnterValue("TICKET_EVENT_IDENTITY_TEXT_FIELD", id);
    }

    public void clearNIK() {
        typeAndEnterValue("TICKET_EVENT_IDENTITY_TEXT_FIELD", " ");
    }

    public void inputPhoneNumber(String type) {
        String phone = type.equals("valid") ? fakerPhoneNumber("0812", 8) : fakerEmail();

        typeAndEnterValue("TICKET_EVENT_PHONE_NUMBER_TEXT_FIELD", phone);
    }

    public void clearPhoneNumber() {
        typeAndEnterValue("TICKET_EVENT_PHONE_NUMBER_TEXT_FIELD", " ");
    }

    public void validateFullNameErrorMessage(String errorType) {
        if (errorType.equals("invalid")) {
            validateDisplayed("TICKET_EVENT_BOOKING_FORM_FULLNAME_ERROR");
        } else if (errorType.equals("empty")) {
            validateDisplayed("TICKET_EVENT_BOOKING_FORM_FULLNAME_ERROR_EMPTY");
        } else {
            Assert.fail("Error type " + errorType + " is not implemented");
        }
    }

    public void validateEmailErrorMessage(String errorType) {
        if (errorType.equals("invalid")) {
            validateDisplayed("TICKET_EVENT_BOOKING_FORM_EMAIL_ERROR");
        } else if (errorType.equals("empty")) {
            validateDisplayed("TICKET_EVENT_BOOKING_FORM_EMAIL_ERROR_EMPTY");
        } else {
            Assert.fail("Error type " + errorType + " is not implemented");
        }
    }

    public void validateNIKErrorMessage(String errorType) {
        if (errorType.equals("invalid")) {
            validateExist("TICKET_EVENT_BOOKING_FORM_NIK_ERROR");
        } else if (errorType.equals("empty")) {
            validateDisplayed("TICKET_EVENT_BOOKING_FORM_NIK_ERROR_EMPTY");
        } else {
            Assert.fail("Error type " + errorType + " is not implemented");
        }
    }

    public void validatePhoneNumberErrorMessage(String errorType) {
        if (errorType.equals("invalid")) {
            validateDisplayed("TICKET_EVENT_BOOKING_FORM_PHONE_ERROR");
        } else if (errorType.equals("empty")) {
            validateDisplayed("TICKET_EVENT_BOOKING_FORM_PHONE_ERROR_EMPTY");
        } else {
            Assert.fail("Error type " + errorType + " is not implemented");
        }
        HelperData.setLastActionPage(new TiketEventBookingPage(iosDriver));
    }
}
