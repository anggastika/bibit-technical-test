package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class PantauBarangSainganFormStepDefinitions extends TestInstrument implements En {
    public PantauBarangSainganFormStepDefinitions() {
        Given("user is in \"Add Pantau Barang Saingan\" page", () -> {
            bukalapak.pantauBarangSainganFormPage().userOnAddPantauBarangSaingan();
        });

        When("^user types keyword \"([^\"]*)\" on Pantau Saingan form$", (String keyword) -> {
            bukalapak.pantauBarangSainganFormPage().inputKeywordPantauSaingan(keyword);
        });

        When("^user chooses to sort by \"([^\"]*)\" on Pantau Saingan form$", (String sorter) -> {
            bukalapak.pantauBarangSainganFormPage().chooseSortBy(sorter);
        });

        When("^user create monitoring with value:$", (DataTable table) -> {
            bukalapak.pantauBarangSainganFormPage().createPantauan(table);
        });
    }
}
