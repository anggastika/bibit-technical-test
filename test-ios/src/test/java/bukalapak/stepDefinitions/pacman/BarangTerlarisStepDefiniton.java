package bukalapak.stepDefinitions.pacman;


import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BarangTerlarisStepDefiniton extends TestInstrument implements En {

    public BarangTerlarisStepDefiniton() {
        Then("^user is in \"Terlaris di Bukalapak\" page$", () -> {
            bukalapak.barangTerlarisPage().verifyUserOnBarangTerlarisPage();
        });

        When("^user click on barang terlaris rank (\\d+)$", (Integer rank) -> {
            bukalapak.barangTerlarisPage().tapBarangTerlarisRank(rank);
        });

        Then("^user is shown category \"(.+)\" on barang terlaris page$", (String categoryName) -> {
            bukalapak.barangTerlarisPage().verifyBarangTerlarisCategory(categoryName);
        });

        Then("^user is shown (\\d+) products on Terlaris di Bukalapak page$", (Integer totalProduct) -> {
            bukalapak.barangTerlarisPage().verifyProducts(totalProduct);
        });
    }
}
