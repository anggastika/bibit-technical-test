package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.PulsaPascabayarData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PulsaPascabayarInvoiceDetailPage extends VpInvoiceDetailsPage {
    public PulsaPascabayarInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateInvoicePulsaPascabayarData() {
        swipeUpToElement("postpaid_phone_credit_invoice_bill_period_text");
        verifyElementDisplayed("postpaid_phone_credit_invoice_product_name_text");
        validateElementWithText("postpaid_phone_credit_invoice_customer_number_text", PulsaPascabayarData.getCustomerNumber());
        validateElementWithText("postpaid_phone_credit_invoice_bill_period_text", PulsaPascabayarData.getBillPeriod());
        verifyElementDisplayed("postpaid_phone_credit_invoice_customer_name_text");
        HelperData.setLastActionPage(new PulsaPascabayarInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
