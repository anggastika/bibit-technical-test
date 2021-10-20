package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PROMData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import static bukalapak.TestInstrument.dotenv;

public class TransactionListPage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(TransactionListPage.class);

    public TransactionListPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnTransactionListPage() {

        if (!isElementVisible("transaction_list_pembelian_tab")) {
            backToTransactionList();
        }
        verifyElementExist("transaction_list_pembelian_tab");
        verifyElementExist("transaction_list_penjualan_tab");
        HelperData.setLastActionPage(new TransactionListPage(iosDriver));
    }

    public void searchInvoice(String invoiceType) {
        String tmpInvoiceType = dotenv.get(invoiceType.replaceAll(" ", "_").toUpperCase() + "_INVOICE_NUMBER");
        typeAndEnterValue("transaction_list_search_edit_text", tmpInvoiceType);
    }

    public void searchTransactionPembelian(String idTransaction) {
        waitLoadingBar();
        IOSElement iosElement = iosDriver.findElement(getLocator("transaction_list_search_edit_text"));
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(idTransaction + "\n");
        waitForVisibilityOf("transaction_list_first_transaction", 50);
    }

    public void searchTransactionPenjualan(String idTransaction) {
        waitLoadingBar();
        typeAndEnterValueWithTimeOut("transaction_list_search_edit_text", idTransaction);
    }

    public void waitLoadingBar() {
        int max_limit = 20;
        int counter = 0;
        do {
            waitFor(5);
            LOGGER.info("loading bar :" + isElementVisible("transaction_list_loading_proses"));
            counter++;
            if (isElementVisible("transaction_list_loading_proses") == false) {
                break;
            }
        } while (isElementVisible("transaction_list_loading_proses") && counter < max_limit);
    }

    public void clickOnTransaction() {
        waitForVisibilityOf("transaction_list_first_transaction", 50);
        tapElement("transaction_list_first_transaction");
    }

    public void clickOnTransactionPenjualan() {
        waitForVisibilityOf("transaction_list_first_transaction_penjualan", 10);
        tapElement("transaction_list_first_transaction_penjualan");
    }

    public void clickOnPenjualanTab() {
        tapElement("transaction_list_penjualan_tab");
    }

    public void checkFirstPromotedBudgetInvoice(String status) {
        waitForVisibilityOf("transaction_list_first_invoice_promoted_title_text", 30);
    }

    public void checkFirstInvoiceDetailProm(String type, String status) {
        switch (type) {
            case "Promoted Push add budget":
            case "Promoted Keyword add budget":
                checkPromotedPushInvoiceList(status);
                goToHomePage();
                break;
            case "Paket Push":
                checkPushPackageInvoiceList(status);
                break;
            default:
                Assert.fail("Please check your selection!");
                break;
        }
    }

    private void checkPromotedPushInvoiceList(String status) {
        waitForVisibilityOf("transaction_list_first_invoice_promoted_title_text", 60);
        if (status.equalsIgnoreCase("paid")) {
            assertEquals("DIBAYAR", getTextFromElement("transaction_list_first_invoice_promoted_status_text"));
        } else if (status.equalsIgnoreCase("pending")) {
            assertTrue(getTextFromElement("transaction_list_first_invoice_promoted_status_text").contains("Hingga"));
        } else {
            assertEquals("KEDALUWARSA", getTextFromElement("transaction_list_first_invoice_promoted_status_text"));
        }
        assertEquals(PROMData.getInputtedBudget(), getIntegerFromTextElement("transaction_list_first_invoice_promoted_price_text"));
    }

    private void checkPushPackageInvoiceList(String status) {
        waitForVisibilityOf("transaction_list_first_invoice_push_title_text", 30);
        if (status.equalsIgnoreCase("paid")) {
            assertEquals("DIBAYAR", getTextFromElement("transaction_list_first_invoice_push_status_text"));
        } else if (status.equalsIgnoreCase("pending")) {
            assertTrue(getTextFromElement("transaction_list_first_invoice_push_status_text").contains("Hingga"));
        } else {
            assertEquals("KEDALUWARSA", getTextFromElement("transaction_list_first_invoice_push_status_text"));
        }
        assertEquals(PROMData.getSelectedPushPackagePrice(), getTextFromElement("transaction_list_first_invoice_push_price_text"));
    }

    public void validatePriorityResiOtomatis() {
        swipeDownToElement("transaction_list_third_invoice_wajib_resi_otomatis_text");
        waitForVisibilityOf("transaction_list_third_invoice_wajib_resi_otomatis_text", 10);
    }

    public void verifyPushTransaction() {
        waitForVisibilityOf("transaction_list_first_invoice_push_title_text", 15);
    }

    public void clickOnPembelianTab() {
        tapElement("transaction_list_pembelian_tab");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapFirstTransactionPembelian() {
        waitForVisibilityOf("transaction_list_first_transaction_pembelian", 60);
        tapElement("transaction_list_first_transaction_pembelian");
    }

    public void validateFreezeWarningBannerDisplayed(String freezeWarningBanner, String bannerState) {
        if (bannerState == null) {
            waitForVisibilityOf("transaction_list_freeze_warning_banner", 10);
            validateElementWithText(constructLocator("transaction_list_freeze_warning_banner_info_name", freezeWarningBanner), freezeWarningBanner);
        } else {
            validateNotExist("transaction_list_freeze_warning_banner", 5);
        }
        HelperData.setLastActionPage(new TransactionListPage(iosDriver));
    }

    public void validateCODlabel() {
        validateExist("transaction_list_COD_label", 3);
        HelperData.setLastActionPage(new TransactionListPage(iosDriver));
    }

    public void tapOnTransactionPenjualan() {
        waitForVisibilityOf("transaction_list_transaction_penjualan");
        tapElement("transaction_list_transaction_penjualan", 20);
    }

    //this method is used to back to transaction list when tap transaction
    // and the landing page is not transaction list but transaction detail page
    private void backToTransactionList() {
        if (isElementVisible("transaction_detail_mengerti_button", 20)) {
            tapElement("transaction_detail_mengerti_button");
        }
        if (isElementVisible("transaction_detail_title_text")) {
            tapElement("transaction_detail_back_button");
        }
        if (isElementVisible("konfirmasi_kirim_title")) {
            tapElement("konfirmasi_kirim_close_button");
            tapElement("transaction_detail_back_button");
        }
    }

    public void tapOnBuatVoucherLapakBanner() {
        try {
            validateExist("transaction_list_voucher_lapak_banner", 10);
        } catch (AssertionError e) {
            doPullRefresh(2);
            validateExist("transaction_list_voucher_lapak_banner", 10);
        }
        tapElement("transaction_list_voucher_lapak_banner");
    }

    public void verifySellerBanner() {
        verifyElementExist("transaction_list_banner");
        verifyElementExist("transaction_list_sembunyikan_btn");
    }

    public void validateTagBukaExpress() {
        waitLoadingBar();
        validateExist("transaction_list_bukaexpress_text");
        validateExist("transaction_list_tooltip_bukaExpress_button");
        HelperData.setLastActionPage(new TransactionListPage(iosDriver));
    }

    public void tapTabMenu(String tabName) {
        tapElement(constructLocator("transaction_list_tab_menu", tabName));
    }

    public void searchSpecificTrxOrInvoice(String type, String keyword) {
        waitForVisibilityOf("transaction_list_search_edit_text", 10);
        tapElement("transaction_list_search_edit_text");
        tapElement("transaction_list_search_clear_button");
        typeValue("transaction_list_search_edit_text", keyword + "\n");
        if (type.equals("invoice")) {
            TransactionData.setInvoiceNo(keyword);
        } else {
            TransactionData.setIdTransaksi(keyword);
        }
    }

    public void validateSearchTrxOrInvoice(String type) {
        switch (type) {
            case "invoice":
                validateExist("transaction_list_tagihan_trx_state", 20);
                validateExist("transaction_list_tagihan_trx_product_name", 5);
                validateExist("transaction_list_tagihan_trx_price", 5);
                validateExist("transaction_list_tagihan_trx_image", 5);
                break;
            case "transaction":
                validateExist("transaction_list_pembelian", 20);
                break;
            default:
                Assert.fail("Invalid Search Type");
        }

    }

    public void tapSearchResultTrx(String type) {
        String btn = (type.equals("transaction")) ? "transaction_list_pembelian" : "transaction_list_tagihan_trx_image";
        tapElement(btn);
    }

    public void validateInvoiceOrTrxNumber(String type) {
        String elementVal;
        String expectedVal;

        if (type.equals("transaction")) {
            elementVal = "detail_pembelian_nomor_transaksi_text2";
            expectedVal = TransactionData.getIdTransaksi();
        } else {
            elementVal = "detail_pembelian_invoice_number_text";
            expectedVal = TransactionData.getInvoiceNo();
        }

        validateExist(elementVal, 20);
        assertEquals(expectedVal,
                getElementValue(elementVal), type + " number doesn't match");
    }
}
