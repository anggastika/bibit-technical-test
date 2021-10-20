package bukalapak.pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaQuranSurahSearchPage extends BasePage {

    public BukaQuranSurahSearchPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void inputSurahOnSearchField() {
        typeAndEnterValueWithTimeOut("buka_quran_search_field", "An-Nisa");
        tapElement("buka_quran_search_result");
    }
}
