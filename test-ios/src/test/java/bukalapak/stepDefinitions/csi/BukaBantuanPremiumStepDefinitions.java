package bukalapak.stepDefinitions.csi;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaBantuanPremiumStepDefinitions extends TestInstrument implements En {

    public BukaBantuanPremiumStepDefinitions() {

        Given("user is in \"bukabantuan_premium\" page", () -> {
            bukalapak.bukaBantuanPremiumPage().userOnBukaBantuanPremiumPage();
        });
    }
}
