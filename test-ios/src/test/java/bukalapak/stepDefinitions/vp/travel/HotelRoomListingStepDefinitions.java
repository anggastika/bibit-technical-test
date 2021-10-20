package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class HotelRoomListingStepDefinitions extends TestInstrument implements En {
    public HotelRoomListingStepDefinitions() {
        Then("^list of available room will displayed$", () -> {
            bukalapak.travelHotelRoomListingPage().validateRoomListData();
        });

        Then("^user should be able to choose specific room$", () -> {
            bukalapak.travelHotelRoomListingPage().tapOnBookRoomButton();
        });

        And("^user select the room$", () -> {
            bukalapak.travelHotelRoomListingPage().validateRoomListData();
            bukalapak.travelHotelRoomListingPage().setRoomType();
            bukalapak.travelHotelRoomListingPage().setRoomPrice();
            bukalapak.travelHotelRoomListingPage().tapOnBookRoomButton();
        });
    }
}
