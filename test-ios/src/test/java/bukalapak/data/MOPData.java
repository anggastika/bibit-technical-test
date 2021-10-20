package bukalapak.data;

public class MOPData {
    private static String flashDealPricePdp;
    private static String flashDealPriceCheckout;
    private static String flashDealPriceCart;
    private static String flashDealPriceCartPage;

    public static String getFlashDealPriceCartPage() {
        return flashDealPriceCartPage;
    }

    public static void setFlashDealPriceCartPage(String flashDealPriceCartPage) {
        MOPData.flashDealPriceCartPage = flashDealPriceCartPage;
    }

    public static String getFlashDealPriceCart() {
        return flashDealPriceCart;
    }

    public static void setFlashDealPriceCart(String flashDealPriceCart) {
        MOPData.flashDealPriceCart = flashDealPriceCart;
    }

    public static String getFlashDealPricePdp() {
        return flashDealPricePdp;
    }

    public static void setFlashDealPricePdp(String flashDealPricePdp) {
        MOPData.flashDealPricePdp = flashDealPricePdp;
    }

    public static String getFlashDealPriceCheckout() {
        return flashDealPriceCheckout;
    }

    public static void setFlashDealPriceCheckout(String flashDealPriceCheckout) {
        MOPData.flashDealPriceCheckout = flashDealPriceCheckout;
    }
}
