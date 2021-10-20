package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class BukaIklanFAQPage extends BasePage {

    public BukaIklanFAQPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukaIklanFAQPage() {
        waitForVisibilityOf("bukaiklan_faq_title", 20);
        assertTrue(isElementVisible("bukaiklan_faq_content"));
        HelperData.setLastActionPage(new BukaIklanPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("bukaiklan_back_button");
        tapElement("bukaiklan_back_button");
        if (!isElementVisible("bukaiklan_pelajari_button")) {
            tapElement("bukaiklan_back_button");
        }
        tapElement("bukaiklan_back_button");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
