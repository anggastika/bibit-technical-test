package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ApplyCCPage extends PostpaidBasePage {

    public ApplyCCPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifiedCardScreenDisplayed() {
        assertFalse(isElementVisible("postpaid_apply_cc_title_text"), "Still on landing page, form screen is not opened yet!");
    }

    public void validateOnPage() {
        skipOnboarding("postpaid_apply_cc_onboarding_button_next", "postpaid_apply_cc_onboarding_button_done");
        isPageDisplayed("postpaid_apply_cc_title_text");
        skipScenarioIfToggledOff("postpaid_apply_cc_card_field");
        HelperData.setLastActionPage(new ApplyCCPage(iosDriver));
    }

    public void chooseCard() {
        tapElement("postpaid_apply_cc_card_field");
        verifyElementExist("postpaid_apply_cc_pilih_title_text");
        tapElement("postpaid_apply_cc_choose_button");
        swipeUpToElement("postpaid_apply_cc_isi_formulir_button");
        tapElement("postpaid_apply_cc_isi_formulir_button");
    }

    public void tapLanjutkanButton() {
        tapElement("postpaid_apply_cc_lanjutkan_button");
    }

    public void inputFillForm(String noKtp, String tempatLahir, String salary) {
        verifyElementExist("postpaid_apply_cc_title_text");
        typeAndEnterValueWithTimeOut("postpaid_apply_cc_no_ktp_field", noKtp);
        typeAndEnterValueWithTimeOut("postpaid_apply_cc_tempat_lahir_field", tempatLahir);
        tapElement("postpaid_apply_cc_domisili_field");
        tapElement("postpaid_apply_cc_domisili_bogor");
        swipeUpToElement("postpaid_apply_cc_selanjutnya_button");
        tapElement("postpaid_apply_cc_pekerjaan_field");
        tapElement("postpaid_apply_cc_pekerjaan_swasta");
        tapElement("postpaid_apply_cc_status_pekerjaan_field");
        tapElement("postpaid_apply_cc_status_pekerjaan_kontrak");
        tapElement("postpaid_apply_cc_tahun_bekerja_field");
        tapElement("postpaid_apply_cc_tahun_2020");
        tapElement("postpaid_apply_cc_terapkan_button");
        tapElement("postpaid_apply_cc_bulan_bekerja_field");
        tapElement("postpaid_apply_cc_bulan_januari");
        tapElement("postpaid_apply_cc_terapkan_button");
        typeAndEnterValueWithTimeOut("postpaid_apply_cc_sallary_input", salary);
        swipeUpToElement("postpaid_apply_cc_selanjutnya_button");
        tapElement("postpaid_apply_cc_selanjutnya_button");
        verifyElementExist("postpaid_apply_cc_info_detail_title_text");
        tapElement("postpaid_apply_cc_setuju_checkbox");
        tapElement("postpaid_apply_cc_ajukan_button");
    }

    public void validateUnverifiedAccount() {
        verifyElementExist("postpaid_apply_cc_not_verifived");
    }

    public void verifiInvalidFillForm(String noKtp) {
        verifyElementExist("postpaid_apply_cc_title_text");
        typeAndEnterValueWithTimeOut("postpaid_apply_cc_no_ktp_field", noKtp);
    }

    public void tapOnIconTransactionHistory() {
        tapElement("postpaid_apply_cc_history_button");
    }

    public void validateTransactionHistoryPage() {
        verifyElementExist("postpaid_apply_cc_history_title_text");
    }

    public void verifiSubmitForm() {
        verifyElementExist("postpaid_apply_cc_info_title_text");
    }

    public void validateItemLoaded(String type) {
        if (type == null) {
            verifyElementExist("postpaid_apply_cc_history_title_text");
        } else {
            verifyElementExist("postpaid_apply_cc_history_title_text");
            assertTextContains("Belum pernah buat pengajuan", getTextFromElement("postpaid_apply_cc_history_page_empty"));
        }
    }

    public void showAlertMessage() {
        waitForVisibilityOf("postpaid_apply_cc_allert_message", 30);
        verifyElementExist("postpaid_apply_cc_allert_message");
        HelperData.setLastActionPage(new ApplyCCPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
