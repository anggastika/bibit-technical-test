package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.TelkomData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TelkomInvoiceDetailPage extends VpInvoiceDetailsPage {
    public TelkomInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTelkomData() {
        swipeUpToElement("postpaid_telkom_invoice_periode_text");
        verifyElementDisplayed("postpaid_telkom_invoice_product_name_text");
        validateElementWithText("postpaid_telkom_invoice_telephone_number_text", TelkomData.getCustomerNumber());
        validateElementWithText("postpaid_telkom_invoice_customer_name_text", TelkomData.getCustomerName());
        validateElementWithText("postpaid_telkom_invoice_periode_text",TelkomData.getCustomerPeriod());
        HelperData.setLastActionPage(new TelkomInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
