package bukalapak.data.vp.tix;

public class TiketBusData {

    private static String sortType;
    private static String busName;


    public static String getSortingType() {
        return sortType;
    }

    public static void setSortingType(String sortType) {
        TiketBusData.sortType = sortType;
    }
    public static String getBusName() {
        return busName;
    }

    public static void setBusName(String busName) {
        TiketBusData.busName = busName;
    }
}
