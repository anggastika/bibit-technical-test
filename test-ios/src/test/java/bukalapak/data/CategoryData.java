package bukalapak.data;

import java.util.ArrayList;

public class CategoryData {

    private static String categoryName;
    private static String productNamePopular;
    private static String productNameRecommendation;
    private static int popularAmout;
    private static ArrayList<String> recoProductList = new ArrayList<String>();

    public static String getCategoryName() {
        return categoryName;
    }

    public static String setCategoryName(String categoryName) {
        CategoryData.categoryName = categoryName;
        return categoryName;
    }

    public static String getProductNamePopular() {
        return productNamePopular;
    }

    public static String setProductNamePopular(String productNamePopular) {
        CategoryData.productNamePopular = productNamePopular;
        return productNamePopular;
    }

    public static String getProductNameRecommendation() {
        return productNameRecommendation;
    }

    public static String setProductNameRecommendation(String productNameRecommendation) {
        CategoryData.productNameRecommendation = productNameRecommendation;
        return productNameRecommendation;
    }

    public static int getPopularAmout() {
        return popularAmout;
    }

    public static int setPopularAmout(int popularAmout) {
        CategoryData.popularAmout = popularAmout;
        return popularAmout;
    }

    public static void setRecoProductList(String productName) {
        recoProductList.add(productName);
    }

    public static ArrayList<String> getRecoProductList() {
        return recoProductList;
    }
}
