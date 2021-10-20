package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PasswordCc3DsWebviewPage extends BasePage {
    public PasswordCc3DsWebviewPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPasswordCc3DsWebviewPage() {
        changeContext().toWebview();
        waitForVisibilityOf("password_cc_3ds_webview_title_text",90);
        verifyElementExist("password_cc_3ds_webview_title_text");
    }

    public void inputCCPasswordField(String password) {
        changeContext().toWebview();
        waitForVisibilityOf("password_cc_3ds_webview_password_field",30);
        typeValue("password_cc_3ds_webview_password_field", password);
        waitForVisibilityOf("password_cc_3ds_webview_ok_button",30);
        tapElement("password_cc_3ds_webview_ok_button");
        changeContext().toNative();
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
