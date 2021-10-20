package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PremiumLandingAlchemyPage extends BasePage {

    public PremiumLandingAlchemyPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPremiumLandingPage() {
        verifyElementExist("premium_landing_alchemy_title");
        verifyElementExist("premium_landing_alchemy_back_button");
        HelperData.setLastActionPage(new PremiumLandingAlchemyPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        tapElement("premium_landing_alchemy_back_button");
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
