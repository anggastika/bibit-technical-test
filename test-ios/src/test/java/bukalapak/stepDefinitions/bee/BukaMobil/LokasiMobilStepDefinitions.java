package bukalapak.stepDefinitions.bee.BukaMobil;

import bukalapak.TestInstrument;
import bukalapak.utils.ChoiceSelector;
import cucumber.api.java8.En;

public class LokasiMobilStepDefinitions extends TestInstrument implements En {

    public LokasiMobilStepDefinitions() {

        And("user click \"([^\"]*)\" link", (String arg0) -> {
            ChoiceSelector.of(arg0)
                    .when("Ubah Lokasi", () -> bukalapak.bukaMobilPage().clickUbahLokasi());
        });

        And("user navigate \"([^\"]*)\" to", (String arg0) -> {
            ChoiceSelector.of(arg0)
                    .when("Ubah Lokasi", () -> bukalapak.bukaMobilPage().onUbahLokasiPage());
        });
        And("^user select \"([^\"]*)\" from menu$", (String arg0) -> {
            ChoiceSelector.of(arg0)
                    .when("Provinsi", () -> bukalapak.bukaMobilPage().selectProvinsi())
                    .when("City", () -> bukalapak.bukaMobilPage().selectCity());
        });
        Then("^user verify \"([^\"]*)\" was selected$", (String arg0) -> {
            ChoiceSelector.of(arg0)
                     .when("City", () -> bukalapak.bukaMobilPage().verifyCity());
        });
    }
}