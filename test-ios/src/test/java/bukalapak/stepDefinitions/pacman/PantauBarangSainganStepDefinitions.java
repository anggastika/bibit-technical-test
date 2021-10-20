package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PantauBarangSainganStepDefinitions extends TestInstrument implements En {
    public PantauBarangSainganStepDefinitions() {
        Given("user is in \"Pantau Barang Saingan Dashboard\" page", () -> {
            bukalapak.pantauBarangSainganPage().userOnPantauBarangSainganDashboard();
        });

        When("^user click tambah pantauan button$", () -> {
            bukalapak.pantauBarangSainganPage().clickTambahPantauan();
        });

        Then("^user is shown empty state Pantau Saingan$", () -> {
            bukalapak.pantauBarangSainganPage().verifyEmptyStatePantauSaingan();
        });

        Then("^user is shown monitoring with keyword \"([^\"]*)\" sort by \"([^\"]*)\"$", (String keyword, String sorter) -> {
            bukalapak.pantauBarangSainganPage().verifyMonitoringInfo(keyword, sorter);
        });

        When("^user click monitoring with keyword \"([^\"]*)\"$", (String keyword) -> {
            bukalapak.pantauBarangSainganPage().tapOnMonitoringWithKeyword(keyword);
        });

        When("^user removes all monitorings$", () -> {
            bukalapak.pantauBarangSainganPage().removeAllMonitorings();
        });

        Then("^user is shown maximum monitoring state on Pantau Barang Saingan page$", () -> {
            bukalapak.pantauBarangSainganPage().verifyMaximumState();
        });
    }
}
