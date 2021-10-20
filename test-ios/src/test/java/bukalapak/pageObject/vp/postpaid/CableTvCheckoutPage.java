package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.PostpaidData;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class CableTvCheckoutPage extends VpCheckOutPage {

    public CableTvCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateInquireData() {
        waitForVisibilityOf("postpaid_cable_tv_checkout_customer_info_text", 15);
        validateElementWithText("postpaid_cable_tv_checkout_customer_number_text", PostpaidData.getCustomerNumber());
        validateElementWithText("postpaid_cable_tv_checkout_name_text", PostpaidData.getCustomerName());
        validateElementWithText("postpaid_cable_tv_checkout_biller_text", PostpaidData.getBiller());
    }
}
