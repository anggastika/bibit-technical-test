package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PROMData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.util.ArrayList;

public class BuatSebarPromosiPage extends BasePage {
    public BuatSebarPromosiPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyBuatSebarPromosiPageDisplayed() {
        waitForVisibilityOf("product_name_title", 15);
        validateExist("product_name_title");
        validateExist("info_label_text");
        validateExist("info_total_favorit_label");
        validateExist("info_message_sebar_promosi_logo");
        validateExist("chat_promo_text_field");
        validateExist("kirim_pesan_button");
        HelperData.setLastActionPage(new BuatSebarPromosiPage(iosDriver));
    }

    public void typeMessage(String valueMesage) {
        waitForElementClickable("chat_promo_text_field", 3);
        typeAndEnterValueWithTimeOut("chat_promo_text_field", valueMesage);
    }

    public void tapKirimPesanButton() {
        waitForVisibilityOf("kirim_pesan_button");
        tapElement("kirim_pesan_button");
    }

    public void verifyPopUpPesanSiapDikirim() {
        waitForVisibilityOf("pesan_siap_dikirim_popup_desc", 20);
        validateDisplayed("pesan_siap_dikirim_popup_title");
        validateDisplayed("pesan_siap_dikirim_popup_nanti_dulu_btn");
        validateDisplayed("pesan_siap_dikirim_popup_kirim_pesan_btn");
    }

    public void tapKirimPesanPopUpButton() {
        waitForVisibilityOf("pesan_siap_dikirim_popup_kirim_pesan_btn");
        tapElement("pesan_siap_dikirim_popup_kirim_pesan_btn");
    }

    public void verifyInvalidMessageInfo() {
        waitForVisibilityOf("buat_sebar_promosi_error_message", 15);
        HelperData.setLastActionPage(new BuatSebarPromosiPage(iosDriver));
    }

    public void tapIconInfoBuatSebarPromosi() {
        waitForVisibilityOf("buat_sebar_promosi_info_icon");
        tapElement("buat_sebar_promosi_info_icon");
    }

    public void verifyPopUpInfoSebarPromosi() {
        waitForVisibilityOf("buat_sebar_promosi_info_popup_title", 15);
        validateExist("buat_sebar_promosi_info_popup_desc");
        validateExist("buat_sebar_promosi_info_popup_oke_lanjut_btn");
        tapElement("buat_sebar_promosi_info_popup_oke_lanjut_btn");
        HelperData.setLastActionPage(new BuatSebarPromosiPage(iosDriver));
    }

    public void verifyPopUpKeluarDariHalaman() {
        waitForVisibilityOf("buat_sebar_promosi_keluar_dari_halaman_popup_title", 15);
        validateDisplayed("buat_sebar_promosi_keluar_dari_halaman_popup_desc");
        validateDisplayed("buat_sebar_promosi_keluar_dari_halaman_popup_keluar_btn");
        validateDisplayed("buat_sebar_promosi_keluar_dari_halaman_popup_tetap_btn");
    }

    public void verifyEmptyTextFieldSebarPromosi() {
        waitForVisibilityOf("buat_sebar_promosi_remain_char_txt", 5);
        HelperData.setLastActionPage(new BuatSebarPromosiPage(iosDriver));
    }

    public void tapAmountOfPeople() {
        waitForVisibilityOf("info_total_favorit_label");
        tapElement("info_total_favorit_label");
    }

    public void verifyDaftarPenerimaPesanPopUp() {
        waitForVisibilityOf("buat_sebar_promosi_daftar_penerima_pesan_usr", 15);
        waitForVisibilityOf("buat_sebar_promosi_daftar_penerima_pesan_usr_img", 15);
        validateDisplayed("buat_sebar_promosi_daftar_penerima_pesan_title");
        validateDisplayed("buat_sebar_promosi_daftar_penerima_pesan_x_btn");
        validateExist("buat_sebar_promosi_daftar_penerima_pesan_usr_amount");
        HelperData.setLastActionPage(new BuatSebarPromosiPage(iosDriver));
    }

    public void verifyEmptyFavOrEmptyItems(String typeSebarPromosi) {
        switch (typeSebarPromosi) {
            case "not have favorite froduct":
                waitForVisibilityOf("buat_sebar_promosi_no_fav_product_title_txt", 20);
                validateDisplayed("buat_sebar_promosi_no_fav_product_desc_txt");
                validateDisplayed("buat_sebar_promosi_no_fav_product_lihat_tips_btn");
                break;
            case "not have product sale":
                waitForVisibilityOf("buat_sebar_promosi_no_product_sale_title_txt", 20);
                validateDisplayed("buat_sebar_promosi_no_product_sale_desc_txt");
                validateDisplayed("buat_sebar_promosi_no_product_sale_upload_barang_btn");
                break;
            default:
                break;
        }
    }

    public void tapLihatTipsOrUploadItems(String typeBtnSebarPromosi) {
        switch (typeBtnSebarPromosi) {
            case "lihat tips":
                tapElement("buat_sebar_promosi_no_fav_product_lihat_tips_btn", 5);
                break;
            case "upload barang":
                tapElement("buat_sebar_promosi_no_product_sale_upload_barang_btn", 5);
                break;
            default:
                break;
        }
    }

    // Managemen Pesan Promosi
    /// Onboarding
    private void skipCRMOnboarding() {
        if (isElementClickable("buat_sebar_promosi_lanjutkan_button")) {
            tapElement("buat_sebar_promosi_lanjutkan_button");
        }
    }

    public void verifyBuatPesanCRMPageDisplayed() {
        skipCRMOnboarding();
        verifyElementDisplayed("buat_sebar_promosi_title");
        HelperData.setLastActionPage(new BuatSebarPromosiPage(iosDriver));
    }

    public void tapLihatContoh() {
        waitForVisibilityOf("buat_sebar_promosi_lihat_contoh", 15);
        tapElement("buat_sebar_promosi_lihat_contoh");
    }

    public void verifyOnboardingCRM() {
        validateDisplayed("buat_sebar_promosi_lihat_contoh_title");
        validateDisplayed("buat_sebar_promosi_lihat_contoh_desc");
    }

    public void closeLihatContoh() {
        waitForVisibilityOf("buat_sebar_promosi_lihat_contoh_close_icon", 10);
        tapElement("buat_sebar_promosi_lihat_contoh_close_icon");
        validateNotDisplayed("buat_sebar_promosi_lihat_contoh_desc");
    }

    public void verifyProductSelectionSection(String state) {
        swipeUpToElement("buat_sebar_promosi_voucher_section_title");
        if (state.contains("automatic")) {
            verifyElementDisplayed("buat_sebar_promosi_automatic_product_section");
        } else {
            verifyElementDisplayed("buat_sebar_promosi_product_selection_section");
        }
        HelperData.setLastActionPage(new BuatSebarPromosiPage(iosDriver));
    }

    public void verifyProductSelectionButton(String type) {
        if (type.contains("empty")) {
            verifyElementDisplayed("buat_sebar_promosi_add_product_button");
            assertEquals(3, getElements("buat_sebar_promosi_add_product_button").size());
        } else {
            verifyElementDisplayed("buat_sebar_promosi_remove_product_button");
        }
        HelperData.setLastActionPage(new BuatSebarPromosiPage(iosDriver));
    }

    public void tapBackFromCRMBuatPesanButton() {
        tapElement("buat_sebar_promosi_back_button");
        tapElement("buat_sebar_promosi_keluar_dari_halaman_popup_keluar_btn");
    }

    private void getSelectedProductAttributes() {
        ArrayList<String> selectedProductName = new ArrayList<>();
        ArrayList<String> selectedProductPrice = new ArrayList<>();
        // need an action to refresh the DOM
        doPullRefresh(3);
        swipeUpToElement("buat_sebar_promosi_selected_product_name_text");
        for (int i = 0; i < getElements("buat_sebar_promosi_selected_product_name_text").size(); i++) {
            selectedProductName.add(getText("buat_sebar_promosi_selected_product_name_text", i));
            selectedProductPrice.add(getText("buat_sebar_promosi_selected_product_price_text", i));
        }
        // max selected products is 3, but only two first items that able to be identified by appium. This step is needed to get all values.
        if (PROMData.getCrmProductSelectionName().size() >= 3) {
            removeSelectedProduct();
            selectedProductName.add(getText("buat_sebar_promosi_selected_product_name_text", 1));
            selectedProductPrice.add(getText("buat_sebar_promosi_selected_product_price_text", 1));
        }
        PROMData.setCrmProductSelectedName(selectedProductName);
        PROMData.setCrmProductSelectedPrice(selectedProductPrice);
    }

    private void removeSelectedProduct() {
        tapElement("buat_sebar_promosi_remove_product_button");
        tapElement("buat_sebar_promosi_yes_confirmation_button");
    }

    public void validateSelectedProduct(int totalSelectedProduct) {
        getSelectedProductAttributes();
        for (int i = 0; i < totalSelectedProduct; i++) {
            assertEquals(PROMData.getCrmProductSelectionName().get(i), PROMData.getCrmProductSelectedName().get(i));
            assertEquals(PROMData.getCrmProductSelectionPrice().get(i), PROMData.getCrmProductSelectedPrice().get(i));
        }
    }

    public void tapProductSelectionField() {
        tapElement("buat_sebar_promosi_add_product_button");
    }

    public void tapSimpanButton() {
        waitForElementClickable("buat_sebar_promosi_add_product_simpan_button", 3);
        tapElement("buat_sebar_promosi_add_product_simpan_button");
    }

    public void validateProductSelectionPage() {
        waitForVisibilityOf("buat_sebar_promosi_add_product_checkbox");
        verifyElementDisplayed("buat_sebar_promosi_add_product_title_text");
        verifyElementDisplayed("buat_sebar_promosi_add_product_simpan_button");
        verifyElementDisplayed("buat_sebar_promosi_add_product_thumbnail_image");
        verifyElementDisplayed("buat_sebar_promosi_add_product_name_text");
        verifyElementDisplayed("buat_sebar_promosi_add_product_sale_price_text");
        verifyElementDisplayed("buat_sebar_promosi_add_product_limit_text");
        if (isElementExist("buat_sebar_promosi_add_product_normal_price_text")) {
            verifyElementDisplayed("buat_sebar_promosi_add_product_discount_text");
        }
    }

    public void tapProductCheckbox(int totalSelectedProduct) {
        PROMData.setTotalSelectedCRMProducts(totalSelectedProduct);
        ArrayList<String> productName = new ArrayList<>();
        ArrayList<String> salePrice = new ArrayList<>();

        for (int i = 0; i < totalSelectedProduct; i++) {
            productName.add(getTextFromElement("buat_sebar_promosi_add_product_name_text", i));
            salePrice.add((getTextFromElement("buat_sebar_promosi_add_product_sale_price_text", i)));
            tapElement("buat_sebar_promosi_add_product_checkbox", i);
        }
        PROMData.setCrmProductSelectionName(productName);
        PROMData.setCrmProductSelectionPrice(salePrice);
    }

    private void validateRemoveProductPopup() {
        waitForVisibilityOf("buat_sebar_promosi_remove_product_confirmation_title");
        verifyElementDisplayed("buat_sebar_promosi_remove_product_confirmation_desc");
        verifyElementDisplayed("buat_sebar_promosi_yes_confirmation_button");
        verifyElementDisplayed("buat_sebar_promosi_no_confirmation_button");
    }

    public void removeSelectedProductOnIndex(String selectedProductIndex) {
        switch (selectedProductIndex) {
            case "first":
                verifyElementDisplayed(constructLocator("buat_sebar_promosi_specific_selected_product_name_text", PROMData.getCrmProductSelectionName().get(0)));
                tapElement("buat_sebar_promosi_remove_product_1_button");
                break;
            case "second":
                verifyElementDisplayed(constructLocator("buat_sebar_promosi_specific_selected_product_name_text", PROMData.getCrmProductSelectionName().get(1)));
                nativeSwipeLeft("buat_sebar_promosi_product_selection_section");
                tapElement("buat_sebar_promosi_remove_product_2_button");
                break;
            default:
                Assert.fail("only remove first or second product only!");
                break;
        }
        validateRemoveProductPopup();
        tapElement("buat_sebar_promosi_yes_confirmation_button");
    }

    public void validateSelectedProductOnIndex(String selectedProductIndex) {
        switch (selectedProductIndex) {
            case "first":
                validateNotDisplayed(constructLocator("buat_sebar_promosi_specific_selected_product_name_text", PROMData.getCrmProductSelectionName().get(0)));
                break;
            case "second":
                validateNotDisplayed(constructLocator("buat_sebar_promosi_specific_selected_product_name_text", PROMData.getCrmProductSelectionName().get(1)));
                break;
            default:
                Assert.fail("only remove first or second product only!");
                break;
        }
        assertEquals(PROMData.getTotalSelectedCRMProducts() - 1, getElements("buat_sebar_promosi_selected_product_name_text").size());
    }

    public void validateCRMProductMaxSelection() {
        getSelectedProductAttributes();
        if (PROMData.getTotalSelectedCRMProducts() > 3) {
            for (int i = 0; i < PROMData.getCrmProductSelectedName().size(); i++) {
                validateValue().notEquals(PROMData.getCrmProductSelectionName().get(3), PROMData.getCrmProductSelectedName().get(i));
            }
        }
    }
    // End region Managemen Pesan Promosi

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
