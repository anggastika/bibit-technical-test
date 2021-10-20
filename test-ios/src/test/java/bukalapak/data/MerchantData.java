package bukalapak.data;

import java.util.ArrayList;
import java.util.List;

public class MerchantData {

    private static String etalaseName;
    private static String productName;
    private static String sellerName;
    private static String barangUnggulanName;
    private static List<String> productUnggulanName = new ArrayList<>();
    private static int notActiveBarangUnggulan;
    private static int notActiveBarangUnggulan2;
    private static int activeBarangUnggulan;
    private static int activeBarangUnggulan2;
    private static String collectionName;
    private static String urutkanName;
    private static String subscribeStatus;

    public static String getProductName() {
        return productName;
    }

    public static String setProductName(String productName) {
        MerchantData.productName = productName.trim();
        return productName;
    }

    public static String getSellerName() {
        return sellerName;
    }

    public static String setSellerName(String sellerName) {
        MerchantData.sellerName = sellerName.trim();
        return sellerName;
    }

    public static List<String> getProductUnggulanName() {
        return productUnggulanName;
    }

    public static void setproductUnggulanName(List<String> productUnggulanName) {
        MerchantData.productUnggulanName = productUnggulanName;
    }

    public static int getNotActiveBarangUnggulan() {
        return notActiveBarangUnggulan;
    }

    public static void setNotActiveBarangUnggulan(int notActiveBarangUnggulan) {
        MerchantData.notActiveBarangUnggulan = notActiveBarangUnggulan;
    }

    public static int getNotActiveBarangUnggulan2() {
        return notActiveBarangUnggulan2;
    }

    public static void setNotActiveBarangUnggulan2(int notActiveBarangUnggulan2) {
        MerchantData.notActiveBarangUnggulan2 = notActiveBarangUnggulan2;
    }

    public static String getBarangUnggulanName() {
        return barangUnggulanName;
    }

    public static String setBarangUnggulanName(String barangUnggulanName) {
        MerchantData.barangUnggulanName = barangUnggulanName.trim();
        return barangUnggulanName;
    }

    public static int getActiveBarangUnggulan() {
        return activeBarangUnggulan;
    }

    public static void setActiveBarangUnggulan(int activeBarangUnggulan) {
        MerchantData.activeBarangUnggulan = activeBarangUnggulan;
    }

    public static int getActiveBarangUnggulan2() {
        return activeBarangUnggulan2;
    }

    public static void setActiveBarangUnggulan2(int activeBarangUnggulan2) {
        MerchantData.activeBarangUnggulan2 = activeBarangUnggulan2;
    }

    public static String getCollectionName() {
        return collectionName;
    }

    public static String setCollectionName(String collectionName) {
        MerchantData.collectionName = collectionName.trim();
        return collectionName;
    }

    public static String getEtalaseName() {
        return etalaseName;
    }

    public static void setEtalaseName(String etalaseName) {
        MerchantData.etalaseName = etalaseName.trim();
    }

    public static String getUrutkanName() {
        return urutkanName;
    }

    public static void setUrutkanName(String urutkanName) {
        MerchantData.urutkanName = urutkanName.trim();
    }

    public static String getSubscribeStatus() {
        return subscribeStatus;
    }

    public static void setSubscribeStatus(String subscribeStatus) {
        MerchantData.subscribeStatus = subscribeStatus.trim();
    }
}
