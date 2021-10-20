package bukalapak.stepDefinitions.vp.insurance.asuransi_tiket_kereta;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * @Author: Ayu Musfita
 * @Date: 03/09/20, Thu
 **/
public class AsuransiTiketKeretaStepDefinitions extends TestInstrument implements En {

    public AsuransiTiketKeretaStepDefinitions() {

        When("^user (ticks|unticks) the train insurance checkbox$", (String state) -> {
            bukalapak.travelTrainDetailOrderPage().tapOnInsuranceCheckbox(state);
            bukalapak.travelTrainDetailOrderPage().setInsuranceFee();
        });

        And("user booking (.*) train schedule with train insurance", (String trip) -> {

            bukalapak.travelTrainDetailOrderPage().validateOnPage();
            bukalapak.travelTrainDetailOrderPage().validateTrainClass("departure");
            bukalapak.travelTrainDetailOrderPage().validateTrainName("departure");
            bukalapak.travelTrainDetailOrderPage().validateTrainCode("departure");
            bukalapak.travelTrainDetailOrderPage().validateTrainTime("departure");

            if (trip.toLowerCase().equals("round trip")) {
                bukalapak.travelTrainDetailOrderPage().validateTrainClass("return");
                bukalapak.travelTrainDetailOrderPage().validateTrainName("return");
                bukalapak.travelTrainDetailOrderPage().validateTrainCode("return");
                bukalapak.travelTrainDetailOrderPage().validateTrainTime("return");
            }
            // set buyer data
            bukalapak.travelTrainDetailOrderPage().tapOnChangeBuyerContact();
            bukalapak.travelTrainDetailOrderPage().validateOnBuyerContactPage();
            bukalapak.travelTrainDetailOrderPage().setBuyerContactName();
            bukalapak.travelTrainDetailOrderPage().setBuyerContactEmail();
            bukalapak.travelTrainDetailOrderPage().setBuyerContactPhone();
            bukalapak.travelTrainDetailOrderPage().tapOnSimpan();

            // set passanger data
            bukalapak.travelTrainDetailOrderPage().tapOnPassangerDetail();
            bukalapak.travelTrainDetailOrderPage().validateOnPassengerContactPage();
            bukalapak.travelTrainDetailOrderPage().setPassengerName();
            bukalapak.travelTrainDetailOrderPage().setPassengerIdentity();
            bukalapak.travelTrainDetailOrderPage().tapOnSimpan();
        });

        Then("user can see list of train insurance benefit on detail order page", () -> {
            bukalapak.travelTrainDetailOrderPage().tapOnInsuranceDetail();
            bukalapak.travelTrainDetailOrderPage().validateOnInsuranceBenefit();
        });
    }
}
