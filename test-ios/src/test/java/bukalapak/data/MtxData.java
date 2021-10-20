package bukalapak.data;

public class MtxData {

    private static String productPricePdp;
    private static String productPriceCheckout;
    private static String totalProductPriceCheckout;
    private static String totalPaymentCheckout;
    private static String paymentMethod;
    private static String mixPayment;
    private static String mixPaymentDana;
    private static String mixPaymentSaldo;
    private static String articleName;
    private static String productName;

    public static String getProductPricePdp() {
        return productPricePdp;
    }

    public static void setProductPricePdp(String productPricePdp) {
        MtxData.productPricePdp = productPricePdp;
    }

    public static String getProdouctPriceCheckout() {
        return productPriceCheckout;
    }

    public static void setProdouctPriceCheckout(String productPriceCheckout) {
        MtxData.productPriceCheckout = productPriceCheckout;
    }

    public static String getTotalProductPriceCheckout() {
        return totalProductPriceCheckout;
    }

    public static void setTotalProductPriceCheckout(String totalProductPriceCheckout) {
        MtxData.totalProductPriceCheckout = totalProductPriceCheckout;
    }

    public static String getTotalPaymentCheckout() {
        return totalPaymentCheckout;
    }

    public static void setTotalPaymentCheckout(String totalPaymentCheckout) {
        MtxData.totalPaymentCheckout = totalPaymentCheckout;
    }

    public static String getPaymentMethod() {
        return paymentMethod;
    }

    public static void setPaymentMethod(String paymentMethod) {
        MtxData.paymentMethod = paymentMethod;
    }

    public static String getMixPayment() {
        return mixPayment;
    }

    public static void setMixPayment(String mixPayment) {
        MtxData.mixPayment = mixPayment;
    }
    public static String getMixPaymentDana() {
        return mixPaymentDana;
    }

    public static void setMixPaymentDana(String mixPaymentDana) {
        MtxData.mixPaymentDana = mixPaymentDana;
    }

    public static String getMixPaymentSaldo() {
        return mixPaymentSaldo;
    }

    public static void setMixPaymentSaldo(String mixPaymentSaldo) {
        MtxData.mixPaymentSaldo = mixPaymentSaldo;
    }

    public static void setArticleName(String articleName) {
        MtxData.articleName = articleName;
    }

    public static String getArticleName() {
        return articleName;
    }

    public static void setProductName(String productName) {
        MtxData.productName = productName;
    }

    public static String getProductName() {
        return productName;
    }
}
