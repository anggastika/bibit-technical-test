package bukalapak.pageObject.bee.bukaMobil;

import bukalapak.data.BeeData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.SuperSellerOptOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaMobilSearchResultPage extends BasePage {
    public BukaMobilSearchResultPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateSearchResult() {
        waitForVisibilityOf("BUKAMOBIL_SEARCH_RESULT_PAGE_TITLE", 10);
        validateExist("BUKAMOBIL_SEARCH_RESULT_PAGE_TITLE");
        validateValue().contains(BeeData.getCar(), getText("BUKAMOBIL_SEARCH_RESULT_PAGE_TITLE"));
        validateDisplayed("BUKAMOBIL_SEARCH_RESULT_IMG_SPECIFIC_CAR");
        validateDisplayed("BUKAMOBIL_SEARCH_RESULT_CAR_NAME");
        validateDisplayed("BUKAMOBIL_SEARCH_RESULT_PRICE");
        validateDisplayed("BUKAMOBIL_SEARCH_RESULT_CAR_SEAT");
        validateDisplayed("BUKAMOBIL_SEARCH_RESULT_CAR_ENGINE_TXT");
        HelperData.setLastActionPage(new BukaMobilPage(iosDriver));
    }

    public void selectCar() {
        tapElement("BUKAMOBIL_SEARCH_RESULT_CAR");
    }
}
