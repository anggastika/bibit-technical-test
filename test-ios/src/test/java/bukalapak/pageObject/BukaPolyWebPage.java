package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaPolyWebPage extends BasePage {

    protected static final int MEDIUM_TIMEOUT = 15;

    public BukaPolyWebPage(IOSDriver<IOSElement> iosDriver)  {
        super(iosDriver);
    }

    public void userIsOnBukaPolyPage(){
        changeContext().toWebview();
        checkBukapolyOnboarding();
        verifyElementExist("BUKAPOLY_KEPINGAN_GRATIS_BUTTON");
        verifyElementExist("BUKAPOLY_AMBIL_KEPINGAN_BUTTON");
        verifyElementExist("BUKAPOLY_MASUKKAN_KODE_BUTTON");
        HelperData.setLastActionPage(new BukaPolyWebPage(iosDriver));
    }

    public void checkBukapolyOnboarding() {
        if(isElementExist("BUKAPOLY_ONBOARDING_CONTENT", MEDIUM_TIMEOUT)) {
            tapElement("BUKAPOLY_ONBOARDING_CONTENT_OKE");
        }
    }

    public void checkKepinganGratisBadge() {
        if(isElementExist("BUKAPOLY_KEPINGAN_GRATIS_BADGE")) {
            int total_mission = Integer.parseInt(getText("BUKAPOLY_KEPINGAN_GRATIS_BADGE"));
            validateValue().equalsTrue(total_mission > 0);
        }
    }

    public void tapOnKepinganGratisButton() {
        tapElement("BUKAPOLY_KEPINGAN_GRATIS_BUTTON");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
