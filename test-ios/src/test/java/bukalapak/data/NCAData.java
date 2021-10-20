package bukalapak.data;
import com.github.javafaker.Faker;

public class NCAData {
    private static String phoneNumber;

    public static String getPhone() { return phoneNumber; }

    public static void setPhone(String phone) { NCAData.phoneNumber = phone; }

    //generate random username
    public static String generateRandomUsername(String prefixUsername, int digits) {
        return prefixUsername + new Faker().number().digits(digits);
    }
}
