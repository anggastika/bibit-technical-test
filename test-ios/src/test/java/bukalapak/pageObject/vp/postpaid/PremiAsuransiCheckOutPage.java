package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.PostpaidData;
import bukalapak.data.vp.*;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class PremiAsuransiCheckOutPage extends VpCheckOutPage {
    public PremiAsuransiCheckOutPage(IOSDriver<IOSElement> iosDriver) { super(iosDriver); }

    public void validateTransactionData() {
        waitForVisibilityOf("vp_checkout_premi_asuransi_text_customer_number", 10);
        assertEquals(PostpaidData.getCustomerNumber(), getTextFromElement("vp_checkout_premi_asuransi_text_customer_number"));
    }
}
