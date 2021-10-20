package bukalapak.stepDefinitions.btp;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class InvoiceMarketplaceDetailStepDefinitions extends TestInstrument implements En {

    public InvoiceMarketplaceDetailStepDefinitions() {

        // mtx
        And("^user redirected to invoice detail page$", () -> {
            bukalapak.invoiceDetailPage().isOnInvoiceDetailPage();
        });

        And("^user cancel transaction with reason \"([^\"]*)\"$", (String reason) -> {
            bukalapak.invoiceDetailPage().cancelTransaction(reason);
            bukalapak.invoiceDetailPage().isOnInvoiceDetailPage();
        });

        And("^user continue payment process on invoice detail page$", () -> {
            bukalapak.invoiceDetailPage().tapContinuePaymentProcessButton();
        });

        And("^user change payment method to \"([^\"]*)\" on invoice detail page( using bank \"([^\"]*)\")?$", (String newPaymentMethod, String bankOption) -> {
            bukalapak.invoiceDetailPage().changePaymentButton();
            if(newPaymentMethod.equals("Transfer ke Virtual Account") && bankOption != null) {
                bukalapak.checkoutMarketplacePage().changePaymentMethodProcessOnInvoiceDetail(newPaymentMethod, bankOption);
            } else {
                bukalapak.checkoutMarketplacePage().changePaymentMethodProcessOnInvoiceDetail(newPaymentMethod);
            }
        });

        Then("^user verify status is \"([^\"]*)\" on invoice detail page$", (String status) -> {
            bukalapak.invoiceDetailPage().verifyStatusOnInvoice(status);
        });

        Then("^user verify payment method is \"([^\"]*)\" on invoice detail page$", (String method) -> {
            bukalapak.invoiceDetailPage().verifyPaymentMethodOnInvoice(method);
        });

        Then("^user verify payment code transfer on invoice detail page$", () -> {
            bukalapak.invoiceDetailPage().verifyPaymentCodeTransferOnInvoice();
        });

        Then("^user verify deduction mixpayment (dana|saldo) on invoice detail page", (String type) -> {
            bukalapak.invoiceDetailPage().verifyMixPaymentDeductionOnInvoice(type);
        });

        Then("^user verify none deduction mixpayment (dana|saldo) on invoice detail page", (String type) -> {
            bukalapak.invoiceDetailPage().verifyMixPaymentDeductionNotExist(type);
        });

        Then("^user verify content 3rd party webview page with payment \"([^\"]*)\"$", (String payment) -> {
            bukalapak.invoiceDetailPage().verifyWebview3rdPayment(payment);
        });
        //end mtx

        Then("user verify rounded amount on invoice detail page", () -> {
            bukalapak.invoiceDetailPage().verifypembulatanOnInvoice();
        });

        Then("user verify no rounded amount on invoice detail page", () -> {
            bukalapak.invoiceDetailPage().verifyNoRoundedOnInvoice();
        });

        Then("user should see status is pending on invoice detail page", () -> {
            bukalapak.invoiceDetailPage().verifyPendingStatusOnInvoice();
        });

        And("^user verify catatan pelapak for contains \"([^\"]*)\" on Invoice Page is correct$",
                (String expectedSellerNote) -> {
            bukalapak.invoiceDetailPage().verifyCatatanPelapakFor(expectedSellerNote);
        });

        When("^user confirms payment with payment time outside \"([^\"]*)\"$", (String time) -> {
            bukalapak.invoiceDetailPage().confirmPayment(time);
        });

        Then("^display \"([^\"]*)\" on orage message$", (String message) -> {
            bukalapak.invoiceDetailPage().validateOrangeMessage(message);
        });

        When("^upload receipt photo from galery$", () -> {
            bukalapak.invoiceDetailPage().uploadReceiptImage();
        });

        Then("^photo appears in Detail Tagihan page$", () -> {
            bukalapak.invoiceDetailPage().validateImageReceipt();
        });

        Then("^display message after upload receipt \"([^\"]*)\"$", (String message) -> {
            bukalapak.invoiceDetailPage().validateModalMessage(message);
            bukalapak.iOSBasePage().tapElement("invoice_detail_modal_oke_button");
        });

        When("^user change receipt photo$", () -> {
            bukalapak.invoiceDetailPage().changeReceiptImage();
        });

        Then("^new photo appears in Detail Tagihan page$", () -> {
            bukalapak.invoiceDetailPage().validateChangedImageReceipt();
        });

        And("^user verify the validation message is \"([^\"]*)\" after update the photo$", (String message) -> {
            bukalapak.invoiceDetailPage().validateMessageUnderPhoto(message);
        });

        // mtq
        When("^user is in invoice detail page with deeplink \"([^\"]*)\"$", (String deeplink) -> {
            bukalapak.invoiceDetailPage().userOnInvoiceDetailPageWithDeeplink(deeplink);
        });

        And("user tap on Lihat Detail", () -> {
            bukalapak.invoiceDetailPage().tapLihatDetailButton();
        });

        Then("user should see status is refunded on invoice detail page", () -> {
            bukalapak.invoiceDetailPage().validateRefundedStatus();
        });

        And("^user validate rejection reason from \"al(?:chemy|gebra)\" contains \"([^\"]*)\"$", (String rejectReason) -> {
            bukalapak.invoiceDetailPage().validateOldRejectReason(rejectReason);
        });

        //voucher section
        Then("user should see all vouchers benefit on invoices", (DataTable table) -> {
            bukalapak.invoiceDetailPage().validateVouchersBenefit(table);
        });

        And("user tap alamat pengiriman section on transaction detail page", () -> {
            bukalapak.invoiceDetailPage().tapAlamatPengirimanSection();
        });

        And("user will see the phone number will be masked with 4 digits in the middle", () -> {
            bukalapak.invoiceDetailPage().validatePhoneNumberMasked();
        });
        //end

        //financing
        And("User verify CTA Bayar Lagi and Cek limit is not displayed", () -> {
            bukalapak.invoiceDetailPage().validateCTAPayLater();
        });
        //end

        /* This step is used by SUBSIDIES */
        And("voucher rule change payment warning modal will( not | )displayed", (String state) -> {
            if (state.trim().isEmpty()) {
                bukalapak.invoiceDetailPage().validateDisqualifyModal();
            } else {
                bukalapak.invoiceDetailPage().validateDisqualifyModalIsNotExist();
            }
        });

        And("User (setuju|batal) ganti pembayaran", (String source) -> {
            if (source.equals("setuju")) {
                bukalapak.invoiceDetailPage().clickSetujuGanti();
            } else {
                bukalapak.invoiceDetailPage().clickBatalGanti();
            }
        });
        //end
    }
}
