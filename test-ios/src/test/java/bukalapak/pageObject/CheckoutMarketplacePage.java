package bukalapak.pageObject;

import bukalapak.data.*;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static bukalapak.TestInstrument.dotenv;

public class CheckoutMarketplacePage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(CheckoutMarketplacePage.class);
    private static int courierFee = 0;
    private static int hargaPerBarang = 0;
    private static QuantityByPrice quantityByPrice = (p, rounded) -> (rounded)
            ? (int) Math.ceil((double) p / CartData.getItemPrice())
            : (int) Math.floor((double) p / CartData.getItemPrice());

    public CheckoutMarketplacePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    private interface QuantityByPrice {
        int getQty(int price, Boolean rounded);
    }

    public void userOnCheckoutPage() {
        // for make sure empty page not display
        waitLoadingBar();
        if (isElementVisible("checkout_marketplace_revamp_assurance_popup_text", 10)) {
            tapElement("checkout_marketplace_revamp_assurance_nanti_saja_button");
        }
        verifyElementExist("checkout_marketplace_alchemy_page_title", 60, "checkout_marketplace_alchemy_page_title");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public String getAddress() {
        String result;
        if (!isElementVisible("checkout_marketplace_alchemy_alamat_pengiriman_text")) {
            nativeSwipeDown();
        }
        try {
            if (isElementVisible("checkout_marketplace_alchemy_alamat_pengiriman_text")) {
                waitForVisibilityOf("checkout_marketplace_alchemy_alamat_terpilih_text", 50);
            }
            result = getElementValue("checkout_marketplace_alchemy_alamat_terpilih_text");
        } catch (Exception e) {
            result = getElementValue("checkout_marketplace_alchemy_alamat_terpilih_new_ui_text");
        }
        return result;
    }

    public String getTotalShippingFee() {
        String result;
        swipeToLocator("checkout_marketplace_alchemy_rincian_harga_text");
        if (isElementVisible("checkout_marketplace_alchemy_ongkos_kirim_text")) {
            result = getElementValue("checkout_marketplace_alchemy_ongkos_kirim_text");
        } else {
            return "0";
        }
        return result.replaceAll("[^0-9]", "");
    }

    public String getTotalItemsFee() {
        String result;
        result = getElementValue("checkout_marketplace_alchemy_total_harga_barang_price");
        return result.replaceAll("[^0-9]", "");
    }

    public String getTotalPrice(Boolean isMixpayment, String totalItemsPrice, String totalShippingFee) {
        String result;
        result = getElementValue("checkout_marketplace_alchemy_total_pembayaran_price");
        String finalPrice = result.replaceAll("[^0-9]", "");
        if (isMixpayment) {
            Double mixUsed = 1.0;
            Double totalBeforeMix = (Double.parseDouble(totalItemsPrice)) + (Double.parseDouble(totalShippingFee));
            Double totalNow = Double.parseDouble(finalPrice);
            assertTrue((Math.abs((totalBeforeMix - mixUsed) - totalNow) < 0.01));
        }
        return finalPrice;
    }

    public String getBiayaAdmin() {
        swipeToLocator("checkout_marketplace_biaya_admin_text");
        String biayaAdmin = getTextFromElement("checkout_marketplace_biaya_admin_fee").replace(".", "").replace("Rp", "");
        return biayaAdmin;
    }

    public void validateAdminFeeFromCartAndCheckoutPage() {
        assertTrue(TransactionData.getAdminFee().equals(getBiayaAdmin()));
    }

    public void tapOnPaymentMethodBtn() {
        tapElement("checkout_metode_bayar_icon", 20);
    }

    public void usePaymentMethod() {
        waitForVisibilityOf("checkout_gunakan_metode_ini_button", 5);
        swipeDownToElement("checkout_gunakan_metode_ini_button");
        tapElement("checkout_gunakan_metode_ini_button");
        waitFor(5);
    }

    public void selectPaymentMethod(String payment_method, String service) {
        MtxData.setPaymentMethod(payment_method);
        TransactionData.setPaymentMethod(payment_method);
        if (isElementVisible("checkout_metode_bayar_icon",5)) {
            tapElement("checkout_metode_bayar_icon");
            tapElement("checkout_marketplace_alchemy_metode_pembayaran");
        } else {
            swipeUpToElement("checkout_metode_pembayaran_section_title");
            tapElement("checkout_metode_bayar_button");
        }

        swipeUpToElement(constructLocator("checkout_marketplace_payment_method_name", payment_method));
        tapElement(constructLocator("checkout_marketplace_payment_method_name", payment_method));

        if (!(payment_method.equalsIgnoreCase("saldo")
                || payment_method.equalsIgnoreCase("dana")
                || payment_method.equalsIgnoreCase("transfer bank manual"))) {
            swipeUpToElement(constructLocator("checkout_marketplace_payment_method_service", service));
            tapElement(constructLocator("checkout_marketplace_payment_method_service", service));
        }

    }

    public void chooseNewPaymentMethod(String payment_method, String payment_choose) {
        tapElement("checkout_metode_bayar_icon");
        tapElement("checkout_marketplace_alchemy_metode_pembayaran");
        String payment_option = "checkout_marketplace_" + payment_method.toLowerCase().replaceAll(" ", "_") + "_option";
        swipeUpToElement(payment_option);
        tapElement(payment_option);
        String payment_choosen = "checkout_marketplace_" + payment_choose.toLowerCase().replaceAll(" ", "_") + "_choosen";
        tapElement(payment_choosen);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void choosePaymentMethod(String payment_method) {
        String payment;
        int maxLoop = 0;
        String payment_option = "checkout_marketplace_" + payment_method.toLowerCase().replaceAll(" ", "_") + "_option";
        do {
            nativeSwipeDown();
            maxLoop++;
            if (maxLoop == 5) {
                break;
            }
        } while (isElementVisible("checkout_metode_bayar_text") == false);
        waitForVisibilityOf("checkout_metode_bayar_icon", 100);
        tapElement("checkout_metode_bayar_icon");
        nativeSwipeDown();
        swipeUpToElement(payment_option);
        waitForVisibilityOf(payment_option);
        payment = payment_method.replaceAll("_", " ");
        switch (payment) {
            case "Transfer Bank":
                tapElement(payment_option);
                assertTrue(isElementVisible("checkout_marketplace_transfer_bank_option"), "Transfer Bank payment is not available!");
                tapElement("checkout_marketplace_alchemy_metode_pembayaran_choosen_button");
                TransactionData.setPaymentMethod("transfer");
                break;
            case "Virtual Account":
                tapElement(payment_option);
                assertTrue(isElementVisible("checkout_marketplace_virtual_account_option"), "Virtual Account payment is not available!");
                tapElement("checkout_marketplace_radio_button_bank");
                tapElement("checkout_marketplace_alchemy_metode_pembayaran_choosen_button");
                TransactionData.setPaymentMethod("VA-BCA");
                break;
            case "Credits":
                tapElement(payment_option);
                assertTrue(isElementVisible("checkout_marketplace_credits_option"), "Credits payment is not available!");
                tapElement("checkout_marketplace_alchemy_metode_pembayaran_choosen_button");
                TransactionData.setPaymentMethod("credits");
                break;
            case "Kartu Kredit atau Debit":
                tapElement(payment_option);
                waitForVisibilityOf("checkout_marketplace_kartu_kredit_atau_debit_option", 60);
                assertTrue(isElementVisible("checkout_marketplace_kartu_kredit_atau_debit_option"), "Kartu Kredit atau Debit payment is not available!");
                waitForVisibilityOf("checkout_marketplace_alchemy_metode_pembayaran_choosen_button", 60);
                tapElement("checkout_marketplace_alchemy_metode_pembayaran_choosen_button");
                break;
            case "Bayar di Tempat(COD)":
                tapElement(payment_option);
                tapElement("checkout_marketplace_alchemy_metode_pembayaran_choosen_button");
                break;
            case "Cicilan Tanpa Kartu Kredit":
                tapElement(payment_option);
                waitForVisibilityOf("checkout_marketplace_kredivo_choosen", 30);
                tapElement("checkout_marketplace_kredivo_choosen");
                waitForVisibilityOf("checkout_marketplace_alchemy_metode_pembayaran_choosen_button", 60);
                tapElement("checkout_marketplace_alchemy_metode_pembayaran_choosen_button");
                break;
            case "Bukatabungan":
                tapElement(payment_option);
                tapElement("checkout_marketplace_alchemy_metode_pembayaran_choosen_button");
                break;
            default:
                LOGGER.info("Invalid parameter or not yet implemented");
        }

        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void choosePaymentMethodSaldo() {
        waitForVisibilityOf("checkout_marketplace_alchemy_total_pembayaran_price", 20);
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
        swipeUpToElement("checkout_marketplace_saldo_option");
        tapElement("checkout_marketplace_saldo_option");
        tapElement("checkout_marketplace_alchemy_metode_pembayaran_choosen_button");
        TransactionData.setPaymentMethod("saldo");
    }

    public void changePaymentMethodProcessOnInvoiceDetail(String newPaymentMethod) {
        assertTrue(isElementVisible("checkout_change_payment_title_page", 45), "Halaman change payment tidak ditemukan!");
        swipeUpToElement(constructLocator("checkout_change_payment_name", newPaymentMethod));
        tapElement(constructLocator("checkout_change_payment_name", newPaymentMethod));
        swipeUpToElement("checkout_marketplace_payment_button");
        TransactionData.setTotalPrice(getElementValue("checkout_total_price_text"));
        TransactionData.setPaymentMethod(newPaymentMethod);
        MtxData.setPaymentMethod(newPaymentMethod);
        MtxData.setTotalPaymentCheckout(TransactionData.getTotalPrice());
        tapElement("checkout_marketplace_payment_button");
        if (isElementVisible("detail_pembelian_lanjutkan_pembayaran_button")) {
            tapElement("detail_pembelian_lanjutkan_pembayaran_button");
        }
    }

    public void changePaymentMethodProcessOnInvoiceDetail(String newPaymentMethod, String options) {
        assertTrue(isElementVisible("checkout_change_payment_title_page", 45), "Halaman change payment tidak ditemukan!");
        swipeUpToElement(constructLocator("checkout_change_payment_name", newPaymentMethod));
        tapElement(constructLocator("checkout_change_payment_name", newPaymentMethod));

        /* Choose Option from Several Payment Methods i.e Virtual Account */
        assertTrue(isElementVisible(constructLocator("checkout_change_payment_name", options), 60), "opsi dari payment method yang digunakan tidak ditemukan!");
        swipeUpToElement(constructLocator("checkout_change_payment_name", options));
        tapElement(constructLocator("checkout_change_payment_name", options));

        swipeUpToElement("checkout_marketplace_payment_button");
        TransactionData.setTotalPrice(getElementValue("checkout_total_price_text"));
        TransactionData.setPaymentMethod(newPaymentMethod);
        MtxData.setTotalPaymentCheckout(TransactionData.getTotalPrice());
        MtxData.setPaymentMethod(newPaymentMethod);

        /* Override value when payment method value is Transfer ke Virtual Account */
        if (newPaymentMethod.equals("Transfer ke Virtual Account")) {
            MtxData.setPaymentMethod("Transfer Bank Otomatis");
            TransactionData.setPaymentMethod("Transfer Bank Otomatis");
        }

        tapElement("checkout_marketplace_payment_button");
        if (isElementVisible("detail_pembelian_lanjutkan_pembayaran_button")) {
            tapElement("detail_pembelian_lanjutkan_pembayaran_button");
        }
    }

    public void untickAllMixPayment() {
        clickCheckboxMixPaymentOnCheckout("untick", "DANA");
        clickCheckboxMixPaymentOnCheckout("untick", "Saldo");
        if (isElementVisible("checkout_marketplace_alchemy_total_pembayaran_price", 10)) {
            MtxData.setTotalPaymentCheckout(getElementValue("checkout_marketplace_alchemy_total_pembayaran_price"));
        }
    }

    public void clickCheckboxMixPaymentOnCheckout(String state, String type) {
        String locatorPaymentType = (type.equals("DANA")) ? "checkout_marketplace_mix_payment_dana_checkbox" : "checkout_marketplace_mix_payment_saldo_checkbox";

        if (isElementVisible(locatorPaymentType, 60) &&
                isElementClickable(locatorPaymentType)) {
            //waiting value checkbox
            waitFor(2);
            String cbState = getElementValue(locatorPaymentType);
            LogUtil.info("Checkbox " + type + " state is " + cbState + " when user want to " + state);

            if (state.equalsIgnoreCase("tick")) {
                if (cbState.equalsIgnoreCase("false")) {
                    tapElement(locatorPaymentType, 15);
                    waitFor(2);
                    validateValue().equals("true", getElementValue(locatorPaymentType));
                }
            } else if (state.equalsIgnoreCase("untick")) {
                if (cbState.equalsIgnoreCase("true")) {
                    tapElement(locatorPaymentType, 10);
                    waitFor(2);
                    validateValue().equals("false", getElementValue(locatorPaymentType));
                }
            }
        } else {
            LogUtil.info("Checkbox mixpayment " + type + " tidak ditemukan alias balance kosong!");
        }
    }

    public void clickChangeMixPaymentButton() {
        swipeUpToElement("checkout_marketplace_mixpayment_change_button");
        tapElement("checkout_marketplace_mixpayment_change_button");
    }

    public Integer setMixpaymentInput(String condition, Integer price) {
        LogUtil.info("Price Before Mixpayment = " + price.toString());
        Integer tmpPrice = price;
        if (condition.equals("greater than")) {
            tmpPrice = (int) (price * (150.0f / 100.0f));
        } else if (condition.equals("less than")) {
            tmpPrice = (int) (price * (50.0f / 100.0f));
        }
        LogUtil.info("Price After Mixpayment = " + tmpPrice.toString());
        return tmpPrice;
    }

    public void setMixpayment(String amount, String type) {
        int lengthInput = getElements("checkout_marketplace_mixpayment_screen_input").size();
        if (type.equals("DANA")) {
            typeAndEnterValue("checkout_marketplace_mixpayment_screen_input", 0, "");
            waitFor(3);
            typeAndEnterValue("checkout_marketplace_mixpayment_screen_input", 0, amount);
        } else if (type.equals("Saldo")) {
            if (lengthInput > 1) {
                typeAndEnterValue("checkout_marketplace_mixpayment_screen_input", 1, "");
                waitFor(3);
                typeAndEnterValue("checkout_marketplace_mixpayment_screen_input", 1, amount);
            } else {
                typeAndEnterValue("checkout_marketplace_mixpayment_screen_input", 0, "");
                waitFor(3);
                typeAndEnterValue("checkout_marketplace_mixpayment_screen_input", 0, amount);
                ;
            }
        }
        waitFor(5);
    }

    public void clickBayarButton() {
        waitForVisibilityOf("checkout_marketplace_alchemy_bayar_button");
        tapElement("checkout_marketplace_alchemy_bayar_button");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void isOnPaymentMethodPage() {
        if (!isElementExist("checkout_marketplace_page_metode_pembayaran", 5)) {
            swipeDownToElement("checkout_marketplace_page_metode_pembayaran");
            verifyElementExist("checkout_marketplace_page_metode_pembayaran");
            tapElement("checkout_marketplace_page_metode_pembayaran");
        } else {
            swipeUpToElement("checkout_marketplace_page_metode_pembayaran");
            verifyElementExist("checkout_marketplace_page_metode_pembayaran");
            tapElement("checkout_marketplace_page_metode_pembayaran");
        }
    }

    public void clickCheckboxMixPayment(String state, String type) {
        int lengthCb = getElements("checkout_marketplace_mixpayment_screen_cb").size();
        if (type.equals("DANA")) {
            tapElements("checkout_marketplace_mixpayment_screen_cb", 0);
        } else if (type.equals("Saldo")) {
            if (lengthCb > 1) {
                tapElements("checkout_marketplace_mixpayment_screen_cb", 1);
            } else {
                tapElements("checkout_marketplace_mixpayment_screen_cb", 0);
            }
        }
    }

    public void goToCheckoutSummary() {
        // go to bottom of checkout summary item
        swipeUpToElement("checkout_marketplace_label_rincian_harga");
        /* handling for simulator iphone 8 when swipeToElement not scroll element to checkout summary */
        if (!isElementVisible("checkout_marketplace_label_rincian_harga")) {
            dragElement("checkout_marketplace_daftar_belanja_text", constructLocator("checkout_marketplace_mixpayment", "Bayarnya Bisa Digabung"));
            swipeUpToElement("checkout_marketplace_alchemy_bottom_of_checkout_summary");
        }

        // get total product price
        swipeUpToElement("checkout_marketplace_alchemy_total_harga_barang_price");
        MtxData.setTotalProductPriceCheckout(getElementValue("checkout_marketplace_alchemy_total_harga_barang_price"));

        // get total payment
        swipeUpToElement("checkout_marketplace_alchemy_total_pembayaran_price");
        MtxData.setTotalPaymentCheckout(getElementValue("checkout_marketplace_alchemy_total_pembayaran_price"));
    }

    public void tapPayButton() {
        waitForVisibilityOf("checkout_marketplace_alchemy_bayar_button");
        waitForElementClickable("checkout_marketplace_alchemy_bayar_button", 30);
        tapElement("checkout_marketplace_alchemy_bayar_button");
        if (isElementVisible("checkout_marketplace_lanjut_pembayaran_button")) {
            waitForElementClickable("checkout_marketplace_lanjut_pembayaran_button", 30);
            tapElement("checkout_marketplace_lanjut_pembayaran_button");
        }
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyTotalProductPrice() {
        LogUtil.info("Product Price Product Detail = " + MtxData.getProductPricePdp());
        LogUtil.info("Total Product Price = " + MtxData.getTotalProductPriceCheckout());
        assertTrue(MtxData.getTotalProductPriceCheckout().equals(MtxData.getProductPricePdp()), "Total product price checkout not match with product price pdp");
    }

    public void verifyTotalProductPriceFromCart() {
        LogUtil.info("Product Price Cart = " + CheckoutData.getProductPrice());
        int actualTotalProductPrice = getIntFromRp(MtxData.getTotalProductPriceCheckout());
        assertEquals(CheckoutData.getProductPrice(), actualTotalProductPrice, "Total product price checkout not match with product price cart" );
    }

    public void verifyMixPaymentDeduction(String type) {
        String locatorPaymentType = constructLocator("checkout_marketplace_alchemy_mixpayment_deduction", type);
        swipeUpToElement(locatorPaymentType);
        assertTrue(isElementVisible(locatorPaymentType), "Potongan mixpayment tidak ditemukan!");
        MtxData.setMixPayment(getElementValue(locatorPaymentType));
        if (type.equals("DANA")) {
            MtxData.setMixPaymentDana(MtxData.getMixPayment());
        } else if (type.equals("Saldo")) {
            MtxData.setMixPaymentSaldo(MtxData.getMixPayment());
        }
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyTransactionTotalPembayaran(Boolean isUseMixPayment) {
        int totalMixPayment, mixPaymentDana, mixPaymentSaldo;
        int totalItemPrice = Integer.parseInt(TransactionData.getTotalItemsPrice());
        int shippingFee = Integer.parseInt(TransactionData.getTotalShippingFee());
        int visibleInsuranceFee = Integer.parseInt(TransactionData.getInsuranceFee());
        int totalCalculation = totalItemPrice + shippingFee + visibleInsuranceFee;
        int totalPembayaran = Integer.parseInt(MtxData.getTotalPaymentCheckout().replaceAll("[^0-9]", ""));
        if (isUseMixPayment) {
            mixPaymentDana = Integer.parseInt(MtxData.getMixPaymentDana().replaceAll("[^0-9]", ""));
            mixPaymentSaldo = Integer.parseInt(MtxData.getMixPaymentSaldo().replaceAll("[^0-9]", ""));
            totalMixPayment = mixPaymentDana + mixPaymentSaldo;
        } else {
            totalMixPayment = 0;
        }
        LogUtil.info("Total Manual Calculation = " + totalCalculation);
        LogUtil.info("Total MixPayment = " + totalMixPayment);
        assertEquals(totalPembayaran, totalCalculation - totalMixPayment, "Total Calculation with deduction MixPayment incorrect! ");
    }

    public void verifyWithOtherMixPaymentDeduction() {
        assertEquals(MtxData.getMixPaymentSaldo(), MtxData.getMixPaymentDana(), "Potongan mixpayment dana dengan saldo tidak sama!");
    }

    public void verifyMixPaymentValidation(String message, String type) {
        int lengthInput = getElements("checkout_marketplace_mixpayment_screen_input").size();
        String messageLocator = constructLocator("checkout_marketplace_mixpayment_label", message);
        if (type.equals("DANA")) {
            assertTrue(isElementVisible(messageLocator, 30), "Label validasi mixpayment dana tidak ditemukan!");
        } else if (type.equals("Saldo")) {
            if (lengthInput > 1) {
                assertTrue(getElementsPresent(messageLocator).get(1).isDisplayed(), "Label validasi mixpayment saldo tidak ditemukan!");
            } else {
                assertTrue(isElementVisible(messageLocator), "Label validasi mixpayment saldo tidak ditemukan!");
            }
        }
    }

    public void verifyMixpaymentTicked(String type, String state) {
        waitFor(5);
        String locatorPaymentType = (type.equals("DANA")) ? "checkout_marketplace_mix_payment_dana_checkbox" : "checkout_marketplace_mix_payment_saldo_checkbox";
        waitForVisibilityOf(locatorPaymentType, 60);
        if (state.equals("ticked")) {
            assertTrue(getElementValue(locatorPaymentType).equals("true"), "Mixpayment " + type + " is not ticked!");
        } else {
            assertTrue(getElementValue(locatorPaymentType).equals("false"), "Mixpayment " + type + " is ticked!");
        }
    }

    public void close3rdPartyWebview() {
        tapElement("checkout_marketplace_close_webiew_3rd_party");
    }

    public void verify3rdPartyWebview(String service) {
        waitFor(5);
        assertTrue(isElementVisible(constructLocator("checkout_marketplace_webview_3rd_party", service), 30), "Webview 3rd party tidak ditemukan!");
    }

    public void tapOtherPaymentMethoFromVA() {
        waitForVisibilityOf("checkout_marketplace_metode_pembayaran_lain_button");
        waitForElementClickable("checkout_marketplace_metode_pembayaran_lain_button", 30);
        tapElement("checkout_marketplace_metode_pembayaran_lain_button");
    }

    public void tapDropshipperCheckbox() {
        waitForElementClickable("dropshipper_checkbox", 10);
        tapElement("dropshipper_checkbox");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void tapBayarSebagianBukadompetOrCredits() {

        if (isElementVisible("checkout_alchemy_mix_payment_checkbox")) {
            waitForElementClickable("checkout_alchemy_mix_payment_checkbox", 10);
            if (isElementVisible("checkout_marketplace_ubah_mixpayment")) {
                tapElement("checkout_alchemy_mix_payment_checkbox");
            }
            tapElement("checkout_alchemy_mix_payment_checkbox");
            if (isElementVisible("checkout_alchemy_bayar_sebagian_dana_option")) {
                tapElement("checkout_alchemy_bayar_sebagian_credits_option");
                typeAndEnterValueWithTimeOut("checkout_alchemy_bayar_sebagian_credits_field", "1");
                tapElement("checkout_alchemy_bayar_sebagian_gunakan_button");
            } else {
                typeAndEnterValueWithTimeOut("checkout_alchemy_bayar_sebagian_field", "1");
                tapElement("simpan_button");
            }
            waitForVisibilityOf("checkout_marketplace_ubah_mixpayment");
            if (isElementVisible("checkout_alchemy_credits_digunakan")) {
                String creditsUsed = getTextFromElement("checkout_alchemy_credits_digunakan").replace("Credits digunakan: Rp", "");
                assertTrue(creditsUsed.equals("1"));
            } else {
                String saldoUsed = getTextFromElement("checkout_alchemy_saldo_digunakan").replace("Saldo digunakan: Rp", "");
                assertTrue(saldoUsed.equals("1"));
            }
        }
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void tapCatatanLink() {
        swipeDownToElement("checkout_voucher_text");
        tapElement("catatan_link");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyCatatanPopup() {
        assertTrue(isElementVisible("catatan_for_pelapak_text"));
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void writeCatatanForSeller(String notes) {
        String tmpNotes = notes.toUpperCase();

        try {
            typeAndEnterValueWithTimeOut("catatan_for_pelapak_field", dotenv.get(tmpNotes + "_NOTES"));
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Type of " + tmpNotes + " is not listed in data.env file.");
        }
        tapElement("simpan_catatan_button");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void tapBayarSekarangButton() {
        tapElement("checkout_bayar_sekarang_button");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyConfirmationPage(String text) {
        waitForVisibilityOf(constructLocator("checkout_marketplace_label_containts_text", text), 60);
        MtxData.setPaymentMethod(text);
    }


    public void verifyDropshipperPopup() {
        waitForVisibilityOf("dropshipper_text", 15);
        validateDisplayed("nama_pengirim_text");
        validateDisplayed("no_telp_pengirim_text");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void dropshipperAs(String dropshipperType) {
        String tmpDropshipperType = dropshipperType.toUpperCase();
        try {
            typeAndEnterValueWithTimeOut("nama_pengirim_field", dotenv.get(tmpDropshipperType + "_DROPSHIPPER_NAME"));
            typeAndEnterValueWithTimeOut("no_telp_pengirim_field", dotenv.get(tmpDropshipperType + "_DROPSHIPPER_DETAIL"));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Type of " + tmpDropshipperType + " is not listed in data.env file.");
        }
        tapElement("dropshipper_simpan_button");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void chooseCourier(String courier) {
        swipeDownToElement("checkout_voucher_text");
        tapElement("checkout_tap_courier_area");
        String courier_choosen = "checkout_courier_" + courier.toLowerCase() + "_choosen";

        swipeDownToElement(courier_choosen);
        tapElement(courier_choosen);
        tapElement("checkout_courier_pilih_button");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyRoundedAmount() {
        swipeDownToElement("checkout_rincian_harga_text");
        assertTrue(isElementVisible("checkout_pembulatan_text"));
        assertTrue(isElementVisible("checkout_pembulatan_amount_text"));
        TransactionData.setRoundedAmount(getElementValue("checkout_pembulatan_amount_text"));
        tapElement("checkout_pembulatan_text");
        assertTrue(isElementVisible("checkout_info_pembulatan_text"));
        tapElement("checkout_info_pembulatan_close");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyOnCimbPage() {
        assertTrue(isElementVisible("checkout_cimb_select_payment_method"));
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyOnBCAKlikPayPage() {
        waitForVisibilityOf("list_bank_is_on_bcaklikpay_homepage", 20);
        assertTrue(isElementVisible("list_bank_is_on_bcaklikpay_homepage"), "We are not in BCA Klikpay webview!");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void quitFromBCAKlikPayPage() {
        tapElement("base_back_button");
        tapElement("popup_alert_ya_button");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void verifyNoRoundedAmount() {
        swipeDownToElement("checkout_rincian_harga_text");
        verifyElementNotExist("checkout_pembulatan_text");
        verifyElementNotExist("checkout_pembulatan_amount_text");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void quitFromCimbPage() {
        tapElement("base_back_button");
        tapElement("checkout_cimb_ya_alert");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    /**
     * This method is used to get shipping fee data without any action like as changing address, changing total product
     *
     * @return courierFee
     */
    private synchronized int getCourierFee() {
        return courierFee;
    }

    /**
     * This method is used to set shipping fee data without any action like as changing address, changing total product
     *
     * @param courierFee
     */
    public synchronized static void setCourierFee(int courierFee) {
        CheckoutMarketplacePage.courierFee = courierFee;
    }

    /**
     * This method is used to set price of goods data
     *
     * @param hargaPerBarang
     */
    public synchronized void setHargaPerBarang(int hargaPerBarang) {
        LOGGER.info("Harga Barang" + hargaPerBarang);
        CheckoutMarketplacePage.hargaPerBarang = hargaPerBarang;
    }

    public synchronized int getHargaPerBarang() {
        return hargaPerBarang;
    }

    /**
     * This method is used to get address that is currently appear in checkout page and set address data
     * that is not yet changing
     */
    public void getCurrentAddress() {
        XPRData.setBeforeAddress(getAddress());
    }


    /**
     * This method is used to get set address data after changing to another address and get address that is appear
     * in checkout page
     */
    public void getNewAddress() {
        XPRData.setAfterAddress(getAddress());
    }

    public void getCurrentShippingFee() {
        XPRData.setOngkirBeforeChangeAddress(XPRData.getOngkirMarketplace());
    }

    /**
     * This method is used to click lanjut ke pembayaran for non alchemy UI
     */
    public void clickLanjutKePembayaran() {
        if (isElementVisible("checkout_marketplace_lanjut_ke_pembayaran", 20)) {
            tapElement("checkout_marketplace_lanjut_ke_pembayaran");
        } else {
            tapElement("new_pop_cart_dialog_continue_payment");
        }
    }


    /**
     * This method is used to validate that address is changed or not
     */
    public void validateChangingAddress() {
        assertNotSame(XPRData.getAfterAddress(), XPRData.getBeforeAddress(), "address berubah");
    }

    /**
     * This method is used to validate that shipping fee is changing after changing address
     */
    public void validateShippingFeeAddress() {
        assertTrue(XPRData.getOngkirMarketplace() != XPRData.getOngkirBeforeChangeAddress(), "ongkos biaya kirim berubah");
    }

    /**
     * This method is used to validate after courier is changing
     *
     * @param courier
     */
    public void validateChangingCourier(String courier) {
        assertTrue(XPRData.getKurirName().contains(courier), "Jasa Pengiriman is updated");
    }

    public void validateShippingFee(String fee) {
        assertTrue(XPRData.getOngkirMarketplace() == (Integer.parseInt(fee)), "shipping fee is expected");
    }

    public void tapBayarSekarangButtonWithVAPaymentMethod() {
        if (isElementVisible("checkout_marketplace_bayar_dengan_virtual_account_button")) {
            tapElement("checkout_marketplace_bayar_dengan_virtual_account_button");
            if (isElementVisible("checkout_marketplace_lanjut_pembayaran_button")) {
                tapElement("checkout_marketplace_lanjut_pembayaran_button");
            }
        } else if (isElementVisible("checkout_bayar_sekarang_button")) {
            tapElement("checkout_bayar_button");
            if (isElementVisible("checkout_marketplace_lanjut_pembayaran_button")) {
                tapElement("checkout_marketplace_lanjut_pembayaran_button");
            }
        }
    }

    public void setTotalPayment() {
        if (isElementVisible("checkout_total_pembayaran_text")) {
            TransactionData.setTotalPrice("Rp" + getTextFromElement("checkout_total_pembayaran_text"));
        } else {
            swipeUpToElement("checkout_marketplace_label_rincian_harga");
            TransactionData.setTotalPrice(getTextFromElement("checkout_total_tagihan_text"));
        }
    }

    public void setTransactionData() {
        setTotalPayment();
    }

    /**
     * This method is used to validate shipping is appear properly
     */
    public void validateShippingfeeIsShown() {
        assertTrue(XPRData.getOngkirMarketplace() > 0, "price is shown");
    }

    /**
     * This method is used to validate estimate time is appear properly
     *
     * @param estimateTime
     */
    public void validateEstimateTime(String estimateTime) {
        assertTrue(XPRData.getEtaCourier().equalsIgnoreCase(estimateTime), "eta is expected");
    }

    /**
     * this method is used to validate new address is saved successfully
     * For validation, address is get in check out page
     *
     * @param newAddress
     */
    public void validateNewAddressisadded(String newAddress) {
        String address;
        address = getAddress();
        assertTrue(address.contains(newAddress), "new address is saved successfully");
    }

    public void setQuantityProduct(int jumlah) {
        // sometimes it is not tapped
        try {
            tapElement("checkout_marketplace_alchemy_total_product_text");
            waitForVisibilityOf("checkout_marketplace_delete_keyboard_button");
        } catch (TimeoutException e) {
            tapElement("checkout_marketplace_alchemy_total_product_text");
            waitForVisibilityOf("checkout_marketplace_delete_keyboard_button");
        }

        //used to clear quantity, can not clear using element.clear()
        for (int i = 0; i < 5; i++) {
            tapElement("checkout_marketplace_delete_keyboard_button");
        }
        typeAndEnterValue("checkout_marketplace_alchemy_total_product_text", Integer.toString(jumlah));
        TransactionData.setQuantityItem(jumlah);
    }

    /**
     * This method is used to add total product in alchemy ui
     *
     * @param jumlah
     */
    public void addTotalProductAlchemy(int jumlah) {
        swipeUpToElement("checkout_marketplace_alchemy_total_product_text");
        setQuantityProduct(jumlah);
    }

    /**
     * This method is used to validate shipping fee is change or not after changing total product for alchemy ui
     *
     * @param increasedStatus, true : shipping fee should be change
     *                         false : shipping fee should be same
     */
    public void validateShippingFeeAfterChangingQuantityAlchemy(boolean increasedStatus) {
        waitForVisibilityOf("checkout_marketplace_protection_title_text");
        if (increasedStatus) {
            assertTrue(XPRData.getOngkirBeforeChangeAddress() < XPRData.getOngkirMarketplace(), "shipping fee is changing");
        } else {
            assertTrue(XPRData.getOngkirBeforeChangeAddress() == XPRData.getOngkirMarketplace(), "shipping fee is same");
        }
    }

    public void tapCategorySection() {
        try {
            tapElement("checkout_marketplace_courier_grup_title_new_ui_text");
        } catch (Exception e) {
            swipeToDirection(Direction.UP);
            swipeDownToElement("checkout_marketplace_courier_category_button");
            tapElement("checkout_marketplace_courier_category_button");
        }
    }

    public void tapCourierSection() {
        try {
            tapElement("checkout_marketplace_courier_service_title_new_ui_text");
        } catch (Exception e) {
            try {
                tapElement("checkout_marketplace_courier_service_button");
            } catch (Exception ex) {
                nativeSwipeUp();
                swipeDownToElement("checkout_marketplace_courier_service_button");
                tapElement("checkout_marketplace_courier_service_button");
            }
        }
    }


    public void tapOnKurirDropdown() {
        if (isElementExist("checkout_marketplace_alchemy_rincian_harga_text")) {
            swipeDownToElement("checkout_marketplace_alchemy_note_pelapak_txt");
        }
        swipeUpToElement("checkout_marketplace_kirim_atau_retur_lebih_aman_text");
        if (isElementExist("checkout_marketplace_alchemy_pilih_kurir_button")) {
            tapElement("checkout_marketplace_alchemy_pilih_kurir_button");
        } else {
            tapElement("checkout_marketplace_alchemy_pilih_kurir_new_btn", 5);
        }
    }

    public void selectCourier(String courier) {
        tapOnKurirDropdown();
        String courierSpesificLocator =constructLocator("choose_specific_courier", courier);
        swipeUpToElement(courierSpesificLocator);
        tapElement(courierSpesificLocator);
        tapElement("choose_courier_pilih_button");
        swipeDownToElement("checkout_metode_bayar_icon");
    }

    /**
     * This method is used to get "total belanja" in checkout page
     * ex :
     * Saldo Sekarang : Rp.0
     * Total harga barang : Rp.1000
     * Biaya Kirim / Ongkod Kirim : RP. 1000
     * Biaya Insurance : RP.1000
     * <p>
     * Total belanja : RP. 30000 (get this value)
     *
     * @return totalBelanja
     */
    public String getTotalBelanja() {
        String totalBelanja;
        totalBelanja = getTextFromElement("checkout_marketplace_alchemy_total_pembayaran_text").replaceAll("[^0-9]", "");
        return totalBelanja;
    }

    /**
     * This method is used to get "total insurance" in checkout page
     * ex :
     * Saldo Sekarang : Rp.0
     * Total harga barang : Rp.1000
     * Biaya Kirim / Ongkod Kirim : RP. 1000
     * Biaya Insurance : RP.1000(get this value)
     * <p>
     * Total belanja : RP. 30000
     *
     * @return insurance
     */
    public String getInsurance(String locator) {
        String insurance;
        insurance = getTextFromElement(locator).replaceAll("[^0-9]", "");
        return insurance;
    }

    public String getTotalAllVisibleInsurance() {
        int totalInsurance = 0;
        if (isElementVisible("checkout_marketplace_alchemy_biaya_asuransi_text")) {
            totalInsurance = totalInsurance + Integer.parseInt(getInsurance(("checkout_marketplace_alchemy_biaya_asuransi_text")));
        }
        if (isElementVisible(("checkout_marketplace_alchemy_biaya_asuransi_retur_text"))) {
            totalInsurance = totalInsurance + Integer.parseInt(getInsurance(("checkout_marketplace_alchemy_biaya_asuransi_retur_text")));
        }
        return String.valueOf(totalInsurance);
    }

    public String getShippingFee() {
        String shippingFee;
        int maxSwipe = 0;
        do {
            nativeSwipeUp();
            maxSwipe++;
        } while (maxSwipe <= 5);
        try {
            shippingFee = getTextFromElement("checkout_marketplace_alchemy_ongkos_kirim_new_ui_text").replaceAll("[^0-9]", "");
        } catch (Exception e) {
            shippingFee = getTextFromElement("checkout_marketplace_alchemy_ongkos_kirim_text").replaceAll("[^0-9]", "");
        }
        return shippingFee;
    }

    /**
     * This method is used to get price of goods in checkout page
     *
     * @return priceGood
     */
    public String getPriceGoods() {
        String priceGood;
        priceGood = getTextFromElement("checkout_marketplace_alchemy_harga_perbarang_text").replaceAll("[^0-9]", "");
        return priceGood;
    }


    private String getBenefitPriorityOngkir() {
        String ongkir = getElementValue("checkout_marketplace_pembeli_prioritas_remaining_benefit_text");
        return ongkir.substring(0, ongkir.length() - 1);
    }

    public void validateBiayaPelayanCOD(String biayaPelayanan) {
        waitForVisibilityOf("checkout_marketplace_alchemy_page_title", 30);
        swipeUpToElement("checkout_marketplace_biaya_pelayanan_text");
        validateValue().equals(getElementValue("checkout_marketplace_biaya_pelayanan_value_text"), biayaPelayanan);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    /**
     * To check Priority Buyer section - Will be updated, for now it is only for benefit usage
     */
    public void checkPembeliPrioritasSection(String status) {
        String priorityCrossSellingBenefit = dotenv.get("PRIO_CROSS_SELLING_PACKAGE_BENEFIT_ONGKIR");
        String priorityCrossSellingPrice = dotenv.get("PRIO_CROSS_SELLING_PACKAGE_PRICE");
        swipeUpToElement("checkout_marketplace_voucher_text");
        if (status.equalsIgnoreCase("subscribed")) {
            verifyElementExist("checkout_marketplace_pembeli_prioritas_text");
            if (isElementVisible("checkout_marketplace_pembeli_prioritas_remaining_benefit_text")) {
                PRIOData.setOngkirBenefit(Integer.parseInt(getBenefitPriorityOngkir()));
            }
        } else {
            String offering;
            String price;
            if (isElementVisible("checkout_marketplace_pembeli_prioritas_ya_langganan_text", 30)) {
                LOGGER.info("User gets control!");
                waitForVisibilityOf("checkout_marketplace_pembeli_prioritas_control_offering_text", 20);
                offering = getTextFromElement("checkout_marketplace_pembeli_prioritas_control_offering_text");
                assertTrue(offering.contains(Objects.requireNonNull(priorityCrossSellingBenefit)),
                        "Benefit ongkir is not matched!");
                assertTrue(offering.contains(Objects.requireNonNull(priorityCrossSellingPrice)),
                        "Price is not matched!");
            } else if (isElementVisible("checkout_marketplace_pembeli_prioritas_selengkapnya_text", 15)) {
                LOGGER.info("User gets new variant!");
                offering = getTextFromElement("checkout_marketplace_pembeli_prioritas_variant1_title_text");
                price = getTextFromElement("checkout_marketplace_pembeli_prioritas_variant1_info_text");
                assertTrue(offering.contains(Objects.requireNonNull(priorityCrossSellingBenefit)),
                        "Benefit ongkir is not matched!");
                assertTrue(price.contains(Objects.requireNonNull(priorityCrossSellingPrice)),
                        "Price is not matched!");
            } else {
                LOGGER.error("No AB test! Please re-check!");
            }
        }
    }

    /**
     * To click Priority Buyer checkbox - Will be updated, for now it is only for benefit usage
     *
     * @param transactionType priority trancastion type, either usage or cross-selling / subscribe
     */
    public void clickPembeliPrioritasCheckbox(String transactionType) {
        swipeUpToElement("checkout_marketplace_voucher_text");
        waitForVisibilityOf("checkout_marketplace_alchemy_total_pembayaran_price", 20);
        if (transactionType.contains("usage")) {
            waitForVisibilityOf("checkout_marketplace_pembeli_prioritas_usage_text", 20);
            verifyElementExist("checkout_marketplace_pembeli_prioritas_usage_text");
            verifyElementExist("checkout_marketplace_pembeli_prioritas_usage_checkout");
            tapElement("checkout_marketplace_pembeli_prioritas_usage_checkout");
        } else {
            if (isElementVisible("checkout_marketplace_pembeli_prioritas_selengkapnya_text")) {
                tapElement("checkout_marketplace_pembeli_prioritas_cross_selling_ver_1_checkbox");
            } else {
                tapElement("checkout_marketplace_pembeli_prioritas_cross_selling_checkbox");
            }
        }
    }

    /**
     * To check info cashback if user use benefit of Pembeli Prioritas
     */
    public void checkPembeliPrioritasInfoCashback() {
        verifyElementExist("checkout_marketplace_pembeli_prioritas_cashback_text");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void checkChosenPaymentSaldo() {
        if (!isElementVisible("checkout_marketplace_alchemy_metode_pembayaran", 10)) {
            swipeDownToElement("checkout_marketplace_alchemy_metode_pembayaran");
        }
        verifyElementExist("checkout_marketplace_chosen_payment_saldo");
    }

    public void checkPriorityBuyerExpiredInfo() {
        verifyElementExist("checkout_marketplace_pembeli_prioritas_error_expired_info");
    }

    /**
     * This method is used to go to home page for alchemy and non alchemy ui
     */
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickAddressContainer() {
        nativeSwipeDown();
        nativeSwipeDown();
        try {
            waitForVisibilityOf("checkout_marketplace_alchemy_alamat_terpilih_text", 50);
            TransactionData.setAddress(getElementValue("checkout_marketplace_alchemy_alamat_terpilih_text"));
            tapElement("checkout_marketplace_alchemy_alamat_terpilih_text");
        } catch (Exception e) {
            waitForVisibilityOf("checkout_marketplace_alchemy_alamat_terpilih_new_ui_text", 50);
            TransactionData.setAddress(getElementValue("checkout_marketplace_alchemy_alamat_terpilih_new_ui_text"));
            tapElement("checkout_marketplace_alchemy_alamat_terpilih_new_ui_text");
        }


    }

    public void validateNotificationSuccessfullAddAddress(String status) {
        if (status.equals("added"))
            waitForVisibilityOf("checkout_marketplace_successfully_add_address");
        else if (status.equals("edited")) {
            waitForVisibilityOf("checkout_marketplace_successfully_edit_address");
        } else {
            Assert.fail("Alamat tidak berhasil ditambah ataupun diubah karena status:" + status);
        }
    }

    public void backToCheckoutAlchemyPage() {
        waitForElementClickable("base_back_button", 10);
        tapElement("base_back_button");
        userOnCheckoutPage();
    }

    public void changeShippingAddress(String addressName) {
        waitForVisibilityOf("alamat_baru_edit_alamat_page");
        tapElement(constructLocator("checkout_marketplace_ship_addr", addressName));
    }

    public void validateChangeShippingAddress(String title, String fullAddress) {
        getNewAddress();
        assertTrue(XPRData.getAfterAddress().equals(title + " - " + fullAddress), "Alamat Berhasil Diganti");
    }

    public void tapBayarDenganButton(String paymentMethod) {
        String payment;
        String payment_option = "checkout_marketplace_bayar_dengan_" + paymentMethod.toLowerCase() + "_button";

        waitForVisibilityOf(payment_option);
        swipeToLocator(payment_option);
        payment = paymentMethod.replaceAll("_", " ");
        switch (payment) {
            case "Credits":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_credits_button"), "Button Bayar dengan Credits is not available!");
                tapElement(payment_option);
                break;
            case "Saldo":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_saldo_button"), "Button Bayar dengan Saldo is not available!");
                tapElement(payment_option);
                break;
            case "Transfer Bank":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_transfer_bank_button"), "Button Bayar dengan Transfer Bank is not available!");
                tapElement(payment_option);
                break;
            case "Gerai":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_gerai_button"), "Button Bayar dengan Gerai is not available!");
                tapElement(payment_option);
                break;
            case "Dana":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_dana_button"), "Button Bayar dengan Dana is not available!");
                tapElement(payment_option);
                break;
            case "Virtual Account":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_virtual_account_button"), "Button Bayar dengan Virtual Account is not available!");
                tapElement(payment_option);
                break;
            case "Internet Banking":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_internet_banking_button"), "Button Bayar dengan Internet Banking is not available!");
                tapElement(payment_option);
                break;
            case "kredivo":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_kredivo_button"), "Button Bayar dengan Kredivo is not available!");
                tapElement(payment_option);
                break;
            case "akulaku":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_akulaku_button"), "Button Bayar dengan akulaku is not available!");
                tapElement(payment_option);
                break;
            case "Bri Ceria":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_bri_ceria_button"), "Button Bayar dengan bri ceria is not available!");
                tapElement(payment_option);
                break;
            case "cod":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_cod_button"), "Button Bayar dengan cod is not available!");
                tapElement(payment_option);
                break;
            case "Binded credit card":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_binded_credit_card_button"), "Button Bayar dengan binded credit card is not available!");
                tapElement(payment_option);
                break;
            case "Unbinded credit card":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_unbinded_credit_card_button"), "Button Bayar dengan unbinded credit card is not available!");
                tapElement(payment_option);
                break;
            case "revamp":
                assertTrue(isElementVisible("checkout_marketplace_bayar_dengan_revamp_button"), "Button revamp checkout is not available!");
                tapElement(payment_option);
                break;
            default:
                LOGGER.info("Invalid parameter or not yet implemented");
                HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
        }
    }

    public void validateItemOnCheckout(ArrayList<Map<String, Object>> items) {
        String elm_itemName;
        String elm_productName;
        for (Map<String, Object> item : items) {
            swipeUpToElement("checkout_marketplace_daftar_belanja_text");
            elm_itemName = constructLocator("checkout_marketplace_elm_itemName", item.get("PRODUCT_NAME").toString());
            elm_productName = constructLocator("checkout_marketplace_elm_productName", item.get("PRODUCT_NAME").toString());
            swipeUpToElement(elm_itemName);
            isElementVisible(elm_productName);
            HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
        }
    }

    public void validateMixpaymentNotAppear() {
        verifyElementNotExist("checkout_alchemy_bayar_sebagian_credits_title");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void addItemAboveMinimumPayment() {
        TransactionData.setTotalPayment(Integer.parseInt(getTotalBelanja()));
        for (int i = 0; i < 10000; i++) {
            waitForElementClickable("checkout_marketplace_add_barang", 10);
            tapElement("checkout_marketplace_add_barang");
            i = Integer.parseInt(getTotalBelanja());
        }
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validatePriceIsChanged(int totalPaymentBefore) {
        int totalPaymentAfter = Integer.parseInt(getTotalBelanja());
        assertNotSame(totalPaymentBefore, totalPaymentAfter);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void checkErrorPriority(String errorInfo) {
        swipeUpToElement("checkout_marketplace_voucher_text");
        switch (errorInfo) {
            case "indicated as dropshipper":
                validateValue().equalsFalse(isElementEnabled("checkout_marketplace_pembeli_prioritas_usage_checkout"),
                        "Checkbox should be disabled!");
                verifyElementExist("checkout_marketplace_error_indicated_dropshipper_text");
                verifyElementExist("checkout_marketplace_error_info_indicated_dropshipper_text");
                LOGGER.info("Error priority buyer dropshipper!");
                break;
            case "using Kredivo or Akulaku":
                assertEquals(
                        "Langganan Pembeli Prioritas tidak berlaku untuk metode pembayaran ini.",
                        getElementValue("checkout_marketplace_error_using_online_credit_text"),
                        "Error message because using Kredivo or Akulaku doesn't match");
                LOGGER.info("Error priority buyer because Akulaku or Kredivo!");
                break;
            case "minimum price doesn't meet the requirement":
                verifyElementExist("checkout_marketplace_error_price_priority_text");
                LOGGER.info("Error priority buyer because price!");
                break;
            case "using not eligible courier":
                verifyElementExist("checkout_marketplace_error_courier_priority_text");
                LOGGER.info("Error priority buyer because courier isn't eligible!");
                break;
            case "using dropshipper":
                verifyElementExist("checkout_marketplace_error_dropshipper_priority_text");
                LOGGER.info("Error priority buyer because using dropshipper!");
                break;
            default:
                LOGGER.error("Please re-check your selection regarding priority buyer error!");
                break;
        }
    }

    public void userOnBukaBantuanIndicatedDropshipperPage() {
        waitForVisibilityOf("checkout_marketplace_bukabantuan_indicated_dropshipper_text", 20);
    }

    public void validateDefaultCourier() {
        swipeDownToElement("checkout_voucher_text");
        isElementVisible("checkout_marketplace_rekomendasi_courier", 20);
        String courierRekomendation = getTextFromElement("checkout_marketplace_rekomendasi_courier").replaceAll("^\\w+\\W+", "");
        TransactionData.setCourierRecommendation(courierRekomendation);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void tapCourierArea() {
        swipeDownToElement("checkout_voucher_text");
        isElementClickable("checkout_tap_courier_area");
        tapElement("checkout_tap_courier_area");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateFirstRecommendationCourier(String defaultCourier) {
        isElementVisible("checkout_marketplace_pilih_kurir_text");
        String firstCourierRecommendation = getTextFromElement("checkout_marketplace_first_rekomendasi_kurir");
        assertEquals(defaultCourier, firstCourierRecommendation);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateCatalogTitleOnCheckout(String catalogTitle) {
        swipeUpToElement("checkout_marketplace_pengiriman_text");
        String elm_catalogttl = constructLocator("checkout_marketplace_cat_ttl", catalogTitle);
        assertTrue(isElementClickable(elm_catalogttl), "Note is not displayed catalog title");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void selectCourier(String group, String courier) {
        String groupCourierElement = "xpath_//XCUIElementTypeTextView[@value='" + group + "']";
        String selectCourierElement = "xpath_//XCUIElementTypeTextView[@value='" + courier + "']";
        swipeDownToElement(groupCourierElement);
        tapElement(groupCourierElement);
        swipeDownToElement(selectCourierElement);
        tapElement(selectCourierElement);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void tapPilihButton() {
        waitForElementClickable("checkout_marketplace_pilih_kurir_button", 10);
        tapElement("checkout_marketplace_pilih_kurir_button");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateSelectedCourier(String courier) {
        String selectedCourierElement = "xpath_//XCUIElementTypeStaticText[@label='" + courier + "']";
        verifyElementExist(selectedCourierElement);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateCourierFee() {
        swipeDownToElement("checkout_voucher_text");
        verifyElementExist("checkout_marketplace_pilih_kurir_fee");
        String infoCourier = getElementValue("checkout_marketplace_pilih_kurir_fee").replaceAll("^\\d+\\W", "");
        String courierPrice = infoCourier.replaceAll("[^0-9]", "");
        setCourierFee(Integer.parseInt(courierPrice));
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void changeToSecondAddress() {
        waitForVisibilityOf("checkout_marketplace_second_address_icon");
        tapElement("checkout_marketplace_second_address_icon");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateCourierFeeIsChange(String address) {
        String currentAddress = getElementValue("checkout_marketplace_alchemy_alamat_terpilih_text");
        assertNotSame(address, currentAddress);
        swipeDownToElement("checkout_voucher_text");
        String infoCourier = getElementValue("checkout_marketplace_pilih_kurir_fee").replaceAll("^\\d+\\W", "");
        String currentCourierPrice = infoCourier.replaceAll("[^0-9]", "");
        assertNotSame(getCourierFee(), currentCourierPrice);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    /*
     * To validate the detail transaction info for priority buyer transaction
     * */
    public void validatePriorityBuyerMarketplaceTransaction(String priorityTransaction) {
        swipeUpToElement("checkout_marketplace_total_harga_barang_info_text");
        assertEquals(getTotalPayment(), getTotalPricePriorityBuyerMarketplace(priorityTransaction), "Total Pembayaran is not matched!");
    }

    /**
     * to get total price for priority buyer transaction
     *
     * @param type type of priority buyer transaction. either cross-selling or only usage.
     * @return integer value of total transaction price for priority buyer transaction.
     */
    private int getTotalPricePriorityBuyerMarketplace(String type) {
        int totalPembayaran = 0;
        if (type.contains("cross-selling")) {
            totalPembayaran = getTotalHargaBarang() + getOngkir() + getLanggananPembeliPrioritas() + getPriorityBuyerOngkir();
        } else if (type.contains("usage")) {
            totalPembayaran = getTotalHargaBarang() + getOngkir() + getPriorityBuyerOngkir();
        } else {
            LOGGER.error("Please check the selection!");
        }
        LOGGER.info(totalPembayaran);
        PRIOData.setCrossSellingPriorityTotalPayment(totalPembayaran);
        return totalPembayaran;
    }

    /**
     * To get integer of item's price
     *
     * @return the integer value of item's price
     */
    private int getTotalHargaBarang() {
        return getIntegerFromValueElement("checkout_marketplace_alchemy_total_harga_barang_text");
    }

    /**
     * To get integer of delivery fee
     *
     * @return the integer value of delivery fee
     */
    private int getOngkir() {
        if (isElementVisible("checkout_marketplace_ongkir_amount_text")) {
            return getIntegerFromValueElement("checkout_marketplace_ongkir_amount_text");
        } else {
            return 0;
        }
    }

    /**
     * To get integer of priority buyer benefit
     *
     * @return the integer value of priority buyer benefit
     */
    private int getPriorityBuyerOngkir() {
        return getIntegerFromValueElement("checkout_marketplace_priority_ongkir_amount_text");
    }

    /**
     * To get integer of total payment
     *
     * @return the integer value of total payment
     */
    private int getTotalPayment() {
        return getIntegerFromValueElement("checkout_marketplace_alchemy_total_pembayaran_price");
    }

    /**
     * To get integer of priority buyer subscription fee
     *
     * @return the integer value of priority buyer subscription fee
     */
    private int getLanggananPembeliPrioritas() {
        return getIntegerFromValueElement("checkout_marketplace_priority_subscription_price");
    }

    public void validateShippingFeeAndOngkir() {
        int shippingFee = XPRData.getOngkirMarketplace();
        int ongkir = Integer.parseInt(getShippingFee());
        assertEquals(ongkir, shippingFee, "Ongkos kirim harus sesuai dengan yang ada di list kurir");
    }

    public void setTotalPrice(int price, boolean roundedUp) {
        validateCourierFee();
        int tmpPrice = price - getCourierFee();
        int quantity = quantityByPrice.getQty(tmpPrice, roundedUp);
        TransactionData.setExpectedTotalBill((CartData.getItemPrice() * quantity) + getCourierFee());
        setQuantityProduct(quantity);
    }

    public boolean isPaymentTypeVA(String payment) {
        return TransactionData.getPaymentsInfoVirtualAccount()
                .stream()
                .filter(p -> p.get("name").equals(payment))
                .findAny()
                .isPresent();
    }

    public int getMinPaymentForMixPayment(String payment) {
        int minimumPayment = Integer.parseInt(((Map) ((isPaymentTypeVA(payment))
                ? TransactionData.getPaymentsInfoVirtualAccount()
                : TransactionData.getPaymentsInfo())
                .stream()
                .filter(p -> p.get("name").equals(payment))
                .map(p -> p.get("transaction_limit"))
                .findFirst()
                .get()).get("min").toString());
        return minimumPayment == 0 ? 1000 : minimumPayment;
    }

    public void setSelectedPaymentMethodForMixPayment() {
        if (isElementVisible("checkout_marketplace_gabung_metode_lain_text")) {
            tapElement("checkout_marketplace_simpan_mixpayment_screen_button");
            swipeUpToElement("checkout_marketplace_selected_payment_method_text");
        }

        waitForVisibilityOf("checkout_marketplace_selected_payment_method_text", 30);
        String payment = getElementValue("checkout_marketplace_selected_payment_method_text");
        if (payment.equals("Saldo") || payment.equals("Credits")) {
            payment = "BukaDompet";
        } else if (payment.contains("Virtual Account")) {
            payment = payment.split(" ", 3)[2].trim();
        } else if (payment.contains(("Transfer"))) {
            payment = "Transfer Bank";
        }
        TransactionData.setMinimumPaymentForMixPayment(getMinPaymentForMixPayment(payment));
    }

    public Boolean multipleMixPaymentState(String payment) {
        String buyWithTextLocator = "xpath_//XCUIElementTypeTextView[contains(@value, '" + payment + "')]";
        swipeDownToElement("checkout_marketplace_alchemy_total_harga_barang_text");
        return isElementVisible(buyWithTextLocator, 20);
    }

    public void setMultipleMixPayment(String payment, String tick) {
        String mixpaymentCheckboxLocator = "xpath_//XCUIElementTypeStaticText[contains(@label , '" + payment + "')]/following-sibling::XCUIElementTypeOther[@name='checkbox_atom']";
        swipeUpToElement(mixpaymentCheckboxLocator);
        verifyElementExist(mixpaymentCheckboxLocator);
        if (tick.equals("tick")) {
            if (multipleMixPaymentState(payment)) return;
            else swipeUpToElement(mixpaymentCheckboxLocator);
            tapElement(mixpaymentCheckboxLocator);
        } else {
            if (multipleMixPaymentState(payment)) {
                swipeUpToElement(mixpaymentCheckboxLocator);
                tapElement(mixpaymentCheckboxLocator);
            } else return;
        }
    }

    public void setUsedMixPayment(String payment, int amount) {
        switch (payment) {
            case "DANA":
                TransactionData.setUsedDANA(amount);
                break;
            case "Credits":
                TransactionData.setUsedCredits(amount);
                break;
            default:
                return;
        }
    }

    public void validateMultipleMixPaymentState(String payment, Boolean state) {
        String mixpaymentCheckboxLocator = "xpath_//XCUIElementTypeStaticText[contains(@label , '" + payment + "')]/following-sibling::XCUIElementTypeOther[@name='checkbox_atom']";
        swipeUpToElement(mixpaymentCheckboxLocator);
        assertEquals(state.equals(true), multipleMixPaymentState(payment));
    }

    public void validateMixPaymentAmount(String payment, int amount) {
        swipeDownToElement("checkout_marketplace_alchemy_total_harga_barang_text");
        String usedAmountTextLocator = "xpath_//XCUIElementTypeTextView[contains(@value, '" + payment + "')]/preceding-sibling::XCUIElementTypeTextView";
        int usedAmount = Integer.parseInt(getTextFromElement(usedAmountTextLocator).replaceAll("[^0-9]", ""));
        assertEquals(
                amount,
                usedAmount
        );
        setUsedMixPayment(payment, amount);
    }

    public void validateTotalPrice(int price) {
        int totalPrice = Integer.parseInt(getTextFromElement("checkout_marketplace_alchemy_total_pembayaran_price").replaceAll("[^0-9]", ""));
        assertEquals(price, totalPrice);
    }

    public void validateMixPaymentNotUsed(String payment) {
        swipeDownToElement("checkout_marketplace_alchemy_total_harga_barang_text");
        String usedAmountText = "xpath_//XCUIElementTypeTextView[contains(@value, '" + payment + "')]/preceding-sibling::XCUIElementTypeTextView";
        int amount = payment.equals("DANA")
                ? UserData.getOwnedDANA()
                : UserData.getOwnedCredits();
        if (!isElementVisible(usedAmountText))
            assertNotSame(amount, 0);
        validateMultipleMixPaymentState(payment, false);
        setUsedMixPayment(payment, 0);
    }

    public void validateSnackbarMsg(String msg, String exist) {
        String snackBarLocator = "label_" + msg + "";
        Boolean snackbar = exist == null;
        if (snackbar) verifyElementExist(snackBarLocator);
        else verifyElementNotExist(snackBarLocator);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void userOnMixPaymentInputPage() {
        verifyElementExist("checkout_marketplace_gabung_metode_lain_text");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateMixPaymentStateOnSetMixPaymentScreen(String payment, String state) {
        Boolean mixState = state == null;
        hideKeyboard();
        assertEquals(mixState, mixPaymentStateOnSetMixPaymentScreen(payment));
    }

    public Boolean mixPaymentStateOnSetMixPaymentScreen(String payment) {
        if (payment.equals("DANA")) {
            waitForVisibilityOf(constructLocator("checkout_marketplace_mixpayment", payment), 50);
        }
        return isElementVisible(constructLocator("checkout_marketplace_mixpayment", payment), 50);
    }

    public void clickCheckboxOnMixpaymentScreen(String payment, String state) {
        switch (payment) {
            case "DANA":
                waitForVisibilityOf("checkout_marketplace_dana_mixpayment_screen_checkbox", 50);
                if (state.equals("tick")) {
                    if (getElement("checkout_marketplace_dana_mixpayment_screen_checkbox").getAttribute("value").equalsIgnoreCase("false")) {
                        waitForElementClickable("checkout_marketplace_dana_mixpayment_screen_checkbox", 20);
                        tapElement("checkout_marketplace_dana_mixpayment_screen_checkbox");
                    }
                } else {
                    if (getElement("checkout_marketplace_dana_mixpayment_screen_checkbox").getAttribute("value").equalsIgnoreCase("true")) {
                        waitForElementClickable("checkout_marketplace_dana_mixpayment_screen_checkbox", 20);
                        tapElement("checkout_marketplace_dana_mixpayment_screen_checkbox");
                    }
                }
                break;
            case "Credits":
                waitForElementClickable("checkout_marketplace_credits_mixpayment_screen_checkbox", 10);
                tapElement("checkout_marketplace_credits_mixpayment_screen_checkbox");
                break;
            default:
                return;
        }
    }

    public void setMixPaymentOnSetMixPaymentScreen(String payment, String state) {
        mixPaymentStateOnSetMixPaymentScreen(payment);
        clickCheckboxOnMixpaymentScreen(payment, state);
    }

    public void validateMixPaymentNotAvailable(String payment) {
        String mixpaymentCheckboxLocator = "xpath_//XCUIElementTypeStaticText[contains(@label , '" + payment + "')]/following-sibling::XCUIElementTypeOther[@name='checkbox_atom']";
        swipeUpToElement("checkout_marketplace_alchemy_metode_pembayaran");
        verifyElementNotExist(mixpaymentCheckboxLocator);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void updateQuantityItem(String state, int quantity) {
        String updateButtonElement = "xpath_//XCUIElementTypeButton[@name='" + state + "']";
        swipeDownToElement(updateButtonElement);
        IntStream.range(0, quantity).forEach(
                tap -> tapElement(updateButtonElement));
    }

    public void clearInputMixPaymentOnSetMixPayment(String payment) {
        if (payment.equals("Credits")) {
            tapElement("checkout_marketplace_input_credits_mixpayment_screen");
            tapElement("checkout_marketplace_clear_input_mixpayment_screen");
        } else {
            tapElement("checkout_marketplace_input_dana_mixpayment_screen");
            tapElement("checkout_marketplace_clear_input_mixpayment_screen");
        }
    }

    public void validateMixPaymentAmountOnSetMixPayment(String payment, int amount) {
        if (isElementVisible("checkout_marketplace_alchemy_metode_pembayaran")) {
            tapElement("checkout_marketplace_ubah_mixpayment_button");
        }
        String actualAmount;
        if (amount != 0) {
            if (payment.equals("Credits")) {
                actualAmount = getElementValue("checkout_marketplace_input_credits_mixpayment_screen").replaceAll("[^0-9]", "");
            } else {
                actualAmount = getElementValue("checkout_marketplace_input_dana_mixpayment_screen").replaceAll("[^0-9]", "");
            }
        } else {
            actualAmount = "";
        }
        assertEquals(amount, actualAmount.equals("") ? 0 : Integer.parseInt(actualAmount));
        setUsedMixPayment(payment, amount);
    }

    public void inputMixPaymentOnSetMixPayment(String payment, int amount) {
        if (payment.equals("Credits")) {
            tapElement("checkout_marketplace_input_credits_mixpayment_screen");
            typeAndEnterValueWithTimeOut("checkout_marketplace_input_credits_mixpayment_screen", Integer.toString(amount));
        } else {
            tapElement("checkout_marketplace_input_dana_mixpayment_screen");
            typeAndEnterValueWithTimeOut("checkout_marketplace_input_dana_mixpayment_screen", Integer.toString(amount));
        }
        setUsedMixPayment(payment, amount);
    }

    public void uncheckDeliveryProtectionCheckBox() {
        swipeUpToElement("checkout_marketplace_protection_title_text");
        waitForVisibilityOf("checkout_marketplace_protection_detail_text", 20);
        if (getElement("checkout_marketplace_protection_detail_checkbox").getAttribute("value").equalsIgnoreCase("true")) {
            tapElement("checkout_marketplace_protection_detail_checkbox");
        } else {
            LOGGER.info("Delivery protection in uncheck state");
        }
    }

    public void tapCatatanPelapak(String sellerName) {
        TransactionData.setLastEdittedSelerNoteName(sellerName);
        swipeUpToElement("checkout_marketplace_alchemy_note_pelapak_txt");
        tapElement("checkout_marketplace_alchemy_note_pelapak_txt");
    }

    public void setCatatanPelapak(String sellerNote, String sellerName) {
        typeAndEnterValue("catatan_for_pelapak_field", sellerNote);
        TransactionData.getInputtedSellerNotes().put(sellerName, sellerNote);
    }

    public void tapUbahCatatanPelapak(String sellerName) {
        TransactionData.setLastEdittedSelerNoteName(sellerName);
        String ubahButtonBuyerNotes = constructLocator("checkout_marketplace_alchemy_ubah_note_pelapak_txt", sellerName);
        waitForVisibilityOf(ubahButtonBuyerNotes, 10);
        swipeUpToElement(ubahButtonBuyerNotes);
        tapElement(ubahButtonBuyerNotes);
    }

    public void verifyCatatanPelapakUpdated(String expectedSellerNote) {
        String expectedSellerNoteTxt = constructLocator("checkout_marketplace_alchemy_catatan_pelapak_txt", expectedSellerNote);
        verifyElementExist(expectedSellerNoteTxt, 5, "expectedSellerNote not found");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

        public void verifyCatatanPelapakNotUpdated(String expectedSellerNote, String notExpectedSellerNote) {
        String expectedSellerNoteTxt = constructLocator("checkout_marketplace_alchemy_catatan_pelapak_txt", expectedSellerNote);
        String notexpectedSellerNoteTxt = constructLocator("checkout_marketplace_alchemy_catatan_pelapak_txt",
                notExpectedSellerNote);
        verifyElementExist(expectedSellerNoteTxt, 5, "expectedSellerNoteTxt" + expectedSellerNoteTxt);
        verifyElementNotExist(notexpectedSellerNoteTxt);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void tapSaveCatatanPelapak() {
        String sellerNote = TransactionData.getInputtedSellerNotes().get(TransactionData.getLastEdittedSelerNoteName());
        TransactionData.getSavedSellerNotes().put(TransactionData.getLastEdittedSelerNoteName(), sellerNote);
        tapElement("simpan_catatan_button");
    }

    public void closeCatatanPelapakSheet() {
        verifyElementExist("checkout_marketplace_close_note_pelapak_button", 5, "error");
        tapElement("checkout_marketplace_close_note_pelapak_button", 5);
    }

    public void exitFromCheckout() {
        tapElement("alchemy_navbar_back_button");
        tapElement("checkout_marketplace_prompt_confirm_exit");
    }

    public void uncheckMixPaymentCheckbox() {
        if (isElementVisible("checkout_marketplace_mix_payment_saldo_text")) {
            swipeDownToElement("checkout_marketplace_mix_payment_saldo_checkbox");
            tapElement("checkout_marketplace_mix_payment_saldo_checkbox");
        }
    }

    public void verifyBankVANumber(String bank) {
        String bank_va_number = "checkout_marketplace_bank_" + bank.toLowerCase().replaceAll(" ", "_") + "_va_number";
        waitForVisibilityOf(bank_va_number, 30);
        verifyElementExist(bank_va_number);
    }

    public void tapUbahPembayaran() {
        swipeUpToElement("checkout_marketplace_ubah_pembayaran_button");
        tapElement("checkout_marketplace_ubah_pembayaran_button");
    }

    private void waitLoadingBar() {
        do {
            waitFor(15);
            LOGGER.info("Loading bar status :" + isElementVisible("checkout_marketplace_loading_icon"));
        }
        while (isElementVisible("checkout_marketplace_loading_icon") && (!isElementEnabled("checkout_marketplace_bayar_button")));
    }

    //Old Checkout - by DANA
    public void chooseOldPaymentMethod(String payment_method) {
        String payment_option = "old_checkout_" + payment_method.toLowerCase().replaceAll(" ", "_") + "_method";
        swipeDownToElement(payment_option);
        tapElement(payment_option);
        MtxData.setPaymentMethod(payment_method);
    }

    public void tapBayar() {
        swipeDownToElement("old_checkout_bayar_button");
        tapElement("old_checkout_bayar_button");
    }

    public void tapLanjutkanPembayaran() {
        if (MtxData.getPaymentMethod().contains("VA") && isElementVisible("old_checkout_va_validation")) {
            waitForVisibilityOf("old_checkout_va_lanjutkan_pembayaran");
            tapElement("old_checkout_va_lanjutkan_pembayaran");
        }
    }

    //end by DANA

    // Begin - VP INSURANCE
    public void tickInsuranceCheckbox(String product, String tick, int sellerIndex) {
        String elementFee = "checkout_marketplace_asuransi_pengiriman_fee";
        String elementCheckbox = (product.toLowerCase().equals("asuransi pengiriman"))
                ? "checkout_marketplace_asuransi_pengiriman_checkbox"
                : "checkout_marketplace_insurance_goods_checkbox";

        swipeToInsuranceSection(constructLocator(elementCheckbox, sellerIndex));
        validateDisplayed(constructLocator(elementCheckbox, sellerIndex));
        tapInsuranceCheckbox(elementCheckbox, tick, sellerIndex);
        countInsuranceFee(elementCheckbox, elementFee, sellerIndex);
    }

    private void swipeToInsuranceSection(String locatorSection) {
        int maxSwipe = 3;

        do {
            swipeUp(0.8, 0.3);
            maxSwipe--;
        } while (!isElementVisible(constructLocator(locatorSection)) && maxSwipe >= 0);
    }

    private void tapInsuranceCheckbox(String element, String tick, int sellerIndex) {
        String checked = getElementValue(constructLocator(element, sellerIndex));

        waitFor(5); // need to wait checkbox value before change

        if ((tick.equals("ticks") && checked.equals("false")) || (tick.equals("unticks") && checked.equals("true"))) {
            tapElement(constructLocator(element, sellerIndex));
        }

        waitFor(5); // need to wait checkbox value after change
    }

    private void countInsuranceFee(String elementCheckbox, String elementFee, int sellerIndex) {
        int fee = getIntFromRp(constructLocator(elementFee, sellerIndex));

        if (getElementValue(constructLocator(elementCheckbox, sellerIndex)).equals("true")) {
            InsuranceData.setInsuranceFee(fee);
        }

        sumInsuranceFee(sellerIndex);
    }

    public void sumInsuranceFee(int sellerIndex) {
        int sum = 0;

        if (InsuranceData.getInsuranceFee().size() > 0) {
            sum = InsuranceData.getInsuranceFee().get(0);
        }

        if (sellerIndex == 2) {
            sum = sum + InsuranceData.getInsuranceFee().get(1);
        }

        InsuranceData.setSumFee(sum);
    }

    public void validateInsuranceFee() {
        swipeUpToElement("checkout_marketplace_insurance_fee");
        assertEquals(getIntFromRp("checkout_marketplace_insurance_fee"), InsuranceData.getSumFee());
        nativeSwipeDown();
    }

    public void validateInsuranceFeeIsNotExist() {
        swipeUp(0.8, 0.2);
        verifyElementNotExist("checkout_marketplace_insurance_fee");
        swipeDownToElement("checkout_marketplace_dropshipper");
    }

    public void tapOnNantiSajaButton() {
        waitFor(5); //load screen checkout
        if (isElementVisible("checkout_marketplace_insurance_coachmark_information_text")) {
            tapElement("checkout_marketplace_insurance_coachmark_nanti_saja_button");
        }
    }

    public void tapOnPelajariButton() {
        waitFor(5); //delay animation
        if (isElementVisible("checkout_marketplace_insurance_coachmark_information_text")) {
            tapElement("checkout_marketplace_insurance_coachmark_pelajari_button");
        }
    }

    public void tapOnMengertiButton() {
        if (isElementVisible("checkout_marketplace_insurance_coachmark_information_text")) {
            tapElement("checkout_marketplace_insurance_benefit_mengerti_button");
        }
    }
    // End - VP INSURANCE

    //Start Subsidies Voucher
    public void scrollUpToPaymentMethodPage() {
        swipeDownToElement("checkout_marketplace_page_metode_pembayaran_txt");
        verifyElementExist("checkout_marketplace_page_metode_pembayaran");
    }

    public void tapOnLihatTagihanButton() {
        swipeUpToElement("checkout_marketplace_lihat_tagihan_pembayaran");
        tapElement("checkout_marketplace_lihat_tagihan_pembayaran", 5);
    }

    public void selectVoucherku(boolean appear, String voucherName) {
        swipeDownToElement("checkout_marketplace_page_input_voucher_code");
        validateExist("checkout_marketplace_page_input_voucher_code", 5);
        //need for load list voucher
        waitFor(6);
        if (appear) {
            //validate multivoucher
            if (isElementExist("checkout_marketplace_page_select_multivoucher", 5)) {
                tapElement("checkout_marketplace_page_select_multivoucher");
                tapElement("checkout_marketplace_page_btn_pakai_multivoucher", 5);
            } else {
                swipeUpToElement(constructLocator("checkout_marketplace_page_name_voucher", voucherName));
                validateExist(constructLocator("checkout_marketplace_page_select_voucher", voucherName), 10);
                tapElement(constructLocator("checkout_marketplace_page_select_voucher", voucherName));
            }
        } else {
            swipeUpToElement(constructLocator("checkout_marketplace_page_name_voucher", voucherName));
            validateNotExist(constructLocator("checkout_marketplace_page_select_voucher", voucherName), 10);
        }
    }

    public void selectVoucherList(String voucher, String seller) {
        waitForVisibilityOf("checkout_marketplace_page_input_voucher_section", 10);
        String voucherLocator = constructLocator("checkout_marketplace_page_select_voucher_list", seller, voucher);
        swipeUpToElement(voucherLocator);
        tapElement(voucherLocator, 5);
    }

    public void tapOnUseOnSelectedVoucher() {
        if (isElementExist("checkout_marketplace_page_apply_selected_voucher", 10)) {
            tapElement("checkout_marketplace_page_apply_selected_voucher");
        } else {
            tapElement("checkout_marketplace_page_btn_pakai_multivoucher", 10);
        }
        //to handle some cases where checkout delayed into fetching state(threedot) after using all selected voucher
        waitFor(3);
    }

    public void tapOnPaymentDetails() {
        waitForVisibilityOf("checkout_marketplace_page_rincian_harga_cc", 20);
        tapElement("checkout_marketplace_page_rincian_harga_cc");
    }

    public void tapOnMultiPakai() {
        waitForVisibilityOf("checkout_marketplace_page_btn_pakai_multivoucher", 20);
        tapElement("checkout_marketplace_page_btn_pakai_multivoucher");
    }

    public void validateVouchersBenefit(DataTable selectedVouchersTable) {
        swipeUpToElement("checkout_marketplace_rincian_harga_text");
        List<Map<String, String>> selectedVouchers = selectedVouchersTable.asMaps();
        selectedVouchers.forEach(voucher -> {
            validateVoucherBenefit(
                    voucher.get("Voucher Owner"),
                    voucher.get("Voucher Type"),
                    voucher.get("Voucher Benefit"));
        });
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateVouchersBenefitCC(DataTable selectedVouchersTable) {
        tapOnPaymentDetails();
        List<Map<String, String>> selectedVouchers = selectedVouchersTable.asMaps();
        selectedVouchers.forEach(voucher -> {
            validateVoucherBenefit(
                    voucher.get("Voucher Owner"),
                    voucher.get("Voucher Type"),
                    voucher.get("Voucher Benefit"));
        });
        tapOnClosePaymentDetails();
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void tapOnClosePaymentDetails() {
        waitForVisibilityOf("checkout_marketplace_close_x_cc_page", 20);
        tapElement("checkout_marketplace_close_x_cc_page");
    }

    public void validateVoucherBenefit(String owner, String type, String amount) {
        String priceDetailCopy = SubsidiesData.buildPriceDetailCopy(owner, type);
        String locator = constructLocator("checkout_marketplace_page_benefit_voucher", priceDetailCopy, getRpFromNumber(Integer.parseInt(amount)));
        waitForVisibilityOf(locator, 20);
        validateExist(locator, 20);
    }

    public void validatePelapak(String pelapakName) {
        swipeDownToElement("checkout_marketplace_page_input_voucher_code");
        validateExist("checkout_marketplace_page_input_voucher_code", 5);
        //need for load list pelapak
        waitFor(5);
        swipeUpToElement(constructLocator("checkout_marketplace_page_pelapak_name", pelapakName));
        validateExist(constructLocator("checkout_marketplace_page_pelapak_name", pelapakName), 5);
    }

    public void typeVoucherWithoutUsed(String voucherName) {
        validateExist("checkout_marketplace_page_input_voucher_code", 5);
        typeAndEnterValue("checkout_marketplace_page_input_voucher_code", voucherName);
        if (isElementExist("checkout_marketplace_page_btn_pakai_voucher", 5)) {
            tapElement("checkout_marketplace_page_btn_pakai_voucher", 15);
        } else {
            tapElement("checkout_marketplace_page_btn_pakai_multivoucher", 15);
        }
    }

    public void typeVoucherCode(String voucherName) {
        typeVoucherWithoutUsed(voucherName);
        if (isElementExist("checkout_marketplace_page_btn_pakai_multivoucher", 15)) {
            tapElement("checkout_marketplace_page_btn_pakai_multivoucher", 5);
        }
    }

    public void setVoucherBukalapak(String voucherCode) {
        inputVoucherCode(voucherCode);
        tapOnUseInputtedVoucher();
    }

    public void inputVoucherCode(String voucherCode) {
        waitForVisibilityOf("checkout_marketplace_page_input_voucher_code", 30);
        typeAndEnterValue("checkout_marketplace_page_input_voucher_code", voucherCode);
    }

    public void tapOnUseInputtedVoucher() {
        waitForVisibilityOf("checkout_marketplace_page_btn_pakai_voucher", 30);
        tapElement("checkout_marketplace_page_btn_pakai_voucher");
    }

    public void tapVoucherField() {
        swipeUpToElement("checkout_marketplace_rincian_harga_text");
        if (isElementVisible("checkout_marketplace_voucher_terpasang_text", 10)) {
            tapElement("checkout_marketplace_voucher_terpasang_text");
        }
        //handle multivoucher and checkout revamp
        if (isElementExist("checkout_marketplace_page_multivoucher", 5)) {
            tapElement("checkout_marketplace_page_multivoucher");
        } else if (isElementExist("checkout_marketplace_page_voucher_checkout_revamp", 5)) {
            tapElement("checkout_marketplace_page_voucher_checkout_revamp");
        } else {
            swipeUpToElement("checkout_marketplace_page_voucher_bl_id_new");
            tapElement("checkout_marketplace_page_voucher_bl_id_new", 10);
        }
    }

    public void closePopUpVoucherCode() {
        swipeUpToElement("checkout_marketplace_rincian_harga_txt");
        tapElement("checkout_marketplace_page_voucher_popup_x_button");
    }

    public void validatePopUpWithoutVoucher() {
        verifyElementExist("checkout_marketplace_pop_up_without_voucher_txt");
        verifyElementExist("checkout_marketplace_pop_up_without_voucher_btn_lanjut");
        tapElement("checkout_marketplace_pop_up_without_voucher_btn_back");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void clearVoucherTextField() {
        swipeDownToElement("checkout_marketplace_page_voucher_clear_button");
        tapElement("checkout_marketplace_page_voucher_clear_button");
    }

    public void closePopUpVoucher() {
        tapElement("checkout_marketplace_page_x_button_voucher", 5);
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void errorVoucherCode(String errorText) {
        if (!isElementExist("checkout_marketplace_page_x_button_voucher", 5)) {
            tapElement("checkout_marketplace_page_input_voucher_code");
        }
        String locator = constructLocator("checkout_marketplace_page_error_voucher_message", errorText);
        assertTrue(isElementVisible(locator));
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void successVoucherCode(String successText) {
        validateDisplayed("checkout_marketplace_page_apply_selected_voucher");
        validateDisplayed("checkout_marketplace_page_voucher_success_title");
        assertTextContains(successText, getTextFromElement("checkout_marketplace_page_voucher_success_title"));
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateVoucherMessage(String voucherText) {
        //need wait for load message show after input voucher
        waitFor(15);
        swipeUpToElement(constructLocator("checkout_marketplace_page_error_voucher_message", voucherText));
        String locator = constructLocator("checkout_marketplace_page_error_voucher_message", voucherText);
        assertTrue(isElementVisible(locator));
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateMessage(String messageText) {
        waitForVisibilityOf(constructLocator("checkout_marketplace_page_error_voucher_message", messageText), 10);
        String locator = constructLocator("checkout_marketplace_page_error_voucher_message", messageText);
        assertTrue(isElementVisible(locator));
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateDeductedVoucher(boolean appear) {
        swipeUpToElement("checkout_marketplace_rincian_harga_text");
        if (appear) {
            validateExist("checkout_marketplace_page_potongan_voucher_harga", 10);
        } else {
            validateNotExist("checkout_marketplace_page_potongan_voucher_harga", 10);
        }
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateDeductedVoucherCC(boolean appear) {
        tapElement("checkout_marketplace_page_rincian_harga_cc", 10);
        if (appear) {
            validateExist("checkout_marketplace_page_potongan_voucher_harga", 10);
        } else {
            validateNotExist("checkout_marketplace_page_potongan_voucher_harga", 10);
        }
        tapElement("checkout_marketplace_close_x_cc_page");
        tapElement("checkout_marketplace_page_lanjut_bayar_cc", 5);
    }

    public void verifyPotonganVoucherApplied(String amount) {
        swipeDownToElement("checkout_marketplace_rincian_harga_text");
        String locator = constructLocator("checkout_marketplace_potongan_voucher_bukalapak", amount);
        assertTrue(isElementVisible(locator));
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    //end voucher

    //Investment
    //need to scroll using coordinate manually due to limitation when using swipeToLocator and others
    public void scrollToBukaeEmasAutoInvestSection() {
        for (int i = 0; i < 3; i++) {
            swipeUp(0.8, 0.2);
        }
    }

    public void validateBukaEmasAutoInvestCheckbox() {
        if (InvestmentData.getAutoInvestActive()) {
            validateValue().equals("true", getElementValue("checkout_autoinvest_checkbox"), "Auto Invest is not checked");
        } else {
            validateValue().equals("false", getElementValue("checkout_autoinvest_checkbox"), "Auto invest is checked");
        }
    }

    public void tapAutoInvestCheckbox() {
        tapElement("checkout_autoinvest_checkbox");
    }

    public void validateBukaEmasAutoInvest() {
        validateValue().equals("Sekalian Beli Otomatis BukaEmas (Rp" + InvestmentData.getAutoInvestBuyerAmount() + ")", getBukaEmasAutoInvestSectionAmount());
        validateValue().equals("Rp" + InvestmentData.getAutoInvestBuyerAmount(), getBukaEmasAutoInvestAmountFromDetailPayment());
    }

    private String getBukaEmasAutoInvestSectionAmount() {
        return (getText("checkout_autoinvest_section"));
    }

    private String getBukaEmasAutoInvestAmountFromDetailPayment() {
        if (!isElementExist("checkout_marketplace_autoinvest_text")) {
            swipeDownToElement("checkout_marketplace_autoinvest_text");
        }
        return (getText("checkout_marketplace_autoinvest_text"));
    }

    public void goToCheckoutUsingDirectCheckoutLink(String target) {
        String link = target.startsWith("http") ? target : dotenv.get(target);
        if (link == null) link = target;

        // sometimes it will fail on the first access because of caching problem
        try {
            openDeepLink(link);
            userOnCheckoutPage();
        } catch (AssertionError e) {
            openDeepLink(link);
            userOnCheckoutPage();
        }
    }

    public void validateItemPrice(String productName, String price) {
        String locator = constructLocator("checkout_marketplace_item_price", productName);
        swipeUpToElement(locator);
        assertEquals(getTextFromElement(locator).replaceAll("[^0-9]", ""), price);
    }

    public void userIsOnInstruksiPembayaranPage() {
        switch (MtxData.getPaymentMethod()) {
            case "Transfer Bank Manual":
                waitForVisibilityOf("payment_confirmation_transfer_batas_waktu_text", 50);
                assertTrue(isElementVisible("payment_confirmation_transfer_total_amount"));
                break;
            case "Transfer Bank":
                waitForVisibilityOf("payment_confirmation_transfer_batas_waktu_text", 50);
                assertTrue(isElementVisible("payment_confirmation_transfer_total_amount"));
                break;
            case "Transfer ke Virtual Account":
                waitForVisibilityOf("payment_confirmation_new_total_amount", 50);
                break;
            default:
                Assert.fail("Instruksi Pembayaran Page is not display");
                break;
        }
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void scrollToRincianHarga() {
        nativeSwipeUpToElement("checkout_marketplace_label_rincian_harga");
        //need to manually scroll due to cannot swipe to element
        int scroll = 0;
        do {
            swipeUp(0.8, 0.3);
            scroll++;
        } while (!isElementVisible("checkout_marketplace_label_rincian_harga") && scroll < 3);

        swipeUpToElement("checkout_marketplace_alchemy_total_harga_barang_price");
        MtxData.setTotalProductPriceCheckout(getElementValue("checkout_marketplace_alchemy_total_harga_barang_price"));

        // get total payment
        swipeUpToElement("checkout_marketplace_alchemy_total_pembayaran_price");
        MtxData.setTotalPaymentCheckout(getElementValue("checkout_marketplace_alchemy_total_pembayaran_price"));
    }

    public void validateItemFromTrayATC() {
        validateValue().equals(CartData.getProductName(), getText("checkout_marketplace_product_name"));
        validateItemPrice(CartData.getProductName(), String.valueOf(CartData.getItemPrice()));
    }

    public void tapVoucherSection() {
        scrollToRincianHarga();
        validateExist("checkout_marketplace_page_multivoucher");
        tapElement("checkout_marketplace_page_multivoucher");
    }

    public void clearVoucherField() {
        tapElement("checkout_field");
        validateExist("delete_button");
        tapElement("delete_button");
    }

    public void verifyDirectCheckoutSuccess(String link) {
        for (int i = 0; i < 10 && !isElementVisible("checkout_marketplace_alchemy_page_title", 30); i++) {
            goToCheckoutUsingDirectCheckoutLink(link);
        }
    }

    public void tapBackButton() {
        tapElement("checkout_voucher_back_button");
    }

    public void goToQuantitySection() {
        swipeDownToElement("checkout_marketplace_quantity_button");
    }

    public void typeVoucherAndApplied(String voucherCode) {
        validateExist("checkout_marketplace_page_input_voucher_code", 5);
        typeAndEnterValue("checkout_marketplace_page_input_voucher_code", voucherCode);
        tapElement("checkout_marketplace_page_btn_pakai_voucher", 5);
    }

    public void tapPakaiMultipleVoucher() {
        if (isElementExist("checkout_marketplace_page_btn_pakai_multivoucher", 15)) {
            tapElement("checkout_marketplace_page_btn_pakai_multivoucher", 5);
        }
    }

    public void validateAppliedVoucherMessage(String voucherMessage) {
        //need wait for load message show after input voucher
        waitFor(15);
        swipeUpToElement(constructLocator("checkout_marketplace_applied_voucher_message", voucherMessage));
        String locator = constructLocator("checkout_marketplace_applied_voucher_message", voucherMessage);
        assertTrue(isElementVisible(locator));
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validatePlaceholderBuyerNotes() {
        swipeUpToElement("catatan_link");
        tapElement("catatan_link");
        validateValue().contains("(Untuk keamanan harap jangan masukkan nomor HP kamu di sini)", getText("catatan_for_pelapak_placeholder"));
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void validateFlashDealTag() {
        swipeUpToElement("checkout_marketplace_flash_deal_tag");
        validateExist("checkout_marketplace_flash_deal_tag");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void userOnAlgebraMPCheckoutPage() {
        verifyElementExist("checkout_marketplace_algebra_text");
        HelperData.setLastActionPage(new CheckoutMarketplacePage(iosDriver));
    }

    public void typeOnBindingCardField(String cvv, String page) {
        switch (page) {
            case "ubah metode pembayaran":
            case "algebra checkout":
                typeAndEnterValueWithTimeOut("checkout_non_marketplace_cvv_textfield", cvv);
                tapElement("checkout_marketplace_algebra_credit_card_tipe_pembayaran_dropdown", 30);
                tapElement("checkout_non_marketplace_pembayaran_penuh_option");
                break;
            case "isi detail":
                typeAndEnterValueWithTimeOut("credit_card_detail_cvv_textfield", cvv);
                tapElement("credit_card_detail_tipe_pembayaran_dropdown", 30);
                tapElement("credit_card_detail_pembayaran_penuh_option");
                tapElement("credit_card_detail_bayar_button", 30);
                try {
                    tapElement("credit_card_detail_lanjut_bayar_button");
                } catch (Exception e) {
                    LogUtil.info("Voucher not auto apply");
                }
                break;
            case "lanjutkan pembayaran":
                typeAndEnterValueWithTimeOut("continue_credit_card_cvv_securetextfield", cvv);
                tapElement("checkout_marketplace_algebra_credit_card_tipe_pembayaran_dropdown", 30);
                tapElement("checkout_non_marketplace_pembayaran_penuh_option");
                break;
            default:
                Assert.fail(page + " is not available");
        }
    }

    public void typeOnUnbindingCardField(String number, String cvv, String page) {
        switch (page) {
            case "ubah metode pembayaran":
            case "lanjutkan pembayaran MP":
                waitForElementClickable("checkout_non_marketplace_no_textfield", 30);
                typeValue("checkout_non_marketplace_no_textfield", number);
                tapElement("checkout_non_marketplace_bulan_dropdown", 30);
                tapElement("checkout_non_marketplace_bulan_02_dropdown");
                tapElement("checkout_non_marketplace_tahun_dropdown", 30);
                tapElement("checkout_non_marketplace_tahun_2025_dropdown");
                typeAndEnterValueWithTimeOut("checkout_non_marketplace_cvv_textfield", cvv);
                tapElement("checkout_marketplace_algebra_credit_card_tipe_pembayaran_dropdown", 30);
                tapElement("checkout_non_marketplace_pembayaran_penuh_option");
                break;
            case "lanjutkan pembayaran":
                waitForElementClickable("checkout_non_marketplace_no_textfield", 30);
                typeAndEnterValue("checkout_non_marketplace_no_textfield", number);
                tapElement("checkout_non_marketplace_bulan_dropdown", 30);
                tapElement("checkout_non_marketplace_bulan_02_dropdown");
                tapElement("checkout_non_marketplace_tahun_dropdown", 30);
                tapElement("checkout_non_marketplace_tahun_2025_dropdown");
                typeAndEnterValueWithTimeOut("checkout_non_marketplace_cvv_textfield", cvv);
                tapElement("checkout_marketplace_algebra_credit_card_tipe_pembayaran_dropdown", 30);
                tapElement("checkout_non_marketplace_pembayaran_penuh_option");
                break;
            case "isi detail":
                typeAndEnterValueWithTimeOut("credit_card_detail_card_no_textfield", number);
                tapElement("credit_card_detail_bulan_dropdown", 30);
                tapElement("credit_card_detail_bulan_01_dropdown");
                tapElement("credit_card_detail_tahun_dropdown", 30);
                tapElement("credit_card_detail_tahun_2025_dropdown");
                try {
                    typeAndEnterValueWithTimeOut("credit_card_detail_cvv_non_binding_card_textfield", cvv);
                } catch (Exception e) {
                    typeAndEnterValueWithTimeOut("credit_card_detail_cvv_textfield", cvv);
                }
                tapElement("credit_card_detail_tipe_pembayaran_dropdown", 30);
                tapElement("credit_card_detail_pembayaran_penuh_option");
                tapElement("credit_card_detail_simpan_kartu_tickbox");
                tapElement("credit_card_detail_bayar_button");
                try {
                    tapElement("credit_card_detail_lanjut_bayar_button");
                } catch (Exception e) {
                    LogUtil.info("Voucher not auto apply");
                }
                break;
            case "algebra checkout":
                typeAndEnterValueWithTimeOut("checkout_non_marketplace_no_textfield", number);
                tapElement("checkout_non_marketplace_bulan_dropdown", 30);
                tapElement("checkout_non_marketplace_bulan_02_dropdown");
                tapElement("checkout_non_marketplace_tahun_dropdown", 30);
                tapElement("checkout_non_marketplace_tahun_2025_dropdown");
                typeAndEnterValueWithTimeOut("checkout_non_marketplace_cvv_textfield", cvv);
                tapElement("checkout_marketplace_algebra_credit_card_tipe_pembayaran_dropdown", 30);
                tapElement("checkout_non_marketplace_pembayaran_penuh_option");
                break;
            default:
                Assert.fail(page + " is not available");

        }
    }

    public void backFromCheckout() {
        tapElement("alchemy_navbar_back_button");
        tapElement("checkout_flash_deal_yes_button");
    }
}
