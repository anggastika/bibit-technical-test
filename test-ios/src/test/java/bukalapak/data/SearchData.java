package bukalapak.data;
import java.util.ArrayList;
import java.util.List;

public class SearchData {
    private static String searchKeywords;
    private static String removedHistory;
    private static String searchBrandName;
    private static String categoryList;
    private static Integer selectedFilterRating;
    private static String address;
    private static List<String> titles;
    private static Boolean isOnBoard = false;

    public static void setSearchKeywords(String keywords){
        if(keywords == null) {
            return;
        }

        searchKeywords = keywords.trim();
    }

    public static String getSearchKeywords(){
        return searchKeywords;
    }

    public static void setSearchBrandName(String brand) {
        searchBrandName = brand;
    }

    public static String getSearchBrandName() {
        return searchBrandName;
    }

    public static void setCategory(String list) {
        categoryList = list;
    }

    public static String getCategory() {
        return categoryList;
    }

    public static String[] getCategories() {
        return categoryList.split(" > ");
    }

    public static void setSelectedFilterRating(String selectedFilterRating) {
        SearchData.selectedFilterRating = Integer.parseInt(selectedFilterRating);
    }

    public static Integer getSelectedFilterRating() {
        return selectedFilterRating;
    }

    public static void setRemovedHistory(String keyword) {
        removedHistory = keyword;
    }

    public static String getRemovedHistory() {
        return removedHistory;
    }

    public static void setAddress(String address) {
        SearchData.address = address;
    }

    public static String getAddress() {
        return address;
    }

    public static void setOnBoard(Boolean isOnBoard) {
        SearchData.isOnBoard = isOnBoard;
    }

    public static Boolean getOnBoard() {
        return isOnBoard;
    }

    public static List<String> getSpecialCards() {
        List<String> cards = new ArrayList<>();

        cards.add("continuation");
        cards.add("best_selling");
        cards.add("nudge");
        cards.add("games");

        return cards;
    }

    public static void setProductsTitles(List<String> titles) {
        SearchData.titles = titles;
    }

    public static List<String> getProductsTitles() {
        return titles;
    }
}
