package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SuperSellerLandingPage extends BasePage {

    public SuperSellerLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnSuperSellerLanding() {
        waitForVisibilityOf("super_seller_landing_title", 20);
        verifyElementExist("super_seller_landing_back_button");
        HelperData.setLastActionPage(new SuperSellerLandingPage(iosDriver));
    }

    public void verifyAktifkanSekarangButton() {
        waitForVisibilityOf("super_seller_landing_pilih_paket_ini_button", 20);
        HelperData.setLastActionPage(new SuperSellerLandingPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
