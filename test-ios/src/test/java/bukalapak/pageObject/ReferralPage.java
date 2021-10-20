package bukalapak.pageObject;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import bukalapak.data.HelperData;

public class ReferralPage extends BasePage {

    public ReferralPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateGaBisaAmbil(){
        validateExist("kado_ga_bisa_diambil_text", 10);
    }

    public void ajakTeman(){
        validateExist("ajak_teman_button", 10);
        tapElement("ajak_teman_button");
    }

    public void dismissModal(){
        if (isElementVisible("ajak_teman_button")) {
            tapElement("ajak_teman_button");
        }
    }

    public void validateVerificationButton(){
        dismissModal();
        validateExist("verify_phone_button", 10);
    }

    public void validateEligibleButton(){
        dismissModal();
        validateExist("eligible_user_button", 10);
    }

    public void validateBelanjaButton(){
        dismissModal();
        validateExist("belanja_dulu_button", 10);
    }

    public void userIsOnSerbuSeruGratisWebviewPage() {
        changeContext().toWebview();
        verifyElementExist("serbu_seru_webview_title");
        verifyElementExist("serbu_seru_lihat_promo_button");
    }

    public void tapOnSerbuGratis(){
        webViewTapOnElement("serbu_seru_lihat_promo_button");
        changeContext().toNative();
    }

    public void shareSerbuGratis(){
        validateDisplayed("modal_share_teman_text");
        tapElement("modal_share_teman_salin_tautan");
    }

    public void tapAfterSerbuGratis(){
        changeContext().toWebview();
        verifyElementExist("post_serbu_berhasil_text", 10, "Element not exist");
        webViewTapOnElement("post_serbu_berhasil_tutup_button");
    }

    public void verifyOnPostSerbuPage(){
        verifyElementExist("post_serbu_mau_gratisan_text");
        HelperData.setLastActionPage(new ReferralPage(iosDriver));
    }

    //referral 3.0
    public void userIsOnRefereePage() {
        changeContext().toWebview();
        waitForVisibilityOf("REFEREE_PAGE_TXT", 20);
        if (isElementExist("REFERRAL_GIFT_SHEET")) {
            tapElement("REFERRAL_CLOSE_GIFT_SHEET_ICON");
        }
        validateExist("REFEREE_PAGE_TXT");
        tapElement("REFEREE_PAGE_TXT");
    }

    public void verifyReferralCuanPage() {
        changeContext().toWebview();
        waitForVisibilityOf("REFERRAL_UNDANG_TEMAN_BTN",20 );
        verifyElementExist("REFERRAL_UNDANG_TEMAN_BTN");
    }

    public void tapOnUndangTemanBtn() {
        webViews().tapElement("REFERRAL_UNDANG_TEMAN_BTN", 10);
    }

    public void tapAmbilCreditsBtn()  {
        tapElement("REFERRAL_AMBIL_CREDITS_BTN");
    }

    public void verifyPhoneSheet() {
        verifyElementExist("VERIFIKASI_HP_SHEET_HEADER");
        tapElement("VERIFIKASI_HP_BTN");
        changeContext().toNative();
        HelperData.setLastActionPage(new ReferralPage(iosDriver));
    }

    public void tapOnRefereeListIcon() {
        tapElement("REFERRAL_LIST_REFEREE_ICON");
    }

    public void isOnListRefereePage() {
        verifyElementExist("cek_credits_text");
    }

    public void verifyEmptyReferee() {
        verifyElementExist("REFERRAL_NO_REFEREE_AMOUNT_TXT");
        verifyElementExist("REFERRAL_EMPTY_TXT");
        HelperData.setLastActionPage(new ReferralPage(iosDriver));
    }

    public void validateShareModal() {
        verifyElementExist("REFERRAL_SELLER_SHARE");
        HelperData.setLastActionPage(new ReferralPage(iosDriver));
    }

    public void validateApologizeModal() {
        changeContext().toWebview();
        verifyElementExist("REFERRAL_APOLIGIZE_MODAL");
        tapElement("REFERRAL_APOLIGIZE_BUTTON");
    }

    public void validateNuzSectionReferee() {
        waitForVisibilityOf("NUZ_BANNER", 20);
        webViewSwipeToElement("NUZ_BANNER");
        tapElement("NUZ_BANNER");
    }
    //end of referral3.0

    public void goToHomePage() {
        changeContext().toNative();
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
