package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembiayaanTunaiOnBoardingPage extends BasePage {
    public PembiayaanTunaiOnBoardingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateInOnboardingPage() {
        waitForVisibilityOf("pembiayaantunai_cobapembiayaantunai_text", 10);
        verifyElementExist("pembiayaantunai_cobapembiayaantunai_text");
    }

    public void tapOnCobaPembiayaanButton() {
        waitForVisibilityOf("pembiayaantunai_cobapembiayaantunai_text", 10);
        tapElement("pembiayaantunai_cobapembiayaantunai_text");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
