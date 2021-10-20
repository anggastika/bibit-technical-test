package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TambahSaldoStepDefinitions extends TestInstrument implements En {

    public TambahSaldoStepDefinitions() {

        Given("user is in \"Tambah Saldo\" page", () -> {
            bukalapak.tambahSaldoPage().userOnTambahSaldoPage();
        });
    }
}
