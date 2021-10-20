package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class GameKurirPage extends BasePage {

    public GameKurirPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnGameKurirPage() {
        waitForVisibilityOf("game_kurir_webview_element", 15);
        verifyElementExist("game_kurir_title_txt");
        verifyElementExist("game_kurir_webview_element");
        HelperData.setLastActionPage(new GameKurirPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
