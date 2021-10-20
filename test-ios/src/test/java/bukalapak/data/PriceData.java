package bukalapak.data;

public class PriceData {
    private static String maxPrice;
    private static String minPrice;
    private static String price1;
    private static String price2;

    public static String getmaxPrice() {
        return maxPrice;
    }

    public static String setmaxPrice(String maxPrice) {
        PriceData.maxPrice = maxPrice;
        return maxPrice;
    }

    public static String getminPrice() {
        return minPrice;
    }

    public static String setminPrice(String minPrice) {
        PriceData.minPrice = minPrice;
        return minPrice;
    }

    public static String getPrice1() {
        return price1;
    }

    public static String setPrice1(String price1) {
        PriceData.price1 = price1;
        return price1;
    }

    public static String getPrice2() {
        return price2;
    }

    public static String setPrice2(String price2) {
        PriceData.price2 = price2;
        return price2;
    }
}


