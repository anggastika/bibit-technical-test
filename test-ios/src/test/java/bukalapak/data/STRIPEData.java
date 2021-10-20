package bukalapak.data;

public class STRIPEData {
    private static String jasaPengiriman;
    private static int slaDuration = 5;
    private static String productName;
    private static String resiNumber;
    private static String nomorTransaksi, catatanPembeli, receiverName;
    private static boolean multipleValidation = true;
    private static String slaType;

    public static int getSLADuration() {
        return slaDuration;
    }

    public static void setSLADuration(int slaDuration) {
        STRIPEData.slaDuration = slaDuration;
    }

    public static String getProductName() {
        return productName;
    }

    public static void setProductName(String productName) {
        STRIPEData.productName = productName;
    }

    public static String getJasaPengiriman() {
        return jasaPengiriman;
    }

    public static void setJasaPengiriman(String jasaPengiriman) {
        STRIPEData.jasaPengiriman = jasaPengiriman;
    }

    public static String getResiNumber() {
        return resiNumber;
    }

    public static void setResiNumber(String resiNumber) {
        STRIPEData.resiNumber = resiNumber;
    }

    public static String getNomorTransaksi() {
        return nomorTransaksi;
    }

    public static void setNomorTransaksi(String nomorTransaksi) {
        STRIPEData.nomorTransaksi = nomorTransaksi;
    }

    public static String getCatatanPembeli() {
        return catatanPembeli;
    }

    public static void setCatatanPembeli(String catatanPembeli) {
        STRIPEData.catatanPembeli = catatanPembeli;
    }

    public static String getNamaPenerima() {
        return receiverName;
    }

    public static void setNamaPenerima(String receiverName) {
        STRIPEData.receiverName = receiverName;
    }

    public static boolean getMultipleValidation() {
        return multipleValidation;
    }

    public static void setMultipleValidation(boolean validity) {
        STRIPEData.multipleValidation = (STRIPEData.multipleValidation && validity);
    }

    public static String getSLAType() {
        return slaType;
    }

    public static void setSLAType(String slaType) {
        STRIPEData.slaType = slaType;
    }
}
