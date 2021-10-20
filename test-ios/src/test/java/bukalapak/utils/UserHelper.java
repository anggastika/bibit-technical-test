package bukalapak.utils;

import java.util.Random;

public class UserHelper {

    public static String generatePhoneNumber(String provider) {
        String prefix;
        String number = String.format("%08d", (new Random()).nextInt(100000000));

        switch (provider.toLowerCase()) {
            case "telkomsel":
                prefix = "0812";
                break;
            case "indosat":
                prefix = "0856";
                break;
            case "tri":
                prefix = "0899";
                break;
            case "smartfren":
                prefix = "0881";
                break;
            case "xl":
                prefix = "0819";
                break;
            case "bolt":
                prefix = "9999";
                break;
            case "axis":
                prefix = "0838";
                break;
            default:
                throw new RuntimeException("Unknown provider: " + provider);
        }

        return prefix + number;
    }

    public static String generatePhoneNumber() {
        return generatePhoneNumber("telkomsel");
    }
}
