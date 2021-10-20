package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.ListrikPascabayarData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ListrikPascabayarCheckoutPage extends VpCheckOutPage {
    public ListrikPascabayarCheckoutPage (IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionData() {
        swipeDownToElement("postpaid_electricity_checkout_page_customer_number_text");
        validateElementWithText("postpaid_electricity_checkout_page_customer_number_text", ListrikPascabayarData.getCustomerNumber());
        validateElementWithText("postpaid_electricity_checkout_page_customer_name_text", ListrikPascabayarData.getCustomerName());
        validateElementWithText("postpaid_electricity_checkout_page_periode_text", ListrikPascabayarData.getCustomerPeriod());
        HelperData.setLastActionPage(new ListrikPascabayarCheckoutPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
