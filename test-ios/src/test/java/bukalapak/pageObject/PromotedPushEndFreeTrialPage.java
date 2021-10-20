package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedPushEndFreeTrialPage extends BasePage {

    public PromotedPushEndFreeTrialPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateEndFreeTrialPage() {
        waitForVisibilityOf("END_FREE_TRIAL_TITLE", 15);
        verifyElementDisplayed("END_FREE_TRIAL_DESCRIPTION_1");
        verifyElementDisplayed("END_FREE_TRIAL_DESCRIPTION_2");
        verifyElementDisplayed("END_FREE_TRIAL_ATUR_BUDGET");
        verifyElementDisplayed("END_FREE_TRIAL_CEK_PROMOSI");
        HelperData.setLastActionPage(new PromotedPushEndFreeTrialPage(iosDriver));
    }

    public void tapAturBudgetButton() {
        tapElement("END_FREE_TRIAL_ATUR_BUDGET");
    }

    public void tapCekPromosiButton() {
        tapElement("END_FREE_TRIAL_CEK_PROMOSI");
    }

    public void skipEndFreeTrialPage() {
        if (isElementVisible(constructLocator("END_FREE_TRIAL_TITLE"), 3)) {
            tapElement("END_FREE_TRIAL_CEK_PROMOSI");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
