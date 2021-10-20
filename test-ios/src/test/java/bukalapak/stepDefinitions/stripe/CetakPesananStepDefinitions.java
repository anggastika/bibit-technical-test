package bukalapak.stepDefinitions.stripe;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class CetakPesananStepDefinitions extends TestInstrument implements En {

    public CetakPesananStepDefinitions() {

        And("the transaction number matched with detail transaction", () -> {
            bukalapak.cetakPesananPenjualanPage().validationTransactionNumberOnDetailPenjualan();
        });

        And("the courier name matched with detail transaction", () -> {
            bukalapak.cetakPesananPenjualanPage().validationCourierOnDetailPenjualan();
        });

        And("user uncheckbox to doesn't show detail barang", () -> {
            bukalapak.cetakPesananPenjualanPage().clickCheckboxCetakPesanan();
        });

        And("the note matched with detail transaction", () -> {
            bukalapak.cetakPesananPenjualanPage().validationNoteOnDetailPenjualan();
        });

        And("the receiver name matched with detail transaction", () -> {
            bukalapak.cetakPesananPenjualanPage().validationReceiverNameOnDetailPenjualan();
        });

        Then("user click button tampilkan to show PDF", () -> {
            bukalapak.cetakPesananPenjualanPage().clickOnTampilkanButton();
        });

    }
}
