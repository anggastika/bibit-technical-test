package bukalapak.data;

public class PostpaidData {
    // general fields
    private static String customerNumber;

    // final static error message
    public final static String billing_code_not_registered = "Tagihan tidak tersedia";

    // specific fields
    private static String area;
    private static String bank;
    private static String biller;
    private static int month;
    private static String cost;
    private static String period;
    private static String postpaidNumber;
    private static String voucherCode;
    private static String rangkaNumber;
    private static boolean coachMark = false;
    private static String propertyName;
    private static String propertyType;
    private static String propertySurfaceArea;
    private static String propertyBuildingArea;
    private static int propertyPrice;


    // REFACTOR: change to English, used?
    private static String totalTagihan;
    private static String customerName;
    private static String nominal;
    private static String option;  // such as foundation, biller, etc.
    private static String phoneNumber;
    private static String tagihan;
    private static String biayaAdmin;

    public static void setCustomerNumber(String customerNumber) {
        PostpaidData.customerNumber = customerNumber;
    }

    public static String getCustomerNumber() {
        return customerNumber;
    }

    public static void setArea(String area) {
        PostpaidData.area = area;
    }

    public static String getArea() {
        return area;
    }

    public static void setBank(String bank) {
        PostpaidData.bank = bank;
    }

    public static String getBank() {
        return bank;
    }

    public static void setBiller(String biller) {
        PostpaidData.biller = biller;
    }

    public static String getBiller() {
        return biller;
    }

    public static void setMonth(int month) {
        PostpaidData.month = month;
    }

    public static int getMonth() {
        return month;
    }

    public static void setOption(String option) {
        PostpaidData.option = option;
    }

    public static String getOption() {
        return option;
    }

    public static String getTotalTagihan() {
        return totalTagihan;
    }

    public static void setTotalTagihan(String totalTagihan) {
        PostpaidData.totalTagihan = totalTagihan;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {
        PostpaidData.customerName = customerName;
    }

    public static void setNominal(String nominal) { PostpaidData.nominal = nominal; }

    public static String getNominal() {
        return nominal;
    }

    public static void setPhoneNumeber(String phoneNumber) { PostpaidData.phoneNumber = phoneNumber; }

    public static String getPhoneNumber() { return  phoneNumber; }

    public static void setTagihan(String tagihan) { PostpaidData.tagihan = tagihan; }

    public static String getTagihan() { return tagihan; }

    public static void setBiayaAdmin(String biayaAdmin) { PostpaidData.biayaAdmin = biayaAdmin; }

    public static String getBiayaAdmin() { return  biayaAdmin; }

    public static void setCost(String cost) {
        PostpaidData.cost = cost;
    }

    public static String getCost() {
        return cost;
    }

    public static void setPeriod(String period) {
        PostpaidData.period = period;
    }

    public static String getPeriod() {
        return period;
    }

    public static String getPostpaidNumber() {
        return postpaidNumber;
    }

    public static void setPostpaidNumber(String postpaidNumber) {
        PostpaidData.postpaidNumber = postpaidNumber;
    }

    public static String getVoucherCode() {
        return voucherCode;
    }

    public static void setVoucherCode(String voucherCode) {
        PostpaidData.voucherCode = voucherCode;
    }

    public static String getRangkaNumber() {
        return rangkaNumber;
    }

    public static void setRangkaNumber(String rangkaNumber) {
        PostpaidData.rangkaNumber = rangkaNumber;
    }

    public static boolean getCoachMark() {
        return coachMark;
    }

    public static void setCoachMark(boolean coachMark) {
        PostpaidData.coachMark = coachMark;
    }

    public static String getPropertyName() {
        return propertyName;
    }

    public static void setPropertyName(String propertyName) {
        PostpaidData.propertyName = propertyName;
    }

    public static String getPropertyType() {
        return propertyType;
    }

    public static void setPropertyType(String propertyType) {
        PostpaidData.propertyType = propertyType;
    }

    public static String getPropertySurfaceArea() {
        return propertySurfaceArea;
    }

    public static void setPropertySurfaceArea(String propertySurfaceArea) {
        PostpaidData.propertySurfaceArea = propertySurfaceArea;
    }

    public static String getPropertyBuildingArea() {
        return propertyBuildingArea;
    }

    public static void setPropertyBuildingArea(String propertyBuildingArea) {
        PostpaidData.propertyBuildingArea = propertyBuildingArea;
    }

    public static int getPropertyPrice() {
        return propertyPrice;
    }

    public static void setPropertyPrice(int propertyPrice) {
        PostpaidData.propertyPrice = propertyPrice;
    }
}
