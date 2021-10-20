package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TerlarisDanKurangLarisStepDefinitions extends TestInstrument implements En {
    public TerlarisDanKurangLarisStepDefinitions() {
        Given("^user is in \"Terlaris Dan Kurang Laris\" page$", () -> {
            bukalapak.terlarisDanKurangLarisPage().userOnTerlarisDanKurangLarisPage();
        });

        Then("^user is shown (\\d+) products on Terlaris dan Kurang Laris page$", (Integer jumlahBarang) -> {
            bukalapak.terlarisDanKurangLarisPage().verifyBarangTerjualStokValueFullList(jumlahBarang);
        });
    }
}
