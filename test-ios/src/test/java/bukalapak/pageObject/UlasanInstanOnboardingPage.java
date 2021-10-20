package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PRIOData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class UlasanInstanOnboardingPage extends BasePage {

    public UlasanInstanOnboardingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnOnboardingPage(boolean status) {
        String bannerState = dotenv.get("AUTOMATIC_REVIEW_BANNER_DISPLAYED");
        assert bannerState != null;
        waitForVisibilityOf("ulasan_instan_onboarding_title_text", 20);
        if (bannerState.equalsIgnoreCase("true")) {
            verifyElementExist("ulasan_instan_onboarding_banner");
        }
        verifyElementExist("ulasan_instan_onboarding_description_text");
        if (status) {
            waitForVisibilityOf("ulasan_instan_onboarding_lihat_paket_button", 15);
        }
        HelperData.setLastActionPage(new UlasanInstanOnboardingPage(iosDriver));
    }

    public void userOnUlasanInstanBlog() {
        waitForVisibilityOf("ulasan_instan_onboarding_fitur_ulasan_text", 30);
        HelperData.setLastActionPage(new UlasanInstanOnboardingPage(iosDriver));
    }

    public void clickBanner() {
        if (isElementVisible("ulasan_instan_onboarding_pelajari_lebih_lanjut_link", 1)) {
            swipeUpToElement("ulasan_instan_onboarding_banner");
        }
        tapElement("ulasan_instan_onboarding_banner");
    }

    public void checkSedangBerlanggananText(String automaticPackage) {
        String maxItems = dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_ITEMS_NUMBER");
        assert maxItems != null;
        waitForVisibilityOf(constructLocator("ulasan_instant_sedang_berlangganan_txt", automaticPackage), 15);
        PRIOData.setSubscriptionStatus(isElementVisible(constructLocator("ulasan_instant_sedang_berlangganan_txt", automaticPackage)));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
