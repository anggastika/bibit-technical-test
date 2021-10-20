package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembatalanTransaksiStepDefinitions extends TestInstrument implements En {

    public PembatalanTransaksiStepDefinitions() {

        Given("user is in \"pembatalan_transaksi\" page", () -> {
            bukalapak.pembatalanTransaksiPage().userOnPembatalanTransaksiPage();
        });

    }

}
