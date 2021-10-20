package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PRIOData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class UlasanInstanCheckoutPage extends BasePage {

    public UlasanInstanCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnAutomaticReviewCheckoutPackagePage(String page, Integer automaticPackage) {
        if (page.equalsIgnoreCase("detail pembelian tab")) {
            checkDetailPembelianTab(automaticPackage);
        } else if (page.equalsIgnoreCase("pembayaran tab")) {
            checkPembayaranTab();
        } else if (page.equalsIgnoreCase("force BukaDompet page")) {
            checkForceBDPage(automaticPackage);
        }
        HelperData.setLastActionPage(new UlasanInstanCheckoutPage(iosDriver));
    }

    private void checkDetailPembelianTab(int automaticPackage) {
        waitForVisibilityOf("ulasan_instan_checkout_detail_pembelian_text");
        verifyElementExist("ulasan_instan_checkout_pembayaran_text");
        verifyElementExist("ulasan_instan_checkout_paket_ulasan_instan_total_text");
        verifyElementExist("ulasan_instan_checkout_paket_masa_aktif_text");
        if (PRIOData.getSubscriptionStatus()) {
            assertEquals("Kuota ulasan yang tersisa akan ditukarkan menjadi Promoted Push dan bisa langsung digunakan. ", getTextFromElement("ulasan_instan_upgrade_info_text"));
        }
        if (isElementVisible("ulasan_instan_checkout_price_text", 3)) {
            assertTrue(getTextFromElement("ulasan_instan_checkout_price_text")
                            .contains(PRIOData.getPromoPrice().get(automaticPackage - 1)),
                    "The promo price in checkout detail for Ulasan Instan isn't matched with the expected!");
        }
        if (isElementVisible("ulasan_instan_checkout_total_belanja_text", 3)) {
            swipeDownToElement("ulasan_instan_checkout_total_belanja_text");
            if (isElementVisible("ulasan_instan_checkout_price_text")) {
                assertTrue(getTextFromElement("ulasan_instan_checkout_total_belanja_text")
                                .contains(PRIOData.getPromoPrice().get(automaticPackage - 1)),
                        "The promo price in checkout detail payment section for Ulasan Instan isn't matched with the expected!");
            } else {
                assertTrue(getTextFromElement("ulasan_instan_checkout_total_belanja_text")
                                .contains(PRIOData.getNormalPrice().get(automaticPackage - 1)),
                        "The normal price in checkout detail payment section for Ulasan Instan isn't matched with the expected!");
            }
            swipeUpToElement("ulasan_instan_checkout_force_bd_button");
            verifyElementExist("ulasan_instan_checkout_force_bd_button");
            swipeDownToElement("ulasan_instan_checkout_metode_lain_button");
        }
    }

    private void checkPembayaranTab() {
        waitForVisibilityOf("ulasan_instan_checkout_other_pilih_metode_text");
        verifyElementExist("ulasan_instan_checkout_other_bukadompet_section");
        verifyElementExist("ulasan_instan_checkout_other_transfer_bank_section");
    }

    private void checkForceBDPage(int automaticPackage) {
        waitForVisibilityOf("ulasan_instan_checkout_force_bd_beli_pakai_bd_text", 20);
        verifyElementExist("ulasan_instan_checkout_force_bd_sisa_bd_text");
        verifyElementExist("ulasan_instan_checkout_force_bd_voucher_checkbox");
        verifyElementExist("ulasan_instan_checkout_bayar_sekarang_button");
        assertEquals("The price in checkout detail payment section for Ulasan Instan using Force BD isn't matched with the expected!",
                PRIOData.getRealPrice(automaticPackage), getTextFromElement("ulasan_instan_checkout_force_bd_payment_total_text"));
    }

    public void userVerifyDetailPayment(int automaticPackage) {
        swipeUpToElement("checkout_non_marketplace_payment_button", 10);
        waitForVisibilityOf("ulasan_instan_checkout_other_voucher_checkbox", 20);
        verifyElementExist("ulasan_instan_checkout_other_ulasan_instan_price");
        assertEquals(getTextFromElement("ulasan_instan_checkout_other_ulasan_instan_price"), dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + automaticPackage + "_PROMO_PRICE"));
    }

    public void payWithBukaDompet() {
        swipeUpToElement("ulasan_instan_checkout_force_bd_button");
        waitForVisibilityOf("ulasan_instan_checkout_force_bd_button", 20);
        tapElement("ulasan_instan_checkout_force_bd_button", 15);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
