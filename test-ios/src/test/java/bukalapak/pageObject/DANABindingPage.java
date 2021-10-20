package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DANABindingPage extends BasePage {

    public DANABindingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void userOnDANABindingPage() {
        waitForVisibilityOf("not_active_account_text", 30);
        waitForVisibilityOf("binding_dana_text", 30);
        HelperData.setLastActionPage(new DANABindingPage(iosDriver));
    }

    public void inputPINDANA(String pinFromENV, boolean isH5) {
        if (isElementVisible("dana_container_pin_page",15)) {
            String pin = TestInstrument.dotenv.get(pinFromENV);

            if (isH5) {
                //trigger keyboard
                tapElement("dana_insert_pin",15);
                //input pin
                iosDriver.findElement(org.openqa.selenium.By.className("new-clearable-input")).sendKeys(pin);
            } else {
                changeContext().toWebview();
                //trigger keyboard
                waitFor(5); //wait loading dana webview
                iosDriver.findElement(org.openqa.selenium.By.className("new-clearable-input")).sendKeys(pin);
                //input pin
                if (pin != null) {
                    for (int i = 0; i < pin.length(); i++) {
                        iosDriver.getKeyboard().pressKey(String.valueOf(pin.charAt(i)));
                        waitFor(2); //lag handling when input pin via SDK dana
                    }
                }
                changeContext().toNative();
            }
            tapLanjutkanButton();
        } else {
            LogUtil.info("Trusted device will be auto binding without pin challenge");
        }
    }

    private void tapLanjutkanButton() {
        String element = "binding_page_lanjutkan_button";

        if (isElementVisible("binding_page_lanjutkan_button")) {
            tapElement(element);
        } else if (isElementVisible("binding_page_hubungkan_button")) {
            element = "binding_page_hubungkan_button";
            tapElement(element);
        } else {
            element = "dana_container_pin_page";
        }
        for (int i = 0; i < 10; i++) {
            if (!isElementVisible(element)) {
                break;
            }
            waitFor(1);
        }
    }

    public void cancelDANABinding() {
        waitForVisibilityOf("dana_binding_page_header",15);
        if (isElementVisible("dana_binding_page_back_btn",3)) { //white back button
            tapElement("dana_binding_page_back_btn");
        } else { //non white back button
            tapElement("dana_container_back_button");
        }
        tapElement("dana_container_cancel_binding", 15);
    }

    public void cancelDanaAfterRegister() {
        waitForVisibilityOf("dana_binding_after_register",15);
        tapElement("dana_binding_after_register", 5);
        tapElement("dana_container_cancel_binding", 15);
    }
}
