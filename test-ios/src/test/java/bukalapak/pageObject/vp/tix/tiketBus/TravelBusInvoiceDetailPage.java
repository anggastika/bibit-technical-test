package bukalapak.pageObject.vp.tix.tiketBus;

import bukalapak.data.BusData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.InvoiceDetailNonMarketplacePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 09/01/19, Wed
 **/
public class TravelBusInvoiceDetailPage extends InvoiceDetailNonMarketplacePage {

    public TravelBusInvoiceDetailPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateBusBoardingPoint(String tripType) {
        if (tripType.equals("departure")) {
            swipeUpToElement("invoice_non_marketplace_bus_nama_bus_label");
            assertEquals(BusData.getBusBoardingPoint(), getTextFromElement("invoice_non_marketplace_bus_boarding_point"));
        } else {
            assertEquals(BusData.getBusReturnBoardingPoint(), getTextFromElement("invoice_non_marketplace_bus_boarding_point"));
        }
    }

    public void validateBusDroppingPoint(String tripType) {
        if (tripType.equals("departure")) {
            assertEquals(BusData.getBusDroppingPoint(), getTextFromElement("invoice_non_marketplace_bus_dropping_point"));
        } else {
            assertEquals(BusData.getBusReturnDroppingPoint(), getTextFromElement("invoice_non_marketplace_bus_dropping_point"));
        }
    }

    public void validateBusTripTime(String tripType) {
        String departTime = getTextFromElement("invoice_non_marketplace_bus_departure_time").split(",")[1].trim();
        String arrivalTime = getTextFromElement("invoice_non_marketplace_bus_arrival_time").split(",")[1].trim();
        if (tripType.equals("departure")) {
            assertEquals(departTime + " - " + arrivalTime, BusData.getBusTripTime());
        } else {
            assertEquals(departTime + " - " + arrivalTime, BusData.getBusReturnTripTime());
        }
    }

    public void validateBusName(String tripType) {
        if (tripType.equals("departure")) {
            swipeUpToElement("invoice_non_marketplace_bus_nomor_kursi_label");
            assertEquals(BusData.getBusTravelName(), getTextFromElement("invoice_non_marketplace_bus_name", 0));
        } else {
            swipeUpToElement("invoice_non_marketplace_bus_nomor_kursi_label");
            assertEquals(BusData.getBusReturnTravelName(), getTextFromElement("invoice_non_marketplace_bus_name"));
        }
    }

    public void validateSeatNumber(String tripType) {
        if (tripType.equals("departure")) {
            assertEquals(BusData.getBusSeatNumber(), getTextFromElement("invoice_non_marketplace_bus_seat_number", 0));
            tapElement("invoice_non_marketplace_bus_tiket_pergi_accordion");
        } else {
            assertEquals(BusData.getBusReturnSeatNumber(), getTextFromElement("invoice_non_marketplace_bus_seat_number"));
        }
        HelperData.setLastActionPage(new TravelBusInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
