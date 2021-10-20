package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.AddOnIndihomeData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AddOnIndihomeCheckoutPage extends VpCheckOutPage {
    public AddOnIndihomeCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateCheckoutAddOnIndihomeData() {
        swipeDownToElement("INDIHOME_CHECKOUT_CUSTOMER_NUMBER_TEXT");
        validateElementWithText("INDIHOME_CHECKOUT_CUSTOMER_NUMBER_TEXT", AddOnIndihomeData.getCustomerNumber());
        validateElementWithText("INDIHOME_CHECKOUT_CUSTOMER_NAME_TEXT", AddOnIndihomeData.getCustomerName());
        validateElementContainsText("INDIHOME_CHECKOUT_PACKAGE_NAME_TEXT", AddOnIndihomeData.getPackageName());
        HelperData.setLastActionPage(new AddOnIndihomeCheckoutPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
