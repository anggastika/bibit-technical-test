package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.BpjsData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BPJSCheckoutPage extends VpCheckOutPage {

    public BPJSCheckoutPage(IOSDriver<IOSElement> iosDriver) { super(iosDriver);
    }

    public void validateTransactionData() {
        swipeDownToElement("postpaid_bpjs_checkout_page_customer_number_text");
        validateElementWithText("postpaid_bpjs_checkout_page_customer_number_text", BpjsData.getCustomerNumber());
        validateElementWithText("postpaid_bpjs_checkout_page_customer_name_text", BpjsData.getCustomerName());
        HelperData.setLastActionPage(new BPJSCheckoutPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
