package bukalapak.data;

/**
 * @Author: Ayu Musfita
 * @Date: 04/06/20, Thu
 **/
public class SubscriptionData {
    private static String packageDealsName;
    private static String packageSkuName;
    private static int packagePrice;
    private static String expiryTime;

    public static String getExpiryTime() {
        return expiryTime;
    }

    public static void setExpiryTime(String expiryTime) {
        SubscriptionData.expiryTime = expiryTime;
    }

    public static String getPackageDealsName() {
        return packageDealsName;
    }

    public static void setPackageDealsName(String packageDealsName) {
        SubscriptionData.packageDealsName = packageDealsName;
    }

    public static int getPackagePrice() {
        return packagePrice;
    }

    public static void setPackagePrice(int packagePrice) {
        SubscriptionData.packagePrice = packagePrice;
    }

    public static String getPackageSkuName() {
        return packageSkuName;
    }

    public static void setPackageSkuName(String packageSkuName) {
        SubscriptionData.packageSkuName = packageSkuName;
    }
}
