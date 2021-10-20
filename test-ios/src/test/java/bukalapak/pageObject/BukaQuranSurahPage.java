package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaQuranSurahPage extends BasePage {

    public BukaQuranSurahPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void validateSurahIsOpen() {
        if (!isElementVisible("buka_quran_bookmark_icon")) {
            tapElement("buka_quran_image");
            verifyElementExist("buka_quran_bookmark_icon");
        } else {
            verifyElementExist("buka_quran_bookmark_icon");
        }
        verifyElementExist("buka_quran_menu_icon");
        HelperData.setLastActionPage(new BukaQuranSurahPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("buka_quran_back_icon");
        tapElement("buka_quran_back_icon");
        tapElement("back_icon");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
