package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedPushSatuanPage extends BasePage {

    public PromotedPushSatuanPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPromotedPushBarangPage() {
        verifyElementExist("promoted_push_satuan_title_text");
        HelperData.setLastActionPage(new PromotedPushSatuanPage(iosDriver));
    }

    public void inputBidPerClick(String bid) {
        verifyElementExist("promoted_push_satuan_bid_field");
        typeAndEnterValue("promoted_push_satuan_bid_field", bid);
        HelperData.setLastActionPage(new PromotedPushSatuanPage(iosDriver));
    }

    public void setPromotedPushStartingDate() {
        verifyElementExist("promoted_push_satuan_date_picker");
        tapElement("promoted_push_satuan_date_picker");
        tapElement("promoted_push_satuan_terapkan_button");
        HelperData.setLastActionPage(new PromotedPushSatuanPage(iosDriver));
    }

    public void setPromotedPushToggleButton(String state) {
        swipeUpToElement("promoted_push_toggle_button");
        if (state.equalsIgnoreCase("disable")) {
            tapElement("promoted_push_toggle_button");
            verifyElementDisplayed("promoted_push_not_active_state_text");
        } else {
            verifyElementDisplayed("promoted_push_active_state_text");
        }
        HelperData.setLastActionPage(new PromotedPushSatuanPage(iosDriver));
    }

    public void continueCoachmark() {
        do {
            tapElement("promoted_push_coachmark_2_lanjut_button");
        } while (isElementVisible("promoted_push_coachmark_2_lanjut_button"));
        tapElement("promoted_push_coachmark_2_tutup_button");
        HelperData.setLastActionPage(new PromotedPushSatuanPage(iosDriver));
    }

    public void verifyCoachmarkDisplayed() {
        waitForVisibilityOf("promoted_push_coachmark_2_text", 15);
    }

    //promoted satuan
    public void userOnPromotedPushSatuanPage() {
        verifyElementDisplayed("promoted_push_satuan_page");
        verifyElementDisplayed("promoted_push_satuan_sisa_budget_section_text");
    }

    public void inputHargaPerKlik(String nominal) {
        validateDisplayed("promoted_push_satuan_harga_per_klik_section_text");
        typeAndEnterValueWithTimeOut("promoted_push_satuan_harga_per_klik_field", nominal);
    }

    public void validateErrorMessageTopup() {
        waitForVisibilityOf("promoted_push_error_minimum_bid", 15);
    }

    public void tapSimpanPengaturanButton() {
        waitForVisibilityOf("promoted_push_satuan_simpan_pengaturan_button");
        tapElement("promoted_push_satuan_simpan_pengaturan_button");
    }

    // End promoted satuan

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
