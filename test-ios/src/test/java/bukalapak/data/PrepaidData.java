package bukalapak.data;

public class PrepaidData {
    private static String noMeter;
    private static String totalTagihan;
    private static String phoneNumber;
    private static String packages;
    private static String price;
    private static String idPelanggan;
    private static boolean onboarded = false;

    public static String getTotalTagihan() {
        return totalTagihan;
    }

    public static void setTotalTagihan(String totalTagihan) {
        PrepaidData.totalTagihan = totalTagihan;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        PrepaidData.phoneNumber = phoneNumber;
    }

    public static String getProductName() {
        return packages;
    }

    public static void setProductName(String packages) {
        PrepaidData.packages = packages;
    }

    public static String getProductPrice() {
        return price;
    }

    public static void setProductPrice(String price) {
        PrepaidData.price = price;
    }

    public static boolean isOnboarded() {
        return onboarded;
    }

    public static void setOnboarded(boolean onboarded) {
        PrepaidData.onboarded = onboarded;
    }

    // Electricity
    public static String getNoMeter() {
        return noMeter;
    }

    public static void setNoMeter(String noMeter) {
        PrepaidData.noMeter = noMeter;
    }

    public static String getIdPelanggan() {
        return idPelanggan;
    }

    public static void setIdPelanggan(String idPelanggan) {
        PrepaidData.idPelanggan = idPelanggan;
    }
}
