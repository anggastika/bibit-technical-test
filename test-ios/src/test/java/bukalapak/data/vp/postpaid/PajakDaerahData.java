package bukalapak.data.vp.postpaid;

public class PajakDaerahData {
    private static String customerNumber;
    private static String province;
    private static String customerName;
    private static int taxYear;

    public static String getCustomerNumber() {
        return customerNumber;
    }

    public static void setCustomerNumber(String customerNumber) {
        PajakDaerahData.customerNumber = customerNumber;
    }

    public static String getProvince() {
        return province;
    }

    public static void setProvince(String province) {
        PajakDaerahData.province = province;
    }

    public static int getTaxYear() {
        return taxYear;
    }

    public static void setTaxYear(int taxYear) {
        PajakDaerahData.taxYear = taxYear;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {
        PajakDaerahData.customerName = customerName;
    }
}
