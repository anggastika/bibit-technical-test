package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PilihKategoriStepDefinitions extends TestInstrument implements En {
    public PilihKategoriStepDefinitions() {
        Given("^user is in \"Pilih Kategori\" page$", () -> {
            bukalapak.pilihKategoriPage().verifyPilihKategoriPage();
        });

        Then("^user taps on category \"([^\"]*)\" on Pilih Kategori page$", (String menuKategori) -> {
            bukalapak.pilihKategoriPage().clickCategoryMenu(menuKategori);
        });

        Then("^user taps on category \"([^\"]*)\" by search on Pilih Kategori page$", (String menuKategori) -> {
            bukalapak.pilihKategoriPage().clickCategoryMenuBySearch(menuKategori);
        });

        Then("^user is shown results containing \"([^\"]*)\" on Pilih Kategori page$", (String categoryName) -> {
            bukalapak.pilihKategoriPage().verifySearchResults(categoryName);
        });
    }
}
