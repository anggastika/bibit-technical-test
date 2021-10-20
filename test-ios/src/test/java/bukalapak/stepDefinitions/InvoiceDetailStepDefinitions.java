package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import bukalapak.data.PrepaidData;
import bukalapak.data.TransactionData;
import cucumber.api.java8.En;

public class InvoiceDetailStepDefinitions extends TestInstrument implements En {

    public InvoiceDetailStepDefinitions() {

        When("invoice detail data and user input data should match", () -> {
            /*
              Please simplify these 4 lines of methods into one line of method in InvoiceDetailPage class. If the data were collected
              from PrepaidData and TransactionData, the method can directly use it from those two Data Class. Or create a method with
              3 params if it's used in another class and intended to use dynamic parameters.
             */
            bukalapak.invoiceDetailPage().isOnInvoiceDetailPage();
            bukalapak.invoiceDetailPage().isProductMatch(PrepaidData.getProductName());
            bukalapak.invoiceDetailPage().isPhoneNumberMatch(PrepaidData.getPhoneNumber());
            bukalapak.invoiceDetailPage().isInvoiceNoMatch(TransactionData.getInvoiceNo());
        });

        When("user see the invoice detail", () -> {
            PrepaidData.setPhoneNumber(bukalapak.iOSBasePage().getElementValue("invoice_detail_phone_number_text"));
            PrepaidData.setProductName(bukalapak.iOSBasePage().getElementValue("invoice_detail_product_name_text"));
        });

        When("user repurchase \"([^\"]*)\" from invoice detail page", (String productName) -> {
            /*
              Method isOnInvoiceDetailPage() can be invoked in tapOnRepurchaseButton() method to make it
              accessible in only one line of method.
             */
            bukalapak.invoiceDetailPage().isOnInvoiceDetailPage();
            bukalapak.invoiceDetailPage().tapOnRepurchaseButton();
        });

        Then("user see \"([^\"]*)\" should be shown as dropshipper", (String args0) -> {
            /*
              Method isOnPaymentConfirmationPage() can be invoked in openInvoiceDetail() method to make it
              accessible in only one line of method.
             */
            bukalapak.paymentConfirmationPage().isOnPaymentConfirmationPage();
            bukalapak.paymentConfirmationPage().openInvoiceDetail();
            bukalapak.invoiceDetailPage().verifyDropshipperOnInvoice(args0);
        });

        Then("user see \"([^\"]*)\" notes on purchase information", (String notes) -> {
            /*
              Method isOnPaymentConfirmationPage() can be invoked in openInvoiceDetail() method to make it
              accessible in only one line of method.
             */
            bukalapak.paymentConfirmationPage().isOnPaymentConfirmationPage();
            bukalapak.paymentConfirmationPage().openInvoiceDetail();
            bukalapak.invoiceDetailPage().verifyCatataPelapakOnInvoice(notes);
        });

        Then("user should see voucher deduction on invoice detail", () -> {
            bukalapak.invoiceDetailPage().isVoucherDiscountPresent();
        });
    }
}
