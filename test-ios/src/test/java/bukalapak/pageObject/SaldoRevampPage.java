package bukalapak.pageObject;

import bukalapak.data.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.util.Objects;

import static bukalapak.TestInstrument.dotenv;

public class SaldoRevampPage extends BasePage {

    public SaldoRevampPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    // General
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateUserIsInSaldoRevampPage() {
        verifyElementExist("saldo_revamp_title");
        HelperData.setLastActionPage(new SaldoRevampPage(iosDriver));
    }

    public void verifyOnCard(String card) {
        verifyElementExist("saldo_revamp_" + card.toLowerCase() + "_mutation_txt");
        HelperData.setLastActionPage(new SaldoRevampPage(iosDriver));
    }

    public void selectCard(String card) {
        validateUserIsInSaldoRevampPage();
        if (!isElementExist("saldo_revamp_" + card.toLowerCase() + "_mutation_txt",5)) {
            tapBackToAkunPage();
            tapElement("saldo_revamp_" + card.toLowerCase() + "_card",10);
        }
    }

    public void tapDompetTab() { tapElement("saldo_revamp_dompet_tab"); }

    public void tapBackToAkunPage() {
        tapElement("saldo_revamp_back_btn");
    }

    public void tapSuperSellerMutationLink() {
        tapElement("saldo_revamp_bukalapak_mutation_super_seller_link",10);
    }

    public void checkCashbackPriorityBuyer() {
        verifyElementExist("saldo_revamp_cashback_priority_text");
        HelperData.setLastActionPage(new SaldoRevampPage(iosDriver));
    }

    private void checkPriorityBuyerTransaction(String transaction) {
        String goldPrice = dotenv.get("GOLD_PRICE");
        String silverPrice = dotenv.get("SILVER_PRICE");

        if (transaction.toLowerCase().contains("gold")) {
            assertEquals(getSaldoBukaDompet(),
                    (SaldoBukalapakData.getSaldoBukadompet() - Integer.parseInt(Objects.requireNonNull(goldPrice))),
                    "Saldo is not reduced!");
        } else if (transaction.toLowerCase().contains("silver")) {
            assertEquals(getSaldoBukaDompet(),
                    (SaldoBukalapakData.getSaldoBukadompet() - Integer.parseInt(Objects.requireNonNull(silverPrice))),
                    "Saldo is not reduced!");
        }
        HelperData.setLastActionPage(new SaldoRevampPage(iosDriver));
    }

    private void validateSaldoAfterPurchasePromotedBudget() {
        assertEquals(getSaldoBukaDompet(), (PROMData.getSaldoKamu() - PROMData.getInputtedBudget()),
                "Saldo is not reduced!");
        HelperData.setLastActionPage(new PromotedKeywordPage(iosDriver));
    }

    public void verifyPopUpVerificationPhoneSaldoBukalapak() {
        verifyElementExist("saldo_revamp_pop_up_verification_phone");
        verifyElementExist("saldo_revamp_label_pop_up_verification_phone");
        HelperData.setLastActionPage(new SaldoRevampPage(iosDriver));
    }

    public int getSaldoBukaDompet() {
        selectCard("saldo");
        verifyElementExist("saldo_revamp_saldo_total_amount");
        return getIntegerFromValueElement("saldo_revamp_saldo_total_amount");
    }

    // DANA
    public void tapMiniDANAButton() {
        waitForVisibilityOf("saldo_revamp_dana_total_amount");
        tapElement("saldo_revamp_mini_dana_button",10);
    }

    public void tapDanaTopUp() {
        tapElement("saldo_revamp_dana_topup_btn",10);
    }

    public void tapPindahkanSaldoToDana() {
        waitForVisibilityOf("saldo_revamp_saldo_total_amount");
        tapElement("saldo_revamp_saldo_pindahkan_btn");
        if (!isElementVisible("saldo_revamp_saldo_pindahkan_to_dana",5)) {
            tapElement(172,337); //need to use coordinates, if can't tap by element
        }
        tapElement("saldo_revamp_saldo_pindahkan_to_dana",5);
    }

    public void disabledTopup() {
        verifyElementExist("saldo_revamp_pindahkan_modal_button_disabled");
        HelperData.setLastActionPage(new SaldoRevampPage(iosDriver));
    }

    public void inputTopupBDAmount(String amount) {
        waitForVisibilityOf("saldo_revamp_pindahkan_nominal_field", 10);
        typeAndEnterValueWithTimeOut("saldo_revamp_pindahkan_nominal_field", amount);
    }

    public void tapConfirmTopUpBD() {
        if (isElementExist("saldo_revamp_pindahkan_konfirmasi_button", 5)) {
            tapElement("saldo_revamp_pindahkan_konfirmasi_button");
        } else {
            waitForVisibilityOf("saldo_revamp_pindahkan_konfirmasi_after_bind_button",10);
            tapElement("saldo_revamp_pindahkan_konfirmasi_after_bind_button");
        }
    }

    public void verifyCannotTopUpFrozenBD() {
        verifyElementExist("saldo_revamp_pindahkan_modal_button_disabled");
        HelperData.setLastActionPage(new SaldoRevampPage(iosDriver));
    }

    public void validateTopupDANAErrorMessage(String message) {
        switch (message) {
            case "insufficient":
                validateDisplayed("saldo_revamp_pindahkan_error_insufficient_balance");
                break;
            case "max":
                validateDisplayed("saldo_revamp_pindahkan_error_max_topup_limit");
                break;
            case "min":
                validateDisplayed("saldo_revamp_pindahkan_error_min_topup_limit");
                break;
            default:
                Assert.fail("Invalid Error Message");
                break;
        }
        validateValue().equals(false, isElementClickable("saldo_revamp_pindahkan_konfirmasi_button"));
        HelperData.setLastActionPage(new SaldoRevampPage(iosDriver));
    }

    public void verifyDANADifferentPNPopupDispayed(){
        verifyElementExist("saldo_revamp_dana_popup_title");
        verifyElementExist("saldo_revamp_dana_popup_description");
        verifyElementExist("saldo_revamp_dana_popup_yes_button");
        verifyElementExist("saldo_revamp_dana_popup_change_dana_button");
    }

    public void tapChangeDANABtn(){
        tapElement("saldo_revamp_dana_popup_change_dana_button");
    }

    public void verifyChangeDANAPage(){
        verifyElementExist("saldo_revamp_dana_popup_change_dana_button_title");
        verifyElementExist("saldo_revamp_dana_popup_change_dana_unbind_button");
    }

    public void tapPutuskanAkunBtn(){
        tapElement("saldo_revamp_dana_popup_change_dana_unbind_button");
    }

    public void verifyPutuskanAkunPopup(){
        verifyElementExist("saldo_revamp_dana_popup_unbind_info_title");
        verifyElementExist("saldo_revamp_dana_popup_unbind_info_desciption");
        verifyElementExist("saldo_revamp_dana_popup_unbind_info_bukabantuan_button");
        verifyElementExist("saldo_revamp_dana_popup_unbind_info_batal_button");
    }

    public void tapBatalBtn(){
        tapElement("saldo_dana_popup_unbind_info_batal_button");
        HelperData.setLastActionPage(new SaldoRevampPage(iosDriver));
    }

    public void validateDANABalanceFrozen() {
        verifyElementExist("saldo_revamp_dana_frozen_status");
        verifyElementExist("saldo_revamp_dana_error_frozen_label");
    }

    public void verifyUpgradeAkunDANApopup() {
        verifyElementExist("saldo_revamp_dana_cashout_upgrade_header");
        verifyElementExist("saldo_revamp_dana_cashout_upgrade_desc");
        verifyElementExist("saldo_revamp_dana_cashout_upgrade_banner");
        verifyElementExist("saldo_revamp_popup_close_btn");
    }

    public void tapUpgradeAkunDANApopup() {
        tapElement("saldo_revamp_dana_cashout_upgrade_btn");
    }

    public void verifyBukaAplikasiDANApopup() {
        verifyElementExist("saldo_revamp_dana_cashout_popup_header");
        verifyElementExist("saldo_revamp_dana_cashout_popup_desc");
        verifyElementExist("saldo_revamp_dana_cashout_buka_aplikasi_dana_btn");
    }

    public void tapBukaAplikasiDANApopup() {
        tapElement("saldo_revamp_popup_close_btn");
    }

    public void tapDanaCashout() {
        tapElement("saldo_revamp_dana_pindahkan_btn",10);
        tapElement("saldo_revamp_dana_pindahkan_to_rekening",10);
    }

    public void tapBindingAtDanaMutation() {
        waitForVisibilityOf("saldo_revamp_dana_hubungkan_dana_title");
        waitForVisibilityOf("saldo_revamp_dana_hubungkan_dana_promo_btn");
        waitForVisibilityOf("saldo_revamp_dana_hubungkan_dana_info_btn");
        tapElement("saldo_revamp_dana_hubungkan_dana_btn",10);
    }

    // Saldo BukaDompet
    public void checkSaldoBukaDompet(String state, String type) {
        if (state.equalsIgnoreCase("before")) {
            SaldoBukalapakData.setSaldoBukaDompet(getSaldoBukaDompet());
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

    public void verifyBDFrozen() {
        verifyElementExist("saldo_revamp_bukalapak_bd_frozen_at_dana");
        selectCard("saldo");
        verifyElementExist("saldo_revamp_bukalapak_bd_frozen");
        verifyElementExist("saldo_revamp_bukalapak_bd_frozen_detail");
    }

    public void verifyBukaDompetMutation() {
        verifyElementExist("saldo_revamp_saldo_plus_mutation");
        validateValue().equals(("+" + TransactionData.getTotalPrice()),
                getElementValue("saldo_revamp_saldo_plus_mutation"));
        validateValue().equals(("-" + TransactionData.getTotalPrice()),
                getElementValue("saldo_revamp_saldo_minus_mutation"));
    }

    // Credits
    public void tapDetailButton() {
        tapElement("saldo_revamp_detail_credits_btn");
    }

    public void validateDANABindingAndCreditsState(String dana, String credits) {
        switch (dana + "-" + credits) {
            case "bound-whitelisted":
                verifyElementExist("saldo_revamp_detail_credits_btn");
                verifyElementNotExist("saldo_revamp_credits_hubungkan_dana_btn");
                verifyElementNotExist("saldo_revamp_credits_unbind_dana_info");
                break;
            case "bound-blacklisted":
            case "unbound-blacklisted":
                verifyElementNotExist("saldo_revamp_detail_credits_btn");
                verifyElementNotExist("saldo_revamp_credits_hubungkan_dana_btn");
                verifyElementNotExist("saldo_revamp_credits_unbind_dana_info");
                break;
            case "unbound-whitelisted":
                verifyElementNotExist("saldo_revamp_detail_credits_btn");
                verifyElementExist("saldo_revamp_credits_hubungkan_dana_btn");
                verifyElementExist("saldo_revamp_credits_unbind_dana_info");
                break;
            default:
                Assert.fail(dana + "-" + credits + "is not defined!");
                break;
        }
    }

    public void tapBindingAtCreditsMutation() {
        waitForVisibilityOf("saldo_revamp_credits_unbind_dana_info");
        tapElement("saldo_revamp_credits_hubungkan_dana_btn",10);
    }

    public void validateBelanjakanBtn() {
        waitForVisibilityOf("saldo_revamp_credits_belanjakan_btn",10);
        tapElement("saldo_revamp_credits_belanjakan_btn");
        verifyElementExist("saldo_revamp_credits_pencarian_populer_txt");
    }

    public void validateCreditsBalance(int exp) {
        waitForVisibilityOf("saldo_revamp_credits_total_amount");
        int act = Integer.parseInt(getElementLabel("saldo_revamp_credits_total_amount").replaceAll("[Rp.]", ""));

        assertEquals(exp, act, "Credits are not match!");
    }

    // Investment
    public void validateSaldoAfterPurchaseBukaReksa() {
        assertEquals(getSaldoBukaDompet(),
                SaldoBukalapakData.getSaldoBukadompet() - parseIntegerFromText(InvestmentData.getNominalPurchase()),
                "Saldo seharusnya berkurang");
    }

    public void validateSaldoAfterRedeemBukaReksa() {
        assertEquals(getSaldoBukaDompet(),
                SaldoBukalapakData.getSaldoBukadompet() + parseIntegerFromText(InvestmentData.getNominalRedemption()),
                "Saldo seharusnya bertambah");
    }

    public void tapInvestasiBanner() { tapElement("saldo_revamp_investasi_banner"); }

    public void tapInvestasiTab() { tapElement("saldo_revamp_investasti_tab"); }

    public void validateUserIsInInvestasiTab() {
        verifyElementExist("saldo_revamp_invest_title");
        verifyElementExist("saldo_revamp_invest_product_title");
        verifyElementExist("saldo_revamp_invest_product_sub_title");
    }

    public void validateUserIsInvestor() {
        verifyElementExist("saldo_revamp_invest_nilai_investasi_amount");
        verifyElementExist("saldo_revamp_invest_total_unit");
        verifyElementExist("saldo_revamp_invest_keuntungan_percentage");
        verifyElementExist("saldo_revamp_invest_keuntungan_amount");
    }

    public void validateUserIsNonInvestor() {
        verifyElementExist("saldo_revamp_invest_nab_per_unit");
        verifyElementExist("saldo_revamp_invest_return_amount");
    }

    public void tapInvestasiBeliButton() { tapElement("saldo_revamp_invest_beli_btn"); }

    public void tapInvestasiJualButton() { tapElement("saldo_revamp_invest_jual_instant_btn"); }

    public void tapInvestasiRegButton() {
        waitForVisibilityOf("saldo_revamp_invest_regis_limit_date");
        tapElement("saldo_revamp_invest_mulai_daftar_btn");
    }

    public void tapInvestasiRegNowButton() { tapElement("saldo_revamp_invest_daftar_sekarang_btn"); }

    public void tapPendingInvestasiAmount() { tapElement("saldo_revamp_invest_pending_amount_btn"); }
}
