package bukalapak.pageObject;

import bukalapak.data.*;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static bukalapak.TestInstrument.dotenv;

public class InvoiceDetailPage extends BasePage {

    public InvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isOnInvoiceDetailPage() {
        assertTrue(isElementVisible("invoice_detail_detail_tagihan_header", 45));
        waitFor(15);
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void isProductMatch(String packageName) {
        getLocator("label_" + packageName);
    }

    public void isPhoneNumberMatch(String phoneNumber) {
        getLocator("label_" + phoneNumber);
    }

    public void isInvoiceNoMatch(String invoiceNo) {
        getLocator("label_" + invoiceNo);
    }

    public void tapOnRepurchaseButton() {
        swipeDownToElement("invoice_detail_repay_button");
        tapElement("invoice_detail_repay_button");
    }

    public void changePaymentButton(){
        swipeUpToElement("invoice_payment_method_ubah");
        tapElement("invoice_payment_method_ubah");
    }

    public void tapContinuePaymentProcessButton() {
        swipeUpToElement("invoice_detail_continue_payment_process_btn");
        tapElement("invoice_detail_continue_payment_process_btn");
    }

    public void cancelTransaction(String reason) {
        swipeUpToElement("invoice_detail_label_batalkan_trx");
        tapElement("invoice_detail_label_batalkan_trx");
        waitForVisibilityOf(constructLocator("invoice_detail_label", reason));
        tapElement(constructLocator("invoice_detail_label", reason));
        tapElement("invoice_detail_confirm_cancel");
    }

    public void verifyStatusOnInvoice(String status) {
        assertTrue(isElementVisible(constructLocator("invoice_detail_status", status.toUpperCase())));
    }

    public void verifyPaymentMethodOnInvoice(String method) {
        swipeUpToElement(constructLocator("invoice_payment_method", method));
        assertTrue(isElementVisible(constructLocator("invoice_payment_method", method)));
    }

    public void verifyPaymentCodeTransferOnInvoice() {
        assertTrue(isElementVisible("invoice_detail_summary_kode_pembayaran"), "Label kode pembayaran tidak ditemukan!");
    }

    public void verifyMixPaymentDeductionNotExist(String type) {
        switch(type) {
            case "saldo":
                assertTrue(!isElementVisible("invoice_detail_summary_potongan_saldo"), "Potongan label mixpayment saldo masih ditemukan!");
                break;
            case "DANA":
                assertTrue(!isElementVisible("invoice_detail_summary_potongan_dana"), "Potongan label mixpayment DANA masih ditemukan!");
                break;
            default:
                assertTrue(!isElementVisible("invoice_detail_summary_potongan_saldo"), "Potongan label mixpayment saldo masih ditemukan!");
                assertTrue(!isElementVisible("invoice_detail_summary_potongan_dana"), "Potongan label mixpayment DANA masih ditemukan!");
        }
    }

    public void verifyMixPaymentDeductionOnInvoice(String type) {
        int amountMixPaymentSaldo;
        String mixPaymentSaldo;
        String paymentMethod = MtxData.getPaymentMethod();
        LogUtil.info("Potongan Mixpayment : " + MtxData.getMixPayment());
        if (type.equals("saldo")) {
            LogUtil.info("Potongan Mixpayment Saldo Bukadompet : " + MtxData.getMixPaymentSaldo());
            assertTrue(isElementVisible("invoice_detail_summary_potongan_saldo"), "Potongan label mixpayment saldo tidak ditemukan!");
            if(paymentMethod.equals("Transfer Bank Otomatis")) {
                /* payment method for handle Saldo BukaDompet inside invoice that always deducted by Saldo Credit for VA */
                amountMixPaymentSaldo = Integer.parseInt(MtxData.getMixPaymentSaldo().replaceAll("[^0-9]", "")) - 10;
                mixPaymentSaldo = getRpFromPrice(amountMixPaymentSaldo);
                try {
                    assertTrue(isElementVisible("invoice_detail_summary_potongan_credits"), "Potongan label mixpayment credits tidak ditemukan!");
                } catch (AssertionError e) {
                    LogUtil.info("credits tidak mencukupi");
                }
                assertTrue(isElementVisible(constructLocator("invoice_detail_summary_jumlah_potongan", "-" + mixPaymentSaldo)), "Potongan harga mixpayment saldo tidak ditemukan!");
            } else {
                assertTrue(isElementVisible(constructLocator("invoice_detail_summary_name",  MtxData.getMixPaymentSaldo())), "Potongan harga mixpayment saldo tidak ditemukan!");
            }
        } else if (type.equals("dana")) {
            LogUtil.info("Potongan Mixpayment DANA : " + MtxData.getMixPaymentDana());
            assertTrue(isElementVisible("invoice_detail_summary_potongan_dana"), "Potongan label mixpayment DANA tidak ditemukan!");
            assertTrue(isElementVisible(constructLocator("invoice_detail_summary_name",  MtxData.getMixPaymentDana())), "Potongan harga mixpayment DANA tidak ditemukan!");
        }
    }

    public void verifyWebview3rdPayment(String payment) {
        assertTrue(isElementVisible(constructLocator("invoice_detail_name", payment), 45), "Halaman webview 3rd dengan payment " + payment + " tidak ditemukan!");
    }

    public void verifyDropshipperOnInvoice(String dropshipperType) {
        String tmpDropshipperType = dropshipperType.toUpperCase();
        swipeToLocator("invoice_detail_dropshipper");
        assertTrue(isElementVisible(constructLocator("invoice_detail_dropshipper_name", dotenv.get(tmpDropshipperType + "_DROPSHIPPER_NAME"))));
        assertTrue(isElementVisible(constructLocator("invoice_detail_dropshipper_detail", dotenv.get(tmpDropshipperType + "_DROPSHIPPER_DETAIL"))));
    }

    public void verifyCatataPelapakOnInvoice(String notes) {
        String tmpNotes = notes.toUpperCase();
        swipeToLocator("invoice_detail_catatan_pelapak");
        verifyElementExist(constructLocator("invoice_detail_note_pelapak", dotenv.get(tmpNotes + "_NOTES")));
    }

    public void verifypembulatanOnInvoice() {
        waitFor(4);
        verifyElementExist("invoice_detail_pembulatan_text");
        verifyElementExist(constructLocator("invoice_detail_pembulatan_name", TransactionData.getRoundedAmount()));
        String pembulatan = "//XCUIElementTypeCell[24]/XCUIElementTypeTextView[@value='Pembulatan " + TransactionData.getRoundedAmount() + " akan dikembalikan ke BukaDompet milikmu.']";
        verifyElementExist(constructLocator("invoice_detail_pembulatan_xpath", pembulatan));
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void verifyNoRoundedOnInvoice() {
        waitFor(4);
        verifyElementNotExist("invoice_detail_pembulatan_text");
        verifyElementNotExist("invoice_detail_pembulatan_info_text");
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void verifyPendingStatusOnInvoice() {
        assertTrue(isElementVisible("invoice_detail_pending_status"));
    }

    public void goToHomePage() {
        backToHomePage();
    }

    public void verifyCatatanPelapakFor(String expectedSellerNote) {
        swipeUpToElement("invoice_detail_catatan_pelapak");
        String expectedSellerNoteTxt = constructLocator("invoice_detail_catatan_pelapak_txt", expectedSellerNote);
        verifyElementExist(expectedSellerNoteTxt,20,"expectedSellerNote not found");
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void confirmPayment(String time) {
        int option = 0;
        if (time.equals("3.00-21.00")) {
            option = 1;
        }
        waitForVisibilityOf("invoice_detail_upload_receipt_button", 10);
        tapElement("invoice_detail_upload_receipt_button");
        tapElements("invoice_detail_confirm_time_radio_button", option);
    }

    public void validateOrangeMessage(String message) {
        String orangeMessage = getTextFromElement("invoice_detail_orange_message_text");
        validateValue().equals(message, orangeMessage);
    }

    private void uploadImage(int albumIndex, int imageIndex) {
        waitForVisibilityOf("invoice_detail_gallery_tab_text", 30);
        tapElements("invoice_detail_gallery_album", albumIndex, 10);
        tapElements("invoice_detail_gallery_image", imageIndex, 10);
        tapElement("invoice_detail_gallery_lanjut_button");
    }

    public void uploadReceiptImage() {
        tapElement("invoice_detail_upload_image_button");
        uploadImage(0,1);
        tapElement("invoice_detail_kirim_image_button");
    }

    public void validateImageReceipt() {
        waitForVisibilityOf("invoice_detail_image_message_under_text", 30);
        String imageTitle = getTextFromElement("invoice_detail_receipt_image_title");
        CSIData.setReceiptImage(imageTitle);
    }

    public void validateModalMessage(String message) {
        waitForVisibilityOf("invoice_detail_success_confirm_modal", 120);
        String modalMessage = getTextFromElement("invoice_detail_modal_message");
        validateValue().equals(message, modalMessage);
    }

    public void changeReceiptImage() {
        waitForVisibilityOf("invoice_detail_ubah_image_button", 10);
        tapElement("invoice_detail_ubah_image_button");
        waitForVisibilityOf("invoice_detail_modal_edit_gambar");
        tapElement("invoice_detail_ganti_image_button");
        uploadImage(0,2);
        waitForVisibilityOf("invoice_detail_simpan_image_button", 10);
        tapElement("invoice_detail_simpan_image_button");
    }

    public void validateChangedImageReceipt() {
        //need time to change image label
        waitFor(3);
        waitForVisibilityOf("invoice_detail_receipt_image_title");
        validateDisplayed("invoice_detail_image_message_under_text");
        String imageTitle = getTextFromElement("invoice_detail_receipt_image_title");
        validateValue().notEquals(imageTitle, CSIData.getReceiptImage());
    }

    public void validateMessageUnderPhoto(String message) {
        waitForVisibilityOf("invoice_detail_image_message_under_text", 120);
        String receiptMessage = getTextFromElement("invoice_detail_image_message_under_text");
        validateValue().contains(message, receiptMessage);
    }

    // Begin - VP INSURANCE
    public void validateInsuranceFee() {
        assertEquals(getIntFromRp("invoice_marketplace_insurance_fee"), InsuranceData.getSumFee());
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void validateInsuranceFeeIsNotExist() {
        verifyElementNotExist("invoice_marketplace_insurance_fee");
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }
    // End - VP INSURANCE

    //Subsidies - Voucher
    public void isVoucherDiscountPresent() {
        if (!isElementExist("invoice_detail_total_tagihan_txt")) {
            tapElement("invoice_detail_informasi_tagihan_txt");
        }
        validateExist("invoice_detail_potongan_voucher", 10);
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void validateVoucherBenefit(String owner, String type, String amount) {
        String priceDetailCopy = SubsidiesData.buildPriceDetailCopy(owner, type);
        String locator = constructLocator("invoice_detail_benefit_voucher", priceDetailCopy, getRpFromNumber(Integer.parseInt(amount)));
        waitForVisibilityOf(locator, 20);
        validateExist(locator, 20);
    }

    public void validateVouchersBenefit(DataTable selectedVouchersTable) {
        List<Map<String, String>> selectedVouchers = selectedVouchersTable.asMaps();
        selectedVouchers.forEach(voucher -> {
            validateVoucherBenefit(
                    voucher.get("Voucher Owner"),
                    voucher.get("Voucher Type"),
                    voucher.get("Voucher Benefit"));
        });
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void validateDisqualifyModal() {
        swipeUpToElement("invoice_detail_cancel_benefit_voucher");
        validateExist("invoice_detail_cancel_benefit_voucher");
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void validateDisqualifyModalIsNotExist() {
        verifyElementNotExist("invoice_detail_cancel_benefit_voucher");
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void clickSetujuGanti() {
        validateExist("invoice_detail_keep_benefit_voucher", 10);
        tapElement("invoice_detail_keep_benefit_voucher");
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void clickBatalGanti() {
        validateExist("invoice_detail_disqualitfy_benefit_voucher", 10);
        tapElement("invoice_detail_disqualitfy_benefit_voucher");
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }
    //End - Subsidies Voucher

    // Begin - MTQ
    public void userOnInvoiceDetailPageWithDeeplink(String deeplinkEnvVar) throws Exception {
        String deeplink = dotenv.get(deeplinkEnvVar);
        if (deeplink == null) {
            throw new Exception(deeplinkEnvVar + " is not set!");
        }

        openDeepLink(deeplink);
        isOnInvoiceDetailPage();
    }

    public void tapLihatDetailButton() {
        swipeUpToElement("invoice_detail_lihat_detail_button");
        tapElement("invoice_detail_lihat_detail_button");
    }

    public void validateRefundedStatus() {
        validateDisplayed("invoice_detail_status_name");
    }

    public void validateOldRejectReason(String reason) {
        validateElementContainsText("invoice_detail_reject_reason_text", reason);
    }
    // End - MTQ

    public void tapAlamatPengirimanSection() {
        swipeToLocator("detail_pembelian_alamat_pengiriman_section");
        tapElement("detail_pembelian_alamat_pengiriman_section");
    }

    public void validatePhoneNumberMasked() {
        validateValue().contains("-****-", getText("detail_pembelian_alamat_content_text"));
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    //financing
    public void validateCTAPayLater() {
        nativeSwipeUp();
        delay(2000);
        nativeSwipeUp();
        validateNotDisplayed("detail_pembelian_bayar_lagi_button");
        validateNotDisplayed("detail_pembelian_cek_limit_button");
    }
}
