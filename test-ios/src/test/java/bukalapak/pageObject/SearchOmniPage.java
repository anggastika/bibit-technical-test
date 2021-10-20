package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.SearchData;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

import org.junit.Assert;

public class SearchOmniPage extends BasePage {

    public SearchOmniPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickOnOmniSearchField() {
        String element = "omni_search_field";

        if(!isElementVisible("omni_search_field", 5)) {
            element = "omni_search_field_product_listing";
        }

        waitForElementClickable(element, 10);
        tapElement(element);

        // ignoring onboarding search by cam
        if (isElementVisible("omni_search_onboarding", 5)) {
            tapElement("omni_search_by_cam_onb_nanti_deh_text");
        }
    }

    private void validateOnBoardingQuickFilter() {
        if (isElementVisible("onboarding_quick_filter_section", 10)) {
            tapElement("onboarding_ok_revamp_button");
        }
    }

    private void validateOnBoardingCatalog() {
        if (isElementVisible("onboarding_catalog_section", 5)) {
            tapElement("onboarding_ok_button");
        }
    }

    private void inputSearchField(String keyword) {
        typeAndEnterValue("omni_search_revamp_field", keyword);
        validateOnBoardingQuickFilter();
        validateOnBoardingCatalog();
        SearchData.setSearchKeywords(keyword);
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void searchProduct(String keywordType) {
        String tmpKeywordType = getKeywords(keywordType);
        SearchData.setSearchKeywords(tmpKeywordType);
        clickOnOmniSearchField();
        inputSearchField(tmpKeywordType);
    }

    private String getKeywords(String keywords) {
        String envKeyword = dotenv.get(keywords.toUpperCase());

        return envKeyword == null ? keywords : envKeyword;
    }

    public void selectKeywordFromOmniPage(String keywordType) {
        waitFor(5);
        switch (keywordType) {
            case "riwayat pencarian":
                waitForVisibilityOf("riwayat_pencarian_keyword", 20);
                SearchData.setSearchKeywords(getElementValue("riwayat_pencarian_keyword"));
                tapElement("riwayat_pencarian_keyword");
                break;
            case "pencarian populer":
                waitForVisibilityOf("pencarian_populer_keyword", 20);
                SearchData.setSearchKeywords(getElementValue("pencarian_populer_keyword_text"));
                tapElement("pencarian_populer_keyword");
                break;
            case "kategori":
                waitForVisibilityOf("kategori_keyword", 20);
                SearchData.setSearchKeywords(getElementValue("kategori_keyword_text"));
                tapElement("kategori_keyword");
                break;
            case "kata kunci":
                swipeToDirection(Direction.UP);
                SearchData.setSearchKeywords(getElementValue("kata_kunci_keyword_text"));
                tapElement("kata_kunci_keyword");
                break;
            default:
                break;
        }
        okOnboarding();
    }

    public void typeProductName(String searchKeywords) {
        clickOnOmniSearchField();
        String tmpSearchKeywords = getKeywords(searchKeywords);
        typeAndEnterValue("omni_search_revamp_field", tmpSearchKeywords);
    }

    public void clickLihatSelengkapnya(){
        swipeToDirection(Direction.UP);
        tapElement("omni_history_lihat_selengkapnya");
    }

    // this script just for type keyword for display "Lihat Semua" button
    public void typeProductNameWithoutEnter(String searchKeyword) {
        typeAndEnterValue("omni_search_revamp_field", dotenv.get(searchKeyword));
    }

    public void clickLihatSemua() {
        waitForVisibilityOf("omni_search_lihat_semua_text", 3);
        tapElement("omni_search_lihat_semua_text");
    }

    public void verifyOnProductListingPage(){
        waitFor(10);
        okOnboarding();
        assertFalse(isElementVisible("omni_search_not_exist"), "Product is Exist");
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void typeKeyword(String input) {
        String keyword = dotenv.get(input) == null ? input : dotenv.get(input);

        typeValue(isElementExist("omni_search_revamp_field") ? "omni_search_revamp_field" : "omni_search_field_product_listing", keyword);
        SearchData.setSearchKeywords(keyword);
        waitFor(2); // wait for suggestion to fully loaded
    }

    public void reTypeKeyword() {
        String keyword = getElementValue("omni_brand_suggestion");
        tapElement("search_result_omni_clear_btn");
        waitFor(2); // wait for content to reload
        typeKeyword(keyword);
    }

    public void verifySuggestion(String product) {
        switch(product) {
            case "BukaMall":
                if(!isElementExist("omni_brand_suggestion")) {
                    reTypeKeyword();
                    return;
                }

                String brandName = getElementValue("omni_brand_suggestion");
                String keyword = SearchData.getSearchKeywords();
                assertTextContains(keyword.toLowerCase(), brandName.toLowerCase());
                SearchData.setSearchBrandName(brandName);
            break;
            case "Virtual Product":
                if(!isElementExist("omni_dope_suggestion")) {
                    reTypeKeyword();
                    return;
                }

                verifyElementExist("omni_dope_suggestion");
            break;
            case "Category":
                String category = getElementValue("kategori_keyword_breadcrumb");
                category = category.replace("di Kategori ", "");
                SearchData.setCategory(category);
            break;
            default:
                Assert.fail("Invalid product: " + product);
        }

        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void tapDopeSuggestion() {
        tapElement("omni_dope_suggestion");
    }

    public void tapItemWithCategorySuggestion() {
        SearchData.setSearchKeywords(
            getElementValue("kategori_keyword_text")
        );
        tapElement("kategori_keyword");
    }

    public void tapPopularSuggestion(String type, Integer index) {
        String property = "";

        /*
           trigger keyboard to close
           because omni is a search field, using hideKeyboard() will trigger click on 'Search' on keyboard -
           and redirecting page to search listing and causing failure
        */
        if(isElementExist("omni_history_remove_all")) {
            tapElement("omni_history_remove_all");
            tapElement("omni_history_kembali_btn");
        }

        switch(type) {
            case "Kategori":
                property = "omni_kategori_populer_buttons";
                swipeToDirection(Direction.UP);
            break;
            case "Pencarian":
                property = "omni_pencarian_populer_text";
                waitForVisibilityOf(constructLocator(property, index), 5);
            break;
            default:
                Assert.fail("Invalid search type: " + type);
        }

        SearchData.setSearchKeywords(
            getElementValue(
                constructLocator(property, index)
            )
        );
        tapElement(constructLocator(property, index));
    }

    public void verifyCatalogSuggestions() {
        waitForVisibilityOf("omni_search_catalog_suggestions_section", 5);
    }

    public void tapCatalogSuggestion(Integer index) {
        tapElement(
            constructLocator("omni_search_catalog_suggestions_item", index)
        );
    }

    public void verifyHistory() {
        waitForVisibilityOf("omni_history_title", 5);
        validateDisplayed("omni_history_keywords");
    }

    public void verifyHistoryExists(String keyword) {
        validateDisplayed(constructLocator("omni_history_by_keyword", keyword));
    }

    public void tapHistory(Boolean hold, Integer index) {
        String keyword = getTextFromElement(constructLocator("omni_history_keyword", index));

        if(hold) {
            SearchData.setRemovedHistory(keyword);
            tapAndHoldElement(constructLocator("omni_history_keyword", index));
            return;
        }

        SearchData.setSearchKeywords(keyword);
        tapElement(constructLocator("omni_history_keyword", index));
    }

    public void tapRemoveAllHistory() {
        tapElement("omni_history_remove_all");
    }

    public void verifyModalRemoveHistory(Boolean removeAll) {
        String element = removeAll ? "omni_history_remove_all_message" : "omni_history_remove_message";
        
        waitForVisibilityOf(element, 3);

        if(!removeAll) {
            String targetedKeyword = SearchData.getRemovedHistory();
            String currentKeyword  = getTextFromElement("omni_history_remove_keyword");

            assertEquals(
                targetedKeyword, currentKeyword,
                "Target : " + targetedKeyword + " is not equal " + currentKeyword
            );
        }
    }

    public void removeHistory() {
        tapElement("omni_history_remove_btn");
    }

    public void verifyHistoryRemoved() {
        String removedKeyword = SearchData.getRemovedHistory();

        validateNotDisplayed(
            constructLocator("omni_history_by_keyword", removedKeyword)
        );
    }

    public void verifyNoHistory() {
        validateNotDisplayed("omni_history_title");
        validateNotDisplayed("omni_history_remove_all");
        validateNotDisplayed("omni_history_keywords");
    }

    public void closeAndReopenOmni() {
        tapElement("omni_sreach_back_btn");

        if(isElementExist("search_result_omni_clear_btn", 3)) {
            tapElement("search_result_omni_clear_btn");
            return;
        }

        clickOnOmniSearchField();
    }

    // double checking before and after deletion
    public void validateHistory(Boolean isRemoveAll) {
        if(isRemoveAll) {
            verifyNoHistory();
            closeAndReopenOmni();
            verifyNoHistory();
        }
        else {
            verifyHistoryRemoved();
            closeAndReopenOmni();
            verifyHistoryRemoved();
        }

        HelperData.setLastActionPage(new SearchOmniPage(iosDriver));
    }

    public void tapViewAllSeller() {
        nativeSwipeUp();
        waitForVisibilityOf("omni_search_lihat_semua_text", 3);
        tapElement("omni_search_lihat_semua_text");
    }

    public void tapCampaignSuggestion() {
        waitForVisibilityOf("omni_campaign_suggestion", 5);
        tapElement("omni_campaign_suggestion");
    }

    public void tapKeywordContinuationAndSearch(Integer position) {
        tapElement(constructLocator("omni_keyword_continuation_icon", position));

        String element = isElementExist("omni_search_revamp_field") ? "omni_search_revamp_field" : "omni_search_field_product_listing";
        SearchData.setSearchKeywords(getElementAttributeValue(element, "value"));
        getElement(element).sendKeys("\n");
    }

    public void verifyNotInOmnipage() {
        validateNotDisplayed("omni_kategori_populer_buttons");
    }
}
