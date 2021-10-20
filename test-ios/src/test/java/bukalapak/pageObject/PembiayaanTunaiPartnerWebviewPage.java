package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembiayaanTunaiPartnerWebviewPage extends BasePage {
    public PembiayaanTunaiPartnerWebviewPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateInPartnerWebviewPage() {
        waitFor(5);
        verifyElementExist("pembiayaantunai_partner_webview_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapOnBackButton() {
        waitForVisibilityOf("pembiayaantunai_preview_selanjutnya_button", 10);
        tapElement("pembiayaantunai_preview_selanjutnya_button");
        waitForVisibilityOf("pembiayaantunai_preview_yakirim_button", 10);
        tapElement("pembiayaantunai_preview_yakirim_button");
    }
}
