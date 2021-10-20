package bukalapak.pageObject.vp.tix.hotel;

import bukalapak.data.HotelData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelHotelRoomListingPage extends BasePage {
    public TravelHotelRoomListingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateRoomListData() {
        String roomAmount = HotelData.getRoomAmount() + " Kamar";
        String guestAmount = HotelData.getGuestAmount() + " Tamu";
        String stayDateRange = HotelData.getCheckInDate() + " - " + HotelData.getCheckOutDate();

        validateDisplayed("hotel_room_list_header");
        validateDisplayed("hotel_room_list_book_button");
        validateDisplayed(constructLocator("hotel_general_contains_text", roomAmount));
        validateDisplayed(constructLocator("hotel_general_contains_text", guestAmount));
        validateDisplayed(constructLocator("hotel_general_contains_text", stayDateRange));
    }

    public void tapOnBookRoomButton() {
        tapElement("hotel_room_list_book_button");
    }

    public void setRoomType() {
        String roomType = getText("hotel_main_room_type_text", 0);

        HotelData.setRoomType(roomType);
    }

    public void setRoomPrice() {
        int roomPrice = getIntegerFromTextElement(getText("hotel_room_price_text", 0));

        HotelData.setRoomPrice(roomPrice);
    }
}
