package bukalapak.stepDefinitions.vp.deals;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * @Author: Fixco Amrizal Candra
 * @Date: 28/07/20
 **/

public class TiketEventStepDefinitions  extends TestInstrument implements En {

    public TiketEventStepDefinitions() {
        Then("^the Event ticket booking page will have( not)? displayed$", (String flag) -> {
            bukalapak.tiketEventBookingPage().validateOnPage(flag == null);
        });

        Then("user choose Event ticket schedule by category ([^\"]*)", (String category) -> {
            bukalapak.tiketEventLandingPage().chooseCategoryEvent(category);
            bukalapak.tiketEventLandingPage().setEventTitleData(dotenv.get("EVENT_INDEX"));
            bukalapak.tiketEventLandingPage().tapOnEventBanner(dotenv.get("EVENT_INDEX"));
        });

        Then("user choose Event ticket with valid data", () -> {
            bukalapak.tiketEventLandingPage().submitDataEventTicket();
        });

        Then("user share Event from detail page", () -> {
            bukalapak.tiketEventLandingPage().tapShareButton();
        });

        Then("the Event will shown social media", () -> {
            bukalapak.tiketEventLandingPage().validateShareMedia();
        });

        Then("^user fill in the booking form with (valid|invalid) data$", (String type) -> {
            bukalapak.tiketEventBookingPage().inputNamaLengkap(type);
            bukalapak.tiketEventBookingPage().inputEmail(type);
            bukalapak.tiketEventBookingPage().inputNIK(type);
            bukalapak.tiketEventBookingPage().inputPhoneNumber(type);
            if (type.equals("valid")) {
                bukalapak.tiketEventBookingPage().tapOnLanjutButton();
                bukalapak.tiketEventBookingPage().tapCobaLagiButton();
            }
        });

        When("user not fill in the booking form", () -> {
            bukalapak.tiketEventBookingPage().clearFullname();
            bukalapak.tiketEventBookingPage().clearEmail();
            bukalapak.tiketEventBookingPage().clearNIK();
            bukalapak.tiketEventBookingPage().clearPhoneNumber();
        });

        Then("^validation text must match (invalid|empty) booking format$", (String type) -> {
            bukalapak.tiketEventBookingPage().validateFullNameErrorMessage(type);
            bukalapak.tiketEventBookingPage().validateEmailErrorMessage(type);
            bukalapak.tiketEventBookingPage().validateNIKErrorMessage(type);
            bukalapak.tiketEventBookingPage().validatePhoneNumberErrorMessage(type);
        });

        When("user sorting Events by location ([^\"]*)", (String city) -> {
            bukalapak.tiketEventLandingPage().openEventFilter();
            bukalapak.tiketEventLandingPage().chooseEventFilter(city);
        });

        Then("user sees a list of events according to location ([^\"]*)", (String city) -> {
            bukalapak.tiketEventLandingPage().validateEventFilter(city);
        });

        When("user search Event name with invalid data", () -> {
            bukalapak.tiketEventLandingPage().searchInvalidEvent();
        });

        Then("the validation text will have equalled no events found", () -> {
            bukalapak.tiketEventLandingPage().validateEventIsNotFound();
        });

        When("^user had buys a Event ticket with valid data$", () -> {
            bukalapak.tiketEventLandingPage().submitDataEventTicket();
            bukalapak.tiketEventBookingPage().inputNamaLengkap("valid");
            bukalapak.tiketEventBookingPage().inputEmail("valid");
            bukalapak.tiketEventBookingPage().inputNIK("valid");
            bukalapak.tiketEventBookingPage().inputPhoneNumber("valid");
            bukalapak.tiketEventBookingPage().tapOnLanjutButton();
            bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            bukalapak.checkoutNonMarketplacePage().choosePaymentMethod("Alfamart");
            bukalapak.checkoutNonMarketplacePage().setTotalTagihan();
            bukalapak.tiketEventCheckoutPage().validateCheckoutDetail();
            bukalapak.checkoutNonMarketplacePage().tapOnBayarButton();
        });

    }
}
