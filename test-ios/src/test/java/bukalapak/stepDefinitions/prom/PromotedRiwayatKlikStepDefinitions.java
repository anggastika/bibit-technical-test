package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedRiwayatKlikStepDefinitions extends TestInstrument implements En {

    public PromotedRiwayatKlikStepDefinitions() {
        Then("user is in \"Promoted Push Riwayat Klik\" page", () -> {
            bukalapak.promotedRiwayatKlikPage().verifyPromotedRiwayatKlikPageDisplayed();
        });

        When("user select date as \"([^\"]*)\" to \"([^\"]*)\"", (String startDate, String endDate) -> {
            bukalapak.promBasePage().hideLeakInfo();
            bukalapak.promBasePage().selectDatePicker(startDate, endDate);
        });

        When("user search product as \"([^\"]*)\" in Riwayat Klik page", (String productName) -> {
            bukalapak.promBasePage().searchProduct(productName);
        });

        When("user verify \"([^\"]*)\" was successfully charged \"([^\"]*)\" on \"([^\"]*)\"$", (String productName, Integer bid, String date) -> {
            bukalapak.promotedRiwayatKlikPage().verifyBidPromotedHistory(productName, bid, date);
        });
    }
}
