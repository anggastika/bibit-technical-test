package bukalapak.pageObject.bee.bukaMobil;

import bukalapak.data.BeeData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaMobilClassificationPage extends BasePage {
    public BukaMobilClassificationPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnClassificationPage() {
        verifyElementDisplayed("BUKAMOBIL_CLASS_PAGE_HEADER");
        verifyElementDisplayed("BUKAMOBIL_CLASS_PAGE_TITLE");
        validateValue().contains(BeeData.getCar(), getText("BUKAMOBIL_CLASS_PAGE_TITLE"));
        verifyElementDisplayed("BUKAMOBIL_SEARCH_RESULT_IMG_SPECIFIC_CAR");
        verifyElementDisplayed("BUKAMOBIL_SEARCH_RESULT_CAR_NAME");
        validateDisplayed("BUKAMOBIL_SEARCH_RESULT_PRICE");
        validateDisplayed("BUKAMOBIL_SEARCH_RESULT_CAR_SEAT");
        validateDisplayed("BUKAMOBIL_SEARCH_RESULT_CAR_ENGINE_TXT");
        HelperData.setLastActionPage(new BukaMobilPage(iosDriver));
    }
}
