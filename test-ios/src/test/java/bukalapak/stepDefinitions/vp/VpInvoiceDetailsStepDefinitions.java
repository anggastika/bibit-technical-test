package bukalapak.stepDefinitions.vp;

import bukalapak.TestInstrument;
import bukalapak.data.TransactionData;
import cucumber.api.java8.En;

import static org.junit.Assert.fail;

public class VpInvoiceDetailsStepDefinitions extends TestInstrument implements En {

    public VpInvoiceDetailsStepDefinitions() {

        Then("^(?:the )?invoice state should be on \"([^\"]*)\" in (.*) invoice detail$", (String status, String product) -> {
            bukalapak.vpInvoiceDetailsPage().validateOnPage();
            bukalapak.vpInvoiceDetailsPage().validateInvoiceNumber(TransactionData.getInvoiceNo());
            bukalapak.vpInvoiceDetailsPage().validateTransactionStatus(status);
            switch (product.toLowerCase()) {

                // region PREPAID
                case "paket data":
                    bukalapak.paketDataInvoiceDetailsPage().validatePaymentAmount();
                    bukalapak.paketDataInvoiceDetailsPage().validateTransactionData();
                    break;
                case "listrik prabayar":
                    bukalapak.listrikPrabayarInvoiceDetailsPage().validatePaymentAmount();
                    bukalapak.listrikPrabayarInvoiceDetailsPage().validateTransactionData();
                    break;
                case "uang elektronik":
                    bukalapak.uangElektronikInvoiceDetailsPage().validatePaymentAmount();
                    bukalapak.uangElektronikInvoiceDetailsPage().validateTransactionData();
                    bukalapak.uangElektronikInvoiceDetailsPage().tapOnButtonCaraUpdateSaldo();
                    bukalapak.uangElektronikInvoiceDetailsPage().validateRedirectedToWebview();
                    bukalapak.uangElektronikInvoiceDetailsPage().tapBackButton();
                    break;
                case "pulsa prabayar":
                    bukalapak.pulsaPrabayarInvoiceDetailPage().validatePaymentAmount();
                    bukalapak.pulsaPrabayarInvoiceDetailPage().validateTransactionData();
                    break;
                // endregion

                // region POSTPAID
                case "listrik pascabayar":
                    bukalapak.listrikPascabayarInvoiceDetailPage().validateListrikPascabayarData();
                    break;
                case "pulsa pascabayar":
                    bukalapak.pulsaPascabayarInvoiceDetailPage().validateInvoicePulsaPascabayarData();
                    break;
                case "bpjs kesehatan":
                    bukalapak.bpjsInvoiceDetailPage().validateBPJSKesehatanData();
                    break;
                case "telkom":
                    bukalapak.telkomInvoiceDetailPage().validateTelkomData();
                    break;
                case "kartu kredit":
                    bukalapak.kartuKreditInvoiceDetailPage().validateInvoiceTransactionData();
                    break;
                case "pajak pbb":
                    bukalapak.invoiceDetailNonMarketplacePage().verifyPBBProductIsTrue();
                    break;
                case "addon indihome":
                    bukalapak.addonIndihomeInvoiceDetailPage().validateInvoiceAddonIndihomeData();
                    break;
                case "pdam":
                    bukalapak.pdamInvoiceDetailPage().validatePDAMData();
                    break;
                case "multifinance":
                    bukalapak.multifinanceInvoiceDetailPage().validateMultifinanceData();
                    break;
                case "tv kabel":
                    bukalapak.cableTvInvoiceDetailPage().validateCableTvData();
                    break;
                // endregion

                // region TIX

                case "tiket kereta":
                    // TODO
                    break;
                // endregion

                // region INSURANCE
                case "asuransi bepergian":
                    bukalapak.invoiceDetailNonMarketplacePage().verifyDetailBepergianInsuranceIsCorrect();
                    break;
                case "asuransi motor":
                    bukalapak.invoiceDetailNonMarketplacePage().verifyDetailMotorInsuranceIsCorrect();
                    break;
                case "premi asuransi":
                    bukalapak.invoiceDetailNonMarketplacePage().verifyInsurancePaymentCustomerNumberIsMatch();
                    break;
                case "asuransi tambahan":
                    bukalapak.invoiceDetailNonMarketplacePage().verifyDetailAsuransiTambahan();
                    break;
                // endregion

                default:
                    fail(String.format("%s step not implemented yet", product));
            }
        });

        Then("^the (discount|cashback) amount will (?:have applied|not have displayed) on the invoice details page$", (String voucherType) -> {
            bukalapak.vpInvoiceDetailsPage().validateDiscountVoucher(voucherType.equals("discount"));
        });
    }
}
