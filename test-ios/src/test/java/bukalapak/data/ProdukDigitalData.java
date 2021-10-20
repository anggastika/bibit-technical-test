package bukalapak.data;

public class ProdukDigitalData {
    private static String produkDigitalName;
    private static String produkDigitalPrice;

    public static String getProdukDigitalName() {
        return produkDigitalName;
    }

    public static void setProdukDigitalName(String produkDigitalName) {
        ProdukDigitalData.produkDigitalName = produkDigitalName;
    }

    public static String getProdukDigitalPrice() {
        return produkDigitalPrice;
    }

    public static void setProdukDigitalPrice(String produkDigitalPrice) {
        ProdukDigitalData.produkDigitalPrice = produkDigitalPrice;
    }
}
