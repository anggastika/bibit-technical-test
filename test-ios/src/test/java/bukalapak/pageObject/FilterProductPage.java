package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.SearchData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.List;
import java.util.Arrays;

public class FilterProductPage extends BasePage {

    public FilterProductPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void closePage() {
        tapBackButton();

        // if still in filter page because of from deeper section
        if(isElementVisible("filter_page_navbar", 3)) {
            tapBackButton();
        }
    }

    public void tapBackButton() {
        waitForElementClickable("filter_page_back_btn", 3);
        tapElement("filter_page_back_btn");
    }
    
    public void selectFilterProduct(String filterName) {
        switch (filterName) {
            case "BukaMall":
                waitFor(2); // wait for radio to be accessable
                tapElement("bukamall_switch");
            break;
            case "warna handphone":
                selectFilterWarnaHandphone();
            break;
            default:
                // set rating
                if(filterName.contains("Rating ")) {
                    String rating = filterName.replaceAll("\\D","");
                    SearchData.setSelectedFilterRating(rating);
                    Integer index = (5 % Integer.parseInt(rating));
                    tapElements("rating_checkbox", index, 10);
                    return;
                }

                tapElement(constructLocator("filterpage_filter_name", filterName));
            break;
        }
    }

    public void selectCategory(String category) {
        String locator = constructLocator("category_filter_label", category);

        if(isElementExist(constructLocator("category_filter_lvl_radio", category))) {
            locator = constructLocator("category_filter_lvl_radio", category);
        }

        swipeUpToElement(locator);
        tapElement(locator);
    }

    public void tapSimpanButton() {
        Boolean isCategoryPage = isElementExist("category_filter_page_title");
        String locator = isCategoryPage ? "category_filter_page_btn" : "simpan_button";

        verifyElementExist(locator);
        tapElement(locator);
    }

    public void verifyFilterSelected(String filterName, Boolean isActive){
        String locator = constructLocator("filterpage_filter_name", filterName);
        String errorMessage = "Filter: " + filterName + " is " + (isActive ? "not " : "") + "active";

        switch(filterName) {
            case "Diskon": case "Cicilan": case "Grosiran": case "Bayar di Tempat (COD)":
                locator = constructLocator("filter_checkbox", filterName);
                swipeUpToElement(locator);
                assertTrue(
                    getElementValue(locator).equals(isActive ? "checked" : "unchecked"),
                    errorMessage
                );
            break;
            case "BukaMall":
                // wait for animation to complete.
                // if not using wait, element can be retrieved but the value somehow cannot.
                waitFor(2);
                assertTrue(getElementValue("bukamall_switch").equals(isActive ? "1" : "0"));
            break;
            case "Category":
                List<IOSElement> categoryTags = getElements("category_filter_tag");
                String[] categoriesList = SearchData.getCategories();

                for(IOSElement tag : categoryTags) {
                    assertTrue(Arrays.asList(categoriesList).contains(tag.getAttribute("value")));
                }
            break;
            default:
                swipeUpToElement(locator, 4);
                Boolean isExist = isElementExist(locator);
                assertTrue(isActive ? isExist : !isExist, errorMessage);
            break;
        }

        HelperData.setLastActionPage(new FilterProductPage(iosDriver));
    }

    public void selectFilterGroup(String groupName){
        Boolean isCategory = isElementExist("category_filter_page_title");
        String locator = isCategory ? "category_filter_page_radio" : "filter_group";
        String element = constructLocator(locator, groupName);

        swipeUpToElement(element);
        tapElement(element);
    }

    public void tapTerapkanFilter() {
        tapElement("button_terapkan_filter");
    }

    public void selectFilterWarnaHandphone(){
        swipeDownToElement("warna_handphone");
        tapElement("warna_handphone");
        swipeDownToElement("warna_hitam");
        tapElement("warna_hitam");
        tapElement("button_simpan");
    }

    public void setPriceRange(String range, String price) {
        int index = range.equals("min") ? 1 : 2;
        String locator = constructLocator("price_range_filter", index);

        waitForVisibilityOf(locator, 3);
        typeAndEnterValue(locator, price);
    }

    public void isInFilterPage() {
        validateDisplayed("filter_page_navbar");
        HelperData.setLastActionPage(new FilterProductPage(iosDriver));
    }

    public void resetFilter() {
        tapElement("filter_reset_btn");
    }

    public void tapDikirimDariDropDown(String dropdown, String option) {
        int index = dropdown.equals("Kota") ? 1 : 0;

        tapElements("third_party_dropdown", index);
        tapElement(constructLocator("third_party_dropdown_options", option));

        // wait for loading screen to disappear,
        // because there is no unique identifier to wait for
        waitFor(1);
    }

    public void chooseCountry(String country) {
        tapElement("third_party_dropdown");
        tapElement(constructLocator("third_party_dropdown_options", country));

        // wait for loading screen to disappear,
        // because there is no unique identifier to wait for
        waitFor(1);
    }

    public void searchAndTapCategory(String category) {
        tapElement("category_filter_page_search_icon");
        waitFor(2); // wait for animation to finish
        typeValue("category_filter_page_search_field", category);
        waitFor(2);
        tapElement(constructLocator("filter_group", category));
        waitFor(3); // wait for redirection
    }

    public void validateAvailabilityCourier(String courier, String availability) {
        swipeUpToElement(constructLocator("filter_group", courier), 6);
        if (availability.equals("available")) {
            validateDisplayed(constructLocator("filter_group", courier));
        } else {
            validateNotDisplayed(constructLocator("filter_group", courier));
        }
        HelperData.setLastActionPage(new FilterProductPage(iosDriver));
    }
}
