package bukalapak.pageObject;

import bukalapak.data.*;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CartPage extends BasePage {

    public CartPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnCartPage() {
        waitForVisibilityOf("cart_page_title", 10);
        verifyElementExist("cart_page_title");
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void clearCart() {
        if (!isElementVisible("cart_page_hapus_button")) {
            if (isElementVisible("cart_page_pilih_semua_checklist")) {
                clickPilihSemua();
                tapElement("cart_page_hapus_button");
                tapElement("cart_page_konfirmasi_hapus_button");
            }
        } else {
            tapElement("cart_page_hapus_button");
            tapElement("cart_page_konfirmasi_hapus_button");
        }
        waitFor(5);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void clickCartMenu() {
        /* Change to this method, because there are many id for cart, example for btp test cases, cart  is in index 6, but for another is 5*/
        //if there is better method, it's ok to be changed
        List<MobileElement> table = getElementTable("XCUIElementTypeNavigationBar", "XCUIElementTypeButton");
        table.get(table.size() - 1).click();// cart is in last index
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void clickPilihSemua() {
        waitForVisibilityOf("cart_page_pilih_semua_checklist", 30);
        tapElement("cart_page_pilih_semua_checklist");
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void clickBayar() {
        waitForElementClickable("cart_page_bayar_button", 40);
        tapElement("cart_page_bayar_button", 25);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void clickBack() {
        tapElement("base_back_button");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public String getBiayaAdmin() {
        String biayaAdmin = getTextFromElement("cart_page_biaya_admin_text").replace(".", "").replace("Biaya administrasi: Rp", "");
        TransactionData.setAdminFee(biayaAdmin);
        return biayaAdmin;
    }

    private void increaseQuantity(int n) {
        for (int i = 0; i < n; i++) {
            tapElement("plus_button");
        }
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    private double getCountSubTotalBill() {
        waitForVisibilityOf("cart_page_sub_total_bill", 10);
        String subtotalBill = getTextFromElement("cart_page_sub_total_bill").replace(".", "").
                replace("Rp", "");
        double fee = Double.parseDouble(subtotalBill);
        LogUtil.info("subtotal jadi = " + fee);
        return fee;
    }

    public void deleteItemsOneByOne() {
        Object[] items = CartData.getItems();
        for (int i = 0; i < items.length; i++) {
            LogUtil.info("items yang mau dihapus bernama..." + items[i]);
            tapElement("cart_page_hapus_icon_button");
            tapElement("cart_page_konfirmasi_hapus_button");
            swipeToDirection(Direction.UP);
            assertFalse(isElementVisible(String.valueOf(By.name(items[i].toString()))));
        }
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    private void decreaseQuantity(int n) {
        for (int i = 0; i < n; i++) {
            tapElement("minus_button");
        }
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void checkRecommendationPanelAppear() {
        swipeUpToElement("cart_page_reco_panel", 7);
        assertTrue(isElementVisible("cart_page_reco_panel"), "Recommendation panel is not appear");
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void modifyQuantityAndRecheckPrice() {
        double bill, check;
        ItemData itemData;
        itemData = API_CALL.getFirstItemFromCart();
        if (itemData.getItemStock() > 1) {
            increaseQuantity(1);
            bill = (itemData.getItemPrice() * 2);
            check = getCountSubTotalBill();
            assertEquals(bill, check, 0.1);

            decreaseQuantity(1);
            bill = bill - (itemData.getItemPrice());
            check = getCountSubTotalBill();
            assertEquals(bill, check, 0.1);
        } else {
            bill = itemData.getItemPrice();
            check = getCountSubTotalBill();
            assertEquals(bill, check, 0.1);
        }
    }

    public void userVerifyAllItemsAndSellers() {
        Object[] sellers = API_CALL.getSellersInCart();
        Object[] items = API_CALL.getCartItemsName();
        String elm_seller;
        String elm_item;

        for (Object seller : sellers) {
            elm_seller = constructLocator("cart_page_elm_seller", seller.toString());
            LogUtil.info("ada seller yang bernama..." + seller);
            verifyElementExist(elm_seller);
        }

        for (Object item : items) {
            elm_item = constructLocator("cart_page_elm_item", item.toString());
            LogUtil.info("ada items yang bernama..." + item);
            verifyElementExist(elm_item);
        }
        CartData.setSellers(sellers);
        CartData.setItems(items);
    }

    public void addToFavoritePage() {
        waitForVisibilityOf("delete_product_and_add_to_favorite", 10);
        tapElement("delete_product_and_add_to_favorite");
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    private void verifySellerName(String sellerName) {
        waitForVisibilityOf("cart_page_title", 20);
        String elm_sellerName = constructLocator("cart_page_elm_sellerName", sellerName);
        swipeUpToElement(elm_sellerName, 5);
        validateDisplayed(elm_sellerName);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    private void verifyProductName(String productName) {
        String elm_productName = constructLocator("cart_page_elm_productName", productName);
        swipeUpToElement(elm_productName, 5);
        validateDisplayed(elm_productName);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    private void verifyProductPrice(String productName) {
        String elm_prodPrice = constructLocator("cart_page_elm_prodPrice", productName);
        swipeUpToElement(elm_prodPrice, 5);
        validateDisplayed(elm_prodPrice);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void verifyProductDetailOnCart(ArrayList<Map<String, Object>> cartDetail) {
        cartDetail.forEach(cd -> {
                verifySellerName(cd.get("STORE_NAME").toString());
                verifyProductName(cd.get("PRODUCT_NAME").toString());
                verifyProductPrice(cd.get("PRODUCT_NAME").toString());
            }
        );
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void verifyDiscountLabel() {
        isElementVisible("cart_page_discount_label", 20);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    private int getTotalBill() {
        String totalBill = getTextFromElement("cart_page_total_bill").replace(".", "").
                replace("Rp", "");
        int totalPrice = Integer.parseInt(totalBill);
        LogUtil.info("Total Belanja = " + totalPrice);
        return totalPrice;
    }

    public void verifyTotalPrice(ArrayList<Map<String, Object>> selectedItem) {
        int totalPrice = 0;
        for (Map<String, Object> item : selectedItem) {
            totalPrice += Integer.parseInt(item.get("QTY").toString()) * Integer.parseInt(item.get("ITEM_PRICE").toString());
        }
        isElementVisible("cart_page_total_bill", 20);
        int currentTotal = getTotalBill();
        assertEquals(Integer.valueOf(totalPrice), Integer.valueOf(currentTotal));
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void selectItem(String itemName) {
        String itemNameLocator = "checkbox_cart_item_" + itemName + "_unchecked";
        String elm_itemNameLocator = constructLocator("cart_page_elm_itemNameLocator", itemNameLocator);
        //swipeDownToElement(By.name(itemNameLocator));
        swipeDownToElement(elm_itemNameLocator);
        tapElement(elm_itemNameLocator);
        ArrayList<Map<String, Object>> selectedItem = CartData.getSelectedItemOnCart();
        ArrayList<Map<String, Object>> allItemOnCart = CartData.getCartDetails();
        Map<String, Object> itemDetail = allItemOnCart
                .stream()
                .filter(i -> i.get("PRODUCT_NAME").equals(itemName))
                .findFirst()
                .get();
        selectedItem.add(itemDetail);
        CartData.setSelectedItemOnCart(selectedItem);
        HelperData.setLastActionPage(new CartPage(iosDriver));

    }

    public void updateItemQuantity(String productName, String update, int quantity) {
        ArrayList<Map<String, Object>> allItemOnCart = CartData.getCartDetails();
        Map<String, Object> itemDetail = allItemOnCart
                .stream()
                .filter(i -> i.get("PRODUCT_NAME").equals(productName))
                .findFirst()
                .get();
        if (update.equals("add")) {
            IntStream.range(0, quantity).forEach(tap -> {
                String addQtyByProductName = constructLocator("cart_page_upd_quant_plus", productName);
                waitForVisibilityOf(addQtyByProductName, 20);
                tapElement(addQtyByProductName);
            });
            itemDetail.replace("QTY", Integer.parseInt(itemDetail.get("QTY").toString()) + quantity);
        } else if (update.equals("substract")) {
            IntStream.range(0, quantity).forEach(tap -> {
                String substractQtyByProductName = constructLocator("cart_page_upd_quant_minus", productName);
                waitForVisibilityOf(substractQtyByProductName, 20);
                tapElement(substractQtyByProductName);
            });
            itemDetail.replace("QTY", Integer.parseInt(itemDetail.get("QTY").toString()) - quantity);
        }
        List<Map<String, Object>> tempItemOnCart = allItemOnCart
                .stream()
                .filter(i -> !i.get("PRODUCT_NAME").equals(productName))
                .collect(Collectors.toList());
        tempItemOnCart.add(itemDetail);
        ArrayList<Map<String, Object>> tempArray = new ArrayList<Map<String, Object>>(tempItemOnCart);
        CartData.setCartDetails(tempArray);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void validateItemPriceChanged(ArrayList<Map<String, Object>> cartDetail) {
        int normalTotalPrice = 0;
        for (Map<String, Object> item : cartDetail) {
            normalTotalPrice += Integer.parseInt(item.get("QTY").toString()) * Integer.parseInt(item.get("ITEM_PRICE").toString());
        }
        isElementVisible("cart_page_sub_total_bill", 20);
        double getprice = getCountSubTotalBill();
        int price = (int) getprice;
        assertNotSame(price, normalTotalPrice);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void tapOnName(String name) {
        verifyElementExist(constructLocator("cart_page_elm_seller", name));
        tapElement(constructLocator("cart_page_elm_seller", name));
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void tapOnProductName(String productName) {
        verifyElementExist(constructLocator("cart_page_elm_product", productName));
        tapElement(constructLocator("cart_page_elm_product", productName));
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void tapOnProductImageName(String productName) {
        verifyElementExist(constructLocator("cart_page_elm_product_image", productName));
        tapElement(constructLocator("cart_page_elm_product_image", productName));
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    private void setProductNameInCart(String productName) {
        waitForVisibilityOf(constructLocator("cart_page_elm_nameitem", productName));
        ProductDetailData.setProductName(getElementValue(constructLocator("cart_page_elm_nameitem", productName)));
    }

    public void tapDeleteIconPerItem(String itemName) {
        setProductNameInCart(itemName);
        String deleteIcon = constructLocator("cart_page_elm_deleteIconLocator", itemName);

        verifyElementExist(deleteIcon, 3, "delete icon for specific item not found");
        swipeUpToElement(deleteIcon);
        tapElement(deleteIcon, 5);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void tapDeletePerItem(String itemName) {
        swipeUpToElement(constructLocator("cart_page_delete_btn_item", itemName));
        verifyElementExist(constructLocator("cart_page_delete_btn_item", itemName));
        tapElement(constructLocator("cart_page_delete_btn_item", itemName));
        MtxData.setProductName(itemName);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void verifyPopupHapusBarang() {
        isElementVisible("cart_page_hapus_item_popup", 20);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void clickHapusBarangButton() {
        isElementVisible("cart_page_hapus_button_on_popup", 20);
        tapElement("cart_page_hapus_button_on_popup");
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void clickHapusBarangToFavouriteButton() {
        isElementVisible("cart_page_hapus_simpan_favourite_button", 20);
        tapElement("cart_page_hapus_simpan_favourite_button");
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void verifyNotificationSuccess(String notif) {
        isElementVisible("cart_page_green_alert", 20);
        String elm_notif = constructLocator("cart_page_elm_notif", notif);
        isElementVisible(elm_notif);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void verifyDeletedItem(String itemName) {
        String elm_item = constructLocator("cart_page_elm_item", itemName);
        verifyElementNotExist(elm_item);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void verifySubTotalPerStore(ArrayList<Map<String, Object>> cartDetail, String storeName) {
        int subTotal = 0;
        List<Map<String, Object>> storeItemOnCart = cartDetail
                .stream()
                .filter(cart ->
                        cart.get("STORE_NAME").toString().equals(storeName)
                ).collect(Collectors.toList());
        for (Map<String, Object> item : storeItemOnCart) {
            subTotal += Integer.parseInt(item.get("QTY").toString()) * Integer.parseInt(item.get("ITEM_PRICE").toString());
        }
        String subTotalLocatorByStore = "//XCUIElementTypeStaticText[@name='" + storeName + "']/ancestor::XCUIElementTypeCell/following-sibling::XCUIElementTypeCell/descendant::XCUIElementTypeStaticText[@label='Subtotal' or @label='SUBTOTAL']/following-sibling::XCUIElementTypeStaticText[contains(@label, 'Rp')]";
        String elm_subTotalLocatorByStore = constructLocator("cart_page_elm_subTotalLocatorByStore", subTotalLocatorByStore);
        waitForVisibilityOf(elm_subTotalLocatorByStore, 20);
        String getcurrSubTotal = getElementValue(elm_subTotalLocatorByStore).replaceAll("[^0-9]", "").
                replace("Rp", "");
        int currSubTotal = Integer.parseInt(getcurrSubTotal);
        assertEquals(subTotal, currSubTotal);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void setItemQuantity(String productName, String quantity) {
        ArrayList<Map<String, Object>> allItemOnCart = CartData.getCartDetails();
        Map<String, Object> itemDetail = allItemOnCart
                .stream()
                .filter(i -> i.get("PRODUCT_NAME").equals(productName))
                .findFirst()
                .get();
        waitForVisibilityOf(constructLocator("cart_page_elm_update_qty_by_product_name", productName), 20);
        typeAndEnterValueWithTimeOut(constructLocator("cart_page_elm_update_qty_by_product_name", productName), quantity);
        itemDetail.replace("QTY", quantity);
        List<Map<String, Object>> tempItemOnCart = allItemOnCart
                .stream()
                .filter(i -> !i.get("PRODUCT_NAME").equals(productName))
                .collect(Collectors.toList());
        tempItemOnCart.add(itemDetail);
        ArrayList<Map<String, Object>> tempArray = new ArrayList<Map<String, Object>>(tempItemOnCart);
        CartData.setCartDetails(tempArray);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void verifyInvalidItemAlertMsg(String msg) {
        try {
            isElementVisible(constructLocator("cart_page_elm_msg", msg));
        } catch (AssertionError e) {
            doPullRefresh(1);
            isElementVisible(constructLocator("cart_page_elm_msg", msg));
        }
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void verifyBayarButtonNotAppear() {
        verifyElementNotExist("cart_page_bayar_button");
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void validateLeftoverItem(String productName, String amount) {
        String alertMessage = "Stok sisa " + amount;
        String alertStockLocator = "//XCUIElementTypeStaticText[@name='" + productName + "']/following-sibling::XCUIElementTypeStaticText[@name='" + alertMessage + "']";
        isElementVisible(constructLocator("cart_page_elm_alertStockLocator", alertStockLocator));
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void validateProductQuantity(int qty, String productName) {
        String qtyPerProductLocator = "//XCUIElementTypeStaticText[@name='" + productName + "']/preceding-sibling::XCUIElementTypeTextField[@value='" + qty + "']";
        isElementVisible(constructLocator("cart_page_elm_qtyPerProductLocator", qtyPerProductLocator));
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void validatePopUpKonfirmasiPembayaran() {
        isElementVisible("cart_page_popup_konfirmasi_pembayaran", 20);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void validateInvalidItemQty(int qty) {
        String invalidtextLocator = "//XCUIElementTypeStaticText[@name='textLabel'][contains(@label,'" + qty + "')]";
        isElementVisible(constructLocator("cart_page_elm_invalidtextLocator", invalidtextLocator));
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void validateHapusText() {
        isElementVisible("cart_page_hapus_button", 20);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void validateEmptyCart() {
        isElementVisible("cart_page_empty_condition", 20);
        isElementVisible("cart_page_empty_text", 20);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void selectSeller(String sellerName) {
        String sellerCheckboxLocator = "checkbox_cart_seller_" + sellerName + "_unchecked";
        String elm_sellerCheckboxLocator = constructLocator("cart_page_elm_sellerCheckboxLocator", sellerCheckboxLocator);
        swipeUpToElement(elm_sellerCheckboxLocator);
        isElementVisible(elm_sellerCheckboxLocator);
        tapElement(elm_sellerCheckboxLocator);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void validateDeletedStore(String sellerName) {
        String sellerLocator = "//XCUIElementTypeStaticText[@name='" + sellerName + "']";
        String elm_sellerlocator = constructLocator("cart_page_elm_sellerlocator", sellerLocator);
        verifyElementNotExist(elm_sellerlocator);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void unSelectAll() {
        if (isElementVisible("cart_page_hapus_button", 15)) {
            clickPilihSemua();
        }
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void checkPilihVariantBarangPage() {
        assertTrue(isElementVisible("pilih_variant_page"), "Gagal Redirect ke Page Pilih Variant");
    }

    public void checkInfoVariantProduct() {
        String productName = CartData.setProductName(getElementValue("product_atc_variat_name"));
        LogUtil.info(productName);
        String variantName = CartData.setVariantName(getElementValue("variant_atc_name"));
        LogUtil.info(variantName);
    }

    public void checkIsRedirectToPopUpAddToCartVariant() {
        String productName = CartData.getProductName();
        String variantName = CartData.getVariantName();
        String elm_productName = constructLocator("cart_page_elm_productName", productName);
        String elm_variantName = constructLocator("cart_page_elm_variantName", variantName);
        assertTrue(isElementVisible("berhasil_masuk_keranjang"), "Product is Exist");
        //assertTrue("Product Variant Gagal Ditambahkan ke Keranjang",isElementVisible(By.name(productName)) || isElementVisible(By.name(variantName)));
        assertTrue(isElementVisible(elm_productName) || isElementVisible(elm_variantName), "Product Variant Gagal Ditambahkan ke Keranjang");
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void checkPopUpFrom() {
        String productName = CartData.getProductName();
        String elm_productName = constructLocator("cart_page_elm_productName", productName);
        waitForVisibilityOf("berhasil_masuk_keranjang");
        //assertTrue("Product is Exist", isElementVisible(By.name(productName)));
        assertTrue(isElementVisible(elm_productName), "Product is Exist");
        tapElement("close_pop_up_add_to_cart");
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void checkIsRedirectToCartPage() {
        assertTrue(isElementVisible("cart_page_title"), "Tidak Berhasil Redirect ke Keranjang");
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void clearCartPopUp() {
        if (isElementVisible("berhasil_masuk_keranjang", 20)) {
            tapElement("icon_delete_pop_up");
            tapElement("button_hapus_pop_up");
            tapElement("close_pop_up_add_to_cart");
        } else {
            tapElement("new_pop_cart_dialog_trash_button");
            tapElement("new_pop_cart_dialog_confirmation_hapus_barang");
        }

    }

    public void checkProductNameOnPopUp() {
        CartData.setProductName(getElementValue("product_name_pop_up"));
    }

    public void setTotalPrice(int price, Boolean roundedUp) {
        int quantity = (roundedUp)
                ? (int) Math.ceil((double) price / CartData.getItemPrice())
                : (int) Math.floor((double) price / CartData.getItemPrice());
        TransactionData.setExpectedTotalBill((CartData.getItemPrice() * quantity));
        setItemQuantity(CartData.getExpectedProductName(), Integer.toString(quantity));
    }

    public void validatePelapakNAme() {
        verifyElementExist("cart_pelapak_name", 20, "pelapak name doesn't exist");
    }

    public void scrollToRecoSameSeller() {
        swipeUpToElement("button_beli_reco_same_seller", 5);
        verifyElementExist("button_beli_reco_same_seller", 30, "button beli same seller doesn't exist");
    }

    public void clickButtonBeliRecoSameSeller() {
        swipeUpToElement("button_beli_reco_same_seller");
        tapElement("button_beli_reco_same_seller");
    }

    public void seePopUpATC() {
        verifyElementExist("cart_pop_up_atc");
        tapElement("button_close_pop_up_atc");
    }

    public void clickProductRecoSameSeller() {
        swipeUpToElement("button_beli_reco_same_seller");
        verifyElementExist("cart_reco_same_seller");
        tapElement("cart_reco_same_seller");
    }

    public void scrollToRecommendation() {
        swipeUpToElement("cart_recommendation", 5);
        verifyElementExist("cart_recommendation", 20, "recommendation product doesn't exist");
    }

    public void clickButtonBeliRecommendation() {
        swipeUpToElement("button_beli_recommendation");
        tapElement("button_beli_recommendation");
    }

    public void clickProductRecommendation() {
        swipeUpToElement("cart_recommendation", 6);
        tapElement("cart_recommendation");
    }

    public void validateReco(){
        if (!isElementExist("cart_page_reco")) {
             verifyElementExist("cart_page_reco_panel");
             verifyElementExist("cart_page_product_reco");
          }else {
                verifyElementExist("cart_page_reco");
        }
    }

    public void validateRecoSameSellerNotExist() {
        verifyElementNotExist("button_beli_reco_same_seller");
    }

    //TRAY ATC POPUP START

    public void openCartPage() {
        waitFor(10);
        openDeepLink("/cart/carts");
    }

    public void validateRecoSection() {
        verifyElementExist("RECO_TITLE");
        verifyElementExist("SECTION_RECO_CART");
        verifyElementExist("PRODUCT_RATING");
        verifyElementExist("PRODUCT_PRICE");
    }

    public void addToCartReco() {
        waitForVisibilityOf("ATC_FIRST_PRODUCT",10);
        tapElement("ATC_FIRST_PRODUCT");
    }

    public String getProductName() {
        return getText("cart_product_name").replaceAll("title_cart_item_" , "");
    }

    public void validateProductOnCart() {
        delay(1000); //need delay for waiting cart page display properly
        validateValue().equals(CartDialogData.getProductName(), getProductName());
    }

    public void tapButtonPlusCartPage(String productName) {
        String addQtyByProductName = constructLocator("cart_page_upd_quant_plus", productName);

        waitForVisibilityOf(addQtyByProductName, 20);
        tapElement(addQtyByProductName);
    }

    public void tapButtonMinusCartPage(String productName) {
        String substractQtyByProductName = constructLocator("cart_page_upd_quant_minus", productName);

        waitForVisibilityOf(substractQtyByProductName, 20);
        tapElement(substractQtyByProductName);
    }

    public void verifySubtotalIsExist() {
        verifyElementExist("cart_page_sub_total_bill", 10, "element subtotal is not exist");
    }

    public void chooseItem(String itemName) {
        String itemNameLocator = "checkbox_cart_item_" + itemName + "_unchecked";
        String elm_itemNameLocator = constructLocator("cart_page_elm_itemNameLocator", itemNameLocator);

        swipeDownToElement(elm_itemNameLocator);
        tapElement(elm_itemNameLocator);
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void validateSnackBar() {
        validateExist("SNACKBAR_REMOVE_PRODUCT", 5);
    }

    public void checkDiscountPrice() {
        waitForVisibilityOf("TRAY_ATC_POPUP_TITLE", 10);
        validateDisplayed("cart_strikethrough_price");
        validateDisplayed("new_pop_cart_dialog_product_price");
        HelperData.setLastActionPage(new CartPage(iosDriver));
    }

    public void autoClearCart() {
        if (isElementVisible("cart_page_pilih_semua_checklist",10)){
            tapElement("cart_page_pilih_semua_checklist",3);
            clearCart();
        } else {
            validateEmptyCart();
        }
    }

    public void flashDealLabelonCart() {
        validateExist("cart_flash_deal_label", 20);
    }

    public void validatePriceAddedCorrect() {
        validateExist("cart_flash_deal_price");
        MOPData.setFlashDealPriceCartPage(getText("cart_flash_deal_price"));

        int atcPrice = ChampionData.priceStringToInteger(MOPData.getFlashDealPriceCartPage());
        int pdpPrice = ChampionData.priceStringToInteger(MOPData.getFlashDealPricePdp());

        assertEquals (pdpPrice, atcPrice);
    }

    public void tapFlashDealProductOnCart() {
        tapElement("cart_flash_deal_image", 10);
    }

    public void validateFDQuantityDisable() {
        validateExist("cart_flash_deal_min_quantity_button");
        validateDisabled("cart_flash_deal_min_quantity_button");
        validateExist("cart_flash_deal_max_quantity_button");
    }

    public void deleteFDProductOnCart() {
        waitForVisibilityOf("cart_flash_deal_delete_button", 30);
        tapElement("cart_flash_deal_delete_button");
    }

    public void tapHapusButtonLink() {
        waitForVisibilityOf("cart_page_hapus_button", 30);
        tapElement("cart_page_hapus_button");
    }
}
