package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.AddOnIndihomeData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AddonIndihomeInvoiceDetailPage extends VpInvoiceDetailsPage {
    public AddonIndihomeInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateInvoiceAddonIndihomeData() {
        swipeUpToElement("INDIHOME_INVOICE_REF_TEXT");
        verifyElementDisplayed("INDIHOME_INVOICE_PRODUCT_TEXT");
        validateElementWithText("INDIHOME_INVOICE_CUSTOMER_NUMBER_TEXT", AddOnIndihomeData.getCustomerNumber());
        validateElementWithText("INDIHOME_INVOICE_CUSTOMER_NAME_TEXT", AddOnIndihomeData.getCustomerName());
        validateElementContainsText("INDIHOME_INVOICE_PACKAGE_NAME_TEXT", AddOnIndihomeData.getPackageName());
        verifyElementDisplayed("INDIHOME_INVOICE_LAYANAN_TEXT");
        HelperData.setLastActionPage(new AddonIndihomeInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
