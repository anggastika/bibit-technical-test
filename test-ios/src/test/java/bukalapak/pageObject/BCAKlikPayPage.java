package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 17/12/18.
 */
public class BCAKlikPayPage extends BasePage {

    public BCAKlikPayPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnBCAKlikPayPage() {
        waitForVisibilityOf("bca_klikpay_login_text", 30);
        HelperData.setLastActionPage(new BCAKlikPayPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
