package bukalapak.pageObject;

import bukalapak.data.HelperData;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by Ihsan Hasanudin on 7/11/2020.
 */

public class CreditsListPage extends BasePage {

    public CreditsListPage(IOSDriver<IOSElement> iosDriver) {super(iosDriver);}

    public void onCreditsListPage() {
        waitForVisibilityOf("credits_list_mutation_title");
        HelperData.setLastActionPage(new CreditsListPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
