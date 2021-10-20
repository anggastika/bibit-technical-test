package bukalapak.data;

public class UserData {

    private static String username = "";
    private static String password = "";
    private static int ownedDana = 0;
    private static int ownedCredits = 0;
    private static boolean loggedIn = false;
    private static boolean firstOpenApp = true;
    private static boolean tfaStatus;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserData.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserData.password = password;
    }

    public static void setOwnedDANA(int amount) {
        UserData.ownedDana = amount;
    }

    public static int getOwnedDANA() {
        return UserData.ownedDana;
    }

    public static void setOwnedCredits(int amount) {
        UserData.ownedCredits = amount;
    }

    public static int getOwnedCredits() {
        return UserData.ownedCredits;
    }

    public static boolean isLoggedIn() {
        return UserData.loggedIn;
    }

    public static void setLoggedIn(boolean isLoggedIn) {
        UserData.loggedIn = isLoggedIn;
    }

    public static boolean isFirstOpenApp() {
        return firstOpenApp;
    }

    public static void setFirstOpenApp(boolean firstOpenApp) {
        UserData.firstOpenApp = firstOpenApp;
    }

    public static boolean getTfaStatus() {
        return UserData.tfaStatus;
    }

    public static void setTfaStatus(boolean status) {
        UserData.tfaStatus = status;
    }
}
