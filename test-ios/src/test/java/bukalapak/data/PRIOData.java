package bukalapak.data;

import java.util.ArrayList;

public class PRIOData {
    //priority buyer
    private static int crossSellingPriorityTotalPayment;
    private static int ongkirBenefit;

    //automatic review
    private static ArrayList<String> numberOfReview = new ArrayList<>();
    private static ArrayList<String> numberOfItems = new ArrayList<>();
    private static ArrayList<String> numberOfReviewPerItem = new ArrayList<>();
    private static ArrayList<String> duration = new ArrayList<>();
    private static ArrayList<String> normalPrice = new ArrayList<>();
    private static int numberOfPromo;
    private static ArrayList<String> promoPrice = new ArrayList<>();
    private static boolean subscriptionStatus;

    public static ArrayList<String> getNumberOfReview() {
        return numberOfReview;
    }

    public static void setNumberOfReview(String numberOfReview1) {
        PRIOData.numberOfReview.add(numberOfReview1);
    }

    public static ArrayList<String> getNumberOfItems() {
        return numberOfItems;
    }

    public static void setNumberOfItems(String numberOfProducts1) {
        PRIOData.numberOfItems.add(numberOfProducts1);
    }

    public static ArrayList<String> getNumberOfReviewPerItem() {
        return numberOfReviewPerItem;
    }

    public static void setNumberOfReviewPerItem(String numberOfReviewPerProduct1) {
        PRIOData.numberOfReviewPerItem.add(numberOfReviewPerProduct1);
    }

    public static ArrayList<String> getDuration() {
        return duration;
    }

    public static void setDuration(String duration1) {
        PRIOData.duration.add(duration1);
    }

    public static ArrayList<String> getNormalPrice() {
        return normalPrice;
    }

    public static void setNormalPrice(String normalPrice1) {
        PRIOData.normalPrice.add(normalPrice1);
    }

    public static int getNumberOfPromo() {
        return numberOfPromo;
    }

    public static void setNumberOfPromo(int numberOfPromo1) {
        PRIOData.numberOfPromo = numberOfPromo1;
    }

    public static ArrayList<String> getPromoPrice() {
        return promoPrice;
    }

    public static void setPromoPrice(String promoPrice1) {
        PRIOData.promoPrice.add(promoPrice1);
    }

    public static String getRealPrice(int automaticPackage) {
        String price = PRIOData.getPromoPrice().get(automaticPackage - 1);
        if (price.equals("0")) {
            price = PRIOData.getNormalPrice().get(automaticPackage - 1);
        }
        return price;
    }

    public static boolean getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public static void setSubscriptionStatus(boolean isSubscribed) {
        PRIOData.subscriptionStatus = isSubscribed;
    }

    //priority buyer
    public static int getCrossSellingPriorityTotalPayment() {
        return crossSellingPriorityTotalPayment;
    }

    public static void setCrossSellingPriorityTotalPayment(int crossSellingPriorityTotalPayment) {
        PRIOData.crossSellingPriorityTotalPayment = crossSellingPriorityTotalPayment;
    }

    public static int getOngkirBenefit() {
        return ongkirBenefit;
    }

    public static void setOngkirBenefit(int ongkirBenefit) {
        PRIOData.ongkirBenefit = ongkirBenefit;
    }
}
