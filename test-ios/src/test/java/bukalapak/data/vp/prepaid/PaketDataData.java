package bukalapak.data.vp.prepaid;

public class PaketDataData {

    private static String phoneNumber;
    private static String denominationName;
    private static String denominationPrice;

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        PaketDataData.phoneNumber = phoneNumber;
    }

    public static String getDenominationName() {
        return denominationName;
    }

    public static void setDenominationName(String denominationName) {
        PaketDataData.denominationName = denominationName;
    }

    public static String getDenominationPrice() {
        return denominationPrice;
    }

    public static void setDenominationPrice(String denominationPrice) {
        PaketDataData.denominationPrice = denominationPrice;
    }
}
