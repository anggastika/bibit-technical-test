package bukalapak.data.vp.prepaid;

public class UangElektronikData {

    private static boolean infoSkipped = false;
    private static String cardNumber;
    private static String denominationName;
    private static String denominationPrice;

    public static boolean isInfoSkipped() {
        return infoSkipped;
    }

    public static void setInfoSkipped(boolean infoSkipped) {
        UangElektronikData.infoSkipped = infoSkipped;
    }

    public static String getCardNumber() {
        return cardNumber;
    }

    public static void setCardNumber(String cardNumber) {
        UangElektronikData.cardNumber = cardNumber;
    }

    public static String getDenominationName() {
        return denominationName;
    }

    public static void setDenominationName(String denominationName) {
        UangElektronikData.denominationName = denominationName;
    }

    public static String getDenominationPrice() {
        return denominationPrice;
    }

    public static void setDenominationPrice(String denominationPrice) {
        UangElektronikData.denominationPrice = denominationPrice;
    }
}
