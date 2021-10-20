package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MoneyTornadoPage extends BasePage {

    public MoneyTornadoPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnMoneyTornadoPage() {
        waitForVisibilityOf("money_tornado_attempts_txt", 15);
        verifyElementExist("money_tornado_title_txt");
        verifyElementExist("money_tornado_attempts_txt");
        HelperData.setLastActionPage(new MoneyTornadoPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
