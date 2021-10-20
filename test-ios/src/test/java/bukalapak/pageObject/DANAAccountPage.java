package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by Ihsan Hasanudin on 19/11/2020.
 */

public class DANAAccountPage extends BasePage {

    public DANAAccountPage(IOSDriver<IOSElement> iosDriver) { super(iosDriver);}

    public void onDANAAccountPage() {
        waitForVisibilityOf("dana_account_saldo_label",15);
        HelperData.setLastActionPage(new DANAAccountPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
