package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import bukalapak.data.BusData;
import cucumber.api.java8.En;

/**
 * @Author: Ayu Musfita
 * @Date: 03/01/19, Thu
 *
 * IMPORTANT NOTES:
 * Please simplify the test steps (from each page object class) into one line only in each features steps.
 **/
public class TravelBusTicketStepDefinitions extends TestInstrument implements En {

    public TravelBusTicketStepDefinitions() {
        And("user choose \"([^\"]*)\" bus trip", (String tripType) -> {
            bukalapak.travelBusTicketPage().validateOnBusTicketingPage();
            bukalapak.travelBusTicketPage().switchTripType(tripType);
        });

        And("user search city for bus schedule", () -> {
            //search stasiun awal
            bukalapak.travelBusTicketPage().tapElement("bus_origin_city");
            bukalapak.travelBusTicketPage().validateOnSearchCityPage();
            bukalapak.travelBusTicketPage().searchCity("awal");
            bukalapak.travelBusTicketPage().tapElement("bus_first_element_terminal");
            bukalapak.travelBusTicketPage().setOriginCity();

            //search stasiun tujuan
            bukalapak.iOSBasePage().tapElement("bus_destination_city");
            bukalapak.travelBusTicketPage().validateOnSearchCityPage();
            bukalapak.travelBusTicketPage().searchCity("tujuan");
            bukalapak.iOSBasePage().tapElement("bus_first_element_terminal");
            bukalapak.travelBusTicketPage().setDestinationCity();
        });

        And("user choose bus passenger count and search schedule", () -> {
            bukalapak.travelBusTicketPage().setCountPassenger(1);
            bukalapak.travelBusTicketPage().tapOnSearchBusButton();
        });

        And("user select bus departure schedule", () -> {
            bukalapak.travelBusSchedulePage().validateOnBusSchedulePage();
            bukalapak.travelBusSchedulePage().validateOriginCityDeparture();
            bukalapak.travelBusSchedulePage().validateDestinationCityDeparture();
            bukalapak.travelBusSchedulePage().tapFirstSchedule("departure");

            //Validate detail order page
            bukalapak.travelBusDetailOrderPage().validateOnDetailOrdersPage();
            bukalapak.travelBusDetailOrderPage().validateBusPrice("departure");
            bukalapak.travelBusDetailOrderPage().validateBusTripTime("departure");
            bukalapak.travelBusDetailOrderPage().validateBusBoardingPoint("departure");
            bukalapak.travelBusDetailOrderPage().validateBusDroppingPoint("departure");
            bukalapak.travelBusTicketPage().tapBackButtonBus();
        });

        Then("user able change bus schedule to another criteria", () -> {
            bukalapak.travelBusSchedulePage().validateOnBusSchedulePage();
            bukalapak.travelBusSchedulePage().validateOriginCityDeparture();
            bukalapak.travelBusSchedulePage().validateDestinationCityDeparture();
            bukalapak.travelBusSchedulePage().tapAnotherSchedule();

            //Validate detail order page
            bukalapak.travelBusDetailOrderPage().validateOnDetailOrdersPage();
            bukalapak.travelBusDetailOrderPage().validateBusPrice("departure");
            bukalapak.travelBusDetailOrderPage().validateBusTripTime("departure");
            bukalapak.travelBusDetailOrderPage().validateBusBoardingPoint("departure");
            bukalapak.travelBusDetailOrderPage().validateBusDroppingPoint("departure");
        });

        And("user select bus return schedule", () -> {
            bukalapak.travelBusSchedulePage().validateOnBusSchedulePage();
            bukalapak.travelBusSchedulePage().validateOriginCityReturn();
            bukalapak.travelBusSchedulePage().validateDestinationCityReturn();
            bukalapak.travelBusSchedulePage().setBusTravelNameReturn();
            bukalapak.travelBusSchedulePage().setReturnTime();
            bukalapak.travelBusSchedulePage().setReturnBusPrice();
            bukalapak.travelBusSchedulePage().setReturnBoardingPoint();
            bukalapak.travelBusSchedulePage().setReturnDroppingPoint();
            bukalapak.iOSBasePage().tapElement("bus_first_schedule_element");

            //Validate detail order page
            bukalapak.travelBusDetailOrderPage().validateOnDetailOrdersPage();
            bukalapak.travelBusDetailOrderPage().validateBusPrice("return");
            bukalapak.travelBusDetailOrderPage().validateBusTripTime("return");
            bukalapak.travelBusDetailOrderPage().validateBusBoardingPoint("return");
            bukalapak.travelBusDetailOrderPage().validateBusDroppingPoint("return");
            bukalapak.travelBusTicketPage().tapOnPilihKursi();
        });

        And("user choose bus seating \"([^\"]*)\"", (String tripType) -> {
            bukalapak.travelBusSeatingPage().tapOnBusSeat("departure");
            bukalapak.travelBusSeatingPage().tapOnLanjutButton();

            if (tripType.equals("round trip")) {
                bukalapak.travelBusSeatingPage().tapOnBusSeat("return");
                bukalapak.travelBusSeatingPage().tapOnLanjutButton();
            }
        });

        And("user fill bus passenger form", () -> {
            bukalapak.travelBusPassengerFormPage().validateOnPassengerFormPage();
            bukalapak.travelBusPassengerFormPage().typeOnInputFullName(true);
            bukalapak.travelBusPassengerFormPage().typeOnInputEmail(true);
            bukalapak.travelBusPassengerFormPage().typeOnInputPhoneNumber(true);
            bukalapak.travelBusPassengerFormPage().tapOnSameWithCustomer();
            bukalapak.travelBusPassengerFormPage().tapOnTitleDropdown();
            bukalapak.travelBusPassengerFormPage().typeOnInputAgePassenger();
            bukalapak.travelBusPassengerFormPage().tapLanjutButtonBus();
        });

        When("user navigate to \"Tiket Bus\" page", () -> {
            bukalapak.travelBusTicketPage().clickTiketBusMenu();
        });

        And("user search \"([^\"]*)\" bus schedule", (String tripType) -> {
            BusData.setTripType(tripType.toLowerCase());
            bukalapak.travelBusTicketPage().switchTripType(BusData.getTripType());
            bukalapak.travelBusTicketPage().tapDepartureCity();
            bukalapak.travelBusTicketPage().searchCity("departure");
            bukalapak.travelBusTicketPage().tapDestinationCity();
            bukalapak.travelBusTicketPage().searchCity("destination");
            bukalapak.travelBusTicketPage().tapDatepicker("departure", Integer.valueOf(dotenv.get("BUS_DEPARTURE_DATE")));
            if (BusData.getTripType().equals("round trip")) {
                bukalapak.travelBusTicketPage().tapDatepicker("return", Integer.valueOf(dotenv.get("BUS_RETURN_DATE")));
            }
            bukalapak.travelBusTicketPage().setCountPassenger(1);
            bukalapak.travelBusTicketPage().tapOnSearchBusButton();
        });

        When("user choose bus schedule for one way trip", () -> {
            bukalapak.travelBusSchedulePage().validateOnBusSchedulePage();
            bukalapak.travelBusSchedulePage().validateOriginCityDeparture();
            bukalapak.travelBusSchedulePage().validateDestinationCityDeparture();
            bukalapak.travelBusSchedulePage().tapFirstSchedule("departure");

            //Validate detail order page
            bukalapak.travelBusDetailOrderPage().validateOnDetailOrdersPage();
            bukalapak.travelBusDetailOrderPage().validateBusPrice("departure");
            bukalapak.travelBusDetailOrderPage().validateBusTripTime("departure");
            bukalapak.travelBusDetailOrderPage().validateBusBoardingPoint("departure");
            bukalapak.travelBusDetailOrderPage().validateBusDroppingPoint("departure");
            bukalapak.travelBusTicketPage().tapOnPilihKursi();

            //Seating
            bukalapak.travelBusSeatingPage().validateOnSeatingPage();
            bukalapak.travelBusSeatingPage().tapOnBusSeat("departure");
            bukalapak.travelBusSeatingPage().tapOnLanjutButton();
        });

        When("user choose bus schedule for round trip", () -> {
            bukalapak.travelBusSchedulePage().validateOnBusSchedulePage();
            bukalapak.travelBusSchedulePage().validateOriginCityDeparture();
            bukalapak.travelBusSchedulePage().validateDestinationCityDeparture();
            bukalapak.travelBusSchedulePage().tapFirstSchedule("departure");

            //Validate detail order page
            bukalapak.travelBusDetailOrderPage().validateOnDetailOrdersPage();
            bukalapak.travelBusDetailOrderPage().validateBusPrice("departure");
            bukalapak.travelBusDetailOrderPage().validateBusTripTime("departure");
            bukalapak.travelBusDetailOrderPage().validateBusBoardingPoint("departure");
            bukalapak.travelBusDetailOrderPage().validateBusDroppingPoint("departure");
            bukalapak.travelBusTicketPage().tapOnPilihButton();
            if (BusData.getTripType().equals("round trip")) {
                bukalapak.travelBusSchedulePage().validateOnBusSchedulePage();
                bukalapak.travelBusSchedulePage().validateOriginCityReturn();
                bukalapak.travelBusSchedulePage().validateDestinationCityReturn();
                bukalapak.travelBusSchedulePage().tapFirstSchedule("return");

                //Validate detail order page
                bukalapak.travelBusDetailOrderPage().validateOnDetailOrdersPage();
                bukalapak.travelBusDetailOrderPage().validateBusPrice("return");
                bukalapak.travelBusDetailOrderPage().validateBusTripTime("return");
                bukalapak.travelBusDetailOrderPage().validateBusBoardingPoint("return");
                bukalapak.travelBusDetailOrderPage().validateBusDroppingPoint("return");
                bukalapak.travelBusTicketPage().tapOnPilihKursi();
            }

            //Seating
            bukalapak.travelBusSeatingPage().tapOnBusSeat("departure");
            bukalapak.travelBusSeatingPage().tapOnLanjutButton();
            bukalapak.travelBusSeatingPage().tapOnBusSeat("return");
            bukalapak.travelBusSeatingPage().tapOnLanjutButton();
        });

        And("user booking bus schedule", () -> {
            // Fill booking form
            bukalapak.travelBusPassengerFormPage().validateOnPassengerFormPage();
            bukalapak.travelBusPassengerFormPage().typeOnInputFullName(true);
            bukalapak.travelBusPassengerFormPage().typeOnInputEmail(true);
            bukalapak.travelBusPassengerFormPage().typeOnInputPhoneNumber(true);
            bukalapak.travelBusPassengerFormPage().tapOnSameWithCustomer();
            bukalapak.travelBusPassengerFormPage().tapOnTitleDropdown();
            bukalapak.travelBusPassengerFormPage().typeOnInputAgePassenger();
            bukalapak.travelBusPassengerFormPage().fillPassengerIdentityIfRequired();
            bukalapak.travelBusPassengerFormPage().tapLanjutButtonBus();
        });

        And("user add more passengers than the maximum limit", () -> {
            bukalapak.travelBusTicketPage().setCountPassenger(4);
        });

        And("max number of passangers should be 3", () -> {
            bukalapak.travelBusTicketPage().validateCountPassenger();
        });

        And("user click back button from bus checkout page", () -> {
            bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            bukalapak.travelBusCheckoutPage().cancelTicketBusTransaction();
        });

        And("page will have redirected to bus landing page", () -> {
            bukalapak.travelBusTicketPage().validateOnBusTicketingPage();
        });

        And("user fill in the bus booking form with invalid contact", () -> {
            bukalapak.travelBusPassengerFormPage().validateOnPassengerFormPage();
            bukalapak.travelBusPassengerFormPage().typeOnInputFullName(false);
            bukalapak.travelBusPassengerFormPage().typeOnInputEmail(false);
            bukalapak.travelBusPassengerFormPage().typeOnInputPhoneNumber(false);
            bukalapak.travelBusPassengerFormPage().tapOnSameWithCustomer();
        });

        And("the validation text will have equalled the bus contact format must match", () -> {
            bukalapak.travelBusPassengerFormPage().validateInvalidNameFormat();
            bukalapak.travelBusPassengerFormPage().validateInvalidEmailFormat();
            bukalapak.travelBusPassengerFormPage().validateInvalidPhoneFormat();
        });

        And("pop up verification account tiket bus will have displayed", () -> {
            bukalapak.travelBusSeatingPage().validatePopupVerifyAccountBus();
        });

        When("^user sort bus schedule by (cheapest|most expensive) price$", (String priceType) -> {
            bukalapak.travelBusSchedulePage().tapOnUrutkan();
            bukalapak.travelBusSchedulePage().sortingByPrice(priceType);
        });

        Then("^user see bus schedule list sorted by (?:cheapest|most expensive) price$", () -> {
            bukalapak.travelBusSchedulePage().waitForBusSchedulePriceList();
            bukalapak.travelBusSchedulePage().validateSortingByPriceresult();
        });

        And("user filter bus schedule by topmost bus name filter", () -> {
            bukalapak.travelBusSchedulePage().tapOnFilter();
            bukalapak.travelBusSchedulePage().tapOnBusName();
            bukalapak.travelBusSchedulePage().tapOnTerapkan();
        });

        And("user see bus schedule list filtered by bus name", () -> {
            bukalapak.travelBusSchedulePage().validateFilterByBusName();
        });
    }
}
