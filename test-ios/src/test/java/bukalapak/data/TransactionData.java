package bukalapak.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransactionData {

    public static final int roundingCounterAmount = 500;

    private static String invoiceNo;
    private static String totalPrice;
    private static String address;
    private static String paymentMethod;
    private static String paymentMethodGroup;
    private static String paymentStatus;
    private static String roundedAmount;
    private static String totalItemsPrice;
    private static String totalShippingFee;
    private static String insuranceFee;
    private static String adminFee;
    private static int totalPayment;
    private static String courierRecommendation;
    private static ArrayList<Map<String, Object>> paymentsInfo = new ArrayList<Map<String, Object>>();
    private static ArrayList<Map<String, Object>> paymentsInfoVirtualAccount = new ArrayList<Map<String, Object>>();
    private static int minimumPaymentForMixPayment = 0;
    private static int usedDANA = 0;
    private static int usedCredits = 0;
    private static int expectedTotalBill;
    private static int quantityItem;
    private static String nomorTransaksi;
    private static String idTransaksi;
    private static String lastEdittedSelerNoteName = "";
    private static String recurringDate;
    private static String nomorKartu;
    // Map of <sellerName, sellerNote> for saved seller notes (have clicked "Simpan" button)
    private static HashMap<String, String> savedSellerNotes = new HashMap<>();
    // Map of <sellerName, sellerNote> for inputted seller notes (haven't clicked "Simpan" button)
    private static HashMap<String, String> inputtedSellerNotes = new HashMap<>();
    private static int totalTagihanTransfer;
    private static String jumlahBayarPlusUniqueAmount;
    private static String availablePromoCode;

    public static String getTotalItemsPrice() {
        return totalItemsPrice;
    }

    public static void setTotalItemsPrice(String totalItemsPrice) {
        TransactionData.totalItemsPrice = totalItemsPrice;
    }

    public static String getTotalShippingFee() {
        return totalShippingFee;
    }

    public static void setTotalShippingFee(String totalShippingFee) {
        TransactionData.totalShippingFee = totalShippingFee;
    }

    public static String getInsuranceFee() { return insuranceFee; }

    public static void setInsuranceFee(String insuranceFee) { TransactionData.insuranceFee = insuranceFee; }

    public static String getAdminFee() {
        return adminFee;
    }

    public static void setAdminFee(String adminFee) {
        TransactionData.adminFee = adminFee;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        TransactionData.address = address;
    }

    public static String getInvoiceNo() {
        return invoiceNo;
    }

    public static void setInvoiceNo(String invoiceNo) {
        TransactionData.invoiceNo = invoiceNo;
    }

    public static String getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(String price) {
        TransactionData.totalPrice = price;
    }

    public static String getRoundedAmount() {
        return roundedAmount;
    }

    public static void setRoundedAmount(String rounded) {
        TransactionData.roundedAmount = rounded;
    }

    public static String getPaymentMethod() {
        return paymentMethod;
    }

    public static void setPaymentMethod(String paymentMethod) {
        TransactionData.paymentMethod = paymentMethod;
    }

    public static String getPaymentMethodGroup() {
        return paymentMethodGroup;
    }

    public static void setPaymentMethodGroup(String paymentMethodGroup) {
        TransactionData.paymentMethodGroup = paymentMethodGroup;
    }

    public static String getPaymentStatus() {
        return paymentStatus;
    }

    public static void setPaymentStatus(String paymentStatus) {
        TransactionData.paymentStatus = paymentStatus;
    }

    public static int getTotalPayment() {
        return totalPayment;
    }

    public static void setTotalPayment(int total) {
        TransactionData.totalPayment = total;
    }

    public static String getCourierRecommendation() {
        return courierRecommendation;
    }

    public static void setCourierRecommendation(String courier) {
        TransactionData.courierRecommendation = courier;
    }

    public static ArrayList<Map<String, Object>> getPaymentsInfo() {
        return paymentsInfo;
    }

    public static void setPaymentsinfo(ArrayList<Map<String, Object>> paymentsInfo) {
        TransactionData.paymentsInfo = paymentsInfo;
    }

    public static ArrayList<Map<String, Object>> getPaymentsInfoVirtualAccount() {
        return paymentsInfoVirtualAccount;
    }

    public static void setPaymentsinfoVirtualAccount(ArrayList<Map<String, Object>> paymentsInfoVirtualAccount) {
        TransactionData.paymentsInfoVirtualAccount = paymentsInfoVirtualAccount;
    }

    public static void setMinimumPaymentForMixPayment(int minimumPaymentForMixPayment) {
        TransactionData.minimumPaymentForMixPayment = minimumPaymentForMixPayment;
    }

    public static int getMinimumPaymentForMixPayment() {
        return minimumPaymentForMixPayment;
    }

    public static int getExpectedTotalBill() {
        return expectedTotalBill;
    }

    public static void setExpectedTotalBill(int expectedTotalBill) {
        TransactionData.expectedTotalBill = expectedTotalBill;
    }

    public static int getQuantityItem() {
        return quantityItem;
    }

    public static void setQuantityItem(int quantity) {
        TransactionData.quantityItem = quantity;
    }

    public static void setUsedDANA(int amount) {
        TransactionData.usedDANA = amount;
    }

    public static int getUsedDANA() {
        return usedDANA;
    }

    public static void setUsedCredits(int amount) {
        TransactionData.usedCredits = amount;
    }

    public static int getUsedCredits() {
        return usedCredits;
    }

    public static String getLastEdittedSelerNoteName() {
        return lastEdittedSelerNoteName;
    }

    public static void setLastEdittedSelerNoteName(String lastEdittedSelerNoteName) {
        TransactionData.lastEdittedSelerNoteName = lastEdittedSelerNoteName;
    }

    public static HashMap<String, String> getSavedSellerNotes() {
        return savedSellerNotes;
    }

    public static HashMap<String, String> getInputtedSellerNotes() {
        return inputtedSellerNotes;
    }

    public static String getNomorTransaksi() {
        return nomorTransaksi;
    }

    public static void setNomorTransaksi(String nomorTransaksi) {
        TransactionData.nomorTransaksi = nomorTransaksi;
    }

    public static String getIdTransaksi() {
        return idTransaksi;
    }

    public static void setIdTransaksi(String idTransaksi) {
        TransactionData.idTransaksi = idTransaksi;
    }

    public static String getRecurringDate() { return recurringDate; }

    public static void setRecurringDate(String recurringDate) {
        TransactionData.recurringDate = recurringDate;
    }

    public static String getNomorKartu() { return nomorKartu; }

    public static void setNomorKartu(String nomorKartu) { TransactionData.nomorKartu = nomorKartu; }

    public static int getTotalTagihanTransfer() {
        return totalTagihanTransfer;
    }

    public static void setTotalTagihanTransfer(int totalTagihanTransfer) {
        TransactionData.totalTagihanTransfer = totalTagihanTransfer;
    }

    public static String getJumlahBayarPlusUniqueAmount() { return jumlahBayarPlusUniqueAmount; }

    public static void setJumlahBayarPlusUniqueAmount(String jumlahBayarPlusUniqueAmount) { TransactionData.jumlahBayarPlusUniqueAmount = jumlahBayarPlusUniqueAmount; }

    public static String getAvailablePromoCode() {
        return availablePromoCode;
    }

    public static void setAvailablePromoCode(String availablePromoCode) {
        TransactionData.availablePromoCode = availablePromoCode;
    }
}
