package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.pageObject.prom.PROMBasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedKeywordAturPromosiPage extends PROMBasePage {

    public PromotedKeywordAturPromosiPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPromotedKeywordAturPromosiPage() {
        waitForVisibilityOf("promoted_keyword_atur_promosi_title_text", 10);
        HelperData.setLastActionPage(new PromotedKeywordPage(iosDriver));
    }

    public void removeAllKeywords() {
        do {
            tapElement("promoted_keyword_atur_promosi_remove_button");
        } while (isElementVisible("promoted_keyword_atur_promosi_remove_button"));
    }

    public void checkSelectedKeyword(String keyword) {
        verifyElementExist(constructLocator("promoted_keyword_selected_keyword", keyword));
    }

    public void inputBidValue() {
        typeAndEnterValueWithTimeOut("promoted_keyword_bid_value_field", "600");
    }

    private void specificClick(String elementLocator) {
        // Need to specific click, due to element is not recognized by appium
        MobileElement element = getElement(elementLocator);
        int axis_x = element.getLocation().x;
        int axis_y = element.getLocation().y;
        int width = element.getSize().width;
        int height = element.getSize().height;
        tapElement(axis_x + (int) (width * 0.2), axis_y + (int) (height * 0.2));
    }

    public void selectBudgetLimit(String option) {
        if (option.equalsIgnoreCase("Tanpa Batas")) {
            scrollToElement("promoted_keyword_budget_unlimited_option", 5);
            specificClick("promoted_keyword_budget_unlimited_option");
            verifyElementNotExist("promoted_keyword_batas_budget_field");
        } else {
            scrollToElement("promoted_keyword_budget_limited_option", 5);
            tapElement("promoted_keyword_budget_limited_option");
            scrollToElement("promoted_keyword_batas_budget_field", 5);
            verifyElementExist("promoted_keyword_batas_budget_field");
        }
    }

    public void selectPeriodeLimit(String option) {
        if (option.equalsIgnoreCase("Tanpa Batas")) {
            scrollToElement("promoted_keyword_periode_unlimited_option", 5);
            specificClick("promoted_keyword_periode_unlimited_option");
            verifyElementNotExist("promoted_periode_promosi_dropdown");
        } else {
            scrollToElement("promoted_keyword_periode_limited_option", 5);
            specificClick("promoted_keyword_periode_limited_option");
            scrollToElement("promoted_periode_promosi_dropdown", 5);
            verifyElementExist("promoted_periode_promosi_dropdown");
        }
    }

    public void clickToggleButton(String status) {
        if (status.equalsIgnoreCase("off")) {
            scrollToElement("promoted_keyword_periode_limited_option", 5);
            MobileElement promoted_toggle_button = getElement("promoted_keyword_aktifkan_promosi_text");
            int promoted_toggle_button_x = promoted_toggle_button.getLocation().x;
            int promoted_toggle_button_y = promoted_toggle_button.getLocation().y;
            int promoted_toggle_button_width = promoted_toggle_button.getSize().width;
            int promoted_toggle_button_height = promoted_toggle_button.getSize().height;
            tapElement(promoted_toggle_button_x + promoted_toggle_button_width + (int) (promoted_toggle_button_height * 0.5), promoted_toggle_button_y + (int) (promoted_toggle_button_height * 0.2));
        }
    }

    public void inputSpecificBid(String bid, String keyword) {
        String bidField = "xpath_//XCUIElementTypeStaticText[@name='" + keyword + "']/preceding-sibling::XCUIElementTypeTextField";
        swipeDownToElement(bidField, 5);
        typeAndEnterValueWithTimeOut(bidField, bid);
    }

    public void checkBidValue(String keyword, String bid) {
        String bidField = constructLocator("promoted_keyword_bid_val", keyword);
        assertEquals(bidField, bid);
    }

    public void checkKeywordBidField(String keyword) {
        String bidField = "xpath_//XCUIElementTypeStaticText[@name='" + keyword + "']/preceding-sibling::XCUIElementTypeTextField";
        verifyElementExist(bidField);
    }

    public void inputBudgetLimit(String budget) {
        typeAndEnterValueWithTimeOut("promoted_keyword_batas_budget_field", budget);
    }

    public void checkBudgetLimit() {
        swipeUpToElement("promoted_keyword_periode_limited_option", 5);
        verifyElementExist("promoted_keyword_batas_budget_field");
    }

    public void checkPeriodeLimit() {
        scrollToElement("promoted_keyword_aktifkan_promosi_text",10);
        verifyElementExist("promoted_periode_promosi_dropdown");
    }

    public void removeSpecificKeyword(String keyword) {
        swipeUpToElement(constructLocator("promoted_keyword_remove_selected_keyword_button", keyword), 3);
        tapElement(constructLocator("promoted_keyword_remove_selected_keyword_button", keyword));
    }

    public void checkRemovedKeyword(String keyword) {
        verifyElementNotExist("label_" + keyword);
    }

    public void tapSimpanPengaturanButton() {
        swipeUpToElement("promoted_keyword_periode_limited_option", 5);
        tapElement("promoted_keyword_simpan_pengaturan_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
