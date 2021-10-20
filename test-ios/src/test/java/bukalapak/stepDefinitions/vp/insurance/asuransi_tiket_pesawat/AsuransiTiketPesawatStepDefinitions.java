package bukalapak.stepDefinitions.vp.insurance.asuransi_tiket_pesawat;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * @Author: Ayu Musfita
 * @Date: 09/09/20, Wed
 **/
public class AsuransiTiketPesawatStepDefinitions extends TestInstrument implements En {

    public AsuransiTiketPesawatStepDefinitions() {

        When("^user booking flight schedule for domestic flight with insurance$", () -> {
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
            bukalapak.tiketPesawatBookingPage().tapOnInsuranceCheckbox(true);
            bukalapak.tiketPesawatBookingPage().collectFlightData();
            bukalapak.tiketPesawatBookingPage().tapOnSelectPaymentMethod();
            bukalapak.tiketPesawatBookingPage().confirmPriceChange();
        });
    }
}
