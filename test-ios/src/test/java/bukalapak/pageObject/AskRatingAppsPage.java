package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AskRatingAppsPage extends BasePage {

    public AskRatingAppsPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isOnAskRatingAppsSheet() {
        verifyElementExist("ASK_RATING_SHEET_HEADER_TEXT");
        verifyElementExist("ASK_RATING_SHEET_BODY_TEXT");
        verifyElementExist("ASK_RATING_SHEET_STAR_BUTTON");
        verifyElementExist("ASK_RATING_SHEET_SELANJUTNYA_BUTTON");
    }

    public void checkDefaultStar() {
        assertEquals("ASK_RATING_SHEET_STAR_BUTTON", "5");
    }

    public void dismissRatingAppsSheet() {
        tapElement("ASK_RATING_APPS_CLOSE_BUTTON");
        verifyElementNotExist("ASK_RATING_SHEET_HEADER_TEXT");
        HelperData.setLastActionPage(new AskRatingAppsPage(iosDriver));
    }
}
