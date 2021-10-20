package bukalapak.data.vp.postpaid;

public class AirPdamData {

    private static String customerNumber;
    private static String customerName;
    private static String customerPeriod;
    private static String customerArea;

    public static String getCustomerNumber() {
        return customerNumber;
    }

    public static void setCustomerNumber(String customerNumber) {
        AirPdamData.customerNumber = customerNumber;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {
        AirPdamData.customerName = customerName;
    }

    public static String getCustomerPeriod() {
        return customerPeriod;
    }

    public static void setCustomerPeriod(String customerPeriod) {
        AirPdamData.customerPeriod = customerPeriod;
    }

    public static String getCustomerArea() {
        return customerArea;
    }

    public static void setCustomerArea(String customerArea) {
        AirPdamData.customerArea = customerArea;
    }
}
