package bukalapak.data;

/**
 * @Author: Ayu Musfita
 * @Date: 03/01/19, Thu
 **/
public class BusData {
    private static String busDestinationCity;
    private static String busOriginCity;
    private static String busBoardingPoint;
    private static String busReturnBoardingPoint;
    private static String busDroppingPoint;
    private static String busReturnDroppingPoint;
    private static String busTripTime;
    private static String busReturnTripTime;
    private static String busTravelName;
    private static String busReturnTravelName;
    private static String busAvailableSeat;
    private static String busPriceFare;
    private static String busReturnPriceFare;
    private static String busDetailOrigin;
    private static String busReturnDetailOrigin;
    private static String busDetailDestination;
    private static String busReturnDetailDestination;
    private static String busSeatNumber;
    private static String busReturnSeatNumber;
    private static String orderName;
    private static String orderEmail;
    private static String orderPhone;
    private static String tripType;
    private static int countPassenger;
    private static boolean coachMarked = false;

    public static final String invalidNameFormatError = "Format nama hanya boleh terdiri oleh huruf dan spasi";
    public static final String invalidPhoneNumberFormatError = "Format tidak valid.";
    public static final String invalidEmailFormatError = "Format e-mail salah";

    public static String getBusDestinationCity() {
        return busDestinationCity;
    }

    public static void setBusDestinationCity(String busDestinationCity) {
        BusData.busDestinationCity = busDestinationCity;
    }

    public static String getBusOriginCity() {
        return busOriginCity;
    }

    public static void setBusOriginCity(String busOriginCity) {
        BusData.busOriginCity = busOriginCity;
    }

    public static String getBusBoardingPoint() {
        return busBoardingPoint;
    }

    public static void setBusBoardingPoint(String busBoardingPoint) {
        BusData.busBoardingPoint = busBoardingPoint;
    }

    public static String getBusReturnBoardingPoint() {
        return busReturnBoardingPoint;
    }

    public static void setBusReturnBoardingPoint(String busReturnBoardingPoint) {
        BusData.busReturnBoardingPoint = busReturnBoardingPoint;
    }

    public static String getBusDroppingPoint() {
        return busDroppingPoint;
    }

    public static void setBusDroppingPoint(String busDroppingPoint) {
        BusData.busDroppingPoint = busDroppingPoint;
    }

    public static String getBusReturnDroppingPoint() {
        return busReturnDroppingPoint;
    }

    public static void setBusReturnDroppingPoint(String busReturnDroppingPoint) {
        BusData.busReturnDroppingPoint = busReturnDroppingPoint;
    }

    public static String getBusTripTime() {
        return busTripTime;
    }

    public static void setBusTripTime(String busTripTime) {
        BusData.busTripTime = busTripTime;
    }

    public static String getBusReturnTripTime() {
        return busReturnTripTime;
    }

    public static void setBusReturnTripTime(String busReturnTripTime) {
        BusData.busReturnTripTime = busReturnTripTime;
    }

    public static String getBusTravelName() {
        return busTravelName;
    }

    public static void setBusTravelName(String busTravelName) {
        BusData.busTravelName = busTravelName;
    }

    public static String getBusReturnTravelName() {
        return busReturnTravelName;
    }

    public static void setBusReturnTravelName(String busReturnTravelName) {
        BusData.busReturnTravelName = busReturnTravelName;
    }

    public static String getBusAvailableSeat() {
        return busAvailableSeat;
    }

    public static void setBusAvailableSeat(String busAvailableSeat) {
        BusData.busAvailableSeat = busAvailableSeat;
    }

    public static String getBusPriceFare() {
        return busPriceFare;
    }

    public static void setBusPriceFare(String busPriceFare) {
        BusData.busPriceFare = busPriceFare;
    }

    public static String getBusReturnPriceFare() {
        return busReturnPriceFare;
    }

    public static void setBusReturnPriceFare(String busReturnPriceFare) {
        BusData.busReturnPriceFare = busReturnPriceFare;
    }

    public static String getBusDetailOrigin() {
        return busDetailOrigin;
    }

    public static void setBusDetailOrigin(String busDetailOrigin) {
        BusData.busDetailOrigin = busDetailOrigin;
    }

    public static String getBusReturnDetailOrigin() {
        return busReturnDetailOrigin;
    }

    public static void setBusReturnDetailOrigin(String busReturnDetailOrigin) {
        BusData.busReturnDetailOrigin = busReturnDetailOrigin;
    }

    public static String getBusDetailDestination() {
        return busDetailDestination;
    }

    public static void setBusDetailDestination(String busDetailDestination) {
        BusData.busDetailDestination = busDetailDestination;
    }

    public static String getBusReturnDetailDestination() {
        return busReturnDetailDestination;
    }

    public static void setBusReturnDetailDestination(String busReturnDetailDestination) {
        BusData.busReturnDetailDestination = busReturnDetailDestination;
    }

    public static String getBusSeatNumber() {
        return busSeatNumber;
    }

    public static void setBusSeatNumber(String busSeatNumber) {
        BusData.busSeatNumber = busSeatNumber;
    }

    public static String getBusReturnSeatNumber() {
        return busReturnSeatNumber;
    }

    public static void setBusReturnSeatNumber(String busReturnSeatNumber) {
        BusData.busReturnSeatNumber = busReturnSeatNumber;
    }

    public static String getOrderName() {
        return orderName;
    }

    public static void setOrderName(String orderName) {
        BusData.orderName = orderName;
    }

    public static String getOrderEmail() {
        return orderEmail;
    }

    public static void setOrderEmail(String orderEmail) {
        BusData.orderEmail = orderEmail;
    }

    public static String getOrderPhone() {
        return orderPhone;
    }

    public static void setOrderPhone(String orderPhone) {
        BusData.orderPhone = orderPhone;
    }

    public static String getTripType() {
        return tripType;
    }

    public static void setTripType(String tripType) {
        BusData.tripType = tripType;
    }

    public static boolean isCoachMarked() {
        return coachMarked;
    }

    public static void setCoachMarked(boolean coachMarked) {
        BusData.coachMarked = coachMarked;
    }

    public static int getCountPassenger() {
        return countPassenger;
    }

    public static void setCountPassenger(int countPassenger) {
        BusData.countPassenger = countPassenger;
    }
}
