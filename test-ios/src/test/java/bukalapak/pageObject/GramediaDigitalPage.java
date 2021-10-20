package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class GramediaDigitalPage extends BasePage {

    public GramediaDigitalPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage(String flag) {
        if (flag == null) {
            waitForVisibilityOf("gramedia_digital_header", 50);
        } else {
            waitForVisibilityOf("gramedia_not_found_header_page", 50);
        }
        HelperData.setLastActionPage(new GramediaDigitalPage(iosDriver));
    }
}
