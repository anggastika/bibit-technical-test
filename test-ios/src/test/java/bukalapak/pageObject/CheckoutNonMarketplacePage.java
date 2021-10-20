package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class CheckoutNonMarketplacePage extends BasePage {

    public CheckoutNonMarketplacePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnCheckoutPage() {
        changeContext().toNative();
        waitForVisibilityOf("checkout_non_marketplace_alchemy_title_text", 30);
        HelperData.setLastActionPage(new CheckoutNonMarketplacePage(iosDriver));
    }

    public void setTotalPayment() {
        String locator = "checkout_non_marketplace_total_biaya_text";

        if (!isElementVisible(locator)) {
            locator = "checkout_non_marketplace_alchemy_total_payment_text";
        }
        TransactionData.setTotalPrice(getElementValue(locator));
    }

    public void choosePaymentMethod(String paymentMethod) {
        String tmpPaymentMethod = paymentMethod.toLowerCase();
        int count = 0;

        do {
            swipeUp(0.7, 0.1);
            count++;
        } while (count < 5);

        swipeDownToElement("checkout_non_marketplace_alchemy_choose_payment_button");

        if (isElementVisible("checkout_non_marketplace_alchemy_choose_payment_button", 15)) {
            tapElement("checkout_non_marketplace_alchemy_choose_payment_button");
            verifyElementExist("checkout_non_marketplace_alchemy_payment_screen_title_text");

            switch (tmpPaymentMethod) {
                // TODO: function to select method as Indomaret
                case "indomaret":
                case "alfamart":
                    swipeUpToElement("checkout_non_marketplace_alchemy_payment_screen_gerai_option");
                    tapElement("checkout_non_marketplace_alchemy_payment_screen_gerai_option");
                    tapElement("checkout_non_marketplace_alchemy_payment_screen_" + tmpPaymentMethod + "_option");
                    break;
                case "virtual_account":
                    tapElement("checkout_non_marketplace_alchemy_payment_screen_virtual_account_option");
                    tapElement("checkout_non_marketplace_alchemy_payment_screen_virtual_account_bca_option");
                    break;
                default:
                    tapElement("checkout_non_marketplace_alchemy_payment_screen_" + tmpPaymentMethod + "_option");
                    break;
            }
            TransactionData.setPaymentMethod(tmpPaymentMethod);
            tapElement("checkout_non_marketplace_alchemy_payment_screen_use_method_button");
        } else {
            String payment_option = "checkout_non_marketplace_" + tmpPaymentMethod + "_choosen";

            swipeUpToElement(payment_option);
            tapElement(payment_option);
        }
        TransactionData.setPaymentMethod(tmpPaymentMethod);
    }

    public void choosePaymentOldCheckout(String paymentMethod) {
        String tmpPaymentMethod = paymentMethod.toLowerCase();

        switch (tmpPaymentMethod) {
            case "saldo":
                waitForVisibilityOf("checkout_non_marketplace_non_alchemy_payment_saldo");
                tapElement("checkout_non_marketplace_non_alchemy_payment_saldo");
                break;
            case "indomaret":
                swipeUpToElement("checkout_non_marketplace_non_alchemy_payment_indomaret");
                tapElement("checkout_non_marketplace_non_alchemy_payment_indomaret");
                break;
            case "virtual_account":
                swipeUpToElement("checkout_non_marketplace_non_alchemy_payment_virtual_account");
                tapElement("checkout_non_marketplace_non_alchemy_payment_virtual_account");
                waitForVisibilityOf("checkout_non_marketplace_non_alchemy_payment_virtual_account_bca");
                break;
            default:
                swipeUpToElement("checkout_non_marketplace_non_alchemy_payment_indomaret");
                waitForVisibilityOf("checkout_non_marketplace_non_alchemy_payment_indomaret");
                tapElement("checkout_non_marketplace_non_alchemy_payment_indomaret");
                break;
        }
        swipeUpToElement("checkout_non_marketplace_non_alchemy_bayar_button");
        tapElement("checkout_non_marketplace_non_alchemy_bayar_button");
        if (isElementVisible("checkout_non_marketplace_non_alchemy_lanjutkan_pembayaran_button", 15)) {
            tapElement("checkout_non_marketplace_non_alchemy_lanjutkan_pembayaran_button");
        }
        TransactionData.setPaymentMethod(tmpPaymentMethod);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void setTotalTagihan() {
        if (isElementVisible("checkout_non_marketplace_jumlah_tagihan")) {
            TransactionData.setTotalPrice(getElementValue("checkout_non_marketplace_jumlah_tagihan"));
        } else if (isElementVisible("invoice_non_marketplace_total_pembelian")) {
            TransactionData.setTotalPrice(getElementValue("invoice_non_marketplace_total_pembelian"));
        } else if (isElementVisible("checkout_non_marketplace_total_belanja")) {
            TransactionData.setTotalPrice(getElementValue("checkout_non_marketplace_total_belanja"));
        } else if (isElementVisible("checkout_non_marketplace_alchemy_total_payment_text")) {
            TransactionData.setTotalPrice(getElementValue("checkout_non_marketplace_alchemy_total_payment_text"));
        }
    }

    public void tapOnBayarButton() {
        String locator = "checkout_non_marketplace_payment_button";
        if (!isElementVisible("checkout_non_marketplace_payment_button")) {
            locator = "checkout_non_marketplace_alchemy_payment_button";
        }

        swipeDownToElement(locator);
        tapElement(locator);

        // handling for VA that already used
        continuePopUpVirtualAccountPayment();
    }

    public void tapBayarSekarangButton() {
        tapElement("checkout_bayar_sekarang_button");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyBCAKlikpayWebView() {
        verifyElementExist("tagihan_bcaKlikPay_text");
        verifyElementExist("web_view_email_text");
        verifyElementExist("web_view_password_text");
        verifyElementExist("web_view_login_button");

        waitForElementClickable("base_back_button", 10);
        tapElement("base_back_button");
    }

    public void continuePopUpVirtualAccountPayment() {
        if (isElementVisible("checkout_non_marketplace_lanjutkan_pembayaran_button", 5)) {
            tapElement("checkout_non_marketplace_lanjutkan_pembayaran_button");
        }
    }

    public void choosePaymentForceBukaDompet() {
        swipeToDirection(Direction.DOWN);
        swipeToDirection(Direction.DOWN);
        if (isElementVisible("checkout_non_marketplace_bayar_dengan_saldo_button")) {
            tapElement("checkout_non_marketplace_bayar_dengan_saldo_button");
        } else {
            tapElement("checkout_non_marketplace_bayar_dengan_bukadompet_button");
        }
        if (isElementVisible("buka_emas_harga_diperbarui_label")) {
//            new BukaEmasPage(iosDriver).approveNewGoldPrice();
        }
    }

    public void enterVoucherCode(String voucherCode) {
        tapVoucherCheckbox();
        enterVoucherCodeField(voucherCode);
        tapOnGunakanButton();
        waitForVisibilityOf("voucher_message_text");
        validateVoucherCodeStatus();
    }

    public void tapVoucherCheckbox() {
        if (isElementVisible("force_gunakan_voucher_checkbox")) {
            tapElement("force_gunakan_voucher_checkbox");
        } else {
            swipeDownToElement("payment_voucher_text");

            if (isElementVisible("checkout_non_marketplace_voucher_text")) {
                tapElement("checkout_non_marketplace_voucher_text");
            } else if (isElementVisible("checkout_non_marketplace_alchemy_voucher_button")) {
                tapElement("checkout_non_marketplace_alchemy_voucher_button");
            }
        }
    }

    public void enterVoucherCodeField(String voucher) {
        String codeVoucher = dotenv.get(voucher);
        String locator = "voucher_voucher_field";

        if (isElementVisible("checkout_non_marketplace_alchemy_voucher_field")) {
            locator = "checkout_non_marketplace_alchemy_voucher_field";
        }
        typeAndEnterValueWithTimeOut(locator, codeVoucher);
    }

    public void tapOnGunakanButton() {
        tapElement("voucher_gunakan_button");
    }

    public void validateVoucherCodeStatus() {
        assertTrue(isElementVisible("voucher_message_text"), "No message");
    }

    public void tapDANAAlgebraVPCheckout() {
        swipeUpToElement("checkout_non_marketplace_dana_choosen");
        tapElement("checkout_non_marketplace_dana_choosen");
    }

    public void tapTambahDANAAlgebraVPCheckout() {
        tapElement("checkout_non_marketplace_dana_tambah_button");
    }

    public void goToMetodePembayaranPageAlchemyVPCheckout() {
        verifyElementExist("checkout_non_marketplace_alchemy_choose_payment_button");
        tapElement("checkout_non_marketplace_alchemy_choose_payment_button");
        verifyElementExist("checkout_non_marketplace_alchemy_payment_screen_title_text");
    }

    public void goToDANAAlchemyVPCheckout() {
        verifyElementExist("checkout_non_marketplace_alchemy_payment_screen_dana_option");
        tapElement("checkout_non_marketplace_alchemy_payment_screen_dana_option");
    }

    public void userOnDANAAlchemyVPCheckout() {
        verifyElementExist("checkout_non_marketplace_alchemy_payment_screen_dana_option_page_title");
        verifyElementExist("checkout_non_marketplace_alchemy_payment_screen_dana_option_page_topup_bd_label");
        verifyElementExist("checkout_non_marketplace_alchemy_payment_screen_dana_option_page_topup_vp_label");
        HelperData.setLastActionPage(new CheckoutNonMarketplacePage(iosDriver));
    }

    public void tapChooseTopUpBDAlchemyVPCheckout() {
        tapElement("checkout_non_marketplace_alchemy_payment_screen_dana_option_page_topup_bd_label");
    }

    public void tapConfirmTopUpBDAlchemyVPCheckout() {
        verifyElementExist("dana_checkout_pindahkan_konfirmasi_button");
        tapElement("dana_checkout_pindahkan_konfirmasi_button");
    }

    public void verifyTopUpBDAlchemyVPCheckoutSuccess() {
        verifyElementExist("dana_checkout_pindahkan_success_text");
        HelperData.setLastActionPage(new CheckoutNonMarketplacePage(iosDriver));
    }

    public void tapTopUpBDAlgebraVPCheckout() {
        verifyElementExist("checkout_non_marketplace_dana_pindahkan_button");
        tapElement("checkout_non_marketplace_dana_pindahkan_button");
    }

    public void tapConfirmTopUpBDAlgebraVPCheckout() {
        verifyElementExist("dana_checkout_pindahkan_konfirmasi_button");
        tapElement("dana_checkout_pindahkan_konfirmasi_button");
    }

    public void verifyTopUpBDAlgebraVPCheckoutSuccess() {
        try {
            verifyElementExist("dana_checkout_pindahkan_success_text");
        } catch (Exception e) {
            verifyElementExist("checkout_non_marketplace_pilih_metode_text");
        }
        HelperData.setLastActionPage(new CheckoutNonMarketplacePage(iosDriver));
    }

    public void userOnAlgebraVPCheckoutPage() {
        verifyElementExist("checkout_non_marketplace_pilih_metode_text");
        HelperData.setLastActionPage(new CheckoutNonMarketplacePage(iosDriver));
    }

    public void tapTambahDANAAlchemyVPCheckout() {
        tapElement("checkout_non_marketplace_alchemy_payment_screen_dana_option_page_topup_vp_label");
    }

    public void cancelTransaction() {
        tapElement("checkout_non_marketplace_alchemy_back_button");
        verifyElementExist("checkout_non_marketplace_alchemy_cancel_trx_message_text");
        tapElement("checkout_non_marketplace_alchemy_cancel_button");
    }

    public void tapOnVoucherField() {
        swipeUpToElement("checkout_non_marketplace_punya_kode_voucher_bukalapak");
        tapElement("checkout_non_marketplace_punya_kode_voucher_bukalapak", 5);
    }

    public void pasteVoucherCode() {
        typeAndEnterValue("checkout_non_marketplace_masukkan_kode_voucher_field", iosDriver.getClipboardText());
    }

    public void verifyTersalin() {
        waitForVisibilityOf("checkout_non_marketplace_masukkan_kode_voucher_field", 5);

        String voucherCode = getElementValue("checkout_non_marketplace_masukkan_kode_voucher_field");

        verifyCopiedText(voucherCode);
        HelperData.setLastActionPage(new CheckoutNonMarketplacePage(iosDriver));
    }

    public void tapCreditsAlchemyVPCheckout() {
        waitForVisibilityOf("checkout_non_marketplace_alchemy_payment_screen_credits_option");
        tapElement("checkout_non_marketplace_alchemy_payment_screen_credits_option");
    }

    public void verifyDANABoundCreditsAlchemyVPCheckoutPage() {
        waitForVisibilityOf("checkout_non_marketplace_alchemy_credits_page_title");
        verifyElementExist("checkout_non_marketplace_alchemy_credits_page_balance");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapAktifkanButtonVPAlgebraCheckout() {
        tapElement("checkout_non_marketplace_dana_aktifkan_button");
    }

    public void verifyBindAlgebraVPCheckoutSuccess() {
        verifyElementExist("dana_checkout_bind_success_text");
        HelperData.setLastActionPage(new CheckoutNonMarketplacePage(iosDriver));
    }

    public void verifyDANABoundAlgebraVPCheckoutPage() {
        verifyElementNotExist("checkout_non_marketplace_dana_aktifkan_button");
        verifyElementExist("checkout_non_marketplace_dana_tambah_button");
        HelperData.setLastActionPage(new CheckoutNonMarketplacePage(iosDriver));
    }

    public void setMixPayment(String mix, String nominal) {
        verifyElementExist("checkout_page_sebagian_pakai_" + mix.toLowerCase() + "_summary");
        tapElement("checkout_alchemy_mix_payment_checkbox");
        waitForVisibilityOf("checkout_non_marketplace_alchemy_input_mixpayment",10);
        typeAndEnterValue("checkout_non_marketplace_alchemy_input_mixpayment", nominal);
        tapElement("checkout_page_mixpayment_simpan_btn");
    }

    public void tapMetodePembayaranMenu() {
        for (int i = 0; i < 5; i++) {
            if (isElementVisible("checkout_medote_bayar_text")) {
                //Try to tap the element by label
                tapElement("checkout_medote_bayar_text");
            } else if (isElementVisible("checkout_non_marketplace_alchemy_choose_payment_button")) {
                //Try to tap the element by xpath
                tapElement("checkout_non_marketplace_alchemy_choose_payment_button");
            } else {
                //Already on payment method page
                break;
            }
            waitFor(2);
        }
    }

    public void selectBankOnVirtualAccountMenu(String bank) {
        LogUtil.info("user select VA as payment method");
        verifyElementDisplayed("checkout_non_marketplace_non_alchemy_payment_virtual_account");
        tapElement("checkout_non_marketplace_non_alchemy_payment_virtual_account");
        String virtualAccountBank = bank.replace(" ", "_");
        swipeUpToElement("checkout_non_marketplace_alchemy_payment_screen_" + virtualAccountBank + "_option");
        tapElement("checkout_non_marketplace_alchemy_payment_screen_" + virtualAccountBank + "_option");
    }

    public void submitVoucherCode(String voucher) {
        swipeUpToElement("train_detail_checkout_voucher");
        tapElement("checkout_non_marketplace_voucher_button");
        typeAndEnterValue("checkout_non_marketplace_voucher_field", voucher);
        tapElement("checkout_non_marketplace_voucher_submit_button");
        verifyElementDisplayed("checkout_non_marketplace_voucher_validation");
    }

    public void selectStoreOnGeraiMenu(String store) {
        swipeUpToElement("checkout_non_marketplace_alchemy_payment_screen_gerai_option");
        tapElement("checkout_non_marketplace_alchemy_payment_screen_gerai_option");
        String gerai = store.replace(" ", "_");
        tapElement("checkout_non_marketplace_alchemy_payment_screen_" + gerai + "_option");
    }

    public void selectPaymentMethod(String paymentMethod) {
        String tmpPaymentMethod = paymentMethod.toLowerCase();

        tapMetodePembayaranMenu();

        switch (tmpPaymentMethod) {
            case "indomaret":
            case "alfamart":
            case "pos indonesia":
                selectStoreOnGeraiMenu(tmpPaymentMethod);
                break;
            case "virtual account bca":
            case "virtual account bri":
            case "virtual account bri (briva)":
            case "virtual account bri syariah":
            case "virtual account mandiri":
            case "virtual account mandiri syariah":
            case "virtual account btn":
            case "virtual account cimb niaga":
            case "virtual account bni syariah":
            case "virtual account bni":
            case "virtual account danamon":
            case "virtual account bsi":
                selectBankOnVirtualAccountMenu(tmpPaymentMethod);
                break;
            default:
                tapElement("checkout_non_marketplace_alchemy_payment_screen_" + tmpPaymentMethod + "_option");
                break;
        }

        swipeUpToElement("checkout_non_marketplace_alchemy_payment_screen_use_method_button");
        tapElement("checkout_non_marketplace_alchemy_payment_screen_use_method_button");
        TransactionData.setPaymentMethod(tmpPaymentMethod);
        HelperData.setLastActionPage(new CheckoutNonMarketplacePage(iosDriver));
    }

    public void clearDANAVoucher() {
        swipeUpToElement("checkout_non_marketplace_rincian_harga_txt");
        if (isElementVisible("checkout_non_marketplace_dana_voucher_used", 5)) {
            tapElement("checkout_non_marketplace_dana_voucher_used");
            validateNotExist("checkout_non_marketplace_dana_voucher_used",5);
        }
        swipeDownToElement("checkout_non_marketplace_alchemy_choose_payment_button");
    }

    public void verifySaldoFreezeVPCheckout() {
        swipeUpToElement("checkout_non_marketplace_non_alchemy_payment_saldo");
        verifyElementDisplayed("checkout_non_marketplace_saldo_freeze_text");
    }

    public void verifyInfoDANAFreezeBySaldoFreezeAlchemyVPCheckout() {
        verifyElementDisplayed("checkout_non_marketplace_alchemy_payment_screen_dana_option_page_title");
        verifyElementDisplayed("checkout_non_marketplace_dana_freeze_by_saldo_freeze_title");
        verifyElementDisplayed("checkout_non_marketplace_dana_freeze_by_saldo_freeze_desc");
        HelperData.setLastActionPage(new CheckoutNonMarketplacePage(iosDriver));
    }
}
