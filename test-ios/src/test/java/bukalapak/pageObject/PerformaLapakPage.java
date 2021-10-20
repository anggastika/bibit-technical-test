package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PerformaLapakPage extends BasePage {
    public PerformaLapakPage(IOSDriver<IOSElement> iosDriver) { super(iosDriver);}

    public void userOnPerformaLapakPage() {
        waitForVisibilityOf("performa_lapak_title", 15);
        validateDisplayed("performa_lapak_back_button");
        HelperData.setLastActionPage(new PerformaLapakPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
