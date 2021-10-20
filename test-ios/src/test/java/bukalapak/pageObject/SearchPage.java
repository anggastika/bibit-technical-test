package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.MerchantData;
import bukalapak.data.ProductDetailData;
import bukalapak.data.SearchData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import static bukalapak.TestInstrument.dotenv;


public class SearchPage extends BasePage {

    public SearchPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnSearchPage() {
        verifyElementExist("search_barang_tab");
        verifyElementExist("search_pelapak_tab");
        HelperData.setLastActionPage(new SearchPage(iosDriver));
    }

    public void goToPelapakTab() {
        tapElement("search_pelapak_tab");
        HelperData.setLastActionPage(new SearchPage(iosDriver));
    }

    public void addToCart() {
        swipeToLocator("seller_page_beli_1st_item_button");
        tapElement("seller_page_beli_1st_item_button");
        tapElement("popup_cart_page_close_button");
        tapElement("base_back_button");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickPelapak(String name) {
        String tmpName = name.toUpperCase();
        try {
            String elementName = "name_" + dotenv.get(tmpName + "_SURENAME");
            tapElement(elementName);
            waitFor(3);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error("Type of " + tmpName + " is not found!");
        }
    }

    public void searchProduct(String arg0) {
        HelperData.setLastActionPage(new SearchPage(iosDriver));
        tapElement("home_search_product_button", 5);
        if (isElementVisible("onboarding_nanti_deh_button")) {
            tapElement("onboarding_nanti_deh_button");
        }
        typeAndEnterValue("home_search_product_search_field", arg0);
        if (isElementVisible("search_oke_button", 10)) {
            tapElement("search_oke_button");
        }
        if(isElementVisible("search_lewati_button",30)){
            tapElement("search_lewati_button");
        }
        if (!isElementVisible("search_beli_button") &&
                (isElementVisible("search_lewati_button"))) {
            tapElement("search_lewati_button");
            typeAndEnterValue("home_search_product_text_field", arg0);
        }
        SearchData.setSearchKeywords(arg0);
    }

    public void checkSellerOnSellerListing() {
        assertTrue(!isElementVisible("search_pelapak_not_exist"), "Product is Exist");
        verifyElementExist("listing_seller_elements");
    }

    public void tapSellerTab() {
        okOnboarding();
        tapElement("listing_seller_tab");
    }

    public void tapFilterButton() {
        okOnboarding();
        waitForVisibilityOf("listing_filter_text", 5);
        tapElement("listing_filter_text");
    }

    public void tapTopSellerFilterButton() {
        tapElement("listing_filter_top_seller");
    }

    public void tapPremiumSellerFilterButton() {
        tapElement("listing_filter_premium_seller");
    }

    public void tapTerapkanFilterButton() {
        tapElement("listing_terapkan_filter_button");
    }

    public void checkPelapakName() {
        String pelapakName = ProductDetailData.getPelapakName();
        assertTrue(isElementVisible(constructLocator("search_pelapak_name", pelapakName)), "Klik Pelapak dari Produk Detail Berhasil");
    }

    public void verifyBadgeSuperSellerOnSellerResult(String index) {
        verifyElementExist(constructLocator("listing_seller_super_seller_badge_image", index));
        HelperData.setLastActionPage(new SearchPage(iosDriver));
    }

    public void verifyBadgeLapakTerbaikOnSellerResult(String index) {
        verifyElementExist(constructLocator("listing_seller_lapak_terbaik_badge_image", index));
        HelperData.setLastActionPage(new SearchPage(iosDriver));
    }

    public void verifyBadgePremiumOnSellerResult(String index) {
        verifyElementExist(constructLocator("listing_seller_premium_badge_image", index));
    }

    public void tapPelapakFilterButton() {
        swipeUpToElement("listing_filter_pelapak_text");
        tapElement("listing_filter_pelapak_text");
    }

    public void tapListViewButton() {
        tapElement("listing_product_list_view_text");
    }

    public void verifyBadgeSuperSellerOnProductResult(int index, String viewType) {
        waitForVisibilityOf(constructLocator("listing_product_" + viewType + "_super_seller_badge_image", Integer.toString(index)), 10);
        HelperData.setLastActionPage(new SearchPage(iosDriver));
    }

    public void verifyBadgeLapakTerbaikOnProductResult(int index, String viewType) {
        verifyElementExist(constructLocator("listing_product_" + viewType + "_lapak_terbaik_badge_image", Integer.toString(index + 1)));
    }

    public void tapSimpanButton() {
        tapElement("listing_product_simpan_button");
    }

    public void tapTerapkanButton() {
        tapElement("listing_terapkan_button");
    }

    public void skipOnboardingSearchResult() {
        okOnboarding();
        if(isElementExist("product_listing_harta_karun_btn")) {
            tapElement("product_listing_harta_karun_btn");
        }
    }

    public void checkDisplayedProduct(String productName) {
        skipOnboardingSearchResult();
        waitForVisibilityOf(constructLocator("search_product_title", productName), 30);
        verifyElementDisplayed(constructLocator("search_product_title", productName));
    }

    public void clickOnProduct(String productName) {
        tapElement("labelcontains_" + productName);
        HelperData.setLastActionPage(new SearchPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void closeOnboardingSearch() {
        if (isElementVisible("onboarding_nanti_deh_button")) {
            tapElement("onboarding_nanti_deh_button");
        }
    }

    public void searchPelapak(String sellerName) {
        MobileElement searchField;

        tapElement("home_search_product_xpath");
        closeOnboardingSearch();
        searchField = getElement("omni_search_revamp_field");
        waitForVisibilityOf("omni_search_revamp_field");
        tapElement("omni_search_revamp_field");
        searchField.sendKeys(sellerName);
        nativeSwipeUp();
        String elm_sellerNameSearch = constructLocator("seller_name_search", sellerName);
        waitForVisibilityOf(elm_sellerNameSearch,20);
        tapElement(elm_sellerNameSearch);
    }

    public void searchPelapakFromHome(String sellerName) {
        MerchantData.setSellerName(sellerName);
        verifyElementExist("home_search_product_xpath");
        searchPelapak(sellerName);
    }

    // PROM section
    public void clickOnPromotedProduct(String productName) {
        waitForVisibilityOf("search_product_price", 15);
        swipeDownToElement(constructLocator("search_promoted_push_icon2", productName));
        if (getElementsPresent(constructLocator("search_promoted_push_icon2", productName)).size() > 1) {
            tapElement(constructLocator("search_promoted_push_icon1", productName));
        } else {
            tapElement(constructLocator("search_promoted_push_icon2", productName));
        }
        HelperData.setLastActionPage(new SearchPage(iosDriver));
    }

    public void verifyIklanLapakAppear() {
        verifyElementDisplayed("iklan_lapak_profile");
        verifyElementDisplayed("iklan_lapak_product");
        verifyElementDisplayed("kunjungi_lapak_text");
        verifyElementDisplayed("super_seller_icon");
        verifyElementDisplayed("rating_iklan_lapak_product");
        verifyElementDisplayed("first_name_product_iklan_lapak");
        assertTextContains("feedback", getTextFromElement("feedback_iklan_lapak_profile"));
        assertTextContains("Rp", getTextFromElement("price_iklan_lapak_product"));
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void checkIklanLapakSection(String username, String productName) {
        waitForVisibilityOf("iklan_lapak_profile");
        assertEquals(username, getElementLabel("product_listing_iklan_lapak_seller"));
        assertEquals(productName, getElementLabel("first_name_product_iklan_lapak"));
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }
    // End region PROM

    public void validateQuickFilterTab() {
        okOnboarding();
        waitForVisibilityOf("quick_filter_section", 30);
    }

    public void validateProductResult() {
        validateDisplayed("product_list");
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void tapQuickFilter(String filter) {
        int maxSwipe = 5;
        while (!isElementClickable(constructLocator("quick_filter_tab", filter)) &&
                (maxSwipe-- > 0)) {
            swipeLeftAtSpecifiedLocator("quick_filter_section");
        }

        tapElement(constructLocator("quick_filter_tab", filter));
    }

    public void validateAddressModal(String flag) {
        if (flag == null) {
            validateDisplayed("select_address_modal");
        } else {
            validateNotDisplayed("select_address_modal");
        }
    }

    public void validateSavedAddressOption(String flag) {
        if (flag  == null) {
            validateDisplayed("saved_address_option");
        } else {
            validateNotDisplayed("saved_address_option");
        }

    }

    public void validateLokasiSekarangOption() {
        validateDisplayed("lokasi_sekarang_option");
    }

    public void tapSavedAddress() {
        tapElement("saved_address_option");
    }

    public void tapCloseAddressModal() {
        tapElement("close_addr_modal", 5);
    }

    public void validateLocationSeller() {
        swipeUpToElement("seller_location");
        verifyElementExist("seller_location", 10, "Seller location not found");
        int productSize = getElements("seller_location").size();
        for (int i = 1; i < productSize; i++) {
            validateValue().equals(getText("seller_location"),
                    getText("seller_location", i));
        }
        SearchData.setAddress(getText("seller_location"));
    }

    public void validateProductList() {
        nativeSwipeUp();
        verifyElementExist("search_result_product_name", 10, "Product list not found");
        int productSize = getElementsPresent("search_result_product_name").size();
        for (int i = 0; i < productSize; i++) {
            validateValue().contains(
                    SearchData.getSearchKeywords(),
                    getText("search_result_product_name", i));
        }
    }

    public void validateKirimDariFilter(boolean isDisplayed) {
        tapFilterButton();
        String element = constructLocator("filter_kirim_dari", SearchData.getAddress());
        swipeUpToElement(element, 3);
        if (isDisplayed) {
            validateDisplayed(element);
        } else {
            validateNotDisplayed(element);
        }
        tapCloseFilter();
    }

    private void tapCloseFilter() {
        tapElement("filter_close_button");
    }

    public void verifySuperSellerIcon() {
        HelperData.setLastActionPage(new SearchPage(iosDriver));
        waitForVisibilityOf("listing_seller_super_seller_icon", 20);
    }
    public void verifyOnboardingModal() {
        waitForVisibilityOf("new_onboarding_modal");
        verifyElementExist("new_onboarding_modal", 10, "onboarding not visible");
    }

    public void verifyOnboardingTextModal(String onboardingStep) {
        String onboardingText = getText("new_onboarding_text");
        switch (onboardingStep){
            case "navbar sort":
                assertTextContains("Urutin hasil pencarian", onboardingText);
                break;
            case "quick filter":
                assertTextContains("filter kilat", onboardingText);
                break;
            case "filter button":
                assertTextContains("filter lainnya", onboardingText);
                break;
            default:
                Assert.fail("Invalid button");
                break;
        }
    }

    public void tapOnboardingButton(String buttonName) {
        switch (buttonName) {
            case "Mulai Kenalan":
                tapElement("new_onboarding_start");
                break;
            case "Lanjut":
                tapElement("new_onboarding_next_ok");
                break;
            case "Kembali":
                tapElement("new_onboarding_back");
                break;
            case "Selesai":
                tapElement("new_onboarding_next_ok");
                break;
            default:
                Assert.fail("Invalid button");
                break;

        }
    }

}
