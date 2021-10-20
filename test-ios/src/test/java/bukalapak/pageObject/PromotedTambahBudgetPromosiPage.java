package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PROMData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedTambahBudgetPromosiPage extends BasePage {

    public PromotedTambahBudgetPromosiPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnTambahBudgetPromosiPage() {
        verifyElementExist("promoted_tambah_budget_promosi_text");
        verifyElementExist("promoted_pilih_nominal_text");
        verifyElementExist("promoted_masukkan_nominal_text");
        verifyElementExist("promoted_lanjut_pembayaran_button");
    }

    public void inputBudget(String budget) {
        PROMData.setInputtedBudget(Integer.parseInt(budget));
        tapElement("promoted_masukkan_nominal_radio_button");
        checkSuperSellerBonusBudget();
        typeAndEnterValueWithTimeOut("promoted_budget_field", budget);
        HelperData.setLastActionPage(new PromotedTambahBudgetPromosiPage(iosDriver));
    }

    public void verifyDailyBudgetInformation(String status) {
        waitForVisibilityOf("daily_budget_information_title_text", 10);
        verifyElementExist("daily_budget_information_first_content_text");
        tapElement("daily_budget_information_navbar");
        verifyElementExist("daily_budget_information_second_content_text");
        verifyElementExist("daily_budget_information_third_content_text");
        if (status == null) {
            swipeUpToElement("daily_budget_information_loan_text");
            verifyElementExist("daily_budget_information_loan_text");
        }
    }

    private void checkSuperSellerBonusBudget() {
        if (isElementVisible("promoted_super_seller_bonus_budget_info_text")) {
            String bonusPercentage = PROMData.getPromotedBonusPercentage();
            PROMData.setNominalBudgetBonus(Integer.parseInt(bonusPercentage) * PROMData.getInputtedBudget() / 100);
        } else {
            PROMData.setNominalBudgetBonus(0);
        }
    }

    public void userOnSuccessPaymentPage() {
        swipeDownToElement("tagihan_lihat_detail_pesanan_button");
        HelperData.setLastActionPage(new PromotedTambahBudgetPromosiPage(iosDriver));
    }

    public void userOnInvoiceDetailPage() {
        waitForVisibilityOf("invoice_detail_detail_tagihan_title", 60);
        HelperData.setLastActionPage(new PromotedTambahBudgetPromosiPage(iosDriver));
    }

    public void checkInformasiTagihan() {
        openDetailTagihan();
        assertEquals(PROMData.getInputtedBudget(), getIntegerFromTextElement("promoted_budget_total_tagihan_price_text"), "Price isnt matched with the expected!");
        HelperData.setLastActionPage(new PromotedTambahBudgetPromosiPage(iosDriver));
    }

    private void openDetailTagihan() {
        if (!isElementVisible("detail_pembelian_status_pesanan_text")) {
            tapElement("detail_pembelian_informasi_pesanan");
        }
        if (isElementVisible("detail_pembelian_rincian_button")) {
            tapElement("detail_pembelian_rincian_button");
        }
    }

    private String getFillingTypeText() {
        return getText("promoted_push_invoice_tipe_pengisian_text");
    }

    public void checkPromotedBudgetSection(String type) {
        swipeDownToElement("promoted_invoice_tipe_pengisian_text");
        String nominalBudgetText = getTextFromElement("promoted_invoice_nominal_budget_text");
        if (nominalBudgetText.contains("+")) {
            String[] budget = nominalBudgetText.split("\\+", 2);
            assertEquals(PROMData.getInputtedBudget(), parseIntegerFromText(budget[0]));
            assertEquals(PROMData.getNominalBudgetBonus(), parseIntegerFromText(budget[1]));
        } else {
            assertEquals(PROMData.getInputtedBudget(), getIntegerFromTextElement("promoted_invoice_nominal_budget_text"));
        }
        if (type.equalsIgnoreCase("sekali isi")) {
            assertEquals("Sekali Isi", getFillingTypeText());
        } else {
            assertEquals("Harian", getFillingTypeText());
        }
    }

    public void validateErrorMessage() {
        verifyElementDisplayed("ERROR_MESSAGE_MINIMUM_TOPUP_TEXT");
        HelperData.setLastActionPage(new PromotedTambahBudgetPromosiPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapOnLihatTagihanPembayaran() {
        swipeUpToElement("tagihan_lihat_detail_pesanan_button");
        tapElement("tagihan_lihat_detail_pesanan_button");
    }

    public void verifyPendingPromotedInstantPayment() {
        openDetailTagihan();
        verifyElementDisplayed("tagihan_menunggu_pembayaran_text");
        assertEquals(PROMData.getInputtedBudget(), getIntegerFromTextElement("harga_total_belanja_price_text"), "Price isnt matched with the expected!");
        swipeUpToElement("promoted_budget_promosi_text"); // to check transaction type is budget promosi
        HelperData.setLastActionPage(new PromotedPushInstantPage(iosDriver));
    }
}
