package bukalapak.pageObject;

import bukalapak.TestInstrument;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DeeplinkTesterPage extends BasePage {

    public DeeplinkTesterPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void fillDeeplinkTester(String deeplink) {
        swipeDownToClickableElement("deeplink_tester_label");
        tapElement("deeplink_tester_label");
        waitForVisibilityOf("deeplink_tester_field",10);
        typeAndEnterValueWithTimeOut("deeplink_tester_field", TestInstrument.dotenv.get(deeplink));
    }
}
