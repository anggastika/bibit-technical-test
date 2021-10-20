package bukalapak.stepDefinitions.stripe;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BelumDiprosesStepDefinitions extends TestInstrument implements En {
    public BelumDiprosesStepDefinitions() {
        And("user mark the checkbox of some transactions", () -> {
            bukalapak.belumDiprosesPage().getProductName();
            bukalapak.belumDiprosesPage().chooseCheckbox();
        });

        And("user tap on Proses Pesanan", () -> {
            bukalapak.belumDiprosesPage().tapOnProsesPesanan();
            bukalapak.belumDiprosesPage().tapOnConfirmProses();
        });

        And("user mark the checkbox of transaction", () -> {
            bukalapak.belumDiprosesPage().chooseCheckbox();
        });

        And("user tap on Tolak Pesanan button", () -> {
            bukalapak.belumDiprosesPage().tapOnTolakPesananButton();
        });

        And("user click Tolak Pesanan on single transaction", () -> {
            bukalapak.belumDiprosesPage().clickTolakPesananSingleTransaction();
        });

        And("user click back to go selling page", () -> {
            bukalapak.belumDiprosesPage().backToSellPage();
        });
    }
}
