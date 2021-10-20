package bukalapak.data;

public class RAGEData {
    private static String currentLikes;
    private static String currentDislikes;
    private static String totalProductBelumDiulas;
    private static String reviewDescription;
    private static String selectedVariant;


    public static String getCurrentLikes() {
        return currentLikes;
    }

    public static void setCurrentLikes(String currentLikes) {
        RAGEData.currentLikes = currentLikes;
    }

    public static String getCurrentDislikes() {
        return currentDislikes;
    }

    public static void setCurrentDislikes(String currentDislikes) {
        RAGEData.currentDislikes = currentDislikes;
    }

    public static String getTotalProductBelumDiulas() {
        return totalProductBelumDiulas;
    }

    public static void setTotalProductBelumDiulas(String totalProductBelumDiulas) {
        RAGEData.totalProductBelumDiulas = totalProductBelumDiulas;
    }

    public static String getReviewDescription() {
        return reviewDescription;
    }

    public static void setReviewDescription(String reviewDescription) {
        RAGEData.reviewDescription = reviewDescription;
    }

    public static String getSelectedVariant() {
        return selectedVariant;
    }

    public static void setSelectedVariant(String selectedVariant) {
        RAGEData.selectedVariant = selectedVariant;
    }

}
