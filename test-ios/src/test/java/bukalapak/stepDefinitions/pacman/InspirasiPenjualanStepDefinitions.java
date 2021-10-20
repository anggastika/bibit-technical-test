package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class InspirasiPenjualanStepDefinitions extends TestInstrument implements En {
    public InspirasiPenjualanStepDefinitions() {
        Given("user is in \"Inspirasi Penjualan\" page", () -> {
            bukalapak.inspirasiPenjualanPage().userOnInspirasiPenjualanPage();
        });

        When("^user see \"([^\"]*)\" menu in inspirasi penjualan page$", (String menuInspirasiPenjualan) -> {
            bukalapak.inspirasiPenjualanPage().verifyMenuInspirasiPenjualan(menuInspirasiPenjualan);
        });

        When("^user click \"([^\"]*)\" menu in inspirasi penjualan$", (String menuInspirasiPenjualan) -> {
            bukalapak.inspirasiPenjualanPage().clickMenuInspirasiPenjualan(menuInspirasiPenjualan);
        });

        When("^user click (\\d+) hari button for get date periode$", (Integer periode) -> {
            bukalapak.inspirasiPenjualanPage().clickDatePeriode(periode);
        });

        When("^user is shown period date for (\\d+) days$", (Integer periode) -> {
            bukalapak.inspirasiPenjualanPage().verifyDatePeriode(periode);
        });

        When("^user verify modal super seller offering display$", () -> {
            bukalapak.inspirasiPenjualanPage().verifySuperSellerModal();
        });

        And("^user close modal super seller offering", () -> {
            bukalapak.inspirasiPenjualanPage().clickCloseSuperSellerModal();
        });
    }
}
