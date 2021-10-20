package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembeliPrioritasFreeTrialPage extends BasePage {

    public PembeliPrioritasFreeTrialPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPriorityBuyerFreeTrialPage() {
        waitForVisibilityOf("priority_free_trial_title_text");
        verifyElementExist("priority_free_trial_gratis_text");
        verifyElementExist("priority_free_trial_next_price_text");
        verifyElementExist("priority_free_trial_tnc_text");
        HelperData.setLastActionPage(new PembeliPrioritasFreeTrialPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
