package bukalapak.data.vp.postpaid;

public class AddOnIndihomeData {
    private static String customerNumber;
    private static String customerName;
    private static String packageName;

    public static String getCustomerNumber() {
        return customerNumber;
    }

    public static void setCustomerNumber(String customerNumber) {
        AddOnIndihomeData.customerNumber = customerNumber;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {
        AddOnIndihomeData.customerName = customerName;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String packageName) {
        AddOnIndihomeData.packageName = packageName;
    }
}
