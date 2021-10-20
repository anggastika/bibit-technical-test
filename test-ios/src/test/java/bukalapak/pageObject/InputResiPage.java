package bukalapak.pageObject;

import bukalapak.data.STRIPEData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.security.SecureRandom;

public class InputResiPage extends BasePage {

    public InputResiPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void typeOnMasukkanNomorResiEditText(String resiNumber) {
        if (isElementVisible("input_resi_field", 10)) {
            typeAndEnterValueWithTimeOut("input_resi_field", resiNumber);
        } else if (isElementVisible("input_resi_bulk_field", 10)){
            typeAndEnterValueWithTimeOut("input_resi_bulk_field", resiNumber);
        } else {
            typeAndEnterValueWithTimeOut("input_resi_text", resiNumber);
        }
        STRIPEData.setResiNumber(resiNumber);
    }

    public int getRandomNum(int range) {
        return new SecureRandom().nextInt(range);
    }

    public void goToHomePage() {
        backToHomePage();
    }

    public void clickOpsionalInputResi() {
        waitForVisibilityOf("opsional_input_resi", 10);
        waitForVisibilityOf("input_resi_cara_input_spinner", 10);
        tapElement("input_resi_cara_input_spinner");
        waitForVisibilityOf("input_resi_choosed", 15);
        tapElement("input_resi_choosed");
    }
}