package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import bukalapak.data.CSIData;
import bukalapak.data.TransactionData;
import cucumber.api.java8.En;

public class InvoiceListStepDefinitions extends TestInstrument implements En {

    public InvoiceListStepDefinitions() {

        When("the invoice number can be search in transaction list", () -> {
            /*
            Please simplify the several methods (goToInvoiceListPage(), isOnInvoiceListPage(),search(String invoiceNo)
            and tapOnInvoiceNo()) in the same class, to be invoked into one line.
             */
            bukalapak.invoiceListPage().goToInvoiceListPage();
            bukalapak.invoiceListPage().isOnInvoiceListPage();
            bukalapak.invoiceListPage().search(TransactionData.getInvoiceNo());
            bukalapak.invoiceListPage().tapOnInvoice();
            /*
            Please simplify the several methods isOnInvoiceDetailPage() and isInvoiceNoMatch() in the same class,
            to be invoked into one line.
             */
            bukalapak.invoiceDetailPage().isOnInvoiceDetailPage();
            bukalapak.invoiceDetailPage().isInvoiceNoMatch(TransactionData.getInvoiceNo());
        });

        Then("^(?:the )?invoice state should be on \"([^\"]*)\" in transaction list$", (String state) -> {
            /*
            Please put these 4 methods into the a method. So it can be invoked in one line only from InvoiceListPage class.
             */
            bukalapak.invoiceListPage().goToInvoiceListPage();
            bukalapak.invoiceListPage().isOnInvoiceListPage();
            bukalapak.invoiceListPage().search(TransactionData.getInvoiceNo());
        });

        When("user search the \"([^\"]*)\" transaction", (String productName) -> {
            /*
            Please put these 4 methods into the a method. So it can be invoked in one line only from InvoiceListPage class.
             */
            bukalapak.invoiceListPage().goToInvoiceListPage();
            bukalapak.invoiceListPage().isOnInvoiceListPage();
            bukalapak.invoiceListPage().search(TransactionData.getInvoiceNo());
            bukalapak.invoiceListPage().tapOnInvoice();
        });

        Then("^Multifinance transaction list will be showed$", () -> {
            try {
                bukalapak.invoiceListPage().goToInvoiceListPage();
                bukalapak.invoiceListPage().isOnInvoiceListPage();
                bukalapak.invoiceListPage().search(TransactionData.getInvoiceNo());
            } catch (Exception e) {
                bukalapak.multifinancePage().verifyMultifinanceNumberHasBeenPaid();
            }
        });

        Then("the previous subscription transaction will have displayed", () -> {
            bukalapak.invoiceListPage().verifySubscriptionInvoice();
        });

        And("^user go to last tagihan transaction$", () -> {
            bukalapak.invoiceListPage().goToInvoiceListPage();
            bukalapak.invoiceListPage().isOnInvoiceListPage();
            bukalapak.invoiceListPage().search(CSIData.getNomorTagihan());
            bukalapak.invoiceListPage().tapOnInvoice();
        });

        And("^user go to Bukatabungan tagihan transaction$", () -> {
            bukalapak.invoiceListPage().tapOnlastInvoice();
            bukalapak.invoiceDetailPage().isOnInvoiceDetailPage();
        });
    }
}
