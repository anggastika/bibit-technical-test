package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class NewUserZonePage extends BasePage {

    public NewUserZonePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnNewUserZone(){
        changeContext().toWebview();
        validateExist("NUZ_FREEGIFT_TAB",30);
        validateExist("NUZ_VOUCHER_TAB", 5);
    }

    public void validateUnverifiedUser(){
        changeContext().toNative();
        verifyElementExist("NUZ_YUKVERIFIKASI_TEXT");
        verifyElementExist("NUZ_VERIFIKASI_BTN");
    }

    public void validateLogoutUser(){
        changeContext().toNative();
        validateExist("NUZ_DAFTAR_TEXT", 5);
        validateExist("NUZ_DAFTAR_BTN", 5);
    }

    public void validateVerifiedUser(){
        changeContext().toNative();
        validateExist("NUZ_TOPUP_TEXT", 10);
        validateExist("NUZ_TOPUP_BTN", 10);
    }

    public void tapOnVerifikasiBtn(){
        tapElement("NUZ_VERIFIKASI_BTN", 5);
    }

    public void tapOnDaftarBtn(){
        tapElement("NUZ_DAFTAR_BTN", 5);
    }

    public void tapOnTopUpBtn(){
        tapElement("NUZ_TOPUP_BTN", 5);
    }

    public void backToProfilePage() {
        if (isElementVisible("alchemy_navbar_back_button")) {
            tapElement("alchemy_navbar_back_button");
        } else {
            tapBackButton();
        }
    }

    public void validateOnVerifPhonePage(){
        waitForVisibilityOf("verification_phone_title", 5);
        verifyElementDisplayed("verification_phone_title");
        tapBackButton();
        HelperData.setLastActionPage(new NewUserZonePage(iosDriver));
    }

    public void validateOnRegisterPage(){
        verifyElementDisplayed("daftar_page_title");
        backToProfilePage();
        HelperData.setLastActionPage(new NewUserZonePage(iosDriver));
    }

    public void validateOnDanaPage(){
        verifyElementDisplayed("NUZ_TOPUP_DANA_PAGE");
        tapElement("NUZ_DANA_BACK_BTN");
    }

    public void validateOnCheckoutFreeGiftPage(){
        changeContext().toWebview();
        validateExist("NUZ_CHECKOUT_PAGE_TITLE", 30);
        validateExist("NUZ_BAYAR_BTN", 5);
        backToHomePageViaDeeplink();
    }

    public void tapOnFreeGiftTab() {
        webViewTapOnElement("NUZ_FREEGIFT_TAB");
    }

    public void validateBarangGratisSection() {
        validateExist("NUZ_BAYARRP1_TEXT");
    }

    public void tapOnAmbilBtn() {
        changeContext().toNative();
        tapElement("NUZ_AMBIL_BTN", 25);
    }

    public void validateNotShowNuzBanner(){
        assertFalse(isElementPresentWithScroll("new_user_banner"));
    }

    public void verifyUdahBelanja(){
        changeContext().toWebview();
        verifyElementExist("NUZ_UDAH_BELANJA_TEXT");
        verifyElementExist("NUZ_LIHAT_PROMO_BTN");
    }

    public void clickNewUserZoneBanner(){
        swipeDownAtSpecifiedLocator("new_user_banner");
        if (isElementVisible("new_user_banner", 20)){
            tapElement("new_user_banner");
        }
    }

    public void clickButtonCheck(){
        // adding time for slowly web view page
        waitFor(10);
        if (isElementVisible("NUZ_PENYESUAIAN_ONGKIR_TEXT", 15)) {
            verifyElementExist("NUZ_CEK_TOTAL_PEMBAYARAN_BTN");
            tapElement("NUZ_CEK_TOTAL_PEMBAYARAN_BTN");
            LogUtil.info("Pop up penyesuaian ongkir show");
        }
        else {
            LogUtil.info("Pop up penyesuaian ongkir not shown");
            verifyElementNotExist("NUZ_PENYESUAIAN_ONGKIR_TEXT");
        }
    }

    public void clickDismissBindDana(){
        if (isElementVisible("SKIP_BINDING_DANA_BTN", 10)) {
            verifyElementExist("BINDING_DANA_BTN");
            tapElement("SKIP_BINDING_DANA_BTN");
            LogUtil.info("Pop up DANA show");
        }
        else {
            LogUtil.info("Pop up binding DANA not shown");
            verifyElementNotExist("SKIP_BINDING_DANA_BTN");
        }
    }

    public void clickBuyButton(){
        changeContext().toNative();
        if (isElementExist("NUZ_BAYAR_BTN_NATIVE", 15)) {
            tapElement("NUZ_BAYAR_BTN_NATIVE", 5);
        }
    }

    public void validateOnVirtualAccountPage(){
        validateExist("NUZ_PAY_WITH_VA_BTN", 15);
    }

    public void clickVABank(){
        if (isElementExist("NUZ_BRI_VA", 15)) {
            tapElement("NUZ_BRI_VA");
        }
    }

    public void clickPayWithVAButton() {
        if (isElementExist("NUZ_PAY_WITH_VA_BTN", 15)) {
            tapElement("NUZ_PAY_WITH_VA_BTN");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateOnMenungguPembayaranPage(){
        waitForVisibilityOf("MENUNGGU_PEMBAYARAN_PAGE", 35);
    }

}
