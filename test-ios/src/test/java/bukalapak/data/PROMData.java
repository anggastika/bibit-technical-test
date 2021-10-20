package bukalapak.data;

import java.util.ArrayList;
import java.util.List;

public class PROMData {

    private static int pushBulkTotal;
    private static int sisaBudget;
    private static int saldoKamu;
    private static int bid;
    private static int bidSet;
    private static int nominalBudgetNonBonus;
    private static int nominalBudgetBonus;
    private static int sisaPush;
    private static int pricePackage;
    private static boolean promotedDailyBudgetStatusActive;
    private static boolean promotedDailyLoanActive;
    private static int inputtedBudget;
    private static String selectedPushPackageName;
    private static String selectedPushPackagePrice;
    private static String selectedPushPackageBonus;
    private static List<String> pushPackageName = new ArrayList<>();
    private static List<String> pushPackageNormalPrice = new ArrayList<>();
    private static List<String> pushPackageDiscountedPrice = new ArrayList<>();
    private static List<String> pushPackageBonus = new ArrayList<>();
    private static List<String> pushPackageDuration = new ArrayList<>();
    private static int dailyBudget;
    private static String iklanLapakPromotionName;
    private static String iklanLapakPromotionCategory;
    private static String iklanLapakTotalProducts;
    private static String promotedBonusPercentage;
    private static int pushPrice;
    // CRM
    private static ArrayList<String> crmProductSelectionName = new ArrayList<>();
    private static ArrayList<String> crmProductSelectionPrice = new ArrayList<>();
    private static ArrayList<String> crmProductSelectedName = new ArrayList<>();
    private static ArrayList<String> crmProductSelectedPrice = new ArrayList<>();
    private static int totalSelectedCRMProducts;

    public static int getSisaBudget() {
        return sisaBudget;
    }

    public static void setSisaBudget(int sisaBudget) {
        PROMData.sisaBudget = sisaBudget;
    }

    public static int getSaldoKamu() {
        return saldoKamu;
    }

    public static void setSaldoKamu(int saldoKamu) {
        PROMData.saldoKamu = saldoKamu;
    }

    public static int getBid() {
        return bid;
    }

    public static void setBid(int bid) {
        PROMData.bid = bid;
    }

    public static int getBidSet() {
        return bidSet;
    }

    public static void setBidSet(int bidSet) {
        PROMData.bidSet = bidSet;
    }

    public static int getNominalBudgetNonBonus() {
        return nominalBudgetNonBonus;
    }

    public static void setNominalBudgetNonBonus(int nominalBudgetNonBonus) {
        PROMData.nominalBudgetNonBonus = nominalBudgetNonBonus;
    }

    public static int getNominalBudgetBonus() {
        return nominalBudgetBonus;
    }

    public static void setNominalBudgetBonus(int nominalBudgetBonus) {
        PROMData.nominalBudgetBonus = nominalBudgetBonus;
    }

    public static int getSisaPush() {
        return sisaPush;
    }

    public static void setSisaPush(int sisaPush) {
        PROMData.sisaPush = sisaPush;
    }

    public static int getPricePackage() {
        return pricePackage;
    }

    public static void setPricePackage(int pricePackage) {
        PROMData.pricePackage = pricePackage;
    }

    public static boolean isPromotedDailyBudgetStatusActive() {
        return promotedDailyBudgetStatusActive;
    }

    public static void setPromotedDailyBudgetStatusActive(boolean state) {
        PROMData.promotedDailyBudgetStatusActive = state;
    }

    public static boolean isPromotedDailyLoanActive() {
        return promotedDailyLoanActive;
    }

    public static void setPromotedDailyLoanActive(boolean state) {
        PROMData.promotedDailyLoanActive = state;
    }

    public static int getInputtedBudget() {
        return inputtedBudget;
    }

    public static void setInputtedBudget(int budget) {
        PROMData.inputtedBudget = budget;
    }

    public static String getSelectedPushPackageName() {
        return selectedPushPackageName;
    }

    public static void setSelectedPushPackageName(String pushName) {
        PROMData.selectedPushPackageName = pushName;
    }

    public static String getSelectedPushPackagePrice() {
        return selectedPushPackagePrice;
    }

    public static void setSelectedPushPackagePrice(String pushPrice) {
        PROMData.selectedPushPackagePrice = pushPrice;
    }

    public static String getSelectedPushPackageBonus() {
        return selectedPushPackageBonus;
    }

    public static void setSelectedPushPackageBonus(String pushBonus) {
        PROMData.selectedPushPackageBonus = pushBonus;
    }

    public static List<String> getPushPackageName() {
        return pushPackageName;
    }

    public static void setPushPackageName(List<String> pushPackageNames1) {
        PROMData.pushPackageName = pushPackageNames1;
    }

    public static List<String> getPushPackageNormalPrice() {
        return pushPackageNormalPrice;
    }

    public static void setPushPackageNormalPrice(List<String> pushPackageNormalPrice1) {
        PROMData.pushPackageNormalPrice = pushPackageNormalPrice1;
    }

    public static List<String> getPushPackageDiscountedPrice() {
        return pushPackageDiscountedPrice;
    }

    public static void setPushPackageDiscountedPrice(List<String> pushPackageDiscountedPrice1) {
        PROMData.pushPackageDiscountedPrice = pushPackageDiscountedPrice1;
    }

    public static List<String> getPushPackageBonus() {
        return pushPackageBonus;
    }

    public static void setPushPackageBonus(List<String> pushPackageBonus1) {
        PROMData.pushPackageBonus = pushPackageBonus1;
    }

    public static List<String> getPushPackageDuration() {
        return pushPackageDuration;
    }

    public static void setPushPackageDuration(List<String> pushPackageDuration1) {
        PROMData.pushPackageDuration = pushPackageDuration1;
    }

    public static int getDailyBudget() {
        return dailyBudget;
    }

    public static void setDailyBudget(int dailyBudget) {
        PROMData.dailyBudget = dailyBudget;
    }

    public static int getPushBulkTotal() {
        return pushBulkTotal;
    }

    public static void setPushBulkTotal(int pushBulkTotal) {
        PROMData.pushBulkTotal = pushBulkTotal;
    }

    public static String getIklanLapakPromotionName() {
        return iklanLapakPromotionName;
    }

    public static void setIklanLapakPromotionName(String iklanLapakPromotionName) {
        PROMData.iklanLapakPromotionName = iklanLapakPromotionName;
    }

    public static String getPromotedBonusPercentage() {
        return promotedBonusPercentage;
    }

    public static void setPromotedBonusPercentage(String promotedBonusPercentage) {
        PROMData.promotedBonusPercentage = promotedBonusPercentage;
    }

    public static void setIklanLapakTotalProducts(String valueOf) {
        PROMData.iklanLapakTotalProducts = valueOf;
    }

    public static String getIklanLapakTotalProducts() {
        return iklanLapakTotalProducts;
    }

    public static void setIklanLapakCategoryName(String categoryName) {
    }

    public static String getIklanLapakPromotionCategory() {
        return iklanLapakPromotionCategory;
    }

    public static void setIklanLapakPromotionCategory(String iklanLapakPromotionCategory) {
        PROMData.iklanLapakPromotionCategory = iklanLapakPromotionCategory;
    }

    public static int getPushPrice() {
        return pushPrice;
    }

    public static void setPushPrice(int pushPrice) {
        PROMData.pushPrice = pushPrice;
    }

    // CRM
    public static ArrayList<String> getCrmProductSelectionName() {
        return crmProductSelectionName;
    }

    public static void setCrmProductSelectionName(ArrayList<String> crmProductSelectionName) {
        PROMData.crmProductSelectionName = crmProductSelectionName;
    }

    public static ArrayList<String> getCrmProductSelectionPrice() {
        return crmProductSelectionPrice;
    }

    public static void setCrmProductSelectionPrice(ArrayList<String> crmProductSelectionPrice) {
        PROMData.crmProductSelectionPrice = crmProductSelectionPrice;
    }

    public static ArrayList<String> getCrmProductSelectedName() {
        return crmProductSelectedName;
    }

    public static void setCrmProductSelectedName(ArrayList<String> crmProductSelectionName) {
        PROMData.crmProductSelectedName = crmProductSelectionName;
    }

    public static ArrayList<String> getCrmProductSelectedPrice() {
        return crmProductSelectedPrice;
    }

    public static void setCrmProductSelectedPrice(ArrayList<String> crmProductSelectionPrice) {
        PROMData.crmProductSelectedPrice = crmProductSelectionPrice;
    }

    public static int getTotalSelectedCRMProducts() {
        return totalSelectedCRMProducts;
    }

    public static void setTotalSelectedCRMProducts(int totalSelectedCRMProducts) {
        PROMData.totalSelectedCRMProducts = totalSelectedCRMProducts;
    }
}
