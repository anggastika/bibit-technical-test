package bukalapak.stepDefinitions.vp;

import bukalapak.TestInstrument;
import bukalapak.data.TransactionData;
import cucumber.api.java8.En;

import static org.junit.Assert.fail;

/**
 * Created by Ferawati Hartanti on 15/07/20.
 */
public class VpTransactionHistoryStepDefinitions extends TestInstrument implements En {
    public VpTransactionHistoryStepDefinitions() {

        When("the (.*) transaction history will have listed with status \"([^\"]*)\"", (String product, String status) -> {
            switch (product.toLowerCase()) {
                case "paket data":
                    bukalapak.iOSBasePage().openDeepLink("/bl/paket-data");
                    bukalapak.paketDataLandingPage().validateOnPage();
                    break;
                case "listrik prabayar":
                    bukalapak.iOSBasePage().openDeepLink("/listrik-pln/token-listrik");
                    bukalapak.listrikPrabayarLandingPage().validateOnPage();
                    break;
                case "uang elektronik":
                    bukalapak.iOSBasePage().openDeepLink("/bl/uang-elektronik");
                    bukalapak.uangElektronikLandingPage().validateOnPage();
                    break;
                case "pulsa prabayar":
                    bukalapak.iOSBasePage().openDeepLink("/bl/pulsa");
                    bukalapak.pulsaPrabayarLandingPage().validateOnPage();
                    break;
                default:
                    fail(String.format("%s step not implemented yet", product));
            }
            bukalapak.vpTransactionHistoryPage().tapOnIconTransactionHistory();
            bukalapak.vpTransactionHistoryPage().validateOnPage();
            bukalapak.vpTransactionHistoryPage().tapOnFilterStatus(status);
            bukalapak.vpTransactionHistoryPage().validateItemTransactionHistory(true);
            bukalapak.vpTransactionHistoryPage().validateStatus(status);
            bukalapak.vpTransactionHistoryPage().tapOnItemTransactionHistory();
            bukalapak.vpInvoiceDetailsPage().validateOnPage();
            bukalapak.vpInvoiceDetailsPage().validateInvoiceNumber(TransactionData.getInvoiceNo());
        });

        When("user navigates to the (?:.*) transaction history page", () -> {
            bukalapak.vpTransactionHistoryPage().tapOnIconTransactionHistory();
        });

        Then("the transaction history page will have displayed", () -> {
            bukalapak.vpTransactionHistoryPage().validateOnPage();
        });

        Then("the transaction history data should have equals with (.*) invoice details data", (String product) -> {
            bukalapak.vpTransactionHistoryPage().validateItemTransactionHistory(true);
            bukalapak.vpTransactionHistoryPage().setLatestTransactionStatus();
            bukalapak.vpTransactionHistoryPage().tapOnFilterStatus(TransactionData.getPaymentStatus());
            bukalapak.vpTransactionHistoryPage().validateStatus(TransactionData.getPaymentStatus());
            bukalapak.vpTransactionHistoryPage().validateItemTransactionHistory(true);
            switch (product.toLowerCase()) {
                case "paket data":
                    bukalapak.paketDataTransactionHistoryPage().setTransactionData();
                    bukalapak.paketDataTransactionHistoryPage().tapOnItemTransactionHistory();
                    bukalapak.paketDataInvoiceDetailsPage().validateOnPage();
                    bukalapak.paketDataInvoiceDetailsPage().validateTransactionDataFromHistory();
                    break;
                case "listrik prabayar":
                    bukalapak.listrikPrabayarTransactionHistoryPage().setTransactionData();
                    bukalapak.listrikPrabayarTransactionHistoryPage().tapOnItemTransactionHistory();
                    bukalapak.listrikPrabayarInvoiceDetailsPage().validateOnPage();
                    bukalapak.listrikPrabayarInvoiceDetailsPage().validateTransactionDataFromHistory();
                    break;
                case "uang elektronik":
                    bukalapak.uangElektronikTransactionHistoryPage().setTransactionData();
                    bukalapak.uangElektronikTransactionHistoryPage().tapOnItemTransactionHistory();
                    bukalapak.uangElektronikInvoiceDetailsPage().validateOnPage();
                    bukalapak.uangElektronikInvoiceDetailsPage().validateTransactionDataFromHistory();
                    break;
                default:
                    fail(String.format("%s step not implemented yet", product));
            }
        });
    }
}
