package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;


public class UlasanInstanInvoicePage extends BasePage {

    public UlasanInstanInvoicePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnUlasanInstanInvoicePage(String paymentType) {
        waitForVisibilityOf("ulasan_instan_invoice_tag_text", 30);
        if (paymentType.equalsIgnoreCase("pending")) {
            swipeUpToElement("ulasan_instan_invoice_ubah_metode_pembayaran_button");
        }
        HelperData.setLastActionPage(new UlasanInstanInvoicePage(iosDriver));
    }

    public void checkInvoiceDetailForAutomaticReview(int automaticPackage) {
        String maxReview = dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_REVIEWS_NUMBER");
        String maxItems = dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_ITEMS_NUMBER");
        String duration = dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_DURATION");
        String price = dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_PROMO_PRICE");
        assert maxReview != null;
        assert maxItems != null;
        assert duration != null;
        waitForVisibilityOf("ulasan_instan_invoice_detail_text", 20);
        swipeUpToElement("ulasan_instan_invoice_masa_aktif_value_text", 15);
        assertTrue(getTextFromElement("ulasan_instan_invoice_total_review_and_item_text").contains(maxReview),
                "Number of reviews isnt matched with the expected!");
        assertTrue(getTextFromElement("ulasan_instan_invoice_total_review_and_item_text").contains(maxItems),
                "Number of items to be registered isnt matched with the expected!");
        assertTrue(getText("ulasan_instan_invoice_total_harga_text").contains(price));
        assertTrue(getTextFromElement("ulasan_instan_invoice_masa_aktif_value_text").contains(duration),
                "Duration isnt matched with the expected");
        if (isElementVisible("ulasan_instan_invoice_aktif_hingga_text")) {
            tapElement("tagihan_informasi_tagihan_text");
            waitForVisibilityOf("tagihan_dibayar_text", 15);
            assertTrue(getText("checkout_non_marketplace_total_tagihan").contains(price));
            assertTrue(getText("ulasan_instan_invoice_chosen_payment_method_text").equals("BukaDompet"));
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
