package bukalapak.pageObject;

import bukalapak.data.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.util.Objects;

import static bukalapak.TestInstrument.dotenv;

/**
 * Created by Ferawati.
 */
public class SaldoBukalapakPage extends BasePage {

    public SaldoBukalapakPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateUserIsInSaldoPage() {
        verifyElementExist("saldo_title");
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void tapBukaDanaTopUp() {
        if (!isElementVisible("saldo_tambah_dana_button")) {
            tapTambahButton();
        }
        tapTambahDanaButton();
    }

    public void checkCashbackPriorityBuyer() {
        verifyElementExist("saldo_cashback_priority_text");
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    // Setter Getter for Data purpose
    public int getSaldoBukaDompet() {
        int totalSaldo;
        waitForVisibilityOf("saldo_tambah_button");
        if (isElementVisible("saldo_total_bukadompet_text", 3)) {
            totalSaldo = getIntegerFromValueElement("saldo_total_bukadompet_text");
        } else {
            waitForVisibilityOf("saldo_total_bukadompet_new_text", 60);
            totalSaldo = getIntFromRp(getTextFromElement("saldo_total_bukadompet_new_text"));
        }
        return totalSaldo;
    }

    public void checkSaldoBukaDompet(String state, String type, int saldoBukaDompet) {
        if (state.equalsIgnoreCase("before")) {
            SaldoBukalapakData.setSaldoBukaDompet(saldoBukaDompet);
        } else {
            validateSaldoBukaDompetAfterAction(type);
        }
    }

    public void validateSaldoBukaDompetAfterAction(String type) {
        switch (type.toLowerCase()) {
            case "priority buyer":
                checkPriorityBuyerTransaction(type);
                break;
            case "add promoted budget one time":
                validateSaldoAfterPurchasePromotedBudget();
                break;
            case "purchase bukareksa product":
                validateSaldoAfterPurchaseBukaReksa();
                break;
            case "redeem bukareksa product":
                validateSaldoAfterRedeemBukaReksa();
                break;
            default:
                Assert.fail("Invalid action type!");
                break;
        }
    }

    private void validateSaldoAfterPurchasePromotedBudget() {
        assertEquals(getSaldoBukaDompet(), (PROMData.getSaldoKamu() - PROMData.getInputtedBudget()), "Saldo is not reduced!");
        HelperData.setLastActionPage(new PromotedKeywordPage(iosDriver));
    }

    public void validateSaldoAfterPurchaseBukaReksa() {
        assertEquals(getSaldoBukaDompet(), SaldoBukalapakData.getSaldoBukadompet() - parseIntegerFromText(InvestmentData.getNominalPurchase()),
                "Saldo seharusnya berkurang");
    }

    public void validateSaldoAfterRedeemBukaReksa() {
        assertEquals(getSaldoBukaDompet(), SaldoBukalapakData.getSaldoBukadompet() + parseIntegerFromText(InvestmentData.getNominalRedemption()),
                "Saldo seharusnya bertambah");
    }

    private void checkPriorityBuyerTransaction(String transaction) {
        String goldPrice = dotenv.get("GOLD_PRICE");
        String silverPrice = dotenv.get("SILVER_PRICE");
        if (transaction.toLowerCase().contains("gold")) {
            assertEquals(getSaldoBukaDompet(), (SaldoBukalapakData.getSaldoBukadompet() - Integer.parseInt(Objects.requireNonNull(goldPrice))), "Saldo is not reduced!");
        } else if (transaction.toLowerCase().contains("silver")) {
            assertEquals(getSaldoBukaDompet(), (SaldoBukalapakData.getSaldoBukadompet() - Integer.parseInt(Objects.requireNonNull(silverPrice))), "Saldo is not reduced!");
        }
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void verifyBDFrozen() {
        verifyElementExist("saldo_bukalapak_bd_frozen");
    }

    public void tapPindahkanSaldo() {
        if (isElementVisible("saldo_pindahkan_button", 10)) {
            tapElement("saldo_pindahkan_button");
        } else {
            tapElement("saldo_back_button");
            isElementVisible("account_lihat_detail_text", 15);
            tapElement("account_lihat_detail_text");
            isElementVisible("saldo_pindahkan_button", 15);
            tapElement("saldo_pindahkan_button");
        }
    }

    public void disabledTopup() {
        isElementVisible("saldo_pindahkan_modal_button_disabled", 10);
        isElementClickable("saldo_pindahkan_modal_button_disabled");
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void tapSaldoBukaDompetTab() {
        if (isElementVisible("saldo_mutasi_tab")) {
            tapElement("saldo_mutasi_tab");
        } else {
            tapElement("saldo_bukadompet_tab");
        }
    }

    public void swipeToSaldoBukaDompetTab() {
        swipeLeftAtSpecifiedLocator("saldo_cell_empty_tab");
    }

    public void inputTopupBDAmount(String amount) {
        verifyElementExist("saldo_pindahkan_nominal_field");
        typeAndEnterValueWithTimeOut("saldo_pindahkan_nominal_field", amount);
    }

    public void tapConfirmTopUpBD() {
        if (isElementExist("saldo_pindahkan_konfirmasi_button", 5)) {
            tapElement("saldo_pindahkan_konfirmasi_button");
        } else {
            verifyElementExist("saldo_pindahkan_konfirmasi_after_bind_button");
            tapElement("saldo_pindahkan_konfirmasi_after_bind_button");
        }
    }

    public void verifyCannotTopUpFrozenBD() {
        waitForVisibilityOf("saldo_pindahkan_error_frozen_bukadompet", 5);
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void validateTopupDANAErrorMessage(String message) {
        switch (message) {
            case "insufficient":
                validateDisplayed("saldo_pindahkan_error_insufficient_balance");
                break;
            case "max":
                validateDisplayed("saldo_pindahkan_error_max_topup_limit");
                break;
            case "min":
                validateDisplayed("saldo_pindahkan_error_min_topup_limit");
                break;
            default:
                Assert.fail("Invalid Error Message");
                break;
        }
        validateValue().equals(false, isElementClickable("saldo_pindahkan_konfirmasi_button"));
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void tapBackToAkunPage() {
        tapElement("saldo_back_button");
    }

    public void tapOnInvestasiBtn() {
        tapElement("SALDO_INVESTASI_BUTTON", 10);
        /*only run if the first tap by xpath location not running properly due to
                 element covered by other hidden element*/
        if (!isElementExist("INVESTASI_DI_BUKAREKSA_HEADER", 5)) {
            tapElement(99,155);
        }
    }

    public void tapSuperSellerMutationLink() {
        waitForVisibilityOf("saldo_bukalapak_mutation_super_seller_link", 50);
        IOSElement element = (IOSElement) getElement("saldo_bukalapak_mutation_super_seller_link");
        int axis_x = element.getLocation().x;
        int axis_y = element.getLocation().y;
        int width = element.getSize().width;
        int height = element.getSize().height;
        tapElement(axis_x + (int) (width * 0.5), axis_y + (int) (height * 0.8), 0);
    }

    public void verifyInvestasikuSectionDisplayed() {
        validateExist("REXA_INVESTASIKU_SECTION", 15);
        verifyElementExist("REXA_INVESTASIKU_BALANCE");
        InvestmentData.setProductPortofolioValue(getText("REXA_INVESTASIKU_BALANCE"));
        verifyElementExist("REXA_INVESTASIKU_CHEVRON_BTN");
        if (!isElementExist("REXA_INVESTASIKU_INFO", 10)) {
            verifyElementExist("REXA_INVESTASIKU_NONINVESTOR_INFO");
        } else {
            verifyElementExist("REXA_INVESTASIKU_INFO");
        }
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void tapOnInvestasikuSection() {
        tapElement("REXA_INVESTASIKU_CHEVRON_BTN");
    }

    public void validateDANABalanceFrozen() {
        verifyElementDisplayed("saldo_dana_error_frozen_label");
    }

    public void tapTambahButton() {
        waitForElementClickable("saldo_tambah_button", 20);
        tapElement("saldo_tambah_button");
    }

    public void tapCairkanButton() {
        waitForElementClickable("saldo_cairkan_button", 15);
        tapElement("saldo_cairkan_button");
    }

    public void tapTambahDanaButton(){
        tapElement("saldo_tambah_dana_button");
    }

    public void validateDANAFrozen(String type) {
        if (type.equalsIgnoreCase("tambah")) {
            tapTambahButton();
        } else {
            tapCairkanButton();
        }
        verifyElementDisplayed("saldo_dana_popup_close_button");
        validateValue().contains("DANA kamu sedang dibekukan", getElementValue("saldo_dana_error_frozen_label"), "Invalid message");
        tapElement("saldo_dana_popup_close_button");
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyDANADifferentPNPopupDispayed(){
        validateExist("saldo_dana_popup_title", 15);
        validateExist("saldo_dana_popup_description");
        validateExist("saldo_dana_popup_yes_button");
        validateExist("saldo_dana_popup_change_dana_button");
    }

    public void tapChangeDANABtn(){
        tapElement("saldo_dana_popup_change_dana_button");
    }

    public void verifyChangeDANAPage(){
        validateExist("saldo_dana_popup_change_dana_button_title", 15);
        validateExist("saldo_dana_popup_change_dana_unbind_button");
    }

    public void tapPutuskanAkunBtn(){
        tapElement("saldo_dana_popup_change_dana_unbind_button");
    }

    public void verifyPutuskanAkunPopup(){
        validateExist("saldo_dana_popup_unbind_info_title", 15);
        validateExist("saldo_dana_popup_unbind_info_desciption");
        validateExist("saldo_dana_popup_unbind_info_bukabantuan_button");
        validateExist("saldo_dana_popup_unbind_info_batal_button");
    }

    public void tapBatalBtn(){
        tapElement("saldo_dana_popup_unbind_info_batal_button");
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void verifyNoPindahkanSaldoBtn(){
        verifyElementNotExist("saldo_pindahkan_button");
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void tapDetailButton() {
        validateDisplayed("saldo_credits_tab");
        waitForVisibilityOf("saldo_detail_credits_mutation_button");
        tapElement("saldo_detail_credits_mutation_button");
    }

    public void validateDetailButtonNotShow() {
        validateDisplayed("saldo_credits_tab");
        verifyElementNotExist("saldo_detail_credits_mutation_button");
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void validateDANABindingAndCreditsTabShow() {
        verifyElementDisplayed("saldo_bind_button");
        tapElement("saldo_credits_tab");
    }

    public void validateDANABindingCreditsShow() {
        validateDisplayed("saldo_bind_bukacredits_button");
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void validateDANABindingCreditsNotShow() {
        verifyElementNotExist("saldo_bind_bukacredits_button");
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void tapMiniDANAButton() {
        tapElement("saldo_mini_dana_button");
    }

    public void verifyOnDANATab() {
        verifyElementExist("saldo_dana_tab");
        isElementSelected("saldo_dana_tab");
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void verifyOnBukaDompetTab() {
        if (isElementVisible("saldo_bukadompet_tab")) {
            isElementSelected("saldo_bukadompet_tab");
        } else {
            isElementSelected("saldo_mutasi_tab");
        }
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void verifyBukaDompetMutation() {
        waitForVisibilityOf("saldo_mutasi_plus",15);
        validateValue().equals(("+" + TransactionData.getTotalPrice()), getElementValue("saldo_mutasi_plus"));
        validateValue().equals(("-" + TransactionData.getTotalPrice()), getElementValue("saldo_mutasi_minus"));
    }

    public void verifyPopUpVerificationPhoneSaldoBukalapak() {
        verifyElementExist("saldo_pop_up_verification_phone");
        verifyElementExist("saldo_label_pop_up_verification_phone");
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    public void verifyUpgradeAkunDANApopup() {
        waitForVisibilityOf("saldo_dana_cashout_upgrade_header");
        verifyElementExist("saldo_dana_cashout_upgrade_desc");
        verifyElementExist("saldo_dana_cashout_upgrade_banner");
        verifyElementExist("saldo_dana_cashout_close");
        tapElement("saldo_dana_cashout_upgrade_btn");
    }

    public void verifyBukaAplikasiDANApopup() {
        waitForVisibilityOf("saldo_dana_cashout_popup_header");
        verifyElementExist("saldo_dana_cashout_popup_desc");
        verifyElementExist("saldo_dana_cashout_popup_btn");
        tapElement("saldo_dana_cashout_close");
    }

    public void tapDanaCashout() {
        tapElement("saldo_cairkan_btn",10);
        waitForVisibilityOf("saldo_dana_cashout_txt",10);
        tapElement("saldo_dana_cashout_btn",10);
    }
}
