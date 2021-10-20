package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.data.vp.postpaid.BpjsData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BPJSInvoiceDetailPage extends VpInvoiceDetailsPage {
    public BPJSInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateBPJSKesehatanData() {
        swipeUpToElement("postpaid_bpjs_invoice_period_text");
        verifyElementDisplayed("postpaid_bpjs_invoice_product_name_text");
        validateElementWithText("postpaid_bpjs_invoice_customer_name_text", BpjsData.getCustomerName());
        validateElementWithText("postpaid_bpjs_invoice_customer_number", BpjsData.getCustomerNumber());
        HelperData.setLastActionPage(new BPJSInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
