package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class RincianPromosiStepDefinitions extends TestInstrument implements En {
    public RincianPromosiStepDefinitions() {
        // for rincian promosi product detail
        Given("^user is in \"Rincian Promosi\" page$", () -> {
            bukalapak.rincianPromosiPage().userOnRincianPromosiPage();
        });

        Then("^user see rincian promosi for product \"([^\"]*)\"$", (String nameProduct) -> {
            bukalapak.rincianPromosiPage().verifyProductDetailDisplay(nameProduct);
        });

        // for rincian promosi product list
        Then("^user is shown products on Rincian Promosi list$", () -> {
            bukalapak.rincianPromosiPage().verifyProductListDisplay();
        });

        When("^user taps on (?:filter etalase|product name) \"([^\"]*)\" on Rincian Promosi$", (String productOrEtalaseName) -> {
            bukalapak.rincianPromosiPage().clickProductOrLabelName(productOrEtalaseName);
        });

        Then("^user is shown product with name \"([^\"]*)\" on Rincian Promosi$", (String productName) -> {
            bukalapak.rincianPromosiPage().verifyProductDisplay(productName);
        });

        When("^user click filter button in Rincian Promosi page$", () -> {
            bukalapak.rincianPromosiPage().clickFilterButton();
        });

        When("^user click filter option \"([^\"]*)\" button", (String filterOption) -> {
            bukalapak.rincianPromosiPage().clickFilterOption(filterOption);
        });

        When("^user click (Terapkan|Reset Semua) button on filter screen$", (String filterOption) -> {
            bukalapak.rincianPromosiPage().clickTerapkanOrResetFilter(filterOption);
        });
    }
}
