package bukalapak.pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class OnTripPage extends BasePage {

    public OnTripPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void isOnOnTripPage() {
        allowPopup();
        assertTrue(isElementVisible("be_carefull", 15));
        assertTrue(isElementVisible("driving_time"));
    }
}
