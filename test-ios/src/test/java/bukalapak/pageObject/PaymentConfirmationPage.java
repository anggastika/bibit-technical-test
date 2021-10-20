package bukalapak.pageObject;

import bukalapak.data.CheckoutData;
import bukalapak.data.MtxData;
import bukalapak.data.TransactionData;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class PaymentConfirmationPage extends BasePage {

    public PaymentConfirmationPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void isOnPaymentConfirmationPage() {
        switch (TransactionData.getPaymentMethod()) {
            case "transfer": case "Transfer Bank Manual" :
                waitForVisibilityOf("payment_confirmation_transfer_batas_waktu_text", 30);
                assertTrue(isElementVisible("payment_confirmation_total_amount"));
                break;
            case "virtual account bca":
            case "VA-BCA":
                waitForVisibilityOf("payment_confirmation_new_total_amount", 30);
                break;
            case "saldo":
                waitForVisibilityOf("payment_confirmation_pembayaran_saldo_berhasil_text", 30);
                break;
            case "cod":
                waitForVisibilityOf("payment_confirmation_cod_text", 50);
                break;
            case "Transfer Bank Otomatis" :
                waitForVisibilityOf("payment_confirmation_batas_waktu_text", 30);
                break;
            default:
                waitForVisibilityOf("payment_confirmation_new_total_amount", 60);
                assertTrue(isElementVisible("payment_confirmation_new_total_amount"));
                assertTrue(isElementVisible("payment_confirmation_new_invoice_id_text"));
                break;
        }

        HelperData.setLastActionPage(new PaymentConfirmationPage(iosDriver));
    }

    public void isOnPaymentConfirmationOldPage() {
        switch (TransactionData.getPaymentMethod()) {
            case "indomaret":
                waitForVisibilityOf("payment_confirmation_total_amount", 50);
                assertTrue(isElementVisible("payment_confirmation_total_amount"));
                assertTrue(isElementVisible("payment_confirmation_invoice_id_text"));
                assertTrue(isElementVisible("payment_confirmation_open_detail_transaction_button"));
                break;
            default:
                waitForVisibilityOf("payment_confirmation_total_amount", 50);
                assertTrue(isElementVisible("payment_confirmation_total_amount"));
                assertTrue(isElementVisible("payment_confirmation_invoice_id_text"));
                assertTrue(isElementVisible("payment_confirmation_open_detail_transaction_button"));
                break;
        }
        HelperData.setLastActionPage(new PaymentConfirmationPage(iosDriver));
    }

    public void userOnPaymentConfirmationTransferVPPage() {
        waitForVisibilityOf("payment_confirmation_transfer_vp_total_amount", 30);
        HelperData.setLastActionPage(new PaymentConfirmationPage(iosDriver));
    }

    public void verifyPage() {
        verifyTotalPayment();
        try {
            TransactionData.setInvoiceNo(getElementValue("payment_confirmation_new_invoice_id_text").substring(1));
        } catch (Exception e) {
            LogUtil.info("Petunjuk pembayaran page doesn't have No Pesanan section");
        }
    }

    public void openInvoiceDetail() {
        tapElement("payment_confirmation_new_open_invoice_button");
    }

    public void openOldInvoiceDetail() {
        tapElement("payment_confirmation_open_detail_transaction_button");
    }

    public void verifyCopyFunctionOfVANumber() {
        copyAndVerify("payment_confirmation_salin_va_text", "payment_confirmation_va_text");
    }

    public void verifyTotalPayment() {
        assertTrue(TransactionData.getTotalPrice().equals(getTextFromElement("payment_confirmation_new_total_amount")));
    }

    public void verifyCopyFunctionOfBillAndAccountNumber() {
        switch (MtxData.getPaymentMethod()) {
            case "transfer bank manual":
            case "transfer":
                copyAndVerify("payment_confirmation_salin_tagihan_text", "payment_confirmation_transfer_total_amount");
                swipeUpToElement("payment_confirmation_no_rekening_bca_text");
                copyAndVerify("payment_confirmation_salin_rekening_bca_text", "payment_confirmation_no_rekening_bca_text");
                swipeUpToElement("payment_confirmation_no_rekening_mandiri_text");
                copyAndVerify("payment_confirmation_salin_rekening_mandiri_text", "payment_confirmation_no_rekening_mandiri_text");
                swipeUpToElement("payment_confirmation_new_transfer_open_invoice_button");
                break;
            default:
                break;
        }
    }

    public void verifyCopyFunctionOfBillAndAccountNumberTransferVP() {
        copyAndVerify("payment_confirmation_salin_tagihan_text", "payment_confirmation_transfer_vp_total_amount");
        copyAndVerify("payment_confirmation_salin_rekening_bca_text", "payment_confirmation_no_rekening_bca_text");
        copyAndVerify("payment_confirmation_salin_rekening_mandiri_text", "payment_confirmation_no_rekening_mandiri_text");
    }

    private void copyAndVerify(String copyButtonLocator, String elementToBeCopied) {
        if (isElementVisible(copyButtonLocator)) {
            tapElement(copyButtonLocator);
            verifyCopiedText(getTextFromElement(elementToBeCopied).replaceAll("[^0-9]", ""));
        }
    }

    public void verifyAdditionalUniqueAmount() {
        Double originalBillAmount = Double.parseDouble(TransactionData.getTotalPrice().replaceAll("[^0-9]", ""));
        Double billWithAdditionalUniqueAmount = Double.parseDouble(getElementValue("payment_confirmation_total_amount").replaceAll("[^0-9]", ""));
        assertTrue(originalBillAmount < billWithAdditionalUniqueAmount);
    }

    public void verifyAdditionalUniqueAmountTransferVP() {
        Double originalBillAmount = Double.parseDouble(TransactionData.getTotalPrice().replaceAll("[^0-9]", ""));
        Double billWithAdditionalUniqueAmount = Double.parseDouble(getElementValue("payment_confirmation_transfer_vp_total_amount").replaceAll("[^0-9]", ""));
        assertTrue(originalBillAmount < billWithAdditionalUniqueAmount);
    }

    public void verifyTopupDANAVP() {
        waitForVisibilityOf("payment_confirmation_open_invoice_button", 30);
        assertTrue(isElementVisible("payment_confirmation_open_invoice_button"));
        HelperData.setLastActionPage(new PaymentConfirmationPage(iosDriver));
    }

    public void verifyOnPaymentInvoicePage() {
        try{
            waitForVisibilityOf("payment_detail_button",10);
        } catch (Exception e) {
            if (isElementVisible("checkout_non_marketplace_popup_va_text", 5)) {
                tapElement("checkout_non_marketplace_lanjutkan_pembayaran_button");
            }
            waitForVisibilityOf("payment_detail_button",10);
        }
    }

    public void verifyJumlahBayar() {
        Double originalBillAmount = Double.parseDouble(TransactionData.getTotalPrice().replaceAll("[^0-9]", ""));
        Double billWithAdditionalUniqueAmount = Double.parseDouble(getElementValue("payment_confirmation_transfer_total_amount").replaceAll("[^0-9]", ""));
        assertTrue(originalBillAmount < billWithAdditionalUniqueAmount);
        TransactionData.setJumlahBayarPlusUniqueAmount(getElementValue("payment_confirmation_transfer_total_amount").replaceAll("[^0-9]", ""));
    }

    public void verifyNoPesanan() {
        swipeUpToElement("payment_confirmation_invoice_number_text");
        assertTrue(isElementVisible("payment_confirmation_invoice_number_text"));
        TransactionData.setInvoiceNo(getElementValue("payment_confirmation_invoice_number_text"));
        HelperData.setLastActionPage(new PaymentConfirmationPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
    }

    public void verifyConfirmationPage() {
        if (isElementVisible("VIRTUAL_ACCOUNT_IS_USED", 5)){
            tapElement("CLICK_NEXT_BUTTON");
        }
        waitForVisibilityOf("DUE_DATE", 60);
        validateExist("DUE_DATE", 5);
    }

    public void veryfyRecoConfirmation(){
        if (!isElementVisible("RECOMMENDATION_TITLE", 10)){
            swipeUpToElement("RECOMMENDATION_TITLE");
        } else if (!isElementVisible("CONFIRMATION_PRODUCT_RECO_PRICE", 5)) {
            swipeUpToElement("CONFIRMATION_PRODUCT_RECO_PRICE");
        }
        validateExist("CONFIRMATION_PRODUCT_RECO_IMAGE", 5);
        validateExist("CONFIRMATION_PRODUCT_RECO_TITLE", 5);
        validateExist("CONFIRMATION_PRODUCT_RECO_PRICE", 5);
    }

    public void swipeCarouselReco(){
        swipeLeftAtSpecifiedLocator("CONFIRMATION_PRODUCT_RECO_IMAGE");
        swipeRightAtSpecifiedLocator("CONFIRMATION_PRODUCT_RECO_IMAGE");
    }

    public void clickProductReco(){
        tapElement("CONFIRMATION_PRODUCT_RECO_IMAGE");
    }

    public void clickOrderDetail(){
        if (!isElementVisible("BUTTON_ORDER_DETAIL", 5)){
            swipeUpToElement("BUTTON_ORDER_DETAIL");
        }
        tapElement("BUTTON_ORDER_DETAIL");
    }

    public void clickCancelTransaction(){
        if (!isElementVisible("CANCEL_BUTTON", 5)){
            swipeUpToElement("CANCEL_BUTTON");
        }
        tapElement("CANCEL_BUTTON");
    }

    public void clickBtnConfirmation(){
        if (!isElementVisible("CONFIRMATION_CANCAL_BTN", 5)){
            swipeUpToElement("CONFIRMATION_CANCAL_BTN");
        }
        tapElement("CONFIRMATION_CANCAL_BTN");
    }

    public void validatePaymentConfirmation(String paymentMethod) {
        switch (paymentMethod) {
            case "Gerai":
                waitForVisibilityOf("payment_confirmation_transfer_vp_total_amount", 20);
                validateValue().equals(CheckoutData.getTotalPaymentCheckout(),
                        getIntFromRp(getElementValue("payment_confirmation_transfer_vp_total_amount")));
                TransactionData.setInvoiceNo(getElementValue("payment_confirmation_new_invoice_id_text").substring(1));
                CheckoutData.setInvoiceNumber(getElementValue("payment_confirmation_new_invoice_id_text").substring(1));
                break;
            case "Transfer Bank Manual":
                waitForVisibilityOf("payment_confirmation_instruksi_pembayaran", 30);
                validateExist("payment_confirmation_payment_due_date",5);
                validateExist("payment_confirmation_count_down_title");
                validateExist("payment_confirmation_total_payment");
                TransactionData.setTotalPayment(getIntFromRp(getElementValue("payment_confirmation_total_payment")));
                validateExist("payment_confirmation_payment_method");
                TransactionData.setPaymentMethod(getElementValue("payment_confirmation_payment_method"));
                validateExist("payment_confirmation_list_bank");
                swipeUpToElement("payment_confirmation_invoice_number");
                validateExist("payment_confirmation_invoice_number");
                TransactionData.setInvoiceNo(getElementValue("payment_confirmation_invoice_number"));
                CheckoutData.setInvoiceNumber(getElementValue("payment_confirmation_invoice_number"));
                break;
            default:
                Assert.fail("Invalid payment method");
                break;
        }
        HelperData.setLastActionPage(new PaymentConfirmationPage(iosDriver));
    }

    public void tapLihatDetailPesananBtn() {
        swipeUpToElement("payment_confirmation_lihat_detail_pesanan_btn");
        tapElement("payment_confirmation_lihat_detail_pesanan_btn");
    }

}
