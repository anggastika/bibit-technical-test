package bukalapak.pageObject.vp.tix.hotel;

import bukalapak.data.HotelData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelHotelSearchResultPage extends BasePage {
    public TravelHotelSearchResultPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateHotelListData() {
        String totalNights = HotelData.getTotalNights() + " malam";
        String roomAmount = HotelData.getRoomAmount() + " kamar";
        String guestAmount = HotelData.getGuestAmount() + " tamu";
        String stayDateRange = HotelData.getCheckInDate() + " - " + HotelData.getCheckOutDate();

        validateDisplayed(constructLocator("hotel_general_text", HotelData.getDestination()));
        validateDisplayed(constructLocator("hotel_general_text", stayDateRange));
        validateDisplayed(constructLocator("hotel_general_text", totalNights));
        validateDisplayed(constructLocator("hotel_general_text", roomAmount));
        validateDisplayed(constructLocator("hotel_general_text", guestAmount));
    }

    public void tapOnButton(String buttonLabel) {
        tapElement(constructLocator("vp_general_dynamic_label_contain", buttonLabel));
    }

    public void tapOnUrutkan(){
        tapElement("hotel_search_urutkan_label");
    }

    public void tapOnTerapkan(){
        tapElement("hotel_search_terapkan_label");
    }

    public void tapOnFilter(){
        tapElement("hotel_search_filter_label");
    }

    public void tapOnBintangHotel(){
        tapElement("hotel_search_bintang_hotel_label");
    }

    public void tapOnFilterCriteria(String filterType, String criteria) {
        if (filterType.toLowerCase().equals("bintang hotel")) {
            tapElement("hotel_search_filter_star_list", 5 - Integer.parseInt(criteria));
        }
    }

    public void tapOnChooseHotel() {
        tapElements("hotel_search_hotel_list", 0);
    }
}
