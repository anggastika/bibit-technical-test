package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.SearchData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukamallBrandPage extends BasePage {

    public BukamallBrandPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void userOnBukamallBrandPage() {
        verifyElementExist("bukamall_brand_page_ori_text");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void userOnTabInformasi() {
        verifyElementExist("brand_page_barang_dikirim_hari_ini_text");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void userOnFeedbackPelapakPage() {
        verifyElementExist("brand_page_feedback_pelapak_title");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void clickBeli() {
        swipeToLocator("brand_page_beli_button");
        isElementPresentWithScroll("brand_page_beli_button");
        tapElement("brand_page_beli_button");
    }

    public void verifyOnBukaMallLanding() {
        tapElement("bukamall_populer_tab");
        swipeDownToElement("bukamall_alchemy_spesial_untukmu_text", 15);
        waitForVisibilityOf("banner_section_bukaMall", 10);
        verifyElementExist("bukamall_chat_icon");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyChatOnBukaMall() {
        waitForVisibilityOf("bukamall_chat_icon", 2);
        verifyElementExist("bukamall_chat_icon");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyBukaMartOnBukaMall() {
        waitForVisibilityOf("bukamart_icon_shortcut", 2);
        verifyElementExist("bukamart_icon_shortcut");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyVoucherOnBukaMall() {
        waitForVisibilityOf("voucher_shortcut", 5);
        verifyElementExist("voucher_shortcut");
    }

    public void verifyVoucherPage() {
        if (isElementExist("notif_empty_voucher", 5)) {
            LogUtil.info("You Have no Voucher yet");
        } else {
            String hasil = getElementValue("notif_count_of_voucher");
            LogUtil.info(hasil);
        }

        tapElement("back_button_voucher");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyCartIconOnBukaMall() {
        waitForVisibilityOf("bukamall_cart_icon", 5);
        verifyElementExist("bukamall_cart_icon");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyProdukPilihanShortcut() {
        waitForVisibilityOf("produk_terlaris_shortcut", 2);
        verifyElementExist("produk_terlaris_shortcut");
    }

    public void verifyProdukPilihanPage() {
        verifyElementExist("produk_pilihan_title");
        verifyElementExist(constructLocator("product_card", 1));
        tapElement("alchemy_back_button");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyBrandTerbaruShortcut() {
        waitForVisibilityOf("brand_terbaru_shortcut", 5);
        verifyElementExist("brand_terbaru_shortcut");
    }

    public void verifyBrandTerbaruPage() {
        verifyElementExist("brand_terbaru_title");
        verifyElementExist("button_ikuti");
        tapElement("alchemy_navbar_back_button");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyBrandPilihanTitle() {
        waitForVisibilityOf("brand_pilihan_title", 2);
        verifyElementExist("brand_pilihan_title");
    }

    public void verifyBrandDalanNegriTitle() {
        nativeSwipeUp();
        verifyElementExist("brand_dalam_negri_title");
    }

    public void verifyBrandDalamNegriPage() {
        verifyElementExist("brand_resmi_title");
        verifyElementExist("dalam_negri_tab");
        waitForVisibilityOf("alchemy_navbar_back_button",5);
        tapElement("alchemy_navbar_back_button");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyProductListingBukaMall() {
        swipeUpToElement("search_product_price");
        verifyElementExist("search_product_price");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyNotifIcon(){
        verifyElementExist("bukamall_notif_icon");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyBrandResmiPage(){
        verifyElementExist("brand_resmi_title");
        verifyElementExist("semua_brand_tab");
        tapElement("alchemy_navbar_back_button");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void skipFlashDealOnboarding() {
        if (isElementVisible("brandpage_onboarding_btn_mengerti", 5)) {
            tapElement("brandpage_onboarding_btn_mengerti");
        }
    }

    public void verifyBukaMallBrandPage() {
        skipFlashDealOnboarding();

        String brandNameOmni = SearchData.getSearchBrandName();
        String brandName = getElementValue("brandpage_brand_name");

        verifyElementExist("brandpage_bukamall_badge");
        assertEquals(brandNameOmni, brandName);
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyNewestProductTitle(){
        waitForVisibilityOf("produk_terbaru_title",5);
    }

    public void verifyProdukTerbaruPage(){
        waitForVisibilityOf("produk_terbaru_page",5);
        waitForVisibilityOf("alchemy_back_button",5);
    }

    public void verifyPopularSectionBukaMall(){
        swipeUpToElement("popular_section_title");
        swipeUpToElement("popular_section_product");
    }

    public void verifyBukaMallIcon(){
        verifyElementDisplayed("brandpage_bukamall_badge");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void tapBukaMartOnBukaMall() {
        waitForVisibilityOf("bukamart_icon_shortcut", 2);
        tapElement("bukamart_icon_shortcut");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyOnMerchandiseOfficial() {
        skipFlashDealOnboarding();
        validateElementVisible("bukamall_alchemy_bukalapak_merch_title");
    }
}
