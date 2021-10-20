package bukalapak.stepDefinitions.xpr;

import bukalapak.TestInstrument;
import bukalapak.data.XPRData;
import cucumber.api.java8.En;

public class BukaGlobalStepDefinitions extends TestInstrument implements En {

    public BukaGlobalStepDefinitions() {

        Then("user is on BukaGlobal page", () -> {
            bukalapak.bukaGlobalPage().userOnBukaGlobalPage();
        });

        When("user kirim barang ke \"([^\"]*)\"", (String country) -> {
            bukalapak.bukaGlobalPage().tapKirimKeNegara(country);
        });

        When("user tap on Lihat Semua of a category \"([^\"]*)\"", (String category) -> {
            XPRData.setCategoryProduct(category);
            bukalapak.bukaGlobalPage().tapLihatSemua();
        });

        Then("validate category product \"([^\"]*)\" is appear in product listing", (String categoryName) -> {
            bukalapak.bukaGlobalPage().validateCategoryBukaGlobal(categoryName);
        });
    }
}
