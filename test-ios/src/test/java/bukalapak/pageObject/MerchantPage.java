package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.MerchantData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static bukalapak.TestInstrument.dotenv;

/**
 * Created by Arka on 11/04/19.
 */
public class MerchantPage extends BasePage {

    public MerchantPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnMerchantPage() {
        skipFlashDealOnboarding();
        waitForVisibilityOf("merchant_page_chat_button", 60);
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void tapOnProductHighlightMerchantPage() {
        tapElement("merchant_page_barang_unggulan_card");
    }

    public void isProductHighlightShow() {
        swipeUpToElement("merchant_page_barang_unggulan_card");
        waitForVisibilityOf("merchant_page_barang_unggulan_card", 20);
    }

    public void userOnMerchantPageWithDeeplink(String deeplinkEnvVar) throws Exception {
        String deeplink = dotenv.get(deeplinkEnvVar);
        if (deeplink == null) {
            throw new Exception(deeplinkEnvVar + " is not set!");
        }

        openDeepLink(deeplink);
        userOnMerchantPage();
    }

    public void isModalPelapakTerbaikShow() {
        waitForVisibilityOf("merchant_page_modal_terbaik_title", 60);
        verifyElementExist("merchant_page_modal_terbaik_description");
        verifyElementExist("merchant_page_modal_terbaik_title");
    }

    public void isModalPelapakPremiumShow() {
        waitForVisibilityOf("merchant_page_modal_premium_title", 60);
        verifyElementExist("merchant_page_modal_premium_description");
        verifyElementExist("merchant_page_modal_premium_title");
    }

    public void isModalEtalaseShow() {
        waitForVisibilityOf("merchant_page_modal_etalse_barang", 60);
        verifyElementExist("merchant_page_modal_etalse_barang");
    }

    public void isModalFeedbackShow() {
        waitForVisibilityOf("merchant_page_modal_feedback_title", 60);
        verifyElementExist("merchant_page_modal_feedback_description");
        verifyElementExist("merchant_page_modal_feedback_title");
    }

    public void isOnMerchantFeedback() {
        verifyElementExist(("merchant_page_feedback"));
    }

    public void isOnMerchantPremium() {
        waitForVisibilityOf("merchant_page_button_langganan_premium", 60);
        verifyElementExist(("merchant_page_button_langganan_premium"));
    }

    public void isModalProcessTimeShow() {
        verifyElementExist("merchant_page_title_modal_waktu_proses");
        verifyElementExist("merchant_page_desc_modal_waktu_proses");
    }

    public void isModalDetailInfoLapakShow() {
        waitForVisibilityOf("merchant_page_title_modal_info_lapak", 60);
    }

    public void isPopUpWaktuProsesShow() {
        waitForVisibilityOf("merchant_page_waktu_process_popup_title", 60);
    }

    public void verifyBadgeLapakTerbaikOnProducts() {
        swipeDownToElement("merchant_page_product_detail_badge_lapak_terbaik");
        verifyElementExist("merchant_page_product_detail_badge_lapak_terbaik");
    }

    public void verifyBadgeBekasOnProducts() {
        waitForVisibilityOf("merchant_page_badge_bekas", 60);
        verifyElementExist("merchant_page_badge_bekas");
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void verifyBadgeSuperSellerOnProducts() {
        swipeUpToElement("merchant_page_product_detail_badge_super_seller", 15);
        verifyElementExist("merchant_page_product_detail_badge_super_seller");
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void verifyBadgeBukaMall() {
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
        waitForVisibilityOf("merchant_page_product_detail_badge_bukamall", 15);
    }

    public void isOnFilterMerchantPage() {
        waitForVisibilityOf("button_terapkan_filter_page", 60);
        verifyElementExist("button_terapkan_filter_page");
    }

    public void verifyVoucherLapakSection(Boolean isOn) {
        waitForVisibilityOf("merchant_page_image_banner", 60);
        if (isOn) {
            waitForVisibilityOf("merchant_page_icon_voucher_lapak_salin");
        } else {
            verifyElementNotExist("merchant_page_icon_voucher_lapak_salin");
        }
    }

    public void tapVoucherLapakUntukmu() {
        waitForVisibilityOf("merchant_page_icon_voucher_lapak_untukmu", 20);
        tapElement("merchant_page_icon_voucher_lapak_untukmu");
    }

    public void verifySliderVoucherDetail() {
        waitForVisibilityOf("merchant_page_icon_voucher_lapak_salin", 25);
    }

    public void verifyVoucherLapakCode(int index, String voucherCode) {
        assertEquals(voucherCode, getTextFromElement("merchant_page_voucher_lapak_code", index));
    }

    public void verifyVoucherLapakEtalase(int index, String etalaseVoucher) {
        assertEquals(etalaseVoucher, getTextFromElement("merchant_page_voucher_lapak_etalase", index));
    }

    public void verifyVoucherLapakDiskon(String isShown, int index, String voucherDiskon) {
        if (isShown.contains("not") && getElements("merchant_page_voucher_lapak_diskon").size() == 0) {
            verifyElementNotExist("merchant_page_voucher_lapak_diskon");
            return;
        }

        if (getElements("merchant_page_voucher_lapak_diskon").size() - 1 != 0) {
            for (int i = 0; i < index; i++) {
                swipeLeft(0.8, 0.1, 0.4);
                i++;
            }
        }
        String discountText = getTextFromElement("merchant_page_voucher_lapak_diskon", index);
        if (!isShown.contains("not")) {
            assertEquals(voucherDiskon, discountText);
        } else {
            assertFalse(voucherDiskon.equals(discountText));
        }
    }

    public void verifyVoucherLapakMinTrx(int index, String minTrx) {
        assertEquals(minTrx, getTextFromElement("merchant_page_voucher_lapak_min_trx", index));
    }

    public void closeSliderVoucherLapak() {
        tapElement("merchant_page_voucher_lapak_close_slider");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifySearchFieldMerchant() {
        waitForVisibilityOf("merchant_page_search_field", 10);
        verifyElementExist("merchant_page_search_field");
    }

    public void searchProductMerchant(String keywords) {
        MerchantData.setProductName(keywords);
        typeAndEnterValue("merchant_page_search_field", keywords);
    }

    public void verifyOnProductListingMerchant() {
        String keyword = MerchantData.getProductName();
        validateDisplayed(constructLocator("merchant_page_prod_name", keyword));
    }

    public void verifyIsOnMerchantPage() {
        skipFlashDealOnboarding();
        swipeDownToElement("merchant_page_seller_name");
        verifySearchFieldMerchant();
    }

    public void verifyButtonOnMerchantPage() {
        if (!isElementExist("atur_barang_unggulan", 5)) {
            swipeUpToElement("atur_barang_unggulan");
        } else {
            verifyElementExist("atur_barang_unggulan");
        }
    }

    public void verifyOnAturUnggulanPage() {
        verifyElementExist("atur_barang_unggulan");
        verifyElementExist("barang_unggulan_terlaris");
        verifyElementExist("barang_unggulan_terbaru");
        verifyElementExist("barang_unggulan_pilihan");
    }

    private List<String> getLabelFromELements(String element) {
        List<IOSElement> elements = getElements(element);
        List<String> labels = new ArrayList<>();
        for (IOSElement element1 : elements) {
            labels.add(element1.getText());
        }
        return labels;
    }

    public int getCountForButton(String buttonName) {

        int i;
        int count = 0;
        int index = 0;

        int max = getElements("status_barang_unggulan", 10).size();
        LogUtil.info("JUMLAHNYAA" + max);

        for (i = 0; i < max; i++) {
            String result1 = getElementValue("status_barang_unggulan", index);
            LogUtil.info("SAMPE SINIIIII " + result1);
            if (result1.equals(buttonName)) {
                count++;
            }
            index++;
        }
        return count;
    }

    public void getProductUnggulanName(String locator) {
        if (!isElementExist(locator, 5)) {
            nativeSwipeUp();
        } else {
            MerchantData.setproductUnggulanName(getLabelFromELements(locator));
            LogUtil.info(MerchantData.getProductUnggulanName().toString());
        }
    }

    public int getProductUnggulanCount() {

        int max = 0;
        if (!isElementExist("product_barang_unggulan", 5)) {
            nativeSwipeUp();
        } else {
            max = getElements("product_barang_unggulan", 10).size();
            LogUtil.info("Jumlah Product Sebanyak " + max);
        }
        return max;
    }

    private void clickChecklistAtIndex(int index) {
        List<IOSElement> elements = getElements("check_box_barang_unggulan");
        LogUtil.info("HASILNYAAAAAAA ELEMENTS " + elements);
        elements.get(index).click();
    }

    public void checkProductUnggulanCount() {
        int productSum = getProductUnggulanCount();
        int i;
        int minimum = 3;

        if (productSum == 0) {
            tapElement("ubah_barang_unggulan");
            for (i = 1; i <= minimum; i++) {
                clickChecklistAtIndex(i);
                tapElement("lanjut_revamp_button");
            }
        } else if (productSum == 1) {
            tapElement("ubah_barang_unggulan");
            for (i = 2; i <= minimum; i++) {
                clickChecklistAtIndex(i);
                tapElement("lanjut_revamp_button");
            }
        } else if (productSum == 2) {
            tapElement("ubah_barang_unggulan");
            for (i = 3; i <= minimum; i++) {
                clickChecklistAtIndex(i);
                tapElement("lanjut_revamp_button");
            }
        } else {
            LogUtil.info("Count of Product accordance with minimum product");
        }
    }

    private String getBarangUnggulanTitle(String state) {
        return getText(constructLocator("barang_unggulan_title", state));
    }

    public void selectBarangUnggulanSection(String state) { 
        MerchantData.setNotActiveBarangUnggulan(getCountForButton(state.toUpperCase()));
        MerchantData.setBarangUnggulanName(getBarangUnggulanTitle(state.toUpperCase()));
        String stateLoc = (state.toUpperCase().equals("DITAMPILKAN")) ? "ditampilkan_barang_unggulan" : "belum_tampil_barang_unggulan";
        tapElement(stateLoc);
        waitForVisibilityOf("ubah_barang_unggulan");
        validateValue().equals(MerchantData.getBarangUnggulanName(), getText("barang_unggulan_name"));
    }

    public void validateDraggableBarangUnggulanModal() {
        waitForVisibilityOf("barang_unggulan_name");
        validateValue().equals(MerchantData.getBarangUnggulanName(), getText("barang_unggulan_name"));
        validateExist("tampilkan_kelompok_ini");
    }

    public void validateUpdateState(String state) {
        String stateLoc = (state.toUpperCase().equals("DITAMPILKAN")) ? "barang_unggulan_ditampilkan" : "barang_unggulan_disembunyikan";
        validateExist(constructLocator(stateLoc, MerchantData.getBarangUnggulanName()), 3);
    }

    public void verifyProductsBarangUnggulan() {
        if (MerchantData.getBarangUnggulanName().equals("Update Judul Pilihan Pelapak")) {
            LogUtil.info("There is no product");
        } else {
            getProductUnggulanName("product_barang_unggulan");
            checkProductUnggulanCount();
        }
    }

    public void verifyCountOfNotActiveButton() {
        MerchantData.setNotActiveBarangUnggulan2(getCountForButton("BELUM TAMPIL"));

        int buttonNotActive1 = MerchantData.getNotActiveBarangUnggulan();
        int buttonNotActive2 = MerchantData.getNotActiveBarangUnggulan2();

        LogUtil.info("Count of Belum Tampil labels before are" + buttonNotActive1);
        LogUtil.info("Count of Belum Tampil labels now are" + buttonNotActive2);

        assertTrue(buttonNotActive1 > buttonNotActive2, "Failed to changes Barang Unggulan status");
    }

    public void hardRefresh(int refreshCount) {
        for (int i = 1; i <= refreshCount; i++) {
            waitFor(2);
            nativeSwipeDown();
            waitFor(2);
        }
    }

    public void backToMerchantPage() {
        while (!isElementExist("merchant_page_search_field", 5)) {
            tapElement("base_back_button");
        }
        hardRefresh(6);
        verifyIsOnMerchantPage();
    }

    public void verifyDitampilkanLabel() {
        MerchantData.setActiveBarangUnggulan(getCountForButton("DITAMPILKAN"));
        LogUtil.info("Count of Ditampilkan labels are" + MerchantData.getActiveBarangUnggulan());
        tapElement("ditampilkan_barang_unggulan");
    }

    public void verifyBarangUnggulanActive() {
        waitForVisibilityOf("barang_unggulan_name");
        MerchantData.setBarangUnggulanName(getElementValue("barang_unggulan_name"));
        LogUtil.info("Barang Unggulan that active is" + MerchantData.getBarangUnggulanName());
    }

    public void verifyCountOfActiveButton() {
        MerchantData.setActiveBarangUnggulan2(getCountForButton("DITAMPILKAN"));

        int buttonActive1 = MerchantData.getActiveBarangUnggulan();
        int buttonActive2 = MerchantData.getActiveBarangUnggulan2();

        LogUtil.info("Count of Ditampilkan labels before are" + buttonActive1);
        LogUtil.info("Count of Ditampilkan labels now are" + buttonActive2);

        assertTrue(buttonActive1 > buttonActive2, "Failed to changes Barang Unggulan status");
    }

    public boolean isBarangUnggulanExist() {
        int i;
        int maxscroll = 5;
        String barangUnggulanName = MerchantData.getBarangUnggulanName();
        LogUtil.info("Barang Unggulan Name is " + barangUnggulanName);

        try {
            for (i = 0; i < maxscroll; i++) {
                if (!isElementExist((constructLocator("barang_unggulan_name_merchant", MerchantData.getBarangUnggulanName())), 5)) {
                    nativeSwipeUp();
                } else {
                    break;
                }
            }
            WebDriverWait wait = new WebDriverWait(this.iosDriver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(this.getLocator(constructLocator("barang_unggulan_name_merchant", barangUnggulanName))));
            return true;
        } catch (Exception var4) {
            LogUtil.error("Element with locator : " + barangUnggulanName + " is not present!");
            return false;
        }
    }

    public void verifyBarangUnggulan(String barangUnggulanType) {
        switch (barangUnggulanType) {
            case "see Barang Unggulan":
                Assert.assertTrue("Barang Unggulan Active Not Found", isBarangUnggulanExist());
                break;
            case "not see Barang Unggulan":
                Assert.assertTrue("Barang Unggulan Active was Found", isBarangUnggulanExist() == false);
                break;
            default:
                break;
        }
    }

    public void verifyProductMerchant() {
        nativeSwipeUp();
        verifyElementExist("product_price_merchant");
    }

    public void verifyProductName() {
        MerchantData.setProductName(getElementValue("product_name_listing"));
        LogUtil.info(MerchantData.getProductName());
    }

    public void clickProductOnMerchantPage() {
        tapElement("product_name_listing");
    }

    public void checkProductMerchantName() {
        String productName = MerchantData.getProductName();

        verifyElementExist(constructLocator("product_name_detail", productName));
    }

    public void verifyWidgetsOnMerchantPage(String widgetsType) {
        switch (widgetsType) {
            case "banners":
                verifyElementExist("banners_on_merchant_page");
                break;
            case "subcategories":
                verifyElementExist("subcategories_content");
                break;
            case "our collection":
                MerchantData.setCollectionName(getElementValue("collection_name"));
                verifyElementExist("collection_content");
                break;
            case "best seller":
                verifyElementExist("best_seller_content");
                break;
            default:
                break;

        }
    }

    public void verifyContentOnProductListingMerchant() {
        verifyElementExist("merchant_page_etalase_menu");
        verifyElementExist("merchant_page_filter_menu");
        verifyElementExist("product_price_merchant");
    }

    public void verifyCollectionPage() {
        String collectionName = MerchantData.getCollectionName();
        verifyElementExist(constructLocator("verify_name", collectionName));
        verifyElementExist("product_price_merchant");
    }

    public void verifyBestSellerPage() {
        waitFor(5);
        verifyElementExist("best_seller_title");
        verifyElementExist("product_price_merchant");
        tapElement("alchemy_back_button");
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void verifyOnSellerPage() {
        skipFlashDealOnboarding();
        verifySearchFieldMerchant();
        verifyElementExist("merchant_page_chat_button");
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void clickOnChatButtonMerchant() {
        verifyElementExist("merchant_page_chat_button");
        tapElement("merchant_page_chat_button");
    }

    public void verifyStatistikTitle() {
        swipeUpToElement("statistik_title");
        verifyElementExist("statistik_title");
    }

    public void checkWaktuKirim() {
        verifyElementExist("waktu_kirim");
        LogUtil.info("Waktu Kirim Merchant " + getElementValue("waktu_kirim"));
    }

    public void countOfPesananDiterima() {
        verifyElementExist("jumlah_pesanan_diterima");
        LogUtil.info("Jumlah Pesanan Diterima " + getElementValue("jumlah_pesanan_diterima"));
    }

    public void countOfPelanggan() {
        verifyElementExist("jumlah_pelanggan");
        LogUtil.info("Jumlah Pelanggan " + getElementValue("jumlah_pelanggan"));
    }

    public void checkFeedbackTitle() {
        swipeUpToElement("merchant_page_feedback_icon");
        verifyElementExist("merchant_page_feedback_icon");
    }

    public void clickTampilkanFeedback() {
        nativeSwipeUp();
        tapElement("button_lihat_semua_feedback");
    }

    public void verifyFeedbackPage() {
        verifyElementExist("feedback_pelapak_title");
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void verifyCatatanPelapak() {
        swipeUpToElement("catatan_lapak_title");
        LogUtil.info("Catatan Lapak is " + getElementValue("catatan_lapak"));
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void verifyPopUpWaktuKirimInfo() {
        verifyElementExist("waktu_proses_pop_up_title");
        tapElement("popup_cart_page_close_button");
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void verifyButtonMerchantPage() {
        verifyElementExist("merchant_page_chat_button");
        verifyElementExist("ikuti_button");
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void validateCopyEtalaseMenu(String etalase) {
        waitForVisibilityOf("etalase_text_view", 60);
        validateValue().equals(getText("etalase_text_view"), etalase);
    }

    public void validateCopyUrutkanMenu(String urutkan) {
        waitForVisibilityOf("urutkan_text_view", 60);
        validateValue().equals(getText("urutkan_text_view"), urutkan);
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void validateCopyOnSearchBox(String keyword) {
        validateValue().equals(getText("keyword_search_box_merchant").contains(keyword), true);
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void validateCopyOnSearchBoxValue(String keyword) {
        validateValue().equals(getText("keyword_search_box_merchant_value").contains(keyword), true);
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }


    public void clickBackButton() {
        tapElement("merchant_page_back_button");
    }

    public void tapThreeDotsButton() {
        // it is often that the three dots button is not tapped
        try {
            validateDisplayed("merchant_page_three_dots");
            tapElement("merchant_page_three_dots");
            verifyElementExist("merchant_page_option_menu");
        } catch (AssertionError e) {
            IOSElement element = getElement("merchant_page_three_dots", 5);
            int x = element.getCoordinates().onPage().getX();
            int y = element.getCoordinates().onPage().getY();
            int height = element.getSize().getHeight();
            int width = element.getSize().getWidth();

            tapElement(x + (width / 2), y + (height / 2));
            verifyElementExist("merchant_page_option_menu");
        }
    }

    public void tapLaporkan() {
        validateDisplayed("merchant_page_report_menu");
        tapElement("merchant_page_report_menu");
    }

    public void skipFlashDealOnboarding() {
        if (isElementVisible("merchant_page_flash_deal_onboarding_button", 5)) {
            tapElement("merchant_page_flash_deal_onboarding_button");
        }
    }

    public void tapOnBarangTab() {
        tapElement("tab_barang_merchant_page");
    }

    public void verifyOnTabBarangPage() {
        validateElementVisible("merchant_page_etalase_menu");
        validateElementVisible("merchant_page_filter_menu");
        validateElementVisible("barang_unggulan_terlaris");
        validateElementVisible("product_price_merchant");
    }

    public void tapOnEtalaseMerchatPage() {
        tapElement("merchant_page_etalase_menu");
    }

    public void verifyEtalasePopUp() {
        validateElementVisible("merchant_page_etalase_title");
        validateElementVisible("etalase_list");
    }

    public void tapEtalaseList() {
        MerchantData.setEtalaseName(getTextFromElement("etalase_list"));
        tapElement("etalase_list");
    }

    public void verifyEtalaseActived() {
        String etalaseName = MerchantData.getEtalaseName();

        waitForVisibilityOf("etalase_name_product_listing", 5);
        validateValue().equals(getText("etalase_name_product_listing").contains(etalaseName), true);
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void verifyUrutkanMerchantPage(String urutkanType) {
        verifyElementExist(constructLocator("urutkan_tab_name", urutkanType));
        MerchantData.setUrutkanName(getTextFromElement(constructLocator("urutkan_tab_name", urutkanType)));
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void tapOnUrutkanTabMerchantPage() {
        String urutkanName = MerchantData.getUrutkanName();

        tapElement(constructLocator("urutkan_tab_name", urutkanName));
    }

    public void tabOnUrutkanNameMerchantPage(String urutkanName) {
        tapElement(constructLocator("urutkan_name_list", urutkanName));
    }

    public void tapOnProfilTabMerchantPage() {
        tapElement("profile_tab_label");
    }

    public void verifyReputasiInfoMerchant() {
        verifyElementExist("reputasi_title");
        LogUtil.info("The reputation is " + getTextFromElement("reputasi_type"));
    }

    public void tapFlashDealTab(){
        validateExist("FLASH_DEAL_TAB");
        tapElement("FLASH_DEAL_TAB");
    };

    public void validateFDNonParticipatedUser(){
        waitForVisibilityOf("FLASH_DEAL_NON_PARTICIPATED_ICON");
        validateExist("FLASH_DEAL_NON_PARTICIPATED_ICON");
        validateExist("FLASH_DEAL_NON_PARTICIPATED_TITLE");
        validateExist("FLASH_DEAL_NON_PARTICIPATED_SUB_TITLE");
    };

    public void tapOnSubscribeButton() {
        if (isElementExist("merchant_page_subscribe_button")) {
            MerchantData.setSubscribeStatus(getTextFromElement("merchant_page_subscribe_button"));
            tapElement("merchant_page_subscribe_button");
        } else {
            MerchantData.setSubscribeStatus(getTextFromElement("merchant_page_unsubscribe_button"));
            tapElement("merchant_page_unsubscribe_button");
        }
    }

    public void verifySubscribeButton() {
        String subscribeStatus = MerchantData.getSubscribeStatus();

        if (subscribeStatus.equals("Ikuti")) {
            verifyElementExist("merchant_page_unsubscribe_button");
        } else {
            verifyElementExist("merchant_page_subscribe_button");
        }
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void tapOnFilter() {
        tapElement("merchant_page_filter_menu");
    }

    public void tapKondisiBarangFilter() {
        waitForVisibilityOf("option_kondisi_barang_filter_page");
        tapElement("option_kondisi_barang_filter_page");
    }

    public void tapBarangBekas() {
        waitForVisibilityOf("option_barang_bekas_filter_page");
        tapElement("option_barang_bekas_filter_page");
    }

    public void tapOnFilterTerapkanButton() {
        tapElement("button_terapkan_filter_page");
    }

    public void validateMerchantFDEntryPoint(){
        validateExist("FLASH_DEAL_EMPTY_STATE_ICON", 10);
        validateExist("FLASH_DEAL_EMPTY_STATE_TEXT", 10);
        validateExist("FLASH_DEAL_MULAI_JUALAN_BUTTON", 10);
    }

    public void goToFDMerchantPage() {
        tapElement("FLASH_DEAL_MULAI_JUALAN_BUTTON");
    }

    public void validateFDOnboardingPageDisplayed() {
        changeContext().toWebview();
        validateExist("MWEB_MERCHANT_FLASH_DEAL_PAGE_TITLE", 30);
        validateExist("MWEB_MERCHANT_FLASH_DEAL_PAGE_BANNER", 30);
        changeContext().toNative();
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void tapOnFilterTabBarang() {
        tapElement("merchant_page_filter_menu");
    }

    public void verifyBarangBekasFilterActive() {
        waitForVisibilityOf("barang_bekas_active_sign", 5);
        verifyElementExist("barang_bekas_active_sign");
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void verifyProductAfterFilterApplied() {
        if (!isElementExist("barang_tidak_ditemukan_title")) {
            verifyElementExist("product_price_merchant");
            verifyElementExist("product_name_merchant_listing");
        }
    }

    public void tapOnKondisiBarangFilter() {
        tapElement("kondisi_barang_filter");
    }

    public void validateNonSsFDEntryPoint() {
        validateExist("FLASH_DEAL_EMPTY_STATE_ICON", 10);
        validateExist("FLASH_DEAL_EMPTY_STATE_TEXT", 10);
        validateExist("flash_deal_aktifkan_super_seller", 10);
    }

    public void tapAktifkanSuperSeller() {
        tapElement("flash_deal_aktifkan_super_seller");
    }

    public void tapFirstProduct() {
        swipeUpToElement("merchant_page_first_product", 10);
        tapElement("merchant_page_first_product", 10);
    }

    public void validateNoOngkirInProductCard() {
        swipeUpToElement("product_tag_no_ongkir");
        verifyElementExist("product_tag_no_ongkir");
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void validatePopUpAgeRestriction() {
        waitForVisibilityOf("merchant_page_age_restriction_pop_up_title", 30);
        validateExist("merchant_page_age_restriction_pop_up_title");
        validateExist("merchant_page_age_restriction_pop_up_ya_18_button");
        validateExist("merchant_page_age_restriction_pop_up_tidak_button");
    }

    public void tapYaButton() {
        waitForVisibilityOf("merchant_page_age_restriction_pop_up_ya_18_button");
        tapElement("merchant_page_age_restriction_pop_up_ya_18_button");
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }

    public void tapTidakButton() {
        waitForVisibilityOf("merchant_page_age_restriction_pop_up_tidak_button");
        tapElement("merchant_page_age_restriction_pop_up_tidak_button");
        HelperData.setLastActionPage(new MerchantPage(iosDriver));
    }
}
