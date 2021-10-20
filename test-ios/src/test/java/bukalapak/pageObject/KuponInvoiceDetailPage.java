package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.KuponData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 01/06/20, Mon
 **/
public class KuponInvoiceDetailPage extends InvoiceDetailNonMarketplacePage {

    public KuponInvoiceDetailPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateKuponData() {
        swipeUpToElement("KUPON_INVOICE_INFO_PARTNER_NAME");
        validateValue().equals(KuponData.getMerchant(), getTextFromElement("KUPON_INVOICE_MERCHANT_NAME"));
        validateValue().equals(KuponData.getCouponName(), getTextFromElement("KUPON_INVOICE_COUPON_NAME"));
        tapOnExpandPartner();
        verifyElementExist("KUPON_INVOICE_PARTNER_NAME");
        HelperData.setLastActionPage(new KuponInvoiceDetailPage(iosDriver));
    }

    public void tapOnExpandPartner() {
        tapElement("KUPON_INVOICE_PARTNER_EXPAND");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
