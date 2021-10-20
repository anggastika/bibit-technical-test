package bukalapak.pageObject.vp.tix.tiketBus;

import bukalapak.data.BusData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 04/01/19, Fri
 **/
public class TravelBusPassengerFormPage extends VpBasePage {

    public TravelBusPassengerFormPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void validateOnPassengerFormPage() {
        waitForVisibilityOf("bus_email_name", 30);
        verifyElementDisplayed("bus_handphone_name");
    }

    public void typeOnInputFullName(boolean isValid) {
        if (isValid) {
            String fullname = generateFullName();
            typeAndEnterValueWithTimeOut("bus_passanger_form_nama_pemesan", fullname);
        } else {
            typeAndEnterValueWithTimeOut("bus_passanger_form_nama_pemesan", "Sallis 123");
        }
    }

    public void typeOnInputEmail(boolean isValid) {
        if (isValid) {
            String fullname = generateFullName().replace(" ", "");
            typeAndEnterValueWithTimeOut("bus_passanger_form_email_pemesan", fullname + "@gmail.com");
        } else {
            typeAndEnterValueWithTimeOut("bus_passanger_form_email_pemesan", "Sallis123@gmail@com");
        }
    }

    public void typeOnInputPhoneNumber(boolean isValid) {
        if (isValid) {
            long phoneNumber = generatePhoneNumber();
            typeAndEnterValueWithTimeOut("bus_passanger_handphone", "08" + phoneNumber);
        } else {
            typeAndEnterValueWithTimeOut("bus_passanger_handphone", "#081234S67859");
        }
    }

    public void tapOnSameWithCustomer() {
        swipeUpToElement("bus_sama_dengan_pemesan_checkbox");
        tapElement("bus_sama_dengan_pemesan_checkbox");
    }

    public void tapOnTitleDropdown() {
        swipeUpToElement("bus_dropdown_title");
        tapElement("bus_dropdown_title");
        tapElement("bus_title_nyonya_name");
    }

    public void typeOnInputAgePassenger() {
        swipeUpToElement("bus_button_lanjut");
        typeAndEnterValueWithTimeOut("bus_passanger_form_umur", "23");
    }

    public void tapLanjutButtonBus() {
        tapElement("bus_button_lanjut");

        if (isElementVisible("bus_harga_berubah_label", 60)) {
            verifyElementExist("bus_harga_sebelum_label");
            verifyElementExist("bus_harga_terbaru_label");
            tapElement("bus_button_lanjut_bayar");
        }
    }

    public void fillPassengerIdentityIfRequired() {
        if (!isElementExist("bus_passenger_form_identity_number")) {
            validateDisplayed("bus_button_lanjut");
        } else {
            tapElement("bus_dropdown_identity_type");
            tapElement("bus_id_ktp_name");
            typeIdentityNumber();
        }
    }

    private void typeIdentityNumber() {
        long identityNumber = (long) (Math.random() * Math.pow(13,13));

        typeAndEnterValueWithTimeOut("bus_passenger_form_identity_number", "1" + identityNumber);
    }

    public void validateInvalidNameFormat() {
        waitForVisibilityOf(constructLocator("bus_general_name", BusData.invalidNameFormatError),3);
        verifyElementExist(constructLocator("bus_general_name", BusData.invalidNameFormatError));
    }

    public void validateInvalidEmailFormat() {
        waitForVisibilityOf(constructLocator("bus_general_name", BusData.invalidEmailFormatError),3);
        verifyElementExist(constructLocator("bus_general_name", BusData.invalidEmailFormatError));
    }

    public void validateInvalidPhoneFormat() {
        waitForVisibilityOf(constructLocator("bus_general_name", BusData.invalidPhoneNumberFormatError),3);
        verifyElementExist(constructLocator("bus_general_name", BusData.invalidPhoneNumberFormatError));

        HelperData.setLastActionPage(new TravelBusPassengerFormPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
