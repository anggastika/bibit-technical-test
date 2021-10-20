package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 28/03/19.
 */
public class BRIEpayPage extends BasePage {

    public BRIEpayPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnBRIEpayPage() {
        waitForVisibilityOf("bri_epay_internet_banking_text", 30);
        HelperData.setLastActionPage(new BRIEpayPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}