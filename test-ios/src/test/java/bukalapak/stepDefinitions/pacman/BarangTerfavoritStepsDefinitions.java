package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BarangTerfavoritStepsDefinitions extends TestInstrument implements En {
    public BarangTerfavoritStepsDefinitions() {
        Given("^user is in \"Barang Terfavorit\" page$", () -> {
            bukalapak.barangTerfavoritPage().userOnBarangTerfavoritePage();
        });

        Then("user click on product (\\d+) on Barang Terfavorit menu$", (Integer urutanBarang) -> {
            bukalapak.barangTerfavoritPage().clickBarangTerfavorite(urutanBarang);
        });

        Then("name on product 1 Barang Terfavorit includes \"([^\"]*)\"$", (String namaBarang) -> {
            bukalapak.barangTerfavoritPage().verifyNameBarangFavorite(namaBarang);
        });

        Then("^user is on filter Barang Terfavorit page$", () -> {
            bukalapak.barangTerfavoritPage().verifyPopUpFilter();
        });

        Then("^user click on filter \"([^\"]*)\" on filter Barang Terfavorit$", (String menuFilter) -> {
            bukalapak.barangTerfavoritPage().clickMenuFilter(menuFilter);
        });

        Then("user taps on checkbox \"([^\"]*)\" on filter Barang Terfavorit", (String urutanCheckbox) -> {
            bukalapak.barangTerfavoritPage().clickCheckBoxFilter(urutanCheckbox);
        });
    }
}
