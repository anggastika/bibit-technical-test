package bukalapak.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DANAData {

    private static String noTagihan;
    private static String noTrx;
    private static String invID;
    private static String amountDonation;
    private static int danaBalance;
    private static int creditsBalance;
    private static int dompetBalance;
    private static int totalAmountTrx;
    private static int mixAmountTrx;

    public static String getNoTagihan() {
        return noTagihan;
    }

    public static void setNoTagihan(String noTagihanDANA) {
        DANAData.noTagihan = noTagihanDANA;
    }

    public static String getNoTrx() {
        return noTrx;
    }

    public static void setNoTrx(String noTrxDANA) {
        DANAData.noTrx = noTrxDANA;
    }

    public static String getInvoiceID() {
        return invID;
    }

    public static void setInvoiceID(String invIdDANA) {
        DANAData.invID = invIdDANA;
    }

    public static String getCurrentDateMutation() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dtf.format(LocalDateTime.now());
    }

    public static void setAmountDonation(String amountDonation) { DANAData.amountDonation = amountDonation; }

    public static String getAmountDonation() {
        return amountDonation;
    }

    public static void setDanaBalance(int danaBalance) {
        DANAData.danaBalance = danaBalance;
    }

    public static Integer getDanaBalance() {
        return danaBalance;
    }

    public static void setCreditsBalance(int creditsBalance) { DANAData.creditsBalance = creditsBalance; }

    public static Integer getCreditsBalance() {
        return creditsBalance;
    }

    public static void setBukaDompetBalance(int dompetBalance) { DANAData.dompetBalance = dompetBalance; }

    public static Integer getBukaDompetBalance() {
        return dompetBalance;
    }

    public static void setTotalAmountTrx(int totalAmountTrx) { DANAData.totalAmountTrx = totalAmountTrx; }

    public static Integer getTotalAmountTrx() {
        return totalAmountTrx;
    }

    public static void setMixAmountTrx(int mixAmountTrx) { DANAData.mixAmountTrx = mixAmountTrx; }

    public static Integer getMixAmountTrx() { return mixAmountTrx; }
}
