package bukalapak.data.vp.postpaid;

public class PulsaPascabayarData {
    private static String customerNumber;
    private static String customerName;
    private static String billPeriod;

    public static String getCustomerNumber() {
        return customerNumber;
    }

    public static void setCustomerNumber(String customerNumber) {
        PulsaPascabayarData.customerNumber = customerNumber;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {
        PulsaPascabayarData.customerName = customerName;
    }

    public static String getBillPeriod() {
        return billPeriod;
    }

    public static void setBillPeriod(String billPeriod) {
        PulsaPascabayarData.billPeriod = billPeriod;
    }
}
