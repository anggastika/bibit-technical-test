package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembeliPrioritasTagihanPage extends BasePage {

    public PembeliPrioritasTagihanPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPembeliPrioritasTagihanPage() {
        waitForVisibilityOf("priority_lihat_tagihan_button", 60);
        HelperData.setLastActionPage(new PembeliPrioritasTagihanPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
