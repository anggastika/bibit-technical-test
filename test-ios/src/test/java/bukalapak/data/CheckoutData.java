package bukalapak.data;

import java.util.ArrayList;
import java.util.Map;

public class CheckoutData {
    private static String productName;
    private static int productPrice;
    private static String dropshipperName;
    private static String dropshipperAdditionalInfo = "No. handphone atau catatan lainnya";
    private static int slashedProductPrice;
    private static String buyerNotes;
    private static int logisticInsuranceNominal;
    private static int returInsuranceNominal;
    private static int productInsuranceNominal;
    private static int crossSelingBukaemasNominal;
    private static int courierShippingNominal;
    private static String courierShippingName;
    private static String groupCourierSHippingName;
    private static int amountMixpaymentDANA;
    private static int amountMixpaymentSaldo;
    private static String productQuantity;
    private static String paymentMethod;
    private static String paymentService;
    private static String mixPayment;
    private static String mixPaymentDana;
    private static String mixPaymentSaldo;
    private static int roundedAmount;
    private static int totalBiayaLainnya;
    private static int totalPaymentCheckout;
    private static String nominalVoucher;
    private static String addressName;
    private static String nomorPesanan;


    private static ArrayList<Map<String, Object>> cartDetails = new ArrayList<Map<String, Object>>();
    private static String productVariant;
    private static String transactionId;
    private static String invoiceId;

    public static String getNomorPesanan() {
        return nomorPesanan;
    }

    public static void setNomorPesanan(String nomorPesanan) {
        CheckoutData.nomorPesanan = nomorPesanan;
    }

    public static String getProductQuantity() {
        return productQuantity;
    }

    public static void setProductQuantity(String productQuantity) {
        CheckoutData.productQuantity = productQuantity;
    }

    public static String getProductName() {
        return productName;
    }

    public static void setProductName(String productName) {
        CheckoutData.productName = productName;
    }

    public static int getProductPrice() {
        return productPrice;
    }

    public static void setProductPrice(int productPrice) {
        CheckoutData.productPrice = productPrice;
    }

    public static String getDropshipperName() {
        return dropshipperName;
    }

    public static void setDropshipperName(String dropshipperName) {
        CheckoutData.dropshipperName = dropshipperName;
    }

    public static String getDropshipperAdditionalInfo() {
        return dropshipperAdditionalInfo;
    }

    public static void setDropshipperAdditionalInfo(String dropshipperAdditionalInfo) {
        CheckoutData.dropshipperAdditionalInfo = dropshipperAdditionalInfo;
    }

    public static int getSlashedProductPrice() {
        return slashedProductPrice;
    }

    public static void setSlashedProductPrice(int slashedProductPrice) {
        CheckoutData.slashedProductPrice = slashedProductPrice;
    }

    public static String getBuyerNotes() {
        return buyerNotes;
    }

    public static void setBuyerNotes(String buyerNotes) {
        CheckoutData.buyerNotes = buyerNotes;
    }

    public static int getLogisticInsuranceNominal() {
        return logisticInsuranceNominal;
    }

    public static void setLogisticInsuranceNominal(int logisticInsuranceNominal) {
        CheckoutData.logisticInsuranceNominal = logisticInsuranceNominal;
    }

    public static int getReturInsuranceNominal() {
        return returInsuranceNominal;
    }

    public static void setReturInsuranceNominal(int returInsuranceNominal) {
        CheckoutData.returInsuranceNominal = returInsuranceNominal;
    }

    public static int getProductInsuranceNominal() {
        return productInsuranceNominal;
    }

    public static void setProductInsuranceNominal(int productInsuranceNominal) {
        CheckoutData.productInsuranceNominal = productInsuranceNominal;
    }

    public static int getCrossSelingBukaemasNominal() {
        return crossSelingBukaemasNominal;
    }

    public static void setCrossSelingBukaemasNominal(int crossSelingBukaemasNominal) {
        CheckoutData.crossSelingBukaemasNominal = crossSelingBukaemasNominal;
    }

    public static int getCourierShippingNominal() {
        return courierShippingNominal;
    }

    public static void setCourierShippingNominal(int courierShippingNominal) {
        CheckoutData.courierShippingNominal = courierShippingNominal;
    }

    public static String getCourierShippingName() {
        return courierShippingName;
    }

    public static void setCourierShippingName(String courierShippingName) {
        CheckoutData.courierShippingName = courierShippingName;
    }

    public static String getGroupCourierSHippingName() {
        return groupCourierSHippingName;
    }

    public static void setGroupCourierSHippingName(String groupCourierSHippingName) {
        CheckoutData.groupCourierSHippingName = groupCourierSHippingName;
    }

    public static int getAmountMixpaymentDANA() {
        return amountMixpaymentDANA;
    }

    public static void setAmountMixpaymentDANA(int amountMixpaymentDANA) {
        CheckoutData.amountMixpaymentDANA = amountMixpaymentDANA;
    }

    public static int getAmountMixpaymentSaldo() {
        return amountMixpaymentSaldo;
    }

    public static void setAmountMixpaymentSaldo(int amountMixpaymentSaldo) {
        CheckoutData.amountMixpaymentSaldo = amountMixpaymentSaldo;
    }

    public static String getPaymentMethod() {
        return paymentMethod;
    }

    public static void setPaymentMethod(String paymentMethod) {
        CheckoutData.paymentMethod = paymentMethod;
    }

    public static String getMixPayment() {
        return mixPayment;
    }

    public static void setMixPayment(String mixPayment) {
        CheckoutData.mixPayment = mixPayment;
    }

    public static String getMixPaymentDana() {
        return mixPaymentDana;
    }

    public static void setMixPaymentDana(String mixPaymentDana) {
        CheckoutData.mixPaymentDana = mixPaymentDana;
    }

    public static String getMixPaymentSaldo() {
        return mixPaymentSaldo;
    }

    public static void setMixPaymentSaldo(String mixPaymentSaldo) {
        CheckoutData.mixPaymentSaldo = mixPaymentSaldo;
    }

    public static int getRoundedAmount() {
        return roundedAmount;
    }

    public static void setRoundedAmount(int roundedAmount) {
        CheckoutData.roundedAmount = roundedAmount;
    }

    public static int getTotalBiayaLainnya() {
        return totalBiayaLainnya;
    }

    public static void setTotalBiayaLainnya(int totalBiayaLainnya) {
        CheckoutData.totalBiayaLainnya = totalBiayaLainnya;
    }

    public static int getTotalPaymentCheckout() {
        return totalPaymentCheckout;
    }

    public static void setTotalPaymentCheckout(int totalPaymentCheckout) {
        CheckoutData.totalPaymentCheckout = totalPaymentCheckout;
    }

    public static String getNominalVoucher() {
        return nominalVoucher;
    }

    public static void setNominalVoucher(String nominalVoucher) {
        CheckoutData.nominalVoucher = nominalVoucher;
    }

    public static String getAddressName() {
        return addressName;
    }

    public static void setAddressName(String addressName) {
        CheckoutData.addressName = addressName;
    }

    public static void resetCheckoutData() {
        setAmountMixpaymentSaldo(0);
        setAmountMixpaymentDANA(0);
        setProductPrice(0);
        setProductInsuranceNominal(0);
        setCourierShippingNominal(0);
        setLogisticInsuranceNominal(0);
        setCrossSelingBukaemasNominal(0);
        setCourierShippingNominal(0);
        setTotalBiayaLainnya(0);
        cartDetails.clear();
    }

    public static void setPaymentService(String service) {
        paymentService = service;
    }

    public static String getPaymentService() {
        return paymentService;
    }

    public static void setCartDetails(ArrayList<Map<String, Object>> cartDetail) {
        CheckoutData.cartDetails = cartDetail;
    }

    public static ArrayList<Map<String, Object>> getCartDetails() {
        return cartDetails;
    }

    public static String getProductVariant() { return productVariant; }

    public static void setProductVariant(String productVariant) {
        CheckoutData.productVariant = productVariant;
    }

    public static void setTrxId(String transactionId) {
        CheckoutData.transactionId = transactionId;
    }

    public static String getTrxId() { return transactionId; }

    public static void setInvoiceNumber(String invoiceId) {
        CheckoutData.invoiceId = invoiceId;
    }

    public static String getInvoiceId() {
        return invoiceId;
    }
}
