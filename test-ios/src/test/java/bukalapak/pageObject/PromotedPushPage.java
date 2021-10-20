package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PROMData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PromotedPushPage extends BasePage {

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM YYYY", new Locale("id", "ID"));

    public PromotedPushPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPromotedPushPage() {
        waitForVisibilityOf("promoted_push_sisa_budget_nominal", 30);
        verifyElementExist("promoted_push_title");
        verifyElementExist("promoted_push_sisa_budget");
        verifyElementExist("promoted_push_tambah_button");
        HelperData.setLastActionPage(new PromotedPushPage(iosDriver));
    }

    public void userOnOnboardingPromotedPushPage(String page) {
        switch (page) {
            case "first":
                verifyElementExist("promoted_push_first_page_title");
                verifyElementExist("promoted_push_first_page_caption");
                verifyElementDisplayed("promoted_push_onboarding_pelajari_cara_button");
                verifyElementDisplayed("promoted_push_onboarding_lanjut_button");
                break;
            case "second":
                verifyElementExist("promoted_push_second_page_title");
                verifyElementExist("promoted_push_second_page_caption");
                verifyElementDisplayed("promoted_push_onboarding_pelajari_cara_button");
                verifyElementDisplayed("promoted_push_onboarding_lanjut_button");
                break;
            case "third":
                verifyElementExist("promoted_push_third_page_title");
                verifyElementExist("promoted_push_third_page_caption");
                verifyElementDisplayed("promoted_push_onboarding_pelajari_cara_button");
                verifyElementDisplayed("promoted_push_onboarding_tutup_button");
                break;
            default:
                break;
        }
        HelperData.setLastActionPage(new PromotedPushPage(iosDriver));
    }

    public void tapPromotedPushTab(String tabName) {
        switch (tabName.toLowerCase()) {
            case "satuan":
                tapElement("promoted_push_satuan_tab");
                break;
            case "grup":
                tapElement("promoted_push_grup_tab");
                break;
            case "statistik":
                tapElement("promoted_push_statistik_tab");
                break;
            default:
                Assert.fail("Tab is not defined! Please check");
                break;
        }
    }

    public void userValidateNominal(String jenisNominal) {
        switch (jenisNominal) {
            case "Saldo Kamu":
                validateSaldoKamu();
                break;
            case "Sisa Budget":
                validateSisaBudgetAfterAddBudget();
                break;
            default:
                break;
        }
        HelperData.setLastActionPage(new PromotedPushPage(iosDriver));
    }

    private void setSisaBudget() {
        PROMData.setSisaBudget(getSisaBudget());
    }

    private void setSaldoKamu() {
        PROMData.setSaldoKamu(getSaldoKamu());
    }

    private int getSisaBudget() {
        assertTrue(isElementVisible("promoted_push_sisa_budget_nominal", 10));
        return getIntegerFromTextElement("promoted_push_sisa_budget_nominal");
    }

    private int getSaldoKamu() {
        assertTrue(isElementVisible("promoted_push_saldo_kamu_nominal", 5));
        return getIntegerFromTextElement("promoted_push_saldo_kamu_nominal");
    }

    public void validateSisaBudgetAfterBid() {
        assertEquals(PROMData.getSisaBudget() - PROMData.getBid(), getSisaBudget(), "Sisa budget promoted push seharusnya berkurang");
    }

    private void validateSisaBudgetAfterAddBudget() {
        assertEquals(PROMData.getSisaBudget() + PROMData.getInputtedBudget() + PROMData.getNominalBudgetBonus(),
                getIntegerFromTextElement("promoted_push_sisa_budget_nominal"),
                "promoted budget should be increased, but need check the budget is less than 1jt too!");
    }

    public void validateSaldoKamu() {
        assertEquals(PROMData.getSaldoKamu() - PROMData.getNominalBudgetNonBonus(), getSaldoKamu(), "Sisa saldo kamu seharusnya berkurang");
    }

    public void validatePromotedPushBudgetHarian(String state) {
        waitForVisibilityOf("promoted_push_budget_harian_status_text", 5);
        if (state.equalsIgnoreCase("Tidak Aktif")) {
            assertEquals("Tidak Aktif", getTextFromElement("promoted_push_budget_harian_status_text"));
            PROMData.setPromotedDailyBudgetStatusActive(false);
        } else {
            assertTrue(getTextFromElement("promoted_push_budget_harian_status_text").contains("Rp"));
            PROMData.setPromotedDailyBudgetStatusActive(true);
        }
        HelperData.setLastActionPage(new PromotedPushPage(iosDriver));
    }

    public void validatePromotedPushBudgetHarianLoan(boolean state) {
        verifyElementExist("promoted_push_budget_harian_active_text");
        if (state) {
            assertTrue(getTextFromElement("promoted_push_budget_harian_active_text").contains("Saldo Pinjaman Tidak Aktif"), "Loan tidak sesuai! Loan tidak aktif!");
            PROMData.setPromotedDailyLoanActive(false);
        } else {
            assertTrue(getTextFromElement("promoted_push_budget_harian_active_text").contains("Saldo Pinjaman Aktif"), "Loan tidak sesuai! Loan aktif!");
            PROMData.setPromotedDailyLoanActive(true);
        }
    }

    public void searchProduct(String productName) {
        waitForVisibilityOf("promoted_push_field_section");
        typeAndEnterValue("promoted_push_search_field", productName);
    }

    public void clickPromotedProductActionButton(String button, String productName) {
        String actionButtonXpath = constructLocator("promoted_push_action_button", productName);
        waitForVisibilityOf(actionButtonXpath, 30);
        if (button.equalsIgnoreCase("Aktifkan")) {
            assertEquals("Aktifkan", getTextFromElement(actionButtonXpath));
        } else {
            assertEquals("Atur", getTextFromElement(actionButtonXpath));
        }
        tapElement(actionButtonXpath);
    }

    public void setPromotedValue(String nominalType) {
        switch (nominalType) {
            case "Saldo Kamu":
                setSaldoKamu();
                break;
            case "Sisa Budget":
                setSisaBudget();
                break;
            default:
                Assert.fail("Please check your selection!");
                break;
        }
    }

    public void checkProductStatus(String productName, boolean status) {
        waitForVisibilityOf(constructLocator("promoted_push_status_text", productName), 30);
        String promotedStatus = getTextFromElement(constructLocator("promoted_push_status_text", productName));
        if (status) {
            assertEquals("Tidak Aktif", promotedStatus);
        } else {
            assertEquals("Aktif", promotedStatus);
        }
        HelperData.setLastActionPage(new PromotedPushPage(iosDriver));
    }

    public void checkBidValue(String productName, String bidValue) {
        assertEquals(Integer.parseInt(bidValue), getIntegerFromTextElement(constructLocator("promoted_push_bid_value_text", productName)));
        HelperData.setLastActionPage(new PromotedPushPage(iosDriver));
    }

    public void validatePromotedPushBudget(String credentialUser, String action, int cost) {
        int budget = API_CALL.getPromotedBudget(credentialUser);
        LogUtil.info("promoted budget: " + budget);
        if (action.contains("buyer clicked")) {
            assertEquals(cost, PROMData.getSisaBudget() - budget, "Not charged! Please Check!");
        } else if (action.contains("owner clicked")) {
            assertEquals(budget, PROMData.getSisaBudget(), "Different! Owner clicked his product! Please Check!");
        } else if (action.contains("add budget")) {
            assertEquals(budget, PROMData.getSisaBudget() + cost, "Not added! Please Check!");
        } else {
            LogUtil.warn("Please check your selection!");
        }
    }

    public void verifyOnboardingDisplayed() {
        waitForVisibilityOf("promoted_push_onboarding_title_1_text", 3);
        verifyElementDisplayed("promoted_push_onboarding_pelajari_cara_button");
        verifyElementDisplayed("promoted_push_onboarding_lanjut_button");
        HelperData.setLastActionPage(new PromotedPushPage(iosDriver));
    }

    public void verifyPromotedPushOnboardingDisappeared() {
        verifyElementNotExist("promoted_push_onboarding_title_3_text");
        verifyElementNotExist("promoted_push_onboarding_pelajari_cara_button");
        verifyElementNotExist("promoted_push_onboarding_tutup_button");
        HelperData.setLastActionPage(new PromotedPushPage(iosDriver));
    }

    public void tapTambahPromosiButton() {
        waitForVisibilityOf("promoted_push_tambah_promosi_button");
        tapElement("promoted_push_tambah_promosi_button");
    }

    // Statistik region
    public void verifyTipsPromosi() {
        verifyPromosi("promoted_statistic_first_tips_promotion_text", "promoted_statistic_first_tips_promotion_content_text");
        verifyPromosi("promoted_statistic_second_tips_promotion_text", "promoted_statistic_second_tips_promotion_content_text");
        verifyPromosi("promoted_statistic_third_tips_promotion_text", "promoted_statistic_third_tips_promotion_content_text");
        verifyPromosi("promoted_statistic_fourth_tips_promotion_text", "promoted_statistic_fourth_tips_promotion_content_text");

    }

    private void verifyPromosi(String title, String content) {
        swipeUpToElement(title);
        tapElement(title);
        swipeUpToElement(content);
        waitForVisibilityOf(content, 15);
        HelperData.setLastActionPage(new PromotedPushPage(iosDriver));
    }

    public void tapOnPeriodStatistic(String days) {
        waitForVisibilityOf("promoted_statistic_periode_7hari_option");
        if (days.equalsIgnoreCase("7")) {
            tapElement("promoted_statistic_periode_7hari_option");
        } else {
            tapElement("promoted_statistic_periode_30hari_option");
        }
    }

    private void checkDaterange(String period) {
        String startDate = LocalDate.now().minusDays(1).format(dateFormatter);
        String endDate = LocalDate.now().minusDays(8).format(dateFormatter);
        if (period.contains("30")) {
            endDate = LocalDate.now().minusDays(31).format(dateFormatter);
        }
        assertTextContains(startDate, getTextFromElement("promoted_statistic_date_range"));
        assertTextContains(endDate, getTextFromElement("promoted_statistic_date_range"));
    }

    public void validateEmptyValueStatistic(String period) {
        checkDaterange(period);
        validateDisplayed("tampil_statistic_section");
        assertEquals("0", getText("tampil_statistic_value_section"));
        validateDisplayed("diklik_statistic_section");
        assertEquals("0", getText("diklik_statistic_value_section"));
        validateDisplayed("terjual_statistic_section");
        assertEquals("0", getText("terjual_statistic_value_section"));
        validateDisplayed("total_penjualan_statistic_section");
        assertEquals("Rp0", getText("total_penjualan_statistic_value_section"));
        validateDisplayed("budget_promosi_dibeli_statistic_section");
        assertEquals("Rp0", getText("budget_promosi_dibeli_statistic_value_section"));
        validateDisplayed("budget_promosi_terpakai_statistic_section");
        assertEquals("Rp0", getText("budget_promosi_terpakai_statistic_value_section"));
    }

    public void checkPromotedStatisticBasedOnPeriode(String period) {
        HelperData.setLastActionPage(new PromotedPushPage(iosDriver));
        checkDaterange(period);
        validateDisplayed("promoted_statistic_diklik_value");
        validateDisplayed("promoted_statistic_terjual_value");
        validateDisplayed("promoted_statistic_total_penjualan_value");
        if (!isElementVisible("promoted_statistic_info_description")) {
            swipeUpToElement("promoted_statistic_info_description");
        }
        validateDisplayed("promoted_statistic_budget_dibeli_value");
        validateDisplayed("promoted_statistic_budget_terpakai_value");
        validateDisplayed("promoted_statistic_info_description");
    }

    public void tapRiwayatStatisticButton() {
        waitForVisibilityOf("promoted_statistic_lihat_riwayat_klik_button");
        tapElement("promoted_statistic_lihat_riwayat_klik_button");
    }
    // End Statistik region

    //Promoted satuan  & grup section
    public void tapPromotedPushIcon() {
        waitForVisibilityOf("promoted_push_onboarding_icon");
        tapElement("promoted_push_onboarding_icon");
    }

    public void clickTambahPromosiButton() {
        validateDisplayed("promoted_push_satuan_tambah_promosi_button");
        tapElement("promoted_push_satuan_tambah_promosi_button");
    }

    public void verifyPromotedGroupCampaign(String title, String state, String totalProducts) {
        waitForVisibilityOf("promoted_push_grup_tab");
        swipeUpToElement(constructLocator("promoted_push_grup_campaign_title", title));
        assertEquals(getElementLabel(constructLocator("promoted_push_grup_campaign_state", title)), state);
        assertEquals(getElementLabel(constructLocator("promoted_push_grup_campaign_products_number", title)), totalProducts);
        HelperData.setLastActionPage(new PromotedPushPage(iosDriver));
    }

    public void tapPromotedPushGroup(String title) {
        swipeUpToElement(constructLocator("promoted_push_grup_campaign_title", title));
        tapElement(constructLocator("promoted_push_grup_campaign_title", title));
    }

    public void verifyDeletedCampaign(String title) {
        verifyElementNotExist(constructLocator("promoted_push_grup_campaign_title", title));
    }
    // End promoted satuan & grup section

    // Error region
    public void verifyErrorMaxCampaign() {
        validateDisplayed("promoted_error_max_campaign");
    }

    public void verifyProductNotFoundState() {
        validateDisplayed("promoted_satuan_product_not_found_error_section");
        validateDisplayed("promoted_satuan_product_not_found_error_title");
        validateDisplayed("promoted_satuan_product_not_found_error_desc");
    }
    // End Error region

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
