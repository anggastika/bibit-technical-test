package bukalapak.data;

import java.util.ArrayList;
import java.util.Map;

public class CartData {
    private static Object[] sellers;
    private static Object[] items;
    private static String productName;
    private static String variantName;
    private static int itemPrice;
    private static int quantityItem;
    private static String expectedTitle;
    private static String expectedName;
    private static String expectedAddress;
    private static String expectedCity;
    private static String expectedDistrict;
    private static String expectedProvince;
    private static String expectedPostCode;
    private static String expectedPhone;
    private static String expectedProductName;
    private static String storeName;
    private static int invalidItemQty = 0;

    private static ArrayList<Map<String, Object>> cartDetails = new ArrayList<Map<String, Object>>();
    private static ArrayList<Map<String, Object>> selectedItemOnCart = new ArrayList<Map<String, Object>>();

    public static Object[] getSellers() {
        return sellers.clone();
    }

    public static void setSellers(Object[] sellers) {
        CartData.sellers = sellers.clone();
    }

    public static Object[] getItems() {
        return items.clone();
    }

    public static void setItems(Object[] items) {
        CartData.items = items.clone();
    }
  
    public static String getProductName() {
        return productName;
    }

    public static String setProductName(String productName) {
        CartData.productName = productName;
        return productName;
    }

    public static String getVariantName() {
        return variantName;
    }

    public static String setVariantName(String variantName) {
        CartData.variantName = variantName;
        return variantName;
    }
  
    public static int getItemPrice() {
        return itemPrice;
    }

    public static void setItemPrice(int itemPrice) {
        CartData.itemPrice = itemPrice;
    }

    public static int getQuantityItem() {
        return quantityItem;
    }

    public static void setQuantityItem(int quantity) {
        CartData.quantityItem = quantity;
    }

    public static String getExpectedTitle() {
        return expectedTitle;
    }

    public static void setExpectedTitle(String expectedTitle) {
        CartData.expectedTitle = expectedTitle;
    }

    public static String getExpectedName() {
        return expectedName;
    }

    public static void setExpectedName(String expectedName) {
        CartData.expectedName = expectedName;
    }

    public static String getExpectedPhone() {
        return expectedPhone;
    }

    public static void setExpectedPhone(String expectedPhone) {
        CartData.expectedPhone = expectedPhone;
    }

    public static String getExpectedProvince() {
        return expectedProvince;
    }

    public static void setExpectedProvince(String expectedProvince) {
        CartData.expectedProvince = expectedProvince;
    }

    public static String getExpectedCity() {
        return expectedCity;
    }

    public static void setExpectedCity(String expectedCity) {
        CartData.expectedCity = expectedCity;
    }

    public static String getExpectedDistrict() {
        return expectedDistrict;
    }

    public static void setExpectedDistrict(String expectedDistrict) {
        CartData.expectedDistrict = expectedDistrict;
    }

    public static String getExpectedAddress() {
        return expectedAddress;
    }

    public static void setExpectedAddress(String expectedAddress) {
        CartData.expectedAddress = expectedAddress;
    }

    public static String getExpectedPostCode() {
        return expectedPostCode;
    }

    public static void setExpectedPostCode(String expectedPostCode) {
        CartData.expectedPostCode = expectedPostCode;
    }

    public static void setCartDetails(ArrayList<Map<String, Object>> cartDetail) {
        CartData.cartDetails = cartDetail;
    }

    public static ArrayList<Map<String, Object>> getCartDetails() {
        return cartDetails;
    }

    public static String getExpectedProductName() {
        return expectedProductName;
    }

    public static void setExpectedProductName(String expectedProductName) {
        CartData.expectedProductName = expectedProductName;
    }

    public static void setStoreName(String storeName) {
        CartData.storeName = storeName;
    }

    public static String getstoreName() {
        return storeName;
    }

    public static void setSelectedItemOnCart(ArrayList<Map<String, Object>> itemOnCart) {
        CartData.selectedItemOnCart = itemOnCart;
    }

    public static ArrayList<Map<String, Object>> getSelectedItemOnCart() {
        return selectedItemOnCart;
    }

    public static void setInvalidItemQty(int quantity){
        CartData.invalidItemQty = quantity;
    }

    public static int getInvalidItemQty() {
        return invalidItemQty;
    }
}
