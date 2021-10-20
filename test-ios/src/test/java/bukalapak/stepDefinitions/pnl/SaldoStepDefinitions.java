package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SaldoStepDefinitions extends TestInstrument implements En {

    public SaldoStepDefinitions() {

        Given("user is in \"saldo\" page", () -> {
            bukalapak.saldoPage().userOnSaldoPage();
        });

    }

}
