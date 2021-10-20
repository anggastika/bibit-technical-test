package bukalapak.data.vp.prepaid;

public class PulsaPrabayarData {

    private static boolean onboarded = false;
    private static String phoneNumber = "No. Telepon";
    private static String packageName;
    private static String packagePrice;

    public static boolean isOnboarded() {
        return onboarded;
    }

    public static void setOnboarded(boolean onboarded) {
        PulsaPrabayarData.onboarded = onboarded;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        PulsaPrabayarData.phoneNumber = phoneNumber;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String packageName) {
        PulsaPrabayarData.packageName = packageName;
    }

    public static String getPackagePrice() {
        return packagePrice;
    }

    public static void setPackagePrice(String packagePrice) {
        PulsaPrabayarData.packagePrice = packagePrice;
    }
}
