package bukalapak.pageObject.vp.tix.tiketBus;

import bukalapak.data.BusData;
import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.TiketBusData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;


/**
 * @Author: Ayu Musfita
 * @Date: 03/01/19, Thu
 **/
public class TravelBusSchedulePage extends BasePage {

    public TravelBusSchedulePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void validateOnBusSchedulePage() {
        waitForVisibilityOf("bus_price_fare", 50);
        validateDisplayed("bus_price_fare");
    }

    public void validateOriginCityDeparture() {
        assertEquals(getElementValue("bus_origin_city_search_page"), BusData.getBusOriginCity());
    }

    public void validateOriginCityReturn() {
        assertEquals(getElementValue("bus_origin_city_search_page"), BusData.getBusDestinationCity());
    }

    public void validateDestinationCityDeparture() {
        assertEquals(getElementValue("bus_destination_city_search_page"), BusData.getBusDestinationCity());
    }

    public void validateDestinationCityReturn() {
        assertEquals(getElementValue("bus_destination_city_search_page"), BusData.getBusOriginCity());
    }

    public void setBusTravelNameDeparture() {
        BusData.setBusTravelName(getElementValue("bus_name", 0).trim());
    }

    public void setBusTravelNameReturn() {
        BusData.setBusReturnTravelName(getElementValue("bus_name", 0).trim());
    }

    public void setDepartureTime() {
        BusData.setBusTripTime(getElementValue("bus_trip_time", 0));
    }

    public void setReturnTime() {
        BusData.setBusReturnTripTime(getElementValue("bus_trip_time", 0));
    }

    public void setDepartureBusPrice() {
        String harga = getElementValue("bus_price_fare", 0).split("/")[0];
        BusData.setBusPriceFare(harga);
    }

    public void setReturnBusPrice() {
        String harga = getElementValue("bus_price_fare", 0).split("/")[0];
        BusData.setBusReturnPriceFare(harga);
    }

    public void setBoardingPoint() {
        BusData.setBusBoardingPoint(getElementValue("bus_boarding_point", 0));
    }

    public void setReturnBoardingPoint() {
        BusData.setBusReturnBoardingPoint(getElementValue("bus_boarding_point", 0));
    }

    public void setDroppingPoint() {
        BusData.setBusDroppingPoint(getElementValue("bus_dropping_point", 0));
    }

    public void setReturnDroppingPoint() {
        BusData.setBusReturnDroppingPoint(getElementValue("bus_dropping_point", 0));
    }

    public void tapFirstSchedule(String tripType) {
        if (tripType.equals("departure")) {
            setBusTravelNameDeparture();
            setDepartureTime();
            setDepartureBusPrice();
            setBoardingPoint();
            setDroppingPoint();
        } else {
            setBusTravelNameReturn();
            setReturnTime();
            setReturnBusPrice();
            setReturnBoardingPoint();
            setReturnDroppingPoint();
        }

        tapElement("bus_first_schedule_element", 0);
    }

    public void seAnotherSchedule() {
        BusData.setBusTravelName(getElementValue("bus_name", 1).trim());
        BusData.setBusTripTime(getElementValue("bus_trip_time", 1));
        String busPrice = getElementValue("bus_price_fare", 1).split("/")[0];
        BusData.setBusPriceFare(busPrice);
        BusData.setBusBoardingPoint(getElementValue("bus_boarding_point", 1));
        BusData.setBusDroppingPoint(getElementValue("bus_dropping_point", 1));
    }

    public void tapAnotherSchedule() {
        seAnotherSchedule();

        tapElements("bus_first_schedule_element", 1);
    }

    public void tapOnUrutkan() {
        waitForVisibilityOf("bus_urutkan_button", 75);
        tapElement("bus_urutkan_button");
    }

    public void sortingByPrice(String priceType) {
        String type = priceType.equals("cheapest") ? "Termurah" : "Termahal";

        tapElement(constructLocator("bus_price_type", type));
        TiketBusData.setSortingType(type);
    }

    public void waitForBusSchedulePriceList() {
        waitForVisibilityOf("bus_schedule_price_list", 5);
    }

    public void validateSortingByPriceresult() {
        int allSeatList = getElements("bus_schedule_price_list").size();
        int allEmptySeatList = getElements("bus_schedule_empty_seat_list").size();
        int totalAvailableList = allSeatList - allEmptySeatList;

        for (int index = 1; index < totalAvailableList - 1; index++) {
            swipeUpToElement(constructLocator("bus_schedule_price", index + 1));
            checkPriceSorting(index);
        }

        HelperData.setLastActionPage(new TravelBusSchedulePage(iosDriver));
    }

    private void checkPriceSorting(int index) {
        int firstPrice = getIntegerFromValueElement(constructLocator("bus_schedule_price", index));
        int secondPrice = getIntegerFromValueElement(constructLocator("bus_schedule_price", index + 1));

        if (TiketBusData.getSortingType().equals("Termurah") && (firstPrice > secondPrice)) {
            Assert.fail("Sorting tiket bus termurah tidak sesuai");
        } else if (TiketBusData.getSortingType().equals("Termahal") && (firstPrice < secondPrice)) {
            Assert.fail("Sorting tiket bus termahal tidak sesuai");
        }
    }

    public void tapOnFilter() {
        tapElement("bus_filter_button");
    }

    public void tapOnBusName() {
        TiketBusData.setBusName(getElementLabel("bus_filter_name"));
        tapElement("bus_filter_name");
    }

    public void tapOnTerapkan() {
        tapElement("bus_apply_button");
    }

    public void validateFilterByBusName() {
        int allBusName = getElements("bus_schedule_name_list").size();

        for (int index = 1; index < allBusName - 1; index++) {
            swipeUpToElement(constructLocator("bus_schedule_name", index + 1));
            validateValue().equals(TiketBusData.getBusName(), getElementLabel(constructLocator("bus_schedule_name", index + 1)));
        }

        HelperData.setLastActionPage(new TravelBusSchedulePage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
