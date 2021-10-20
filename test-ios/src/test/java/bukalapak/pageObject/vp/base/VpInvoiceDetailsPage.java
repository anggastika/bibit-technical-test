package bukalapak.pageObject.vp.base;

import bukalapak.data.HelperData;
import bukalapak.data.vp.VpTransactionData;
import bukalapak.data.vp.prepaid.PulsaPrabayarData;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class VpInvoiceDetailsPage extends VpBasePage {

    public VpInvoiceDetailsPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    protected void swipeToBottom() {
        // minimize section for faster to bottom
        tapElement("vp_invoice_details_section_informasi_pesanan");
        if (isElementVisible("vp_invoice_details_section_petunjuk_pembayaran"))
            tapElement("vp_invoice_details_section_petunjuk_pembayaran");
        swipeUpToElement("vp_invoice_details_section_informasi_partner");
    }

    public void validateOnPage() {
        waitForVisibilityOf("vp_invoice_details_text_no_pesanan", VERY_LONG_TIMEOUT);
        HelperData.setLastActionPage(new VpInvoiceDetailsPage(iosDriver));
    }

    public void validateInvoiceNumber(String invoiceNumber) {
        validateElementWithText("vp_invoice_details_text_no_pesanan", invoiceNumber);
    }

    public void validateTransactionStatus(String status) {
        String tempStatus = status.contains("Menunggu") ? "MENUNGGU PEMBAYARAN" : status;

        validateElementWithText("vp_invoice_details_text_status_transaksi", tempStatus);
    }

    public void validatePaymentAmount() {
        int totalAmount = 0;
        // get all amount elements then sum
        List<Integer> amount = new ArrayList<>();
        List<IOSElement> detailsAmountElements = getElements("vp_invoice_details_text_payment_amount");

        for (IOSElement detailsAmountElement : detailsAmountElements) {
            int priceAmount = getIntFromRp(detailsAmountElement.getText());
            amount.add(priceAmount);
            LogUtil.info("Price: " + priceAmount);
        }
        for (int subTotal : amount) {
            totalAmount += subTotal;
            LogUtil.info("Subtotal: " + subTotal);
        }
        LogUtil.info("Total: " + totalAmount);
        validateValue().equals(totalAmount, getIntFromRp(getText("vp_invoice_details_text_total_amount")));
        validateValue().equals(totalAmount, getIntFromRp(getText("vp_invoice_details_text_details_total_amount")));
    }

    public void validateDiscountVoucher(boolean isApplied) {
        if (isApplied) {
            validateExist("vp_invoice_details_text_total_amount", 15);
            try {
                validateValue().equals(VpTransactionData.getDiscountAmount(), getText("vp_invoice_details_text_details_voucher_amount_payment"));
            } catch (NoSuchElementException e) {
                // handle intermittent API invoice loading
                validateExist("vp_invoice_details_text_details_voucher_amount_payment", VERY_LONG_TIMEOUT, "Connection TimeOut");
                validateValue().equals(VpTransactionData.getDiscountAmount(), getText("vp_invoice_details_text_details_voucher_amount_payment"));
            }
        } else validateNotExist("vp_invoice_details_text_details_voucher_amount_payment", SHORT_TIMEOUT);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
