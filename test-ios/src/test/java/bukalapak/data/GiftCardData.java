package bukalapak.data;

public class GiftCardData {
    private static String giftCardName;
    private static String recipientName;
    private static String recipientPhoneNumber;
    private static boolean buyForOther;

    public static boolean isBuyForOther() {
        return buyForOther;
    }

    public static void setBuyForOther(boolean buyForOther) {
        GiftCardData.buyForOther = buyForOther;
    }

    public static String getGiftCardName() {
        return giftCardName;
    }

    public static void setGiftCardName(String giftCardName) {
        GiftCardData.giftCardName = giftCardName;
    }

    public static String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public static void setRecipientPhoneNumber(String recipientPhoneNumber) {
        GiftCardData.recipientPhoneNumber = recipientPhoneNumber;
    }

    public static String getRecipientName() {
        return recipientName;
    }

    public static void setRecipientName(String recipientName) {
        GiftCardData.recipientName = recipientName;
    }
}
