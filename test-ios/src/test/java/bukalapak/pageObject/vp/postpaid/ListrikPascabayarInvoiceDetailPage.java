package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.ListrikPascabayarData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ListrikPascabayarInvoiceDetailPage extends VpInvoiceDetailsPage {
    public ListrikPascabayarInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateListrikPascabayarData() {
        swipeUpToElement("postpaid_electricity_invoice_periode_text");
        verifyElementDisplayed("postpaid_electricity_invoice_product_name_text");
        validateElementWithText("postpaid_electricity_invoice_id_pelanggan_text", ListrikPascabayarData.getCustomerNumber());
        validateElementWithText("postpaid_electricity_invoice_customer_name_text", ListrikPascabayarData.getCustomerName());
        validateElementWithText("postpaid_electricity_invoice_periode_text", ListrikPascabayarData.getCustomerPeriod());
        HelperData.setLastActionPage(new ListrikPascabayarInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
