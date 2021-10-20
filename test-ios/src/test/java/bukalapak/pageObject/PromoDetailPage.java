package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromoDetailPage extends BasePage{

    public PromoDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isOnPromoDetailPage() {
        verifyElementExist("promo_detail_navbar");
        verifyElementExist("promo_detail_image");
        verifyElementExist("promo_detail_title");
        HelperData.setLastActionPage(new PromoDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
