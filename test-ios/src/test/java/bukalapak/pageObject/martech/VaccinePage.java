package bukalapak.pageObject.martech;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import bukalapak.data.GMTData;
import bukalapak.pageObject.HomePage;
import org.apache.commons.lang3.RandomUtils;
import java.util.Date;
import java.util.concurrent.*;
import java.text.SimpleDateFormat;
import static bukalapak.TestInstrument.dotenv;

/**
 * Created by koryang on 19/05/21.
 */

public class VaccinePage extends BasePage {

    public VaccinePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userClickDaftarVaksinSekarang() {
        waitForVisibilityOf("vaksin_daftar_sekarang", 50);
        tapElement("vaksin_daftar_sekarang");
        if (isElementVisible("vaksin_daftar_sekarang")) {
            tapElement("vaksin_daftar_sekarang");
        }
    }

    public void verifyVaccineRegistrationLocationPage() {
        verifyElementExist("vaksin_daftar_lansia", 20, "vaccine registration location page not found");
        HelperData.setLastActionPage(new VaccinePage(iosDriver));
    }

    public void userClickBuatJanji() {
        waitForVisibilityOf("vaksin_buat_janji", 30);
        swipeUpToElement("vaksin_event_bukalapak", 3);
        tapElement("vaksin_buat_janji_last");
    }

    public void userClickOnLacakJadwal() {
        waitForVisibilityOf("vaksin_lacak_jadwal_menu", 30);
        tapElement("vaksin_lacak_jadwal_menu");
    }

    public void userClickOnBackToHome() {
        waitForVisibilityOf("vaksin_transaction_detail_back_to_home_button", 30);
        tapElement("vaksin_transaction_detail_back_to_home_button");
    }

    public void userClickBacaSekarang() {
        waitForVisibilityOf("vaksin_baca_sekarang", 30);
        tapElement("vaksin_baca_sekarang");
    }

    public void verifyVaccineInfoPage() {
        verifyElementExist("vaksin_saya_mengerti_setuju", 20, "vaccine info page not found");
        HelperData.setLastActionPage(new VaccinePage(iosDriver));
    }

    public void clickAgreeVaccineInfo() {
        waitForVisibilityOf("vaksin_saya_mengerti_setuju", 20);
        tapElement("vaksin_saya_mengerti_setuju");
    }

    public void validateSheduleSelectionPage() {
        waitForVisibilityOf("vaksin_pilih_jadwal_title_txt", 20);
        validateDisplayed("vaksin_pilih_jadwal_title_txt");
        validateDisplayed("vaksin_alamat_section");
        validateDisplayed("vaksin_lihat_peta_txt");
        validateDisplayed("vaksin_ubah_tanggal_button");
        validateDisplayed("vaksin_lanjutkan_button");
        HelperData.setLastActionPage(new VaccinePage(iosDriver));
    }

    public void clickChangeDateButton() {
        tapElement("vaksin_ubah_tanggal_button");
    }

    public void clickSelectDate() {
        waitForVisibilityOf("vaksin_pilih_tanggal_button", 10);
        tapElement("vaksin_pilih_tanggal_button");
    }

    public void selectDate(String date) {
        tapElement(constructLocator("vaksin_date_selection", date), 15);
    }
    public void selectTimeSlot(String time) {
        tapElement(constructLocator("vaksin_time_slot", time), 15);
    }

    public void clickLanjutkan() {
        waitForVisibilityOf("vaksin_lanjutkan_button", 30);
        tapElement("vaksin_lanjutkan_button");
    }

    public void validatePersonsalInformationPage() {
        validateDisplayed("vaksin_isi_data_title_txt");
        validateDisplayed("vaksin_nik_section");
        validateDisplayed("vaksin_ktp_section");
        validateDisplayed("vaksin_gender_section");
        validateDisplayed("vaksin_email_section");
        validateDisplayed("vaksin_no_hp_section");
        validateDisplayed("vaksin_simpan_button");
        HelperData.setLastActionPage(new VaccinePage(iosDriver));
    }

    public void inputPersonalInformation(String data) {
        inputNIKField(data);
        typeAndEnterValue("vaksin_personal_information_first_name_field", dotenv.get("VACCINE_FIRST_NAME"));
        typeAndEnterValue("vaksin_personal_information_last_name_field", dotenv.get("VACCINE_LAST_NAME"));
        tapElement("vaksin_personal_information_gender_checkbox");
        inputEmailField(data);
        inputPhoneNumberField(data);
        tapElement("vaksin_personal_information_rules_checkbox");
        waitForVisibilityOf("vaksin_personal_information_simpan_button", 20);
        tapElement("vaksin_personal_information_simpan_button");
    }

    public void inputNIKField(String data) {
        String nik = "3313042505920001";
        if (data.equals("correct")) {
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
            Date date = new Date(TimeUnit.MILLISECONDS
                    .convert(ThreadLocalRandom.current().nextLong(10000000, 100000000), TimeUnit.MINUTES));
            String strDate = sdf.format(date);
            nik = "320503" + strDate + "0001";
        }

        waitForVisibilityOf("vaksin_personal_information_nik_field", 20);
        typeAndEnterValue("vaksin_personal_information_nik_field", nik);
        GMTData.setNIK(nik);
    }

    public void inputEmailField(String data) {
        String email = "qwerty";
        if (data.equals("correct")) {
            int randomNumber = + new java.util.Random().nextInt(100000);
            email = "email" + Integer.toString(randomNumber) + "@email.com";
        }

        typeAndEnterValue("vaksin_personal_information_email_field", email);
    }

    public void inputPhoneNumberField(String data) {
        String phone = "qwerty";
        if (data.equals("correct")) {
            String randomize = String.valueOf(RandomUtils.nextInt(100000000, 999999999));
            phone = "081" + randomize;
        }

        typeAndEnterValue("vaksin_personal_information_phone_number_field", phone);
    }

    public void inputName(String name) {
        if (isElementExist("vaksin_name_field", 30)) {
            typeAndEnterValue("vaksin_name_field", name);
        } else {
            userClickOnLacakJadwal();
            waitForVisibilityOf("vaksin_name_field", 30);
            typeAndEnterValue("vaksin_name_field", name);
        }
    }

    public void inputNIK(String nik) {
        waitForVisibilityOf("vaksin_nik_field", 30);
        typeAndEnterValue("vaksin_nik_field", nik);
        tapElement("vaksin_lacak_jadwal_button");
    }

    public void validateTransactionDetailPage(String nik) {
        verifyElementExist("vaksin_transaction_detail_label", 15, "Vaccine transaction detail not found");
        verifyElementExist(constructLocator("vaksin_transaction_detail_nik_label", nik));
        HelperData.setLastActionPage(new VaccinePage(iosDriver));
    }

    public void validateTrackNIKMessage(String message) {
        verifyElementExist(constructLocator("vaksin_track_message", message), 15, "Vaccine transaction detail not found");
        HelperData.setLastActionPage(new VaccinePage(iosDriver));
    }

    public void validateVaccineRegistrationHomePage() {
        verifyElementExist("vaksin_vaccine_home_page_label", 15, "Vaccine home page not found");
        HelperData.setLastActionPage(new VaccinePage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyTicketCategoryField() {
        verifyElementExist("vaksin_ticket_category_dropdown", 15, "Vaccine Category field not found");
        HelperData.setLastActionPage(new VaccinePage(iosDriver));
    }

    public void selectTicketCategory() {
        tapElement("vaksin_ticket_category_dropdown", 10);
        waitForVisibilityOf("vaksin_ticket_category_pickerwheel", 10);
        selectPickerWheel("Usia 18-59 Tahun");
        tapElement("vaksin_done_pickerwheel");
        verifyElementExist("vaksin_second_ticket_category", 15, "Selected Vaccine Category not found");
        HelperData.setLastActionPage(new VaccinePage(iosDriver));
    }
}
