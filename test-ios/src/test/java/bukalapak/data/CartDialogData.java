package bukalapak.data;

public class CartDialogData {

    private static String productName;

    public static String getProductName() {
        return productName;
    }

    public static void setProductName(String productName) {
        CartDialogData.productName = productName;
    }
}
