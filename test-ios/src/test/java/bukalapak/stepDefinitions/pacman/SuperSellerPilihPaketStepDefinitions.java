package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SuperSellerPilihPaketStepDefinitions extends TestInstrument implements En {
    public SuperSellerPilihPaketStepDefinitions() {
        And("user is in \"Super Seller Pilih Paket\" page", () -> {
            bukalapak.superSellerPilihPaketPage().verifyInfoPilihPaketSuperSeller();
        });

        When("^user choose Super Seller (Bronze|Silver|Gold) package$", (String packageName) -> {
            bukalapak.superSellerPilihPaketPage().chooseSuperSellerPackage(packageName);
        });

        Then("^user is shown pop up confirmation ganti paket$", () -> {
            bukalapak.superSellerPilihPaketPage().verifyPopUpGantiPaket();
        });
    }
}
