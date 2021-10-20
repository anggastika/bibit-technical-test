package bukalapak.data.vp.tix;

public class TiketPesawatData {
    private static int passengerCount;
    private static String passengerName;
    private static String passengerEmail;
    private static String passengerPhoneNumber;
    private static String passengerIdentityNumber;
    private static String passengerPassportExpiry;
    private static String passengerBirthdayDate;
    private static String buyerName;
    private static String buyerEmail;
    private static String buyerPhoneNumber;
    private static String departureAirport;
    private static String arrivalAirport;
    private static String airlineFilter;
    private static String selectedDepartureAirline;
    private static String selectedReturnAirline;
    private static boolean roundTrip;
    private static String departureDate;
    private static String returnDate;
    private static String totalPrice;
    private static int totalInsurancePrice;
    private static boolean usingFlightInsurance;
    private static boolean priceChanged;
    private static String sortCriteria;
    private static boolean international;
    private static boolean coachMarked = false;
    private static int minimumPrice;
    private static int maximumPrice;

    public static final String invalidEmailFormatError = "Format e-mail salah";
    public static final String minLengthPhoneError = "No. handphone minimum 10 karakter";
    public static final String invalidNameFormatError = "Format nama hanya boleh terdiri oleh huruf dan spasi";

    public static String getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(String totalPrice) {
        TiketPesawatData.totalPrice = totalPrice;
    }

    public static String getPassengerIdentityNumber() {
        return passengerIdentityNumber;
    }

    public static void setPassengerIdentityNumber(String passengerIdentityNumber) {
        TiketPesawatData.passengerIdentityNumber = passengerIdentityNumber;
    }

    public static String getPassengerPassportExpiry() {
        return passengerPassportExpiry;
    }

    public static void setPassengerPassportExpiry(String passengerPassportExpiry) {
        TiketPesawatData.passengerPassportExpiry = passengerPassportExpiry;
    }

    public static String getPassengerBirthdayDate() {
        return passengerBirthdayDate;
    }

    public static void setPassengerBirthdayDate(String passengerBirthdayDate) {
        TiketPesawatData.passengerBirthdayDate = passengerBirthdayDate;
    }

    public static boolean isInternational() {
        return international;
    }

    public static void setInternational(boolean international) {
        TiketPesawatData.international = international;
    }

    public static String getSortCriteria() {
        return sortCriteria;
    }

    public static void setSortCriteria(String sortCriteria) {
        TiketPesawatData.sortCriteria = sortCriteria;
    }

    public static String getPassengerName() {
        return passengerName;
    }

    public static void setPassengerName(String passengerName) {
        TiketPesawatData.passengerName = passengerName;
    }

    public static String getPassengerEmail() {
        return passengerEmail;
    }

    public static void setPassengerEmail(String passengerEmail) {
        TiketPesawatData.passengerEmail = passengerEmail;
    }

    public static String getPassengerPhoneNumber() {
        return passengerPhoneNumber;
    }

    public static void setPassengerPhoneNumber(String passengerPhoneNumber) {
        TiketPesawatData.passengerPhoneNumber = passengerPhoneNumber;
    }

    public static String getBuyerName() {
        return buyerName;
    }

    public static void setBuyerName(String buyerName) {
        TiketPesawatData.buyerName = buyerName;
    }

    public static String getBuyerEmail() {
        return buyerEmail;
    }

    public static void setBuyerEmail(String buyerEmail) {
        TiketPesawatData.buyerEmail = buyerEmail;
    }

    public static String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public static void setBuyerPhoneNumber(String buyerPhoneNumber) {
        TiketPesawatData.buyerPhoneNumber = buyerPhoneNumber;
    }

    public static boolean isPriceChanged() {
        return priceChanged;
    }

    public static void setPriceChanged(boolean priceChanged) {
        TiketPesawatData.priceChanged = priceChanged;
    }

    public static boolean isUsingFlightInsurance() {
        return usingFlightInsurance;
    }

    public static void setUsingFlightInsurance(boolean flightInsurance) {
        TiketPesawatData.usingFlightInsurance = flightInsurance;
    }

    public static int getTotalInsurancePrice() {
        return totalInsurancePrice;
    }

    public static void setTotalInsurancePrice(int totalInsurancePrice) {
        TiketPesawatData.totalInsurancePrice = totalInsurancePrice;
    }

    public static String getDepartureDate() {
        return departureDate;
    }

    public static void setDepartureDate(String departureDate) {
        TiketPesawatData.departureDate = departureDate;
    }

    public static String getSelectedDepartureAirline() {
        return selectedDepartureAirline;
    }

    public static void setSelectedDepartureAirline(String selectedDepartureAirline) {
        TiketPesawatData.selectedDepartureAirline = selectedDepartureAirline;
    }

    public static String getAirlineFilter() {
        return airlineFilter;
    }

    public static void setAirlineFilter(String airlineFilter) {
        TiketPesawatData.airlineFilter = airlineFilter;
    }

    public static int getPassengerCount() {
        return passengerCount;
    }

    public static void setPassengerCount(int passengerCount) {
        TiketPesawatData.passengerCount = passengerCount;
    }


    public static String getDepartureAirport() {
        return departureAirport;
    }

    public static void setDepartureAirport(String departureAirport) {
        TiketPesawatData.departureAirport = departureAirport;
    }

    public static String getArrivalAirport() {
        return arrivalAirport;
    }

    public static void setArrivalAirport(String arrivalAirport) {
        TiketPesawatData.arrivalAirport = arrivalAirport;
    }

    public static boolean isRoundTrip() {
        return roundTrip;
    }

    public static void setRoundTrip(boolean roundTrip) {
        TiketPesawatData.roundTrip = roundTrip;
    }

    public static boolean isCoachMarked() {
        return coachMarked;
    }

    public static void setCoachMarked(boolean coachMarked) {
        TiketPesawatData.coachMarked = coachMarked;
    }


    public static String getReturnDate() {
        return returnDate;
    }

    public static void setReturnDate(String returnDate) {
        TiketPesawatData.returnDate = returnDate;
    }

    public static String getSelectedReturnAirline() {
        return selectedReturnAirline;
    }

    public static void setSelectedReturnAirline(String selectedReturnAirline) {
        TiketPesawatData.selectedReturnAirline = selectedReturnAirline;
    }

    public static int getMinimumPrice() {
        return minimumPrice;
    }

    public static void setMinimumPrice(int minimumPrice) {
        TiketPesawatData.minimumPrice = minimumPrice;
    }

    public static int getMaximumPrice() {
        return maximumPrice;
    }

    public static void setMaximumPrice(int maximumPrice) {
        TiketPesawatData.maximumPrice = maximumPrice;
    }
}
