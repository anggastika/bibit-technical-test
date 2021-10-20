package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.vp.postpaid.PajakDaerahData;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PajakDerahCheckoutPage extends VpCheckOutPage {

    public PajakDerahCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionData() {
        waitForVisibilityOf("pajak_daerah_checkout_customer_info");
        validateElementWithText("pajak_daerah_checkout_customer_name", PajakDaerahData.getCustomerName());
        validateElementWithText("pajak_daerah_checkout_tax_type", "PBB");
        validateElementWithText("pajak_daerah_checkout_customer_number", PajakDaerahData.getCustomerNumber());
    }
}
