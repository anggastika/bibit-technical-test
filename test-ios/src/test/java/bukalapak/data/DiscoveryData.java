package bukalapak.data;

import java.util.ArrayList;

public class DiscoveryData {
    private static String promoButtonName;
    private static String homeKeywordSuggestion;
    private static String categoryKeywordSuggestion;
    private static ArrayList<String> dopeList = new ArrayList<String>();
    private static String dopeName;
    private static String categoryDopeName;
    private static String brandSectionType;

    public static String getBrandSectionType() {
        return brandSectionType;
    }

    public static void setBrandSectionType(String brandSectionType) {
        DiscoveryData.brandSectionType = brandSectionType;
    }

    public static String getCategoryDopeName() {
        return categoryDopeName;
    }

    public static void setCategoryDopeName(String categoryDopeName) {
        DiscoveryData.categoryDopeName = categoryDopeName;
    }

    public static String getDopeName() {
        return dopeName;
    }

    public static void setDopeName(String dopeName) {
        DiscoveryData.dopeName = dopeName;
    }

    public static String getPromoButtonName() {
        return promoButtonName;
    }

    public static void setPromoButtonName(String promoButtonName) {
        DiscoveryData.promoButtonName = promoButtonName;
    }

    public static String getHomeKeywordSuggestion() {
        return homeKeywordSuggestion;
    }

    public static void setHomeKeywordSuggestion(String homeKeywordSuggestion) {
        DiscoveryData.homeKeywordSuggestion = homeKeywordSuggestion;
    }

    public static String getCategoryKeywordSuggestion() {
        return categoryKeywordSuggestion;
    }

    public static void setCategoryKeywordSuggestion(String categoryKeywordSuggestion) {
        DiscoveryData.categoryKeywordSuggestion = categoryKeywordSuggestion;
    }

    public static void setDopeList(String productTitle) {
        dopeList.add(productTitle);
    }

    public static ArrayList<String> getDopeList() { return dopeList; }

}
