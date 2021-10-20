package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.TelkomData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TelkomCheckoutPage extends VpCheckOutPage {
    public TelkomCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionData() {
        swipeDownToElement("postpaid_telkom_checkout_page_customer_number_text");
        validateElementWithText("postpaid_telkom_checkout_page_customer_number_text", TelkomData.getCustomerNumber());
        validateElementWithText("postpaid_telkom_checkout_page_customer_name_text", TelkomData.getCustomerName());
        validateElementWithText("postpaid_telkom_checkout_page_periode_text",TelkomData.getCustomerPeriod());
        HelperData.setLastActionPage(new TelkomCheckoutPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
