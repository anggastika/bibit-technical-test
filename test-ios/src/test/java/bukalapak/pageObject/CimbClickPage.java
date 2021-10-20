package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 18/12/18.
 */
public class CimbClickPage extends BasePage {

    public CimbClickPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnCimbClickPage() {
        waitForVisibilityOf("cimb_click_payment_method_text", 30);
        HelperData.setLastActionPage(new CimbClickPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
