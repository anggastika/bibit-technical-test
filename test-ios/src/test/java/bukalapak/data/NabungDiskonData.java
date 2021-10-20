package bukalapak.data;

public class NabungDiskonData {
    private static int totalProduct;
    private static int productId;
    private static String productName;
    private static int productCurrentStock;
    private static int productTenor;

    public static void setTotalProduct(int totalProduct) {
        NabungDiskonData.totalProduct = totalProduct;
    }

    public static int getTotalProduct() {
        return totalProduct;
    }

    public static void setProductId(int productId) {
        NabungDiskonData.productId = productId;
    }

    public static int getProductId() {
        return productId;
    }

    public static void setProductName(String productName) {
        NabungDiskonData.productName = productName;
    }

    public static String getProductName() {
        return productName;
    }

    public static void setProductCurrentStock(int productCurrentStock) {
        NabungDiskonData.productCurrentStock = productCurrentStock;
    }

    public static int getProductCurrentStock() {
        return productCurrentStock;
    }

    public static void setProductTenor(int productTenor) {
        NabungDiskonData.productTenor = productTenor;
    }

    public static int getProductTenor() {
        return productTenor;
    }
}
