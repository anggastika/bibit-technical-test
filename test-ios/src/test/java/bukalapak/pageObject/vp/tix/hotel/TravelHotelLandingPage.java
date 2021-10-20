package bukalapak.pageObject.vp.tix.hotel;

import bukalapak.data.HotelData;
import bukalapak.pageObject.BasePage;
import bukalapak.utils.ChoiceSelector;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static junit.framework.TestCase.fail;
import static bukalapak.TestInstrument.dotenv;

/**
 * @Author: Ayu Musfita
 * @Date: 25/02/20, Tue
 **/
public class TravelHotelLandingPage extends BasePage {

    public TravelHotelLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        validateDisplayed("hotel_landing_destination_field");
        validateDisplayed("hotel_landing_checkin_field");
        validateDisplayed("hotel_landing_checkout_field");
        validateDisplayed("hotel_landing_guest_room_field");
        validateDisplayed("hotel_landing_cari_hotel_name");
    }

    public void tapOnRoomField() {
        tapElement("hotel_landing_guest_room_field");
    }

    public void validatePopUpRoomDisplayed() {
        validateDisplayed("hotel_detail_jumlah_tamu_kamar");
    }

    public void setGuestAmount(int guestAmount) {
        int idx = 1;
        while (idx <= guestAmount) {
            tapElement(constructLocator("hotel_landing_guest_item", idx));
            idx++;
            // Delay animation before next tap
            delay(2000);
        }
        HotelData.setGuestAmount(guestAmount);
    }

    public void setRoomAmount(int roomAmount) {
        int idx = 1;
        while (idx <= roomAmount) {
            tapElement(constructLocator("hotel_landing_room_item", idx));
            idx++;
            // Delay animation before next tap
            delay(2000);
        }
        HotelData.setRoomAmount(roomAmount);
    }

    public void tapOnPilihButton(){
        tapElement("hotel_detail_pilih_button");
    }

    public void tapOnCariHotel(){
        tapElement("hotel_landing_cari_hotel_name");
    }

    public void validateErrorMessageDisplayed(String errorType) {
        ChoiceSelector.of(errorType.toLowerCase())
                .when("room amount", () -> {
                    validateDisplayed(constructLocator("vp_general_dynamic_label_contain", HotelData.ROOM_AMOUNT_ERROR_MESSAGE));
                })
                .when("date range", () -> {
                    validateDisplayed(constructLocator("vp_general_dynamic_label_contain", HotelData.DATE_RANGE_ERROR_MESSAGE));
                })
                .orElse(() -> fail(String.format("Error message not specified yet")));
    }

    public void tapOnCheckinField() {
        tapElement("hotel_landing_checkin_field");
    }

    public void tapOnCheckOutField() {
        tapElement("hotel_landing_checkout_field");
    }

    public void tapOnNextMonth() {
        tapElement("hotel_landing_datepicker_nextmonth");
    }

    public void setCheckinDate(int date) {
        validateDisplayed("hotel_landing_checkin_name");
        tapElements(constructLocator("vp_general_dynamic_name", date), 0);
    }

    public void setCheckoutDate(int date) {
        validateDisplayed("hotel_landing_checkout_name");
        tapElements(constructLocator("vp_general_dynamic_name", date), 0);
    }

    public void tapOnDestinationField() {
        tapElement("hotel_landing_destination_field");
    }

    public void validateOnDestinationListPage() {
        validateDisplayed("hotel_landing_destination_popular_item");
    }

    public void typeOnSearchHotelField(String by) {
        if (by.toLowerCase().equals("valid") || by.toLowerCase().equals("hotel name")) {
            typeAndEnterValue("hotel_landing_destination_search_field", dotenv.get("HOTEL_NAME"));
        } else if (by.toLowerCase().equals("city")){
            typeAndEnterValue("hotel_landing_destination_search_field", dotenv.get("HOTEL_CITY"));
        } else {
            typeAndEnterValue("hotel_landing_destination_search_field", "invalid hotel name");
        }
    }

    public void validateHotelDisplayedOnList(String hotelName) {
        validateDisplayed(constructLocator("hotel_landing_destination_search_result_field", hotelName));
    }

    public void validateHotelNotFoundDisplayed() {
        validateDisplayed(constructLocator("vp_general_dynamic_label_contain", HotelData.HOTEL_NOT_FOUND_ERROR_MESSAGE));
    }

    public void setLengthOfStay() {
        String[] temp = getText("hotel_landing_total_night_text").split(" ");

        // text is like this -> Lama menginap: x malam
        HotelData.setTotalNights(Integer.parseInt(temp[2]));
    }

    public void setCheckInOutDate() {
        String checkInDate = getText("hotel_landing_checkin_field").split(", ")[1];
        String checkOutDate = getText("hotel_landing_checkout_field").split(", ")[1];

        HotelData.setCheckInDate(checkInDate);
        HotelData.setCheckOutDate(checkOutDate);
    }
}
