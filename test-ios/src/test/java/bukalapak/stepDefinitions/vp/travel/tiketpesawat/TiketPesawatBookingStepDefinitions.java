package bukalapak.stepDefinitions.vp.travel.tiketpesawat;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TiketPesawatBookingStepDefinitions extends TestInstrument implements En {
    public TiketPesawatBookingStepDefinitions() {
        When("^user fill in the flight booking form with the contact format order is invalid$", () -> {
            bukalapak.tiketPesawatBookingPage().tapOnEditBuyerInfo();
            bukalapak.tiketPesawatBookingPage().editBuyerName(false);
            bukalapak.tiketPesawatBookingPage().editBuyerEmail(false);
            bukalapak.tiketPesawatBookingPage().editBuyerPhoneNumber(false);
            bukalapak.tiketPesawatBookingPage().tapOnSaveButton();
        });

        Then("^the flight validation text will have equalled the contact format must match$", () -> {
            bukalapak.tiketPesawatBookingPage().validateIncorrectEmailFormatError();
            bukalapak.tiketPesawatBookingPage().validateInvalidNameFormatError();
            bukalapak.tiketPesawatBookingPage().validateMinLengthPhoneError();
        });

        When("user booking flight schedule for domestic flight", () -> {
            bukalapak.tiketPesawatBookingPage().tapOnEditBuyerInfo();
            bukalapak.tiketPesawatBookingPage().editBuyerName(true);
            bukalapak.tiketPesawatBookingPage().editBuyerEmail(true);
            bukalapak.tiketPesawatBookingPage().editBuyerPhoneNumber(true);
            bukalapak.tiketPesawatBookingPage().tapOnSaveButton();
            bukalapak.tiketPesawatBookingPage().validateBuyerData();
            bukalapak.tiketPesawatBookingPage().tapOnPassengerDetail();
            bukalapak.tiketPesawatBookingPage().fillPassengerForm(true);
            bukalapak.tiketPesawatBookingPage().tapOnSaveButton();
            bukalapak.tiketPesawatBookingPage().validatePassengerData();
            bukalapak.tiketPesawatBookingPage().tapOnInsuranceCheckbox(false);
            bukalapak.tiketPesawatBookingPage().collectFlightData();
            bukalapak.tiketPesawatBookingPage().tapOnSelectPaymentMethod();
            bukalapak.tiketPesawatBookingPage().confirmPriceChange();
        });

        When("^user booking flight schedule for international flight$", () -> {
            bukalapak.tiketPesawatBookingPage().tapOnEditBuyerInfo();
            bukalapak.tiketPesawatBookingPage().editBuyerName(true);
            bukalapak.tiketPesawatBookingPage().editBuyerEmail(true);
            bukalapak.tiketPesawatBookingPage().editBuyerPhoneNumber(true);
            bukalapak.tiketPesawatBookingPage().tapOnSaveButton();
            bukalapak.tiketPesawatBookingPage().validateBuyerData();
            bukalapak.tiketPesawatBookingPage().tapOnPassengerDetail();
            bukalapak.tiketPesawatBookingPage().fillPassengerForm(true);
            bukalapak.tiketPesawatBookingPage().editPassengerPassportExpiry();
            bukalapak.tiketPesawatBookingPage().tapOnSaveButton();
            bukalapak.tiketPesawatBookingPage().validatePassengerData();
            bukalapak.tiketPesawatBookingPage().tapOnInsuranceCheckbox(false);
            bukalapak.tiketPesawatBookingPage().collectFlightData();
            bukalapak.tiketPesawatBookingPage().tapOnSelectPaymentMethod();
            bukalapak.tiketPesawatBookingPage().confirmPriceChange();
        });

        When("user click back button to cancel the flight transaction", () -> {
            bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            bukalapak.tiketPesawatBookingPage().cancelFlightTransaction();
        });

        Then("the validation text will have equalled the flight contact format must match", () -> {
            bukalapak.tiketPesawatBookingPage().validateInvalidFullname();
            bukalapak.tiketPesawatBookingPage().validateInvalidEmail();
            bukalapak.tiketPesawatBookingPage().validateInvalidPhoneNumber();
        });

        Then("page will have redirected to order detail pesawat page", () -> {
            bukalapak.tiketPesawatBookingPage().validateUserIsOnOrderDetailPage();
        });

    }
}
