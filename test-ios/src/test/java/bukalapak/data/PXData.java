package bukalapak.data;

import org.apache.commons.lang3.RandomUtils;

public class PXData {
    private static String randomProductName = "";
    private static String prodName = "";
    private static String prodPrice = "";
    private static String prodDiscount;

    //generate random product name
    public static String generateRandomProductName(String provider) {
        String productName;
        int randomNum = RandomUtils.nextInt(100000, 999999);
        String randomize = String.valueOf(randomNum);
        String names = "Product IOS";
        productName = names + randomize;

        return productName;
    }

    public static String getProductName() { return prodName; }

    public static void setProductName(String prodName) { PXData.prodName = prodName; }

    public static String getProductPrice() { return prodPrice; }

    public static void setProductPrice(String prodPrice) { PXData.prodPrice = prodPrice; }

    public static String getRandomProductName() { return randomProductName; }

    public static void setRandomProductName(String randomProductName) { PXData.randomProductName = randomProductName;
    }

    public static String getProdDiscount() {
        return prodDiscount;
    }

    public static void setProdDiscount(String prodDiscount) {
        PXData.prodDiscount = prodDiscount;
    }

}
