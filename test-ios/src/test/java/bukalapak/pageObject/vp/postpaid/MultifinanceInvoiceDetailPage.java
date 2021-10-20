package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MultifinanceInvoiceDetailPage extends VpInvoiceDetailsPage {
    public MultifinanceInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateMultifinanceData() {
        swipeUpToElement("postpaid_multifinance_invoice_nomor_referensi_text");
        verifyElementDisplayed("postpaid_multifinance_invoice_product_name_text");
        verifyElementDisplayed("postpaid_multifinance_invoice_customer_name_text");
        verifyElementDisplayed("postpaid_multifinance_invoice_customer_number_text");
        verifyElementDisplayed("postpaid_multifinance_invoice_penyedia_jasa_text");
        HelperData.setLastActionPage(new MultifinanceInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
