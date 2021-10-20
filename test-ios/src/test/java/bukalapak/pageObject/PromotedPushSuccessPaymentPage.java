package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedPushSuccessPaymentPage extends BasePage {

    public PromotedPushSuccessPaymentPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnSuccessPaymentPage() {
        waitForVisibilityOf("tagihan_lihat_detail_pesanan_button", 60);
        HelperData.setLastActionPage(new PromotedPushSuccessPaymentPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
