package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.*;
import bukalapak.utils.Constants;
import com.bukalapak.salad.util.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import junit.framework.TestCase;
import org.junit.Assert;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;

/**
 * Created by sekarayukarindra on 25/09/18.
 */
public class HomePage extends BasePage {

    private Dimension screenSize;

    public HomePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    /**
     * Select navigation tab in Home page
     *
     * @param tabName is name of navigation tab
     */
    public void selectNavigationTab(String tabName) {
        switch (tabName.toLowerCase()) {
            case "home":
                tapElement("home_blhome_tab");
                break;
            case "favorit":
                tapElement("home_favorit_tab");
                break;
            case "bukamall":
                tapElement("bukamall_tab");
                break;
            case "transaksi":
                tapElement("home_transaksi_tab");
                break;
            case "akun":
                tapElement("home_akun_tab");
                break;
            default:
                Assert.fail(tabName + " isn't the tab name");
        }
    }

    private void danaOnBoarding() {
        if (UserData.isLoggedIn() && !HomeData.getDanaOnBoardingState() &&
                isElementVisible("onboarding_kenalan_dong_button", 5)) {
            tapElement("onboarding_kenalan_dong_button");
            tapElement("onboarding_nanti_saja_button");
            HomeData.setDanaOnBoardingState(true);
        }
    }

    public void dismissCampaignPopUp() {
        // periodically check the pop up for each second (max 10 seconds)
        for (int maxWait = 10; maxWait > 0; maxWait--) {
            if (isElementExist("braze_inapp_notification_pop_up")) {
                tapElement("braze_inapp_notification_pop_up");
                break;
            } else if (isElementExist("braze_canvas_inapp_pop_up_close")) {
                tapElement("braze_canvas_inapp_pop_up_close");
                break;
            }
            delay(1000);
        }
    }

    public void allowAccessATTPopUp() {
        try {
            if (isElementExist("home_att_pop_up", 5)) {
                waitForVisibilityOf("home_continue_att_pop_up", 3);
                tapElement("home_continue_att_pop_up");
            }
        } catch (Exception e) {
            LogUtil.info("ATT pop-up only display on 14.5 OS version");
        }
    }

    private void dismissDanaPopup() {
        if (isElementVisible("onboarding_sheet_draggable_modal", 5) &&
                isElementVisible("onboarding_binding_dana")) {
            if (isElementVisible("hubungkan_dana_popup_close_alt")) {
                tapElement("hubungkan_dana_popup_close_alt");
            } else {
                tapElement("onboarding_sheet_draggable_modal_close");
            }
        }
    }

    public void allowNotificationPopup() {
        if (isElementExist("home_allow_button", 3)) {
            tapElement("home_allow_button");
        }
    }

    /**
     * this method is used to dismiss rating apps
     */
    private void ratingAppsSheet() {
        if (!UserData.isFirstOpenApp() && isElementVisible("ASK_RATING_APPS_CLOSE_BUTTON", 3)) {
            tapElement("ASK_RATING_APPS_CLOSE_BUTTON");
            verifyElementNotExist("ASK_RATING_SHEET_HEADER_TEXT");
        }
    }

    private void dismissSearchOnboarding() {
        if (isElementVisible("onboarding_ok_button", 3)) {
            tapElement("onboarding_ok_button");
            verifyElementNotExist("onboarding_ok_button");
        }
        HelperData.setLastActionPage(new AskRatingAppsPage(iosDriver));
    }

    private void skipOnBoarding() {
        waitFor(5);
        danaOnBoarding();
        dismissDanaPopup();
        ratingAppsSheet();
        dismissSearchOnboarding();
    }

    private void checkFirstAppLoad() {
        if (UserData.isFirstOpenApp()) {
            allowAccessATTPopUp(); // allow ATT pop up
            allowPopup();
            dismissCampaignPopUp(); // dismiss braze pop up
            closeDebugButton(); // close doraemon kit

            UserData.setFirstOpenApp(false);
        }
    }

    private void checkAfterLoggedIn() {
        if (UserData.isLoggedIn() && !HomeData.getOnBoardingState()) {
            dismissCampaignPopUp();
            skipOnBoarding();

            HomeData.setOnBoardingState(true);
        }
    }

    private void checkFirstAppLoadWithoutDismisCapaignPopUp() {
        if (UserData.isFirstOpenApp()) {
            closeDebugButton(); // close doraemon kit

            UserData.setFirstOpenApp(false);
        }
    }

    private void checkAfterLoggedInWithoutDismisCapaignPopUp() {
        if (UserData.isLoggedIn() && !HomeData.getOnBoardingState()) {
            skipOnBoarding();

            HomeData.setOnBoardingState(true);
        }
    }

    private static String errorLastActionPageMessage(String className) {
        return String.format("Unable to perform goToHome! " +
                "Your latest visited page is %s, please check method goToHomePage on that page.", className);
    }

    public void isOnHomePage() {
        // handle while context is in WebView
        if (!iosDriver.getContext().contains("NATIVE_APP")) changeContext().toNative();

        try {
            HelperData.goToHomePage();
        } catch (Exception e) {
            String className = HelperData.getLastActionPage().getClass().getSimpleName();
            throw new RuntimeException(errorLastActionPageMessage(className));
        }

        checkFirstAppLoad();
        checkAfterLoggedIn();

        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void isOnHomePageWithoutCloseTheInApp() {
        // handle while context is in WebView
        if (!iosDriver.getContext().contains("NATIVE_APP")) changeContext().toNative();

        try {
            HelperData.goToHomePage();
        } catch (Exception e) {
            String className = HelperData.getLastActionPage().getClass().getSimpleName();
            throw new RuntimeException(errorLastActionPageMessage(className));
        }

        checkFirstAppLoadWithoutDismisCapaignPopUp();
        checkAfterLoggedInWithoutDismisCapaignPopUp();

        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    private void closeDebugButton() {
        if (isElementVisible("home_debug_button", 3) &&
                getElementAttributeValue("home_debug_button", "visible").equals("true")) {
            tapElement("home_debug_button", 3);
            tapElement("home_close_debug_button", 5);
            autoAcceptAlert("home_close_debug_confirmation_ok_button");
            delay(2000); // need delay animation after close the pop up alert
        }
    }

    public void goToHomePage() {
        LogUtil.info("On Home page..");
    }

    public void searchByKeyword(String keyword) {
        String name = "name_" + keyword;
        swipeDownToElement("home_lainnya_widget");
        tapElement("home_search_text_field");
        typeAndEnterValueWithTimeOut("search_text_field", keyword);
        tapElement(name);
        try {
            waitFor(10);
            okOnboarding();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tapBackButton();
        tapElement("home_search_text_field");
        typeAndEnterValueWithTimeOut("search_text_field", keyword);
        tapElement(name);
    }

    public void clickTransaksiTab() {
        for (int i = 0; i < 5; i++) {
            try {
                selectNavigationTab("Transaksi");
                break;
            } catch (Exception e) {
                tapBackButton();
            }
        }
        HelperData.setLastActionPage(new TransactionListPage(iosDriver));
    }

    public void clickHomePage() {
        selectNavigationTab("Home");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void searchPelapakInHomePage(String name) {
        swipeToDirection(Direction.DOWN);
        swipeToDirection(Direction.DOWN);
        tapElement("home_search_text_field");
        String tmpName = name.toUpperCase();
        try {
            typeAndEnterValueWithTimeOut("search_text_field", TestInstrument.dotenv.get(tmpName + "_SURENAME"));
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error("Type of " + tmpName + " is not listed in data.env file.");
        }
        HelperData.setLastActionPage(new SearchPage(iosDriver));
    }

    /* This method is used to navigate to home page from other page
     **
     */
    public void backToHome() {
        dismissCampaignPopUp();
        danaOnBoarding();
        ratingAppsSheet();

        if (isElementVisible("home_selamat_datang_text", 3)) {
            tapElement("home_selamat_datang_text");
        } else {
            LogUtil.info("Welcoming Text");
        }

        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapUserSaldo() {
        tapElement("home_saldo_button");
    }

    public void checkIconShorcutsOnHome() {
        verifyElementExist("homepage_shorcuts_section_buka_mart");
        verifyElementExist("homepage_shorcuts_section_gratis_ongkir");
        verifyElementExist("homepage_shorcuts_section_barang_favorit");
    }

    public void checkFlashBannersOnHome() {
        waitForVisibilityOf("lihat_semua_flash_banner_revamp", 10);
        assertTrue(isElementVisible("lihat_semua_flash_banner_revamp"), "Flash Banners is not Exist");
    }

    public void checkHomePageMenusOnHome() {
        assertTrue(isElementVisible("home_menu_favorit"), "Home Page Menus is Exist");
    }

    public void checkSecondaryBannerOnHome() {
        swipeToDirection(Direction.DOWN);
        assertTrue(isElementVisible("secondary_banner"), "Secondary Banner is Exist");
    }

    public void checkCategoriesOnHome() {
        scrollToElement("category-navbar-section");
        scrollToElement("lihat_semua_category_navbar");
        scrollToElement("category_section_first_category");
        verifyElementExist("category_section_first_category");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void checkCategoryTabOnHome() {
        verifyElementExist("category_tab_home");
    }

    public void checkTodaysDealOnHome() {
        assertTrue(isElementVisible("promo_hari_ini"), "Todays Deal is not Exist");
        nativeSwipeUp();
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void checkNewItemsOnHome() {
        swipeDownToElement("produk_baru_bukalapak");
        assertTrue(isElementVisible("produk_baru_bukalapak"), "New Items is Exist");
    }

    public void checkHotlistsOnHome() {
        swipeDownToElement("trending_products");
        assertTrue(isElementVisible("trending_products"), "Hotlist is Exist");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void checkHotlistOnHome() {
        swipeDownToElement("trending_products");
        assertTrue(isElementVisible("trending_products"), "Hotlist is Exist");
    }

    public void tapHomepageShortcut(String shortcutName) {
        String value = "";
        switch (shortcutName) {
            case "Flash Deal":
                value += "homepage_shorcuts_section_flashdeal";
                break;
            case "Gratis Ongkir":
                value += "homepage_shorcuts_section_gratis_ongkir";
                break;
            case "Barang Favorit":
                value += "homepage_shorcuts_section_barang_favorit";
                break;
            case "BukaMart":
                value += "homepage_shorcuts_section_buka_mart";
                break;
            default:
                LogUtil.info("There are no such shortcut");
        }
        swipeUpToElement(value);
        tapElement(value);
    }

    public void homepageRedirectedTo(String shortcutName) {
        String value = "";
        switch (shortcutName) {
            case "Flash Deal":
                value += "shorcut_section_flashdeal_result";
                verifyElementExist(value);
                break;
            case "Gratis Ongkir":
                value += "shorcut_section_gratis_ongkir_result";
                verifyElementExist(value);
                break;
            case "Barang Favorit":
                value += "shorcut_section_barang_favorit_result";
                verifyElementExist(value);
                HelperData.setLastActionPage(new HomePage(iosDriver));
                break;
            case "Trending Products":
                value += "trending_products_result";
                verifyElementExist(value);
                break;
            case "Hotlist Product":
                value += "trending_products_list";
                verifyElementExist(value);
                break;
            case "Flash Banner":
                value += "flash_banner_result";
                verifyElementExist(value);
                HelperData.setLastActionPage(new PromoPage(iosDriver));
                break;
            case "Categories":
                value += "categories_page_navbar";
                verifyElementExist(value);
                break;
            case "Today Deals":
                value += "flash_banner_result";
                verifyElementExist(value);
                break;
            case "BukaMart":
                value += "buka_mart_result";
                verifyElementExist(value);
                break;
            default:
                LogUtil.info("There are no such page");
        }
    }

    public void checkListHotlistOnHome() {
        assertTrue(isElementVisible("home_jas_hujan"), "Page List Hotlist is Exist");
    }

    public void clickHotlistFromList() {
        tapElement("select_trending_products");
    }

    public void clickLihatSemua(String lihatSemuaType) {
        switch (lihatSemuaType) {
            case "Hotlist":
                swipeToLocator("lihat_semua_hotlist");
                tapElement("lihat_semua_hotlist");
                break;
            case "Flash Banner":
                swipeToLocator("lihat_semua_flash_banner_revamp");
                tapElement("lihat_semua_flash_banner_revamp");
                break;
            case "Categories":
                tapElement("lihat_semua_category_navbar");
                break;
            case "Today Deals":
                swipeToLocator("lihat_semua_today_deals");
                tapElement("lihat_semua_today_deals");
                break;
            case "Rekomendasi":
                swipeToLocator("home_lihat_semua_reco_link");
                tapElement("home_lihat_semua_reco_link");
                break;
            case "Promo Untukmu":
                tapElement("home_lihat_semua_promo_untukmu");
                break;
            case "Thematic Campaign":
                tapElement("home_lihat_semua_thematic_campaign");
                break;
            default:
                break;
        }
    }

    public void fiturPelapakMenu(String submenu) {
        switch (submenu) {
            case "BukaPengiriman":
                swipeDownToElement("home_buka_pengiriman_text");
                tapElement("home_buka_pengiriman_text");
                break;
            case "Super Seller":
                swipeUpToElement("home_super_seller_widget", 15);
                tapElement("home_super_seller_widget");
                break;
            default:
                LogUtil.info("the " + submenu + " is not available in fitur pelapak!");
                break;
        }
    }

    public void verifyFiturPelapakMenu(String icon) {
        switch (icon) {
            case "BukaGrosir":
                // butuh swipe 2x karena some how swipeUpToElement stuck di bagian flashdeal
                // juga butuh lebih banyak handle karena di homepage banyak section yang menggunakan
                // multiple scroll
                nativeSwipeUpToElement("home_buka_grosir_icon");
                waitForVisibilityOf("home_buka_grosir_icon", 10);
                break;
            default:
                Assert.fail("Invalid Parameter!");
                break;
        }
    }

    public void clickRecoMenu(String type) {
        switch (type) {
            case "product":
                swipeDownToElement("home_reco_subproduct");
                break;
            case "category":
                swipeDownToElement("home_reco_subcategory");
                break;
            default:
                LogUtil.info("Reco type " + type + " is not available!");
                break;
        }
        tapElement("home_reco_menu");
    }

    public void chooseRecoMenu(String menu) {
        switch (menu) {
            case "discard":
                tapElement("home_reco_dont_show");
                break;
            case "feedback":
                tapElement("home_reco_show_feedback");
                break;
            default:
                LogUtil.info("Menu " + menu + " is not available!");
                break;
        }
    }

    public void verifyDiscardReco() {
        tapElement("home_reco_cancel_discard");
    }

    public void cancelDiscard() {
        swipeDownToElement("home_reco_cancel_discard");
        tapElement("home_reco_cancel_discard");
    }

    public void sendRecoFeedback(String feedback) {
        if (feedback.equals("complete")) {
            tapElement("home_feedback_irrelevant");
        }
    }

    public void clickCartIcon() {
        tapElement("cart_icon", 10);
    }

    public void navigateToInspirationPage() {
        swipeDownToElement("home_inspirasi_tab");
        tapElement("lihat_semua_inspirasi_text");
        HelperData.setLastActionPage(new InspirationPage(iosDriver));
    }

    public void navigateToSuperSecretNinja() {
        String secretMenuPassword = TestInstrument.dotenv.get("SECRET_MENU_PASSWORD");

        selectNavigationTab("Akun");

        if (isElementVisible("ttgaplikasi_secret_pass_field")) {
            tapElement("base_back_button");
        }

        if (isElementVisible("menulain_tentang_aplikasi_tab")) {
            tapElement("base_back_button");
        }

        if (isElementVisible("akun_onboarding_menu_lain_button")) {
            tapElement("akun_onboarding_menu_lain_button");
        } else {
            swipeUpToElement("tentang_kami_text");
            tapElement("tentang_kami_text");
        }

        tapElement("menulain_tentang_aplikasi_tab");

        if (!isElementVisible("ttgaplikasi_secret_pass_field")) {
            tapMultipleOnElement("ttgaplikasi_secret_tab", 7);
        }

        if (isElementVisible("ttgaplikasi_secret_pass_field")) {
            typeAndEnterValueWithTimeOut("ttgaplikasi_secret_pass_field", secretMenuPassword);
        }

        HelperData.setLastActionPage(new SuperSecretNinjaPage(iosDriver));
    }

    public void validateMailConfirmationAppear() {
        assertTrue(isElementVisible("account_verify"));
        assertTrue(isElementVisible("email_verify"));
    }

    public void validatePhoneConfirmationAppear() {
        assertTrue(isElementVisible("account_verify"));
        assertTrue(isElementVisible("phone_verify"));
    }

    public void checkRecommendationPanelAppear() {
        swipeDownToElement("home_rekomendasi_kamu_text", 20);
    }

    public void checkRecommendationPanelNotAppear() {
        verifyElementNotExist("home_reco_panel");
    }

    public void navigateToCategoryFashionWanita() {
        swipeDownToElement("lihat_semua_categories");
        tapElement("lihat_semua_categories");
        tapElement("category_fashion_wanita_text");
        tapElement("category_semua_fashion_wanita_text");
        doPullRefresh(1);
        HelperData.setLastActionPage(new CategoryFashionWanitaPage(iosDriver));
    }

    public void validateHighlightSection() {
        swipeDownToElement("highlight_banner", 10);
        verifyElementExist("highlight_title");
        verifyElementExist("highlight_banner");
        verifyElementExist("highlight_seller_product");
    }

    public void navigateToHighlightPage() {
        swipeDownToElement("highlight_banner", 10);
        tapElement("highlight_banner");
        waitForVisibilityOf("highlight_selected_product", 15);
        waitForVisibilityOf("highlight_seller_header", 15);
    }

    public void tapBarangFavoritMenu() {
        if (isElementVisible("homepage_shorcuts_section_barang_favorit")) {
            tapElement("homepage_shorcuts_section_barang_favorit");
        } else {
            selectNavigationTab("Akun");
            swipeDownToElement("homepage_shorcut_barang_favorit_id");
            tapElement("homepage_shorcuts_section_barang_favorit");
        }
    }

    public void clickCategoryFromHome(String category) {
        switch (category) {
            case "Category Manager":
                tapElement("category_manager");
                break;
            case "Non Category Manager":
                tapElement("non_category_manager");
                break;
            default:
                break;
        }
    }

    public void checkScanNavbarIsExist() {
        assertTrue(isElementVisible("scan_qr"), "Icon Scan Bar Code Tidak Ditemukan");
    }

    public void checkNotifNavbarIsExist() {
        assertTrue(isElementVisible("notif_icon"), "Icon Notif Code Tidak Ditemukan");
    }

    public void checkChatNavbarIsExist() {
        assertTrue(isElementVisible("chat_icon"), "Icon Chat Tidak Ditemukan");
    }

    public void checkCartNavbarIsExist() {
        assertTrue(isElementVisible("cart_icon"), "Icon Cart Tidak Ditemukan");
    }

    public void checkNavbar() {
        checkScanNavbarIsExist();
        checkNotifNavbarIsExist();
        checkChatNavbarIsExist();
        checkCartNavbarIsExist();
    }

    public void inputProductName(String productName) {
        swipeUpToElement("home_search_product_field");
        tapElement("home_search_product_field");
        if (isElementVisible("omni_search_by_cam_onb_nanti_deh_text")) {
            tapElement("omni_search_by_cam_onb_nanti_deh_text");
        }
        typeAndEnterValue("home_search_product_field", productName);
    }

    public void clickWidgetFiturPelapak(String widget) {
        scrollToElement(widget);
        tapElement(widget);
    }

    public void verifyDANA() {
        swipeDownToElement("homepage_dana_balance_text");
        waitForVisibilityOf("homepage_dana_balance_text", 20);
        int danaBalance = Integer.parseInt(getTextFromElement("homepage_dana_balance_text").replaceAll("[^0-9]", ""));
        UserData.setOwnedDANA(danaBalance);
        DANAData.setDanaBalance(danaBalance);
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyCredits() {
        waitForVisibilityOf("homepage_credits_balance_text", 20);
        int creditsBalance = Integer.parseInt(getTextFromElement("homepage_credits_balance_text").replaceAll("[^0-9]", ""));
        UserData.setOwnedCredits(creditsBalance);
    }

    public void clickPromotedInstant() {
        scrollToElement("home_promoted_push_instant_img");
        MobileElement element = getElement("home_promoted_push_instant_img");
        int axis_x = element.getLocation().x;
        int axis_y = element.getLocation().y;
        int width = element.getSize().width;
        int height = element.getSize().height;
        tapElement(axis_x + (int) (width * 0.8), axis_y + (int) (height * 0.5));
    }

    public void clickTagihanPromotedInstant() {
        scrollToElement("home_promoted_push_instant_img");
        MobileElement element = getElement("home_promoted_push_instant_img");
        int axis_x = element.getLocation().x;
        int axis_y = element.getLocation().y;
        int width = element.getSize().width;
        int height = element.getSize().height;
        tapElement(axis_x + (int) (width * 0.3), axis_y + (int) (height * 0.7));
    }

    public void tapSerbuSeruShowAll() {
        scrollToElement("home_lihat_semua_serbu_seru_text");
        waitForVisibilityOf("home_lihat_semua_serbu_seru_text");
        tapElement("home_lihat_semua_serbu_seru_text");
        if (isElementVisible("onboardingBp_saya_mengerti_button",5)) {
            tapElement("onboardingBp_saya_mengerti_button");
        }
    }

    public void inputTopUpAmount(String amount) {
        waitForElementClickable("topup_dana_vp_amount_field", 10);
        typeAndEnterValue("topup_dana_vp_amount_field", amount);
        waitForVisibilityOf("topup_dana_vp_lanjut_bayar_button", 5);
        tapElement("topup_dana_vp_lanjut_bayar_button");
    }

    public void verifyVoucherkuFinancial() {
        waitForVisibilityOf("home_voucherku_tab", 50);
        swipeToLocator("home_voucherku_tab");
        verifyElementExist("home_voucherku_tab");
    }

    public void verifyPromoPage() {
        if (HomeData.getVoucherOnHomePageState() == true) {
            waitForVisibilityOf("home_voucherku_tab", 50);
            verifyElementExist("home_voucherku_tab");
            verifyElementExist("button_pakai_voucher");
        } else {
            verifyElementExist("promo_page");
            verifyElementExist("home_voucherku_tab");
            verifyElementNotExist("button_pakai_voucher");
        }
        tapElement("alchemy_back_button");
        HelperData.setLastActionPage(new PromoPage(iosDriver));
    }

    public boolean isUserHaveVoucher() {
        if (!isElementVisible("mau_lihat_voucher")){
            return true;
        } else {
            LogUtil.error("Element is not present!");
            return false;
        }
    }

    public void clickVoucherOnHome() {
        HomeData.setVoucherOnHomePageState(isUserHaveVoucher());

        if (isElementVisible("voucher_home_page")) {
            tapElement("voucher_home_page");
        } else if (isElementVisible("home_voucherku_copy_entry")) {
            tapElement("home_voucherku_copy_entry");
        } else if (isElementVisible("mau_lihat_voucher")) {
            tapElement("mau_lihat_voucher");
        } else {
            tapElement("home_voucherku_tab");
        }
    }

    public void clickSmartcard() {
        waitForElementClickable("smart_card_verification", 10);
        tapElement("smart_card_verification");
    }

    public void verifySmartCardVerification() {
        verifyElementExist("smart_card_verification");
    }

    public void verifyPopUpVerification() {
        verifyElementExist("pop_up_verification");
        verifyElementExist("button_verification_email");
        verifyElementExist("button_verification_phone");
    }

    public void verifyPopUpVerificationPhone() {
        verifyElementExist("pop_up_verification_phone");
        verifyElementExist("label_pop_up_verification_phone");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyVerificationEmailPage() {
        verifyElementExist("verification_email_page_title");
        verifyElementExist("verification_email_message");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyVerificationPhonePage() {
        waitForVisibilityOf("verification_phone_page_title", 15);
        verifyElementExist("verification_phone_page_title");
        verifyElementExist("phone_number_field");
        verifyElementExist("password_field");
    }

    public void verifyPopularBrandProducts() {
        if (!isElementVisible("popular_brand_product_home")) {
            scrollToElement("popular_brand_product_home");
        }
    }

    public void clickProductPopular() {
        tapElement("product_name_popular");
    }

    public void tapTopUpDANA() {
        waitForVisibilityOf("home_e_voucher_and_tiket_label", 5);
        tapElement("home_e_voucher_and_tiket_label");
        waitForVisibilityOf("home_top_up_dana_label", 5);
        tapElement("home_top_up_dana_label");
    }

    public void scrollToPopularProductSection() {
        while (!isElementVisible("lihat_semua_koleksi_popular")) {
            nativeSwipeUp();
        }
        verifyElementExist("lihat_semua_koleksi_popular");
    }

    public void skipHomePage() {
        // really need delay because some of weird behaviour when use deeplink and login
        delay(2500);
        skipOnBoarding();
    }

    public void tapDanaHomepageIcon() {
        tapElement("home_hubungkan_dana_button");
    }

    private void scrollToElement(String locator) {
        // Need to be scrolled manual like this, due to some elements in Home page cant be scrolled.
        int scroll = 0;
        while (!isElementVisible(locator) && scroll < 20) {
            swipeUp(0.8, 0.3);
            scroll++;
        }
    }

    public void scrollToTodayDeals() {
        scrollToElement("home_reco_title");
        swipeDownToElement("promo_hari_ini");
    }

    public void verifyPopularProductSection() {
        scrollToElement("popular_section");
        verifyElementDisplayed("popular_section");
    }

    public void clickPopularProduct() {
        scrollToElement("popular_product");
        tapElement("popular_product");
    }

    public void verifyHasVoucherkuCard() {
        waitForVisibilityOf("home_voucherku_tab", 50);
        swipeToLocator("home_voucherku_tab");
        verifyElementExist("home_voucherku_tab");
        validateValue().equalsTrue(getTextFromElement("home_has_voucherku_tab").contains("voucher nih"));
    }

    public void verifyEmptyVoucherkuCard() {
        waitForVisibilityOf("home_voucherku_tab", 50);
        swipeToLocator("home_voucherku_tab");
        verifyElementExist("home_empty_voucherku_tab");
        validateValue().equalsTrue(getTextFromElement("home_empty_voucherku_tab").contains("Mau Lihat?"));
    }

    public void validateRecoDOH(boolean isDisplayed) {
        swipeUpToElement("reco_section_title", 20);
        if (isDisplayed) {
            verifyElementDisplayed("reco_section_title");
        } else {
            validateNotDisplayed("reco_section_title");
        }
    }

    public void validateRecoState(String state) {
        if (state.isEmpty()) {
            swipeUpToElement("reco_product", 4);
            verifyElementDisplayed("reco_product");
        } else {
            swipeUpToElement("reco_tab_riwayat", 5);
            tapElement("reco_tab_riwayat");
            swipeUpToElement("reco_empty_state", 5);
            validateExist("reco_empty_state");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateRecoFloatingBtn() {
        waitForVisibilityOf("reco_floating_btn", 40);
        verifyElementDisplayed("reco_floating_btn");
    }

    public void clickRecoFloatingBtn() {
        tapElement("reco_floating_btn");
    }

    public void clickRecoTabOptions(String options) {
        verifyElementDisplayed(constructLocator("reco_tab_options", options));
        tapElement(constructLocator("reco_tab_options", options));
    }

    public void clickRecoProduct() {
        swipeUpToElement("reco_product", 10);
        tapElement("reco_product");
    }

    public void validateRecoProduct() {
        waitForVisibilityOf("reco_product");
        verifyElementDisplayed("reco_product");
    }

    public void validateRecoPrime(String flag) {
        if (flag.equals("login")) {
            swipeUpToElement("reco_prime_section_title_login", 3);
            verifyElementDisplayed("reco_prime_section_title_login");
        } else if (flag.equals("non login")) {
            swipeUpToElement("reco_prime_section_title_non_login", 3);
            verifyElementDisplayed("reco_prime_section_title_non_login");
        } else {
            Assert.fail("Flag " + flag + " is undefined");
        }
    }

    public void validateRecoPrimeProductInformation() {
        swipeUpToElement("reco_prime_number_of_sold_product");
        verifyElementDisplayed("reco_prime_product_name");
        validateDisplayed("reco_prime_product_prime");
        validateDisplayed("reco_prime_product_rating");
        validateDisplayed("reco_prime_number_of_sold_product");
        validateDisplayed("reco_prime_icon_super_seller");
        validateDisplayed("reco_prime_icon_gratis_ongkir");
    }

    public void clickRecoPrimeProduct() {
        tapElement("reco_prime_product_name");
    }

    public void goToFaq() {
        selectNavigationTab("Akun");
        verifyElementExist("akun_onboarding_menu_lain_button");
        tapElement("akun_onboarding_menu_lain_button");
        verifyElementExist("menulain_informasi_tab");
        tapElement("menulain_informasi_tab");
        tapElement("menulain_faq_button");
    }

    public void scrollToBrandSection(String flag) {
        DiscoveryData.setBrandSectionType(flag.toLowerCase());

        if (flag.toLowerCase().equals("popular brand")) {
            scrollToElement("popular_brand_section");
        }
        else if (flag.toLowerCase().equals("brand entry")) {
            scrollToElement("brand_section_revamp_store_names");
        }
        else LogUtil.error("Homepage brand section didn't show up!");
    }

    public void validateHomepageBrandSection() {
        if (DiscoveryData.getBrandSectionType().equals("popular brand")) {
            verifyElementDisplayed("brand_icon_popular_brand");
        }
        else if (DiscoveryData.getBrandSectionType().equals("brand entry")) {
            verifyElementDisplayed("brand_section_revamp_lihat_semua");
            verifyElementDisplayed("brand_section_revamp_banner");
            verifyElementDisplayed("brand_section_revamp_store_imgs");
            verifyElementDisplayed("brand_section_revamp_product_imgs");
        }
        else LogUtil.error("Homepage brand section didn't show up!");

        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapOnHomepageBrandSection(String itemName) {
        switch (itemName.toLowerCase()) {
            case "brand image":
                tapBrandInBrandSection();
                break;
            case "product":
                verifyPopularBrandProducts();
                break;
            case "banner":
                tapElements("brand_section_revamp_banner", 0);
                break;
            case "lihat semua":
                tapElement("brand_section_revamp_lihat_semua");
                break;
            default:
                LogUtil.error("Please enter the right value!");
        }
    }

    public void tapBrandInBrandSection() {
        String flag = DiscoveryData.getBrandSectionType();
        if (flag.equals("popular brand")) {
            tapElement("brand_icon_popular_brand");
        }
        else if (flag.equals("brand entry")) {
            tapElements("brand_section_revamp_store_imgs", 0);
        }
        else LogUtil.error("Homepage Brand Section is not appeared!");
    }

    public void swipeLeftOnPupularBrand() {
        nativeSwipeLeftToElement("popular_brand_product");
    }

    public void tapLihatSemuaPotongHarga() {
        scrollToElement("lihat_semua_potong_harga_text");
        tapElement("lihat_semua_potong_harga_text");
    }

    public void verifyChangePasswordPopUp() {
        waitForVisibilityOf("home_change_password_pop_up_label");
        verifyElementExist("home_change_password_pop_up_label");
    }

    public void clickTransactionPage() {
        selectNavigationTab("Transaksi");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyRecoSection() {
        if (!PNRData.isOnRecoDoh()) {
            scrollToElement("doh_title_section");
            verifyElementExist("doh_title_section");
        } else {
            verifyElementExist("doh_product_card");
        }
        PNRData.setIsOnRecoDOH(true);
    }

    public void validationTabBuatkamu() {
        swipeUpToElement("doh_tab_buat_kamu");
        verifyElementExist("doh_tab_buat_kamu");
    }

    public void validationDohTab(String tab) {
        switch (tab) {
            case "buat kamu":
                if (!isElementVisible("empty_image_buat_kamu")) {
                    scrollToElement("empty_image_buat_kamu");
                }
                nativeSwipeUpToElement("empty_title_buat_kamu");
                waitForVisibilityOf("empty_section_buatkamu");
                verifyElementDisplayed("empty_section_buatkamu");
                break;
            case "bukamart":
                verifyElementExist("list_product");
                break;
            default:
                LogUtil.info("tab " + tab + " is not available!");
                break;
        }
    }

    public void clickTabBukamart() {
        if (!isElementVisible("doh_tab_bukamart")) {
            swipeLeft(0.27, 0.08, 0.4);
        }
        tapElement("doh_tab_bukamart");
    }

    public void validateTopFiveProductActiveEvent() {
        scrollToElement("lihat_semua_potong_harga_text");
        int i = 0;
        swipeUpToElement("home_potong_harga_potong_button");
        while (i <= 4) {
            validateValue().contains(getText("home_potong_harga_all_product_name"), PotongHargaData.getListProductName().toString());
            swipeLeft(0.65, 0.1, 0.5);
            i++;
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapProductCardOnPotongHargaSection() {
        scrollToElement("home_potong_harga_potong_button");
        verifyElementDisplayed("home_potong_harga_first_product_name");
        PotongHargaData.setProductNamePotongHarga(getText("home_potong_harga_first_product_name"));
        tapElement("home_potong_harga_first_product_name");
    }

    public void scrollToRecoPrime(String condition) {
        doPullRefresh(2);
        if (condition.equals("login")) {
            swipeUpToElement("reco_title_login");
        } else if (condition.equals("logout")) {
            waitFor(10);
            swipeUpToElement("reco_title_logout");
        } else {
            Assert.fail("Flag " + condition + " is undefined");
        }
    }

    public void validateSectionReco(String condition) {
        swipeUpToElement("section_reco");
        verifyElementExist("section_reco");
        if (condition.equals("login")) {
            verifyElementExist("reco_title_login");
        } else if (condition.equals("logout")) {
            verifyElementExist("reco_title_logout");
        } else {
            Assert.fail("Flag " + condition + " is undefined");
        }
    }

    public void validateProductReco() {
        swipeUpToElement("reco_prime_icon_super_seller");
        verifyElementExist("reco_prime_product_name");
        verifyElementExist("reco_prime_product_rating");
        swipeUpToElement("reco_prime_icon_gratis_ongkir");
        verifyElementExist("reco_prime_number_of_sold_product");
        verifyElementExist("reco_prime_icon_super_seller");
    }

    public void swipeProductReco(String swipe) {
        switch (swipe) {
            case "left":
                swipeLeft(0.97, 0.08, 0.4);
                break;
            case "right":
                swipeRight(0.08, 0.97, 0.4);
            default:
                LogUtil.info("swipe " + swipe + " is not available!");
                break;
        }
    }

    public void tapMulaiPotongHarga() {
        scrollToElement("home_potong_harga_potong_button");
        tapElement("home_potong_harga_potong_button");
    }

    public void openProductSameSeller(String state) {
        String url;
        if (state.equalsIgnoreCase("has")) {
            url = TestInstrument.dotenv.get("PRODUCT_SAME_SELLER");
        } else if (state.equalsIgnoreCase("has_bmss")) {
            url = TestInstrument.dotenv.get("PRODUCT_SAME_SELLER_BMSS");
        } else if (state.equalsIgnoreCase("has_tray_atc")) {
            url = TestInstrument.dotenv.get("PRODUCT_SAME_SELLER_ON_CART");
        } else if (state.equalsIgnoreCase("has_rating")){
            url = TestInstrument.dotenv.get("PRODUCT_HAS_RATING");
        } else {
            url = TestInstrument.dotenv.get("PRODUCT_SAME_SELLER_NOT_EXIST_ON_CART");
        }
        assert url != null;
        TestCase.assertNotNull("parameter url is null", url);
        openDeepLink(url);
    }

    public void tapHubungkanDanaOnFinancialSection() {
        waitForVisibilityOf("home_hubungkan_dana_text", 30);
        tapElement("home_hubungkan_dana_text");
    }

    public void validateFlashDealSection() {
        swipeUpToElement("FLASH_DEAL_ICON");
        validateExist("FLASH_DEAL_ICON");
        validateExist("FLASH_DEAL_COUNTDOWN_TIMER");
        validateExist("FLASH_DEAL_LIHAT_SEMUA_TEXT");
        validateExist("FLASH_DEAL_HOMEPAGE_PRODUCT_IMAGE");
    }

    public void tapFlashDealProduct() {
        swipeUpToElement("FLASH_DEAL_HOMEPAGE_PRODUCT_IMAGE");
        validateExist("FLASH_DEAL_HOMEPAGE_PRODUCT_IMAGE");
        tapElement("FLASH_DEAL_HOMEPAGE_PRODUCT_IMAGE");
    }

    public void tapFlashDealLihatSemua() {
        tapElement("FLASH_DEAL_LIHAT_SEMUA_TEXT");
    }

    public void tapFlashDealLihatSemuaCard() {
        swipeUpToElement("HOMEPAGE_BANNER");
        nativeSwipeLeftToElement("FLASH_DEAL_HOMEPAGE_PRODUCT_IMAGE");
        nativeSwipeLeftToElement("FLASH_DEAL_HOMEPAGE_PRODUCT_IMAGE");
        validateExist("FLASH_DEAL_LIHAT_SEMUA_CARD", 10);
        tapElement("FLASH_DEAL_LIHAT_SEMUA_CARD");
    }

    public void tapDANABalance() {
        waitForVisibilityOf("homepage_dana_and_credits_balance_text");
        tapElement("homepage_dana_and_credits_balance_text");
    }

    public void scrollToCampaignBanner() {
        try {
            scrollToElement("campaign_banner_right");
        } catch (Exception e){
            LogUtil.info("Could not found Element!");
        }
    }

    public void verifyGapuraCampaignBannerSection() {
        if (isElementExist("campaign_banner_right")) {
            verifyElementExist("campaign_banner_right");
            verifyElementExist("campaign_banner_left");
        } else {
            LogUtil.info("Gapura Campaign Banner doesn't exist");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void scrollToHorizontalRecoHome() {
        waitFor(10);
        scrollToElement("horizontal_reco_section");
        nativeSwipeUp();
    }

    public void verifyHorizontalRecoHome() {
        waitForVisibilityOf("horizontal_reco_title", 10);
        verifyElementExist("horizontal_reco_title");
    }

    public void swipeToOpsiLainnyaButton() {
        int loop;

        for (loop = 0; loop <= 7; loop++) {
            swipeLeftAtSpecifiedLocator("horizontal_reco_section");
        }
    }

    public void verifyOpsiLainnyaButton() {
        waitForVisibilityOf("opsi_lainnya_button", 10);
        verifyElementExist("opsi_lainnya_button");
    }

    public void tapOnOpsiLainnyaButton() {
        tapElement("opsi_lainnya_button");
    }

    public void doPullRefreshHome(int refreshCount) {
        for (int i = 1; i <= refreshCount; i++) {
            waitFor(2);
            nativeSwipeDown();
            waitFor(2);
        }
    }

    public void clickProductHorizontalReco() {
        waitForVisibilityOf("horizontal_product_card", 5);
        tapElement("horizontal_product_card");
    }

    public void scrollToPromoUntukmuSection() {
        scrollToElement("home_promo_button");
    }

    public void verifyPromoUntukmuSection() {
        validateDisplayed("home_promo_untukmu_title");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void scrollToThematicCampaignSection() {
        scrollToElement("home_thematic_campaign");
        scrollToElement("home_price_product_thematic");
    }

    public void verifyLihatSemuaThematicCampaign() {
        verifyElementExist("home_lihat_semua_thematic_campaign");
    }

    public void verifyMicrositePage() {
        waitForVisibilityOf("microsite_horizontal_scroll_bar", 10);
        verifyElementExist("microsite_horizontal_scroll_bar");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyThematicCampaignProducts() {
        verifyElementExist("home_product_thematic_campaign");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickProductThematicCampaign() {
        waitForVisibilityOf("home_product_thematic_campaign", 5);
        tapElement("home_product_thematic_campaign");
    }

    public void scrollToPersonalizedInspiration() {
        scrollToElement("home_personalized_inspiration");
        scrollToElement("home_promo_card_inspiration");
    }

    public void verifyIdeLainnyaButton() {
        verifyElementExist("home_inspiration_title");
        verifyElementExist("home_Ide_lainnya_inspiration");
        verifyElementExist("home_inspiration_card");
    }

    public void clickIdeLainnyaButton() {
        waitForVisibilityOf("home_Ide_lainnya_inspiration", 5);
        tapElement("home_Ide_lainnya_inspiration");
    }

    public void verifyProductImagesInspiration() {
        verifyElementExist("home_inspiration_card");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickProductCardInspiration() {
        waitForVisibilityOf("home_inspiration_card", 5);
        tapElement("home_inspiration_card");
    }

    public void clickPromoCardInspiration() {
        waitForVisibilityOf("home_promo_card_inspiration", 5);
        tapElement("home_promo_card_inspiration");
    }

    public void navigateTabDohSection() {
        scrollToElement("doh_title_section");
    }

    public void verifyTabBukamart() {
        swipeUp();
        verifyElementExist("list_product");
    }

    public void swipeTabDoH(String tab) {
        switch (tab) {
            case "Buat Kamu":
            case "Riwayat":
            case "Bukamart":
            case "HP & Smartphone":
            case "AC":
            case "Aksesoris Motor":
                int swipeCount = 0;
                while (swipeCount < 4 && !isElementVisible(constructLocator("reco_tab_options", tab))) {
                    swipeLeftAtSpecifiedLocator("dob_tab");
                    swipeCount++;
                }
                tapElement(constructLocator("reco_tab_options", tab));
                break;
            default:
                Assert.fail(tab + "Tab is undefined");
                break;
        }
    }

    public void skipDanaOnboarding() {
        if (isElementVisible("onboarding_kenalan_dong_button", 30)) {
            tapElement("onboarding_kenalan_dong_button");
            tapElement("onboarding_nanti_saja_button");
            HomeData.setDanaOnBoardingState(true);
        }
    }

    //voucher claim home promo and home reco
    public void scrollToHomeRecoSection() {
        scrollToElement("home_voucher_reco_section");
    }

    public void validateVoucherHomePromo(String voucherName) {
        verifyElementExist("home_voucher_promo_section");
        verifyElementExist(constructLocator("home_voucher_claim_promo_txt", voucherName));
        tapElement(constructLocator("home_voucher_claim_promo_txt", voucherName), 5);
    }

    public void claimVoucherHomePromo(String voucherName) {
        if (isElementExist(constructLocator("home_voucher_claim_ambil_btn", voucherName), 5)) {
            tapElement(constructLocator("home_voucher_claim_ambil_btn", voucherName), 5);
        } else {
            copyVoucherHomePromo(voucherName);
        }
    }

    public void copyVoucherHomePromo(String voucherName) {
        verifyElementExist(constructLocator("home_voucher_claim_salin_btn", voucherName));
        tapElement(constructLocator("home_voucher_claim_salin_btn", voucherName), 5);
    }

    public void validateVoucherHomeReco(String voucherName) {
        swipeUpToElement("home_voucher_claim_reco_date");
        waitForVisibilityOf(constructLocator("home_voucher_claim_reco_txt", voucherName), 10);
        verifyElementExist(constructLocator("home_voucher_claim_reco_txt", voucherName));
        if (isElementExist(constructLocator("home_voucher_reco_lihat_detail_btn"))) {
            tapElement(constructLocator("home_voucher_reco_lihat_detail_btn", voucherName), 5);
        }
    }

    public void claimVoucherHomeReco(String voucherName) {
        verifyElementExist(constructLocator("home_voucher_reco_ambil_btn", voucherName));
        tapElement(constructLocator("home_voucher_reco_ambil_btn", voucherName), 5);
    }

    public void validateVoucherHomeRecoBtn(String buttonName) {
        verifyElementExist(constructLocator("home_voucher_reco_button_type", buttonName));
    }

    public void scrollToOpsiLainnyaButton() {
        scrollToElement("opsi_lainnya_button");
    }

    public void goToTopHomePage() {
        if (!isElementVisible("lihat_semua_flash_banner_revamp")) {
            tapElement("home_blhome_tab");
        }
    }

    public void validateBukaLiveSection() {
        assertTrue(
                swipeUpToElement("bukalive_card_stream_info", 2, 20),
                "BukaLive stream info doesn't exist"
        );
    }

    public void tapBukaLiveSection(String actionName) {
        switch (actionName.toLowerCase()) {
            case "see all":
                tapElement("bukalive_lihat_semua");
                break;
            case "see more":
                tapElement("bukalive_card_stream_see_more");
                break;
            case "stream card":
                tapElements("bukalive_card_stream_list", 0);
                break;
            default:
                LogUtil.error("Wrong input!");
                break;
        }
    }

    @Override
    public void swipeLeftAtSpecifiedLocator(String elementContainer, String elementLocator, int swipeCount) {
        for (int i = 0; i < swipeCount; i++) {
            // handle swipe count if has max value
            maxSwipeCountError(elementLocator, i, swipeCount);

            if (!isElementVisible(elementLocator)) {
                swipe().leftAtSpecifiedLocator(elementContainer, Constants.DEFAULT_SWIPE_DURATION);

                selectNavigationTab("Akun");
                selectNavigationTab("Home");
            } else break;
        }
    }

    public void swipeToSeeMoreCardButton() {
        swipeLeftAtSpecifiedLocator(
                "bukalive_horizontal_section",
                "bukalive_card_stream_see_more",
                5
        );
    }

    public void clickBukaMall() {
        selectNavigationTab("bukamall");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyBrazePN() {
        waitFor(5);
        screenSize = iosDriver.manage().window().getSize();
        showNotifications();
        String pnText = iosDriver.findElementByXPath("//*[contains(@label, 'BUKALAPAK')]").getText();
        assertTextContains("Test Push Notification ART", pnText);
    }

    public void clickBrazePN() {
        iosDriver.findElementByXPath("//*[contains(@label, 'BUKALAPAK')]").click();
    }

    private void showNotifications() {
        manageNotifications(true);
    }

    private void manageNotifications(Boolean show) {
        int yMargin = 5;
        int xMid = screenSize.width / 2;
        PointOption top = PointOption.point(xMid, yMargin);
        PointOption bottom = PointOption.point(xMid, screenSize.height - yMargin);

        TouchAction action = new TouchAction(iosDriver);
        if (show) {
            action.press(top);
        } else {
            action.press(bottom);
        }
        action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
        if (show) {
            action.moveTo(bottom);
        } else {
            action.moveTo(top);
        }
        action.perform();
    }

    public void tapOnFirstCategory() {
        try {
            tapElement("category_section_first_category");
        } catch (Exception e) {
            nativeSwipeUp();
            tapElement("category_section_first_category");
        }
    }

    public void tapOnPromoSection(String actionName) {
        switch (actionName.toLowerCase()) {
            case "tab":
                tapElements("home_promo_tab_title", 2);
                waitForVisibilityOf("home_promo_banner_img", 15);
                break;
            case "banner":
                tapElements("home_promo_banner_img", 0);
                break;
            case "load more":
                waitForVisibilityOf("home_promo_load_more_card");
                tapElement("home_promo_load_more_card");
                break;
            case "redeem button":
                DiscoveryData.setPromoButtonName(getTextFromElement("home_promo_button", 0));
                tapElements("home_promo_button", 0);
            default:
                LogUtil.error("Wrong input!");
                break;
        }
    }

    public void validatePromoSectionBanner() {
        verifyElementDisplayed("home_promo_banner_img");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void swipeLeftOnPromoSectionBanner(int maxSwipe) {
        int swipeCount = 0;

        while (swipeCount < maxSwipe && !isElementVisible("home_promo_load_more_card")) {
            swipeLeftAtSpecifiedLocator("home_promo_section");
            swipeCount++;
        }
    }

    public void validatePromoLoadMoreCard() {
        verifyElementDisplayed("home_promo_load_more_card");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyRedirectedFromPromoSection(){
        validateNotExist("home_promo_untukmu_title",3);
    }

    public void validatePromoButton() {
        String buttonName = DiscoveryData.getPromoButtonName().toLowerCase();

        if (buttonName.equals("lihat")) {
            verifyRedirectedFromPromoSection();
        }
        else if (buttonName.equals("salin")) {
            validateSnackbarMsg("Kode Voucher berhasil disalin.", "true");
        }
    }

    public void validateSnackbarMsg(String msg, String exist) {
        String snackBarLocator = "label_" + msg + "";
        Boolean snackbar = exist == null;

        if (snackbar) verifyElementExist(snackBarLocator, 5, "Snackbar didn't appear!");
        else verifyElementNotExist(snackBarLocator);
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickCampaignPopUp() {
        try {
            waitForVisibilityOf("home_campaign_pop_up", 20);
            tapElement("home_campaign_pop_up_button");
            GMTData.setBrazePopupState(true);
        } catch (Exception e) {
            GMTData.setBrazePopupState(false);
        }
    }

    public void verifyNotInHomePage() {
        waitFor(1);
        verifyElementNotExist("dope_see_all_button");
    }

    public void tapCategoryTabbing() {
        DiscoveryData.setHomeKeywordSuggestion(getTextFromElement("home_search_product_button").replace("Cari ", "").toLowerCase());
        tapElement("category_tab_home");
    }

    public void verifyDopePageOnMview() {
        waitForVisibilityOf("mview_dope", 10);
        verifyElementExist("mview_dope");
        verifyElementExist("mview_vertical_scroll_bar");
        verifyElementExist("mview_horizontal_scroll_bar");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapFavIconRecoProduct() {
        swipeUpToElement("doh_favorite_icon", 10);
        tapElement("doh_favorite_icon", 10);
    }

    public void validateFavIcon(String color) {
        String currentColorIcon;
        try {
            currentColorIcon = getCurrentColorIcon();
        } catch (Exception e) {
            currentColorIcon = getCurrentColorIcon();
        }

        switch (color) {
            case "red" :
                validateValue().equals(currentColorIcon, "true");
                break;
            case "white" :
                validateValue().equals(currentColorIcon, "false");
                break;
            default:
                Assert.fail("Invalid Color icon");
                break;
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    private String getCurrentColorIcon() {
        waitForVisibilityOf("doh_favorite_icon", 10);
        return getElementAttributeValue("doh_favorite_icon", "value");
    }

    public void tapRecoNewCampaignTab() {
        if (!isElementExist("doh_product_name", 5)) swipeUpToElement("doh_product_name");
        PNRData.setProductName(getText("doh_product_name"));
        tapElement("doh_new_campaign_tab");
    }

    public void validateRecoNewCampaignTabSelected() {
        waitForVisibilityOf("doh_product_name", 15);
        validateValue().notEquals(PNRData.getProductName(),
                getText("doh_product_name"), "New Campaign is not selected");
        validateNotExist(constructLocator("vp_general_dynamic_label",
                PNRData.getProductName()), 5, "New Campaign is not selected");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateRecoNewCampaignProduct(String productCardComponent) {
        switch (productCardComponent) {
            case "product name":
                validateExist("doh_product_name", 3);
                break;
            case "product price":
                validateExist("doh_product_price", 3);
                break;
            case "product rating icon":
                validateExist("doh_product_rating_icon", 3);
                break;
            case "product rating":
                validateExist("doh_product_rating_label", 3);
                break;
            case "total sold product":
                validateExist("doh_product_total_sold", 3);
                break;
            case "BMSS icon":
                validateExist("doh_product_user_badge", 3);
                break;
            default:
                Assert.fail("Invalid component");
                break;
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyNoOngkirPromoPage(String state) {
        if (state.equalsIgnoreCase("has no")) {
            waitForVisibilityOf("home_voucherku_tab", 50);
            verifyElementExist("home_voucherku_tab");
            verifyElementNotExist("home_voucherku_copy_entry");
        } else {
            waitForVisibilityOf("home_voucherku_tab", 50);
            isElementVisible("home_voucherku_copy_entry");
            verifyElementExist("home_voucherku_copy_entry");
        }
    }

    public void tapTabTopic(String tabOrder) {
        if (!isElementExist("doh_product_name", 5)) swipeUpToElement("doh_product_name");
        PNRData.setProductName(getText("doh_product_name"));
        switch (tabOrder) {
            case "first":
                tapElement("doh_topic_1");
                break;
            case "second":
                tapElement("doh_topic_2");
                break;
            case "third":
                tapElement("doh_topic_3");
                break;
            default:
                Assert.fail("Invalid component");
                break;
        }
    }

    public void searchHomeKeywordSuggestion() {
        SearchData.setSearchKeywords(getTextFromElement("home_search_product_field").replace("Cari ","").toLowerCase());
        typeAndEnterValue("home_search_product_field", "");
    }

    //region Digital Widget

    public void goToDigitalWidgetSection() {
        //need to refresh here to make sure digital widget section shows up
        doPullRefreshHome(3);
        scrollToDigitalWidget();
    }

    private void scrollToDigitalWidget() {
        nativeSwipeUpToElement("DIGITAL_WIDGET_SECTION");
    }

    public void validateDigitalWidgetSection() {
        try {
            verifyElementExist("DIGITAL_WIDGET_TAB");
        } catch (AssertionError e) {
            isElementPresentWithScroll("DIGITAL_WIDGET_SECTION");
        }
    }

    public void tapOnDigitalWidgetTab(String tabName) {
        //try catch in case tab section is scrolled out of page when getting digital widget into view
        try {
            tapElement(constructLocator("DIGITAL_WIDGET_TAB_NAME", tabName));
        } catch (NoSuchElementException e) {
            nativeSwipeLeft(constructLocator("DIGITAL_WIDGET_TAB_NAME", tabName));
        }
    }

    private void swipeLeftDigitalWidgetCard(String product) {
        swipeLeftAtSpecifiedLocator("DIGITAL_WIDGET_SECTION", constructLocator("DIGITAL_WIDGET_SPECIFIC_CARD_BODY", product), 8);
        validateExist(constructLocator("DIGITAL_WIDGET_SPECIFIC_CARD_BODY", product), 5);
    }

    private void swipeRightDigitalWidgetCard(String product) {
        swipeRightAtSpecifiedLocator("DIGITAL_WIDGET_SECTION", constructLocator("DIGITAL_WIDGET_SPECIFIC_CARD_BODY", product), 8);
        validateExist(constructLocator("DIGITAL_WIDGET_SPECIFIC_CARD_BODY", product), 5);
    }

    public void tapOnDigitalWidgetCard(String type) {
        //try catch here in case card is out of screen
        try {
            tapOnSpecificDigitalWidgetCard(type);
        } catch (NoSuchElementException e) {
            handleSwipeDigitalWidgetCard(type);
        }
    }

    private void handleSwipeDigitalWidgetCard(String type) {
        //handle positioning of cards, swipe left and right once to try locating the card
        try {
            swipeRightDigitalWidgetCard(type);
            tapOnSpecificDigitalWidgetCard(type);
        } catch (NoSuchElementException e) {
            swipeLeftDigitalWidgetCard(type);
            tapOnSpecificDigitalWidgetCard(type);
        }
    }

    public void tapOnSpecificDigitalWidgetCard(String cardTitle) {
        tapElement(constructLocator("DIGITAL_WIDGET_SPECIFIC_CARD_BODY", cardTitle), 10);
    }

    //region Investment Digital Widget validation
    public void validateInvestmentCard(String investmentCardType) {
        switch (investmentCardType.toLowerCase()) {
            case "non investor":
                validateInvestmentCardInfo("BukaReksa", "non investor");
                validateInvestmentCardInfo("BukaEmas", "non investor");
                break;
            case "investor":
                validateInvestmentCardInfo("BukaReksa", "investor");
                validateInvestmentCardInfo("BukaEmas", "investor");
                break;
            case "bukareksa investor only":
                validateInvestmentCardInfo("BukaEmas", "non investor");
                validateInvestmentCardInfo("BukaReksa", "investor");
                break;
            case "bukaemas investor only":
                validateInvestmentCardInfo("BukaReksa", "non investor");
                validateInvestmentCardInfo("BukaEmas", "investor");
                break;
            default:
                Assert.fail("Invalid Investment Card Type!");
        }
    }

    private void validateInvestmentCardInfo(String product, String investor) {
        if (investor.equalsIgnoreCase("non investor")) {
            //try catch to handle positioning of investor with portfolio card
            try {
                validateNonInvestorCard(product);
            } catch (AssertionError e) {
                swipeLeftDigitalWidgetCard(product);
            }
        } else if (investor.equalsIgnoreCase("investor")) {
            try {
                validateInvestorCard(product);
            } catch (AssertionError e) {
                swipeLeftDigitalWidgetCard(product);
            }
        } else {
            Assert.fail("Invalid Investment Product Type!");
        }
    }

    private void validateInvestorCard(String type) {
        validateExist(constructLocator("DIGITAL_WIDGET_CARD_SPECIFIC_TITLE", type), 10);
        validateValue().contains("Portfolio", getInvestmentCardPortfolioText(type));
        validateValue().contains("Rp", getInvestmentCardPortfolioBalance(type));
        validateExist(constructLocator("DIGITAL_WIDGET_SPECIFIC_CARD_FOOTER", type), 10);
    }

    private String getInvestmentCardPortfolioText(String type) {
        return getText(constructLocator("DIGITAL_WIDGET_SPECIFIC_CARD_BODY", type));
    }

    private String getInvestmentCardPortfolioBalance(String type) {
        return getText(constructLocator("DIGITAL_WIDGET_SPECIFIC_CARD_FOOTER", type));
    }

    private void validateNonInvestorCard(String product) {
        String infoText = null;
        if (product.equalsIgnoreCase("bukaemas")) {
            infoText = "emas";
        } else if (product.equalsIgnoreCase("bukareksa")) {
            infoText = "reksa";
        }  else {
            Assert.fail("Invalid Investment Product!");
        }

        String cardInfo = constructLocator("DIGITAL_WIDGET_SPECIFIC_CARD_BODY", product);
        validateExist(constructLocator("DIGITAL_WIDGET_CARD_SPECIFIC_TITLE", product), 10);
        validateValue().contains(infoText, getText(cardInfo).toLowerCase());
        validateNotExist(constructLocator("DIGITAL_WIDGET_SPECIFIC_CARD_SUBFOOTER", product), 10);
    }

    public void tapOnInvestmentCard(String product) {
        try {
            getPortfolioBalanceFromInvestmentCard(product);
        } catch (NoSuchElementException e) {
            handleSwipePortfolioBalanceCard(product);
        }
        tapOnDigitalWidgetCard(product);
    }

    private void getPortfolioBalanceFromInvestmentCard(String product) {
        String portfolioBalance = getText(constructLocator("DIGITAL_WIDGET_SPECIFIC_CARD_FOOTER", product));
        InvestmentData.setTotalPortfolio(portfolioBalance);
    }

    private void handleSwipePortfolioBalanceCard(String product) {
        //handle positioning of cards, swipe left and right once to try locating the card
        try {
            swipeLeftDigitalWidgetCard(product);
            getPortfolioBalanceFromInvestmentCard(product);
        } catch (NoSuchElementException e) {
            swipeRightDigitalWidgetCard(product);
            getPortfolioBalanceFromInvestmentCard(product);
        }
    }
    //endregion digital widget

    public void verifyBannerFlashDeal(){
        if (!isElementVisible("FLASH_DEAL_BANNER")){
            scrollToElement("FLASH_DEAL_BANNER");
        } else {
            verifyElementExist("FLASH_DEAL_BANNER");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickFlashDealBanner(){
        tapElement("FLASH_DEAL_BANNER");
    }

    public void validateNabungDiskon () {
        scrollToElement("NABUNG_DISKON_SECTION");
        validateExist("NABUNG_DISKON_SECTION");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapNabungDiskonSection() {
        tapElement("NABUNG_DISKON_SECTION");
    }

    public void backToHomeViaDeeplink(){
        openDeepLink("https://www.bukalapak.com/");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapDopeItemOnHomepage(String dopeName) {
        if(!isElementVisible(constructLocator("dope_item_text", dopeName))) {
            swipeLeftAtSpecifiedLocator("dope_icon", "dope_item_text", 5);
        }

        tapElement(constructLocator("dope_item_text", dopeName));
    }

    public void getUnreadChatBadge() {
        verifyElementDisplayed("chat_icon_badge");
        CHATData.setDynamicChatData(getTextFromElement("chat_icon_badge"));
    }

    public void unreadChatBadgeShouldBeReduced() {
        verifyElementDisplayed("chat_icon_badge");
        int currentUnreadBadges = Integer.parseInt(getTextFromElement("chat_icon_badge"));
        validateValue().equals(Integer.parseInt(CHATData.getDynamicChatData()) - 1, currentUnreadBadges);
    }

    public void tapChatBtnOnHomepage() {
        tapElement("chat_icon");
    }
}


