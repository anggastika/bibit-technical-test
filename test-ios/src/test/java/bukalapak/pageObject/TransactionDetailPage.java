package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.STRIPEData;
import bukalapak.data.TransactionData;
import bukalapak.data.XPRData;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static bukalapak.TestInstrument.dotenv;

public class TransactionDetailPage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(TransactionDetailPage.class);

    public TransactionDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isOnTransactionDetailPage() {
        if (isElementVisible("transaction_detail_mengerti_button", 30)) {
            tapElement("transaction_detail_mengerti_button");
        }
        assertTrue(isElementVisible("transaction_detail_title_text"));
        HelperData.setLastActionPage(new TransactionDetailPage(iosDriver));
    }

    public void openTransactionDetailPage() {
        tapElement("invoice_detail_product_name_text");
    }

    public void tapOnRepurchaseButton() {
        tapElement("transaction_detail_beli_lagi_button");
    }

    public void validationResiNumber() {
        waitFor(5);
        assertEquals(STRIPEData.getResiNumber(), getText("transaction_detail_textview_nores"));
    }

    public void validateResiNumber() {
        waitForVisibilityOf("penjualan_resi_number_text", 20);
        assertEquals(getTextFromElement("penjualan_resi_number_text"), STRIPEData.getResiNumber());
        HelperData.setLastActionPage(new TransactionDetailPage(iosDriver));
    }

    public void validateJasaPengiriman() {
        assertEquals(getTextFromElement("penjualan_jasa_pengiriman"), STRIPEData.getJasaPengiriman());
        HelperData.setLastActionPage(new TransactionDetailPage(iosDriver));
    }

    public void validateStatusPengirimanInPembelianPage(String status) {
        assertEquals(getTextFromElement("transaction_detil_status_text"), status);
        HelperData.setLastActionPage(new TransactionDetailPage(iosDriver));
        XPRData.setNoTransaksiPenjualan(getTextFromElement("detail_pembelian_nomor_transaksi_text"));
    }

    public void validateStatusPengirimanInPenjualanPage(String status) {
        assertEquals(getTextFromElement("transaction_detil_penjualan_status_text"), status);
        HelperData.setLastActionPage(new TransactionDetailPage(iosDriver));
        XPRData.setNoTransaksiPenjualan(getTextFromElement("transaction_detil_no_transaksi_text"));

    }

    public void validateButtonKodeUnik() {
        assertTrue(isElementVisible("transaction_detil_Masukkan_Kode_Unik_button"), "Masukan kode unik is available");
    }

    public void clickMasukanKodeUnik() {
        waitForVisibilityOf("transaction_detail_masukan_kode_unik_button");
        tapElement("transaction_detail_masukan_kode_unik_button");
    }

    private void validateStatusBeforeInputUnikKode(DataTable status) {
        List<List<String>> rows = status.asLists(String.class);
        for (List<String> columns : rows) {
            for (String label : columns) {
                if (label.equals("Masukkan Kode Unik")) {
                    validateDisplayed("transaction_detail_masukan_kode_unik_button");
                    validateValue().equals(getText("transaction_detail_masukan_kode_unik_button"), label);
                } else {
                    validateDisplayed(constructLocator("transaction_detail_status_ambil_sendiri_text", label));
                    validateValue().equals(getElementValue(constructLocator("transaction_detail_status_ambil_sendiri_text", label)), label);
                }
            }
        }
        tapElement("transaction_detail_detail_status_ambil_sendiri_button");
        validateDisplayed("transaction_detail_status_pengiriman1_ambil_sendiri_text");
        validateDisplayed("transaction_detail_status_pengiriman2_ambil_sendiri_text");
        tapElement("Konfirmasi_kirim_manual_close_popup");
    }

    private void validateStatusAfterInputUnikKode(DataTable status) {
        List<List<String>> rows = status.asLists(String.class);
        for (List<String> columns : rows) {
            for (String label : columns) {
                if (label.equals("Ingatkan Konfirmasi")) {
                    validateDisplayed("transaction_detail_ingatkan_konfirmasi_text");
                    validateValue().equals(getText("transaction_detail_ingatkan_konfirmasi_text"), label);
                } else {
                    validateDisplayed(constructLocator("transaction_detail_status_ambil_sendiri_text", label));
                    validateValue().equals(getElementValue(constructLocator("transaction_detail_status_ambil_sendiri_text", label)), label);
                }
            }
        }
    }

    public void validateStatusAmbilSendiri(String state, DataTable status) {
        if (state.equals("before")) {
            validateStatusBeforeInputUnikKode(status);
        } else {
            validateStatusAfterInputUnikKode(status);
        }
        HelperData.setLastActionPage(new TransactionDetailPage(iosDriver));
    }

    public void validateStatusAfterInputKodeUnik() {
        assertEquals(getTextFromElement("transaction_detil_status_after_verification_text"), "Kode berhasil diverifikasi");
    }

    public void validateKonfirmasiButton() {
        validateDisplayed("transaction_detail_konfirmation_button");
        HelperData.setLastActionPage(new TransactionDetailPage(iosDriver));
    }

    public void tapOnKomplainbutton() {
        waitForVisibilityOf("transaction_detail_komplain_button", 40);
        tapElement("transaction_detail_komplain_button");
    }

    public void tapOnBukabantuan() {
        tapElement("transaction_detail_bukabantuan_link", Direction.UP);
    }

    public void validateKomplainText(String caption) {
        swipeUpToElement("transaction_detail_komplain_text");
        String complaintCaption = getTextFromElement("transaction_detail_komplain_text");
        validateValue().equals(caption, complaintCaption);
    }

    public void tapOnHalamanKomplainLink() {
        waitForVisibilityOf("transaction_detail_komplain_link", 10);
        tapElement("transaction_detail_komplain_link");
    }

    public void validateButtonText(String caption) {
        waitForVisibilityOf(constructLocator("transaction_detail_button_text", caption), 10);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void scrollsToRecommendation() {
        swipeUpToElement("transaction_detail_recommendation_text", 3);
    }

    public void validateRecommendationTitle() {
        validateDisplayed("transaction_detail_recommendation_text");
    }

    public void tapButtonBeliRecommendation() {
        swipeUpToElement("transaction_detail_button_beli", 1);
        tapElement("transaction_detail_button_beli");
    }

    public void validateCartDialogue() {
        validateDisplayed("transaction_detail_cart_dialog");
        tapElement("transaction_detail_button_close_cart_dialog");
    }

    public void tapProductRecommendation() {
        validateExist("transaction_detail_recommendation_product", 10);
        tapElements("transaction_detail_recommendation_product", 1);
    }

    public void verifySuperSellerFeeDetail() {
        swipeUpToElement("transaction_detail_pengiriman_title", 15);
        validateDisplayed("transaction_detail_biaya_super_seller_text");
        validateDisplayed("transaction_detail_biaya_super_seller_chevron_image");
        validateRpFormat("transaction_detail_biaya_super_seller_price_text");
    }

    public void tapSuperSellerFeeDetailText() {
        swipeUpToElement("transaction_detail_pengiriman_title", 15);
        tapElement("transaction_detail_biaya_super_seller_text", 5);
    }

    public void verifySuperSellerFeeTextNotExist() {
        swipeUpToElement("transaction_detail_pengiriman_title");
        validateNotDisplayed("transaction_detail_biaya_super_seller_text");
    }

    public void verifySuperSellerFeeDetail(String detailName) {
        swipeUpToElement("transaction_detail_pengiriman_title");
        validateDisplayed(constructLocator("transaction_detail_biaya_super_seller_detail_text", detailName));
        validateRpFormat(constructLocator("transaction_detail_biaya_super_seller_detail_price_text", detailName));
    }

    public void verifyInfoBiayaSuperSellerLink() {
        swipeUpToElement("transaction_detail_info_biaya_super_seller_text");
        validateDisplayed("transaction_detail_info_biaya_super_seller_text");
        HelperData.setLastActionPage(new TransactionDetailPage(iosDriver));
    }

    public void verifyInfoBiayaSuperSellerPopUp() {
        waitForVisibilityOf("transaction_detail_info_biaya_ss_mutasi_text");
        verifyElementExist("transaction_detail_info_biaya_ss_title_text");
        verifyElementExist("transaction_detail_info_biaya_ss_close_button");
        validateDisplayed("transaction_detail_info_biaya_ss_jenis_biaya_text");
        validateDisplayed("transaction_detail_info_biaya_ss_biaya_layanan_text");
        validateDisplayed("transaction_detail_info_biaya_ss_cashback_text");
        swipeUpToElement("transaction_detail_info_biaya_ss_ketentuan_voucher_text");
        validateDisplayed("transaction_detail_info_biaya_ss_ketentuan_voucher_text");
        swipeUpToElement("transaction_detail_info_biaya_ss_voucher_lapak_text");
        validateDisplayed("transaction_detail_info_biaya_ss_voucher_lapak_text");
        HelperData.setLastActionPage(new TransactionDetailPage(iosDriver));
    }

    public void tapKunjungiMutasiSuperSeller() {
        tapElement("transaction_detail_info_kunjungi_mutasi_super_seller_btn", 10);
    }

    public void goToTransactionDetailPageWithDeeplink(String deeplinkEnvVar) throws Exception {
        String deeplink = dotenv.get(deeplinkEnvVar);
        if (deeplink == null) {
            throw new Exception(deeplinkEnvVar + " is not set!");
        }

        openDeepLink(deeplink);
        isOnTransactionDetailPage();
    }

    public void validateRefundedStatus() {
        validateDisplayed("transaction_detail_refunded_status_text");
    }

    public void validateOldRejectReason(String rejectReason) {
        validateDisplayed(constructLocator("transaction_detail_reject_reason_text", rejectReason));
    }

    public void validateTagBukaExpressOnTransactionDetail() {
        validateDisplayed("transaction_detail_bukaexpress_text");
        validateDisplayed("transaction_detail_tooltip_bukaExpress_button");
        HelperData.setLastActionPage(new TransactionDetailPage(iosDriver));
    }

    public void openDetailTransactionViaDeeplinkwithIdTrxFromAPI() {
        String deeplink = "https://www.bukalapak.com/payment/transactions/" + TransactionData.getIdTransaksi();
        LOGGER.info("deeplink :" + deeplink);
        openDeepLink(deeplink);
    }

    public void validateTrxDetailPage() {
        waitForVisibilityOf("TITLE_TRANSACTION_DETAIL_PAGE", 3);
        validateDisplayed("TITLE_TRANSACTION_DETAIL_PAGE");
        if (!isElementVisible("SHOW_TRANSACTION_DETAIL", 3)){
            swipeUpToElement("SHOW_TRANSACTION_DETAIL", 2);
        }
        validateDisplayed("SHOW_TRANSACTION_DETAIL");
    }
}
