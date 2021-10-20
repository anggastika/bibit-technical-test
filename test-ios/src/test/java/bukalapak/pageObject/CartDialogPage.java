package bukalapak.pageObject;

import bukalapak.data.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class CartDialogPage extends BasePage{

    public CartDialogPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTrayATC() {
        verifyElementExist("TRAY_ATC_TITLE", 40,"tray atc didn't present");
        HelperData.setLastActionPage(new CartDialogPage(iosDriver));
    }

    public void tapLihatKeranjang() {
        CartDialogData.setProductName(getElementValue("TRAY_ATC_PRODUCT_NAME"));
        waitForVisibilityOf("TRAY_ATC_LIHAT_KERANJANG_BTN", 20);
        tapElement("TRAY_ATC_LIHAT_KERANJANG_BTN");
    }

    public void validateTrayATCDetail(String trayATCComponent) {
        switch (trayATCComponent) {
            case "product name" :
                verifyElementExist("TRAY_ATC_PRODUCT_NAME", 10 , "Product name not shown");
                break;
            case "price" :
                verifyElementExist("TRAY_ATC_PRODUCT_PRICE", 10 , "Price not shown");
                break;
            case "quantity" :
                validateQty();
                break;
            case "icon delete" :
                verifyElementExist("TRAY_ATC_DELETE_PRODUCT_BTN", 10 , "Icon delete not shown");
                break;
            case "total price" :
                verifyElementExist("TRAY_ATC_TOTAL_PRICE", 10 , "Total price not shown");
                break;
            case "close button" :
                verifyElementExist("TRAY_ATC_CLOSE_BTN", 10 , "Close button not shown");
                break;
            case "Lihat Keranjang button" :
                verifyElementExist("TRAY_ATC_LIHAT_KERANJANG_BTN", 10 , "Lihat Keranjang button not shown");
                break;
            case "Lanjut Bayar button" :
                verifyElementExist("TRAY_ATC_BAYAR_BTN", 10 , "Lanjut Bayar button not shown");
                break;
            case "pre-order label" :
                verifyElementExist("TRAY_ATC_PRE_ORDER_LABEL", 10 , "pre-order label not shown");
                break;
            case "slashed price product" :
                verifyElementExist("TRAY_ATC_STRICE_PRICE_PRODUCT_TEXT", 10, "slased price product not shown");
                break;
            default:
                break;
        }
        HelperData.setLastActionPage(new CartDialogPage(iosDriver));
    }

    public void validateQty() {
        if (isElementExist("TRAY_ATC_LANJUT_BAYAR_BTN")) {
            assertEquals(getTextFromElement("TRAY_ATC_PRODUCT_QTY"), "1");
        } else {
            verifyElementExist("TRAY_ATC_PRODUCT_QTY", 10, "Quantity not shown");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new CartDialogPage(iosDriver));
    }

    private void getSlishedProductPrice() {
        if (isElementExist("TRAY_ATC_STRIKED_PRICE_PRODUCT_TEXT", 10)) {
            verifyElementExist("TRAY_ATC_STRIKED_PRICE_PRODUCT_TEXT");
            CheckoutData.setSlashedProductPrice(getIntFromRp(getText("TRAY_ATC_STRIKED_PRICE_PRODUCT_TEXT")));
        }
    }

    public void validateSlishedProductPrice() {
        verifyElementExist("TRAY_ATC_STRIKED_PRICE_PRODUCT_TEXT", 5, "Discount Price is not displayed");
    }

    private void validateVariantProduct() {
        if (isElementExist("TRAY_ATC_VARIANT_PRODUCT", 10)) {
            verifyElementExist("TRAY_ATC_VARIANT_PRODUCT");
            CheckoutData.setSlashedProductPrice(getIntFromRp(getText("TRAY_ATC_VARIANT_PRODUCT")));
        }
    }

    public void validateTrayATCPopup(String flag) {
        if (flag == null) {
            waitForVisibilityOf("TRAY_ATC_POPUP_TITLE", 40);
            if(isElementExist("TRAY_ATC_LANJUT_BAYAR_BTN")){
                verifyElementExist("TRAY_ATC_LANJUT_BAYAR_BTN");
            } else {
                verifyElementExist("TRAY_ATC_LIHAT_KERANJANG_BTN");
                verifyElementExist("TRAY_ATC_POPUP_BAYAR_BTN");
            }
            getSlishedProductPrice();
            verifyElementExist("TRAY_ATC_PRODUCT_NAME");
            verifyElementExist("TRAY_ATC_PRODUCT_PRICE");
            verifyElementExist("TRAY_ATC_PRODUCT_QTY");
            CartData.setItemPrice(convertPriceToInt(getText("TRAY_ATC_PRODUCT_PRICE")));
            CartData.setProductName(getTextFromElement("TRAY_ATC_PRODUCT_NAME"));
            CheckoutData.setProductName(getTextFromElement("TRAY_ATC_PRODUCT_NAME"));
            CheckoutData.setProductPrice(convertPriceToInt(getText("TRAY_ATC_PRODUCT_PRICE")));
            CheckoutData.setProductQuantity(getTextFromElement("TRAY_ATC_PRODUCT_QTY"));
            validateVariantProduct();
        } else {
            validateNotExist("TRAY_ATC_POPUP_TITLE", 5);
        }
        HelperData.setLastActionPage(new CartDialogPage(iosDriver));
    }

    public void tapRemoveIcon() {
        tapElement("TRAY_ATC_DELETE_PRODUCT_BTN");
    }

    public void tapLihatKeranjangBtn() {
        tapElement("TRAY_ATC_LIHAT_KERANJANG_BTN");
    }

    public void tapBayarBtn() {
        tapElement("TRAY_ATC_POPUP_BAYAR_BTN");
    }

    public void closeTrayAtc() {
        tapElement("TRAY_ATC_POPUP_CLOSE_BTN", 10);
    }

    public void updateQtyTrayATCPopup(String productName, String qty) {
        String locator = constructLocator("TRAY_ATC_POPUP_SPECIFIC_PRODUCT_QTY", productName);
        tapElement(locator);
        verifyElementExist("TRAY_ATC_POPUP_DELETE_NUM_KEYBOARD");
        nativeDoubleTap(locator);
        tapElement("TRAY_ATC_POPUP_DELETE_NUM_KEYBOARD");
        typeValue(locator, qty);
        hideKeyboard();
        CheckoutData.setProductQuantity(qty);
        verifyElementExist("TRAY_ATC_PRODUCT_PRICE", 10, "product price not displayed");
        CheckoutData.setProductPrice(convertPriceToInt(getText("TRAY_ATC_PRODUCT_PRICE")));
    }

    public void validateProductPriceGrosir() {
        waitForVisibilityOf("TRAY_ATC_PRODUCT_PRICE", 5);
        validateValue().notEquals(CartData.getItemPrice(),
                convertPriceToInt(getText("TRAY_ATC_PRODUCT_PRICE")));
    }

    public void validateProductQuantityOnTrayATC(String productName, String qty) {
        waitFor(5);
        String qtyActual = getElementValue("TRAY_ATC_NUMBER_FIELD");
        assertEquals(qty, qtyActual,"qty not updated");
        CheckoutData.setProductQuantity(qtyActual);
    }


    public void validatePopupDeleteProduct() {
        verifyElementExist("TRAY_ATC_POPUP_DELETE_PRODUCT", 10 , "delete pop up not shown");
        HelperData.setLastActionPage(new CartDialogPage(iosDriver));
    }

    public void tapDeleteOnPopup() {
        tapElement("TRAY_ATC_DELETE_PRODUCT_ON_POPUP");
    }

    public void tapDeleteAndFavOnPopup() {
        tapElement("TRAY_ATC_DELETE_AND_FAV_ON_POPUP");
    }

    public void tapCloseTrayATC() {
        tapElement("TRAY_ATC_POPUP_CLOSE_BTN");
    }

    public void validateVariantProduct(String variant, String product) {
        validateExist(constructLocator("TRAY_ATC_SPECIFIC_VARIANT_PRODUCT", product, variant));
        HelperData.setLastActionPage(new CartDialogPage(iosDriver));
    }

    public void tapLanjutBayarTrayATCFromBeliSekarangPDP(){
        waitForVisibilityOf("TRAY_ATC_LANJUT_BAYAR_BTN", 10);
        tapElement("TRAY_ATC_LANJUT_BAYAR_BTN");
    }

    public void validateErrorMsg(String productName, String errorMsg) {
        verifyElementExist(constructLocator("TRAY_ATC_ERROR_MSG", productName, errorMsg), 20 , "Error message " + errorMsg + " is not displayed");
    }

    public void removeSpecificProduct(String productName) {
        tapElement(constructLocator("TRAY_ATC_DELETE_SPECIFIC_PRODUCT_BTN", productName));
    }

    public void validateSpecificProduct(String productName, String flag) {
        if (flag == null) {
            validateExist(constructLocator("TRAY_ATC_SPECIFIC_PRODUCT_NAME", productName), 5);
        } else {
            validateNotExist(constructLocator("TRAY_ATC_SPECIFIC_PRODUCT_NAME", productName), 5);
        }
    }
}
