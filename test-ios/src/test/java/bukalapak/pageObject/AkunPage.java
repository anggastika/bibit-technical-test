package bukalapak.pageObject;

import bukalapak.data.CSIData;
import bukalapak.data.HelperData;
import bukalapak.data.RAGEData;
import bukalapak.data.UserData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.util.List;
import java.util.Objects;

import static bukalapak.TestInstrument.dotenv;

/**
 * Created by sekarayukarindra on 01/10/18.
 */
public class AkunPage extends BasePage {

    private static boolean newAkunMenu = false;

    public AkunPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnAkunOnboarding() {
        verifyElementExist("akun_onboarding_close_button");
        verifyElementExist("akun_onboarding_login_button");
        verifyElementExist("akun_onboarding_daftar_akun_button");
        verifyElementExist("akun_onboarding_menu_lain_button");
        //HelperData.setLastActionPage(new AkunPage());
    }

    public void tapDaftarAkun() {
        verifyElementExist("akun_onboarding_daftar_akun_button");
        tapElement("akun_onboarding_daftar_akun_button");
    }

    public boolean getStatusAkunMenu() {
        return newAkunMenu;
    }

    private synchronized void setStatusAkunMenu(boolean newakunmenu) {
        newAkunMenu = newakunmenu;
    }

    public void waitAkunMenu() {
        waitFor(10);
        waitForVisibilityOf("home_akun_tab", 15);
    }

    public void clickAkunMenu() {
        waitAkunMenu();

        int loop = 3;

        // click Akun menu up to 3 times while Account page not displayed
        while (isElementExist("search_on_search_page", 5) &&
                isElementExist("home_blhome_tab", 5) &&
                loop > 0) {
            tapElement("home_akun_tab");

            loop--;
        }

        if (isElementExist("akun_pengaturan_akun_title", 5)) {
            tapElement("alchemy_back_button");
        }

        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }

    public void tapPengaturanAkunOption() {
        if (!isElementVisible("pembeli_tab")) {
            swipeDownToElement("pembeli_tab");
            tapElement("pembeli_tab");
        }
        swipeUpToElement("akun_pengaturan_akun_option");
        tapElement("akun_pengaturan_akun_option");
    }

    public void backToAkunPage() {
        isElementExist("password_page_title", 5);
        tapElement("base_back_button");
    }

    private void goToSecretNinjaPage() {
        String secretMenuPassword = dotenv.get("SECRET_MENU_PASSWORD");
        backToHomePageViaDeeplink();
        tapElement("home_akun_tab");
        HelperData.setLastActionPage(new AkunPage(iosDriver));
        if (isElementVisible("pembeli_tab", 2)) {
            // Login
            tapElement("pembeli_tab");
            swipeUpToElement("tentang_kami_text");
            tapElement("tentang_kami_text");
        } else {
            // Non Login
            tapElement("akun_onboarding_menu_lain_button");
        }
        if (isElementVisible("menulain_super_secret_ninja_tab", 2)) {
            // debug
            tapElement("menulain_super_secret_ninja_tab");
        } else {
            // release
            tapElement("menulain_tentang_aplikasi_tab");
            if (!isElementVisible("ttgaplikasi_secret_pass_field")) {
                tapMultipleOnElement("ttgaplikasi_secret_tab", 7);
            }
            if (isElementVisible("ttgaplikasi_secret_pass_field")) {
                typeAndEnterValueWithTimeOut("ttgaplikasi_secret_pass_field", secretMenuPassword);
            }
        }
    }

    public void changeStagingEnv(String rawEnv) {
        String tmpRawEnv = rawEnv.replace(" ", "_").toUpperCase();
        String environment = dotenv.get("ENVIRONMENT_" + tmpRawEnv);
        goToSecretNinjaPage();
        chooseStagingEnv(environment);
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    private void chooseStagingEnv(String environment) {
        swipeDownToClickableElement("secretninja_change_env_tab");
        tapElement("secretninja_change_env_tab");
        if (!isElementVisible(constructLocator("secretninja_environment", environment))) {
            swipeUpToElement(constructLocator("secretninja_environment", environment), 20);
        }
        tapElement(constructLocator("secretninja_environment", environment));
    }

    public void forceSplitter(String experiment, String variant) {
        tapElement("secretninja_splitter_manipulator_tab");
        typeAndEnterValueWithTimeOut("secretninja_splitter_manipulator_search_field", experiment);

        //String variantLocator = "name_" + variant;
        String variantLocator = constructLocator("akun_force_splitter_variant", variant);
        tapElement("secretninja_splitter_manipulator_variant_dropdown");
        //swipeDownToElement(getLocator(variantLocator));
        swipeDownToElement(variantLocator);
        tapElement(variantLocator);

        tapElement("menulain_back_button");
    }

    public void emailVerificationPopUpShouldDisplayed() {
        verifyElementExist("account_email_verification_text");
    }

    private String getProfileNameText() {
        waitForVisibilityOf("akun_user_name", 10);
        return getText("akun_user_name");
    }

    /**
     * Validate equality of username on account page using ENV
     *
     * @param username accept credential with and without suffix NAME
     *                 e.g. USER BL, user bl, USER_BL_NAME, USER_BL_USERNAME
     */
    public void validateUsername(String username) {
        String tmpUsername = username;
        if (username.contains(" ")) {
            String[] usernames = username.replaceAll(" ", "_").toUpperCase().split("_");
            if (!usernames[usernames.length - 1].equals("NAME")) {
                tmpUsername = username + "_NAME";
            }
        }

        swipeDownToElement("akun_user_name");
        UserData.setLoggedIn(true);
        assertEquals(getProfileNameText(), Objects.requireNonNull(dotenv.get(tmpUsername)), "Profile name doesnt match");
    }

    public void clickWaktuProses() {
        swipeUpToElement("akun_waktu_proses_pesanan", 10);
        tapElement("akun_waktu_proses_pesanan");
    }

    public void clickJasaPengiriman() {
        swipeUpToElement("akun_jasa_pengiriman_text", 10);
        tapElement("akun_jasa_pengiriman_text");
    }

    public void clickLihatDetailText() {
        swipeDownToElement("account_lihat_detail_text");
        tapElement("account_lihat_detail_text");
    }

    public void clickPembekuanLapak() {
        swipeUpToElement("akun_pembekuan_lapak", 15);
        tapElement("akun_pembekuan_lapak");
    }

    public void userOnAkunPage() {
        if (!isElementVisible("akun_user_name")) {
            tapGoToTopPage();
            swipeDownToElement("akun_user_name", 10);
        }
        if (isElementVisible("pelapak_tab")) {
            verifyElementExist("pelapak_tab");
            //newAkunMenu = true;
            setStatusAkunMenu(true);
        } else {
            verifyElementExist("akun_edit_profil_text");
            verifyElementExist("akun_saldo_bd_text");
            //newAkunMenu = false;
            setStatusAkunMenu(false);
        }
        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }

    public void tapGoToTopPage() {
        if (isElementVisible("pembeli_tab", 2)) {
            tapElement("pembeli_tab", 2);
            swipeDownToElement("akun_go_to_top_button", 1);
            if (isElementVisible("akun_go_to_top_button", 2)) {
                tapElement("akun_go_to_top_button", 2);
            }
        }
    }

    //For go to Bukareview page
    public void goToBukareviewPage() {
        tapElement("akun_onboarding_menu_lain_button");
        tapElement("menulain_bukareview_tab");
        HelperData.setLastActionPage(new BukareviewHomePage(iosDriver));
    }

    public void goBackToProduction() {
        goToSecretNinjaPage();
        swipeDownToElement("secretninja_change_env_tab");
        tapElement("secretninja_change_env_tab");
        swipeUpToElement("environment_production_text");
        tapElement("environment_production_text");
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickDraftMenu() {
        swipeDownToElement("pelapak_draft_button");
        tapElement("pelapak_draft_button");
    }

    public void setNeoToggle(String status, String toggleName) {
        String toggleLocator = constructLocator("akun_toggle_name", toggleName);
        goToSecretNinjaPage();

        tapElement("secretninja_neo_toggle_manipulator_tab");
        typeAndEnterValueWithTimeOut("neo_toggle_search_textfield", toggleName);

        switch (status) {
            case "ENABLE":
                if (isElementVisible("neo_toggle_enable_button")) {
                    LogUtil.info("Neo Toggle : " + toggleName + " is already enable");
                    break;
                } else {
                    tapElement(toggleLocator);
                    break;
                }
            case "DISABLE":
                if (isElementVisible("neo_toggle_disable_button")) {
                    LogUtil.info("Neo Toggle : " + toggleName + " is already disable");
                    break;
                } else {
                    tapElement(toggleLocator);
                    break;
                }
            default:
                Assert.fail("Invalid status : " + status + ". Status should be ENABLE or DISABLE");
                break;
        }

        tapElement("alchemy_back_button");
    }

    public void setUserAgent(String userAgent) {
        swipeDownToClickableElement("set_user_agent");
        tapElement("set_user_agent");
        typeAndEnterValueWithTimeOut("user_agent_textfield", userAgent);
        tapElement("user_agent_ok_button");
    }

    public void verifyPromotedPushOnboardingDisplayed() {
        waitForVisibilityOf("akun_promoted_onboarding_coba_button", 10);
        verifyElementDisplayed("akun_promoted_onboarding_title_text");
    }

    public void verifyPromotedPushOnboardingDisappeared() {
        verifyElementNotExist("akun_promoted_onboarding_coba_button");
    }

    public void closePromotedPushOnboarding() {
        if (isElementExist("akun_promoted_onboarding_coba_button", 5)) {
            tapElement("akun_promoted_onboarding_lihat_keuntungan_button");
            tapElement("akun_promoted_onboarding_lanjut_button");
            tapElement("akun_promoted_onboarding_lanjut_button");
            tapElement("akun_promoted_onboarding_tutup_button");
        }
    }

    public void clickUlasanKamuText() {
        waitForVisibilityOf("akun_menu_ulasan_kamu_text");
        verifyElementExist("akun_menu_ulasan_kamu_text");
        tapElement("akun_menu_ulasan_kamu_text");
    }

    public void ulasanKamuSection() {
        //scroll to menu pengaturan
        swipeUpToElement("akun_menu_komplain_text");
        verifyElementExist("akun_menu_ulasan_kamu_text");
    }

    public void verifyNotificationUlasanKamuMenuAkun() {
        verifyElementExist("notification_bubble_menu_akun");
        RAGEData.setTotalProductBelumDiulas(getElementValue("notification_bubble_menu_akun"));
    }

    public void clickBarangDijual() {
        swipeUpToElement("akun_barang_dijual_text", 15);
        tapElement("akun_barang_dijual_text");
    }

    public void clickTentangSuperSeller(String menuSuperSeller) {
        switch (menuSuperSeller) {
            case "Tentang Super Seller":
                swipeUpToElement("akun_tentang_super_seller_text", 20);
                tapElement("akun_tentang_super_seller_text");
                break;
            case "Status Program":
                swipeUpToElement("akun_status_program_text", 20);
                tapElement("akun_status_program_text");
                break;
            case "Dashboard Super Seller":
                swipeUpToElement("akun_super_seller_dashboard_text", 20);
                tapElement("akun_super_seller_dashboard_text");
                break;
            default:
                LogUtil.info("Tentang super seller or Status Program not found");
                break;
        }
    }

    public void clickEtalase() {
        swipeUpToElement("etalase_label", 15);
        tapElement("etalase_label");
    }

    public void scrollAndClickFeedback() {
        swipeUpToElement("feedback_pelapak_text", 15);
        tapElement("feedback_pelapak_text");
    }

    public void scrollAndClickBukaBantuan() {
        swipeUpToElement("akun_bukabantuan_text", 20);
        tapElement("akun_bukabantuan_text");
    }

    public void scrollAndClickKomplain() {
        swipeUpToElement("akun_komplain_text", 15);
        tapElement("akun_komplain_text");
    }

    public void scrollAndClickReferral() {
        swipeUpToElement("referral_label", 10);
        tapElement("referral_label");
    }

    public void scrollAndClickInspirasiPenjualan() {
        swipeUpToElement("akun_inspirasi_penjualan_text", 20);
        tapElement("akun_inspirasi_penjualan_text");
    }

    public void scrollAndClickPendapatan() {
        swipeUpToElement("akun_pendapatan_text", 20);
        tapElement("akun_pendapatan_text");
    }

    public void scrollAndClickLinkJualBeli() {
        swipeUpToElement("akun_link_jual_beli_text", 20);
        tapElement("akun_link_jual_beli_text");
    }

    public void clickKaryawanLapakMenu() {
        swipeUpToElement("akun_karyawan_lapak_text", 15);
        tapElement("akun_karyawan_lapak_text");
    }

    public void clickTemanCuanMenu() {
        swipeUpToElement("akun_teman_cuan_text", 15);
        tapElement("akun_teman_cuan_text");
    }

    public void verifyDANABoundAkunPage() {
        verifyElementNotExist("account_hubungkan_dana_text");
        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }

    public void tapOnTransaksiRutinMenu() {
        verifyElementExist("transaksi_rutin");
        tapElement("transaksi_rutin");
    }

    public void scrollToRecommendation() {
        swipeUpToElement("recommendation_section", 6);
        verifyElementExist("recommendation_section");
    }

    public void tapButtonBeli() {
        swipeUpToElement("button_beli", 6);
        tapElement("button_beli");
    }

    public void verifyAddToCart() {
        waitFor(10);
        verifyElementExist("pop_up_atc");
        tapElement("exit_pop_up_atc");
    }

    public void clickRecommendationProduct() {
        swipeUpToElement("recommendation_product", 4);
        verifyElementExist("recommendation_product");
        tapElement("recommendation_product");
    }

    public void verifyRecoNotOnMyAccount() {
        swipeUpToElement("recommendation_section", 4);
        verifyElementNotExist("recommendation_section");
    }

    public void tapResurrectionZoneBanner() {
        waitForVisibilityOf("resurrection_zone_banner", 15);
        tapElement("resurrection_zone_banner");
    }

    public void tapPelapakTab() {
        tapElement("pelapak_tab");
    }

    public void tapVerificationPhoneNumber() {
        if (isElementVisible("account_email_verification_button", 5)) {
            swipeLeftAtSpecifiedLocator("account_email_verification_button");
        }
        waitForVisibilityOf("account_phone_number_verification_button", 5);
        tapElement("account_phone_number_verification_button");
    }

    public void checkKomplainBadge() {
        swipeUpToElement("akun_komplain_text", 15);
        int notifProfileCount = Integer.parseInt(getElementValue("akun_notif_komplain_badge")
                .replaceAll("\\D+", ""));
        CSIData.setNotifCount(notifProfileCount);
    }

    public void validateComplaintDecreased() {
        //wait for background job to decreasing complaint count
        delay(6000);
        tapElement("komplain_back_button");
        tapElement("komplain_back_button");

        //wait for count on badge decreased
        delay(2000);
        int notifProfileCount = Integer.parseInt(getElementValue("akun_notif_komplain_badge")
                .replaceAll("\\D+", ""));
        validateValue().equals(CSIData.getNotifCount() - 1, notifProfileCount);
    }

    public void tapBuatVoucherLapak() {
        swipeUpToElement("akun_buat_voucher_lapak_text", 15);
        tapElement("akun_buat_voucher_lapak_text");
    }

    public void verifyBadgeAdaUpdateStatusProgram(String state) {
        swipeUpToElement("akun_status_program_text", 20);
        if (state == null) {
            waitForVisibilityOf("akun_status_program_ada_update_badge", 5);
        } else {
            validateNotDisplayed("akun_status_program_ada_update_badge");
        }
        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }

    public void verifyFreezeWarningAkunPelapakNotDisplayed() {
        validateNotExist("freeze_warning_banner_name", 3);
    }

    public void clickPerformaLapak() {
        swipeUpToElement("akun_performa_lapak_text");
        tapElement("akun_performa_lapak_text");
    }

    public void verifyPopUpVerificationPhoneAkun() {
        verifyElementExist("akun_pop_up_verification_phone");
        verifyElementExist("akun_label_pop_up_verification_phone");
        HelperData.setLastActionPage(new AkunPage(iosDriver));
    }

    public void swipeToPhoneVerificationCard() {
        int start = 1;
        int end = 5;

        verifyElementExist("account_email_verification_button");
        while (end > start) {
            swipeLeftAtSpecifiedLocator("account_email_verification_button");
            end--;
        }
    }

    public void verifyFreezeBannerDisplayed(String freezeContext) {
        waitForVisibilityOf("freeze_warning_banner_name", 10);
        validateElementWithText(constructLocator("freeze_warning_banner_info_name", freezeContext), freezeContext);
    }

    public void clickHyperlinkFreezeBanner() {
        tapElement("freeze_banner_hyperlink_button");
    }

    public void verifyRecommendationCarousel() {
        swipeUpToElement("product_image_carousel", 15);
        verifyElementExist("carousel_recommendation_label");
    }

    public void verifyProductCardCarousel() {
        swipeUpToElement("product_bmss_carousel");
        verifyElementExist("product_image_carousel");
        verifyElementExist("product_label_carousel");
        verifyElementExist("product_price_carousel");
        verifyElementExist("product_rating_carousel");
        verifyElementExist("product_rating_label_carousel");
        verifyElementExist("product_sold_carousel");
        verifyElementExist("product_bmss_carousel");
    }

    public void swipeCarouselProduct() {
        swipeLeftAtSpecifiedLocator("product_image_carousel");
        swipeRightAtSpecifiedLocator("product_image_carousel");
    }

    public void tapRecommendationCarousel() {
        tapElement("product_image_carousel");
    }

    public void verifyRecommendationCarouselNotExist() {
        swipeUpToElement("voucherku");
        verifyElementNotExist("carousel_recommendation_label");
    }

    public void setBeliveEnvironment(String env) {
        goToSecretNinjaPage();
        swipeDownToElement("secretninja_change_belive_env");
        tapElement("secretninja_change_belive_env");
        tapElement(constructLocator("secretninja_select_belive_env", env));
        backToHomePageViaDeeplink();
        doPullRefresh(1);
    }

    public void clickJualBarang() {
        swipeUpToElement("akun_tambah_barang_text");
        tapElement("akun_tambah_barang_text");
    }

    public void tapEditProfilIcon() {
        tapElement("akun_user_name", 10);
    }

    public void verifySellerBanner(String state) {
        if (state == null) {
            swipeUpAtSpecifiedLocator("akun_seller_banner");
            verifyElementExist("akun_seller_banner");
            verifyElementExist("akun_sembunyikan_btn");
        } else {
            swipeUpAtSpecifiedLocator("akun_performa_lapak_text");
            verifyElementNotExist("akun_seller_banner");
            verifyElementNotExist("akun_sembunyikan_btn");
        }
    }

    public void tapSellerBannerSembunyikanButton() {
        tapElement("akun_sembunyikan_btn");
    }

    public void tapNavigationBar(String navbarName) {
        switch (navbarName.toLowerCase()) {
            case "notification":
                tapElements("akun_navbar_icons", 0);
                break;
            case "chat":
                tapElements("akun_navbar_icons", 1);
                break;
            case "setting":
                tapElements("akun_navbar_icons", 2);
                break;
            default:
                LogUtil.error("wrong navbar name!");
                break;
        }
    }
}
