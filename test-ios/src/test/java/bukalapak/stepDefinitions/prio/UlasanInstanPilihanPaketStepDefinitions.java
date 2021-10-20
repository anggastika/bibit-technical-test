package bukalapak.stepDefinitions.prio;

import bukalapak.TestInstrument;
import bukalapak.stepDefinitions.BaseStepDefinitions;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UlasanInstanPilihanPaketStepDefinitions extends TestInstrument implements En {

    public UlasanInstanPilihanPaketStepDefinitions() {
        Given("user is in \"Ulasan Instan Pilihan Paket\" page", () -> {
            bukalapak.ulasanInstanPilihanPaketPage().userOnUlasanInstanPilihanPaketPage();
        });

        Then("^user select package (.*)$", (String selection) -> {
            bukalapak.ulasanInstanPilihanPaketPage().userSelectAutomaticReviewPackage(Integer.parseInt(selection));
        });
    }
}
