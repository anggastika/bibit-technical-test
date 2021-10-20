package bukalapak.pageObject;

import bukalapak.data.ChampionData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromoPage extends BasePage {

    public PromoPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPromoPage() {
        verifyElementExist("promo_title");
        verifyElementExist("promo_search_text_field");
    }

    public void goToPromoPage() {
        openDeepLink("https://www.bukalapak.com/promo");
        HelperData.setLastActionPage(new PromoPage(iosDriver));
    }

    public void swipeLeftToPromoTab(String tabName, int maxScroll) {
        int tmpMaxScroll = maxScroll;
        double screenHeight = iosDriver.manage().window().getSize().height;

        waitForVisibilityOf("promo_tabs_container", 10);
        double yPivot = getElement("promo_tabs_container").getCenter().getY();

        double percentageY = yPivot / screenHeight;

        while (!isElementVisible(constructLocator("promo_tab", tabName), 5) && tmpMaxScroll > 0) {
            swipeLeft(0.7, 0.3, percentageY);
            tmpMaxScroll--;
        }
    }

    public void clickPromoTab(String tabName) {
        swipeLeftToPromoTab(tabName, 10);
        String elm_promotab = constructLocator("promo_tab", tabName);
        waitForElementClickable(elm_promotab,10);
        tapElement(elm_promotab);
    }

    public void clickFirstProductCampaign() {
        waitForVisibilityOf("promo_barang_first_title", 20);
        String campaignName = getTextFromElement("promo_barang_first_title");
        tapElement("promo_barang_first_title");
        ChampionData.setProductsCampaignName(campaignName);
    }

    public void goToHomePage() {
        backToHomePage();
    }
}
