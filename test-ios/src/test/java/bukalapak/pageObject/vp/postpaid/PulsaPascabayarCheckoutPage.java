package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.PulsaPascabayarData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PulsaPascabayarCheckoutPage extends VpCheckOutPage {
    public PulsaPascabayarCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateCheckoutPulsaPascabayarData() {
        swipeDownToElement("postpaid_phone_credit_checkout_customer_number_text");
        validateElementWithText("postpaid_phone_credit_checkout_customer_number_text", PulsaPascabayarData.getCustomerNumber());
        verifyElementDisplayed("postpaid_phone_credit_checkout_customer_name_text");
        verifyElementDisplayed("postpaid_phone_credit_checkout_bill_period_text");
        HelperData.setLastActionPage(new PulsaPascabayarCheckoutPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
