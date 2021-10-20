package bukalapak.pageObject.vp.base;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import bukalapak.data.vp.VpTransactionData;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assume;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class VpCheckOutPage extends VpBasePage {

    public VpCheckOutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    // check by element's attribute
    @Override
    public boolean isElementVisible(String elementLocator) {
        try {
            return Boolean.parseBoolean((iosDriver.findElement(this.getLocator(elementLocator))).getAttribute("visible"));
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // need to override bcs screen has floating button that can not be swiped
    @Override
    public boolean swipeUpToElement(String elementLocator) {
        for (int i = 0; i < 3; i++) {
            if (!isElementVisible(elementLocator)) {
                swipeUp(0.6, 0.1);
            }
        }
        return false;
    }

    // need to override bcs screen has floating button that can not be swiped
    @Override
    public boolean swipeDownToElement(String elementLocator) {
        for (int i = 0; i < 3; i++) {
            if (!isElementVisible(elementLocator)) {
                swipeDown(0.4, 0.9);
            }
        }
        return false;
    }

    public void validateOnPage() {
        changeContext().toNative();
        waitForVisibilityOf("vp_check_out_text_toolbar_title", VERY_LONG_TIMEOUT);
        HelperData.setLastActionPage(new VpCheckOutPage(iosDriver));
    }

    public void choosePaymentMethod(String paymentMethod) {
        String tmpPaymentMethod = paymentMethod.toLowerCase().replaceAll(" ", "_");

        swipeUpToElement("vp_check_out_menu_metode_pembayaran");
        tapElement("vp_check_out_menu_metode_pembayaran");
        verifyElementExist("vp_check_out_text_toolbar_title_metode_pembayaran");
        switch (tmpPaymentMethod) {
            case "dana":
                tapElement("vp_check_out_group_dana");
                break;
            case "bukatabungan":
                tapElement("vp_check_out_group_bukatabungan");
                break;
            case "credits":
                Assume.assumeFalse("Credits channel is down!", isElementVisible("vp_check_out_group_credits_text_gangguan"));
                tapElement("vp_check_out_group_credits");
                break;
            case "va_bca":
            case "va_bni":
            case "va_bni_syariah":
            case "va_bri":
            case "va_bri_syariah":
            case "va_mandiri":
            case "va_mandiri_syariah":
            case "va_cimb_niaga":
            case "va_danamon":
            case "va_ocbc_nisp":
            case "va_permata":
                TransactionData.setPaymentMethodGroup("virtual account");
                tapElement("vp_check_out_group_transfer_va");
                waitForVisibilityOf("vp_check_out_radio_button");
                tapElement("vp_check_out_radio_button_" + tmpPaymentMethod, Direction.UP);
                break;
            case "transfer_bank":
                TransactionData.setPaymentMethodGroup("transfer bank");
                tapElement("vp_check_out_group_transfer_manual", Direction.UP);
                break;
            case "mitra":
            case "indomaret":
            case "alfamart":
            case "pos_indonesia":
                TransactionData.setPaymentMethodGroup("gerai");
                // need to re-tap if debug element covers target element
                for (int i = 0; i < 3; i++) {
                    swipeUpToElement("vp_check_out_group_gerai");
                    tapElement("vp_check_out_group_gerai", Direction.UP);
                    if (isElementExist("vp_check_out_radio_button")) break;
                }
                waitForVisibilityOf("vp_check_out_radio_button_" + tmpPaymentMethod, 5);
                tapElement("vp_check_out_radio_button_" + tmpPaymentMethod);
                break;
            case "kartu_kredit":
                tapElement("vp_check_out_group_kartu_kredit", Direction.UP);
                break;
            default:
                fail(String.format("%s step not implemented yet", tmpPaymentMethod));
        }
        waitForVisibilityOf("vp_check_out_button_gunakan_metode_ini");
        tapElement("vp_check_out_button_gunakan_metode_ini");
        TransactionData.setPaymentMethod(paymentMethod);
    }

    public void validatePaymentAmount() {
        int totalAmount = 0;
        List<Integer> amount = new ArrayList<>();
        List<IOSElement> detailsAmountElements = getElements("vp_check_out_text_transaction_amounts");

        for (IOSElement detailsAmountElement : detailsAmountElements)
            amount.add(getIntFromRp(detailsAmountElement.getText()));
        for (int lists : amount) totalAmount += lists;
        validateValue().equals(totalAmount, getIntFromRp(getText("vp_check_out_text_total_pembayaran")));
        TransactionData.setTotalPrice(getText("vp_check_out_text_total_pembayaran"));
    }

    public void tapOnButtonBayar() {
        try {
            // Handle stall element
            delay(3000);
            waitForVisibilityOf("vp_check_out_button_bayar_dengan", MEDIUM_TIMEOUT);
            tapCenterOfElement("vp_check_out_button_bayar_dengan");
        } catch (WebDriverException e) { // intermittent no views in hierarchy found matching
            // Handle stall element
            delay(3000);
            waitForVisibilityOf("vp_check_out_button_bayar_dengan", MEDIUM_TIMEOUT);
            tapCenterOfElement("vp_check_out_button_bayar_dengan");
        }

        if (TransactionData.getPaymentMethod().contains("VA") && isElementVisible("vp_check_out_pop_up_lanjut_pembayaran", SHORT_TIMEOUT))
            tapElement("vp_check_out_pop_up_lanjut_pembayaran");
        if (TransactionData.getPaymentMethod().equals("DANA"))
            Assume.assumeFalse("DANA channel is down!", isElementVisible("vp_confirmation_check_out_text_kendala"));
    }

    public void applyVoucher(String voucher) {
        swipeDownToElement("vp_checkout_pilih_metode_pembayaran");
        if (getElementValue("vp_checkout_pilih_metode_pembayaran").equalsIgnoreCase("Pilih Metode Pembayaran")) {
            choosePaymentMethod("Alfamart");
        }
        swipeUpToElement("checkout_non_marketplace_last_rincian_harga");
        tapElement("checkout_non_marketplace_voucher_button");
        typeAndEnterValue("vp_check_out_field_voucher", voucher);
        tapElement("checkout_non_marketplace_voucher_submit_button");
    }

    public void validateDiscountVoucher(boolean isDiscount) {
        if (isDiscount) {
            swipeUpToElement("checkout_non_marketplace_voucher_detail_price");
            verifyElementDisplayed("checkout_non_marketplace_voucher_detail_price");
            VpTransactionData.setDiscountAmount(getText("checkout_non_marketplace_voucher_detail_price"));
        } else validateNotExist("checkout_non_marketplace_voucher_detail_price", 10);
    }

    public void validateDefaultPayment(String paymentMethod) {
        swipeUpToElement("vp_check_out_menu_metode_pembayaran");
        verifyElementDisplayed(constructLocator("vp_check_out_default_payment", paymentMethod));
    }

    public void setOnCheckBoxMixDana(boolean isChecked) {
        if (isElementExist("checkout_non_marketplace_dana_checkbox")) {
            // handle element stale
            waitForVisibilityOf("checkout_non_marketplace_dana_checkbox", MEDIUM_TIMEOUT);
            if (isChecked && !isElementChecked("checkout_non_marketplace_dana_checkbox"))
                tapElement("checkout_non_marketplace_dana_checkbox");
            else if (!isChecked && isElementChecked("checkout_non_marketplace_dana_checkbox"))
                tapElement("checkout_non_marketplace_dana_checkbox");
        }
    }

    public void setOnFieldVoucherCode(String voucherCode) {
        waitForVisibilityOf("checkout_non_marketplace_alchemy_total_payment_text", MEDIUM_TIMEOUT);
        VpTransactionData.setPaymentTotal(getText("checkout_non_marketplace_alchemy_total_payment_text"));
        if (getElementValue("vp_checkout_pilih_metode_pembayaran").equalsIgnoreCase("Pilih Metode Pembayaran")) {
            choosePaymentMethod("Alfamart");
        }
        swipeUpToElement("checkout_non_marketplace_voucher_button");
        tapElement("checkout_non_marketplace_voucher_button");
        waitForVisibilityOf("vp_check_out_field_voucher", MEDIUM_TIMEOUT);
        typeAndEnterValue("vp_check_out_field_voucher", voucherCode);
        tapElement("checkout_non_marketplace_voucher_submit_button");
    }

    public void validateVoucherValidationText(String text) {
        delay(3000);
        swipeUpToElement(constructLocator("checkout_non_marketplace_voucher_valid_msg_text", text));
        validateExist(constructLocator("checkout_non_marketplace_voucher_valid_msg_text", text), MEDIUM_TIMEOUT);
    }
}
