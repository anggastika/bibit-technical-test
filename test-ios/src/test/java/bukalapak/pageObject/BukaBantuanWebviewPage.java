package bukalapak.pageObject;

import bukalapak.data.CSIData;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class BukaBantuanWebviewPage extends BasePage {

    public BukaBantuanWebviewPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukaBantuanPage() {
        waitForVisibilityOf("bukabantuan_title", 10);
        changeContext().toWebview();
        verifyElementExist("bb_caption_text");
        verifyElementExist("bb_onboarding_search_field");
        HelperData.setLastActionPage(new BukaBantuanWebviewPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void expandCategory(String category) {
        verifyElementExist(constructLocator("bb_lihat_selengkapnya_category", category));
        tapElement(constructLocator("bb_lihat_selengkapnya_category", category));

        //wait until DOM is stable
        delay(3000);
    }

    public void chooseSubCategory(String subcategory) {
        verifyElementExist(constructLocator("bb_subcategory_button", subcategory));
        tapElement(constructLocator("bb_subcategory_button", subcategory));
    }

    public void tapFirstTransaction() {
        verifyElementExist("bb_transaction_first_card");
        tapElement("bb_transaction_first_card");
    }

    public void validateArticlePage(String title) {
        verifyElementExist(constructLocator("bb_article_page_title", title));
        CSIData.setJenisMasalah(title);
    }

    public void submitLivechat() {
        String complaint = CSIData.getJenisMasalah();
        verifyElementExist("bb_livechat_button");
        tapElement("bb_livechat_button");
        verifyElementExist("bb_livechat_confirm_button");
        tapElement("bb_livechat_confirm_button");
        switch (complaint) {
            case "Pengembalian Dana Belum Masuk":
                submitRegularPrechat();
                break;
            case "Barang Sudah Dikirim Tapi Belum Sampai":
                submitChabotPreChat();
                break;
            default:
                break;
        }

    }

    private void submitRegularPrechat() {
        verifyElementExist("bb_prechat_message_field");
        typeValue("bb_prechat_message_field", "This is just a test");
        verifyElementExist("bb_livechat_confirm_button");
        tapElement("bb_livechat_confirm_button");
        verifyElementExist("bb_livechat_tnc_checkbox");
        tapElement("bb_livechat_tnc_checkbox");
        tapElement("bb_livechat_confirm_button");
        verifyElementExist("bb_livechat_confirm_mulai_button");
        tapElement("bb_livechat_confirm_mulai_button");
    }

    private void submitChabotPreChat() {
        verifyElementExist("bb_livechat_tnc_checkbox");
        tapElement("bb_livechat_tnc_checkbox");
        tapElement("bb_livechat_confirm_button");
        verifyElementExist("bb_livechat_confirm_mulai_button");
        tapElement("bb_livechat_confirm_mulai_button");
    }

    public void validateChatbotSession() {
        verifyElementExist("bb_livechat_bubble");
        verifyElementExist("bb_chatbot_message");
        verifyElementExist("bb_chatbot_option_wrapper");
    }

    public void chooseChatbotOption(String option) {
        verifyElementExist(constructLocator("bb_chatbot_option", option));
        tapElement(constructLocator("bb_chatbot_option", option));
    }

    public void validateLivechatResponse(String message) {
        verifyElementExist(constructLocator("bb_livechat_message_text", message));
    }

    public void endChatbotSession() {
        chooseChatbotOption("Ya, membantu");
        chooseChatbotOption("Akhiri percakapan");
    }

    public void validateScoringLivechatButton() {
        verifyElementExist("bb_livechat_scoring_button", 120, "livechat not ended");
    }

    public void searchTransaction(String transaction) {
        String searchInput = transaction;
        switch (transaction) {
            case "lastTransaction":
                searchInput = TransactionData.getNomorTransaksi();
                break;
            case "lastInvoice":
                searchInput = TransactionData.getInvoiceNo();
                break;
            case "noTagihan" :
                searchInput= CSIData.getNomorTagihan();
                break;
            case "noTransaksi":
                searchInput = CSIData.getNomorTransaksi();
                break;
            default:
                break;
        }
        verifyElementExist("bb_transactions_search_field");
        webViewTypeElementValue("bb_transactions_search_field", searchInput);
    }

    public void validateLivechatSession() {
        verifyElementExist(constructLocator("bb_livechat_message_text",
                "Saat ini kamu sedang berada di antrian live chat"), 30, "livechat not responding");
        verifyElementExist("bb_livechat_separator_text", 60, "agents not coming");
        verifyElementExist("bb_livechat_agent_message", 60, "agents not responding");
    }

    public void sendMessageLivechat(String message) {
        verifyElementExist("bb_livechat_message_input", 30, "message field can't be found");
        typeValue("bb_livechat_message_input", message);
        tapElement("bb_livechat_send_button");
        //wait for message received to agent
        delay(2000);
    }

    public void tapLastTransaction() {
        verifyElementExist(constructLocator("bb_transaction_card_number", TransactionData.getNomorTransaksi()));
        tapElement(constructLocator("bb_transaction_card_number", TransactionData.getNomorTransaksi()));
    }

    public void goToTransaction() {
        webViewSwipeToElement("bb_lihat_semua_button");
        tapElement("bb_lihat_semua_button");
    }

    public void validateTransactionCard() {
        verifyElementExist("bb_transaction_result_text");
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
        verifyElementExist("bb_transaction_result_text");
        validateDisplayed(constructLocator("bb_transaction_card_number", tmpKeyword));
    }

    public void filterTransaction(String keywords) {
        waitForVisibilityOf("bb_search_field");
        tapElement("bb_filter_button", 10);
        tapElement("bb_filter_status_menu", 10);
        tapElement(constructLocator("bb_filter_status_option", keywords), 10);
        tapElement("bb_filter_terapkan_button");
    }

    public void validateFilterTransaction(String keywords) {
        verifyElementExist("bb_transaction_result_text");
        String transactionStatus = getTextFromElement("bb_transaction_state_search");
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

    public void tapSoughtTransaction(String keyword) {
        verifyElementExist("bb_transaction_state_search");
        String transactionStatus = getTextFromElement("bb_transaction_state_search");
        switch (keyword){
            case "BELUM DIBAYAR" :
                assertTextContains("Belum dibayar", transactionStatus);
                break;
            case "KEDALUWARSA":
                assertTextContains("Dibatalkan (kedaluwarsa)", transactionStatus);
                break;
            case "DIBAYAR":
                assertTextContains("Dibayar", transactionStatus);
                break;
            case "DIKIRIM":
                assertTextContains("Dikirim", transactionStatus);
                break;
            default:
                break;
        }
        tapElement("bb_transaction_state_search");
    }

    public void userOnPilihMasalahPage() {
        if (iosDriver.getContext().contains("NATIVE_APP")) changeContext().toWebview();
        verifyElementExist("bb_pilih_masalah_text");
        changeContext().toNative();
    }

    public void tapProblem(String keyword) {
        if (iosDriver.getContext().contains("NATIVE_APP")) changeContext().toWebview();
        verifyElementExist(constructLocator("bb_kategori_masalah", keyword));
        tapElement(constructLocator("bb_kategori_masalah", keyword));
        verifyElementExist(constructLocator("bb_article_page_title", keyword));
        CSIData.setJenisMasalah(keyword);
    }

    public void tapFormBantuan() {
        waitForVisibilityOf("bb_isi_form_bantuan_link", 30);
        tapElement("bb_isi_form_bantuan_link");
    }

    public void userOnFormBantuanPage() {
        verifyElementExist(constructLocator("bb_form_title", CSIData.getJenisMasalah()));
    }

    public void chooseProblemForm(String keyword) {
        if (!iosDriver.getContext().contains("NATIVE_APP")) changeContext().toNative(); //method selectPickerWheel only exists on the native page
        waitForVisibilityOf("bukabantuan_form_problem_field", 30);
        tapElement("bukabantuan_form_problem_field");
        selectPickerWheel(keyword);
        tapElement("bukabantuan_form_pickerwheel_done_button");
    }

    public void userOnSuccessSubmitPage() {
        if (iosDriver.getContext().contains("NATIVE_APP")) changeContext().toWebview();
        verifyElementExist("bb_sukses_kirim_title");
        verifyElementExist("bb_sukses_kirim_text");
        HelperData.setLastActionPage(new BukaBantuanWebviewPage(iosDriver));
        changeContext().toNative();
    }

    public void scrollAndClickCategory(String keyword) {
        tapElement(constructLocator("bb_category_text", keyword));
        CSIData.setJenisMasalah(keyword);
    }

    public void tapSubCategory(String keyword) {
        verifyElementExist(constructLocator("bb_subcategory_button", keyword));
        tapElement(constructLocator("bb_subcategory_button", keyword));
        CSIData.setJenisMasalah(keyword);
    }

    public void makeComplaintForm(String keyword) {
        chooseProblemForm(keyword);
        swipeUpToElement("bukabantuan_kirim_button");
        tapElement("bukabantuan_form_tnc_checkbox");
        tapElement("bukabantuan_kirim_button");
    }
}
