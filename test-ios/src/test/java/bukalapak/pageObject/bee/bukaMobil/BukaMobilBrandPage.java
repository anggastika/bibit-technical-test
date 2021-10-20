package bukalapak.pageObject.bee.bukaMobil;

import bukalapak.data.BeeData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.DetailKomplainPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaMobilBrandPage extends BasePage {
    public BukaMobilBrandPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnBrandPage() {
        verifyElementDisplayed("BUKAMOBIL_BRAND_PAGE_HEADER");
        verifyElementDisplayed("BUKAMOBIL_BRAND_PAGE_TITLE");
        verifyElementDisplayed("BUKAMOBIL_BRAND_ICON");
        verifyElementDisplayed("BUKAMOBIL_BRAND_DP");
        verifyElementDisplayed("BUKAMOBIL_BRAND_AVAIL_CAR");
        verifyElementDisplayed("BUKAMOBIL_SEARCH_RESULT_IMG_SPECIFIC_CAR");
        verifyElementDisplayed("BUKAMOBIL_SEARCH_RESULT_CAR_NAME");
        verifyElementDisplayed("BUKAMOBIL_SEARCH_RESULT_PRICE");
        verifyElementDisplayed("BUKAMOBIL_SEARCH_RESULT_CAR_SEAT");
        verifyElementDisplayed("BUKAMOBIL_SEARCH_RESULT_CAR_ENGINE_TXT");
        HelperData.setLastActionPage(new BukaMobilPage(iosDriver));
    }
}
