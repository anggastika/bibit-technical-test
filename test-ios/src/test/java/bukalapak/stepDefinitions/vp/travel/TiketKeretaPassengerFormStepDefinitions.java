package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by ferawati on 03/09/20.
 */
public class TiketKeretaPassengerFormStepDefinitions extends TestInstrument implements En {
    public TiketKeretaPassengerFormStepDefinitions(){
        When("user fill in the booking form with details of the passenger format is invalid", () -> {
            bukalapak.travelTrainDetailOrderPage().tapOnPassangerDetail();
            bukalapak.travelTrainPassengerFormPage().typeOnNamaLengkapEditText(false);
            bukalapak.travelTrainPassengerFormPage().typeOnNoIdentitas(false);
            bukalapak.travelTrainPassengerFormPage().tapSimpanButton();
        });

        Then("the validation text will have equalled the details of the passenger format must match", () -> {
            bukalapak.travelTrainPassengerFormPage().validateFullNameErrorMessage("invalid");
            bukalapak.travelTrainPassengerFormPage().validateIdentityErrorMessage("invalid");
        });

        When("user fill in the booking form with the details of the passenger blank", () -> {
            bukalapak.travelTrainDetailOrderPage().tapOnPassangerDetail();
            bukalapak.travelTrainPassengerFormPage().clearFullname();
            bukalapak.travelTrainPassengerFormPage().clearIdentity();
            bukalapak.travelTrainPassengerFormPage().tapSimpanButton();
        });

        Then("the validation text will have equalled the details passanger can not be empty", () -> {
            bukalapak.travelTrainPassengerFormPage().validateFullNameErrorMessage("empty");
            bukalapak.travelTrainPassengerFormPage().validateIdentityErrorMessage("empty");
        });
    }
}
