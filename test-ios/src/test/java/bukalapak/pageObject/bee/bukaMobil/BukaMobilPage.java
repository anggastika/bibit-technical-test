package bukalapak.pageObject.bee.bukaMobil;

import bukalapak.data.BeeData;
import bukalapak.data.HelperData;
import bukalapak.data.MtxData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.Coordinates;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.PrecisionDescriber;
import com.bukalapak.salad.util.SwipeSpeed;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaMobilPage extends BasePage {

    public BukaMobilPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void onBukaMobilLandingPage(){
        if (isElementExist("BUKAMOBIL_ONBOARDING_IMG")) {
            validateDisplayed("BUKAMOBIL_ONBOARDING_CONTENT");
            validateDisplayed("BUKAMOBIL_ONBOARDING_LANJUTKAN_BTN");
        }
        validateExist("BUKAMOBIL_LANDING_PAGE_ICON");
        validateExist("BUKAMOBIL_LANDING_PAGE_SEARCH_FIELD");
        validateExist("BUKAMOBIL_LANDING_PAGE_DAFTAR_TRANSACTION_ICON");
        validateExist("BUKAMOBIL_LANDING_PAGE_UBAH_LOCATION_TXT");
        HelperData.setLastActionPage(new BukaMobilPage(iosDriver));
    }

    public void validateJenisSection(String flag) {
        swipeUpToElement("BUKAMOBIL_CLASSIFICATION_TXT");

        if (flag.equals("with")) {
            validateDisplayed("BUKAMOBIL_CLASSIFICATION_TXT");
        } else {
            validateNotDisplayed("BUKAMOBIL_CLASSIFICATION_TXT");
        }
    }

    public void tapOnJenisMobil(String classification){
        swipeUpToElement("BUKAMOBIL_CLASSIFICATION_TXT");
        tapElement(constructLocator("BUKAMOBIL_CLASSIFICATION_CAR_TXT", classification));
        BeeData.setCar(classification);
    }

    public void validateBrandSection(String flag) {
        swipeUpToElement("BUKAMOBIL_BRAND_TXT");

        if (flag.equals("with")) {
            validateExist("BUKAMOBIL_BRAND_SPECIFIC_CAR");
        } else {
            validateNotExist("BUKAMOBIL_BRAND_SPECIFIC_CAR", 3);
        }
    }

    public void tapOnBrandMobil(){
        swipeUpToElement("BUKAMOBIL_BRAND_TXT", 3);
        tapElement("BUKAMOBIL_BRAND_SPECIFIC_CAR");
    }

    public void tapOnLihatSemuaMobilPilihan(){
        swipeUpToElement("BUKAMOBIL_LANDING_PAGE_LIHAT_SEMUA_MOBIL_PILIHAN");
        tapElement("BUKAMOBIL_LANDING_PAGE_LIHAT_SEMUA_MOBIL_PILIHAN");
    }

    public void tapOnLiveChat(){
        swipeUpToElement("BUKAMOBIL_LANDING_PAGE_BUKAREVIEW_TXT");
        swipeUpToElement("BUKAMOBIL_LANDING_PAGE_LIVE_CHAT_BTN");
        tapElement("BUKAMOBIL_LANDING_PAGE_LIVE_CHAT_BTN");
    }

    public void onCityCarPage(){
        verifyElementExist("BukaMobil_jenis_city_car_page_text");
        HelperData.setLastActionPage(new BukaMobilPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickUbahLokasi(){
        swipeUpToElement("BUKAMOBIL_LANDING_PAGE_UBAH_TXT");
        tapElement("BUKAMOBIL_LANDING_PAGE_UBAH_TXT");
    }

    public void onUbahLokasiPage(){
        verifyElementExist("BukaMobil_lokasi_notes_text");
    }

    public void selectProvinsi(){
        tapElement("BukaMobil_provinsi_menu");
    }

    public void selectCity(){
        tapElement("BukaMobil_city_menu");
    }

    public void verifyCity(){
        tapElement("BukaMobil_back_button");
        swipeDownToClickableElement("BukaMobil_ubah_lokasi_link");
        verifyElementExist("BukaMobil_selected_city");
    }

    public void searchCar(String keyword) {
        waitForVisibilityOf("BUKAMOBIL_SEARCH_FIELD", 3);
        tapElement("BUKAMOBIL_SEARCH_FIELD");
        waitForVisibilityOf("BUKAMOBIL_SEARCH_VALUE_FIELD", 3);
        typeAndEnterValueWithTimeOut("BUKAMOBIL_SEARCH_VALUE_FIELD", keyword);
        BeeData.setCar(keyword);
    }

    public void backToBukaMObilPage() {
        tapElement("bukamobil_back_search_button");
        tapElement("bukamobil_back_suggestion_button");
        HelperData.setLastActionPage(new BukaMobilPage(iosDriver));
    }

    public void selectHeaderMenu(String headerMenu) {
        swipeUpToElement("BUKAMOBIL_HEADER_MENU");
        tapElement("BUKAMOBIL_HEADER_MENU");
        waitForVisibilityOf("BUKAMOBIL_HEADER_OPTIONS_MODAL", 3);
        String element = headerMenu.equalsIgnoreCase("BukaMotor") ? "BUKAMOBIL_HEADER_BUKAMOTOR" : "BUKAMOBIL_HEADER_BUKALAPAK";
        tapElement(element);
    }

    public void tapLihatSemuaBukareview() {
        swipeUpToElement("BUKAMOBIL_LANDING_PAGE_LIHAT_SEMUA_BUKAREVIEW");
        tapElement("BUKAMOBIL_LANDING_PAGE_LIHAT_SEMUA_BUKAREVIEW");
    }


    public void tapArticleBukareview() {
        swipeUpToElement("BUKAMOBIL_LANDING_PAGE_BUKAREVIEW_ARTICLE");
        MtxData.setArticleName(getText("BUKAMOBIL_LANDING_PAGE_BUKAREVIEW_ARTICLE"));
        tapElement("BUKAMOBIL_LANDING_PAGE_BUKAREVIEW_ARTICLE");
    }

    public void tapLihatSemuaFAQ() {
        swipeUpToElement("BUKAMOBIL_LANDING_PAGE_LIHAT_SEMUA_FAQ");
        tapElement("BUKAMOBIL_LANDING_PAGE_LIHAT_SEMUA_FAQ");
    }

    public void tapLihatSelengkapnyaBtn() {
        tapElement("BUKAMOBIL_LANDING_PAGE_LIHAT_SELENGKAPNYA");
    }

    public void validateBukaMotorOnboardingPage() {
        validateExist("BUKAMOBIL_BUKAMOTOR_ONBOARDING", 5);
    }

    public void validateBukalapakHomepage() {
        validateExist("BUKAMOBIL_BUKALAPAK_BANNER", 10);
    }

    public void validateBukaReviewHomepage() {
        validateExist("BUKAMOBIL_BUKAREVIEW_TITLE", 15);
    }

    public void validateFAQPage() {
        validateExist("BUKAMOBIL_FAQ_TITLE", 10);
    }

    public void tapTransactionIconHeader() {
        tapElement("BUKAMOBIL_LANDING_PAGE_DAFTAR_TRANSACTION_ICON");
    }
}
