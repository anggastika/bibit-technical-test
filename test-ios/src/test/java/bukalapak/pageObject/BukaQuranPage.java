package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaQuranPage extends BasePage {

    public BukaQuranPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void tapOnSurah(String surah) {
        tapElement("surah_" + surah);
    }

    public void validateSurahIsNotBookmarked() {
        waitFor(2);
        verifyElementNotExist("surah_Al-Baqarah");
        HelperData.setLastActionPage(new BukaQuranSurahPage(iosDriver));
    }
  
    public void validateLastSeenSurah() {
        verifyElementExist("buka_quran_last_seen");
        verifyElementExist("buka_quran_seen_surah");
        verifyElementExist("buka_quran_seen_hal");
        verifyElementExist("buka_quran_seen_juz");
    }

    public void goToHomePage() {
        tapElement("buka_quran_back_icon");
        tapElement("back_icon");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
