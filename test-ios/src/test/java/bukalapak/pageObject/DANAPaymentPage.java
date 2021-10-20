package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.DANAData;
import bukalapak.data.HelperData;
import bukalapak.data.MtxData;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class DANAPaymentPage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(DANAPaymentPage.class);

    public DANAPaymentPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void selectDANAasPaymentMethod() {
        if (isElementExist("checkout_page_bayar_dengan_dana_button",10)) {
            LOGGER.info("DANA already selected as main payment method");
        } else {
            selectMetodePembayaran();
            selectDANAinMetodePembayaran();
            handleCardPaymentOnboarding();
            handleTopupOnboarding();
            tapGunakanMetodeIniButton();
        }
    }

    public void selectDANAasPayment() {
        if (isElementExist("checkout_page_bayar_dengan_dana_button",10)) {
            LOGGER.info("DANA already selected as main payment method");
        } else {
            selectDANAinMetodePembayaran();
            handleCardPaymentOnboarding();
            handleTopupOnboarding();
            tapGunakanMetodeIniButton();
        }
    }

    public void selectDANAUnbindAsPaymentMethod() {
        selectMetodePembayaran();
        selectDANAinMetodePembayaran();
    }

    public void selectMetodePembayaran() {
        waitForVisibilityOf("checkout_marketplace_alchemy_total_pembayaran_price",20);
        for (int i = 0; i < 5; i++) {
            if (isElementVisible("checkout_page_metode_pembayaran")) {
                //Try to tap the element by accessibilityId
                tapElement("checkout_page_metode_pembayaran");
            } else if (isElementVisible("checkout_marketplace_page_metode_pembayaran")) {
                //Try to tap the element by xpath
                tapElement("checkout_marketplace_page_metode_pembayaran");
            } else {
                //Already on payment method page
                break;
            }
            waitFor(2);
        }
    }

    public void selectDANAinMetodePembayaran() {
        for (int i = 0; i < 5; i++) {
            if (isElementVisible("use_this_method_button")) {
                break;
            }
            tapDANAbyDescription();
        }
    }

    public void tapDANAbyDescription() {
        try {
            tapDANAbyCardDesc();
        } catch (Exception e) {
            tapDANAbySaldo();
        }
        handleCardPaymentOnboarding();
        handleTopupOnboarding();
    }

    public void tapDANAbySaldo() {
        try {
            swipeDownToElement("checkout_page_dana_saldo");
            tapElement("checkout_page_dana_saldo");
            LOGGER.info("Select DANA in Metode Pembayaran using Saldo");
        } catch (Exception e) {
            LOGGER.info("Retry tapDANAbyDescription");
        }
    }

    public void tapDANAbyCardDesc() {
        swipeUpToElement("checkout_page_dana_option_card_payment_version");
        tapElement("checkout_page_dana_option_card_payment_version");
        LOGGER.info("Select DANA in Metode Pembayaran using Card Description");
    }

    public void tapBayarDenganDANAButton() {
        for (int i = 0; i < 5; i++) {
            if (isElementVisible("checkout_page_bayar_dengan_dana_button",15)) {
                tapElement("checkout_page_bayar_dengan_dana_button");
            } else {
                break;
            }
            waitFor(2);
        }
    }

    public void verifyIsOnDANAOTPPage() {
        waitFor(5);
        hideKeyboard();
        try {
            LOGGER.info("Checking OTP page");
            verifyElementExist("checkout_page_masukan_OTP_dana");
        } catch (AssertionError e) {
            LOGGER.info("Checking PIN page");
            verifyElementExist("checkout_page_masukan_pin_dana");
        }
    }

    public void verifyIsOnSuccessPage() {
        waitFor(10);
        isElementVisible("checkout_page_mixpayment_tagihan_success_page_id");
        isElementVisible("checkout_page_mixpayment_jumlah_success_page_id");
    }

    public void handleCardPaymentOnboarding() {
        if (isElementVisible("dana_oke_button_popup_card_payment",5)) {
            tapElement("dana_oke_button_popup_card_payment");
        }
    }

    public void handleTopupOnboarding() {
        if (isElementVisible("lanjut_revamp_button",5)) tapElement("lanjut_revamp_button");
        if (isElementVisible("onboarding_text_ok",5)) tapElement("onboarding_text_ok");
    }

    private void tapGunakanMetodeIniButton(){
        tapElement("use_this_method_button");
        try {
            LOGGER.info("Uncheck mixpayment");
            waitForVisibilityOf("dana_mixpayment_checked",15);
            tapElement("dana_mixpayment_checked");
        } catch (Exception e){
            LOGGER.info("Mixpayment unavailable for this user");
        }
    }

    public void closeOTPPage() {
        if (isElementVisible("checkout_page_close_dana_pin_button")) {
            tapElement("checkout_page_close_dana_pin_button");
        } else if (isElementVisible("back_button")) {
            LOGGER.info("Invisible close button not appear");
            tapElement("back_button");
        }
        tapElement("back_button");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void justCloseOTPPage() {
        if (isElementVisible("checkout_page_close_dana_pin_button")) {
            tapElement("checkout_page_close_dana_pin_button");
        } else if (isElementVisible("back_button")) {
            LOGGER.info("Invisible close button not appear");
            tapElement("back_button");
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void backPINOTPDANA() {
        waitFor(10); //wait loading dana webview
        if (isElementVisible("done_to_hide_keyboard")) {
            tapElement("done_to_hide_keyboard");
        }
        if (isElementExist("dana_cashier_page_back_button",10)) {
            tapElement("dana_cashier_page_back_button");
        } else if (isElementExist("back_button_dana",5)) {
            tapElement("back_button_dana");
        } else {
            tapElement(18,52);
        }
    }

    public void unsetAutoApplyDANAVoucher() {
        swipeUpToElement("checkout_marketplace_alchemy_rincian_harga_text");
        if (isElementVisible("dana_checkout_marketplace_dana_voucher_close")) {
            tapElement("dana_checkout_marketplace_dana_voucher_close");
        }
    }

    public void isDANAVoucherExist(Integer totalVoucher) {
        swipeUpToElement("checkout_rincian_harga_text");
        if (totalVoucher == 0) {
            verifyElementNotExist("checkout_page_dana_voucher_section");
        } else {
            verifyElementExist("checkout_page_dana_voucher_section");
        }
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void validationMixPaymentSummarySaldoAndDana() {
        swipeToLocator("checkout_page_pakai_dana_summary");
        isElementVisible("checkout_page_pakai_saldo_summary");
    }

    public void validationMixPaymentSummaryDANAandCredits() {
        swipeToLocator("checkout_marketplace_alchemy_rincian_harga_text");
        isElementVisible("checkout_page_pakai_saldo_summary");
    }

    public void verifyBLVoucherReduction(String state) {
        if (state.equalsIgnoreCase("card")) {
            tapElement("dana_card_payment_rincian_harga");
            isElementVisible("checkout_marketplace_voucher_amount_text");
            tapElement("dana_card_payment_close_rincian_harga");
        } else {
            isElementVisible("checkout_marketplace_voucher_text");
        }
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validationMixPaymentSummaryDANA() {
        swipeDownToElement("checkout_page_sebagian_pakai_dana_summary");
        isElementVisible("checkout_page_sebagian_pakai_dana_summary");
    }

    public void handleDANAConfirmPage() {
        if (isElementVisible("payment_confirmation_lihat_tagihan_button", 10)) {
            tapElement("payment_confirmation_lihat_tagihan_button");
        } else if (isElementVisible("payment_confirmation_lihat_detail_pesanan_button")) {
            tapElement("payment_confirmation_lihat_detail_pesanan_button");
        } else {
            LOGGER.info("DANA payment callback should be delayed");
        }
    }

    public void verifyDANAMutationTab(String state) {
        waitForVisibilityOf("saldo_bl_page_saldo_dana",20);
        tapElement("saldo_dana_tab");
        switch (state) {
            case "payment":
                swipeUpToElement("payment_dana_label",3);
                break;
            case "refund":
                swipeUpToElement("refund_dana_label",3);
                break;
            default:
                Assert.fail("Undefined mutation state!");
                break;
        }
        HelperData.setLastActionPage(new SaldoBukalapakPage(iosDriver));
    }

    // Card Payment Section
    private void tapGunakanKartuLain() {
        if (isElementVisible("dana_card_payment_other_card_btn")) {
            tapElement("dana_card_payment_other_card_btn");
        } else {
            tapElement("dana_card_payment_new_card_btn");
        }
    }

    private void tapRegisteredCard() {
        swipeToDirection(Direction.UP);
        tapElement("dana_card_payment_existing_card_btn");
    }

    public void tapIsiDetailKartu() {
        tapElement("dana_isi_detail_kartu_button_label", 5);
    }

    public void tapBayar() {
        tapElement("dana_bayar_button_label");
    }

    public void inputCVV(String card){
        if (card.equals("new")) {
            typeAndEnterValue("dana_input_cvv_field","123");
        } else {
            typeAndEnterValue("dana_input_cvv_field_alt","123");
        }
    }

    public void setDANACardPayment(String state) {
        selectMetodePembayaran();
        selectDANAinMetodePembayaran();
        handleCardPaymentOnboarding();
        if (state.equals("new")) {
            LogUtil.info("Set payment method using DANA new card");
            tapGunakanKartuLain();
        } else if (state.equals("registered")) {
            LogUtil.info("Set payment method using DANA registered card");
            tapRegisteredCard();
        }
        swipeUpToElement("dana_card_payment_tnc_btn");
        tapGunakanMetodeIniButton();
        waitForVisibilityOf("checkout_alchemy_page_title");
    }

    public void setDANACreditCardPayment(String state) {
        selectDANAinMetodePembayaran();
        handleCardPaymentOnboarding();
        if (state.equals("new")) {
            LogUtil.info("Set payment method using DANA new card");
            tapGunakanKartuLain();
        } else if (state.equals("registered")) {
            LogUtil.info("Set payment method using DANA registered card");
            tapRegisteredCard();
        }
        swipeUpToElement("dana_card_payment_tnc_btn");
        tapGunakanMetodeIniButton();
        waitForVisibilityOf("checkout_alchemy_page_title");
    }

    public void typeCardNumber(String card_number) {
        waitForVisibilityOf("dana_input_card_number");
        typeAndEnterValue("dana_input_card_number", TestInstrument.dotenv.get(card_number));
    }

    public void fillDetailCard() {
        tapElement("dana_month_dropdown", 5);
        tapElement("dana_month_value", 5);
        tapElement("dana_year_dropdown", 5);
        tapElement("dana_year_value", 5);
        inputCVV("new");
    }

    public void verifyDANAPaymentMethod(String paymentMethod) {
        if (isElementVisible("product_details_on_boarding_benefit_text")) {
            tapElement("product_details_ok_button"); //handle intermittent race condition onboarding
        }
        if (paymentMethod.equals("DANA - Kartu Debit") || paymentMethod.equals("DANA - Kartu Kredit")) {
            //tapElement("checkout_page_close_dana_pin_button"); //using bypass dana otp user
            if (isElementVisible("ulasan_instan_landing_lihat_tagihan_button", 20)) {
                tapElement("ulasan_instan_landing_lihat_tagihan_button");
            } else if (isElementVisible("payment_confirmation_open_invoice_button",20)) {
                tapElement("payment_confirmation_open_invoice_button");
            } else {
                tapElement("payment_confirmation_open_order_button",20);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                if (isElementVisible("detail_pembelian_informasi_pesanan") || isElementVisible("detail_pembelian_informasi_tagihan")) {
                    break;
                } else if (isElementVisible("payment_confirmation_lihat_detail_pesanan_button")) {
                    tapElement("payment_confirmation_lihat_detail_pesanan_button");
                    break;
                } else if (isElementVisible("payment_confirmation_lihat_tagihan_button")) {
                    tapElement("payment_confirmation_lihat_tagihan_button");
                    break;
                } else {
                    waitFor(1);
                }
            }
            new DetailPembelianMarketplacePage(iosDriver).verifyPaidStatusOnInvoice();
        }
        waitForVisibilityOf(constructLocator("dana_invoice_card_payment_txt", paymentMethod), 20);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void cardPaymentLimitationAmount() {
        selectMetodePembayaran();
        selectDANAinMetodePembayaran();
        handleCardPaymentOnboarding();
        isElementVisible("dana_min_amount_error");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyDanaCardPaymentFee(String payment_fee_env) {
        waitForVisibilityOf("dana_checkout_marketplace_total_pembayaran_value",15);
        swipeDownAtSpecifiedLocator("dana_checkout_marketplace_biaya_pelayanan_value");

        double payment_fee = 0.0, total_payment, actual_fee_on_checkout, without_fee, expected_fee, dana_voucher;
        String payment_fee_from_env = TestInstrument.dotenv.get(payment_fee_env);
        if (payment_fee_from_env != null) {
            payment_fee = Double.parseDouble(payment_fee_from_env)/100;
        }
        total_payment = Double.parseDouble(
                getElementValue("dana_checkout_marketplace_total_pembayaran_value")
                .replace(".", "").replace("Rp", ""));
        actual_fee_on_checkout = Double.parseDouble(
                getElementValue("dana_checkout_marketplace_biaya_pelayanan_value")
                .replace(".", "").replace("Rp", ""));
        if (isElementVisible("dana_checkout_marketplace_dana_voucher_value")) {
            dana_voucher = Double.parseDouble(
                    getElementValue("dana_checkout_marketplace_dana_voucher_value")
                    .replace(".", "").replace("-", "")
                    .replace("Rp", ""));
            without_fee = total_payment - actual_fee_on_checkout + dana_voucher;
        } else {
            without_fee = total_payment - actual_fee_on_checkout;
        }

        expected_fee = without_fee * payment_fee;
        Assert.assertEquals(Math.round(expected_fee), Math.round(actual_fee_on_checkout));
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyInvalidCardError(){
        typeCardNumber("DANA_INVALID_CARD_NUMBER");
        tapElement("simpan_kartu_txt");
        waitForVisibilityOf("invalid_dana_card_error_txt", 20);
        tapBayar();
        waitForVisibilityOf("gagal_menggunakan_kartu_txt", 20);
        tapElement("ubah_kartu_btn");
        waitForVisibilityOf("invalid_dana_card_error_txt", 20);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyDanaSavedCard() {
        String strlist = TestInstrument.dotenv.get("DANA_SAVED_CARD_LIST");
        assert strlist != null;
        String[] list = strlist.split(",");

        for (int i = 0; i < list.length; i++) {
            swipeUpToElement(constructLocator("dana_saved_card_last_numb", list[i]));
            verifyElementExist(constructLocator("dana_saved_card_last_numb", list[i]));
        }
    }
    // End section of card payment

    //Mix Payment DANA
    public void selectMixPaymentMethodWithDANA(String payMethod) {
        swipeDownToElement("checkout_marketplace_page_metode_pembayaran");
        waitForVisibilityOf("checkout_marketplace_page_metode_pembayaran",15);
        tapElement("checkout_marketplace_page_metode_pembayaran");
        swipeUpToElement(constructLocator("dana_checkout_payment_method", payMethod));
        tapElement(constructLocator("dana_checkout_payment_method", payMethod));
    }

    public void selectSubPaymentMethod(String subPayMethod) {
        if (subPayMethod.equals("Transfer")) {
            MtxData.setPaymentMethod("Transfer Bank Manual");
            waitForVisibilityOf("checkout_marketplace_transfer_bank_manual_txt");
        } else {
            swipeUpToElement(constructLocator("dana_checkout_sub_payment_method", subPayMethod));
            tapElement(constructLocator("dana_checkout_sub_payment_method", subPayMethod));
        }
        tapElement("checkout_gunakan_metode_ini_button");
    }

    public void closeInternetBanking(String ibank) {
        waitForVisibilityOf(constructLocator("checkout_page_ibank_txt", ibank), 20);
        tapElement("checkout_page_Internet_bank_back_btn");
        try {
            tapElement("popup_alert_ya_button");
        } catch (Exception e){ //handle auto tap Ya button
            waitForVisibilityOf("detail_pembelian_informasi_pesanan");
        }
        setDANATagihanTrx();
    }

    public void setDANATagihanTrx() {
        if (isElementVisible("tagihan_lihat_detail_pesanan_button")) {
            tapElement("tagihan_lihat_detail_pesanan_button");
        }
        swipeDownToElement("dana_no_tagihan");
        DANAData.setNoTagihan(getElementLabel("dana_no_tagihan"));
        if (DANAData.getNoTagihan() == null) DANAData.setNoTagihan(getElementValue("dana_no_tagihan"));
        swipeUpToElement("dana_no_trx");
        DANAData.setNoTrx(getElementValue("dana_no_trx"));
    }

    public void tapBayarDenganButton() {
        waitForVisibilityOf("priority_checkout_force_bd",15);
        waitForVisibilityOf("dana_checkout_marketplace_total_pembayaran_value",15);
        tapElement("priority_checkout_force_bd");
        if (isElementVisible("checkout_marketplace_lanjut_pembayaran_button")) {
            tapElement("checkout_marketplace_lanjut_pembayaran_button");
        }
    }

    public void setMixPayment(String mix) {
        tapElement("checkout_page_mixpayment_" + mix);
        if (isElementClickable("checkout_page_mixpayment_clearinput")) {
            tapElement("checkout_page_mixpayment_clearinput");
        }
        typeAndEnterValue("checkout_page_mixpayment_" + mix,"100");
        tapElement("checkout_page_mixpayment_" + mix + "_checkbox");
        waitForVisibilityOf("checkout_page_mixpayment_bayar_pake_" + mix,15);
    }

    public void mixPaymentDANAWithCredits() {
        swipeDownToElement("checkout_marketplace_alchemy_metode_pembayaran");
        waitForVisibilityOf("dana_checkout_marketplace_total_pembayaran_value",20);
        if (getElementValue("checkout_page_mixpayment_checkbox").equals("false")) {
            tapElement("checkout_page_mixpayment_checkbox");
            waitForVisibilityOf("dana_checkout_marketplace_total_pembayaran_value",20);
        }
        swipeUpToElement("dana_checkout_marketplace_mix_payment_value");
        DANAData.setMixAmountTrx(Integer.parseInt(
                getElementValue("dana_checkout_marketplace_mix_payment_value")
                        .replace(".", "").replace("-Rp", "")));
    }

    public void tapUbahMixPayment() {
        waitForVisibilityOf("checkout_page_ubah_mix_payment", 15);
        for (int i = 0; i < 5; i++) {
            tapElement("checkout_page_ubah_mix_payment");
            if (isElementVisible("checkout_marketplace_gabung_metode_lain_text")) {
                break;
            }
            else {
                waitFor(3);
            }
        }
    }

    public void unsetMixPayment(String mix) {
        if (isElementVisible("checkout_page_mixpayment_bayar_pake_" + mix)) {
            tapElement("checkout_page_mixpayment_bayar_pake_" + mix);
            tapElement("checkout_page_mixpayment_" + mix + "_checkbox");
        }
    }

    public void unsetAutoMixPayment() {
        waitForVisibilityOf("dana_checkout_marketplace_total_pembayaran_value",20);
        if (getElementValue("checkout_page_mixpayment_checkbox").equals("true")) {
            tapElement("checkout_page_mixpayment_checkbox");
            waitForVisibilityOf("dana_checkout_marketplace_total_pembayaran_value",20);
        }
    }

    public void unsetAutoTripleMixPayment() {
        waitForVisibilityOf("dana_checkout_marketplace_total_pembayaran_value",20);
        if (getElementValue("checkout_page_mixpayment_dana_checkbox").equals("true")) {
            tapElement("checkout_page_mixpayment_dana_checkbox");
            waitForVisibilityOf("dana_checkout_marketplace_total_pembayaran_value",20);
        }
        if (getElementValue("checkout_page_mixpayment_saldo_checkbox").equals("true")) {
            tapElement("checkout_page_mixpayment_saldo_checkbox");
            waitForVisibilityOf("dana_checkout_marketplace_total_pembayaran_value",20);
        }
    }

    public void tapSimpanMixPayment() {
        swipeUpToElement("checkout_page_mixpayment_simpan_btn");
        tapElement("checkout_page_mixpayment_simpan_btn");
    }

    public void hideKeyboardMixpayment() {
        if (isElementVisible("done_to_hide_keyboard")) {
            tapElement("done_to_hide_keyboard");
        }
    }

    public void verifyMixpaymentWithDANA() {
        verifyElementExist("detail_pembelian_potogan_saldo_dana_text");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void verifyMixpaymentWithBukaDompet() {
        verifyElementExist("detail_pembelian_potogan_saldo_bukadompet_text");
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void verifyMixpaymentWithSaldo() {
        try {
            verifyElementExist("detail_pembelian_potogan_saldo_credits_text");
        } catch (Exception e) {
            verifyElementExist("detail_pembelian_potogan_saldo_bukadompet_text");
        }
        HelperData.setLastActionPage(new InvoiceDetailPage(iosDriver));
    }

    public void tapLihatTagihanOnDanaSuccessPage() {
        try {
            new PaymentStatusPage(iosDriver).tapSeePaymentDetailButton();
        } catch (Exception e) {
            swipeUpToElement("invoice_detail");
            if (isElementVisible("dana_invoice_detail_memproses_pembayaran_text")) {
                doPullRefresh(1);
            }
        }
    }
    // End section of Mix Payment DANA

    // Change Payment DANA
    public void tapUbahPembayaran() {
        swipeUpToElement("dana_ubah_pembayaran", 1);
        tapElement("dana_ubah_pembayaran");
    }

    public void setChangeTo(String payMethod) {
        waitForVisibilityOf("dana_change_payment_checkout_page", 20);
        swipeUpToElement(constructLocator("dana_change_payment_other", payMethod));
        tapElement(constructLocator("dana_change_payment_other", payMethod));
    }

    public void tapBayarOnOldCheckout() {
        for (int i = 0; i < 3; i++) {
            nativeSwipeUp();
            if (isElementVisible("dana_change_payment_bayar_btn")) {
                break;
            }
        }
        tapElement("dana_change_payment_bayar_btn");
        if (isElementVisible("detail_pembelian_lanjutkan_pembayaran_button")) {
            tapElement("detail_pembelian_lanjutkan_pembayaran_button");
        }
    }

    public void tapBackFromDetailPembelian() {
        for (int i = 0; i < 6; i++) {
            if (isElementVisible("home_blhome_tab")) {
                tapElement("home_blhome_tab");
                break;
            }
            try {
                tapElement("base_back_button");
            } catch (Exception e) {
                if (isElementVisible("dana_alchemy_back_btn")) {
                    tapElement("dana_alchemy_back_btn");
                }
            }
        }
        tapElement("home_blhome_tab");
    }

    public void verifykNoTagihan() {
        tapElement("dana_saldo_homepage");
        Assert.assertTrue(isElementVisible(constructLocator("dana_refund_info", DANAData.getNoTagihan())));
        verifyElementExist("dana_mutasi_nominal");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }
    //End section of Change Payment

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyDANARefundMutation(String trxNo, String invoiceNo, String[] mutation, String payMethod) {
        if (Objects.equals(mutation[0], "100")) LOGGER.info("hardcoded for mix payment only");
        if (mutation[1] != null) assertEquals( "credit", mutation[1]);
        if (Objects.equals(payMethod, "Credits")) {
            if (Objects.equals(mutation[2], "issuing")) {
                assertTextContains("Refund", mutation[3]);
            } else { // refund normal non-issuing
                assertTextContains("Pengembalian", mutation[3]);
            }
        } else { // Main Payment == DANA
            if (mutation[2] != null) assertEquals( "refund", mutation[2]);
            if (mutation[3] != null) {
                if (Objects.equals(payMethod, "DANA")) {
                    assertEquals(invoiceNo, mutation[3]);
                } else { // Mix DANA + Credits
                    assertEquals(trxNo, mutation[3]);
                }
            }
        }
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void selectDANAasPaymentMethodForUnbindUsers() {
        selectMetodePembayaran();
        selectDANAinMetodePembayaranScreen();
    }

    public void selectDANAinMetodePembayaranScreen() {
        if (isElementVisible("checkout_page_dana_hubungkan_DANA")) {
            tapHubungkanDANA();
        }
        else {
            tapDANAbyDescription();
        }
    }

    public void tapHubungkanDANA() {
        swipeUpToElement("checkout_page_dana_hubungkan_DANA");
        tapElement("checkout_page_dana_hubungkan_DANA");
        LOGGER.info("Binding DANA in Metode Pembayaran");
    }

    public void verifyDANAPaymentSelectionScreen(){
        if (isElementExist("dana_oke_button_popup_card_payment", 10)) {
            tapElement("dana_oke_button_popup_card_payment");
        }

        waitForVisibilityOf("dana_payment_selection_screen_label", 10);
        verifyElementExist("dana_gunakan_metode_ini_button");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void verifyDANATopupScreen() {
        isElementExist("dana_payment_topup_label", 10);
        verifyElementExist("dana_nominal_topup_label");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void selectDANA() {
        try {
            waitForVisibilityOf("checkout_page_metode_pembayaran", 20);
            tapElement("checkout_page_metode_pembayaran");
        } catch (Exception e) {
            swipeDownToElement("checkout_page_metode_pembayaran_revamp", 3);
            tapElement("checkout_page_metode_pembayaran_revamp");
        }
        swipeUpToElement("checkout_page_dana_option_card_payment_version");
        tapElement("checkout_page_dana_option_card_payment_version");
    }

    public void verifyDANAPaymentPage() {
        handleCardPaymentOnboarding();
        handleTopupOnboarding();
        verifyElementExist("checkout_page_top_up_dana_title");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void topUpDANAViaMetodeLain(String amount) {
        waitForVisibilityOf("checkout_page_top_up_dana_metode_lain_button");
        tapElement("checkout_page_top_up_dana_metode_lain_button");
        waitForVisibilityOf("topup_dana_vp_amount_field");
        typeAndEnterValue("topup_dana_vp_amount_field", amount);
        waitForVisibilityOf("topup_dana_vp_lanjut_bayar_button");
        tapElement("topup_dana_vp_lanjut_bayar_button");
    }

    public void selectTopUpDANAMetodeLainMPCheckout() {
        waitForVisibilityOf("checkout_page_top_up_dana_metode_lain_button");
        tapElement("checkout_page_top_up_dana_metode_lain_button");
    }

    public void selectTopUpDANAMetodeLainVPCheckout(){
        waitForVisibilityOf("checkout_page_top_up_dana_metode_lain_vp_button");
        tapElement("checkout_page_top_up_dana_metode_lain_vp_button");
    }

    public void selectTopUpDANAMetodeLainCheckout(String page){
        if (page.equals("MP")) {
            selectTopUpDANAMetodeLainMPCheckout();
        } else if (page.equals("VP")){
            selectTopUpDANAMetodeLainVPCheckout();
        } else {
            Assert.fail("Choose between MP and VP");
        }
    }

    public void selectDANAasVPPaymentMethodForUnbindUsers() {
        selectMetodePembayaranForVPProduct();
        selectDANAinVPMetodePembayaranScreen();
    }

    public void selectSALDOasVPPaymentMethodForUnbindUsers() {
        selectMetodePembayaranForVPProduct();
        selectSALDOinVPMetodePembayaranScreen();
    }

    public void selectDANAAsSerbuSeruPaymentMethod() {
        waitForElementClickable("serbu_seru_select_payment_method", 10);
        tapElement("serbu_seru_select_payment_method");

        if (isElementExist("serbu_seru_topup_dana_button", 5)) {
            tapElement("serbu_seru_topup_dana_button");
        } else if (isElementExist("serbu_seru_aktifkan_dana_button", 5)) {
            tapElement("serbu_seru_aktifkan_dana_button");
        } else {
            tapElement("serbu_seru_dana_payment_method");
        }
    }

    public void selectMetodePembayaranForVPProduct() {
        waitForVisibilityOf("checkout_page_vp_metode_pembayaran",20);
        tapElement("checkout_page_vp_metode_pembayaran");
    }

    public void selectDANAinVPMetodePembayaranScreen() {
        waitForVisibilityOf("checkout_medote_bayar_text", 20);
        swipeUpToElement("checkout_page_dana_option_payment_method");
        tapElement("checkout_page_dana_option_payment_method");
    }

    public void selectSALDOinVPMetodePembayaranScreen() {
        tapElement("checkout_non_marketplace_saldo_choosen");
    }

    public void selectTopUpDANAFromDompetMPCheckout() {
        verifyElementExist("checkout_page_top_up_dana_saldo_button");
        tapElement("checkout_page_top_up_dana_saldo_button");
    }

    public void selectTopUpDANAFromDompetVPCheckout(){
        waitForVisibilityOf("checkout_page_top_up_dana_bukadompet_vp_button");
        tapElement("checkout_page_top_up_dana_bukadompet_vp_button");
    }

    public void selectTopUpDANAFromDompetCheckout(String page){
        if (page.equals("MP")) {
            selectTopUpDANAFromDompetMPCheckout();
        } else if (page.equals("VP")){
            selectTopUpDANAFromDompetVPCheckout();
        } else {
            Assert.fail("Choose between MP and VP");
        }
    }

    public void inputTopupBDAmount(String amount) {
        if (isElementExist("dana_checkout_pindahkan_nominal_field",5)) {
            typeAndEnterValueWithTimeOut("dana_checkout_pindahkan_nominal_field", amount);
        } else {
            typeAndEnterValueWithTimeOut("dana_checkout_pindahkan_nominal_field_alt", amount);
        }
    }

    public void tapConfirmTopUpBDCheckout() {
        waitForElementClickable("dana_checkout_pindahkan_konfirmasi_button", 15);
        tapElement("dana_checkout_pindahkan_konfirmasi_button");
    }

    public void verifyTopUpBDCheckoutSuccess() {
        try {
            verifyElementExist("dana_checkout_pindahkan_success_text");
        } catch (Exception e) {
            verifyElementExist("checkout_page_top_up_dana_title");
        }
    }

    public void verifySuccessBindAlchemyVPCheckoutPage() {
        verifyElementExist("dana_checkout_bind_success_text");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void verifyDANABoundAlchemyMPCheckoutPage() {
        verifyElementExist("checkout_page_top_up_dana_metode_lain_button");
        verifyElementExist("checkout_page_dana_id_text");
        verifyElementExist("checkout_page_dana_nominal_text");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void verifyDANABoundAlchemyVPCheckoutPage() {
        verifyElementExist("checkout_page_top_up_dana_metode_lain_vp_button");
        verifyElementExist("checkout_page_dana_id_text");
        verifyElementExist("checkout_page_dana_nominal_text");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void verifyDANABoundSerbuSeruCheckoutPage() {
        tapElement("alchemy_navbar_back_button");
        tapElement("serbu_seru_select_payment_method");
        verifyElementNotExist("serbu_seru_aktifkan_dana_button");
        verifyElementNotExist("serbu_seru_dana_belum_aktif_text");
        HelperData.setLastActionPage(new SerbuSeruPage(iosDriver));
    }

    public void tapDANAPaymentMethod(){
        if (isElementExist("dana_payment_selection_screen_label",5)) {
            tapElement("dana_payment_selection_screen_label");
        } else if (isElementExist("dana_payment_selection_screen_radio_btn")) {
            tapElement("dana_payment_selection_screen_radio_btn");
        } else {
            tapElement("dana_payment_selection_screen_card_info");
        }
    }

    public void verifyInfoDANAFreeze() {
        verifyElementExist("dana_algebra_checkout_freeze_dana_info");
        verifyElementExist("dana_algebra_checkout_freeze_dana_bantuan_btn");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void verifyInfoDANAFreezeBySaldoFreeze() {
        verifyElementExist("dana_alchemy_vp_checkout_freeze_title");
        verifyElementExist("dana_alchemy_mp_checkout_freeze_by_saldo_freeze");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void verifyNoTopupWithSaldo(){
        verifyElementNotExist("checkout_page_top_up_dana_saldo_button");
    }

    public void verifyDANAFrozenAlchemyVPCheckout() {
        waitForVisibilityOf("dana_alchemy_vp_checkout_freeze_title");
        verifyElementDisplayed("dana_alchemy_vp_checkout_freeze_desc");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void notEnoughDanaError() {
        if (!isElementVisible("checkout_marketplace_dana_balance_insufficient")) {
            verifyElementExist("checkout_marketplace_dana_balance_insufficient_alt");
        }
        tapElement("checkout_page_bayar_dengan_dana_button");
        if (!isElementVisible("checkout_marketplace_dana_balance_insufficient")) {
            verifyElementExist("checkout_marketplace_dana_balance_insufficient_alt");
        }
    }

    public void unbindDanaError() {
        waitForVisibilityOf("checkout_metode_bayar_name");
        swipeUpToElement("checkout_page_dana_hubungkan_DANA");
    }

    public void userOnKendalaTeknisPage() {
        waitForVisibilityOf("dana_kendala_teknis_title_text");
        verifyElementDisplayed("dana_kendala_teknis_description_text");
        tapElement("dana_kendala_teknis_lihat_pesanan_button_text");
    }

    public void verifyPopUpVerificationPhoneAlchemyMPCheckout() {
        verifyElementExist("dana_checkout_pop_up_verification_phone");
        verifyElementExist("dana_checkout_label_pop_up_verification_phone");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));

    }

    public void skipVpPaymentOndboarding() {
        if (isElementVisible("onboarding_lanjut_button")) tapElement("onboarding_lanjut_button");
    }

    public void verifySaldoFreezeOldMPCheckout() {
        swipeUpToElement("dana_old_checkout_saldo_title");
        tapElement("dana_old_checkout_saldo_title");
        verifyElementDisplayed("dana_old_checkout_saldo_freeze_desc");
    }

    public void verifyInfoDANAFreezeBySaldoFreezeOldMPCheckout() {
        swipeUpToElement("dana_old_checkout_dana_title");
        tapElement("dana_old_checkout_dana_title");
        verifyElementDisplayed("dana_old_checkout_dana_freeze_desc");
        HelperData.setLastActionPage(new DANAPaymentPage(iosDriver));
    }

    public void setTotalAmountTrx() {
        waitForVisibilityOf("dana_checkout_marketplace_total_pembayaran_value",20);
        DANAData.setTotalAmountTrx(Integer.parseInt(
                getElementValue("dana_checkout_marketplace_total_pembayaran_value")
                .replace(".", "").replace("Rp", "")));
    }

    public void validateBalanceAfterTrx(String trx, int danaAfterTrx, int creditsAfterTrx, int dompetAfterTrx) {
        int danaReduction = 0;
        int creditsReduction = 0;

        switch (trx) {
            case "dana only":
                danaReduction = DANAData.getTotalAmountTrx();
                break;
            case "credits only":
                creditsReduction = DANAData.getTotalAmountTrx();
                break;
            case "dana mix credits":
                danaReduction = DANAData.getTotalAmountTrx();
                creditsReduction = DANAData.getMixAmountTrx();
                break;
            case "other mix dana":
                danaReduction = 100; //hardcoded on mixpayment step
                break;
            case "other mix dana and credits":
                danaReduction = 100; //hardcoded on mixpayment step
                creditsReduction = 100; //hardcoded on mixpayment step
                break;
            default:
                Assert.fail("Undefined payment method!");
                break;
        }
        assertEquals(DANAData.getDanaBalance() - danaReduction, danaAfterTrx);
        assertEquals(DANAData.getCreditsBalance() - creditsReduction, creditsAfterTrx);
        assertEquals(DANAData.getBukaDompetBalance(), dompetAfterTrx);
    }

    public void validateBalanceAfterRefund(int danaAfterRefund, int creditsAfterRefund, int dompetAfterRefund) {
        assertEquals(DANAData.getDanaBalance(), danaAfterRefund);
        assertEquals(DANAData.getCreditsBalance(), creditsAfterRefund);
        assertEquals(DANAData.getBukaDompetBalance(), dompetAfterRefund);
    }

    public void validateTrxPaid() {
        waitForVisibilityOf("detail_pembelian_page_title",10);

        for (int i = 0; i < 5; i++) {
            if (!isElementVisible("detail_pembelian_status_pesanan_text",5)) {
                tapElement("detail_pembelian_informasi_pesanan");
            }
            if (isElementVisible("detail_pembelian_dibayar_text",5)) {
                break;
            }
            doPullRefresh(1);
        }
        verifyElementExist("detail_pembelian_dibayar_text");
    }

    public void validateTrxRefunded() {
        doPullRefresh(1);
        waitForVisibilityOf("detail_pembelian_page_title",10);

        for (int i = 0; i < 5; i++) {
            swipeUpToElement("detail_pembelian_catatan_pelapak_text");
            if (isElementVisible("detail_pembelian_dikembalikan_text",5)) {
                break;
            }
            doPullRefresh(1);
        }
        verifyElementExist("detail_pembelian_dikembalikan_text");
    }

    public void validateBalanceAfterTopup(int danaAfterTopup, int creditsAfterTopup, int dompetAfterTopup, int topup) {
        assertEquals(DANAData.getDanaBalance() + topup, danaAfterTopup);
        assertEquals(DANAData.getCreditsBalance(), creditsAfterTopup);
        assertEquals(DANAData.getBukaDompetBalance() - topup, dompetAfterTopup);
    }
}
