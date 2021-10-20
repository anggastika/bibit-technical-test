package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by koryanggraeni on 10/03/2020.
 */

public class SerbuSeruPage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(SerbuSeruPage.class);

    public SerbuSeruPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isOnSerbuSeruPage() {
        if (isElementExist("serbu_seru_saya_mengerti_button", 20)) {
            tapElement("serbu_seru_saya_mengerti_button");
        }
        validateExist("serbu_seru_history_icon", 15);
        validateExist("serbu_seru_main_back_button", 15);
        validateExist("serbu_seru_main_banner", 15);
        validateExist("serbu_seru_serbu_sekarang_tab", 15);
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void validateFilterSerbuSeruButton(String filter) {
        waitForVisibilityOf(constructLocator("serbuseru_filter_button_label", filter), 15);
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void tapOnEligibleProductForVA() {
        int elementIndex = 1;
        boolean isEligibleForVA = false;
        boolean hasExploredAll = false;
        int swipeCount = 1 ;
        while (!isEligibleForVA && !hasExploredAll && swipeCount <= 6) {
            if (!isElementVisible(constructLocator("serbu_seru_product_list_price_label", Integer.toString(elementIndex)), 20)){
                swipeUp(0.7, 0.1);
                elementIndex = 1;
                swipeCount++;
            } else {
                waitForVisibilityOf(constructLocator("serbu_seru_product_list_price_label", Integer.toString(elementIndex)), 15);
                if (isElementExist(constructLocator("serbu_seru_product_list_price_label", Integer.toString(elementIndex)), 15)) {
                    int price = getIntFromRp(getTextFromElement(constructLocator("serbu_seru_product_list_price_label", Integer.toString(elementIndex))));
                    if (price >= 10_000) {
                        isEligibleForVA = true;
                    }

                    elementIndex++;
                } else {
                    hasExploredAll = true;
                }
            }
        }
        tapElement(constructLocator("serbu_seru_product_list_cell", Integer.toString(elementIndex)));

        if (isElementExist("serbu_seru_lanjut_serbu_popup_button", 10)) {
            tapElement("serbu_seru_lanjut_serbu_popup_button");
        }
        if (isElementExist("serbu_seru_second_reward_popup_button",10)){
            tapElement("serbu_seru_second_reward_popup_button");
        }

        verifyElementExist("serbu_seru_serbu_button");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void serbuProductOnProductDetailPageUsingVA() {
        tapElement("serbu_seru_payment_method_dropdown_button");
        tapElement("serbu_seru_virtual_account_label");
        tapElement("serbu_seru_bca_label");

        if (isElementExist("serbu_seru_va_payment_serbu_button", 15)) {
            tapElement("serbu_seru_va_payment_serbu_button");
        }

        if (isElementExist("serbu_seru_bayar_button", 15)) {
            tapElement("serbu_seru_bayar_button");
        }

        if (isElementExist("serbu_seru_lanjutkan_pembayaran_button", 15)) {
            tapElement("serbu_seru_lanjutkan_pembayaran_button");
        }
        if (isElementExist("serbu_seru_confirm_second_reward_button",10)) {
            tapElement("serbu_seru_confirm_second_reward_button");
        }

        if (isElementExist("serbu_seru_va_sudah_digunakan_title_txt", 15)) {
            tapElement("serbu_seru_va_lanjutkan_pembayaran_button");
        }

        if (isElementExist("serbu_seru_setuju_button", 15)) {
            tapElement("serbu_seru_setuju_button");
        }
    }

    public void serbuHasSucceeded() {
        verifyElementExist("serbu_seru_detail_tagihan_button");
    }

    public void tapLihatDetailTagihan() {
        if (isElementExist("serbu_seru_detail_tagihan_button", 15)) {
            tapElement("serbu_seru_detail_tagihan_button");
        } else if (isElementVisible("serbu_seru_detail_tagihan_button_alt")) {
            tapElement("serbu_seru_detail_tagihan_button_alt");
        }
    }

    public void isOnSerbuSeruTransactionDetailPage() {
        verifyElementExist("serbu_seru_detail_pembelian_title");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void tapSerbuButton() {
        swipeUpToElement("serbu_seru_first_serbu_button", 5);
        tapElement("serbu_seru_first_serbu_button");

        if (isElementExist("serbu_seru_ada_yang_baru_sheet", 15)) {
            tapElement("serbu_seru_lanjut_serbu_button");
        }

        verifyElementExist("serbu_seru_serbu_button");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void switchNotifToOn() {
        tapElement("serbuseru_notif_icon");
        if (isElementExist("serbuseru_notif_snackbar_on_txt", 5)) {
            tapElement("serbuseru_notif_icon");
        } else {
            LOGGER.info("Notification state is Off");
        }
        tapElement("serbuseru_notif_icon");
    }

    public void switchNotifToOff() {
        tapElement("serbuseru_notif_icon");
        if (isElementExist("serbuseru_notif_snackbar_off_txt", 5)) {
            tapElement("serbuseru_notif_icon");
        } else {
            LOGGER.info("Notification state is On");
        }
        tapElement("serbuseru_notif_icon");
    }

    public void swicthRemindMeIcon(String state) {
        switch (state) {
            case "on":
                switchNotifToOn();
                break;
            case "off":
                switchNotifToOff();
                break;
            default:
                LOGGER.info("Sorry, you made an invalid choice.");
                break;
        }
    }

    public void validateSnacbarRemindMe(String state) {
        switch (state) {
            case "on":
                validateDisplayed("serbuseru_notif_snackbar_on_txt");
                break;
            case "off":
                validateDisplayed("serbuseru_notif_snackbar_off_txt");
                break;
            default:
                LOGGER.info("Sorry, you made an invalid choice.");
                break;
        }
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void tapHistoryIcon() {
        tapElement("serbu_seru_history_icon");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void validateMelesetOnHistory() {
        int historyCardNumber = 1;
        while (!isElementExist("serbu_seru_missed_title", 5) && historyCardNumber <= 30) {
            if (isElementExist("base_back_button", 10)) {
                tapElement("base_back_button");
            }
            swipeUpToElement(constructLocator("serbu_seru_card_history", historyCardNumber));
            tapElement(constructLocator("serbu_seru_card_history", historyCardNumber));
            historyCardNumber++;
        }
        validateExist("serbu_seru_penyerbu_terpilih_panel_txt", 15);
        validateExist("serbu_seru_penyerbu_terpilih_name_txt", 15);
        validateExist("serbu_seru_check_refund_type_button", 15);
        validateExist("serbu_seru_coba_lagi_button", 15);
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void tapFilterRefund(String filter) {
        tapElement(constructLocator("serbuseru_filter_button_label", filter));
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void validateInfoRefundToDonasi(){
        validateDisplayed("serbu_seru_info_refund_to_donasi_label", "Information Donation Not Exist");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void validateInfoRefundToDANASaldo(){
        validateDisplayed("serbu_seru_info_refund_to_saldo_label", "Information Refund Saldo Not Exist");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void validateInformationProductPdp() {
        if (isElementExist("serbu_seru_second_reward_popup_button", 10)){
            tapElement("serbu_seru_second_reward_popup_button");
        }
        validateExist("serbu_seru_image_product_pdp", 30);
        validateDisplayed("serbu_seru_image_product_pdp");
        validateDisplayed("serbu_seru_name_product_pdp");
        validateDisplayed("serbu_seru_price_label_product");
        validateDisplayed("serbu_seru_original_price_label_product");
        validateDisplayed("serbu_seru_highlight_info_default_left");
        validateDisplayed("serbu_seru_highlight_info_default_right");
        validateExist("serbu_seru_daftar_penyerbu_txt");
        validateExist("serbu_seru_informasi_barang_pdp_txt");
        validateExist("serbu_seru_penyedia_barang_pdp_txt");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void validateFaqSerbuSeru() {
        swipeUpToElement("serbu_seru_faq_label", 5);
        validateDisplayed("serbu_seru_faq_label");
        tapElement("serbu_seru_faq_label");
        validateExist("serbu_seru_header_popup_faq_label", 20);
        tapElement("serbu_seru_close_sheet_faq");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void selectPaymentMethod(){
        tapElement("serbu_seru_select_payment_method");
    }

    public void tapAktifkanButton() {
        if (isElementVisible("serbu_seru_topup_dana_button",5)) {
            tapElement("serbu_seru_topup_dana_button");
        } else {
            tapElement("serbu_seru_aktifkan_dana_button");
        }
    }

    private String getSnackbarText() {
        return getText("serbu_seru_dana_freeze_snackbar_txt");
    }

    public void validateInfoDANAFreeze() {
        validateExist("serbu_seru_dana_freeze_snackbar_txt", 3);
        validateValue().contains("DANA kamu sedang dibekukan.", getSnackbarText(), "invalid message");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void tapSerbuButtonOnPDP() {
        validateExist("serbu_seru_serbu_button", 15);
        tapElement("serbu_seru_serbu_button");
    }

    public void validateConsentPopupDonasi() {
        validateExist("serbu_seru_donasi_consent_popup_header_txt", 15);
        validateDisplayed(constructLocator("serbu_seru_donasi_consent_popup_option", 1));
        validateDisplayed(constructLocator("serbu_seru_donasi_consent_popup_option", 2));
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void validateOptionSaldoDana(String option) {
        validateExist(constructLocator("serbu_seru_donasi_consent_popup_radio_button", 2), 15);
        tapElement(constructLocator("serbu_seru_donasi_consent_popup_radio_button", 2));
        switch (option){
            case "enabled" :
                validateValue().equals("true", getElementAttributeValue(constructLocator("serbu_seru_donasi_consent_popup_radio_button", 2), "value"));
                break;
            case "disabled":
                validateValue().equals("false", getElementAttributeValue(constructLocator("serbu_seru_donasi_consent_popup_radio_button", 2), "value"));
                break;
            default:
                LOGGER.info("Sorry, you made an invalid choice.");
                break;
        }
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void typeBidSerbuSeru(String bid) {
        tapElement("serbu_seru_bid_number_field");
        typeAndEnterValue("serbu_seru_bid_number_field", bid);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapSerbuGratisReferralButton() {
        validateExist("serbu_seru_referral_button", 15);
        tapElement("serbu_seru_referral_button");
    }

    public void tapTopUpDANAButton() {
        waitForElementClickable("serbu_seru_topup_dana_button", 15);
        tapElement("serbu_seru_topup_dana_button");
    }

    public void validatePendingOnHistory() {
        int historyCardNumber = 1;
        while (!isElementExist("base_back_button", 5) && historyCardNumber <= 10) {
            if (isElementExist("serbu_seru_missed_title", 10)) {
                tapElement("serbu_seru_sheet_close_button");
            }
            swipeUpToElement(constructLocator("serbu_seru_card_history", historyCardNumber));
            tapElement(constructLocator("serbu_seru_card_history", historyCardNumber));
            historyCardNumber++;
        }
        verifyElementExist("invoice_detail_pending_status");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void tapSetujuAndLanjutkanButton() {
        validateElementVisible("serbu_seru_setuju_button");
        tapElement("serbu_seru_setuju_button");
        }

    public void verifySuccessfulPaidSerbuSeruDANA() {
        validateElementVisible("serbu_seru_informasi_tagihan_text");
        tapElement("serbu_seru_informasi_tagihan_text");
        validateElementVisible("serbu_seru_informasi_tagihan_status");
        validateElementVisible("serbu_seru_informasi_tagihan_dana");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }
}
