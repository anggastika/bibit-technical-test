package bukalapak.data;

/**
 * @Author: Ayu Musfita
 * @Date: 28/05/20, Thu
 **/
public class KuponData {
    private static boolean coachMarked = false;
    public final static String COUPON_TITLE_NOT_FOUND = "Maaf, Kupon yang kamu cari tidak ditemukan.";
    public final static String COUPON_CAPTION_NOT_FOUND = "Kami tidak dapat menemukan kupon pilihanmu. Silakan coba pilihan lainnya.";
    public final static String COUPON_MERCHANT_TITLE_NOT_FOUND = "Maaf merchant yang kamu cari tidak ditemukan.";
    public final static String COUPON_MERCHANT_CAPTION_NOT_FOUND = "Kami tidak dapat menemukan merchant pilihanmu. " +
            "Silakan coba pilihan lainnya";
    private static String merchant;
    private static String couponName;
    private static int minPrice;
    private static int maxPrice;

    public static int getMinPrice() {
        return minPrice;
    }

    public static void setMinPrice(int minPrice) {
        KuponData.minPrice = minPrice;
    }

    public static int getMaxPrice() {
        return maxPrice;
    }

    public static void setMaxPrice(int maxPrice) {
        KuponData.maxPrice = maxPrice;
    }

    public static String getCouponName() {
        return couponName;
    }

    public static void setCouponName(String couponName) {
        KuponData.couponName = couponName;
    }

    public static String getMerchant() {
        return merchant;
    }

    public static void setMerchant(String merchant) {
        KuponData.merchant = merchant;
    }

    public static boolean isCoachMarked() {
        return coachMarked;
    }

    public static void setCoachMarked(boolean coachMarked) {
        KuponData.coachMarked = coachMarked;
    }

}
