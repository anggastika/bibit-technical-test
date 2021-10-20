package bukalapak.pageObject.vp.tix.tiketBus;

import bukalapak.data.BusData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 07/01/19, Mon
 **/
public class TravelBusConfirmationOrderPage extends BasePage {

    public TravelBusConfirmationOrderPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void validateOnConfirmationPage() {
        waitForVisibilityOf("bus_confirmation_detail_pesanan_label", 60);
        verifyElementDisplayed("bus_confirmation_detail_no_kursi_label");
        verifyElementDisplayed("bus_confirmation_data_penumpang_label");
    }

    public void validateDataConfirmation() {
        validateTravelName("departure");
        validateSeatNumber("departure");
        validateBusBoardingPoint("departure");
        validateBusDroppingPoint("departure");
        validateBusTripTime("departure");

        if (BusData.getTripType().equals("round trip")) {
            validateTravelName("return");
            validateSeatNumber("return");
            validateBusBoardingPoint("return");
            validateBusDroppingPoint("return");
            validateBusTripTime("return");
        }

        HelperData.setLastActionPage(new TravelBusConfirmationOrderPage(iosDriver));
    }

    public void validateTravelName(String tripType) {
        if (tripType.equals("departure")) {
            assertEquals(BusData.getBusTravelName(), getTextFromElement("bus_confirmation_departure_travel_name"));
        } else {
            swipeUpToElement("bus_email_name");
            assertEquals(BusData.getBusReturnTravelName(), getTextFromElement("bus_confirmation_return_travel_name"));
        }
    }

    public void validateSeatNumber(String tripType) {
        if (tripType.equals("departure")) {
            assertEquals(BusData.getBusSeatNumber(), getTextFromElement("bus_confirmation_departure_seat_number"));
        } else {
            assertEquals(BusData.getBusReturnSeatNumber(), getTextFromElement("bus_confirmation_return_seat_number"));
        }
    }

    public void validateBusBoardingPoint(String tripType) {
        if (tripType.equals("departure")) {
            assertEquals(BusData.getBusBoardingPoint(), getTextFromElement("bus_confirmation_departure_boarding_point"));
        } else {
            assertEquals(BusData.getBusReturnBoardingPoint(), getTextFromElement("bus_confirmation_return_boarding_point"));
        }
    }

    public void validateBusDroppingPoint(String tripType) {
        if (tripType.equals("departure")) {
            assertEquals(BusData.getBusDroppingPoint(), getTextFromElement("bus_confirmation_departure_drop_point"));
        } else {
            assertEquals(BusData.getBusReturnDroppingPoint(), getTextFromElement("bus_confirmation_return_drop_point"));
        }
    }

    public void validateBusTripTime(String tripType) {
        if (tripType.equals("departure")) {
            String departTime = getTextFromElement("bus_confirmation_origin_departure_time").split(",")[1].trim();
            String arrivalTime = getTextFromElement("bus_confirmation_origin_arrival_time").split(",")[1].trim();

            assertEquals(departTime + " - " + arrivalTime, BusData.getBusTripTime());
        } else {
            String returnDepartTime = getTextFromElement("bus_confirmation_destination_departure_time").split(",")[1].trim();
            String returnArrivalTime = getTextFromElement("bus_confirmation_destination_arrival_time").split(",")[1].trim();

            assertEquals(returnDepartTime + " - " + returnArrivalTime, BusData.getBusReturnTripTime());
        }
    }

    public void tapOnLanjutButton() {
        tapElement("bus_button_lanjut");
    }

}
