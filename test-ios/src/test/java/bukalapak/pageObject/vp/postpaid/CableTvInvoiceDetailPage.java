package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class CableTvInvoiceDetailPage extends VpInvoiceDetailsPage {
    public CableTvInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateCableTvData() {
        swipeUpToElement("postpaid_cable_tv_invoice_layanan_text");
        validateElementWithText("postpaid_cable_tv_invoice_produk_text", "TV Kabel & Internet");
        validateElementWithText("postpaid_cable_tv_invoice_customer_number_text", PostpaidData.getCustomerNumber());
        validateElementWithText("postpaid_cable_tv_invoice_name_text", PostpaidData.getCustomerName());
        validateElementWithText("postpaid_cable_tv_invoice_layanan_text", PostpaidData.getBiller());
        HelperData.setLastActionPage(new CableTvInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
