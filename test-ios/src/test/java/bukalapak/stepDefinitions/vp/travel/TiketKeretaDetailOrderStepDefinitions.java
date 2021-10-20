package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by Ayu Musfita on 22/01/20.
 */
public class TiketKeretaDetailOrderStepDefinitions extends TestInstrument implements En {
    public TiketKeretaDetailOrderStepDefinitions(){

        And("user booking (.*) train schedule", (String trip) -> {
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

            // set passenger data
            bukalapak.travelTrainDetailOrderPage().tapOnPassangerDetail();
            bukalapak.travelTrainDetailOrderPage().validateOnPassengerContactPage();
            bukalapak.travelTrainDetailOrderPage().setPassengerName();
            bukalapak.travelTrainDetailOrderPage().setPassengerIdentity();
            bukalapak.travelTrainDetailOrderPage().tapOnSimpan();
        });

        And("user proceed Tiket Kereta booking to checkout", () -> {
            bukalapak.travelTrainDetailOrderPage().setTotalAmount();
            bukalapak.travelTrainDetailOrderPage().tapOnLanjutkanPesanan();
            bukalapak.travelTrainDetailOrderPage().validatePopUpOrderDisplayed();
            bukalapak.travelTrainDetailOrderPage().tapOnYaBenar();
        });
    
    }
}
