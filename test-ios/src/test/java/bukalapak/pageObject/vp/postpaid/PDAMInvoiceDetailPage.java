package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.AirPdamData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PDAMInvoiceDetailPage extends VpInvoiceDetailsPage {
    public PDAMInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validatePDAMData() {
        swipeUpToElement("postpaid_pdam_invoice_period_text");
        verifyElementDisplayed("postpaid_pdam_invoice_product_name_text");
        validateElementWithText("postpaid_pdam_invoice_customer_name_text", AirPdamData.getCustomerName());
        validateElementWithText("postpaid_pdam_invoice_customer_number_text", AirPdamData.getCustomerNumber());
        validateElementWithText("postpaid_pdam_invoice_area_text", AirPdamData.getCustomerArea());
        validateElementWithText("postpaid_pdam_invoice_period_text", AirPdamData.getCustomerPeriod());
        HelperData.setLastActionPage(new PDAMInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
