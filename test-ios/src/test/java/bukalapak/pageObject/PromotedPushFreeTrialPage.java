package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedPushFreeTrialPage extends BasePage {

    public PromotedPushFreeTrialPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateFreeTrialPage() {
        waitForVisibilityOf("FREE_TRIAL_TITLE", 15);
        verifyElementDisplayed("FREE_TRIAL_SUB_TITLE");
        verifyElementDisplayed("FREE_TRIAL_DESC_1_TEXT");
        verifyElementDisplayed("FREE_TRIAL_DESC_2_TEXT");
        verifyElementDisplayed("FREE_TRIAL_DESC_3_TEXT");
        verifyElementDisplayed("FREE_TRIAL_DESC_4_TEXT");
        verifyElementDisplayed("FREE_TRIAL_CLAIM_BUTTON");
        verifyElementDisplayed("FREE_TRIAL_INFO_TEXT");
        HelperData.setLastActionPage(new PromotedPushFreeTrialPage(iosDriver));
    }

    public void tapCobaButton() {
        tapElement("FREE_TRIAL_CLAIM_BUTTON");
    }

    public void validateFailedClaimFreeTrialSnackbar() {
        verifyElementDisplayed("FREE_TRIAL_FAILED_INFO_TEXT");
    }

    // Promoted Push Info Link
    public void tapPromotedPushInfoLink() {
        swipeUpToElement("FREE_TRIAL_INFO_LINK");
        tapElement("FREE_TRIAL_INFO_LINK");
    }

    public void validatePromotedPushInfoWebView() {
        changeContext().toWebview();
        validateExist("FREE_TRIAL_TITLE_WEBVIEW_TEXT", 15);
        changeContext().toNative();
        verifyElementDisplayed("FREE_TRIAL_INFO_TITLE");
    }
    // End region of Promoted Push Info Link

    // TNC section
    public void tapTNCLink() {
        swipeUpToElement("FREE_TRIAL_TNC_LINK");
        tapElement("FREE_TRIAL_TNC_LINK");
    }

    public void tapCloseTNCButton() {
        tapElement("FREE_TRIAL_TNC_CLOSE_BUTTON");
    }

    public void validateTNCModal() {
        waitForVisibilityOf("FREE_TRIAL_TNC_TITLE_TEXT", 10);
        verifyElementDisplayed("FREE_TRIAL_TNC_1_TEXT");
        verifyElementDisplayed("FREE_TRIAL_TNC_2_TEXT");
        verifyElementDisplayed("FREE_TRIAL_TNC_3_TEXT");
        verifyElementDisplayed("FREE_TRIAL_TNC_4_TEXT");
        verifyElementDisplayed("FREE_TRIAL_TNC_5_TEXT");
        verifyElementDisplayed("FREE_TRIAL_TNC_6_TEXT");
        verifyElementDisplayed("FREE_TRIAL_TNC_7_TEXT");
        verifyElementDisplayed("FREE_TRIAL_TNC_8_TEXT");
        verifyElementDisplayed("FREE_TRIAL_TNC_CLOSE_BUTTON");
    }
    // End region of TNC section

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
