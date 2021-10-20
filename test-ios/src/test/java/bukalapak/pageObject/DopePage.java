package bukalapak.pageObject;

import bukalapak.data.CategoryData;
import bukalapak.data.DiscoveryData;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.util.ArrayList;

public class DopePage extends BasePage {

    public DopePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToWidget(String element) {
        swipeDownToElement("home_lainnya_widget");
        tapElement("dope_see_all_button");
        try {
            swipeDownToElement(element);
        } catch (Exception e) {
            swipeToLocator(element);
        }
        tapElement(element);
    }

    public void goToDopeScreen() {
        swipeDownToElement("home_lainnya_widget");
        tapElement("dope_see_all_button", 10);
    }

    public void goToDopePage() throws Exception {
        allowPopup();
        swipeDownToElement("semua_menu_dope");
        tapElement("semua_menu_dope");
    }

    public void isOnDopePage() {
        if (isElementVisible("onboarding_ok_button")) {
            tapElement("onboarding_ok_button");
        }

        if(isElementExist("dope_search_field", 10)) {
            verifyElementExist("dope_search_field", 10, "old dope not exist");
        } else {
            isOnDopeRevampPage();
        }

        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    public void tapOnWidgetText(String title) throws Exception {
        try {
            swipeUpToElement(title + "_widget", 10);
            tapElement(title + "_widget");
        } catch (Exception e) {
            throw new Exception("Dope menu for `" + title + "` is not found!");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void isOnMwebDopePage() {
        assertTrue(isElementVisible("dope_menu_mweb"), "Failed redirect to Dope Mweb");
        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    public void isTagihanTabExist() {
        waitForVisibilityOf("tagihan_tab", 10);
        verifyElementExist("tagihan_tab");
        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    public void scrollDownToDope() {
        if (isElementVisible("dope_section"))
            swipeDownToElement("dope_section");
    }

    public void skipOnboardingDope() {
        if (isElementVisible("onboarding_text_ok")) {
            tapElement("onboarding_text_ok");
        }
    }

    public void isSemuaMenuExist() {
        skipOnboardingDope();
        waitForVisibilityOf("semua_menu_tab", 20);
        verifyElementExist("semua_menu_tab");
        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    public void clickBukamart(String text) {
        tapElement(constructLocator("bukamart_shortcut_text", text));
    }

    public void isBukamartPageExist() {
        allowPopup();
        doPullRefresh(1);
        waitForVisibilityOf("omni_search_bukamart", 10);
        verifyElementExist("omni_search_bukamart");
    }

    public void searchDopeMenu(String keywordType) {
        if(isElementVisible("dope_search_field")) {
            typeAndEnterValue("dope_search_field", keywordType);
        } else {
            typeAndEnterValue("dope_revamp_search_field", keywordType);
        }
    }

    public void isSearchResultExist(String keywordType) {
        assertTrue(!isElementVisible("dope_search_result_not_exist"), "Dope is Exist");
        verifyElementExist(constructLocator("dope_search_result", keywordType));
        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    public void tapSearchResultExist(String searchWidget) {
        String widgetElement = searchWidget.toLowerCase().replace(" ","_");
        tapElement("widget_" + widgetElement);
    }

    public void validateDANADopeFrozen() {
        waitForVisibilityOf("dope_dana_freeze_toast_text",10);
    }

    public void validateDANAHomeFrozen() {
        for (int i = 0; i < 5; i++) {
            if (isElementVisible("dope_dana_freeze_toast_text",10)) break;
            tapElement("home_dana_muat_ulang",5);
        }
    }

    public void validateSingleLineDope() {
        verifyElementDisplayed("dope_single_line_section");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void swipeDopeImage(){
        swipeLeftAtSpecifiedLocator("dope_icon");
        swipeRightAtSpecifiedLocator("dope_icon");
    }

    public void validateDopeSingleLine(int totalDopeExpected) {
        int swapCount = 0;
        while (swapCount < 10) {
            ArrayList<String> homepageDopeList = DiscoveryData.getDopeList();
            int homepageDope = getMultipleElement().withLocator("dope_title").size();

            for (int i = 0; i < homepageDope; i++) {
                String dopeName = getText("dope_title", i);
                if (homepageDopeList.size() < 4) {
                    DiscoveryData.setDopeList(dopeName);
                } else {
                    setDopeList(homepageDopeList, dopeName);
                }
            }
            swipeLeftAtSpecifiedLocator("dope_icon");
            swapCount++;
        }

        int totalDope = DiscoveryData.getDopeList().size();
        validateValue().equalsTrue(totalDope <= totalDopeExpected && totalDope >= 4);
        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    private void setDopeList(ArrayList<String> list, String dope) {
        for (String dopeOnList : list) {
            if (dopeOnList.equals(dope)) {
                DiscoveryData.getDopeList().remove(dopeOnList);
                break;
            }
        }
        DiscoveryData.setDopeList(dope);
    }

    public void isOnDopeRevampPage() {
        assertTrue(isElementVisible("dope_revamp_search_field", 10));
        assertTrue(isElementVisible("dope_category_tab_icons", 10));
        assertTrue(isElementVisible("dope_revamp_items", 10));
        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    public void tapDopeCategoryTab(String categoryName) {
        swipeUpToElement(constructLocator("dope_category_tab", categoryName), 5);

        DiscoveryData.setCategoryDopeName(getTextFromElement("dope_revamp_title", 0));
        tapElement(constructLocator("dope_category_tab", categoryName), 10);
    }

    public void tapDopeIcon(String dopeName) {
        DiscoveryData.setDopeName(dopeName);
        swipeUpToElement(constructLocator("dope_revamp_items_title", dopeName), 5);

        tapElement(constructLocator("dope_revamp_items_title", dopeName), 10);
    }

    public void validateLastViewedDope() {
        validateElementVisible("dope_last_viewed_menu");
        validateDisplayed(constructLocator("dope_revamp_items_title", DiscoveryData.getDopeName()));
        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    public void tapBackToDopeRevamp() {
        tapElement("alchemy_navbar_back_button");
        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    public void validateCategorySelected() {
        validateValue().notEquals(DiscoveryData.getCategoryDopeName(), getTextFromElement("dope_revamp_title", 0));
        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    public void validateTagInDopeMenu(String tagName) {
        switch (tagName.toLowerCase()) {
            case "promo" :
                validateDisplayed("dope_promo_tag");
                break;
            case "baru" :
                validateDisplayed("dope_new_menu_tag");
                break;
            default:
                LogUtil.error("Wrong input!");
                break;
        }
        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    public void validateCategorySelected(String dopeCategoryName) {
        if (isElementVisible(constructLocator("dope_category_tab_normal", dopeCategoryName))) {
            validateDisplayed(constructLocator("dope_category_tab_normal", dopeCategoryName));
        } else {
            validateDisplayed(constructLocator("dope_revamp_selected_category", dopeCategoryName));
        }
        HelperData.setLastActionPage(new DopePage(iosDriver));
    }

    public void doPullRefreshDopePage(int refreshCount) {
        for (int i = 1; i <= refreshCount; i++) {
            waitFor(2);
            swipeDownAtSpecifiedLocator("dope_vertical_screen_fragment");
            waitFor(2);
        }
    }

    public void tapMenuFromSearchResult(String menuName) {
        tapElement(constructLocator("dope_search_result", menuName));
    }
}
