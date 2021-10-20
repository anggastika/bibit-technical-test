package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SebarPromosiPage extends BasePage {
    public SebarPromosiPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifySebarPromosiPageDisplayed() {
        waitForVisibilityOf("title_sebar_promosi_text", 15);
        HelperData.setLastActionPage(new SebarPromosiPage(iosDriver));
    }

    public void verifyUserHaveProductFavorite() {
        waitForVisibilityOf("product_favorited_item_list", 20);
        verifyElementExist("product_favorite_name_list");
        verifyElementExist("product_favorite_disukai_list");
        HelperData.setLastActionPage(new SebarPromosiPage(iosDriver));
    }

    public void tapProductSebarPromosList() {
        waitForVisibilityOf("product_favorited_page", 15);
        tapElement("product_favorited_page");
    }

    public void tapProductFavoriteWithName(String nameProduct) {
        tapElement(constructLocator("product_favorite_name", nameProduct));
    }

    public void clickProductOnSebarPromosiList(int index) {
        tapElements("product_favorited_item_list", index, 10);
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
