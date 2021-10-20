package bukalapak.data.vp.postpaid;

public class KartuKreditData {
    private static String customerNumber;
    private static String customerName;
    private static String bankName;

    public static String getCustomerNumber() {
        return customerNumber;
    }

    public static void setCustomerNumber(String customerNumber) {
        KartuKreditData.customerNumber = customerNumber;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {
        KartuKreditData.customerName = customerName;
    }

    public static String getBankName() {
        return bankName;
    }

    public static void setBankName(String bankName) {
        KartuKreditData.bankName = bankName;
    }
}
