package bukalapak.stepDefinitions.vp;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by Ikhsan Danu on 25/11/20.
 */
public class HistoryTransactionDetailStepDefinitions extends TestInstrument implements En {
    public HistoryTransactionDetailStepDefinitions() {
        Then("^(?:the )?\"([^\"]*)\" invoice details will have displayed$", (String product) -> {
            bukalapak.historyTransactionDetailPage().validateTrxDetail(product);
        });

        And("^(?:the )?Paket Data invoice details data will have equalled by the transaction$", () -> {
            bukalapak.historyTransactionDetailPage().tapToShowInformasiTagihan();
            bukalapak.historyTransactionDetailPage().validateTotalTagihan();
        });
    }
}
