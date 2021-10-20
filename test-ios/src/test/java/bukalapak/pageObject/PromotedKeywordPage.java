package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PROMData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class PromotedKeywordPage extends BasePage {

    public PromotedKeywordPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPromotedKeywordPage() {
        if (isElementVisible("skip_onboarding")) {
            tapElement("skip_onboarding");
        }
        verifyElementExist("promoted_keyword_title_text");
        swipeUpToElement("promoted_keyword_sisa_budget");
        verifyElementExist("promoted_keyword_sisa_budget");
        verifyElementExist("promoted_keyword_tambah_budget_button");
        HelperData.setLastActionPage(new PromotedKeywordPage(iosDriver));
    }

    public void tapIconOnboarding() {
        tapElement("promoted_keyword_onboarding_icon");
    }

    public void userOnBannerPromotedkeyword(String page) {
        switch (page) {
            case "first":
                verifyElementDisplayed("promoted_keyword_onboarding_first_page_title_text");
                verifyElementDisplayed("promoted_keyword_onboarding_first_page_content_text");
                break;
            case "second":
                verifyElementDisplayed("promoted_keyword_onboarding_second_page_title_text");
                verifyElementDisplayed("promoted_keyword_onboarding_second_page_content_text");
                break;
            case "third":
                verifyElementDisplayed("promoted_keyword_onboarding_third_page_title_text");
                verifyElementDisplayed("promoted_keyword_onboarding_third_page_content_text");
                break;
            default:
                Assert.fail("option not available, please check again!");
                break;
        }
    }

    public void tapOnNextButton() {
        tapElement("promoted_keyword_next_button");
    }

    public void tapOnPrevButton() {
        tapElement("promoted_keyword_previous_button");
    }

    public void tapOnFinishButton() {
        tapElement("promoted_keyword_selesai_button");
    }

    public void userOnPromotedKeywordLoanPaymentPage() {
        verifyElementExist("promoted_keyword_saldo_loan_payment");
        verifyElementExist("promoted_keyword_jumlah_tagihan_loan");
        HelperData.setLastActionPage(new PromotedKeywordPage(iosDriver));
    }

    public void loanPayment(String state) {
        if (state.equalsIgnoreCase("true")) {
            verifyElementExist("promoted_keyword_loan_payment_text");
        } else {
            verifyElementNotExist("promoted_keyword_loan_payment_text");
        }
    }

    private void setSisaBudget() {
        PROMData.setSisaBudget(getSisaBudget());
    }

    private void setBudgetHarian() {
        PROMData.setDailyBudget(getDailyBudget());
    }

    private int getSisaBudget() {
        swipeDownToElement("promoted_keyword_sisa_budget_text");
        assertTrue(isElementVisible("promoted_keyword_sisa_budget_text", 5));
        return getIntegerFromTextElement("promoted_keyword_sisa_budget_text");
    }

    private int getDailyBudget() {
        assertTrue(isElementVisible("promoted_keyword_daily_budget_text", 5));
        return getIntegerFromTextElement("promoted_keyword_daily_budget_text");
    }

    public void userValidateNominal(String jenisNominal) {
        switch (jenisNominal) {
            case "after add budget":
                validateSisaBudgetAfterAdd();
                break;
            case "reduction":
                validateSisaBudgetAfterReduction();
                break;
            default:
                break;
        }
    }

    private static void validateSisaBudgetAfterReduction() {
        LogUtil.warn("Reserved! Will be added later!");
    }

    private void validateSisaBudgetAfterAdd() {
        assertEquals(PROMData.getSisaBudget() + PROMData.getInputtedBudget() + PROMData.getNominalBudgetBonus(),
                getIntegerFromTextElement("promoted_keyword_sisa_budget_text"),
                "Sisa budget promoted keyword seharusnya bertambah");
        goToHomePage();
    }

    public void setPromotedValue(String nominalType) {
        switch (nominalType) {
            case "Budget Harian":
                setBudgetHarian();
                break;
            case "Sisa Budget":
                setSisaBudget();
                break;
            default:
                Assert.fail("Please check the selection!");
                break;
        }
    }

    public void validatePromotedKeywordBudgetHarian(String state) {
        if (state.equalsIgnoreCase("Tidak Aktif")) {
            waitForVisibilityOf("promoted_keyword_budget_harian_not_active_text", 15);
            PROMData.setPromotedDailyBudgetStatusActive(false);
        } else {
            verifyElementExist("promoted_keyword_budget_harian_active_text");
            assertTrue(getTextFromElement("promoted_keyword_budget_harian_active_text").contains(state), "Budget harian tidak tersimpan!");
            PROMData.setPromotedDailyBudgetStatusActive(true);
        }
        HelperData.setLastActionPage(new PromotedKeywordPage(iosDriver));
    }

    public void validatePromotedKeywordBudgetHarianLoan(String state) {
        waitForVisibilityOf("promoted_keyword_budget_harian_active_text", 15);
        if (state == null) {
            assertTrue(getTextFromElement("promoted_keyword_budget_harian_active_text").contains("Saldo Pinjaman Aktif"), "Loan tidak sesuai! Loan aktif!");
            PROMData.setPromotedDailyLoanActive(true);
        } else {
            assertTrue(getTextFromElement("promoted_keyword_budget_harian_active_text").contains("Saldo Pinjaman Tidak Aktif"), "Loan tidak sesuai! Loan tidak aktif!");
            PROMData.setPromotedDailyLoanActive(false);
        }
    }

    public void checkPromotedKeywordProductStatus(String productName, boolean state) {
        waitForVisibilityOf(constructLocator("promoted_keyword_product_state_text", productName), 5);
        String actualStatus = getTextFromElement(constructLocator("promoted_keyword_product_state_text", productName));
        if (state) {
            assertEquals("Tidak Aktif", actualStatus, "The status isn't matched! Should be Tidak Aktif!");
        } else {
            assertEquals("Aktif", actualStatus, "The status isn't matched! Should be Aktif!");
        }
    }

    public void clickPromotedKeywordButton(String buttonName, String productName) {
        waitForVisibilityOf(constructLocator("promoted_keyword_product_setting_button", productName), 5);
        String actualButtonName = getTextFromElement(constructLocator("promoted_keyword_product_setting_button", productName));
        if (buttonName.equalsIgnoreCase("Aktifkan")) {
            assertEquals("Aktifkan", actualButtonName, "The button isn't matched! Should be Aktifkan!");
        } else {
            assertEquals("Atur", actualButtonName, "The button isn't matched! Should be Atur!");
        }
        tapElement(constructLocator("promoted_keyword_product_setting_button", productName));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void saldoBukalapakPage() {
        verifyElementExist("saldo_bukalapak_header");
        verifyElementExist("status_pending_pembayaran");
        HelperData.setLastActionPage(new PromotedKeywordPage(iosDriver));
    }

    public void goToMenu(String elementName) {
        swipeUpToElement(elementName);
    }

    public void tapPromotedKeywordTab(String tab) {
        if (tab.equalsIgnoreCase("daftar barang")) {
            tapElement("promoted_keyword_daftar_barang_tab");
        } else {
            tapElement("promoted_keyword_riwayat_klik_tab");
        }
    }

    public void searchProduct(String productName) {
        waitForVisibilityOf("promoted_keyword_search_field");
        typeAndEnterValue("promoted_keyword_search_field", productName);
    }

    public void checkMutationPromotedKeyword(String productName, String keyword, String bid) {
        validateDisplayed(constructLocator("promoted_keyword_product_name_text", productName));
        validateDisplayed(constructLocator("promoted_keyword_text", keyword));
        validateValue().contains(bid, getText(constructLocator("promoted_keyword_bid_value_text", bid)));
    }
}
