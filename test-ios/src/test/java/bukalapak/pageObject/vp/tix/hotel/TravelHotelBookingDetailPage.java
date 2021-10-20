package bukalapak.pageObject.vp.tix.hotel;

import bukalapak.TestInstrument;
import bukalapak.data.HotelData;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelHotelBookingDetailPage extends VpBasePage {
    public TravelHotelBookingDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnBookingDetailPage() {
        validateDisplayed("hotel_detail_pesanan_text");
        swipeToLocator("hotel_kontak_pemesan_text");
        validateDisplayed("hotel_kontak_pemesan_text");
        swipeToLocator("hotel_spec_request_text");
        validateDisplayed("hotel_spec_request_text");
        swipeToLocator("hotel_pilih_pembayaran_text");
        validateDisplayed("hotel_pilih_pembayaran_text");
    }

    public void validateBookingData() {
        String roomAmount = HotelData.getRoomAmount() + " kamar";
        String guestAmount = HotelData.getGuestAmount() + " tamu";
        String totalNights = HotelData.getTotalNights() + " malam";

        validateDisplayed(constructLocator("hotel_general_text", HotelData.getHotelName()));
        validateDisplayed(constructLocator("hotel_general_text", HotelData.getRoomType()));
        validateDisplayed(constructLocator("hotel_general_text", roomAmount));
        validateDisplayed(constructLocator("hotel_general_text", guestAmount));
        validateDisplayed(constructLocator("hotel_general_text", totalNights));
        validateDisplayed(constructLocator("hotel_general_contains_text", HotelData.getCheckInDate()));
        validateDisplayed(constructLocator("hotel_general_contains_text", HotelData.getCheckOutDate()));
    }

    public void tapContactCustomerSection() {
        swipeToLocator("hotel_kontak_pemesan_text");
        tapElement("hotel_isi_kontak_lain_text");
    }

    public void tapAnotherGuestSection() {
        swipeToLocator("hotel_pesan_untuk_orang_text");
        tapElement("hotel_pesan_untuk_orang_text");
    }

    public void tapSpecialRequestSection() {
        swipeToLocator("hotel_spec_request_text");
        tapElement("hotel_tambahkan_pesanan_text");
    }

    public void tapSaveButton() {
        tapElement("hotel_simpan_button");
    }

    public void validateContactForm() {
        validateDisplayed("hotel_kontak_pemesan_text");
        validateDisplayed("hotel_titel_text");
        validateDisplayed("hotel_nama_lengkap_text");
        validateDisplayed("hotel_email_text");
        validateDisplayed("hotel_no_hp_text");
    }

    public void fillContactForm(String typeData) {
        String fullName = generateFullName();

        if (typeData.equals("valid")) {
            setContactName(fullName);
            setContactEmail(fullName);
            setContactPhoneNumber(typeData);
        } else {
            setContactName(fullName + "1");
            setContactEmail(fullName + "@");
            setContactPhoneNumber("invalid");
        }
    }

    public void fillAnotherGuestData(String typeData) {
        String fullName = generateFullName();

        if (typeData.equals("valid")) {
            typeAndEnterValue("hotel_booking_form_nama_tamu_field", fullName);
            HotelData.setGuestName(fullName);
        } else {
            typeAndEnterValue("hotel_booking_form_nama_tamu_field", "lala 123");
        }
    }

    public void setContactName(String fullName) {
        typeAndEnterValue("hotel_booking_form_nama_lengkap_field", fullName);
        HotelData.setContactName(fullName);
    }

    public void setContactEmail(String fullName) {
        typeAndEnterValue("hotel_booking_form_email_field",
                fullName.toLowerCase().replace(" ", "") + "@gmail.com");
        HotelData.setContactEmail(fullName);
    }

    public void setContactPhoneNumber(String typeData) {
        long phoneNumber = generatePhoneNumber();

        if (typeData.equals("valid")) {
            typeAndEnterValue("hotel_booking_form_hp_field", "08" + phoneNumber);
            HotelData.setContactPhoneNumber("08" + phoneNumber);
        } else {
            typeAndEnterValue("hotel_booking_form_hp_field", "+$-" + phoneNumber);
        }
    }

    public void validateContactData() {
        validateDisplayed(constructLocator("hotel_booking_form_contact_name", HotelData.getContactName()));
        validateDisplayed(constructLocator("hotel_booking_form_contact_email", HotelData.getContactEmail()));
        validateDisplayed(constructLocator("hotel_booking_form_contact_hp", HotelData.getContactPhoneNumber()));
    }

    public void validateAnotherGuestData() {
        validateDisplayed(constructLocator("hotel_booking_form_guest_name", HotelData.getGuestName()));
    }

    public void validateInvalidContact() {
        validateDisplayed(constructLocator("hotel_general_text", HotelData.CONTACT_NAME_ERROR_MESSAGE));
        validateDisplayed(constructLocator("hotel_general_text", HotelData.CONTACT_EMAIL_ERROR_MESSAGE));
        validateDisplayed(constructLocator("hotel_general_text", HotelData.CONTACT_HP_ERROR_MESSAGE));
    }

    public void validateInvalidAnotherGuest() {
        validateDisplayed(constructLocator("hotel_general_text", HotelData.CONTACT_NAME_ERROR_MESSAGE));
    }

    public void chooseSpecialRequest(String request) {
        validateDisplayed("hotel_spec_request_long_text");
        tapElement(constructLocator("hotel_booking_form_special_request", request));
        HotelData.setSpecialRequest(request);

        if (request.equals("Lainnya")) {
            typeAndEnterValue("hotel_booking_form_special_request_etc", TestInstrument.dotenv.get("HOTEL_SPECIAL_REQUEST"));
        }
    }

    public void validateSpecialRequest() {
        swipeToLocator("hotel_rincian_harga_text");
        validateDisplayed(constructLocator("hotel_general_contains_text", HotelData.getSpecialRequest()));
    }

    public void tapChoosePaymentMethod() {
        swipeToLocator("hotel_pilih_pembayaran_button");
        tapElement("hotel_pilih_pembayaran_button");
    }

    public void setHotelAmount() {
        swipeToLocator("hotel_pilih_pembayaran_button");
        int hotelAmount = parseIntegerFromText(getText(constructLocator("hotel_booking_form_hotel_amount", HotelData.getRoomType())));
        int taxAmount = parseIntegerFromText(getText("hotel_booking_form_hotel_amount_pajak"));
        int totalAmount = parseIntegerFromText(getText("hotel_booking_form_hotel_amount_total"));

        HotelData.setHotelPrice(hotelAmount);
        HotelData.setTaxPrice(taxAmount);
        HotelData.setTotalPrice(totalAmount);
    }

    public void tapDetailHotel() {
        tapElement("hotel_detail_button");
    }
}
