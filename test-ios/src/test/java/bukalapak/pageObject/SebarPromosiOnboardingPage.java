package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SebarPromosiOnboardingPage extends BasePage {
    public SebarPromosiOnboardingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifySebarPromosiOnboardingDisplayed() {
        if (isElementVisible(("sebar_promosi_onboarding_text"), 15)) {
            validateExist("sebar_promosi_onboarding_text");
            tapElement("Mulai_sekarang_button");
        }
        HelperData.setLastActionPage(new SebarPromosiOnboardingPage(iosDriver));
    }

    public void verifySebarPromosiOnboardingNonSuperSeller() {
        waitForVisibilityOf("sebar_promosi_onboarding_non_super_seller_text", 15);
        validateDisplayed("sebar_promosi_onboarding_aktifkan_super_seller_btn");
        HelperData.setLastActionPage(new SebarPromosiOnboardingPage(iosDriver));
    }

    public void tapAktifkanSuperSeller() {
        tapElement("sebar_promosi_onboarding_aktifkan_super_seller_btn");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
