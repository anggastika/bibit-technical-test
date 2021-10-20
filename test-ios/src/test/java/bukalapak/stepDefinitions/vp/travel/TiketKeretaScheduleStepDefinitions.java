package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by Ayu Musfita on 24/02/20.
 */
public class TiketKeretaScheduleStepDefinitions extends TestInstrument implements En {
    public TiketKeretaScheduleStepDefinitions(){

        And("^user chooses an (.*) class train ticket for (.*)$", (String trainClass, String tripType) -> {
            bukalapak.travelTrainSchedulePage().validateOnPage();
            bukalapak.travelTrainSchedulePage().validateStation("departure");
            bukalapak.travelTrainSchedulePage().tapOnFilterIcon();
            bukalapak.travelTrainSchedulePage().tapOnFilterOption("Kelas");
            bukalapak.travelTrainSchedulePage().checkTrainClassFilter(trainClass, "departure");
            bukalapak.travelTrainSchedulePage().tapOnTerapkanButton();
            bukalapak.travelTrainSchedulePage().validateScheduleClassDisplayed(trainClass, "departure");
            bukalapak.travelTrainSchedulePage().setTrainName("departure");
            bukalapak.travelTrainSchedulePage().setTrainPrice("departure");
            bukalapak.travelTrainSchedulePage().setTrainTime("departure");
            bukalapak.travelTrainSchedulePage().setStationCode("departure");
            bukalapak.travelTrainSchedulePage().tapOnScheduleCard();
            if(tripType.toLowerCase().equals("round trip")) {
                bukalapak.travelTrainSchedulePage().validateOnPage();
                bukalapak.travelTrainSchedulePage().validateStation("return");
                bukalapak.travelTrainSchedulePage().tapOnFilterIcon();
                bukalapak.travelTrainSchedulePage().tapOnFilterOption("Kelas");
                bukalapak.travelTrainSchedulePage().checkTrainClassFilter(trainClass, "return");
                bukalapak.travelTrainSchedulePage().tapOnTerapkanButton();
                bukalapak.travelTrainSchedulePage().validateScheduleClassDisplayed(trainClass, "return");
                bukalapak.travelTrainSchedulePage().setTrainName("return");
                bukalapak.travelTrainSchedulePage().setTrainPrice("return");
                bukalapak.travelTrainSchedulePage().setTrainTime("return");
                bukalapak.travelTrainSchedulePage().setStationCode("return");
                bukalapak.travelTrainSchedulePage().tapOnScheduleCard();
            }
        });

        When("user able to sort train schedule", () -> {
            bukalapak.travelTrainSchedulePage().tapSortingIcon();
            bukalapak.travelTrainSchedulePage().tapSortingType("Termahal");
            bukalapak.travelTrainSchedulePage().tapTerapkanButton();
        });

        Then("user sees a list of train schedule in order", () -> {
            bukalapak.travelTrainSchedulePage().validateTrainSorting("price");
        });
    }
}
