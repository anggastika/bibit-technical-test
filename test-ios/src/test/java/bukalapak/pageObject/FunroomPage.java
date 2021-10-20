package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class FunroomPage extends BasePage {

    public FunroomPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnFunroomPage() {
        verifyElementExist("funroom_title_txt");
        verifyElementExist("funroom_all_games_btn");
        HelperData.setLastActionPage(new FunroomPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
