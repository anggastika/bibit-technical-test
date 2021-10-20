package bukalapak.data;

public class BukaMobilData {
    private static boolean onboarded = false;
    private static String lokasi;

    public static boolean isOnboarded() {
        return onboarded;
    }

    public static void setOnboarded(boolean onboarded) {
        BukaMobilData.onboarded = onboarded;
    }

    public static String getLokasi() {
        return lokasi;
    }

    public static void setLokasi(String lokasi) {
        BukaMobilData.lokasi = lokasi;
    }
}
