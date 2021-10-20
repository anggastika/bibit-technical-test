package bukalapak.data.vp.postpaid;

public class ListrikPascabayarData {
    private static String customerNumber;
    private static String customerName;
    private static String customerPeriod;

    public static String getCustomerNumber() {
        return customerNumber;
    }

    public static void setCustomerNumber(String customerNumber) {
        ListrikPascabayarData.customerNumber = customerNumber;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {
        ListrikPascabayarData.customerName = customerName;
    }

    public static String getCustomerPeriod() {
        return customerPeriod;
    }

    public static void setCustomerPeriod(String customerPeriod) {
        ListrikPascabayarData.customerPeriod = customerPeriod;
    }

}
