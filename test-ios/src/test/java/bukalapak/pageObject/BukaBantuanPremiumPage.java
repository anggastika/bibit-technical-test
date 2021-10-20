package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaBantuanPremiumPage extends BasePage {

    public BukaBantuanPremiumPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnBukaBantuanPremiumPage() {
        waitForVisibilityOf("bukabantuan_premium_header");
        HelperData.setLastActionPage(new BukaBantuanPremiumPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
