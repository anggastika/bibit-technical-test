package bukalapak.pageObject.vp.tix.tiketBus;

import bukalapak.data.BusData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


/**
 * @Author: Ayu Musfita
 * @Date: 04/01/19, Fri
 **/
public class TravelBusDetailOrderPage extends BasePage {

    public TravelBusDetailOrderPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnDetailOrdersPage() {
        waitForVisibilityOf("bus_detail_order_detail_bus_label", 35);
        validateDisplayed("bus_detail_order_detail_perjalanan_label");
    }

    public void validateBusPrice(String tripType) {
        String price = getTextFromElement("bus_detail_order_price").trim().split(" ")[1];

        if (tripType.equals("departure")) {
            assertEquals(BusData.getBusPriceFare(), price);
        } else {
            assertEquals(BusData.getBusReturnPriceFare(), price);
        }
    }

    public void validateBusTripTime(String tripType) {
        String departTime = getTextFromElement("bus_detail_order_origin_time").split(",")[1].trim();
        String arrivalTime = getTextFromElement("bus_detail_order_destination_time").split(",")[1].trim();
        if (tripType.equals("departure")) {
            assertEquals(departTime + " - " + arrivalTime, BusData.getBusTripTime());
        } else {
            assertEquals(departTime + " - " + arrivalTime, BusData.getBusReturnTripTime());
        }
    }

    public void validateBusBoardingPoint(String tripType) {
        if (tripType.equals("departure")) {
            assertEquals(getTextFromElement("bus_detail_order_origin_city"), BusData.getBusBoardingPoint());
        } else {
            assertEquals(getTextFromElement("bus_detail_order_origin_city"), BusData.getBusReturnBoardingPoint());
        }
    }

    public void validateBusDroppingPoint(String tripType) {
        if (tripType.equals("departure")) {
            assertEquals(getTextFromElement("bus_detail_order_destination_city"), BusData.getBusDroppingPoint());
        } else {
            assertEquals(getTextFromElement("bus_detail_order_destination_city"), BusData.getBusReturnDroppingPoint());
        }

        HelperData.setLastActionPage(new TravelBusDetailOrderPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
