package bukalapak.pageObject;

import bukalapak.data.PostpaidData;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.time.Duration;

public class TopupDanaPage extends BasePage {

    public TopupDanaPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void isOnTopupDanaPage(){
        verifyElementExist("topup_dana_vp_screen_title");
        waitForVisibilityOf("topup_dana_vp_phone_masking_text",10);
        PostpaidData.setPhoneNumeber(getElementValue("topup_dana_vp_phone_masking_text"));
        HelperData.setLastActionPage(new TopupDanaPage(iosDriver));
    }

    public void setNominalTopup(){
        typeAndEnterValueWithTimeOut("topup_dana_vp_nominal_field", "30000");
        PostpaidData.setNominal(getElementValue("topup_dana_vp_nominal_field"));
    }

    public void tapOnTopupButton(){
        tapElement("topup_dana_vp_button_topup");
    }

    public void verifyCheckoutTopupDana(){
        assertEquals(PostpaidData.getPhoneNumber(), getElementValue("topup_dana_vp_invoice_phone_masking_text"));
        assertEquals(getElementValue("topup_dana_vp_invoice_nominal_text"), "Rp"+PostpaidData.getNominal());
    }

    public void activatedPopUpDisplayed(){
        assertTrue(isElementVisible("topup_dana_vp_popup_nonaktif"));
        HelperData.setLastActionPage(new TopupDanaPage(iosDriver));
    }

    public void tapOnTransactionListButton(){
        tapElement("topup_dana_vp_transaction_list_button");
        verifyElementExist("topup_dana_vp_menunggu_pembayaran_button");
    }

    public void verifyLastTopupDana(){
        tapElement("topup_dana_vp_menunggu_pembayaran_button");
        assertEquals(getElementValue("topup_dana_vp_list_transaction_state"), "Menunggu Pembayaran");
        assertEquals(getElementValue("topup_dana_vp_list_transaction_nominal_text"), "Top Up Rp"+PostpaidData.getNominal());
        HelperData.setLastActionPage(new TopupDanaPage(iosDriver));
    }

    public void repurchaseTopupDana(){
        tapElement("topup_dana_vp_detail_button");
        verifyElementExist("topup_dana_vp_topup_lagi_button");
        tapElement("topup_dana_vp_topup_lagi_button");
    }

    public void verifySuccessBindTopUpDANAVPPage() {
        verifyElementExist("dana_checkout_bind_success_text");
        HelperData.setLastActionPage(new TopupDanaPage(iosDriver));
    }

    public void inputTopupAmount(String amount) {
        waitForVisibilityOf("topup_dana_vp_amount_field");
        typeAndEnterValueWithTimeOut("topup_dana_vp_amount_field", amount);
    }

    public void chooseTopupAmount(String amount) {
        validateDisplayed("topup_dana_vp_amount_field");
        if(amount.equalsIgnoreCase("history")){
            validateDisplayed("topup_dana_vp_last_denom_section");
            tapElement("topup_dana_vp_last_denom_button");
        } else {
            tapElement(constructLocator("topup_dana_vp_choose_denom_button", amount));
        }
    }

    public void tapBayarTopUpDANAVp(){
        swipeUpToElement("topup_dana_vp_lanjut_bayar_button");
        tapElement("topup_dana_vp_lanjut_bayar_button");
        HelperData.setLastActionPage(new TopupDanaPage(iosDriver));
    }

    public void validateVANumber(String number) {
        validateDisplayed(constructLocator("topup_dana_vp_va_number_txt", number));
    }

    public void validateExceededDANATopupDisable(){
        waitForVisibilityOf("topup_dana_vp_error_msg", 20);
        validateDANATopUpBayarBtnDisable();
        HelperData.setLastActionPage(new TopupDanaPage(iosDriver));
    }

    public void validateMinimumDANATopupDisable(){
        waitForVisibilityOf("topup_dana_vp_error_min_topup_msg", 20);
        validateDANATopUpBayarBtnDisable();
        HelperData.setLastActionPage(new TopupDanaPage(iosDriver));
    }

    private void validateDANATopUpBayarBtnDisable(){
        validateValue().equals(false, isElementClickable("topup_dana_vp_lanjut_bayar_button"));
    }

    public void tapAktifkanSekarang(){
        waitForElementClickable("topup_dana_vp_aktifkan_button", 15);
        tapElement("topup_dana_vp_aktifkan_button");
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void noHistoryTopup() {
        verifyElementNotExist("topup_dana_vp_last_denom_section");
        tapElement("topup_dana_vp_transaction_list_button");
        verifyElementExist("topup_dana_vp_empty_transaction");
    }

    public void validateTopupBreakerPopup() {
        if (isElementVisible("topup_breaker_header")) {
            LogUtil.info("get element by xpath");
        } else if (isElementVisible("topup_breaker_header_vp")) {
            LogUtil.info("get element by name");
        } else {
            waitForVisibilityOf("topup_breaker_header_other");
        }
        String[] element = {"topup_breaker_desc", "topup_breaker_timer", "topup_breaker_red_btn"};
        verifyElementListExist(element);
    }

    public void closeTopupBreakerPopup() {
        if (isElementVisible("topup_breaker_close_btn", 5)) {
            tapElement("topup_breaker_close_btn");
        } else { //tap grey area
            tapElement(60, 60, 1);
        }
        verifyElementNotExist("topup_breaker_desc");
        HelperData.setLastActionPage(new TopupDanaPage(iosDriver));
    }

    /**
     * Top Up DANA for freeze BukaDompet
     */
    public void verifySaldoFreezeTopupBreaker(){
        validateDisplayed("topup_breaker_saldo_freeze_popup_title");
        validateDisplayed("topup_breaker_saldo_freeze_popup_desc");
        validateDisplayed("topup_breaker_saldo_freeze_popup_bukabantuan_btn");
        validateDisplayed("topup_breaker_saldo_freeze_popup_cancel_btn");
    }

    public void tapHubungiBukabantuanSaldoFreezeBreaker(){
        tapElement("topup_breaker_saldo_freeze_popup_bukabantuan_btn");
        waitForVisibilityOf("topup_breaker_saldo_freeze_bukabantuan_title", 20);
    }

    public void tapKembaliSaldoFreezeBreaker(){
        tapElement("topup_breaker_saldo_freeze_popup_cancel_btn");
    }
}
