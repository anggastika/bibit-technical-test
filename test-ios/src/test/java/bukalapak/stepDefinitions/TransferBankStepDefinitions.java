package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TransferBankStepDefinitions extends TestInstrument implements En {

    public TransferBankStepDefinitions() {

        Given("user is in \"transfer_bank\" page", () -> {
            bukalapak.transferBankPage().userOnTransferBankPage();
        });
    }
}
