package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DailyGiftboxPage extends BasePage {

    public DailyGiftboxPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnDailyGiftboxPage() {
        verifyElementExist("daily_giftbox_title");
        HelperData.setLastActionPage(new DailyGiftboxPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
