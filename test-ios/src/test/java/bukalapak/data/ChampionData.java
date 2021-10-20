package bukalapak.data;

public class ChampionData {

    private static String productsCampaignName;
    private static String searchKeyword;

    public static String getProductsCampaignName() {
        return ChampionData.productsCampaignName;
    }

    public static void setProductsCampaignName(String productsCampaignName) {
        ChampionData.productsCampaignName = productsCampaignName;
    }

    public static String getSearchKeyword() {
        return ChampionData.searchKeyword;
    }

    public static void setSearchKeyword(String searchKeyword) {
        ChampionData.searchKeyword = searchKeyword;
    }

    public static int priceStringToInteger(String priceStr) {
        String tmpPriceStr = priceStr.toLowerCase().replace("rp", "");;
        if (tmpPriceStr.contains("jt")) {
            tmpPriceStr = tmpPriceStr.replace("jt", "").replace(",", ".");
            return (int) (Double.parseDouble(tmpPriceStr) * 1000000);
        } else if (tmpPriceStr.contains("rb")) {
            tmpPriceStr = tmpPriceStr.replace("rb", "").replace(",", ".");
            return (int) (Double.parseDouble(tmpPriceStr) * 1000);
        } else {
            tmpPriceStr = tmpPriceStr.replace(".", "");
            return Integer.parseInt(tmpPriceStr);
        }
    }
}
