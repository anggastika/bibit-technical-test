package bukalapak.pageObject.bee.bukaSend.WebView;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaSendWebViewLandingPage extends BasePage {
    public BukaSendWebViewLandingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateLandingPage() {
        delay(4000);
        changeContext().toWebview();
        verifyElementExist("BUKASEND_WEBVIEW_SEND_PACKAGE_BUTTON", 5,"Send Package Button not found");
    }

    public void clickSendSinglePackageButton() {
        try {
            tapElement("BUKASEND_WEBVIEW_SEND_PACKAGE_BUTTON");
        } catch (Exception e) {
            tapElement("BUKASEND_WEBVIEW_CLOSE_ICON");
            tapElement("BUKASEND_WEBVIEW_SEND_PACKAGE_BUTTON");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
