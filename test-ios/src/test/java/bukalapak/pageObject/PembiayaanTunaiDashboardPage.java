package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class PembiayaanTunaiDashboardPage extends BasePage {
    public PembiayaanTunaiDashboardPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateInDashboardPage() {
        waitForVisibilityOf("pembiayaantunai_ajukanpembiayaan_text", 10);
        verifyElementExist("pembiayaantunai_ajukanpembiayaan_text");
    }

    public void tapOnAjukanPembiayaanButton() {
        tapElement("pembiayaantunai_ajukanpembiayaan_text");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}