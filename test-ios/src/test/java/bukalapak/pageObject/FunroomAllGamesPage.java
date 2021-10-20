package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class FunroomAllGamesPage extends BasePage {

    public FunroomAllGamesPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnFunroomAllGamesPage() {
        verifyElementExist("funroom_all_games_first_content_image");
        HelperData.setLastActionPage(new FunroomAllGamesPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
