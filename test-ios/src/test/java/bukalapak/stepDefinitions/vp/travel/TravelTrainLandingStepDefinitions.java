package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import bukalapak.data.vp.tix.TiketKeretaData;
import cucumber.api.java8.En;

/**
 * Created by sekarayukarindra on 24/02/20.
 */
public class TravelTrainLandingStepDefinitions extends TestInstrument implements En {

    public TravelTrainLandingStepDefinitions(){
        When("user navigate to \"Tiket Kereta\" page", () -> {
            bukalapak.travelTrainLandingPage().clickTiketKeretaMenu();
        });

        And("^user search (.*) train ticket with valid schedule$", (String tripType) -> {
            TiketKeretaData.setTrainTripType(tripType.toLowerCase());
            bukalapak.travelTrainLandingPage().tapOnOriginStation();
            bukalapak.travelTrainLandingPage().searchStation("origin", dotenv.get("ORIGIN_STATION"));
            bukalapak.travelTrainLandingPage().tapOnDestinationStation();
            bukalapak.travelTrainLandingPage().searchStation("destination", dotenv.get("DESTINATION_STATION"));
            bukalapak.travelTrainLandingPage().tapOnDatePicker("departure");
            bukalapak.travelTrainLandingPage().chooseTripDate(Integer.valueOf(dotenv.get("TRAIN_DEPARTURE_DATE")));
            if (TiketKeretaData.getTrainTripType().equals("round trip")) {
                bukalapak.travelTrainLandingPage().tapOnDatePicker("return");
                bukalapak.travelTrainLandingPage().chooseTripDate(Integer.valueOf(dotenv.get("TRAIN_RETURN_DATE")));
            }
            bukalapak.travelTrainLandingPage().setOnFieldPenumpang(1, 0);
            bukalapak.travelTrainLandingPage().tapOnSearchTrain();
        });

        When("user search train ticket with the same station", () -> {
            bukalapak.travelTrainLandingPage().tapOnOriginStation();
            bukalapak.travelTrainLandingPage().searchStation("origin", dotenv.get("ORIGIN_STATION"));
            bukalapak.travelTrainLandingPage().tapOnDestinationStation();
            bukalapak.travelTrainLandingPage().searchStation("destination", dotenv.get("ORIGIN_STATION"));
        });

        Then("the validation text will have equalled the station must be different", () -> {
            bukalapak.travelTrainLandingPage().validateTrainStationMustBeDifferent();
        });

        When("user search train ticket with the with more infant passengers than adults", () -> {
            bukalapak.travelTrainLandingPage().setOnFieldPenumpang(1, 2);
        });

        Then("the validation text will have equalled the number of baby passengers cannot be more than adult", () -> {
            bukalapak.travelTrainLandingPage().validateAdultMustBeExceededInfant();
        });
    }
}
