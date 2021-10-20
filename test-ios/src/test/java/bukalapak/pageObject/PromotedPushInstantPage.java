package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PROMData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedPushInstantPage extends BasePage {

    public PromotedPushInstantPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userIsOnPromotedInstant() {
        skipErrorNotif();
        waitForVisibilityOf("promoted_push_instant_header", 10);
        verifyElementExist("promoted_push_instant_header");
        HelperData.setLastActionPage(new PromotedPushInstantPage(iosDriver));
    }

    private void skipErrorNotif() {
        // sometimes on debug version, there is an error notif related Tracker failed: install
        if (isElementVisible("promoted_instant_tracker_error_notif", 2)) {
            tapElement("promoted_instant_tracker_error_notif");
        }
    }

    public void verifyTNCPromotedInstant() {
        waitForVisibilityOf("tnc_promoted_instant");
        tapCenterOfElement("tnc_promoted_instant");
        waitForVisibilityOf("tnc_description");
    }

    public void userIsOnPromotedInstantMweb() {
        waitForVisibilityOf("promoted_push_instant_header_mweb", 30);
        verifyElementExist("promoted_push_instant_keuntungan_mweb");
        verifyElementExist("promoted_push_instant_terjangkau_mweb");
        verifyElementExist("promoted_push_instant_cepat_laku_mweb");
        HelperData.setLastActionPage(new PromotedPushInstantPage(iosDriver));
    }

    public void tncPromotedInstantMweb() {
        tapElement("tnc_promoted_instant_mweb");
        waitForVisibilityOf("tnc_header_mweb", 10);
        verifyElementExist("tnc_point_one");
        verifyElementExist("tnc_point_two");
        verifyElementExist("tnc_point_three");
        verifyElementExist("tnc_point_four");
        verifyElementExist("tnc_point_five");
    }

    public void searchProduct(String productName) {
        IOSElement iosElement = getElementPresent("promoted_push_instant_search_field");
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(productName);
        tapElement("promoted_instant_search_button");
    }

    public void clickProduct(String productSearch) {
        tapElement(constructLocator("selected_product_checkbox", productSearch));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickPromosikanSekarang(String budgetPromoted) {
        tapElement("promosikan_sekarang_button");
        PROMData.setInputtedBudget(Integer.parseInt(budgetPromoted));
    }

    public void tapBayarPromotedInstant() {
        swipeUpToElement("promoted_bayar_button", 2);
        nativeSwipeUp(); // avoid failure on bezel-less iphone
        tapElement("promoted_bayar_button");
    }
}
