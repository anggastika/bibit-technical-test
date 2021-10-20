package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedPushDetailCampaignPage extends BasePage {

    public PromotedPushDetailCampaignPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void goToPromotedPushCampaignPage(String pageTitle) {
        String pageTitleUrl = pageTitle.replaceAll(" ", "%20");
        openDeepLink("/promoted-products-campaign?title="+pageTitleUrl+"&keyword="+pageTitleUrl);
    }

    public void verifyPromotedPushCampaignTitlePage(String pageTitle) {
        verifyElementExist(constructLocator("promoted_push_campaign_page_title", pageTitle));
    }

    public void verifyPromotedPushCampaignProducts(String productName) {
        verifyElementExist(constructLocator("promoted_push_campaign_product_title", productName));
        HelperData.setLastActionPage(new PromotedPushDetailCampaignPage(iosDriver));
    }

    private void verifyDeleteModal() {
        waitForVisibilityOf("promoted_push_campaign_delete_modal_title");
        verifyElementDisplayed("promoted_push_campaign_delete_modal_description");
        verifyElementDisplayed("promoted_push_campaign_delete_modal_yes_button");
        verifyElementDisplayed("promoted_push_campaign_delete_modal_no_button");
    }

    public void deletePromotedCampaign() {
        verifyElementDisplayed("promoted_push_campaign_delete_button");
        tapElement("promoted_push_campaign_delete_button");
        verifyDeleteModal();
        tapElement("promoted_push_campaign_delete_modal_yes_button");
    }

    public void switchesToggle(String state) {
        waitForVisibilityOf("promoted_push_detail_campaign_toggle", 10);
        if (state.equalsIgnoreCase("aktif")) {
            if (getText("promoted_push_detail_campaign_promotion_state").equalsIgnoreCase("Tidak Aktif")) {
                tapElement("promoted_push_detail_campaign_toggle");
            }
        } else {
            if (getText("promoted_push_detail_campaign_promotion_state").equalsIgnoreCase("Aktif")) {
                tapElement("promoted_push_detail_campaign_toggle");
            }
        }
    }

    public void tabBackButtonFromDetailCampaign() {
        waitForElementClickable("promoted_push_detail_campaign_back_button", 10);
        tapElement("promoted_push_detail_campaign_back_button");
    }
}
