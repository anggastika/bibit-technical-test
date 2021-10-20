package bukalapak.data;

/**
 * @Author: Ayu Musfita
 * @Date: 25/02/20, Tue
 **/
public class HotelData {
    public static final String ROOM_AMOUNT_ERROR_MESSAGE = "Jumlah kamar tidak boleh melebihi jumlah tamu";
    public static final String DATE_RANGE_ERROR_MESSAGE = "Maks. menginap adalah 15 malam";
    public static final String HOTEL_NOT_FOUND_ERROR_MESSAGE = "Ganti pilihan hotel atau lokasi buat lihat alternatif tempat kamu menginap";
    public static final String CONTACT_NAME_ERROR_MESSAGE = "Hanya bisa diisi dengan huruf";
    public static final String CONTACT_EMAIL_ERROR_MESSAGE = "Format yang kamu masukin salah. Silakan periksa lagi.";
    public static final String CONTACT_HP_ERROR_MESSAGE = "Hanya bisa diisi angka.";
    public static final String EMPTY_ERROR_MESSAGE = "Wajib diisi.";
    private static String checkInDate;
    private static String checkOutDate;
    private static int guestAmount;
    private static int roomAmount;
    private static int totalNights;
    private static String destination;
    private static int roomPrice;
    private static String roomType;
    private static String contactName;
    private static String contactEmail;
    private static String contactPhoneNumber;
    private static String guestName;
    private static String specialRequest;
    private static int hotelPrice;
    private static int taxPrice;
    private static int totalPrice;
    private static String hotelName;

    public static String getDestination() {
        return destination;
    }

    public static void setDestination(String destination) {
        HotelData.destination = destination;
    }

    public static int getGuestAmount() {
        return guestAmount;
    }

    public static void setGuestAmount(int guestAmount) {
        HotelData.guestAmount = guestAmount;
    }

    public static int getRoomAmount() {
        return roomAmount;
    }

    public static void setRoomAmount(int roomAmount) {
        HotelData.roomAmount = roomAmount;
    }

    public static int getTotalNights() {
        return totalNights;
    }

    public static void setTotalNights(int totalNights) {
        HotelData.totalNights = totalNights;
    }

    public static String getCheckInDate() {
        return checkInDate;
    }

    public static void setCheckInDate(String checkInDate) {
        HotelData.checkInDate = checkInDate;
    }

    public static String getCheckOutDate() {
        return checkOutDate;
    }

    public static void setCheckOutDate(String checkOutDate) {
        HotelData.checkOutDate = checkOutDate;
    }

    public static int getRoomPrice() {
        return roomPrice;
    }

    public static void setRoomPrice(int roomPrice) {
        HotelData.roomPrice = roomPrice;
    }

    public static String getRoomType() {
        return roomType;
    }

    public static void setRoomType(String roomType) {
        HotelData.roomType = roomType;
    }

    public static String getContactName() {
        return contactName;
    }

    public static void setContactName(String contactName) {
        HotelData.contactName = contactName;
    }

    public static String getContactEmail() {
        return contactEmail;
    }

    public static void setContactEmail(String contactEmail) {
        HotelData.contactEmail = contactEmail;
    }

    public static String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public static void setContactPhoneNumber(String contactPhoneNumber) {
        HotelData.contactPhoneNumber = contactPhoneNumber;
    }

    public static String getGuestName() {
        return guestName;
    }

    public static void setGuestName(String guestName) {
        HotelData.guestName = guestName;
    }

    public static String getSpecialRequest() {
        return specialRequest;
    }

    public static void setSpecialRequest(String specialRequest) {
        HotelData.specialRequest = specialRequest;
    }

    public static int getHotelPrice() {
        return hotelPrice;
    }

    public static void setHotelPrice(int hotelPrice) {
        HotelData.hotelPrice = hotelPrice;
    }

    public static int getTaxPrice() {
        return taxPrice;
    }

    public static void setTaxPrice(int taxPrice) {
        HotelData.taxPrice = taxPrice;
    }

    public static int getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(int totalPrice) {
        HotelData.totalPrice = totalPrice;
    }

    public static String getHotelName() {
        return hotelName;
    }

    public static void setHotelName(String hotelName) {
        HotelData.hotelName = hotelName;
    }
}
