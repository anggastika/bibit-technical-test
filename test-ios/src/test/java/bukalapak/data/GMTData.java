package bukalapak.data;

public class GMTData {

    private static boolean brazePopupState;
    private static String nikVaccineRegistration;

    public static void setBrazePopupState(Boolean element) {
        GMTData.brazePopupState = element;
    }

    public static Boolean getBrazePopupState() {
        return brazePopupState;
    }

    public static String getNIK() {
        return nikVaccineRegistration;
    }

    public static void setNIK(String nik) {
        GMTData.nikVaccineRegistration = nik;
    }
}
