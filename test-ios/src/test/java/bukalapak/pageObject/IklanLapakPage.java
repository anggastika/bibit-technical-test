package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PROMData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class IklanLapakPage extends BasePage {
    public IklanLapakPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyIklanLapakPageDisplayed() {
        closeIklanLapakOnboarding();
        verifyElementExist("iklan_lapak_page_title");
        HelperData.setLastActionPage(new IklanLapakPage(iosDriver));
    }

    public void inputPromotionName(String promotionName) {
        typeAndEnterValue("nama_promosi_iklan_lapaktext_field", promotionName);
        PROMData.setIklanLapakPromotionName(promotionName);
    }

    public void chooseCategory(String categoryName) {
        tapElement("category_iklan_lapak_settings_cb");
        tapElement(constructLocator("choose_category_iklan_lapak_settings_dropdown", categoryName));
        PROMData.setIklanLapakPromotionCategory(categoryName);
    }

    public void verifySuccessCreateIklanLapak() {
        verifyElementDisplayed("iklan_lapak_siap_tayang_text");
    }

    public void verifyIklanLapakStatus(String promotionName, String status) {
        verifyElementDisplayed(constructLocator("iklan_lapak_name_text", promotionName));
        assertEquals(promotionName, getTextFromElement(constructLocator("iklan_lapak_name_text", promotionName)));
        assertEquals(status, getTextFromElement(constructLocator("iklan_lapak_status_text", promotionName)));
        HelperData.setLastActionPage(new IklanLapakPage(iosDriver));
    }

    public void tapFirstCampaignIklanLapak() {
        waitForVisibilityOf("first_row_iklan_lapak_campaign");
        tapElement("first_row_iklan_lapak_campaign");
    }

    public void verifyActiveProductIklanLapak() {
        verifyElementDisplayed("tampilan_iklan_lapak_text");
        verifyElementDisplayed("detail_campaign_image_iklan_lapak");
        verifyElementDisplayed("detail_campaign_kunjungi_lapak");
        verifyElementDisplayed("detail_campaign_feedback");
        verifyElementDisplayed("detail_campaign_product_price");
        verifyElementDisplayed("detail_campaign_product_rating");
    }

    public void deleteIklanLapak() {
        tapElement("detail_campaign_delete_button");
        tapElement("detail_campaign_ya_hapus_button", 5);
    }

    public void tapOnSelectedIklanLapakCampaign(String campaignName) {
        tapElement(constructLocator("selected_iklan_lapak_row", campaignName));
    }

    public void validateIklanLapakIsDeleted(String campaignName) {
        verifyElementNotExist(constructLocator("selected_iklan_lapak_row", campaignName));
    }

    public void verifyOnboardingIklanLapak() {
        waitForVisibilityOf("iklan_lapak_onboarding_header_text");
        waitForVisibilityOf("iklan_lapak_onboarding_title_text");
    }

    public void verifyTambahPromosiPage() {
        waitForVisibilityOf("iklan_lapak_tambah_promosi_header_text");
        waitForVisibilityOf("iklan_lapak_tambah_promosi_title_text");
    }

    public void verifyInfoReachMaxLimit() {
        verifyElementDisplayed("iklan_lapak_info_reach_max_limit");
        HelperData.setLastActionPage(new IklanLapakPage(iosDriver));
    }

    public void verifyDaftarPromosiLimit(String items, String maxItems) {
        String numberOfCampaign = items + "/" + maxItems;
        verifyElementDisplayed(constructLocator("iklan_lapak_daftar_promosi_max_text", numberOfCampaign));
        HelperData.setLastActionPage(new IklanLapakPage(iosDriver));
    }

    public void verifyListOfCampaigns() {
        verifyElementExist("iklan_lapak_item");
        verifyElementNotExist("iklan_lapak_empty_state");
        HelperData.setLastActionPage(new IklanLapakPage(iosDriver));
    }

    private void closeIklanLapakOnboarding() {
        if (isElementVisible("iklan_lapak_coba_iklan_button")) {
            tapCenterOfElement("iklan_lapak_onboarding_close_button");
        }
    }

    public void validateIklanLapakCampaignDescription() {
        validateDisplayed(constructLocator("iklan_lapak_name_text", PROMData.getIklanLapakPromotionName()));
        validateValue().equals(PROMData.getIklanLapakTotalProducts() + " barang dalam Kategori " + PROMData.getIklanLapakPromotionCategory(), getElementLabel("iklan_lapak_description_text"));
    }

    // Tambah Promosi page 1
    public void selectIklanLapakCheckbox() {
        tapElement("iklan_lapak_tambah_promosi_checkbox");
    }

    public void selectIklanLapakCheckbox(int products) {
        waitForVisibilityOf("iklan_lapak_tambah_promosi_checkbox", 5);
        for (int i = 0; i < products; i++) {
            if (!getText("iklan_lapak_tambah_promosi_info_product_selection").contains(products + "/10")) {
                if ((i % 2) == 0) {
                    swipeToNextCheckbox();
                }
                selectIklanLapakCheckbox();
            }
        }
        PROMData.setIklanLapakTotalProducts(String.valueOf(products));
    }

    public void swipeToNextCheckbox() {
        swipeDown(0.8, 0.55);
    }

    public void verifyTotalSelectedProducts(String selectedProduct) {
        validateValue().contains(selectedProduct + "/10", getText("iklan_lapak_tambah_promosi_info_product_selection"));
    }
    // End region Tambah Promosi page 1

    // Tambah Promosi page 2
    private void inputCustomAmountBudgetOption(String bid) {
        tapCenterOfElement("iklan_lapak_atur_batas_budget");
        waitForVisibilityOf("iklan_lapak_custom_budget_field");
        typeAndEnterValue("iklan_lapak_custom_budget_field", bid);
    }

    public void selectBudgetLimitOption(String option) {
        if (option.equalsIgnoreCase("tanpa batas")) {
            tapElement("tanpa_batas_iklan_lapak_settings_rb");
        } else {
            inputCustomAmountBudgetOption(option);
        }
    }

    public void verifyErrorMessage(String error) {
        verifyElementDisplayed(constructLocator("iklan_lapak_error_message", error));
    }
    // End region Tambah Promosi page 2

    // Confirmation modal
    public void checkIklanLapakConfirmationPage(String state) {
        waitForVisibilityOf("oke_mengerti_iklan_lapak_button", 10);
        if (state.equalsIgnoreCase("active")) {
            validateDisplayed("iklan_lapak_success_confirmation");
            validateDisplayed("iklan_lapak_success_active_confirmation");
            validateDisplayed("iklan_lapak_success_active_info_confirmation");
        } else {
            validateDisplayed("iklan_lapak_dormant_confirmation");
            validateDisplayed("iklan_lapak_success_dormant_confirmation");
            validateDisplayed("iklan_lapak_success_dormant_info_confirmation");
        }
    }
    // End region Confirmation modal

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void checkFirstCampaignStatus(String status) {
        assertTextContains(status, getTextFromElement("first_iklan_lapak_campaign_status_text"), "Status iklan lapak campaign invalid");
        HelperData.setLastActionPage(new IklanLapakPage(iosDriver));
    }

    public void tapOnUpdatePromosiButton(String action) {
        if (action.equalsIgnoreCase("aktifkan")) {
            tapElement("detail_campaign_aktifkan_button");
        } else {
            tapElement("detail_campaign_non_aktifkan_button");
        }
    }

    public void tapOnPelajariButton() {
        tapElement("iklan_lapak_pelajari_button");
    }

    public void tapOnCobaIklanLapakButton() {
        tapElement("iklan_lapak_coba_iklan_button");
    }

    public void validatePromotedBudgetIklanLapak(String credentialUser) {
        int budget = API_CALL.getPromotedBudget(credentialUser);
        LogUtil.info("promoted budget: " + budget);
        assertEquals(API_CALL.getIklanLapakPrice(credentialUser), PROMData.getSisaBudget() - budget, "Iklan Lapak not charged! Please Check!");
    }
}
