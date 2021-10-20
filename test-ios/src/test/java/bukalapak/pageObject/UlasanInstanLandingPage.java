package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


import static bukalapak.TestInstrument.dotenv;

public class UlasanInstanLandingPage extends BasePage {

    public UlasanInstanLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnDashboardPage(boolean status) {
        doPullRefresh(1);
        waitForVisibilityOf("ulasan_instan_landing_title_text", 30);
        verifyElementExist("ulasan_instan_landing_dashboard_text");
        verifyElementExist("ulasan_instan_landing_riwayat_text");
        verifyElementExist("ulasan_instan_landing_onboarding_button");
        if (status) {
            waitForVisibilityOf("ulasan_instan_landing_ayo_langganan_text", 30);
            verifyElementExist("ulasan_instan_landing_invitation_text");
            verifyElementExist("ulasan_instan_landing_lihat_paket_button");
        } else {
            waitForVisibilityOf("ulasan_instan_landing_ulasan_number_text", 30);
            verifyElementExist("ulasan_instan_landing_items_number_text");
            verifyElementExist("ulasan_instan_landing_expired_date_text");
        }
        HelperData.setLastActionPage(new UlasanInstanLandingPage(iosDriver));
    }

    public void userOnUlasanInstanPendingPaymentDashboardPage() {
        waitForVisibilityOf("ulasan_instan_landing_title_text", 20);
        waitForVisibilityOf("ulasan_instan_pending_payment_info_text", 20);
        verifyElementExist("ulasan_instan_pending_payment_description_text");
        verifyElementExist("ulasan_instan_landing_lihat_tagihan_button");
    }

    public void userOnUlasanInstanPendingPaymentRiwayatPage(int automaticPackage) {
        String maxReview = dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_REVIEWS_NUMBER");
        assert maxReview != null;
        waitForVisibilityOf("ulasan_instan_riwayat_pending_payment_info_text", 20);
        assertTrue(getTextFromElement("ulasan_instan_riwayat_pending_payment_package_info_text").contains(maxReview),
                "Number of reviews isnt matched with the expected!");
        assertTrue(getTextFromElement("ulasan_instan_riwayat_pending_payment_package_price_text").contains(dotenv.get("AUTOMATIC_REVIEW_PACKAGE_"+automaticPackage+"_PROMO_PRICE")));
    }

    public void checkRegisteredAutomaticPackage(int automaticPackage) {
        String maxReview = dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_REVIEWS_NUMBER");
        String maxItems = dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_ITEMS_NUMBER");
        assert maxReview != null;
        assert maxItems != null;
        waitForVisibilityOf("ulasan_instan_landing_tambah_button", 30);
        doPullRefresh(1);
        assertTrue(getTextFromElement("ulasan_instan_landing_ulasan_number_text").contains(maxReview),
                "Number of reviews isnt matched with the expected!");
        assertTrue(getTextFromElement("ulasan_instan_landing_items_number_text").contains(maxItems),
                "Max products isnt matched with the expected!");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
