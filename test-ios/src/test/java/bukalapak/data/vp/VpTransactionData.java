package bukalapak.data.vp;

public class VpTransactionData {
  private static String paymentMethod;
  private static String paymentTotal;
  private static String discountAmount = "Rp0";

  public static String getPaymentMethod() {
    return paymentMethod;
  }

  public static void setPaymentMethod(String paymentMethod) {
    VpTransactionData.paymentMethod = paymentMethod;
  }

  public static String getPaymentTotal() {
    return paymentTotal;
  }

  public static void setPaymentTotal(String paymentTotal) {
    VpTransactionData.paymentTotal = paymentTotal;
  }

  public static String getDiscountAmount() {
    return discountAmount;
  }

  public static void setDiscountAmount(String discountAmount) {
    VpTransactionData.discountAmount = discountAmount;
  }

}
