package bukalapak.data;

public class PNRData {

    private static String productName;
    private static boolean isOnRecoDOH = false;

    public static void setIsOnRecoDOH(boolean isOnRecoDOH) {
        PNRData.isOnRecoDOH = isOnRecoDOH;
    }

    public static boolean isOnRecoDoh() {
        return isOnRecoDOH;
    }

    public static String getProductName() {
        return productName;
    }

    public static void setProductName(String productName) {
        PNRData.productName = productName.trim();
    }
}
