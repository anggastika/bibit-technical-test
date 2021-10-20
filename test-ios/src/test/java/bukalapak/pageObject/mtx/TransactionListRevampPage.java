package bukalapak.pageObject.mtx;

import bukalapak.data.CheckoutData;
import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.CartDialogPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class TransactionListRevampPage extends BasePage {
    public TransactionListRevampPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToListTransaction() {
        HelperData.goToHomePage();
        waitForVisibilityOf("home_transaksi_tab", 10);
        tapElement("home_transaksi_tab");
        HelperData.setLastActionPage(new TransactionListRevampPage(iosDriver));
    }

    public void validateOnTransactionList() {
        verifyElementExist("trx_list_title_txt", 5, "transaction list page failed loading");
        verifyElementExist("trx_list_tagihan_tab");
        verifyElementExist("trx_list_pembelian_tab");
        verifyElementExist("trx_list_penjualan_tab");
        HelperData.setLastActionPage(new TransactionListRevampPage(iosDriver));
    }

    public void searchInvoiceNumber(String noInvoice) {
        String invoiceNumber = noInvoice;

        waitForVisibilityOf("trx_list_trx_item", 10);
        if (noInvoice == null) {
            invoiceNumber = CheckoutData.getInvoiceId();
        }
        tapElement("trx_list_tagihan_search_field");
        tapElement("trx_list_clear_search_button");
        delay(1000); //waiting after user clear search button
        typeValue("trx_list_tagihan_search_field", invoiceNumber + "\n");
    }

    public void verifyLabelState(String state) {
        String locator = constructLocator("trx_list_state_capsule", state);
        switch (state) {
            case "Menunggu pembayaran":
                verifyElementExist(locator, 10, "Label Menunggu pembayaran not shown");
                break;
            case "Dibayarkan":
                verifyElementExist(locator, 10, "Label Dibayarkan not shown");
                break;
            case "Tagihan Dibatalkan":
                verifyElementExist(locator, 10, "Label Tagihan Dibatalkan not shown");
                break;
            case "Tagihan Kedaluwarsa":
                verifyElementExist(locator, 10, "Label Tagihan Kedaluwarsa not shown");
                break;
            default:
                Assert.fail("Invalid state");
                break;
        }
        HelperData.setLastActionPage(new TransactionListRevampPage(iosDriver));
    }

    private void validateSectionShown(String section) {
        switch (section) {
            case "Product Name":
                verifyElementExist(constructLocator("trx_list_product_name_txt", section), 5, "Product name not shown");
                break;
            case "Product Price":
                verifyElementExist(constructLocator("trx_list_product_price_txt", section), 5, "Product price not shown");
                break;
            case "Multiple Product":
                verifyElementExist(constructLocator("trx_list_multiple_product_txt", section), 5, "Multiple product not shown");
                break;
            case "Product Image":
                verifyElementExist(constructLocator("trx_list_product_image", section), 5, "Product image not shown");
                break;
            case "Cara Bayar Button":
                verifyElementExist(constructLocator("trx_list_instruction_payment_btn", section), 5, "Cara bayar button not shown");
                break;
            default:
                Assert.fail("Invalid state");
                break;
        }
    }

    private void validateSectionNotShown(String section) {
        switch (section) {
            case "Product Name":
                verifyElementNotExist(constructLocator("trx_list_product_name_txt", section));
                break;
            case "Product Price":
                verifyElementNotExist(constructLocator("trx_list_product_price_txt", section));
                break;
            case "Multiple Product":
                verifyElementNotExist(constructLocator("trx_list_multiple_product_txt", section));
                break;
            case "Product Image":
                verifyElementNotExist(constructLocator("trx_list_product_image", section));
                break;
            case "Cara Bayar Button":
                verifyElementNotExist(constructLocator("trx_list_instruction_payment_btn", section));
                break;
            default:
                Assert.fail("Invalid state");
                break;
        }
    }

    public void validateSection(String condition, String section) {
        if (condition.equalsIgnoreCase("can")) {
            validateSectionShown(section);
        } else {
            validateSectionNotShown(section);
        }
        HelperData.setLastActionPage(new TransactionListRevampPage(iosDriver));
    }

    public void tapCaraBayarBtn() {
        tapElement("trx_list_instruction_payment_btn");
    }

    public void validateInvoiceId() {
        verifyElementExist("trx_list_instruction_payment_no_invoice_txt");
        assertEquals(CheckoutData.getInvoiceId(), getElementValue("trx_list_instruction_payment_no_invoice_txt"), "invoice id not same");
    }

    public void validateInstructionPaymentPage(String paymentMethod) {
        if (paymentMethod.equalsIgnoreCase("Transfer Bank")) {
            waitForVisibilityOf("payment_confirmation_instruksi_pembayaran", 30);
            validateExist("payment_confirmation_payment_due_date",5);
            validateExist("payment_confirmation_count_down_title");
            validateExist("payment_confirmation_total_payment");
            validateExist("payment_confirmation_payment_method");
            validateExist("payment_confirmation_list_bank");
            swipeUpToElement("payment_confirmation_invoice_number");
            validateExist("payment_confirmation_invoice_number");
            assertEquals(CheckoutData.getInvoiceId(), getElementValue("payment_confirmation_invoice_number"));
        } else {
            String paymentMethodLocator = constructLocator("trx_list_instruction_payment", paymentMethod);
            verifyElementExist("trx_list_instruction_payment_title_txt", 10, "instruction payment page failed loading");
            verifyElementExist("trx_list_instruction_payment_salin_nomor_btn");
            validateInvoiceId();
            verifyElementExist(paymentMethodLocator, 10, "instruction payment not shown");
        }
        HelperData.setLastActionPage(new TransactionListRevampPage(iosDriver));
    }

    public void validateChangePaymentButton(String condition) {
        if (condition.equalsIgnoreCase("can")) {
            swipeUpToElement("trx_list_instruction_payment_change_payment_callout");
            verifyElementExist("trx_list_instruction_payment_change_payment_callout");
            verifyElementExist("trx_list_instruction_payment_change_payment_button");
        } else {
            verifyElementNotExist("trx_list_instruction_payment_change_payment_callout");
            verifyElementNotExist("trx_list_instruction_payment_change_payment_callout");
        }
        HelperData.setLastActionPage(new TransactionListRevampPage(iosDriver));
    }

    public void changePaymentMethod(String paymentMethod) {
        String paymentMethodChoosen = constructLocator("change_payment_list_radio_btn", paymentMethod);
        tapElement("trx_list_instruction_payment_change_payment_button");
        swipeUpToElement(paymentMethodChoosen);
        tapElement(paymentMethodChoosen);
        swipeUpToElement("change_payment_total_belanja_txt");
        CheckoutData.setTotalPaymentCheckout(getIntegerFromValueElement("change_payment_total_belanja_txt"));
        tapElement("change_payment_bayar_btn");
        HelperData.setLastActionPage(new TransactionListRevampPage(iosDriver));
    }

    public void backToTransactionList(String paymentMethod) {
        if (paymentMethod == null) {
            tapElement("base_back_button", 5);
        }
        else {
            tapElement("alchemy_back_button", 5);
        }
        HelperData.setLastActionPage(new TransactionListRevampPage(iosDriver));
    }
}
