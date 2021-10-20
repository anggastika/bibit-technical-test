package bukalapak.pageObject.investment;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaReksaProfilePage extends BasePage {

    public BukaReksaProfilePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void checkProcessingProfilePageDisplayed() {
        completeDisclaimer();
        isProfileTabSelected();
        checkProfileHeaderTitleDisplayed();
        tapOnProfileRiskDropdown();
        checkProfileRiskSection();
    }

    public void completeDisclaimer() {
        isDisclaimerModalDisplayed();
        tapDisclaimerModalBtn();
    }

    public void isProfilePageDisplayed() {
        isProfileTabSelected();
        checkProfileHeaderTitleDisplayed();
        checkProfileRiskSection();
        HelperData.setLastActionPage(new BukaReksaProfilePage(iosDriver));
    }

    public void isProfileTabSelected() {
        validateSelected("REXA_NAV_PROFILE", "Profile tab not selected");
    }

    private void isProfileRiskCollapsed() {
        verifyElementExist("PROFILE_HEADER_SET_PROFILE_RISK_BTN");
    }

    private void isDisclaimerModalDisplayed() {
        verifyElementExist("REXA_DISCLAIMER_MODAL_TITLE");
    }

    public void checkProfileHeaderTitleDisplayed() {
        validateExist("REXA_PROFIL_HEADER_TITLE", 5, "Profile title not displayed!");
    }

    private void checkProfileRiskSection() {
        verifyElementExist("PROFILE_HEADER_SECTION");
        verifyElementExist("PROFILE_HEADER_PROFILE_RISK_STATE");
        isProfileRiskCollapsed();
    }

    private void tapOnProfileRiskDropdown() {
        tapElement("PROFILE_HEADER_DROPDOWN");
    }

    private void tapDisclaimerModalBtn() {
        tapElement("REXA_DISCLAIMER_MODAL_BTN");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
