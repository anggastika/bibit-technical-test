package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.KuponData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 01/06/20, Mon
 **/
public class KuponCheckoutPage extends CheckoutNonMarketplacePage {

    public KuponCheckoutPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateCheckoutDetail() {
        assertEquals(KuponData.getCouponName(), getTextFromElement("KUPON_CHECKOUT_NAME"));
        HelperData.setLastActionPage(new KuponCheckoutPage(iosDriver));
    }

    public void cancelTransaction() {
        tapElement("checkout_non_marketplace_alchemy_back_button");
        verifyElementExist("KUPON_CHECKOUT_CANCEL_BUTTON");
        tapElement("KUPON_CHECKOUT_CANCEL_BUTTON");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
