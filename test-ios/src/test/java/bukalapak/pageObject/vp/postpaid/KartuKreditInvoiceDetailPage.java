package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.KartuKreditData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class KartuKreditInvoiceDetailPage extends VpInvoiceDetailsPage {
    public KartuKreditInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateInvoiceTransactionData() {
        swipeUpToElement("postpaid_credit_card_invoice_bank_name_text");
        verifyElementDisplayed("postpaid_credit_card_invoice_product_text");
        validateElementWithText("postpaid_credit_card_invoice_customer_number_text", KartuKreditData.getCustomerNumber());
        validateElementWithText("postpaid_credit_card_invoice_customer_name_text", KartuKreditData.getCustomerName());
        validateElementWithText("postpaid_credit_card_invoice_bank_name_text", KartuKreditData.getBankName());
        HelperData.setLastActionPage(new KartuKreditInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
