package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * @Author: Ayu Musfita
 * @Date: 25/02/20, Tue
 **/
public class HotelStepDefinitions extends TestInstrument implements En {
    public HotelStepDefinitions() {

        Then("^the Hotel landing page will have displayed$", () -> {
           bukalapak.travelHotelLandingPage().validateOnPage();
        });

        When("^user inputs hotel guest amount lower than room amount$", () -> {
            bukalapak.travelHotelLandingPage().tapOnRoomField();
            bukalapak.travelHotelLandingPage().validatePopUpRoomDisplayed();
            bukalapak.travelHotelLandingPage().setGuestAmount(2);
            bukalapak.travelHotelLandingPage().setRoomAmount(3);
            bukalapak.travelHotelLandingPage().tapOnPilihButton();
        });

        Then("^error message (.*) should be displayed$", (String errorType) -> {
            bukalapak.travelHotelLandingPage().validateErrorMessageDisplayed(errorType);
        });

        When("^user inputs check out date more 15 days than check in date$", () -> {
            bukalapak.travelHotelLandingPage().tapOnCheckinField();
            bukalapak.travelHotelLandingPage().tapOnNextMonth();
            bukalapak.travelHotelLandingPage().setCheckinDate(5);
            bukalapak.travelHotelLandingPage().setCheckoutDate(27);
            bukalapak.travelHotelLandingPage().tapOnPilihButton();
        });

        When("^user search for (.*) hotel destination$", (String state) -> {
            bukalapak.travelHotelLandingPage().tapOnDestinationField();
            bukalapak.travelHotelLandingPage().validateOnDestinationListPage();
            bukalapak.travelHotelLandingPage().typeOnSearchHotelField(state);
        });

        Then("^list of hotel destination result should be displayed$", () -> {
            bukalapak.travelHotelLandingPage().validateHotelDisplayedOnList(dotenv.get("HOTEL_NAME"));
        });

        Then("^hotel tidak tersedia page should be displayed$", () -> {
            bukalapak.travelHotelLandingPage().validateHotelNotFoundDisplayed();
        });

        When("^user search hotel by \"([^\"]*)\" name$", (String by) -> {
            bukalapak.travelHotelLandingPage().tapOnDestinationField();
            bukalapak.travelHotelLandingPage().validateOnDestinationListPage();
            bukalapak.travelHotelLandingPage().typeOnSearchHotelField(by);
        });

        Then("^list of hotel result by \"([^\"]*)\" should be displayed$", (String by) -> {
            bukalapak.travelHotelLandingPage().validateHotelDisplayedOnList(dotenv.get("HOTEL_"+by.toUpperCase()));
        });

        When("^user search hotel by \"([^\"]*)\" with valid requirement$", (String state) -> {
            bukalapak.travelHotelLandingPage().tapOnDestinationField();
            bukalapak.travelHotelLandingPage().validateOnDestinationListPage();
            bukalapak.travelHotelLandingPage().typeOnSearchHotelField(state);
            bukalapak.travelHotelLandingPage().tapOnCheckinField();
            bukalapak.travelHotelLandingPage().tapOnNextMonth();
            bukalapak.travelHotelLandingPage().setCheckinDate(10);
            bukalapak.travelHotelLandingPage().setCheckoutDate(12);
            bukalapak.travelHotelLandingPage().tapOnPilihButton();
            bukalapak.travelHotelLandingPage().tapOnRoomField();
            bukalapak.travelHotelLandingPage().validatePopUpRoomDisplayed();
            bukalapak.travelHotelLandingPage().setGuestAmount(1);
            bukalapak.travelHotelLandingPage().setRoomAmount(1);
            bukalapak.travelHotelLandingPage().tapOnPilihButton();
            bukalapak.travelHotelLandingPage().setLengthOfStay();
            bukalapak.travelHotelLandingPage().setCheckInOutDate();
            bukalapak.travelHotelLandingPage().tapOnCariHotel();
        });
    }
}
