package bukalapak.data;

/**
 * @Author: Fixco Amrizal Candra
 **/

public class EventData {
    private static boolean coachMarked = false;
    private static String eventTitle;
    private static String eventPrice;
    private static String eventCategory;
    private static String eventID;

    public static boolean isCoachMarked() {
        return coachMarked;
    }

    public static void setCoachMarked(boolean coachMarked) {
        EventData.coachMarked = coachMarked;
    }

    public static void setEventTitle(String title) {
        EventData.eventTitle = title;
    }

    public static String getEventTitle() {
        return eventTitle;
    }

    public static void setEventPrice(String price) {
        EventData.eventPrice = price;
    }

    public static String getEventPrice() {
        return eventPrice;
    }

    public static void setEventCategory(String category) {
        EventData.eventCategory = category;
    }

    public static String getEventCategory() {
        return eventCategory;
    }

    public static void setEventID(String id) {
        EventData.eventID = id;
    }

    public static String getEventID() {
        return eventID;
    }

}
