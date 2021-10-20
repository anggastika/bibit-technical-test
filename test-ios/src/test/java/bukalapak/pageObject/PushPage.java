package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PROMData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.ArrayList;
import java.util.List;

public class PushPage extends BasePage {

    public PushPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPushPage() {
        if (isElementVisible("push_onboarding_lewati_button")) {
            tapElement("push_onboarding_lewati_button");
        }
        verifyElementExist("push_title");
        verifyElementExist("push_sisa_budget");
        HelperData.setLastActionPage(new PushPage(iosDriver));
    }

    public void searchProduct(String productName) {
        typeAndEnterValueWithTimeOut("push_search_product_field", productName);
        verifyElementExist("push_button");
    }

    public void clickPushButton(String productName) {
        waitForVisibilityOf(constructLocator("push_button_specific_product", productName));
        tapElement(constructLocator("push_button_specific_product", productName));
    }

    public void checkPushSuccessInfo(String productName) {
        verifyElementExist(constructLocator("push_success_info_text", productName));
    }

    public void clickTheFirstPushCheckbox(String productName) {
        waitForVisibilityOf(constructLocator("push_product_checkbox", productName), 20);
        List<IOSElement> productElements = getElements(constructLocator("push_product_checkbox", productName));
        PROMData.setPushBulkTotal(productElements.size());
        tapCenterOfElement(constructLocator("push_product_checkbox", productName));
    }

    public void clickSelectAllCheckbox() {
        tapElement("push_select_all_checkbox");
    }

    public void clickPushAllButton() {
        tapElement(constructLocator("push_bulk_button", String.valueOf(PROMData.getPushBulkTotal())));
    }

    public void checkBuyPushOrLoanModal() {
        verifyElementExist("push_beli_push_button");
    }

    public void checkPushBalance(String actionType) {
        int pushBalance = getIntegerFromTextElement("push_sisa_push_value");
        if (actionType.contains("single")) {
            assertEquals((PROMData.getSisaPush() - 1), pushBalance);
        } else if (actionType.contains("bulk")) {
            int totalBulk = PROMData.getPushBulkTotal();
            assertEquals((PROMData.getSisaPush() - totalBulk), pushBalance);
        } else {
            PROMData.setSisaPush(pushBalance);
        }
        HelperData.setLastActionPage(new PushPage(iosDriver));
    }

    public void clickOnboardingButton() {
        waitForVisibilityOf("push_onboarding_button");
        tapElement("push_onboarding_button");
    }

    // Onboarding region
    public void userOnOnboardingPushPage(String page) {
        switch (page) {
            case "first":
                waitForVisibilityOf("push_first_page_title");
                verifyElementExist("push_first_page_caption");
                verifyElementExist("push_first_page_indicator");
                verifyElementExist("push_onboarding_lewati_button");
                verifyElementExist("push_onboarding_pelajari_button");
                break;
            case "second":
                waitForVisibilityOf("push_second_page_title");
                verifyElementExist("push_second_page_caption");
                verifyElementExist("push_second_page_indicator");
                verifyElementExist("push_onboarding_kembali_button");
                verifyElementExist("push_onboarding_lanjut_button");
                break;
            case "third":
                waitForVisibilityOf("push_third_page_title");
                verifyElementExist("push_third_page_caption");
                verifyElementExist("push_third_page_indicator");
                verifyElementExist("push_onboarding_kembali_button");
                verifyElementExist("push_onboarding_lanjut_button");
                break;
            case "fourth":
                waitForVisibilityOf("push_fourth_page_title");
                verifyElementExist("push_fourth_page_caption");
                verifyElementExist("push_fourth_page_indicator");
                verifyElementExist("push_onboarding_kembali_button");
                verifyElementExist("push_onboarding_selesai_button");
                break;
            default:
                LogUtil.error("Please select one of Promoted Push onboarding options above!");
                break;
        }
        HelperData.setLastActionPage(new PushPage(iosDriver));
    }

    // Push Package region
    public void userOnBuyPushPackagePage() {
        waitForVisibilityOf("push_package_name_text", 20);
        PROMData.setPushPackageName(getLabelFromELements("push_package_name_text"));
        PROMData.setPushPackageBonus(getLabelFromELements("push_package_bonus_text"));
        PROMData.setPushPackageNormalPrice(getLabelFromELements("push_package_price_text"));
        PROMData.setPushPackageDuration(getLabelFromELements("push_package_duration_text"));
        assertTrue(PROMData.getPushPackageName().get(0).contains("50"));
        assertTrue(PROMData.getPushPackageBonus().get(0).contains("5.000"));
        assertTrue(PROMData.getPushPackageNormalPrice().get(0).contains("80.000"));
        assertTrue(PROMData.getPushPackageDuration().get(0).contains("15"));
        HelperData.setLastActionPage(new PushPage(iosDriver));
    }

    private List<String> getLabelFromELements(String element) {
        List<IOSElement> elements = getElements(element);
        List<String> labels = new ArrayList<>();
        for (IOSElement element1 : elements) {
            labels.add(element1.getText());
        }
        return labels;
    }

    public void clickPushPackage(String pushPackage) {
        tapElement(constructLocator("selected_package_name_text", pushPackage));
    }

    // Checkout region
    public void userOnPushCheckoutPage() {
        if (isElementVisible("push_package_aktif_hingga_text")) {
            verifyElementExist("push_package_checkout_push_name_text");
            verifyElementExist("push_package_checkout_price");
            verifyElementExist("push_package_bonus_text");
        }
        if (isElementVisible("available_bukadompet_saldo_text"))
            verifyElementExist("push_package_checkout_total_payment");
    }

    public void userOnPushSuccessInvoicePage() {
        waitForVisibilityOf("push_close_button");
        swipeToLocator("tagihan_lihat_detail_pesanan_button");
    }

    public void tapPushSuccessInvoiceCloseButton() {
        waitForElementClickable("push_close_button", 3);
        tapElement("push_close_button");
    }

    public void userOnPushInvoiceDetailPage() {
        waitForVisibilityOf("invoice_detail_detail_tagihan_title", 60);
        HelperData.setLastActionPage(new PushPage(iosDriver));
    }

    public void selectAutoExtend(String paymentMethod) {
        if (paymentMethod.equalsIgnoreCase("bukadompet")) {
            tapElement("push_package_checkout_bd_extend_checkbox");
        } else {
            tapElement("push_package_checkout_loan_extend_checkbox");
        }
    }

    public void checkPushPaymentInfo() {
        assertEquals(PROMData.getSelectedPushPackageName(), getTextFromElement("push_package_checkout_push_name_text"));
        assertEquals(PROMData.getSelectedPushPackageBonus(), getTextFromElement("push_package_bonus_text"));
        assertTrue(getTextFromElement("push_package_checkout_price").contains(PROMData.getSelectedPushPackagePrice()));
        if (isElementVisible("available_bukadompet_saldo_text")) {
            assertEquals(PROMData.getSelectedPushPackagePrice(), getTextFromElement("push_package_checkout_total_payment"));
        }
    }

    public void storePushDetailInfo(String pushPackage) {
        PROMData.setSelectedPushPackageName(getTextFromElement(constructLocator("selected_package_name_text", pushPackage)));
        PROMData.setSelectedPushPackageBonus(getTextFromElement(constructLocator("selected_package_bonus_text", pushPackage)));
        if (isElementVisible(constructLocator("selected_package_discount_text", pushPackage))) {
            PROMData.setSelectedPushPackagePrice(getTextFromElement(constructLocator("selected_package_discount_text", pushPackage)));
        } else {
            PROMData.setSelectedPushPackagePrice(getTextFromElement(constructLocator("selected_package_price_text", pushPackage)));
        }
    }

    private void tapBayarDenganSaldoButton() {
        swipeUpToElement("push_bayar_dengan_saldo_button");
        tapElement("push_bayar_dengan_saldo_button");
    }

    private void tapPilihMetodePembayaranLain() {
        swipeUpToElement("push_pilih_metode_pembayaran_lain_button");
        tapElement("push_pilih_metode_pembayaran_lain_button");
    }

    public void selectPaymentMethod(String selectedPayment) {
        if (selectedPayment.toLowerCase().contains("saldo")) {
            tapBayarDenganSaldoButton();
        } else {
            tapPilihMetodePembayaranLain();
        }
    }

    public void checkPaymentConfirmationPage() {
        waitForVisibilityOf("push_confirmation_payment_page_saldo_text");
        verifyElementExist("push_confirmation_payment_page_title_text");
        verifyElementExist("push_confirmation_payment_page_total_belanja_text");
        verifyElementExist("push_confirmation_payment_page_sisa_saldo_text");
    }

    public void verifyAturPromosiOtomatisPage() {
        verifyElementExist("atur_promosi_otomatis_title_text");
    }

    public void validatePushAutoExtendOffText() {
        waitForVisibilityOf("auto_extend_off_text", 10);
    }

    public void validatePushAutoExtendOnText(String text) {
        waitForVisibilityOf(constructLocator("auto_extend_on_text", text));
        HelperData.setLastActionPage(new PushPage(iosDriver));
    }

    public void selectPushAutoExtend(String option) {
        waitForVisibilityOf("atur_push_isi_otomatis_auto_extend_switch", 10);
        boolean isEnabled = Boolean.parseBoolean(getElement("atur_push_isi_otomatis_loan_switch").getAttribute("enabled"));
        if (option.equals("on")) {
            if (!isEnabled) {
                tapElement("atur_push_isi_otomatis_auto_extend_switch");
            }
        } else {
            if (isEnabled) {
                tapElement("atur_push_isi_otomatis_auto_extend_switch");
            }
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void checkIsiUlangOtomatis() {
        tapElement("push_checkout_otomatis_isi_checkbox");
    }

    public void checkLoanOptionIsiUlangOtomatis() {
        waitForVisibilityOf("push_checkout_otomatis_isi_loan_checkout");
        tapElement("push_checkout_otomatis_isi_loan_checkout");
    }

    public void validatePushAutoExtendLoan(String loanStatus) {
        if (loanStatus.equalsIgnoreCase("Aktif")){
            verifyElementDisplayed("push_auto_extend_with_loan_aktif");
        }else {
            verifyElementDisplayed("push_auto_extend_with_loan_nonaktif");
        }
    }

    public void tapOnLihatDetailPesananButton() {
        swipeUpToElement("tagihan_lihat_detail_pesanan_button");
        tapElement("tagihan_lihat_detail_pesanan_button");
    }

    public void tapOnTransferBankPayment() {
        waitForVisibilityOf("checkout_non_marketplace_alchemy_payment_screen_transfer_option");
        tapElement("checkout_non_marketplace_alchemy_payment_screen_transfer_option");
    }
}
