package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class HotelDetailStepDefinitions extends TestInstrument implements En {
    public HotelDetailStepDefinitions() {

        And("^hotel detail is displayed$", () -> {
            bukalapak.travelHotelDetailPage().validateOnPage();
        });

        When("^user taps on hotel detail description$", () -> {
            bukalapak.travelHotelDetailPage().tapTentangHotelButton();
        });

        Then("^hotel description is displayed$", () -> {
            bukalapak.travelHotelDetailPage().validateOnHotelDescriptionPage();
        });

        When("^user tap on choose room option from hotel detail$", () -> {
            bukalapak.travelHotelDetailPage().validateOnPage();
            bukalapak.travelHotelDetailPage().tapPilihKamarButton();
        });
    }
}
