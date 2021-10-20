package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DiscoverPage extends BasePage {

    public DiscoverPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void clickDiscoverMenu() {
        tapElement("discover_bottom_bar");
        waitForVisibilityOf("discover_gps_notif_title");
        turnOffGPSNotif();
        waitForVisibilityOf("discover_onboarding_title");
    }

    public void userGoToDiscoverPage() {
        tapElement("discover_bottom_bar");
        waitFor(5);
        if (isElementVisible("discover_location_access_popup")) {
            tapElement("discover_gps_allow_access_label");
            waitFor(10);
            tapElement(20, 11);
            LogUtil.info("condition true dijalankan");
            waitFor(10);
        } else {
            waitForVisibilityOf("discover_greetings_text");
            LogUtil.info("condition else dijalankan");
        }
    }

    public void clickOnboardingDiscover() {
        waitForVisibilityOf("discover_onboarding_title");
        tapElement("discover_onboarding_button");
        tapElement("discover_onboarding_lokasi_button");
    }

    public void clickSetLocation() {
        tapElement("discover_set_lokasi_button");
    }

    public void searchLocationDiscover(String location) {
        waitForVisibilityOf("discover_location_listing_search_field", 20);
        tapElement("discover_location_listing_search_field");
        typeAndEnterValue("discover_location_listing_search_field", location);
        tapElement(constructLocator("discover_location_selectloc", location));
        waitForVisibilityOf("discover_greetings_text");
    }

    public void selectDiscoverLocation(String location) {
        clickSetLocation();
        searchLocationDiscover(location);
    }

    public void turnOnGPSNotif() {
        verifyElementExist("discover_gps_notif_title");
        tapElement("discover_gps_notif_yes_button");
    }

    public void turnOffGPSNotif() {
        verifyElementExist("discover_gps_notif_title");
        tapElement("discover_gps_notif_no_button");
    }

    /*
     * Verify panel exist in every section
     */
    public void isPanelExist(String panel) {
        switch (panel) {
            case ("last seen"):
                waitForVisibilityOf("discover_last_seen_panel");
                verifyElementExist("discover_last_seen_panel");
                break;
            case ("last atf"):
                swipeDownToElement("discover_last_atf_panel");
                verifyElementExist("discover_last_atf_panel");
                break;
            case ("last atc"):
                swipeDownToElement("discover_last_atc_panel");
                verifyElementExist("discover_last_atc_panel");
                break;
            case ("best selling"):
                swipeDownToElement("discover_best_selling_panel", 6);
                verifyElementExist("discover_best_selling_panel");
                break;
            case ("history"):
                swipeDownToElement("discover_history_panel", 7);
                verifyElementExist("discover_history_panel");
                break;
            case ("store reco"):
                swipeDownToElement("discover_store_reco_panel", 8);
                verifyElementExist("discover_store_reco_panel");
                break;
            case ("nearest product"):
                swipeDownToElement("discover_view_more_button");
                verifyElementExist("discover_nearest_product_panel");
                break;
            case ("nearest store"):
                swipeDownToElement("discover_view_more_button");
                verifyElementExist("discover_nearest_product_panel");
                break;
            case ("favorite product"):
                swipeDownToElement("discover_favorite_item_panel", 11);
                verifyElementExist("discover_favorite_item_panel");
                break;
            case ("subscribed merchant"):
                swipeDownToElement("discover_favorite_store_panel", 13);
                verifyElementExist("discover_favorite_store_panel");
                break;
            case ("games"):
                swipeDownToElement("discover_games_panel", 16);
                verifyElementExist("discover_games_panel");
            default:
                LogUtil.info("panel" + panel + "not found");
                break;
        }
        HelperData.setLastActionPage(new DiscoverPage(iosDriver));
    }

    public void clickRecoSubtitle(String recoType) {
        switch (recoType) {
            case ("last seen"):
                waitForVisibilityOf("discover_last_seen_panel");
                tapElement("discover_subtitle_last_seen_panel");
                break;
            case ("last atf"):
                waitForVisibilityOf("discover_last_atf_panel");
                tapElement("discover_subtitle_last_atf_panel");
                break;
            case ("last atc"):
                waitForVisibilityOf("discover_last_atc_panel");
                tapElement("discover_subtitle_last_atc_panel");
                break;
            default:
                break;
        }
        tapBackButton();
        HelperData.setLastActionPage(new DiscoverPage(iosDriver));
    }

    /*
     * Click View More button on the panel in every section
     */

    public void findAndClickButton(String element){
        swipeDownToElement(element);
        tapElement(element);
    }

    public void clickViewMoreButton(String panel) {
        switch (panel) {
            case ("last seen"):
                findAndClickButton("discover_last_seen_view_more");
                break;
            case ("last atf"):
                findAndClickButton("discover_last_atf_view_more");
                break;
            case ("last atc"):
                findAndClickButton("discover_last_atc_view_more");
                break;
            case ("best selling"):
                findAndClickButton("discover_best_selling_view_more");
                break;
            case ("history"):
                findAndClickButton("discover_history_view_more");
                break;
            case ("store reco"):
                findAndClickButton("discover_store_reco_view_more");
                break;
            case ("nearest product"):
                findAndClickButton("discover_nearest_product_view_more");
                break;
            case ("nearest store"):
                findAndClickButton("discover_nearest_store_view_more");
                break;
            case ("favorite product"):
                swipeDownToElement("discover_favorite_item_panel",11);
                findAndClickButton("discover_favorite_product_view_more");
                break;
            case ("subscribed merchant"):
                swipeDownToElement("discover_favorite_store_panel", 13);
                findAndClickButton("discover_subscribed_merchant_view_more");
                break;
            default:
                LogUtil.info("panel" + panel + "not found");
                break;
        }
        HelperData.setLastActionPage(new DiscoverPage(iosDriver));
    }

    /*
     * Click Panel Anchor
     */
    public void clickPanelAnchorByName(String anchorName) {
        switch (anchorName) {
            case ("recommendation"):
                tapElement("discover_reco_achor");
                break;
            case ("nearest"):
                tapElement("discover_nearest_achor");
                break;
            case ("favorite"):
                tapElement("discover_favorit_achor");
                break;
            case ("games"):
                tapElement("discover_games_achor");
                break;
            default:
                break;
        }
    }

    public void clickPanelAnchorByCoordinate(String anchorName) {
        switch (anchorName) {
            case ("recommendation"):
                tapElement(62, 106);
                break;
            case ("nearest"):
                tapElement(145, 106);
                break;
            case ("favorite"):
                tapElement(228, 106);
                break;
            case ("games"):
                tapElement(310, 106);
                break;
            default:
                break;
        }
    }

    /*
     * Click Product in every panel per section
     */
    public void clickProduct(String panel) {
        switch (panel) {
            case ("last seen"):
                tapElement("discover_last_seen_product");
                break;
            case ("last atf"):
                tapElement("discover_last_atf_product");
                break;
            case ("last atc"):
                tapElement("discover_last_atc_product");
                break;
            case ("best selling"):
                tapElement("discover_best_selling_product");
                break;
            case ("history"):
                tapElement("discover_history_product");
                break;
            case ("store reco"):
                tapElement("discover_store_reco_product");
                break;
            case ("nearest product"):
                tapElement("discover_nearest_product_product");
                break;
            case ("nearest store"):
                tapElement("discover_nearest_store_product");
                break;
            case ("favorite product"):
                tapElement("discover_favorite_product_product");
                break;
            case ("subscribed merchant"):
                tapElement("discover_subscribed_merchant_product");
                break;
            case ("games"):
                tapElement("discover_games_product");
                break;
            default:
                LogUtil.info("panel" + panel + "not found");
                break;
        }
        HelperData.setLastActionPage(new DiscoverPage(iosDriver));
    }

    public void isOnViewMore(String panel) {
        switch (panel) {
            case ("last seen"):
                waitForVisibilityOf("discover_reco_achor");
                verifyElementExist("discover_last_seen_all");
                break;
            case ("last atf"):
                waitForVisibilityOf("discover_reco_achor");
                verifyElementExist("discover_last_atf_all");
                break;
            case ("last atc"):
                waitForVisibilityOf("discover_reco_achor");
                verifyElementExist("discover_last_atc_all");
                break;
            case ("best selling"):
                waitForVisibilityOf("discover_best_selling_all");
                verifyElementExist("discover_best_selling_all");
                break;
            case ("history"):
                waitForVisibilityOf("discover_history_all");
                verifyElementExist("discover_history_all");
                break;
            case ("store reco"):
                waitForVisibilityOf("discover_store_reco_all");
                verifyElementExist("discover_store_reco_all");
                break;
            case ("nearest product"):
                waitForVisibilityOf("discover_nearest_product_all");
                verifyElementExist("discover_nearest_product_all");
                break;
            case ("nearest store"):
                waitForVisibilityOf("discover_nearest_store_all");
                verifyElementExist("discover_nearest_store_all");
                break;
            case ("favorite product"):
                waitForVisibilityOf("discover_favorite_product_all");
                verifyElementExist("discover_favorite_product_all");
                break;
            case ("subscribed merchant"):
                waitForVisibilityOf("discover_subscribed_merchant_all");
                verifyElementExist("discover_subscribed_merchant_all");
                break;
            default:
                LogUtil.info("panel" + panel + "not found");
                break;
        }
        HelperData.setLastActionPage(new DiscoverPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("base_back_button");
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
