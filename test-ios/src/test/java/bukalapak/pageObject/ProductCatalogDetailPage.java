package bukalapak.pageObject;

import bukalapak.data.WOWData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Andi Maryono
 */

public class ProductCatalogDetailPage extends BasePage {

    public ProductCatalogDetailPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnProductCatalogDetailPage() {
        if (isElementVisible("catalog_detail_empty_catalog_message",30)) {
            verifyElementExist("catalog_detail_empty_catalog_message");
        } else {
            // revamp
            if (isElementVisible("product_catalog_detail_title",30)) {
                verifyElementExist("product_catalog_detail_title");
                verifyElementExist("catalog_detail_chat_button");
                verifyElementExist("catalog_detail_cart_button");
                verifyElementExist("catalog_detail_product_beli_sekarang_button");
                verifyElementExist("product_catalog_detail_title");
            } else {
                verifyElementExist("product_catalog_detail_title_old");
            }
        }

        HelperData.setLastActionPage(new ProductCatalogDetailPage(iosDriver));
    }

    public void checkCatalogDetailRating(String status) {
        switch (status) {
            case "has rating":
                assertTrue(isElementVisible("catalog_detail_rating"), "Catalog detail not displays catalog rating");
                break;
            case "has no rating":
                assertFalse(isElementVisible("catalog_detail_rating"), "Catalog detail displays catalog rating");
                break;
            default:
                Assert.fail("Invalid catalog detail rating status");
                break;
        }
    }

    private String getCatalogMedianPrice(String price_condition) {
        IOSElement medianPriceElement = null;
        String medianPrice;
        List<String> rawPrice;

        switch (price_condition) {
            case "Harga Baru":
                medianPriceElement = getElement("catalog_detail_new_price", 6);
                break;
            case "Harga Bekas":
                medianPriceElement = getElement("catalog_detail_used_price", 6);
                break;
            default:
                Assert.fail("Invalid catalog detail price condition");
                break;
        }

        if (medianPriceElement.getText().equals("Tidak Ada")) {
            medianPrice = medianPriceElement.getText();
        } else {
            rawPrice = Arrays.asList(medianPriceElement.getText().replace("Rp", "").split(" "));
            medianPrice = rawPrice.get(1);
        }

        return medianPrice;
    }

    public void verifyMedianPrice(DataTable arg0) {
        List<Map<String, String>> price = arg0.asMaps();

        assertTrue(price.get(0).get("NEW_PRICE").equals(getCatalogMedianPrice("Harga Baru")), "Harga Baru is not valid");
        assertTrue(price.get(0).get("USED_PRICE").equals(getCatalogMedianPrice("Harga Bekas")), "Harga Bekas is not valid");
    }

    public void verifyDescriptionContent(DataTable arg0) {
        List<String> description = arg0.asList();

        assertTrue(isElementVisible("catalog_detail_description_container"), "Description content didn't displayed");
        assertEquals(description.get(0), getText("catalog_detail_description_container"));
    }

    public void verifyNotVisibleContents(DataTable arg0) {
        List<String> contents = arg0.asList();

        for (String content : contents) {
            switch (content) {
                case "super seller badge":
                    assertFalse(isElementVisible("catalog_detail_product_recomendation_premium_badge"), "Super seller badge is displayed");
                    break;
                case "bukamall badge":
                    assertFalse(isElementVisible("catalog_detail_product_recomendation_bukamall_badge"), "Merchant Bukamall badge is displayed");
                    break;
                default:
                    Assert.fail("Invalid content");
                    break;
            }
        }
    }

    public void verifyVisibleContents(DataTable arg0) {
        List<String> contens = arg0.asList();

        for (String content : contens) {
            switch (content) {
                case "super seller badge":
                    assertTrue(isElementVisible("catalog_detail_product_recomendation_premium_badge"), "Super seller badge did not displayed");
                    break;
                case "bukamall badge":
                    assertTrue(isElementVisible("catalog_detail_product_recomendation_bukamall_badge"), "Merchant Bukamall badge is not displayed");
                    break;
                case "discount price":
                    assertTrue(isElementVisible("catalog_detail_product_recomendation_price"), "Discount price did not displayed");
                    break;
                case "gratis ongkir label":
                    assertTrue(isElementVisible("catalog_detail_product_recomendation_benefit_btn"), "Gratis ongkir did not displayed");
                    break;
                case "garansi and benefit":
                    assertTrue(isElementVisible("catalog_detail_product_recomendation_benefit_btn"), "Label did not displayed");
                    break;
                default:
                    Assert.fail("Invalid content");
                    break;
            }
        }
    }

    private String getProductCatalogTitle() {
        return getTextFromElement("product_catalog_detail_title");
    }

    private String getPromotedProductTitle() {
        return getTextFromElement("catalog_detail_product_recomendation_title");
    }

    public void clickAtPromotedProduct(String element) {
        if (isElementClickable("catalog_detail_product_recomendation_label")) {
            switch (element) {
                case "TITLE":
                    tapElement("catalog_detail_product_recomendation_title");
                    break;
                case "BELI_BUTTON":
                    tapElement("catalog_detail_product_recomendation_beli_button");
                    break;
                default:
                    Assert.fail("Element is not part of promoted product content");
                    break;
            }
        } else {
            Assert.fail("Product recomendation didn't displayed and not clickable");
        }
    }

    public void verifyNoteInCheckoutMarketplace(DataTable arg0) {
        List<String> buttons = arg0.asList();
        String product_recomendation_title = constructLocator("product_recomendation_title",getProductCatalogTitle());
        String assertion_message = "Note is not displayed product recomendation title";
        CheckoutMarketplacePage checkoutMarketplacePage = new CheckoutMarketplacePage(iosDriver);

        switch (buttons.get(0)) {
            case "Beli":
                clickAtPromotedProduct("BELI_BUTTON");
                clickBuyOnVariantProduct();
                checkoutMarketplacePage.userOnCheckoutPage();
                assertTrue(isElementClickable(product_recomendation_title), assertion_message);
                break;
            case "Beli Sekarang":
                tapElement("catalog_detail_product_beli_sekarang_button");
                clickBuyOnVariantProduct();
                checkoutMarketplacePage.userOnCheckoutPage();
                assertTrue(isElementClickable(product_recomendation_title), assertion_message);
                break;
            default:
                Assert.fail("Invalid button");
                break;
        }
    }

    public void clickBuyOnVariantProduct() {
        if (isElementVisible("catalog_detail_choose_variant_text")) {
            tapElement("catalog_detail_product_recomendation_beli_button");
        }
    }

    public void verifyPromotedProductInChatRoom() {
        String product_recomendation_title = constructLocator("prom_product_recomendation_title",getPromotedProductTitle());

        tapElement("catalog_detail_chat_button");
        new ChatRoomPage(iosDriver).userOnChatRoom();
        verifyElementExist(product_recomendation_title);
    }

    public void verifyProceedToCheckout() {
        String product_recomendation_title = constructLocator("prom_product_recomendation_title",getPromotedProductTitle());
        CheckoutMarketplacePage checkoutMarketplacePage =  new CheckoutMarketplacePage(iosDriver);

        tapElement("catalog_detail_cart_button");
        clickBuyOnVariantProduct();

        if (isElementClickable("catalog_detail_product_lanjut_pay")) {
            assertTrue(isElementVisible(product_recomendation_title), "Promoted product in cart modal didn't displayed");

            checkoutMarketplacePage.clickLanjutKePembayaran();
            checkoutMarketplacePage.userOnCheckoutPage();
            verifyElementExist(product_recomendation_title);
        } else {
            Assert.fail("Cart modal didn't displayed");
        }
    }

    public void verifyPromotedProductContent() {
        if (isElementClickable("catalog_detail_product_recomendation_label")) {
            verifyElementExist("catalog_detail_product_recomendation_label");
            verifyElementExist("catalog_detail_product_recomendation_title");
            verifyElementExist("catalog_detail_product_recomendation_price");
            verifyElementExist("catalog_detail_product_recomendation_location");
            verifyElementExist("catalog_detail_product_recomendation_merchant");
            verifyElementExist("catalog_detail_product_recomendation_beli_button");
        } else {
            Assert.fail("Product recomendation didn't displayed and not clickable");
        }
    }

    public void chooseVariantOption(Integer index) {
        List<WebElement> elements = getElements("catalog_detail_variant_option", 6);

        if (elements != null) {
            elements.get(index - 1).click();
            tapElement("catalog_detail_variant_apply_button");
        } else {
            Assert.fail("Variant didn't displayed");
        }
    }

    public void verifyCatalogName(String catalog_name) {
        String productCatalogTitle = getProductCatalogTitle();
        assertTrue(productCatalogTitle.contains(catalog_name), "Invalid product catalog name");
    }

    public void scrollDownToUlasanBarang() {
        nativeSwipeUpToElement("catalog_detail_description_text");
    }

    public void verifyReviewVisibility(String status) {
        switch (status) {
            case "VISIBLE":
                verifyElementExist("catalog_detail_ulasan_barang_text");
                break;
            case "NOT_VISIBLE":
                verifyElementNotExist("catalog_detail_ulasan_barang_text");
                break;
            default:
                Assert.fail("Invalid review visibility status");
                break;
        }
    }

    public void goToHomePage() {
        backToHomePage();
        if (isElementVisible("back_button_produt_listing_revamp")) {
            tapElement("back_button_produt_listing_revamp");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapLihatBarangLainnya() {
        WOWData.setCatalogTitle(getProductCatalogTitle());
        swipeDownToClickableElement("catalog_detail_other_product_text");
        tapElement("catalog_detail_other_product_text");
    }

    public void verifyBadgeModal(String badge) {
        verifyElementExist("catalog_detail_product_recomendation_benefit_modal");
        switch (badge) {
            case "free shipping BL":
                verifyElementExist("catalog_detail_product_recomendation_benefit_text");
                verifyElementExist("catalog_detail_product_recomendation_free_shipping_text");
                verifyElementExist("catalog_detail_product_recomendation_free_shipping_content");
                verifyElementExist("catalog_detail_product_recomendation_voucher_code_text");
                verifyElementExist("catalog_detail_product_recomendation_copy_voucher_text");
                break;
            case "free shipping seller":
                verifyElementExist("catalog_detail_product_recomendation_benefit_text");
                verifyElementExist("catalog_detail_product_recomendation_free_shipping_seller_text");
                verifyElementExist("catalog_detail_product_recomendation_free_shipping_seller_content");
                break;
            case "product guarantee":
                verifyElementExist("catalog_detail_product_recomendation_benefit_guarantee_text");
                verifyElementExist("catalog_detail_product_recomendation_guarantee_text");
                verifyElementExist("catalog_detail_product_recomendation_guarantee_content");
                verifyElementExist("catalog_detail_product_recomendation_guarantee_selengkapnya_text");
                break;
            case "rocket delivery":
                verifyElementExist("catalog_detail_product_recomendation_benefit_guarantee_text");
                verifyElementExist("catalog_detail_product_recomendation_rocket_delivery_text");
                verifyElementExist("catalog_detail_product_recomendation_rocket_delivery_content");
                break;
            default:
                Assert.fail("Invalid badge");
                break;
        }
        tapElement("catalog_detail_product_recomendation_benefit_modal_close_btn");
    }

    public void tapCartIcon(){
        waitForVisibilityOf("catalog_detail_cart_button");
        tapElement("catalog_detail_cart_button");
    }

    public void verifyCartModal(){
        waitForVisibilityOf("cart_dialog");
    }

}
