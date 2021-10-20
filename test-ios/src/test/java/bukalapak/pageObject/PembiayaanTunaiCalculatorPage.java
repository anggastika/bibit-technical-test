package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembiayaanTunaiCalculatorPage extends BasePage {
    public PembiayaanTunaiCalculatorPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateInCalculatorPage() {
        waitForVisibilityOf("pembiayaantunai_ajukansekarang_button", 10);
        verifyElementExist("pembiayaantunai_ajukansekarang_button");
    }
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapOnAjukanSekarangButton() {
        waitForVisibilityOf("pembiayaantunai_ajukansekarang_button", 10);
        tapElement("pembiayaantunai_ajukansekarang_button");
    }
}
