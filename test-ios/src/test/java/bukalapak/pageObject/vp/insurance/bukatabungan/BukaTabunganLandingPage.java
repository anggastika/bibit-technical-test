package bukalapak.pageObject.vp.insurance.bukatabungan;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaTabunganLandingPage extends VpBasePage {
    public BukaTabunganLandingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        validateExist("bukatabungan_info_promo", 15);
    }

    public void validateProductSection() {
        verifyElementExist("bukatabungan_info_promo");
        verifyElementExist("bukatabungan_pilihan_layanan");
        verifyElementExist("bukatabungan_simpanan");
        verifyElementExist("bukatabungan_simpanan_icon");
        verifyElementExist("bukatabungan_bayar_nanti");
        verifyElementExist("bukatabungan_bayar_nanti_icon");
        swipeUpToElement("bukatabungan_pinjaman_icon");
        verifyElementExist("bukatabungan_cicilan_icon");
        verifyElementExist("bukatabungan_cicilan");
        verifyElementExist("bukatabungan_pinjaman");
        verifyElementExist("bukatabungan_pinjaman_icon");
    }

    public void validateFooterSection() {
        swipeUpToElement("bukatabungan_footer");
        verifyElementExist("bukatabungan_panduan");
        verifyElementExist("bukatabungan_panduan_icon");
        verifyElementExist("bukatabungan_logo_standard_chartered");
        verifyElementExist("bukatabungan_logo_ojk");
        verifyElementExist("bukatabungan_logo_lps");

        HelperData.setLastActionPage(new BukaTabunganLandingPage(iosDriver));
    }

    public void validateReferralSection(boolean isRegistered) {
        if (isRegistered) {
            swipeUpToElement("bukatabungan_video_onboarding_title");
            verifyElementExist("bukatabungan_referal_code_title");
            verifyElementExist("bukatabungan_referal_bagikan_button");
            verifyElementExist("bukatabungan_referal_detail");
        }
    }

    public void validateRegisterSection(boolean isRegistered) {
        if (!isRegistered) {
            verifyElementExist("bukatabungan_button_pendaftaran");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
