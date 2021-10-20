package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.KartuKreditData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class KartuKreditCheckoutPage extends VpCheckOutPage {
    public KartuKreditCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void setTransactionData() {
        swipeDownToElement("postpaid_credit_card_checkout_customer_number_text");
        KartuKreditData.setCustomerNumber(getTextFromElement("postpaid_credit_card_checkout_customer_number_text"));
        KartuKreditData.setCustomerName(getTextFromElement("postpaid_credit_card_checkout_customer_name_text"));
        KartuKreditData.setBankName(getTextFromElement("postpaid_credit_card_checkout_bank_name_text"));
        HelperData.setLastActionPage(new KartuKreditCheckoutPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
