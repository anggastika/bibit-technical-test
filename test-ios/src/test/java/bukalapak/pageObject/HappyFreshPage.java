package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class HappyFreshPage extends BasePage  {

    public HappyFreshPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage(String flag) {
        if (flag == null) {
            waitForVisibilityOf("happyfresh_header", 50);
        } else {
            waitForVisibilityOf("happyfresh_not_found_header_page", 50);
        }

        HelperData.setLastActionPage(new HappyFreshPage(iosDriver));
    }
}
