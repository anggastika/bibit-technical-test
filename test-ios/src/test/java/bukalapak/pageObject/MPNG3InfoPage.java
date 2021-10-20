package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MPNG3InfoPage extends BasePage {

    public MPNG3InfoPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void tapMPNG3Info() {
        waitForVisibilityOf("mpng3_billcode_input", 15);
        tapElement("mpng3_info_btn");
    }

    public void validateMPNG3Info() {
        waitForVisibilityOf("mpng3_info_text", 15);
        swipeDownToElement("mpng3_dja_info");
        isElementVisible("mpng3_djp_info");
        isElementVisible("mpng3_djbc_info");
        HelperData.setLastActionPage(new MPNG3InfoPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("mpng3_info_back");
        tapElement("alchemy_navbar_back_button");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
