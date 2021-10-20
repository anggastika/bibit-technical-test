package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import org.openqa.selenium.TimeoutException;

public class PaymentConfirmationStepDefinitions extends TestInstrument implements En {

    public PaymentConfirmationStepDefinitions() {

        Given("user is in \"payment_confirmation\" page", () -> {
            bukalapak.paymentConfirmationPage().isOnPaymentConfirmationPage();
        });

        Given("user is in \"payment_confirmation_transfer_vp\" page", () -> {
            bukalapak.paymentConfirmationPage().userOnPaymentConfirmationTransferVPPage();
        });

        When("user navigates to invoice details from the confirmation check out old page", () -> {
            bukalapak.paymentConfirmationPage().isOnPaymentConfirmationOldPage();
            bukalapak.paymentConfirmationPage().verifyPage();
            bukalapak.paymentConfirmationPage().openOldInvoiceDetail();
        });

        When("user verify total payment in payment confirmation page is match with the one memorized before", () -> {
            bukalapak.paymentConfirmationPage().verifyTotalPayment();
        });
        When("user verify there is additional unique amount of total payment in payment confirmation page", () -> {
            bukalapak.paymentConfirmationPage().verifyAdditionalUniqueAmount();
        });

        When("user verify there is additional unique amount of total payment in transfer vp payment confirmation page", () -> {
            bukalapak.paymentConfirmationPage().verifyAdditionalUniqueAmountTransferVP();
        });

        When("user verify the copy function of bill and account number works properly in payment confirmation page", () -> {
            bukalapak.paymentConfirmationPage().verifyCopyFunctionOfBillAndAccountNumber();
        });

        When("user verify the copy function of bill and account number works properly in transfer vp payment confirmation page", () -> {
            bukalapak.paymentConfirmationPage().verifyCopyFunctionOfBillAndAccountNumberTransferVP();
        });

        When("user verify the copy function of virtual account number works properly in payment confirmation page", () -> {
            bukalapak.paymentConfirmationPage().verifyCopyFunctionOfVANumber();
        });
        Then("validate transaction buka pengiriman is created successfully", () -> {
            bukalapak.paymentConfirmationPage().isOnPaymentConfirmationPage();
        });

        And("user success top up DANA VP", () -> {
            bukalapak.paymentConfirmationPage().verifyTopupDANAVP();
        });

        And("user is redirected to payment invoice page", () -> {
            bukalapak.paymentConfirmationPage().verifyOnPaymentInvoicePage();
        });

        When("user opens ([^\"]*) history transaction with status \"([^\"]*)\"", (String product, String state) -> {
            try {
                bukalapak.paymentConfirmationPage().isOnPaymentConfirmationPage();
                bukalapak.paymentConfirmationPage().verifyPage();
                bukalapak.paymentConfirmationPage().openInvoiceDetail();
                bukalapak.invoiceDetailNonMarketplacePage().userOnInvoiceDetailPage();
                bukalapak.invoiceDetailNonMarketplacePage().verifyInvoiceStateIsMatch(state);
            } catch (TimeoutException e) {
                bukalapak.multifinancePage().verifyMultifinanceNumberHasBeenPaid();
            }
        });

        Then("user verify jumlah bayar plus unique amount in payment confirmation page is match with the one memorized before", () -> {
            bukalapak.paymentConfirmationPage().verifyJumlahBayar();
        });

        Then("user verify no pesanan in payment confirmation page", () -> {
            bukalapak.paymentConfirmationPage().verifyNoPesanan();
        });

        And("user redirected to payment confirmation", () -> {
            bukalapak.paymentConfirmationPage().verifyConfirmationPage();
        });

        And("user should see reco payment confirmation", () -> {
            bukalapak.paymentConfirmationPage().veryfyRecoConfirmation();
        });

        And("user user can swipe left and swipe right carousel reco", () -> {
            bukalapak.paymentConfirmationPage().swipeCarouselReco();
        });

        And("user click one of product carousel reco", () -> {
            bukalapak.paymentConfirmationPage().clickProductReco();
        });

        And("user tap Lihat detail pesanan", () -> {
            bukalapak.paymentConfirmationPage().clickOrderDetail();
        });

        And("user tap Batalkan Transaksi", () -> {
            bukalapak.paymentConfirmationPage().clickCancelTransaction();
        });

        And("user tap Confirmation Button", () -> {
            bukalapak.paymentConfirmationPage().clickBtnConfirmation();
        });

        And("^user is on payment confirmation \"([^\"]*)\"$", (String paymentMethod) -> {
            bukalapak.paymentConfirmationPage().validatePaymentConfirmation(paymentMethod);
        });

        And("user tap lihat detail pesanan on payment confirmation page", () -> {
            bukalapak.paymentConfirmationPage().tapLihatDetailPesananBtn();
        });
    }
}
