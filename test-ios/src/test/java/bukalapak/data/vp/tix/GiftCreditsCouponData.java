package bukalapak.data.vp.tix;

public class GiftCreditsCouponData {
    public static final String invalidPhoneFormatError = "Format no telepon salah";
    private static String recipientName;
    private static String recipientPhoneNumber;
    private static String dealsName;
    private static int dealsPrice;
    private static int totalPrice;
    private static String giftCardType;

    public static String getRecipientName() {
        return recipientName;
    }

    public static void setRecipientName(String recipientName) {
        GiftCreditsCouponData.recipientName = recipientName;
    }

    public static String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public static void setRecipientPhoneNumber(String recipientPhoneNumber) {
        GiftCreditsCouponData.recipientPhoneNumber = recipientPhoneNumber;
    }

    public static String getDealsName() {
        return dealsName;
    }

    public static void setDealsName(String dealsName) {
        GiftCreditsCouponData.dealsName = dealsName;
    }

    public static int getDealsPrice() {
        return dealsPrice;
    }

    public static void setDealsPrice(int dealsPrice) {
        GiftCreditsCouponData.dealsPrice = dealsPrice;
    }

    public static int getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(int totalPrice) {
        GiftCreditsCouponData.totalPrice = totalPrice;
    }

    public static void setGiftCardType(String giftCardType) {
        GiftCreditsCouponData.giftCardType = giftCardType;
    }

    public static String getGiftCardType() {
        return giftCardType;
    }
}
