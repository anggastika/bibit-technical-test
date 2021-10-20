package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaIklanPage extends BasePage {

    public BukaIklanPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukaIklanPage() {
        assertTrue(isElementVisible("bukaiklan_header_text"));
        HelperData.setLastActionPage(new BukaIklanPage(iosDriver));
    }

    public void tapPelajariButton() {
        swipeDownToElement("bukaiklan_pelajari_button");
        if (isElementVisible("bukaiklan_pelajari_button")) {
            tapElement("bukaiklan_pelajari_button");
        }
    }

    public void goToHomePage() {
        tapElement("bukaiklan_back_button");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
