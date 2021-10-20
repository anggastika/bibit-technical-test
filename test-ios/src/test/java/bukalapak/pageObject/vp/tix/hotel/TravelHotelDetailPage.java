package bukalapak.pageObject.vp.tix.hotel;

import bukalapak.data.HotelData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelHotelDetailPage extends BasePage {

    public TravelHotelDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        validateDisplayed("hotel_detail_share_button");
        validateDisplayed("hotel_detail_back_button");
        validateDisplayed("hotel_general_image_carousel");
        validateDisplayed(constructLocator("hotel_general_text", HotelData.getDestination()));
        swipeToLocator("hotel_fasilitas_text");
        validateDisplayed("hotel_fasilitas_text");
        swipeToLocator("hotel_lokasi_text");
        validateDisplayed("hotel_lokasi_text");
        swipeToLocator("hotel_tentang_hotel_text");
        validateDisplayed("hotel_tentang_hotel_text");
        HotelData.setHotelName(getElementValue("hotel_detail_name_hotel"));
    }

    public void tapTentangHotelButton(){
        tapElement("hotel_detail_tentang_hotel_button");
    }

    public void validateOnHotelDescriptionPage() {
        validateDisplayed(constructLocator("hotel_general_text", "Tentang " + HotelData.getHotelName()));
    }

    public void tapPilihKamarButton() {
        tapElement("hotel_detail_pilih_kamar_button");
    }
}
