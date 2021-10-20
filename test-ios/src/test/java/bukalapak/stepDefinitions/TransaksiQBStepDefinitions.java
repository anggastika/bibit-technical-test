package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TransaksiQBStepDefinitions extends TestInstrument implements En {

    public TransaksiQBStepDefinitions() {

        Given("user is in \"Transaksi QB\" page", () -> {
            bukalapak.transaksiQBPage().userOnTransaksiQBPage();
        });
    }
}
