package bukalapak.pageObject;

import bukalapak.data.CSIData;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import org.openqa.selenium.Rectangle;

public class BukaBantuanPage extends BasePage {

    public BukaBantuanPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukaBantuanPage() {
        waitForVisibilityOf("bukabantuan_title", 10);
        waitForVisibilityOf("bukabantuan_onboarding_title");
        waitForVisibilityOf("bukabantuan_search_area");
        HelperData.setLastActionPage(new BukaBantuanPage(iosDriver));
    }

    public void tapPengajuanKomplain() {
        tapElement("bukabantuan_pengajuan_komplain_text");
    }

    public void goToTransaction() {
        swipeUpToElement("bukabantuan_lihat_semua_button");
        tapElement("bukabantuan_lihat_semua_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void backToKomplainPage() {
        while (isElementVisible("base_back_button")) {
            try {
                tapElement("base_back_button");
            } catch (Exception e) {
                break;
            }
        }
    }

    public void searchTransaction(String keyword) {
        String tmpKeyword = keyword;
        switch (keyword){
            case "noTagihan" :
                tmpKeyword= CSIData.getNomorTagihan();
                break;
            case "noTransaksi":
                tmpKeyword = CSIData.getNomorTransaksi();
                break;
            default:
                LogUtil.warn("using direct keyword :" + keyword);
                break;
        }
        waitForVisibilityOf("bukabantuan_cari_transaksi_textfield");
        IOSElement searchField = iosDriver.findElement(getLocator("bukabantuan_cari_transaksi_textfield"));
        searchField.sendKeys(tmpKeyword + "\n");
    }

    public void tapSoughtTransaction(String keyword) {
        switch (keyword){
            case "BELUM DIBAYAR" :
                waitForVisibilityOf("bukabantuan_status_hasil_transaksi_text1", 10);
                tapElement("bukabantuan_status_hasil_transaksi_text1");
                break;
            case "KEDALUWARSA":
                waitForVisibilityOf("bukabantuan_status_hasil_transaksi_text2", 10);
                tapElement("bukabantuan_status_hasil_transaksi_text2");
                break;
            case "DIBAYAR":
                waitForVisibilityOf("bukabantuan_status_hasil_transaksi_text3", 10);
                tapElement("bukabantuan_status_hasil_transaksi_text3");
                break;
            case "DIKIRIM":
                waitForVisibilityOf("bukabantuan_status_hasil_transaksi_text4", 10);
                tapElement("bukabantuan_status_hasil_transaksi_text4");
                break;
            default:
                break;
        }
    }

    public void userOnPilihMasalahPage() {
        waitForVisibilityOf("bukabantuan_pilih_masalah_text");
    }

    public void userOnDetailMasalahPage(String problem) {
        switch (problem){
            case "Konfirmasi Pembayaran" :
                verifyElementExist("bukabantuan_konfirmasi_pembayaran_title");
                CSIData.setJenisMasalah("Konfirmasi Pembayaran");
                break;
            case "Pengembalian Dana atau Refund":
                verifyElementExist("bukabantuan_pegembalian_dana_title");
                CSIData.setJenisMasalah("Pengembalian Dana (Refund)");
                break;
            default:
                break;
        }
    }

    public void tapProblem(String keyword) {
        waitForVisibilityOf(constructLocator("bukabantuan_kategori_masalah", keyword), 30);
        tapElement(constructLocator("bukabantuan_kategori_masalah", keyword));
        waitForVisibilityOf(constructLocator("bukabantuan_artikel_masalah_title", keyword), 60);
        CSIData.setJenisMasalah(keyword);
    }

    public void tapFormBantuan() {
        swipeUpToElement("bukabantuan_isi_form_bantuan_link");
        tapElement("bukabantuan_isi_form_bantuan_link");
    }

    public void userOnFormBantuanPage() {
        waitForVisibilityOf(constructLocator("bukabantuan_form_title", CSIData.getJenisMasalah()), 30);
    }

    public void userOnSuccessSubmitPage() {
        waitForVisibilityOf("bukabantuan_sukses_kirim_title", 10);
        verifyElementExist("bukabantuan_sukses_kirim_text");
        waitFor(5);
        HelperData.setLastActionPage(new BukaBantuanPage(iosDriver));
    }

    public void scrollAndClickCategory(String keyword) {
        swipeUpToElement(constructLocator("bukabantuan_category_text", keyword));
        tapElement(constructLocator("bukabantuan_category_text", keyword));
        CSIData.setJenisMasalah(keyword);
    }

    public void tapSubCategory(String keyword) {
        waitForVisibilityOf(constructLocator("bukabantuan_category_text", keyword));
        tapElement(constructLocator("bukabantuan_category_text", keyword));
        CSIData.setJenisMasalah(keyword);
    }

    public void userOnBukaBantuanArticle() {
        waitForVisibilityOf(constructLocator("bukabantuan_webview_text", CSIData.getJenisMasalah()));
        verifyElementExist(constructLocator("bukabantuan_article_text", CSIData.getJenisMasalah()));
    }

    public void makeComplaintForm(String keyword) {
        chooseProblemForm(keyword);
        swipeUpToElement("bukabantuan_kirim_button");
        tapElement("bukabantuan_form_tnc_checkbox");
        tapElement("bukabantuan_kirim_button");
    }

    public void searchArticle(String keywords) {
        swipeDownToElement("bukabantuan_search_area");
        tapElement("bukabantuan_search_area");
        waitForVisibilityOf("bukabantuan_search_field", 10);
        IOSElement searchField = iosDriver.findElement(getLocator("bukabantuan_search_field"));
        searchField.sendKeys(keywords + "\n");
    }

    public void tapFirstArticleSuggestion(String category) {
        if (category.equalsIgnoreCase("faq")) {
            waitForVisibilityOf("bukabantuan_faq_search_result", 10);
            tapElement("bukabantuan_faq_search_result");
        } else {
            waitForVisibilityOf("bukabantuan_article_search_result", 10);
            tapElement("bukabantuan_article_search_result");
        }
    }

    public void validateArticle(String keywords) {
        waitForVisibilityOf(constructLocator("bukabantuan_article_title", keywords));
    }

    public void validateFirstTransaction(String keywords) {
        String tmpKeyword = keywords;
        switch (keywords){
            case "noTagihan" :
                tmpKeyword = CSIData.getNomorTagihan();
                break;
            case "noTransaksi":
                tmpKeyword = CSIData.getNomorTransaksi();
                break;
            default:
                LogUtil.warn("using direct keyword :" + keywords);
                break;
        }
        waitForVisibilityOf("bukabantuan_transaction_result_text", 30);
        validateDisplayed(constructLocator("bukabantuan_transaction_text", tmpKeyword));
    }

    public void filterTransaction(String keywords) {
        waitForVisibilityOf("bukabantuan_search_field");
        Rectangle rectangle = getElementPresent("bukabantuan_search_field").getRect();
        int x = rectangle.getX() + rectangle.getWidth() + 36;
        int y = rectangle.getY() + 19;
        LogUtil.info("element location: " + x + ", " + y);
        tapElement(x, y, 0);
        tapElement("bukabantuan_filter_status_menu", 10);
        tapElement(constructLocator("bukabantuan_filter_status_option", keywords), 10);
        tapElement("bukabantuan_filter_terapkan_button");
    }

    public void validateFilterTransaction(String keywords) {
        waitForVisibilityOf("bukabantuan_transaction_result_text", 30);
        String transactionStatus = getTextFromElement("bukabantuan_transaction_state");
        LogUtil.info("transactionStatus = "+transactionStatus);
        switch (keywords.toLowerCase()) {
            case "menunggu":
                assertTextContains("Belum dibayar", transactionStatus);
                break;
            case "dibayar":
                assertTrue(transactionStatus.equals("Sampai Tujuan") ||
                        transactionStatus.equals("Diterima & selesai") ||
                        transactionStatus.equals("Dikirim") ||
                        transactionStatus.equals("Dikembalikan (refund) ") ||
                        transactionStatus.equals("Dibayar"));
                break;
            case "kadaluwarsa":
                assertTextContains("Kadaluwarsa", transactionStatus);
                break;
            case "dibatalkan":
                assertTextContains("Dibatalkan", transactionStatus);
                break;
            default:
                Assert.fail(keywords + " isn't a status of transaction!");
                break;
        }
    }

    public void chooseProblemForm(String keyword) {
        waitForVisibilityOf("bukabantuan_form_problem_field", 30);
        tapElement("bukabantuan_form_problem_field");
        selectPickerWheel(keyword);
        tapElement("bukabantuan_form_pickerwheel_done_button");
    }

    public void validateTransactionCard() {
        waitForVisibilityOf("bukabantuan_transaction_result_text", 60);
    }
}
