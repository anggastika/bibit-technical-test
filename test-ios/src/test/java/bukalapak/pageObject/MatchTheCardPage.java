package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MatchTheCardPage extends BasePage {

    public MatchTheCardPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnMatchTheCardPage() {
        waitForVisibilityOf("match_the_card_webview_element", 30);
        verifyElementExist("match_the_card_title_txt");
        verifyElementExist("match_the_card_webview_element");
        HelperData.setLastActionPage(new MatchTheCardPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
