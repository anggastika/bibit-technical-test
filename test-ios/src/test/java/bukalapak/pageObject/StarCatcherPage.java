package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class StarCatcherPage extends BasePage {

    public StarCatcherPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnStarCatcherPage() {
        waitForVisibilityOf("star_catcher_webview_element", 30);
        verifyElementExist("star_catcher_title_txt");
        verifyElementExist("star_catcher_webview_element");
        HelperData.setLastActionPage(new StarCatcherPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
