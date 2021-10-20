package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembiayaanTunaiKycPreviewPage extends BasePage {
    public PembiayaanTunaiKycPreviewPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateInKycPreviewPage() {
        waitForVisibilityOf("pembiayaantunai_preview_selanjutnya_button", 10);
        verifyElementExist("pembiayaantunai_preview_selanjutnya_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapOnSelanjutnyaButton() {
        waitForVisibilityOf("pembiayaantunai_preview_selanjutnya_button", 10);
        tapElement("pembiayaantunai_preview_selanjutnya_button");
        waitFor(2);
        tapElement("pembiayaantunai_preview_yakirim_button");
    }
}
