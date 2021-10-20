package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assume;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FlashDealProductListPage extends BasePage {

    public FlashDealProductListPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnProductListFlashDeal() {
        waitForVisibilityOf("flash_deal_title", 30);
        validateDisplayed("flash_deal_title");
        validateDisplayed("flash_deal_share_button");
        validateDisplayed("flash_deal_product_card_list");
        HelperData.setLastActionPage(new FlashDealProductListPage(iosDriver));
    }

    public void goToFlashDealWithDeeplink() {
        openDeepLink("https://www.bukalapak.com/flash-deal");
        HelperData.setLastActionPage(new FlashDealProductListPage(iosDriver));
    }

    public void validateBannerFlashDeal() {
        validateDisplayed("flash_deal_banner");
    }

    public void validateFlashDealTimer() {
        validateDisplayed("flash_deal_timer");
    }

    public void validateNumberOfTabs(int tabNumber) {
        assertEquals(getElementsPresent("flash_deal_tabs").size(), tabNumber);
    }

    public void tapOnUpcomingFlashDealTab() {
        tapElements("flash_deal_tabs", 1);
    }

    public void validateTabActive(String tabName) {
        validateEnabled(constructLocator("flash_deal_tab", tabName));
    }

    public void tapOnShareFlashDealButton() {
        tapElement("flash_deal_share_button");
    }

    public void validateShareFlashDealModal() {
        validateDisplayed("flash_deal_share_modal");
        validateExist("flash_deal_title");
        validateExist("flash_deal_share_button");
        HelperData.setLastActionPage(new FlashDealProductListPage(iosDriver));
    }

    public void buyPulsaFlashDeal() {
        swipeUpToElement("flash_deal_pulsa_prepaid");
        tapElement("flash_deal_pulsa_prepaid");
    }

    public void tapOnCurrentFlashDealTab() {
        verifyElementExist("PRODUCT_LIST_FLASHDEAL_BANNER");
        tapElement("PRODUCT_LIST_TAB_CURRENT");
    }

    public void tapOnFlashDealProduct(int index) {
        waitForVisibilityOf("PRODUCT_LIST_FLASH_DEAL_NAME", 30);
        tapElement("PRODUCT_LIST_FLASH_DEAL_NAME", index);
    }

    public void tapOnFlashDealProduct(String status) {
        tapElements("flash_deal_product_" + status, 0, 5);
    }

    public void swipeToOutOfStockProduct() {
        swipeUpToElement("flash_deal_product_unavailable", 35);
        Assume.assumeTrue("No out of stock flash deal product", isElementVisible("flash_deal_product_unavailable", 1));
    }

    public void validateOutOfStockLabel() {
        validateDisplayed("flash_deal_product_unavailable_overlay");
    }

    public void validateUpcomingPrice() {
        validateDisplayed("flash_deal_product_price_masked");
        validateDisplayed("flash_deal_product_price_base");
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapProductRatingFourPlus() {
        swipeUpToElement("PRODUCT_LIST_RATING_FOUR");
        tapElement("PRODUCT_LIST_RATING_FOUR");
    }

    public void tapFDBackButton() {
        tapElement("FLASH_DEAL_BACK_BUTTON");
    }

    public void selectFlashDealTab(String state) {
        validateExist("PRODUCT_LIST_FLASHDEAL_BANNER", 10);
        if(state.toLowerCase().equals("ongoing"))
            tapElement("PRODUCT_LIST_TAB_ONGOING");
        else
            tapElement("PRODUCT_LIST_TAB_UPCOMING");
    }

    public void validateTimer(String state) {
        validateExist("product_list_flashdeal_countdown_" + state, 10);
        validateExist("PRODUCT_LIST_FLASHDEAL_TIMER", 5);
    }

    public void validatePrice(String priceState) {
        String price = getText("PRODUCT_LIST_FLASH_DEAL_PRICE_ONGOING", 1);

        LogUtil.info("Displayed price in flash deal product listing is : " + price);
        if (priceState.equals("masked")) {
            validateValue().contains("?", price);
        } else {
            validateValue().equalsFalse(price.contains("?"));
        }
    }

    public void validateSortButtonNotExist() {
        validateNotExist("flash_deal_sort_button", 5);
    }

    /**
     * Flash deal product list sorting.
     * @param sortOption is full string of the sort pop up option items (e.q. "Harga Termurah")
     */
    public void sortFlashDealProduct(String sortOption) {
        tapCenterOfElement("flash_deal_sort_button", 10);

        String locator = constructLocator("flash_deal_sort_option", sortOption);
        validateExist(locator, 5);
        tapElement(locator);
        validateExist("flash_deal_sort_button", 5);
    }

    public void validateFlashDealProductSorted(String sortOption) {
        validateExist("flash_deal_product_price", 10);
        switch (sortOption) {
            case "Harga Termurah":
                validateMultipleElementsValueSorted("flash_deal_product_price", "ascendant", "Product prices are not sorted by termurah");
                break;
            case "Diskon Terbesar":
                validateMultipleElementsValueSorted("flash_deal_product_discount", "descendant", "Product discounts are not sorted by the biggest discount");
                break;
            default:
                break;
        }
    }

    /***
     * Method to validate sorted integer value of an array of elements with specified locator
     * @param locator : locator of the integer value to be validated
     * @param mode : sort mode (e.q. ascendant or descendant)
     * @param error : error message if values are not sorted
     */
    public void validateMultipleElementsValueSorted(String locator, String mode, String error) {
        List<IOSElement> elements = getElementsPresent(locator);
        int previousValue = Integer.parseInt(elements.get(0).getText().replaceAll("[^-?0-9]+", ""));
        int currentValue;
        for (int i=1; i< elements.size(); i++) {
            currentValue = Integer.parseInt(elements.get(i).getText().replaceAll("[^-?0-9]+", ""));
            if (mode.equalsIgnoreCase("ascendant")) {
                validateValue().equalsTrue(previousValue <= currentValue, error);
            } else {
                validateValue().equalsTrue(previousValue >= currentValue, error);
            }
            previousValue = currentValue;
        }
    }

    public void validateRecomendationFilterNotExist() {
        validateNotExist("flash_deal_buat_kamu_filter", 10);
    }

    public void validateRecomendationFilterExist() {
        validateExist("flash_deal_buat_kamu_filter", 10);
        HelperData.setLastActionPage(new FlashDealProductListPage(iosDriver));
    }

    public void tapRecomendationFilter() {
        tapElement("flash_deal_buat_kamu_filter");
    }
}
