package bukalapak.pageObject.vp.base;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static org.junit.Assert.fail;

public class VpConfirmationCheckOutPage extends VpBasePage {

    public VpConfirmationCheckOutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        verifyElementExist("vp_confirmation_check_out_total");
        validateExist("vp_confirmation_check_out_text_total", LONG_TIMEOUT);
        HelperData.setLastActionPage(new VpConfirmationCheckOutPage(iosDriver));
    }

    public void setTransactionData() {
        TransactionData.setTotalPrice(getText("vp_confirmation_check_out_text_total"));
        switch (TransactionData.getPaymentMethodGroup()) {
            case "virtual account":
            case "transfer bank":
                swipeUpToElement("vp_confirmation_check_out_va_text_nomor_pesanan");
                String rawInvoiceNo = getText("vp_confirmation_check_out_va_text_nomor_pesanan");
                String invoiceNo = rawInvoiceNo.substring(rawInvoiceNo.lastIndexOf(" ") + 1).replaceAll("[^A-Z0-9]", "");
                TransactionData.setInvoiceNo(invoiceNo);
                break;
            case "gerai":
                TransactionData.setInvoiceNo(
                    getText("vp_confirmation_check_out_gerai_text_nomor_pesanan")
                        .replaceAll("[^A-Z0-9]", "")
                );
                break;
            default:
                fail(String.format("%s step not implemented yet", TransactionData.getPaymentMethodGroup()));
        }
    }

    public void tapOnButtonLihatPesanan() {
        swipeUpToElement("vp_confirmation_check_out_text_button_lihat_pesanan");
        tapElement("vp_confirmation_check_out_text_button_lihat_pesanan");
    }
}
