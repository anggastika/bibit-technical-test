package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class LapakmuPage extends BasePage {

    public LapakmuPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnLapakmuPage() {
        verifyElementExist("navigation_bar_title");
        verifyElementExist("menu_lapak_tab");
        verifyElementExist("statistik_tab");
        verifyElementExist("promoted_push_text");
        HelperData.setLastActionPage(new LapakmuPage(iosDriver));
    }
}
