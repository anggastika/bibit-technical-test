package bukalapak.pageObject.vp.tix.tiketKereta;

import bukalapak.data.HelperData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import static bukalapak.TestInstrument.dotenv;

public class TravelTrainPassengerFormPage extends VpBasePage {

    public TravelTrainPassengerFormPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void isOnPassengerFormPage() {
        assertTrue(isElementVisible("train_passenger_form_page"));
    }

    public void typeOnNamaLengkapEditText(boolean isNameValid) {
        String fullname = isNameValid ? dotenv.get("BUYER_NAME") : fakerPhoneNumber("0812", 5);

        validateExist("train_nama_lengkap_pemesan", 10);
        typeAndEnterValue("train_nama_lengkap_pemesan", fullname);
    }

    public void clearFullname() {
        validateExist("train_nama_lengkap_pemesan", 10);
        typeAndEnterValue("train_nama_lengkap_pemesan", "");
    }

    public void typeOnEmailEditText() {
        typeAndEnterValue("train_email_pemesan", dotenv.get("BUYER_EMAIL"));
    }

    public void clearEmail() {
        typeAndEnterValue("train_email_pemesan", "");
    }

    public void typeOnNoHandphone() {
        typeAndEnterValue("train_handphone_pemesan", dotenv.get("BUYER_HANDPHONE"));
    }

    public void clearPhone() {
        typeAndEnterValue("train_handphone_pemesan", "");
    }

    public void tapOnSamaDenganPemesan() {
        tapElement("train_checkbox_sama_dengan_pemesan");
    }

    public void typeOnNoIdentitas(boolean isIdentityValid) {
        String identity = isIdentityValid ? dotenv.get("BUYER_IDENTITY") : fakerEmail();

        validateExist("train_no_identitas", 10);
        typeAndEnterValue("train_no_identitas", identity);
    }

    public void clearIdentity() {
        validateExist("train_no_identitas", 10);
        typeAndEnterValue("train_no_identitas", "");
    }

    public void tapOnPilihKursiButton() {
        tapElement("train_button_pilih_kursi");
    }

    public void tapOnYaButton() {
        tapElement("train_button_ya_popup");
    }

    public void tapSimpanButton() {
        tapElement("train_passenger_form_simpan_button");
    }

    public void validateFullNameErrorMessage(String errorType) {
        if (errorType.equals("invalid")) {
            validateDisplayed("train_passenger_form_fullname_error");
        } else if (errorType.equals("empty")) {
            validateDisplayed("train_passenger_form_fullname_error_empty");
        } else {
            Assert.fail("Error type " + errorType + " is not implemented");
        }
    }

    public void validateIdentityErrorMessage(String errorType) {
        if (errorType.equals("invalid")) {
            validateDisplayed("train_passenger_form_identity_error");
        } else if (errorType.equals("empty")) {
            validateDisplayed("train_passenger_form_identity_error_empty");
        } else {
            Assert.fail("Error type " + errorType + " is not implemented");
        }

        HelperData.setLastActionPage(new TravelTrainPassengerFormPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
