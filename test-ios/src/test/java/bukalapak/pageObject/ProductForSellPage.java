package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class ProductForSellPage extends BasePage {

    public ProductForSellPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPage() {
        verifyElementExist("product_for_sell_title");
        HelperData.setLastActionPage(new ProductForSellPage(iosDriver));
    }

    public void userOnPageWithDeeplink(String deeplinkEnvVar) throws Exception {
        String deeplink = dotenv.get(deeplinkEnvVar);
        if (deeplink == null) {
            throw new Exception(deeplinkEnvVar + " is not set!");
        }

        openDeepLink(deeplink);
        userOnPage();
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void searchProduct(String productName) {
        typeAndEnterValue("product_for_sell_search_field", productName);
        waitForVisibilityOf(constructLocator("product_for_sell_all_product_text", 1));
    }

    public void openProductMenu() {
        tapElement("product_for_sell_product_menu_button");
        verifyElementDisplayed("product_for_sell_product_menu_title");
    }

    public void setProductNotForSell() {
        tapElement("product_for_sell_set_not_for_sell_button");
    }

    public void tapOnDeleteProductButton() {
        tapElement("product_for_sell_delete_product_button");
    }

    public void verifyStock(int stock) {
        verifyElementExist(constructLocator("product_for_sell_stock_text", stock));
        HelperData.setLastActionPage(new ProductForSellPage(iosDriver));
    }

    public void validateDiscount(String discountbadge) {
        validateValue().equals(discountbadge, getText("discount_tag_label"));
    }

     public void tapOnSellProductLainnyaButton() {
        tapElement("product_for_sell_product_lainnya");
    }
}
