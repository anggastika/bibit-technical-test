package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembeliPrioritasBerhentiBerlanggananPage extends BasePage {

    public PembeliPrioritasBerhentiBerlanggananPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnBerhentiBerlanggananPage() {
        waitForVisibilityOf("priority_berhenti_perpanjangan_button", 15);
        assertEquals("Berhenti Perpanjang Otomatis", getTextFromElement("priority_berhenti_perpanjangan_button"));
        HelperData.setLastActionPage(new PembeliPrioritasBerhentiBerlanggananPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
