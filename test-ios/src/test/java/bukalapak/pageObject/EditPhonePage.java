package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */

public class EditPhonePage extends BasePage {
    public EditPhonePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    //region Navigation
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    //endregion Navigation

    //region Simple action
    public void typeOnNewPhoneNumberText(String newphone){
        typeAndEnterValue("phone_number_new_field", newphone);
    }

    public void typeOnPasswordText(String pass){
        typeAndEnterValue("phone_number_password_field", pass);
    }

    public void tapInactiveTFAToggle(){
        tapElement("tfa_toggle_switch");
        if (isElementVisible("inactive_tfa_text")) {
            tapElement("inactive_tfa_lanjutkan_button");
        }
        else {
            tapElement("tfa_toggle_switch");
        }
        HelperData.setLastActionPage(new EditPhonePage(iosDriver));
    }

    public void updateCurrentPhoneNumber(String newphone, String pass){
        typeOnNewPhoneNumberText(newphone);
        typeOnPasswordText(pass);
        tapElement("pengaturan_akun_simpan_button");
        waitForVisibilityOf("phone_number_confirmation_popup",10);
        tapElement("phone_number_confirmation_popup");
    }

    public void updateCurrentDANAPhoneNumber(String newphone, String pass){
        typeOnNewPhoneNumberText(newphone);
        typeOnPasswordText(pass);
        tapElement("pengaturan_akun_simpan_button");
        if (isElementVisible("dana_change_pn_checkbox_txt",5)) {
            verifyElementExist("dana_change_pn_header");
        } else if (isElementVisible("phone_number_confirmation_popup")) {
            tapElement("phone_number_confirmation_popup");
        }
    }
    //endregion Simple action

    //region Validation
    public void userOnEditPhonePage() {
        verifyElementExist("phone_page_title",5,"element is not exist");
        HelperData.setLastActionPage(new EditPhonePage(iosDriver));
    }

    public void userOnSuccessEditPhonePage() {
        if (isElementVisible("phone_number_success_update_text", 10)) {
            tapElement("phone_number_success_update_button");
        }
        tapElement("close_dana_modal",5);
        HelperData.setLastActionPage(new EditPhonePage(iosDriver));
    }

    //endregion Validation

    public void verifyPhoneNumberSettingPage() {
        validateDisplayed("phone_number_header_title");
        waitForElementClickable("phone_number_header_back_button", 5);
    }

    public void tapBackButton(){
        tapElement("phone_number_header_back_button");
    }
}
