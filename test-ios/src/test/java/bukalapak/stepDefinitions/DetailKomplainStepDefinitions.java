package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class DetailKomplainStepDefinitions extends TestInstrument implements En {

    public DetailKomplainStepDefinitions() {

        Given("user is in \"Detail Komplain\" page", () -> {
            bukalapak.detailKomplainPage().userOnDetailKomplainPage();
        });

        Then("user is in \"Detail Diskusi Komplain\" page", () -> {
           bukalapak.detailKomplainPage().userOnDetailKomplainBukaBantuan();
        });

        When("user tap on \"Lihat Detail Transaksi\" card", () -> {
            bukalapak.detailKomplainPage().tapOnTransactionCard();
        });

        And("user is in detail komplain return page", () -> {
            bukalapak.detailKomplainPage().inDetailKomplainRetur();
        });
    }
}
