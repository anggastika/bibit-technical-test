package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by Salis Mahmudah on 7/04/20.
 */

public class HotelBookingDetailStepDefinitions extends TestInstrument implements En {
    public HotelBookingDetailStepDefinitions() {
        When("^user booking the hotel$", () -> {
            bukalapak.travelHotelBookingDetailPage().validateOnBookingDetailPage();
            bukalapak.travelHotelBookingDetailPage().validateBookingData();
            bukalapak.travelHotelBookingDetailPage().tapContactCustomerSection();
            bukalapak.travelHotelBookingDetailPage().validateContactForm();
            bukalapak.travelHotelBookingDetailPage().fillContactForm("valid");
            bukalapak.travelHotelBookingDetailPage().tapSaveButton();
            bukalapak.travelHotelBookingDetailPage().validateContactData();
        });

        And("^user proceed Hotel booking to checkout$", () -> {
            bukalapak.travelHotelBookingDetailPage().setHotelAmount();
            bukalapak.travelHotelBookingDetailPage().tapChoosePaymentMethod();
        });

        When("^user booking the hotel for another guest$", () -> {
            bukalapak.travelHotelBookingDetailPage().validateOnBookingDetailPage();
            bukalapak.travelHotelBookingDetailPage().validateBookingData();
            bukalapak.travelHotelBookingDetailPage().tapContactCustomerSection();
            bukalapak.travelHotelBookingDetailPage().validateContactForm();
            bukalapak.travelHotelBookingDetailPage().fillContactForm("valid");
            bukalapak.travelHotelBookingDetailPage().tapSaveButton();
            bukalapak.travelHotelBookingDetailPage().validateContactData();
            bukalapak.travelHotelBookingDetailPage().tapAnotherGuestSection();
            bukalapak.travelHotelBookingDetailPage().fillAnotherGuestData("valid");
            bukalapak.travelHotelBookingDetailPage().tapSaveButton();
            bukalapak.travelHotelBookingDetailPage().validateAnotherGuestData();
        });

        When("^booking the hotel with the contact format order is invalid$", () -> {
            bukalapak.travelHotelBookingDetailPage().validateOnBookingDetailPage();
            bukalapak.travelHotelBookingDetailPage().validateBookingData();
            bukalapak.travelHotelBookingDetailPage().tapContactCustomerSection();
            bukalapak.travelHotelBookingDetailPage().validateContactForm();
            bukalapak.travelHotelBookingDetailPage().fillContactForm("invalid");
            bukalapak.travelHotelBookingDetailPage().tapSaveButton();
        });

        Then("^the validation text will have equalled the hotel contact format must match$", () -> {
            bukalapak.travelHotelBookingDetailPage().validateInvalidContact();
        });

        When("^user booking the hotel with the another guest format order is invalid$", () -> {
            bukalapak.travelHotelBookingDetailPage().validateOnBookingDetailPage();
            bukalapak.travelHotelBookingDetailPage().validateBookingData();
            bukalapak.travelHotelBookingDetailPage().tapAnotherGuestSection();
            bukalapak.travelHotelBookingDetailPage().fillAnotherGuestData("invalid");
            bukalapak.travelHotelBookingDetailPage().tapSaveButton();
        });

        Then("^the validation text will have equalled the hotel another guest format must match$", () -> {
            bukalapak.travelHotelBookingDetailPage().validateInvalidAnotherGuest();
        });
    }
}
