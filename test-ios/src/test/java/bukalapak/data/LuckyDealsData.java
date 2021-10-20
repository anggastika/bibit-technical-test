package bukalapak.data;

public class LuckyDealsData {

    private static int eventIdLuckyDeals;
    private static int productIdLuckyDeals;

    public static void setEventIdLuckyDeals(int eventIdLuckyDeals) {
        LuckyDealsData.eventIdLuckyDeals = eventIdLuckyDeals;
    }

    public static int getEventIdLuckyDeals() {
        return eventIdLuckyDeals;
    }

    public static void setProductIdLuckyDeals(int productIdLuckyDeals) {
        LuckyDealsData.productIdLuckyDeals = productIdLuckyDeals;
    }

    public static int getProductIdLuckyDeals() {
        return productIdLuckyDeals;
    }
}
