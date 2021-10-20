package bukalapak.pageObject.vp.tix.tiketBus;

import bukalapak.data.BusData;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelBusCheckoutPage extends VpBasePage {
    public TravelBusCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateCheckoutDetail() {
        waitForVisibilityOf("bus_perjalanan_pergi_label", 10);
        assertEquals(BusData.getBusTravelName().toLowerCase(), getTextFromElement("bus_checkout_departure_travel_name").toLowerCase());
        verifyElementDisplayed(constructLocator("bus_checkout_departure_boarding_point", BusData.getBusBoardingPoint()));
        verifyElementDisplayed(constructLocator("bus_checkout_departure_drop_point", BusData.getBusDroppingPoint()));

        if (BusData.getTripType().equals("round trip")) {
            verifyElementDisplayed("bus_perjalanan_pulang_label");
            assertEquals(BusData.getBusReturnTravelName().toLowerCase(), getTextFromElement("bus_checkout_return_travel_name").toLowerCase());
            verifyElementDisplayed(constructLocator("bus_checkout_return_boarding_point", BusData.getBusReturnBoardingPoint()));
            verifyElementDisplayed(constructLocator("bus_checkout_return_drop_point", BusData.getBusReturnDroppingPoint()));
        }

        validationSeatNumber();
    }

    public void validationSeatNumber() {
        swipeUpToElement("bus_checkout_departure_seat_number");
        assertEquals(BusData.getBusSeatNumber(), getTextFromElement("bus_checkout_departure_seat_number"));

        if (BusData.getTripType().equals("round trip")) {
            swipeUpToElement("bus_nomor_kursi_pulang_label");
            assertEquals(BusData.getBusReturnSeatNumber(), getTextFromElement("bus_checkout_return_seat_number"));
            tapElement("bus_checkout_detail_passenger_accordion");
        }
    }

    public void cancelTicketBusTransaction() {
        tapElement("bus_checkout_back_button");
        waitForVisibilityOf("bus_checkout_cancel_transaction_text", 5);
        tapElement("bus_checkout_cancel_transaction_button");
    }
}
