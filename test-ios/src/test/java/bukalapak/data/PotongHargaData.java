package bukalapak.data;

import java.util.ArrayList;

public class PotongHargaData {
    private static int eventId;
    private static String eventName;
    private static int totalProductEvent;
    private static int productIdPotongHarga;
    private static String productIdMarketplace;
    private static String productSkuId;
    private static String productNamePotongHarga;
    private static String stateHistoryProduct;
    private static int currentPriceHistoryProduct;
    private static ArrayList listProductName = new ArrayList();

    public static void setEventId(int eventId) {
        PotongHargaData.eventId = eventId;
    }

    public static int getEventId() {
        return eventId;
    }

    public static void setEventName(String eventName) {
        PotongHargaData.eventName = eventName;
    }

    public static String getEventName() {
        return eventName;
    }

    public static void setTotalProductEvent(int totalProductEvent) {
        PotongHargaData.totalProductEvent = totalProductEvent;
    }

    public static int getTotalProductEvent() {
        return totalProductEvent;
    }

    public static void setProductIdPotongHarga(int productIdPotongHarga) {
        PotongHargaData.productIdPotongHarga = productIdPotongHarga;
    }

    public static int getProductIdPotongHarga() {
        return productIdPotongHarga;
    }

    public static void setProductIdMarketplace(String productIdMarketplace) {
        PotongHargaData.productIdMarketplace = productIdMarketplace;
    }

    public static String getProductIdMarketplace() {
        return productIdMarketplace;
    }

    public static void setProductSkuId(String productSkuId) {
        PotongHargaData.productSkuId = productSkuId;
    }

    public static String getProductSkuId() {
        return productSkuId;
    }

    public static void setProductNamePotongHarga(String productNamePotongHarga) {
        PotongHargaData.productNamePotongHarga = productNamePotongHarga;
    }

    public static String getProductNamePotongHarga() {
        return productNamePotongHarga;
    }

    public static void setListProductName(ArrayList listProductName) {
        PotongHargaData.listProductName = listProductName;
    }

    public static ArrayList getListProductName() {
        return listProductName;
    }

    public static void setStateHistoryProduct(String stateHistoryProduct) {
        PotongHargaData.stateHistoryProduct = stateHistoryProduct;
    }

    public static String getStateHistoryProduct() {
        return stateHistoryProduct;
    }

    public static void setCurrentPriceHistoryProduct(int currentPriceHistoryProduct) {
        PotongHargaData.currentPriceHistoryProduct = currentPriceHistoryProduct;
    }

    public static int getCurrentPriceHistoryProduct() {
        return currentPriceHistoryProduct;
    }
}
