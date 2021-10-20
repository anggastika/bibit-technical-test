package bukalapak.pageObject.vp.insurance;

import bukalapak.data.HelperData;
import bukalapak.pageObject.VpBasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.util.Calendar;

/**
 * Created by ferawati h.p. on 30/06/20.
 */
public class AsuransiBepergianRegistrationPage extends VpBasePage {

    public AsuransiBepergianRegistrationPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void inputFormulirPengajuanFullname() {
        typeValue("asuransi_bepergian_registration_page_form_pengajuan_fullname_field", fakerFullName(""));
    }

    public void inputFormulirPengajuanEmail() {
        typeValue("asuransi_bepergian_registration_page_form_pengajuan_email_field", fakerEmail());
    }

    public void inputFormulirPengajuanPhone() {
        typeValue("asuransi_bepergian_registration_page_form_pengajuan_phone_field", fakerPhoneNumber("0812", 8));
    }

    public void tapFormulirPengajuanTertanggung(int people) {
        if (isElementExist(constructLocator("asuransi_bepergian_registration_page_form_pengajuan_tertanggung_option", people))) {
            tapElement(constructLocator("asuransi_bepergian_registration_page_form_pengajuan_tertanggung_option", people));
        }
    }

    public void tapLanjutButton() {
        swipeUpToElement("asuransi_bepergian_registration_page_form_pengajuan_lanjut_button");
        tapElement("asuransi_bepergian_registration_page_form_pengajuan_lanjut_button");
    }

    public void inputFormulirPengajuanAddress() {
        typeValue("asuransi_bepergian_registration_page_form_pengajuan_address_field", fakerAddress());
    }

    public void inputFormulirPengajuanStreetName() {
        typeValue("asuransi_bepergian_registration_page_form_pengajuan_street_field", fakerStreetAddress());
    }

    public void chooseFormulirPengajuanCity() {
        tapElement("asuransi_bepergian_registration_page_form_pengajuan_city_field");
        tapElement("asuransi_bepergian_registration_page_form_pengajuan_city_value");
    }

    public void chooseFormulirPengajuanKecamatan() {
        tapElement("asuransi_bepergian_registration_page_form_pengajuan_kecamatan_field");
        tapElement("asuransi_bepergian_registration_page_form_pengajuan_kecamatan_value");
    }

    public void inputFormulirPengajuanPostalCode() {
        typeValue("asuransi_bepergian_registration_page_form_pengajuan_postal_code_field", fakerZipCode());
    }

    public void inputDetailTertanggungFullname() {
        typeValue("asuransi_bepergian_registration_page_detail_tertanggung_fullname_field", fakerFullName(""));
    }

    public void inputDetailTertanggungBirthdate() {
        waitForVisibilityOf("asuransi_bepergian_registration_page_detail_tertanggung_birthdate", 10);
        tapElement("asuransi_bepergian_registration_page_detail_tertanggung_birthdate");
        waitForVisibilityOf("asuransi_bepergian_registration_page_detail_tertanggung_apply_button");
        tapElement("asuransi_bepergian_registration_page_detail_tertanggung_apply_button");
    }



    public void tapSimpanButton() {
        waitForElementClickable("asuransi_bepergian_registration_page_detail_tertanggung_simpan_button", 10);
        tapElement("asuransi_bepergian_registration_page_detail_tertanggung_simpan_button");
    }

    public void validateBepergianInsuranceEmptyInfoPemegangPolisInput() {
        verifyElementExist("asuransi_bepergian_registration_page_form_pengajuan_empty_fullname_error_message_text");
        verifyElementExist("asuransi_bepergian_registration_page_form_pengajuan_empty_email_error_message_text");
        verifyElementExist("asuransi_bepergian_registration_page_form_pengajuan_empty_phone_error_message_text");
        HelperData.setLastActionPage(new AsuransiBepergianRegistrationPage(iosDriver));
    }

    public void validateBepergianInsuranceEmptyInfoTertanggungInput() {
        verifyElementExist("asuransi_bepergian_registration_page_form_pengajuan_empty_fullname_error_message_text");
        verifyElementExist("asuransi_bepergian_registration_page_form_pengajuan_empty_birthdate_error_message_text");
        HelperData.setLastActionPage(new AsuransiBepergianRegistrationPage(iosDriver));
    }

    public void validateBepergianInsuranceEmptyInfoTempatTinggalInput() {
        verifyElementExist("asuransi_bepergian_registration_page_form_pengajuan_address_error_message_text");
        verifyElementExist("asuransi_bepergian_registration_page_form_pengajuan_street_error_message_text");
        verifyElementExist("asuransi_bepergian_registration_page_form_pengajuan_city_error_message_text");
        verifyElementExist("asuransi_bepergian_registration_page_form_pengajuan_kecamatan_error_message_text");
        verifyElementExist("asuransi_bepergian_registration_page_form_pengajuan_postal_code_error_message_text");
        HelperData.setLastActionPage(new AsuransiBepergianRegistrationPage(iosDriver));
    }

    public void inputInvalidFieldInput(String field) {

        switch (field) {
            case "phone number":
                typeValue("asuransi_bepergian_registration_page_form_pengajuan_phone_field", fakerPhoneNumber("0812", 1));
                break;
            case "email":
                typeValue("asuransi_bepergian_registration_page_form_pengajuan_email_field", fakerEmail()+"@gmail.com");
                break;
            default:
                Assert.fail("Field doesn't include");
                break;
        }
    }

    public void validateBepergianInsuranceErrorMessage(String message) {
        validateExist(constructLocator("asuransi_bepergian_registration_page_form_pengajuan_invalid_format_error_message_text", message), 25);
        HelperData.setLastActionPage(new AsuransiBepergianRegistrationPage(iosDriver));
    }

    public void validateOnDetailTertanggungPage() {
        verifyElementExist("asuransi_bepergian_registration_page_detail_tertanggung_fullname_field");
        verifyElementExist("asuransi_bepergian_registration_page_detail_tertanggung_birthdate");
    }

    public void tapBeliSekarangButton() {
        swipeUpToElement("asuransi_bepergian_landing_page_belisekarang_button");
        tapElement("asuransi_bepergian_landing_page_belisekarang_button");
    }

    public void tapLanjutIsiFormulirButton() {
        verifyElementExist("asuransi_bepergian_landing_page_lanjutisiformulir_button");
        tapElement("asuransi_bepergian_landing_page_lanjutisiformulir_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
