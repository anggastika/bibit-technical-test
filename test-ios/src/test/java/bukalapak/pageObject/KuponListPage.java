package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.KuponData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 28/05/20, Thu
 **/
public class KuponListPage extends VpBasePage {

    public KuponListPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        verifyElementExist("KUPON_LIST_SEARCH_BAR");
        verifyElementExist("KUPON_LIST_ITEM");
        verifyElementExist("KUPON_LIST_FILTER_ALL_BUTTON");
        verifyElementExist("KUPON_LIST_SORT_DEFAULT_BUTTON");
        verifyElementExist("KUPON_LIST_ITEM_NAME");
    }

    public void validateCouponWithMerchant() {
        int countList = getElementsPresent("KUPON_LIST_ITEM").size() - 1;

        while (countList >= 0) {
            validateValue().equals(KuponData.getMerchant(),
                    getTextFromElement(constructLocator("KUPON_LIST_ITEM_MERCHANT_TEXT",
                            KuponData.getMerchant()), countList));
            countList -= 1;
        }
        HelperData.setLastActionPage(new KuponListPage(iosDriver));
    }

    public void setCouponName() {
        KuponData.setCouponName(getTextFromElement("KUPON_LIST_ITEM_NAME", 0));
    }

    public void tapOnFirstCoupon() {
        tapElements("KUPON_LIST_ITEM", 0);
    }

    public void searchCoupon(String keyword) {
        typeAndEnterValue("KUPON_LIST_SEARCH_BAR", keyword);
    }

    public void validateSearchedCoupon(String keyword) {
        verifyElementExist(constructLocator("KUPON_LIST_ITEM_MERCHANT_TEXT", keyword));
        HelperData.setLastActionPage(new KuponListPage(iosDriver));
    }

    public void validateCouponByCategory(String category) {
        verifyElementExist(constructLocator("KUPON_LIST_CATEGORY_TEXT", category));
        HelperData.setLastActionPage(new KuponListPage(iosDriver));
    }

    public void tapOnFilterCategory() {
        waitForVisibilityOf("KUPON_LIST_CATEGORY_OPTION_SEMUA_TEXT");
        tapElement("KUPON_LIST_CATEGORY_OPTION_SEMUA_TEXT");
    }

    public void validateCategoryModalDisplayed() {
        verifyElementDisplayed("KUPON_LIST_KATEGORI_NAME");
        verifyElementDisplayed("KUPON_LIST_FILTER_RESULT");
        HelperData.setLastActionPage(new KuponListPage(iosDriver));
    }

    public void chooseCategory(String category) {
        swipeToLocator(constructLocator("KUPON_LIST_CATEGORY_OPTION_TEXT", category));
        tapElement(constructLocator("KUPON_LIST_CATEGORY_OPTION_TEXT", category));
    }

    public void tapOnFilterAllButton() {
        tapElement("KUPON_LIST_FILTER_ALL_BUTTON");
    }

    public void validateFilterModalDisplayed() {
        waitForVisibilityOf("KUPON_LIST_FILTER_MODAL_APPLY_FILTER_BUTTON");
        verifyElementDisplayed("KUPON_LIST_FILTER_MODAL_APPLY_FILTER_BUTTON");
        verifyElementDisplayed("KUPON_LIST_FILTER_MODAL_MERCHANT_SECTION");
    }

    public void tapOnApplyFilter() {
        tapElement("KUPON_LIST_FILTER_MODAL_APPLY_FILTER_BUTTON");
    }

    public void tapOnMerchantFilter() {
        tapElement("KUPON_LIST_FILTER_MODAL_MERCHANT_SECTION");
    }

    public void validateMerchantListModalDisplayed() {
        verifyElementDisplayed("KUPON_LIST_MERCHANT_LIST_MODAL_SEARCH_FIELD");
        verifyElementDisplayed("KUPON_LIST_MERCHANT_LIST_MODAL_CHECKBOX");
        verifyElementDisplayed("KUPON_LIST_MERCHANT_LIST_MODAL_ITEM_TEXT");
        verifyElementDisplayed("KUPON_LIST_MERCHANT_LIST_MODAL_SAVE_BUTTON");
    }

    public void typeOnSearchMerchant(String merchant) {
        typeAndEnterValue("KUPON_LIST_MERCHANT_LIST_MODAL_SEARCH_FIELD", merchant);
        try {
            hideKeyboard();
        } catch (Exception e) {
            LogUtil.info("Keyboard is not shown");
        }
    }

    public void validateSearchedMerchant(String merchant) {
        validateElementWithText("KUPON_LIST_MERCHANT_LIST_MODAL_ITEM_TEXT", merchant);
        KuponData.setMerchant(getTextFromElement("KUPON_LIST_MERCHANT_LIST_MODAL_ITEM_TEXT"));
    }

    public void checkSelectedMerchant() {
        tapElements("KUPON_LIST_MERCHANT_LIST_MODAL_CHECKBOX", 0);
    }

    public void tapOnSaveButton() {
        tapElement("KUPON_LIST_MERCHANT_LIST_MODAL_SAVE_BUTTON");
    }

    public void typeOnPriceRange(String minPrice, String maxPrice) {
        KuponData.setMinPrice(Integer.parseInt(minPrice));
        KuponData.setMaxPrice(Integer.parseInt(maxPrice));
        typeAndEnterValue("KUPON_LIST_FILTER_MODAL_INPUT_MINIMUM_PRICE_TEXT_FIELD", minPrice);
        typeAndEnterValue("KUPON_LIST_FILTER_MODAL_INPUT_MAXIMUM_PRICE_TEXT_FIELD", maxPrice);
    }

    public void validateOnPriceRange() {
        int countList = getElementsPresent("KUPON_LIST_ITEM_PRICE_TEXT").size() - 1;
        int index = 0;

        while(index <= countList) {
            swipeToLocator("KUPON_LIST_ITEM_PRICE_TEXT");
            int couponPrice = parseIntegerFromText(getTextFromElement("KUPON_LIST_ITEM_PRICE_TEXT", index));
            validateValue().equalsTrue(couponPrice >= KuponData.getMinPrice() && couponPrice <= KuponData.getMaxPrice());
            index++;
        }
        HelperData.setLastActionPage(new KuponListPage(iosDriver));
    }

    public void tapOnSortButton() {
        tapElement("KUPON_LIST_SORT_DEFAULT_BUTTON");
    }

    public void validateSortModalDisplayed() {
        validateExist("KUPON_MODAL_SORTING_TEXT");
    }

    public void tapOnSortBestSellerOption() {
        tapElement("KUPON_LIST_SORT_OPTION_TERLARIS_TEXT");
    }

    public void validateCouponBestSeller() {
        int firstQty = parseIntegerFromText(getTextFromElement("KUPON_LIST_ITEM_SOLD_QTY_TEXT", 0));
        int secondQty = parseIntegerFromText(getTextFromElement("KUPON_LIST_ITEM_SOLD_QTY_TEXT", 1));

        validateValue().equalsTrue(firstQty >= secondQty);
        HelperData.setLastActionPage(new KuponListPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
