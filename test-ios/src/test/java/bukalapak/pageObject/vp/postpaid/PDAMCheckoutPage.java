package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.AirPdamData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PDAMCheckoutPage extends VpCheckOutPage {

    public PDAMCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionData() {
        swipeDownToElement("postpaid_pdam_checkout_customer_number");
        waitForVisibilityOf("postpaid_pdam_checkout_customer_number");
        validateElementWithText("postpaid_pdam_checkout_customer_number", AirPdamData.getCustomerNumber());
        validateElementWithText("postpaid_pdam_checkout_customer_name", AirPdamData.getCustomerName());
        validateElementWithText("postpaid_pdam_checkout_customer_period", AirPdamData.getCustomerPeriod());
        HelperData.setLastActionPage(new PDAMCheckoutPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
