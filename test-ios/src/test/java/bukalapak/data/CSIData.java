package bukalapak.data;

public class CSIData {

    private static String nomorTransaksi;
    private static String nomorTagihan;
    private static String jenisMasalah;
    private static String diskusiText;
    private static int notifProfileCount;
    private static String idComplaint;
    private static String receiptImage;
    private static String addressChanged;


    public static String getNomorTransaksi() {
        return nomorTransaksi;
    }

    public static void setNomorTransaksi(String nomorTransaksi) {
        CSIData.nomorTransaksi = nomorTransaksi;
    }

    public static String getNomorTagihan() {
        return nomorTagihan;
    }

    public static void setNomorTagihan(String nomorTagihan) {
        CSIData.nomorTagihan = nomorTagihan;
    }

    public static String getJenisMasalah() {
        return jenisMasalah;
    }

    public static void setJenisMasalah(String jenisMasalah) {
        CSIData.jenisMasalah = jenisMasalah;
    }

    public static String getDiskusiText() {
        return diskusiText;
    }

    public static void setDiskusiText(String diskusiText) {
        CSIData.diskusiText = diskusiText;
    }

    public static String getIdComplaint() {
        return idComplaint;
    }

    public static void setIdComplaint(String idComplaint) {
        CSIData.idComplaint = idComplaint;
    }

    public static void setReceiptImage(String imageTitle) {
        CSIData.receiptImage = imageTitle;
    }

    public static String getReceiptImage() {
        return  receiptImage;
    }
    public static void setNotifCount(int notifProfileCount) {
        CSIData.notifProfileCount = notifProfileCount;
    }

    public static int getNotifCount() {
        return notifProfileCount;
    }

    public static void setAddressChanged(String addressChanged) { CSIData.addressChanged = addressChanged; }

    public static String getAddressChanged() { return addressChanged; }

}
