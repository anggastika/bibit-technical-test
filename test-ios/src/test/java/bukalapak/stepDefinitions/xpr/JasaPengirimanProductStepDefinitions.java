package bukalapak.stepDefinitions.xpr;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class JasaPengirimanProductStepDefinitions extends TestInstrument implements En {

    public JasaPengirimanProductStepDefinitions() {

        Given("user is on \"Jasa Pengiriman Product\" page", () -> {
            bukalapak.jasaPengirimanProductPage().userOnJasaPengirimanProductPage();
        });

        Then("^user validate \"([^\"]*)\" is (available|not available) on list courier product$", (String courier, String availability) -> {
            bukalapak.jasaPengirimanProductPage().validateAvailabilityCourierProduct(courier, availability);
        });

        When("^user tap checkbox courier \"([^\"]*)\" on list courier product$", (String courier) -> {
            bukalapak.jasaPengirimanProductPage().tapCheckboxCourier(courier);
        });

        When("user tap simpan button on list courier product", () -> {
            bukalapak.jasaPengirimanProductPage().tapSimpanButton();
        });

        Then("^user validate service \"([^\"]*)\" on list courier product$", (String courier) -> {
            bukalapak.jasaPengirimanProductPage().validateStatusCourier(courier);
        });

    }
}
