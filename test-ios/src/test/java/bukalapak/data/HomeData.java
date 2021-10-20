package bukalapak.data;

public class HomeData {

    private static boolean onBoardingState = false;
    private static boolean danaOnBoardingState = false;
    private static boolean voucherOnHomePageState = false;

    public static boolean getOnBoardingState() {
        return onBoardingState;
    }

    public static void setOnBoardingState(boolean onBoardingState) {
        HomeData.onBoardingState = onBoardingState;
    }

    public static boolean getDanaOnBoardingState() {
        return danaOnBoardingState;
    }

    public static void setDanaOnBoardingState(boolean danaOnBoardingState) {
        HomeData.danaOnBoardingState = danaOnBoardingState;
    }

    public static boolean getVoucherOnHomePageState() {
        return voucherOnHomePageState;
    }

    public static void setVoucherOnHomePageState(boolean voucherOnHomePageState) {
        HomeData.voucherOnHomePageState = voucherOnHomePageState;
    }
}
