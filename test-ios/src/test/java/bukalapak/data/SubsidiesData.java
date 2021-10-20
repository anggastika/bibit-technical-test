package bukalapak.data;

public class SubsidiesData {

    private static String voucherName;
    private static String actualProductPrice;
    private static String voucherCode;
    public static final String VOUCHER_PRICE_DETAIL_PREFIX = "Voucher";
    public static final String VOUCHER_PRICE_DETAIL_DISCOUNT = "diskon";
    public static final String VOUCHER_PRICE_DETAIL_SHIPPING = "ongkir";
    public static final String VOUCHER_PRICE_DETAIL_OWNER_BUKALAPAK = "Bukalapak";
    public static final String VOUCHER_PRICE_DETAIL_OWNER_PELAPAK = "Pelapak";

    public static String getVoucherName() {
        return voucherName;
    }

    public static String setVoucherName(String voucherName) {
        SubsidiesData.voucherName = voucherName;
        return voucherName;
    }

    public static String getVoucherCode() {
        return voucherCode;
    }

    public static void setVoucherCode(String voucherCode) {
        SubsidiesData.voucherCode = voucherCode;
    }

    public static void setActualProductPrice(String actualProductPrice) {
        SubsidiesData.actualProductPrice = actualProductPrice;
    }

    public static String getActualProductPrice() {
        return actualProductPrice;
    }

    public static String getVoucherOwnerPriceDetailCopy(String owner) {
        if (owner.equals("Bukalapak")) {
            return VOUCHER_PRICE_DETAIL_OWNER_BUKALAPAK;
        } else if (owner.equals("Pelapak")) {
            return VOUCHER_PRICE_DETAIL_OWNER_PELAPAK;
        } else {
            return "";
        }
    }

    public static String getVoucherTypePriceDetailCopy(String type) {
        if (type.equals("Shipping")) {
            return VOUCHER_PRICE_DETAIL_SHIPPING;
        } else if (type.equals("Discount")) {
            return VOUCHER_PRICE_DETAIL_DISCOUNT;
        } else {
            return "";
        }
    }

    public static String buildPriceDetailCopy(String owner, String type) {
        String ownerCopy = getVoucherOwnerPriceDetailCopy(owner);
        String typeCopy = getVoucherTypePriceDetailCopy(type);
        return VOUCHER_PRICE_DETAIL_PREFIX + " " + typeCopy + " " + ownerCopy;
    }
}
