package bukalapak.data.vp.postpaid;

public class BpjsData {
    private static String customerNumber;
    private static String customerName;
    private static String customerPeriod;

    public static String getCustomerNumber() {
        return customerNumber;
    }

    public static void setCustomerNumber(String customerNumber) {
        BpjsData.customerNumber = customerNumber;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {
        BpjsData.customerName = customerName;
    }

    public static String getCustomerPeriod() {
        return customerPeriod;
    }

    public static void setCustomerPeriod(String customerPeriod) {
        BpjsData.customerPeriod = customerPeriod;
    }
}
