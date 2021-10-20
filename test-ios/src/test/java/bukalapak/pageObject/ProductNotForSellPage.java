package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class ProductNotForSellPage extends BasePage {

    public ProductNotForSellPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPage() {
        verifyElementExist("product_not_for_sell_title");
        HelperData.setLastActionPage(new ProductNotForSellPage(iosDriver));
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
        typeAndEnterValue("product_not_for_sell_search_field", productName);
        waitForVisibilityOf(constructLocator("product_not_for_sell_all_product_text", 1));
    }

    public void openProductMenu() {
        tapElement("product_not_for_sell_product_menu_button");
        verifyElementDisplayed("product_not_for_sell_product_menu_title");
    }

    public void setProductForSell() {
        tapElement("product_not_for_sell_set_for_sell_button");
    }

    public void verifyStock(int stock) {
        verifyElementExist(constructLocator("product_not_for_sell_stock_text", stock));
        HelperData.setLastActionPage(new ProductNotForSellPage(iosDriver));
    }

    public void tapOnBulkProductCheckboxes() {
        waitForVisibilityOf("product_for_sell_product_checkboxes");
        tapElement("product_for_sell_product_checkboxes");
        tapElement("product_for_sell_product_checkboxes1");
        tapElement("product_for_sell_product_checkboxes2");
    }
}
