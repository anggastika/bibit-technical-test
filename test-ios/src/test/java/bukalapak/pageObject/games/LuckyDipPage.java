package bukalapak.pageObject.games;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class LuckyDipPage extends BasePage {

    public LuckyDipPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userIsOnLuckyDipHomePage() {
        changeContext().toWebview();
        verifyElementExist("LUCKY_DIP_IMAGE_LOGO", 20, "Game logo is not exist");
        verifyElementExist("LUCKY_DIP_BACK_BUTTON", 10, "Back button is not exist");
        verifyElementExist("LUCKY_DIP_HEADER_INFO", 10, "Header info is not exist");
        verifyElementExist("LUCKY_DIP_REWARD_BUTTON", 10, "Reward button is not exist");
        verifyElementExist("LUCKY_DIP_IMAGE_BACKGROUND", 10, "Image bg is not exist");
        verifyElementExist("LUCKY_DIP_ONBOARDING_STEP", 10, "Onboarding step is not exist");
        verifyElementExist("LUCKY_DIP_ENVELOPE_PAPER_BACK", 10, "Envelope paper back is not exist");
        verifyElementExist("LUCKY_DIP_ENVELOPE_BANNER_REWARD", 30, "Envelope banner reward is not exist");
        verifyElementExist("LUCKY_DIP_ENVELOPE_PAPER_FRONT", 10, "Envelope paper front is not exist");
        verifyElementExist("LUCKY_DIP_SCRATCH_HAND", 10, "Scratch hand is not exist");
        verifyElementExist("LUCKY_DIP_TODAY_SESSION_HEADER", 10, "Today session header is not exist");
        verifyElementExist("LUCKY_DIP_EVENT_TIME_INFO", 10, "Event time info is not exist");
        verifyElementExist("LUCKY_DIP_EVENT_TIME_CLAIMABLE", 10, "Event time claimable is not exist");
        verifyElementExist("LUCKY_DIP_NEXT_EVENT_REWARD_HEADER", 10, "Next event reward header is not exist");
        verifyElementExist("LUCKY_DIP_NEXT_EVENT_REWARD_IMAGE", 10, "Next event reward image is not exist");
        verifyElementExist("LUCKY_DIP_TNC_HEADER", 10, "TnC header is not exist");
        HelperData.setLastActionPage(new LuckyDipPage(iosDriver));
    }

    public void scratchOnCouponScratchArea() {
        //need to use coordinates, can't scratch (swipe) with element locator
        double xStart = 0.11;
        double y = 0.7;
        double xEnd = 0.4;
        changeContext().toNative();
        swipeWithCustomCoordinate(xStart, xEnd, y, y);
        changeContext().toWebview();
    }

    public void verifySnackBarNotificationIsVisible () {
        verifyElementExist("LUCKY_DIP_SNACK_BAR_NOTIFICATION", 20, "Snack bar is not exist");
    }

    public void verifyCouponCodeIsVisible () {
        verifyElementExist("LUCKY_DIP_EVENT_CODE_INFO");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
