package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.KuponData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 28/05/20, Thu
 **/
public class KuponLandingPage extends VpBasePage {

    public KuponLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        skipCoachMark();
        waitForVisibilityOf("KUPON_LANDING_CATEGORY_ITEM", 20);
        verifyElementExist("KUPON_LANDING_SEARCH_KUPON");
        verifyElementExist("KUPON_LANDING_HEADER_TEXT");
        verifyElementExist("KUPON_LANDING_CATEGORY_ITEM");
        verifyElementExist("KUPON_LANDING_POPULAR_MERCHANT_ITEM");
    }

    public void skipCoachMark() {
        if (!KuponData.isCoachMarked()) {
            tapOnCoachMark();
            KuponData.setCoachMarked(true);
        }
    }

    public void tapOnCoachMark() {
        verifyElementExist("KUPON_LANDING_COACHMARK_BUTTON");
        tapElement("KUPON_LANDING_COACHMARK_BUTTON");
        verifyElementNotExist("KUPON_LANDING_COACHMARK_BUTTON");
    }

    public void tapOnSeeAllMerchant() {
        verifyElementExist("KUPON_LANDING_POPULAR_MERCHANT_SEE_ALL_BUTTON");
        tapElement("KUPON_LANDING_POPULAR_MERCHANT_SEE_ALL_BUTTON");
        HelperData.setLastActionPage(new KuponLandingPage(iosDriver));
    }

    public void choosePopularMerchant() {
        KuponData.setMerchant(getTextFromElement("KUPON_LANDING_POPULAR_MERCHANT_TEXT", 0));
        tapElements("KUPON_LANDING_POPULAR_MERCHANT_ITEM", 0);
        HelperData.setLastActionPage(new KuponLandingPage(iosDriver));
    }

    public void tapOnCategory(String category) {
        tapElement(constructLocator("KUPON_LANDING_CATEGORY_NAME_TEXT", category));
    }

    public void searchCoupon(String keyword) {
        typeAndEnterValue("KUPON_LANDING_SEARCH_KUPON", keyword);
    }

    public void tapOnSemuaKupon() {
        tapElement("KUPON_LANDING_SEMUA_KUPON");
    }

    public void searchInvalidKupon() {
        waitForVisibilityOf("KUPON_LANDING_SEARCH_KUPON_2", 5);
        typeAndEnterValue("KUPON_LANDING_SEARCH_KUPON_2", "!@#$%^&*()-_=+");
    }

    public void searchInvalidKuponFromLandingPage() {
        waitForVisibilityOf("KUPON_LANDING_SEARCH_KUPON", 5);
        typeAndEnterValue("KUPON_LANDING_SEARCH_KUPON", "!@#$%^&*()-_=+");
    }

    public void validateKuponIsNotFound() {
        validateDisplayed("KUPON_LANDING_NOT_FOUND_CONTENT_1");
        validateDisplayed("KUPON_LANDING_NOT_FOUND_CONTENT_2");
        HelperData.setLastActionPage(new KuponLandingPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
