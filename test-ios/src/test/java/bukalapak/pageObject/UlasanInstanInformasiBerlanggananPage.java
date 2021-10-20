package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class UlasanInstanInformasiBerlanggananPage extends BasePage {

    public UlasanInstanInformasiBerlanggananPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnInformasiBerlanggananPage() {
        waitForVisibilityOf("ulasan_instan_informasi_berlangganan_title_text", 20);
        waitForVisibilityOf("ulasan_instan_informasi_kuota_ulasan_text", 20);
        waitForVisibilityOf("ulasan_instan_informasi_kuota_ulasan_barang_text", 1);
        waitForVisibilityOf("ulasan_instan_informasi_kuota_max_barang_text", 1);
        waitForVisibilityOf("ulasan_instan_informasi_masa_aktif_text", 1);
        waitForVisibilityOf("ulasan_instan_informasi_harga_text", 1);
        HelperData.setLastActionPage(new UlasanInstanInformasiBerlanggananPage(iosDriver));
    }

    public void checkAutoExtend(boolean status) {
        if (!status) {
            if (isElementVisible("ulasan_instan_informasi_kembali_berlangganan_button", 5)) {
                LogUtil.info("state when user want to stop auto extend but user already stop the auto extend");
                waitForVisibilityOf("ulasan_instan_informasi_kembali_berlangganan_button", 5);
                tapElement("ulasan_instan_informasi_kembali_berlangganan_button");
            }
            waitForVisibilityOf("ulasan_instan_informasi_berhenti_berlangganan_button", 20);
        } else if (status) {
            if (isElementVisible("ulasan_instan_informasi_berhenti_berlangganan_button", 10)) {
                LogUtil.info("state when user want to auto extend but user already in auto extend state");
                tapElement("ulasan_instan_informasi_berhenti_berlangganan_button");
                waitForVisibilityOf("ulasan_instan_konfirmasi_berhenti_alasan_1_radio_button", 15);
                tapElement("ulasan_instan_konfirmasi_berhenti_alasan_1_radio_button");
                tapElement("ulasan_instan_konfirmasi_berhenti_berhenti_berlangganan_button");
            }
            waitForVisibilityOf("ulasan_instan_informasi_kembali_berlangganan_text", 20);
            verifyElementDisplayed("ulasan_instan_informasi_kembali_berlangganan_button");
        }
        HelperData.setLastActionPage(new UlasanInstanInformasiBerlanggananPage(iosDriver));
    }

    public void userSuccessExtend(String extendStatus) {
        if (extendStatus.equals("reactivate")) {
            waitForVisibilityOf("ulasan_instan_konfirmasi_berhenti_berhenti_berlangganan_button", 20);
        }
        else {
            waitForVisibilityOf("ulasan_instan_informasi_kembali_berlangganan_button", 20);
            verifyElementDisplayed("ulasan_instan_informasi_kembali_berlangganan_button");
        }
    }

    public void checkAutomaticPackage(String automaticPackage) {
        String maxReview = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_REVIEWS_NUMBER");
        String maxItems = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_ITEMS_NUMBER");
        String duration = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_DURATION");
        String price = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_PRICE");
        String maxReviewsPerItem = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage +"_REVIEW_PER_ITEM");
        String promoPrice = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage +"_PROMO_PRICE");
        assert maxReview != null;
        assert maxItems != null;
        assert duration != null;
        assert price != null;
        assert maxReviewsPerItem != null;
        assert promoPrice  != null;
        String realPrice = price;
        assertTrue(getTextFromElement("ulasan_instan_informasi_kuota_ulasan_text").contains(maxReview),
                "Total review isnt matched!");
        assertTrue(getTextFromElement("ulasan_instan_informasi_kuota_ulasan_barang_text").contains(maxReviewsPerItem),
                "Total review per item isnt matched!");
        assertTrue(getTextFromElement("ulasan_instan_informasi_kuota_max_barang_text").contains(maxItems),
                "Total items isnt matched!");
        assertTrue(getTextFromElement("ulasan_instan_informasi_masa_aktif_text").contains(duration),
                "Duration isnt matched!");
        assertTrue(getTextFromElement("ulasan_instan_informasi_harga_text").contains(realPrice),
                "Price isnt matched!");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
