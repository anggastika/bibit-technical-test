package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 18/12/18.
 */
public class LinkAjaPage extends BasePage {

    public LinkAjaPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnLinkAjaPage() {
        waitForVisibilityOf("linkaja_login_text", 60);
        HelperData.setLastActionPage(new LinkAjaPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapOnBackButton() {
        waitForVisibilityOf("base_back_button");
        tapElement("base_back_button");
    }
}
