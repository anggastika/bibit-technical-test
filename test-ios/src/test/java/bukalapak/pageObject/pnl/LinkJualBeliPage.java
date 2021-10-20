package bukalapak.pageObject.pnl;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class LinkJualBeliPage extends BasePage {

    public LinkJualBeliPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyDashboardWebview(){
        changeContext().toWebview();
        verifyElementExist("buat_link_baru_text", 30, "page not loaded");
    }

    public void verifyOnboardingWebview(){
        changeContext().toWebview();
        verifyElementExist("pelajari_text", 30, "page not loaded");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
