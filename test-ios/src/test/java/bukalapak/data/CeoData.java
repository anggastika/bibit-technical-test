package bukalapak.data;

public class CeoData {
    //Bukareview
    private static String titleArticle;
    private static String searchResultArticle;
    private static String typeTitle;
    private static String subCategoryTitle;
    private static String tagTitle;
    private static String authorName;

    public static String getTitleArticle() {
        return titleArticle;
    }

    public static void setTitleArticle(String titleArticle) {
        CeoData.titleArticle = titleArticle;
    }

    public static String getSearchResultArticle() {
        return searchResultArticle;
    }

    public static void setSearchResultArticle(String searchResultArticle) {
        CeoData.searchResultArticle = searchResultArticle;
    }

    public static String getTypeTitle() {
        return typeTitle;
    }

    public static void setTypeTitle(String typeTitle) {
        CeoData.typeTitle = typeTitle;
    }

    public static String getSubCategoryTitle() {
        return subCategoryTitle;
    }

    public static void setSubCategoryTitle(String subCategoryTitle) {
        CeoData.subCategoryTitle = subCategoryTitle;
    }

    public static String getTagTitle() {
        return tagTitle;
    }

    public static void setTagTitle(String tagTitle) {
        CeoData.tagTitle = tagTitle;
    }

    public static String getAuthorName() {
        return authorName;
    }

    public static void setAuthorName(String authorName) {
        CeoData.authorName = authorName;
    }

}
