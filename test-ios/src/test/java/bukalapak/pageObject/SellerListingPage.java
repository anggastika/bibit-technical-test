package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.SearchData;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SellerListingPage extends BasePage {

    public SellerListingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyInSellerListingPage() {
        waitForVisibilityOf(constructLocator("seller_listing_card", 1), 5);

        String searchKeywords = SearchData.getSearchKeywords();
        assertEquals(
            getElementAttributeValue("search_field_result", "value").toLowerCase(),
            searchKeywords.toLowerCase(),
            "The keywords don't match"
        );

        HelperData.setLastActionPage(new SellerListingPage(iosDriver));
    }

    public void tapFilterBtn(String type) {
        nativeSwipeDown();
        tapElement(type.equals("filter") ? "seller_listing_filter_btn" : "seller_listing_sort_btn");
    }

    public void selectFilter(String filter) {
        waitFor(2);
        validateDisplayed(
            constructLocator("seller_listing_filter_checkbox", filter),
            "\"" + filter + "\" filter is not displayed."
        );
        tapElement(constructLocator("seller_listing_filter_checkbox", filter));
    }

    public void verifyCardComponent(Integer index, String value, String type) {
        String locator = constructLocator(
            type.equals("label") ? "seller_listing_card_label" : "seller_listing_card_badge",
            index, value
        );

        swipeUpToElement(constructLocator("seller_listing_card", index));
        validateDisplayed(
            locator,
            "Seller Card Position " + index + " doesn't have " + type + " " + value + "!"
        );

        HelperData.setLastActionPage(new SellerListingPage(iosDriver));
    }

    public void verifyFilterActive(String filter) {
        String groupName = "Pelapak"; // default value

        switch(filter) {
            case "Lokasi Pelapak":
                groupName = "Lokasi Pelapak";
            break;
            default: // Pelapak -> Super Seller
                waitFor(2);
                String locator = constructLocator("seller_listing_filter_checkbox", filter);
                String value = getElementAttributeValue(locator, "value");
                assertTrue(value.equals("checked"), "Filter " + filter + " is not active!");
            break;
        }

        assertTrue(
            isElementExist(
                constructLocator("seller_listing_filter_checkmark", groupName)
            ),
            "Checkmark icon is not exists on " + groupName + " filter group!"
        );

        HelperData.setLastActionPage(new SellerListingPage(iosDriver));
    }

    public void sortBy(String sortBy) {
        tapElement(constructLocator("seller_listing_sort_option", sortBy));
    }

    public void verifySortActive(String sortBy) {
        String locator = constructLocator("seller_listing_sort_active", sortBy);

        waitForVisibilityOf(locator, 5);
        tapElement(locator);
        waitFor(2); // wait for animation to finish

        String value = getText("seller_listing_sort_option_active");

        assertTrue(
            value.equals(sortBy),
            sortBy + " is not active. current active value is " + value
        );

        HelperData.setLastActionPage(new SellerListingPage(iosDriver));
    }

    public void tapCardButton(String element, Integer position) {
        tapElement(
            constructLocator("seller_listing_card_button", position, element)
        );
    }

    public void tapSellerCard(Integer position) {
        tapElement(constructLocator("seller_listing_card", position));
    }
}
