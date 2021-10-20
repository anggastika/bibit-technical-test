package bukalapak.data.vp.prepaid;

import com.bukalapak.salad.util.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListrikPrabayarData {

    private static boolean onboarded = false;
    private static String customerId;
    private static String meterNumber;
    private static String fullName;
    private static String power;
    private static String denominationName;
    private static String denominationPrice;

    public static boolean isOnboarded() {
        return onboarded;
    }

    public static void setOnboarded(boolean onboarded) {
        ListrikPrabayarData.onboarded = onboarded;
    }

    public static String getMeterNumber() {
        return meterNumber;
    }

    public static void setMeterNumber(String meterNumber) {
        ListrikPrabayarData.meterNumber = meterNumber;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        ListrikPrabayarData.fullName = fullName;
    }

    public static String getPower() {
        return power;
    }

    public static void setPower(String power) {
        ListrikPrabayarData.power = power;
    }

    public static String getCustomerId() {
        return customerId;
    }

    public static void setCustomerId(String customerId) {
        ListrikPrabayarData.customerId = customerId;
    }

    public static String getDenominationName() {
        return denominationName;
    }

    public static void setDenominationName(String denominationName) {
        ListrikPrabayarData.denominationName = denominationName;
    }

    public static String getDenominationPrice() {
        return denominationPrice;
    }

    public static void setDenominationPrice(String denominationPrice) {
        ListrikPrabayarData.denominationPrice = denominationPrice;
    }
}
