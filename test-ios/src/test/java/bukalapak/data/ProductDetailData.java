package bukalapak.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProductDetailData {
    private static String ongkirPrice1;
    private static String ongkirPrice2;
    private static String amountValue1;
    private static String amountValue2;
    private static String kategoriName;
    private static String pelapakName;
    private static String productName;
    private static String productPrice;
    private static ArrayList<String> productVariant = new ArrayList<>();

    public static String getOngkirPrice1() {
        return ongkirPrice1;
    }

    public static String setOngkirPrice1(String ongkirPrice1) {
        ProductDetailData.ongkirPrice1 = ongkirPrice1;
        return ongkirPrice1;
    }

    public static String getOngkirPrice2() {
        return ongkirPrice2;
    }

    public static String setOngkirPrice2(String ongkirPrice2) {
        ProductDetailData.ongkirPrice2 = ongkirPrice2;
        return ongkirPrice2;
    }

    public static String getAmountValue1() {
        return amountValue1;
    }

    public static String setAmountValue1(String amountValue1) {
        ProductDetailData.amountValue1 = amountValue1;
        return amountValue1;
    }

    public static String getAmountValue2() {
        return amountValue2;
    }

    public static String setAmountValue2(String amountValue2) {
        ProductDetailData.amountValue2 = amountValue2;
        return amountValue2;
    }

    public static String getKategoriName() {
        return kategoriName;
    }

    public static String setKategoriName(String kategoriName) {
        ProductDetailData.kategoriName = kategoriName;
        return kategoriName;
    }

    public static String getPelapakName() {
        return pelapakName;
    }

    public static String setPelapakName(String pelapakName) {
        ProductDetailData.pelapakName = pelapakName;
        return pelapakName;
    }

    public static String getProductName() {
        return productName;
    }

    public static String setProductName(String productName) {
        ProductDetailData.productName = productName.trim();
        return productName;
    }

    public static String getProductPrice() {
        return productPrice;
    }

    public static void setProductPrice(String productPrice) {
        ProductDetailData.productPrice = productPrice;
    }

    public static void setVariant(String variant) {
        productVariant.add(variant);
    }

    public static ArrayList<String> getVariant() {
        return productVariant;
    }
}
