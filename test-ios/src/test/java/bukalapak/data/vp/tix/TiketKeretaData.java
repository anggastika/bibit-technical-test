package bukalapak.data.vp.tix;

public class TiketKeretaData {

    private static int departurePrice;
    private static int returnPrice;
    private static int numberOfPassenger;
    private static String originStation;
    private static String destinationStation;
    private static boolean coachMarked = false;
    private static String departureDate;
    private static String returnDate;
    private static String departureTrainName;
    private static String returnTrainName;
    private static String departureTimeOrigin;
    private static String departureTimeArrival;
    private static String returnTimeOrigin;
    private static String returnTimeArrival;
    private static String departureOriginStationCode;
    private static String departureDestinationStationCode;
    private static String trainClassDeparture;
    private static String trainClassReturn;
    private static String passangerName;
    private static String passangerID;
    private static String trainSeatNumberDeparture;
    private static String trainSeatNumberReturn;
    private static String trainWagonDeparture;
    private static String trainWagonReturn;
    private static String buyerName;
    private static String buyerPhone;
    private static String buyerEmail;
    private static int insurancePrice;
    public static final String invalidEmailFormatError = "Format e-mail salah";
    public static final String minLengthPhoneError = "No. handphone minimum 10 karakter";
    public static final String invalidNameFormatError = "Format nama hanya boleh terdiri oleh huruf dan spasi";
    public static final String minLengthIdentityError = "No. Identitas minimum 6 karakter";
    private static int totalAmount;
    private static String trainTripType;
    private static String returnOriginStationCode;
    private static String returnDestinationStationCode;
    private static String sort;
    private static String voucherCode;

    public static void setDeparturePrice(int departurePrice) {
        TiketKeretaData.departurePrice = departurePrice;
    }

    public static int getReturnPrice() {
        return returnPrice;
    }

    public static void setReturnPrice(int returnPrice) {
        TiketKeretaData.returnPrice = returnPrice;
    }

    public static int getNumberOfPassenger() {
        return numberOfPassenger;
    }

    public static void setNumberOfPassenger(int numberOfPassenger) {
        TiketKeretaData.numberOfPassenger = numberOfPassenger;
    }

    public static String getOriginStation() {
        return originStation;
    }

    public static void setOriginStation(String originStation) {
        TiketKeretaData.originStation = originStation;
    }

    public static String getDestinationStation() {
        return destinationStation;
    }

    public static void setDestinationStation(String destinationStation) {
        TiketKeretaData.destinationStation = destinationStation;
    }

    public static boolean isCoachMarked() {
        return coachMarked;
    }

    public static void setCoachMarked(boolean coachMarked) {
        TiketKeretaData.coachMarked = coachMarked;
    }

    public static String getInvalidEmailFormatError() {
        return invalidEmailFormatError;
    }

    public static String getMinLengthPhoneError() {
        return minLengthPhoneError;
    }

    public static String getInvalidNameFormatError() {
        return invalidNameFormatError;
    }

    public static String getMinLengthIdentityError() {
        return minLengthIdentityError;
    }

    public static String getTrainWagonDeparture() {
        return trainWagonDeparture;
    }

    public static void setTrainWagonDeparture(String trainWagonDeparture) {
        TiketKeretaData.trainWagonDeparture = trainWagonDeparture;
    }

    public static String getTrainWagonReturn() {
        return trainWagonReturn;
    }

    public static void setTrainWagonReturn(String trainWagonReturn) {
        TiketKeretaData.trainWagonReturn = trainWagonReturn;
    }

    public static String getTrainSeatNumberReturn() {
        return trainSeatNumberReturn;
    }

    public static void setTrainSeatNumberReturn(String trainSeatNumberReturn) {
        TiketKeretaData.trainSeatNumberReturn = trainSeatNumberReturn;
    }

    public static int getInsurancePrice() {
        return insurancePrice;
    }

    public static void setInsurancePrice(int insurancePrice) {
        TiketKeretaData.insurancePrice = insurancePrice;
    }

    public static String getBuyerName() {
        return buyerName;
    }

    public static void setBuyerName(String buyerName) {
        TiketKeretaData.buyerName = buyerName;
    }

    public static String getBuyerPhone() {
        return buyerPhone;
    }

    public static void setBuyerPhone(String buyerPhone) {
        TiketKeretaData.buyerPhone = buyerPhone;
    }

    public static String getBuyerEmail() {
        return buyerEmail;
    }

    public static void setBuyerEmail(String buyerEmail) {
        TiketKeretaData.buyerEmail = buyerEmail;
    }

    public static String getTrainClassReturn() {
        return trainClassReturn;
    }

    public static void setTrainClassReturn(String trainClassReturn) {
        TiketKeretaData.trainClassReturn = trainClassReturn;
    }

    public static String getTrainSeatNumberDeparture() {
        return trainSeatNumberDeparture;
    }

    public static void setTrainSeatNumberDeparture(String trainSeatNumberDeparture) {
        TiketKeretaData.trainSeatNumberDeparture = trainSeatNumberDeparture;
    }

    public static String getPassangerName() {
        return passangerName;
    }

    public static void setPassangerName(String passangerName) {
        TiketKeretaData.passangerName = passangerName;
    }

    public static String getPassangerID() {
        return passangerID;
    }

    public static void setPassangerID(String passangerID) {
        TiketKeretaData.passangerID = passangerID;
    }

    public static String getTrainClassDeparture() {
        return trainClassDeparture;
    }

    public static void setTrainClassDeparture(String trainClassDeparture) {
        TiketKeretaData.trainClassDeparture = trainClassDeparture;
    }

    public static String getDepartureTimeArrival() {
        return departureTimeArrival;
    }

    public static void setDepartureTimeArrival(String departureTimeArrival) {
        TiketKeretaData.departureTimeArrival = departureTimeArrival;
    }

    public static String getReturnTimeOrigin() {
        return returnTimeOrigin;
    }

    public static void setReturnTimeOrigin(String returnTimeOrigin) {
        TiketKeretaData.returnTimeOrigin = returnTimeOrigin;
    }

    public static String getReturnTimeArrival() {
        return returnTimeArrival;
    }

    public static void setReturnTimeArrival(String returnTimeArrival) {
        TiketKeretaData.returnTimeArrival = returnTimeArrival;
    }

    public static String getDepartureOriginStationCode() {
        return departureOriginStationCode;
    }

    public static void setDepartureOriginStationCode(String departureOriginStationCode) {
        TiketKeretaData.departureOriginStationCode = departureOriginStationCode;
    }

    public static String getDepartureDestinationStationCode() {
        return departureDestinationStationCode;
    }

    public static void setDepartureDestinationStationCode(String departureDestinationStationCode) {
        TiketKeretaData.departureDestinationStationCode = departureDestinationStationCode;
    }

    public static String getDepartureDate() {
        return departureDate;
    }

    public static void setDepartureDate(String departureDate) {
        TiketKeretaData.departureDate = departureDate;
    }

    public static String getReturnDate() {
        return returnDate;
    }

    public static void setReturnDate(String returnDate) {
        TiketKeretaData.returnDate = returnDate;
    }

    public static String getDepartureTrainName() {
        return departureTrainName;
    }

    public static void setDepartureTrainName(String departureTrainName) {
        TiketKeretaData.departureTrainName = departureTrainName;
    }

    public static String getReturnTrainName() {
        return returnTrainName;
    }

    public static void setReturnTrainName(String returnTrainName) {
        TiketKeretaData.returnTrainName = returnTrainName;
    }

    public static String getDepartureTimeOrigin() {
        return departureTimeOrigin;
    }

    public static void setDepartureTimeOrigin(String departureTimeOrigin) {
        TiketKeretaData.departureTimeOrigin = departureTimeOrigin;
    }

    public static int getDeparturePrice() {
        return departurePrice;
    }

    public static int getTotalAmount() {
        return totalAmount;
    }

    public static void setTotalAmount(int totalAmount) {
        TiketKeretaData.totalAmount = totalAmount;
    }

    public static String getTrainTripType() {
        return trainTripType;
    }

    public static void setTrainTripType(String trainTripType) {
        TiketKeretaData.trainTripType = trainTripType;
    }

    public static String getReturnOriginStationCode() {
        return returnOriginStationCode;
    }

    public static void setReturnOriginStationCode(String returnOriginStationCode) {
        TiketKeretaData.returnOriginStationCode = returnOriginStationCode;
    }

    public static String getReturnDestinationStationCode() {
        return returnDestinationStationCode;
    }

    public static void setReturnDestinationStationCode(String returnDestinationStationCode) {
        TiketKeretaData.returnDestinationStationCode = returnDestinationStationCode;
    }

    public static String getSortingType() {
        return sort;
    }

    public static void setSortingType(String sort) {
        TiketKeretaData.sort = sort;
    }

    public static String getVoucherCode() {
        return voucherCode;
    }

    public static void setVoucherCode(String voucherCode) {
        TiketKeretaData.voucherCode = voucherCode;
    }
}
