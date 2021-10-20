package bukalapak.pageObject;

import bukalapak.data.ChampionData;
import bukalapak.data.HelperData;
import bukalapak.data.MOPData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ProductDetailFlashDealPage extends BasePage {
    public ProductDetailFlashDealPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateProductDetailFlashDealPageDisplayed() {
        verifyElementExist("FLASH_DEAL_PRODUCT_IMAGE", 10, "Product image is not displayed");
        verifyElementExist("FLASH_DEAL_PRODUCT_STOCK");
    }

    public void validateFDProductName(){
        verifyElementExist("FLASH_DEAL_PRODUCT_NAME");
    }

    public void validateFDStock(){
        verifyElementExist("FLASH_DEAL_PRODUCT_STOCK");
    }

    public void validateProductInfo(){
        swipeUpToElement("FLASH_DEAL_INFORMASI_BARANG_LIHAT_SELENGKAPNYA_TEXT");
        verifyElementExist("FLASH_DEAL_INFORMASI_BARANG_TITLE");
        verifyElementExist("FLASH_DEAL_INFORMASI_BARANG_KONDISI_TEXT");
        verifyElementExist("FLASH_DEAL_INFORMASI_BARANG_BERAT_TEXT");
        verifyElementExist("FLASH_DEAL_INFORMASI_BARANG_DESKRIPSI_TEXT");
    }

    public void tapLihatSemuanya(){
        swipeUpToElement("FLASH_DEAL_PELAPAK_NAME");
        tapElement("FLASH_DEAL_INFORMASI_BARANG_LIHAT_SELENGKAPNYA_TEXT");
    }

    public void validateInformasiBarangPopup(){
        verifyElementExist("FLASH_DEAL_INFORMASI_BARANG_POPUP_TITLE");
        verifyElementExist("FLASH_DEAL_INFORMASI_BARANG_POPUP_KONDISI_TEXT");
        verifyElementExist("FLASH_DEAL_INFORMASI_BARANG_POPUP_BERAT_TEXT");
        verifyElementExist("FLASH_DEAL_INFORMASI_BARANG_POPUP_DESKRIPSI_TITLE");
        verifyElementExist("FLASH_DEAL_INFORMASI_BARANG_POPUP_DESKRIPSI_TEXT");
    }

    public void tapCloseButtonOnInformasiBarangPopup(){
        verifyElementExist("FLASH_DEAL_INFORMASI_BARANG_POPUP_CLOSE_BUTTON");
        tapElement("FLASH_DEAL_INFORMASI_BARANG_POPUP_CLOSE_BUTTON");
    }

    public void validatePelapakInfo(){
        swipeUpToElement("FLASH_DEAL_PELAPAK_INFO_SECTION");
        verifyElementExist("FLASH_DEAL_PELAPAK_NAME");
        verifyElementExist("FLASH_DEAL_PELAPAK_INFO_SECTION");
    }

    public void tapPelapakInfoSection(){
        swipeUpToElement("FLASH_DEAL_PELAPAK_INFO_SECTION");
        tapElement("FLASH_DEAL_PELAPAK_NAME");
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateProductReviewSection() {
        verifyElementExist("FLASH_DEAL_ULASAN_RATING");
        swipeUpToElement("FLASH_DEAL_ULASAN_BARANG_TITLE");
        verifyElementExist("FLASH_DEAL_ULASAN_BARANG_TITLE");
        verifyElementExist("FLASH_DEAL_ULASAN_LAINNYA_BUTTON");
        verifyElementExist("FLASH_DEAL_TOTAL_ULASAN_TEXT");
    }

    public void validateProductReviewBehaviour() {
        tapUlasanLainnyaButton();
        isOnUlasanPage();
        tapElement("FLASH_DEAL_ULASAN_BARANG_BACK_BUTTON");
    }

    public void tapUlasanLainnyaButton() {
        tapElement("FLASH_DEAL_ULASAN_LAINNYA_BUTTON");
    }

    public void isOnUlasanPage() {
        verifyElementExist("FLASH_DEAL_ULASAN_BARANG_PAGE");
    }

    public void validateProductRecommendationSection() {
        swipeUpToElement("FLASH_DEAL_REKOMENDASI_TITLE");
        try {
            verifyElementExist("FLASH_DEAL_REKOMENDASI_BELI_BUTTON");
        } catch (AssertionError e) {
            LogUtil.info("FLASH_DEAL_REKOMENDASI_BELI_BUTTON is covered by the sticky buy button, trying to swipe a little more");
        } finally {
            verifyElementExist("FLASH_DEAL_REKOMENDASI_TITLE");
            verifyElementExist("FLASH_DEAL_REKOMENDASI_PRODUCT_IMAGE");
        }
    }

    public void tapProductRecommendation() {
        tapElement("FLASH_DEAL_REKOMENDASI_PRODUCT_IMAGE");
    }

    public void tapBeliButtonOnProductRecommendation() {
        swipeUpToElement("FLASH_DEAL_REKOMENDASI_TITLE");
        tapElement("FLASH_DEAL_REKOMENDASI_BELI_BUTTON");
        verifyElementExist("FLASH_DEAL_ATC_POP_UP");
        tapElement("FLASH_DEAL_ATC_CLOSE_BUTTON");
    }

    public void validateProductRelatedSection() {
        swipeUpToElement("FLASH_DEAL_BARANG_TERKAIT_TITLE");
        verifyElementExist("FLASH_DEAL_BARANG_TERKAIT_TITLE");
        verifyElementExist("FLASH_DEAL_SELENGKAPNYA_TEXT");
        swipeUpToElement("FLASH_DEAL_BARANG_TERKAIT_BELI_BUTTON");
        verifyElementExist("FLASH_DEAL_BARANG_TERKAIT_PRODUCT_IMAGE");
        verifyElementExist("FLASH_DEAL_BARANG_TERKAIT_BELI_BUTTON");
    }

    public void validateProductRelatedBehaviour() {
        tapSelengkapnyaOnBarangTerkait();
        isOnBarangTerkaitPage();
        tapBeliButtonOnProductRelated();
    }

    public void tapProductRelated(){
        tapElement("FLASH_DEAL_BARANG_TERKAIT_PRODUCT_IMAGE");
    }

    public void tapSelengkapnyaOnBarangTerkait(){
        tapElement("FLASH_DEAL_SELENGKAPNYA_TEXT");
    }

    public void isOnBarangTerkaitPage() {
        verifyElementExist("FLASH_DEAL_BARANG_TERKAIT_PAGE");
        tapElement("FLASH_DEAL_BARANG_TERKAIT_BACK_BUTTON");
    }

    public void tapBeliButtonOnProductRelated() {
        tapElement("FLASH_DEAL_BARANG_TERKAIT_BELI_BUTTON");
        verifyElementExist("FLASH_DEAL_ATC_POP_UP");
        tapElement("FLASH_DEAL_ATC_CLOSE_BUTTON");
    }

    public void tapPDPBackButton() {
        tapElement("FLASH_DEAL_PDP_BACK_BUTTON");
    }

    public void validatePDPTimer(String state) {
        waitForVisibilityOf("FLASH_DEAL_COUNTDOWN_TITLE", 10);
        String countDownTitle = getText("FLASH_DEAL_COUNTDOWN_TITLE");

        LogUtil.info("Displayed PDP count down title is : \"" + countDownTitle + "\"");
        if (state.equals("ongoing")) {
            validateValue().equals("Berakhir dalam", countDownTitle);
            validateOngoingTimerSticky();
        } else {
            validateValue().equals("Dimulai dalam", countDownTitle);
            validateUpcomingTimerSticky();
        }
    }

    public void validateOngoingTimerSticky() {
        swipeUpToElement("FLASH_DEAL_PELAPAK_INFO_SECTION");
        validateExist("FLASH_DEAL_BERAKHIR_DALAM_STICKY_TEXT");
        validateExist("FLASH_DEAL_STICKY_COUNTDOWN");
    }

    public void validateUpcomingTimerSticky() {
        swipeUpToElement("FLASH_DEAL_PELAPAK_INFO_SECTION");
        validateExist("FLASH_DEAL_DIMULAI_DALAM_STICKY_TEXT");
        validateExist("FLASH_DEAL_STICKY_COUNTDOWN");
    }

    public void validatePDPPrice(String state) {
        validateExist("FLASH_DEAL_ORIGINAL_PRICE", 30);
        validateValue().equalsFalse(getText("FLASH_DEAL_ORIGINAL_PRICE").contains("?"));
        String flashPrice = getText("FLASH_DEAL_DISCOUNT_PRICE");

        LogUtil.info("Displayed price is : " + flashPrice);
        if (state.equals("masked")) {
            validateValue().contains("?", flashPrice);
        } else {
            validateValue().equalsFalse(flashPrice.contains("?"));
        }
    }

    public void tapBackButton() {
        waitForVisibilityOf("FLASH_DEAL_BACK_BUTTON", 10);
        tapElement("FLASH_DEAL_BACK_BUTTON");
        HelperData.setLastActionPage(new ProductDetailFlashDealPage(iosDriver));
    }

    public void tapBeliButton() {
        MOPData.setFlashDealPricePdp(getElementValue("FLASH_DEAL_DISCOUNT_PRICE"));
        verifyElementExist("flash_deal_beli_button");
        tapElement("flash_deal_beli_button");
    }

    public void proceedToFDCheckoutPage() {
        if(isElementVisible("peninjauan_barang_title", 10) || isElementVisible("pilih_variant_barang_title", 10)) {
            tapElement("flash_deal_bayar_button");
        }
    }

    public void userOnFDCheckoutPage() {
        verifyElementExist("checkout_marketplace_flash_deal_price");
        MOPData.setFlashDealPriceCheckout(getElementValue("checkout_marketplace_flash_deal_price"));
        HelperData.setLastActionPage(new ProductDetailFlashDealPage(iosDriver));
    }

    public void validateFlashDealPriceonCheckout() {
         LogUtil.info("Product Price Product Detail = " + MOPData.getFlashDealPricePdp());
         LogUtil.info("Total Product Price = " + MOPData.getFlashDealPriceCheckout());
         assertEquals(MOPData.getFlashDealPricePdp(),MOPData.getFlashDealPriceCheckout());
         HelperData.setLastActionPage(new ProductDetailFlashDealPage(iosDriver));
    }

    public void tapAddToCartFD() {
        waitForVisibilityOf("flash_deal_add_to_cart_button", 10);
        tapElement("flash_deal_add_to_cart_button");
        MOPData.setFlashDealPricePdp(getElementValue("flash_deal_discount_price_text"));
    }

    public void validateATCPopup() {
        validateExist("flash_deal_add_to_cart_popup", 10);
    }

    public void validateProductAddedToCart() {
        validateExist("flash_deal_add_to_cart_name");
        validateExist("flash_deal_add_to_cart_price");
        MOPData.setFlashDealPriceCart(getText("flash_deal_add_to_cart_price"));

        int atcPrice = ChampionData.priceStringToInteger(MOPData.getFlashDealPriceCart());
        int pdpPrice = ChampionData.priceStringToInteger(MOPData.getFlashDealPricePdp());

        assertEquals (pdpPrice, atcPrice);
    }

    public void validateFlashDealTagOnATC() {
        validateExist("flash_deal_add_to_cart_tag", 5);
        validateExist("flash_deal_add_to_cart_countdown", 5);
        HelperData.setLastActionPage(new ProductDetailFlashDealPage(iosDriver));
    }

    public void validateRatingAndReview() {
        validateExist("FLASH_DEAL_ULASAN_RATING");
    }

    public void tapRatingAndReview() {
        tapElement("FLASH_DEAL_ULASAN_RATING");
    }

    public void validateFDShippingInfo() {
        swipeUpToElement("FLASH_DEAL_ULASAN_BARANG_TITLE");
        validateExist("flash_deal_shipping_info");
    }

    public void tapShippingInfo() {
        tapElement("flash_deal_shipping_info");
    }

    public void validateShippingPopUp() {
        validateExist("flash_deal_shipping_popup_title");
        validateExist("flash_deal_lokasi_pengiriman_text");
        validateExist("flash_deal_jumlah_barang_text");
        validateExist("flash_deal_input_quantity");
    }

    public void tapCloseButtonOnShippingPopup() {
        waitForVisibilityOf("flash_deal_shipping_pop_up_close_button", 10);
        tapElement("flash_deal_shipping_pop_up_close_button");
        HelperData.setLastActionPage(new ProductDetailFlashDealPage(iosDriver));
    }
}
