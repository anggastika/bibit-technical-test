package bukalapak.stepDefinitions.prio;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembeliPrioritasRiwayatCashbackStepDefinitions extends TestInstrument implements En {

    public PembeliPrioritasRiwayatCashbackStepDefinitions() {
        Given("user is in \"riwayat_cashback\" page", () -> {
            bukalapak.pembeliPrioritasRiwayatCashbackPage().userOnRiwayatCashbackPage();
        });

        Then("^user see list cashbacks in Riwayat Cashback page$", () -> {
            bukalapak.pembeliPrioritasRiwayatCashbackPage().checkRiwayatCashback();
        });
    }
}
