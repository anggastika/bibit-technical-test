package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class GalaxyTowerPage extends BasePage {

    public GalaxyTowerPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnGalaxyTowerPage() {
        waitForVisibilityOf("galaxy_tower_webview_element", 30);
        verifyElementExist("galaxy_tower_title_txt");
        verifyElementExist("galaxy_tower_webview_element");
        HelperData.setLastActionPage(new GalaxyTowerPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
