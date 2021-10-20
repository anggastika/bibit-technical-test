package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedPushBarangGrupPage extends BasePage {

    public PromotedPushBarangGrupPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPromotedGrupPage() {
        verifyElementDisplayed("promoted_push_grup_page");
        verifyElementDisplayed("promoted_push_grup_barang_terpilih_text");
        HelperData.setLastActionPage(new PromotedPushBarangGrupPage(iosDriver));
    }

    public void inputHargaPerKlikBarang(String harga) {
        validateDisplayed("promoted_push_grup_harga_per_klik_barang_section_text");
        typeAndEnterValueWithTimeOut("promoted_push_grup_harga_per_klik_barang_field", harga);
    }

    public void inputBatasBudget(String budget) {
        validateDisplayed("promoted_push_grup_batas_budget_promosi_section_text");
        typeAndEnterValueWithTimeOut("promoted_push_grup_batas_budget_promosi_field", budget);
    }

    public void validateErrorMessageTopupGrup() {
        waitForVisibilityOf("promoted_push_error_minimum_bid", 15);
        HelperData.setLastActionPage(new PromotedPushBarangGrupPage(iosDriver));
    }

    public void validateErrorMessageTopupBatasBudgetGrup() {
        waitForVisibilityOf("promoted_push_error_minimum_batas_budget_text", 15);
        HelperData.setLastActionPage(new PromotedPushBarangGrupPage(iosDriver));
    }

    public void inputPromotedGroupTitle(String promotedGrupTitle) {
        waitForVisibilityOf("promoted_push_grup_title_field");
        typeAndEnterValue("promoted_push_grup_title_field", promotedGrupTitle);
    }

    private void inputBidLimit(String selection) {
        tapElement("promoted_push_grup_batas_budget_radio_button");
        waitForVisibilityOf("promoted_push_grup_batas_budget_promosi_field");
        typeAndEnterValue("promoted_push_grup_batas_budget_promosi_field", selection);
    }

    private void selectDate(String date) {
        waitForVisibilityOf("promoted_push_grup_date_limit_selection_popup");
        tapCenterOfElement(constructLocator("promoted_push_grup_date_selection", date), 0);
    }

    private void tapDateDropdown() {
        tapElement("promoted_push_grup_date_limit_radio_button");
        waitForVisibilityOf("promoted_push_grup_date_limit_dropdown");
        tapElement("promoted_push_grup_date_limit_dropdown");
    }

    public void selectPromotedPushLimit(String optionType, String selection) {
        if (optionType.toLowerCase().contains("batas budget")) {
            inputBidLimit(selection);
        } else {
            tapDateDropdown();
            selectDate(selection);
        }
    }

    public void tapSimpanPengaturanButton() {
        waitForVisibilityOf("promoted_push_grup_simpan_pengaturan_button");
        tapElement("promoted_push_grup_simpan_pengaturan_button");
    }

    public void verifyMaxCampaignError() {
        verifyElementExist("promoted_push_grup_max_campaign_error");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
