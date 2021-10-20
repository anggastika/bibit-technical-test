package bukalapak.pageObject;

import bukalapak.data.CategoryData;
import bukalapak.data.HelperData;
import bukalapak.data.ProductDetailData;
import bukalapak.data.SearchData;
import bukalapak.data.UserData;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

import static bukalapak.TestInstrument.dotenv;

public class ProductListingPage extends BasePage {

    public ProductListingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void viaDeeplink(String deeplink) {
        if (!UserData.isLoggedIn()) {
            // need to delay due to weird behaviour,
            // when previous step is using deeplink, this step won't run
            waitFor(3);
        }

        String decodedUrl = urlDecode(deeplink);
        Map<String, String> queries = urlQueryParams(decodedUrl);

        SearchData.setSearchKeywords(queries.get("search[keywords]"));
        openDeepLink(deeplink);
    }

    public void searchResultOkOnboarding() {
        // if already onboard, skip waiting to save execution time
        if(SearchData.getOnBoard()) return;

        if (isElementVisible("onboarding_lewati_button", 10)) {
            tapElement("onboarding_lewati_button");
            SearchData.setOnBoard(true);
        }
    }

    public void checkProductOnProductListing(String verify) {
        searchResultOkOnboarding();

        // close kotak harta karun popup
        if(isElementExist("product_listing_harta_karun_btn", 3)) {
            tapElement("product_listing_harta_karun_btn");
        }

        String searchKeywords = SearchData.getSearchKeywords().toLowerCase();
        String currentKeyword = getElementAttributeValue("search_field_result", "value").toLowerCase();
        assertEquals(currentKeyword, searchKeywords, "The keywords don't match");
        assertFalse(isElementVisible("omni_search_not_exist"), "Product is Exist");

        if(verify != null) {
            LogUtil.info(verify);
            List<IOSElement> elements = getElements("product_card_titles");
            List<String> titles = new ArrayList<>();

            for (IOSElement element : elements) {
                titles.add(element.getAttribute("value"));
            }

            SearchData.setProductsTitles(titles);
        }

        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void clickBuy(String buyFrom) {
        switch (buyFrom) {
            case "product listing":
                ProductDetailData.setProductName(getTextFromElement(constructLocator("product_listing_prod_card_name", SearchData.getSearchKeywords())));
                swipeToLocator("click_buy_from_list");
                tapElement("click_buy_from_list");
                break;
            case "product detail":
                tapElement("click_buy_from_product_detail");
                break;
            case "pop up atc variant":
                tapElement("buy_base_button");
                break;
            default:
                break;
        }
        waitFor(5);
    }

    public void buyFromProductListing(String productName) {
        swipeUpToElement(constructLocator("product_listing_page_btn_beli", productName));
        tapElement(constructLocator("product_listing_page_btn_beli", productName));
        if (isElementExist("product_listing_page_bayar_product_list_revamp", 5)) {
            tapElement("product_listing_page_bayar_product_list_revamp");
        } else {
            tapElement("product_listing_page_lanjut_pembayaran", 5);
        }
    }

    public void buyAgainProductListing(String productName) {
        swipeUpToElement(constructLocator("product_listing_page_btn_beli", productName));
        tapElement(constructLocator("product_listing_page_btn_beli", productName));
        if (isElementExist("product_listing_page_tambah_barang" , 5)) {
            tapElement("product_listing_page_tambah_barang");
        } else {
            tapElement("product_listing_page_close_pop_up", 5);
        }
    }

    private void isItValidPrice(String element) {
        waitForVisibilityOf(element, 10);
        int price = Integer.parseInt(getText(element).replaceAll("[^0-9]+", ""));
        Assert.assertTrue("Product price is not valid", price > 0);
    }

    public void checkIsRedirectToPopUpAddToCart() {
        String productName = ProductDetailData.getProductName();
        if (productName.length() > 35) {
            productName = productName.substring(0, 35);
        }
        waitForVisibilityOf("berhasil_masuk_keranjang");
        verifyElementExist(constructLocator("product_listing_label_contains", productName));
        isItValidPrice("product_price_pop_up_cart");
        isItValidPrice("total_price_pop_up_cart");
        assertTrue(isElementClickable("checkout_pop_up_cart_button"), "This element cannot be clicked");
        tapElement("close_pop_up_add_to_cart");
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void checkIsOnProductDetail() {
        if (isElementVisible("product_details_on_boarding_benefit_text", 15)) {
            tapElement("product_details_ok_button");
        }
        if (!isElementVisible("berhasil_masuk_product_detail", 20)) {
            assertTrue(isElementVisible("berhasil_masuk_product_detail_preorder", 20),
                    "Product does not Exist");
        }
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void checkIsOnOwnProductDetail() {
        if (isElementExist("product_details_on_boarding_benefit_text", 10)) {
            tapElement("product_details_ok_button");
        }
        Assert.assertTrue("Product is Exist", !isElementVisible("berhasil_masuk_product_detail"));
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    private void closePopUpAddToCart() {
        if (isElementVisible("close_pop_up_add_to_cart")) {
            tapElement("close_pop_up_add_to_cart");
        }
    }

    public void clickOnTabFilter() {
        if(!isElementVisible("click_tab_filter", 2)) {
            nativeSwipeDown();
        }

        tapElement("click_tab_filter");
    }

    public void clickProductOnListing() {
        waitForVisibilityOf("click_product", 10);
        tapElements("click_product", 0);
    }

    public void checkIsBukaMallProductsExist() {
        int x = 3, i;
        for (i = 0; i < x; i++) {
            swipeToDirection(Direction.DOWN);
            assertTrue(isElementVisible("check_bukaMall_product"), "BukaMall Products is Exist");
        }
    }

    public void checkIsDiscountedProductsExist() {
        int swipeAttempt = 3;
        for (int i = 0; i < swipeAttempt; i++) {
            swipeToDirection(Direction.UP);
            waitFor(2);
            validateExist("discounted_product");
        }
    }

    public void backToHome() {
        backToHomePageViaDeeplink();
    }

    public void getTextProductName() {
        getTextFromElement("product_name");
    }

    public void clickProductFromListing() {
        tapElement(constructLocator("search_result_product_card", 1));
    }

    public void selectListMode() {
        if (isElementVisible("mode_list")) {
            tapElement("mode_list");
        } else {
            LogUtil.info("Mode listing is List");
        }
    }

    public void selectGridMode() {
        if (isElementVisible("mode_grid")) {
            tapElement("mode_grid");
        } else {
            LogUtil.info("Mode listing is Grid");
        }
    }

    public void clickTabUrutkan() {
        tapElement("tab_urutkan");
    }

    public void selectUrutkanTab(String urutkanType) {
        switch (urutkanType) {
            case "terbaru":
                this.clickUrutkanTerbaru();
                break;
            case "termurah":
                this.clickUrutkanTermurah();
                break;
            case "termahal":
                this.clickUrutkanTermahal();
                break;
            case "terlaris":
                this.clickUrutkanTerlaris();
                break;
            case "relevansi":
                this.clickUrutkanRelevansi();
                break;
            default:
                break;
        }
    }

    public void clickUrutkanTerbaru() {
        tapElement("tab_urutkan_terbaru");
    }

    public void checkUrutkanTerbaruIsActive() {
        assertTrue(isElementVisible("tab_urutkan_terbaru"), "Urutkan Terbaru Aktif");
    }

    public void clickUrutkanTermurah() {
        tapElement("tab_urutkan_termurah");
    }

    public void checkUrutkanTermurahIsActive() {
        assertTrue(isElementVisible("tab_urutkan_termurah"), "Urutkan Termurah Aktif");
    }

    public void clickUrutkanTermahal() {
        tapElement("tab_urutkan_termahal");
    }

    public void checkUrutkanTermahalIsActive() {
        assertTrue(isElementVisible("tab_urutkan_termahal"), "Urutkan Termahal Aktif");
    }

    public void clickUrutkanTerlaris() {
        tapElement("tab_urutkan_terlaris");
    }

    public void checkUrutkanTerlarisIsActive() {
        assertTrue(isElementVisible("tab_urutkan_terlaris"), "Urutkan Terlaris Aktif");
    }

    public void clickUrutkanRelevansi() {
        tapElement("tab_urutkan_relevansi");
    }

    public void checkUrutkanRelevansiIsActive() {
        assertTrue(isElementVisible("tab_urutkan_relevansi"), "Urutkan Relevansi Aktif");
    }

    public void scrollToNonPromotedProduct() {
        if (isElementVisible("list_promoted_product")) {
            swipeDownToElement("list_non_promoted_product");
        } else {
            while (isElementVisible("icon_promoted")) {
                swipeToDirection(Direction.DOWN);
            }
        }
    }

    public void clickProductAtIndex(String index) {
        swipeUpToElement(constructLocator("search_result_product_card", index));
        tapElement(constructLocator("search_result_product_card", index));
    }

    public void clickBackToProductDetail() {
        tapElement("back_icon");
    }

    public void closePopUpInfoBarang() {
        tapElement("close_pop_up_info_barang");
    }

    public void checkCategoryOnListing() {
        String categoryInfoBarang = ProductDetailData.getKategoriName();
        String categoryListing = getElementValue("kategori_on_product_listing");
        assertTrue(categoryInfoBarang.equals(categoryListing), "Klik Kategori dari Info Barang Berhasil");
        clickBackToProductDetail();
        closePopUpInfoBarang();
    }

    public void scrollToProductRating() {
        while (!isElementVisible("rating_product")) {
            swipeDownToElement("rating_product");
        }
    }

    public void clickProductsRating() {
        tapElement("rating_product");
    }

    public void searchProduct(String namaBarang) {
        tapElement("barang_dijual_search_button");
        typeAndEnterValueWithTimeOut("barang_dijual_search_button", namaBarang + "");
    }

    public void backToHomeFromATCPopUp() {
        closePopUpAddToCart();
        while (isElementVisible("base_back_button")) {
            tapElement("base_back_button");
        }
    }

    public void clickDeleteFromPopUp() {
        if (isElementClickable("icon_delete_pop_up")) {
            tapElement("icon_delete_pop_up");
        } else {
            tapElement("new_pop_cart_dialog_trash_button");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void selectTab(String tab_name) {
        switch (tab_name) {
            case "Katalog":
                tapElement("search_katalog_tab");
                break;
            case "Pelapak":
                tapElement("search_pelapak_tab");
                break;
            case "Barang":
                tapElement("search_barang_tab");
                break;
            default:
                Assert.fail("Invalid tab name");
                break;
        }
    }

    public void isAbleToCompleteCatalogCardOnb() {
        if (isElementVisible("product_catalog_card_onb")) {
            tapElement("onboarding_ok_button");
            assertFalse(isElementVisible("product_catalog_card_onb"), "Product listing page still displays product catalog card onboarding");
        } else {
            okOnboarding();
        }
    }

    public void swipeLeftToLihatSemuaCatalogCard() {
        List<WebElement> catalogCard = getElements("product_catalog_card", 6);
        final double catalogCardHeight = catalogCard.get(0).getSize().height;
        final double catalogCardCenterHeight = catalogCardHeight / 2;
        final double catalogCardYCoordinate = catalogCard.get(0).getLocation().y;
        final double deviceHeight = iosDriver.manage().window().getSize().height;
        final double yCoordinate = (catalogCardYCoordinate + catalogCardCenterHeight) / deviceHeight;

        for (int i = 0; i < 9; i++) {
            swipeLeft(0.9, 0.1, yCoordinate);
        }
    }

    public List<WebElement> getCatalogCardElements() {
        return getElements("product_catalog_card_title", 6);
    }

    public void verifyCatalogCardAmount() {
        List<WebElement> product_catalog_card_element = getCatalogCardElements();
        List<String> product_catalog_card_names = new ArrayList<>();
        List<WebElement> catalogCard = getElements("product_catalog_card", 6);

        final double catalogCardHeight = catalogCard.get(0).getSize().height;
        final double catalogCardCenterHeight = catalogCardHeight / 2;
        final double catalogCardYCoordinate = catalogCard.get(0).getLocation().y;
        final double deviceHeight = iosDriver.manage().window().getSize().height;
        final double yCoordinate = (catalogCardYCoordinate + catalogCardCenterHeight) / deviceHeight;

        // assign value for first displayed product catalog card
        for (int i = 0; i < product_catalog_card_element.size(); i++) {
            product_catalog_card_names.add(product_catalog_card_element.get(i).getText());
        }

        // assign value for the rest displayed product catalog card while swiping
        for (int j = 0; j < 9; j++) {
            swipeLeft(0.9, 0.1, yCoordinate);
            product_catalog_card_element = getCatalogCardElements();

            for (int k = 0; k < product_catalog_card_element.size(); k++) {
                product_catalog_card_names.add(product_catalog_card_element.get(k).getText());
            }
        }

        product_catalog_card_names = product_catalog_card_names.stream().distinct().collect(Collectors.toList());
        assertTrue(product_catalog_card_names.size() <= 5, "Product catalog cards are more than 5");
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    /**
     * @param keyword use env keyword
     */
    public void verifyCatalogCardProductName(String keyword) {
        waitForVisibilityOf("product_catalog_card_title", 10);
        String product_catalog_card_name = getTextFromElement("product_catalog_card_title", 0).toLowerCase();
        assertTrue(product_catalog_card_name.contains(dotenv.get(keyword.toUpperCase()).toLowerCase()), "Product catalog card not contain related product catalog");
    }

    public void waitForCatalogCardElement() {
        waitForElementClickable("product_catalog_card", 10);
    }

    public void verifyCatalogCardExist() {
        assertTrue(isElementVisible("product_catalog_card"), "Product listing page not display product catalog card");
    }

    public void verifyCatalogTabContent() {
        verifyElementExist("catalog_listing_catalog_cell");
        assertTrue(isElementVisible("catalog_listing_catalog_count"), "Product catalog list not contains catalog count");
        assertTrue(isElementVisible("catalog_listing_catalog_cell"), "Product catalog list not contains product catalog");
        assertTrue(isElementVisible("catalog_listing_catalog_image"), "Product catalog list not contains catalog image");
        assertTrue(isElementVisible("catalog_listing_catalog_title"), "Product catalog list not contains catalog name");
        assertTrue(isElementVisible("catalog_listing_catalog_price"), "Product catalog list not contains catalog price");
        assertTrue(isElementVisible("catalog_listing_catalog_product_count"), "Product catalog list not contains catalog product count");
        assertTrue(isElementVisible("catalog_listing_catalog_median_icon"), "Product catalog list not contains catalog median icon");
    }

    public void filterCatalogBySpecificFilter(String filter_type) {
        FilterProductPage filterProductPage = new FilterProductPage(iosDriver);
        switch (filter_type) {
            case "filter":
                clickOnTabFilter();
                swipeToDirection(Direction.UP);
                tapElement("filter_product_condition");
                tapElement("filter_product_condition_baru");
                tapElement("button_simpan");
                filterProductPage.tapTerapkanFilter();
                break;
            case "sorting":
                clickTabUrutkan();
                clickUrutkanTermurah();
                break;
            case "view option":
                selectListMode();
                break;
            case "quick filter":
                tapElement("qf_bukamall");
                break;
            case "invalid filter":
                clickOnTabFilter();
                filterProductPage.selectFilterProduct("diskon");
                filterProductPage.selectFilterProduct("cicilan");
                filterProductPage.selectFilterProduct("grosir");
                filterProductPage.tapTerapkanFilter();
                break;
            case "category":
                tapElement("category_filter");
                String catalog_category_handphone = constructLocator("product_listing_label_contains", dotenv.get("CATALOG_CATEGORY_HANDPHONE"));
                tapElement(catalog_category_handphone);
                String catalog_category_semua_handphone = constructLocator("product_listing_label_contains", dotenv.get("CATALOG_CATEGORY_SEMUA_HANDPHONE"));
                tapElement(catalog_category_semua_handphone);
                break;
            default:
                Assert.fail("Invalid filter type");
                break;
        }
    }

    public void verifyCatalogCardContent(DataTable arg0) {
        List<Map<String, String>> product_catalog = arg0.asMaps();
        IOSElement elementProductCatalogName = getElement("product_catalog_card_title", 6);
        IOSElement elementProductCatalogPrice = getElement("product_catalog_card_price", 6);
        assertTrue(elementProductCatalogName.getText().equals(product_catalog.get(0).get("NAME")), "Product catalog card has invalid name");
        assertTrue(elementProductCatalogPrice.getText().equals("Rp" + product_catalog.get(0).get("PRICE")), "Product catalog card has invalid price");
        assertTrue(isElementVisible(constructLocator("product_listing_label", product_catalog.get(0).get("TOTAL_PRODUCT"))), "Product catalog card has invalid product count");
    }

    public void verifyCatalogListingProductName(String keyword) {
        String catalog_list_catalog_name = getTextFromElement("catalog_listing_catalog_title", 0).toLowerCase();
        // check product catalog listing contain keyword
        assertTrue(catalog_list_catalog_name.contains(Objects.requireNonNull(dotenv.get(keyword.toUpperCase())).toLowerCase()), "Catalog listing not contain related product catalog");
    }

    public void tapCartIcon() {
        isElementVisible("cart_icon_on_pdp");
        tapElement("cart_icon_on_pdp");
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void checkCategoryName() {
        String categoryName = CategoryData.getCategoryName();
        String elm_categoryName = constructLocator("product_listing_name", categoryName);
        assertTrue(isElementVisible(elm_categoryName), "Category Tidak Sesuai");
    }

    public void clickBackFromPLRevamp() {
        tapElement("back_pl_revamp");
    }

    public void checkCategoryOnProductListing() {
        String categoryName = CategoryData.getCategoryName();
        assertTrue(isElementVisible(constructLocator("product_listing_name", categoryName)), "Filter Kategori Tidak ditampilkan di Product Listing");
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void isCategoryApplied(String categoryName) {
        switch (categoryName) {
            case "sub category non cm":
                waitForVisibilityOf("sub-category-filter", 10);
                HelperData.setLastActionPage(new ProductListingPage(iosDriver));
                break;
            case "sub category cm":
                assertTrue(isElementVisible("sub-category-filter"), "Kategori tidak ditemukan");
                HelperData.setLastActionPage(new ProductListingPage(iosDriver));
                break;
            default:
                break;
        }
    }

    public void clickOnTheQuickFilter(String filter) {
        int swipeCount = 0;

        while(swipeCount < 5 && !isElementVisible(constructLocator("quick_filter_button", filter))) {
            swipeLeftAtSpecifiedLocator("quick_filter_container");
            swipeCount++;
        }

        validateDisplayed(
            constructLocator("quick_filter_button", filter),
            "Quick filter " + filter + " is not found!"
        );

        tapElement(constructLocator("quick_filter_button", filter));
    }

    public void verifyFirstActiveQuickFilter(String filter, String condition) {
        assertTrue(isElementVisible("first_quick_filter_button"), "Quick Filters button not found");
        String expected_filter_name = dotenv.get(filter);
        String actual_first_filter_name = getElementValue("first_quick_filter_button");
        if (condition.equals("select")) {
            assertEquals("The first Quick Filter is not " + expected_filter_name, actual_first_filter_name, expected_filter_name);
        } else {
            assertNotSame("The first Quick Filter is " + expected_filter_name + ", it should be different", actual_first_filter_name, expected_filter_name);
        }
    }

    public void scrollDownToCatalogCard() {
        swipeDownToElement("catalog_search_listing_see_all_btn_list_view", 5);
    }

    public void verifyCatalogCard() {
        waitForVisibilityOf("product_catalog_card");
        assertTrue(isElementVisible("product_catalog_card_title"));
        assertTrue(isElementVisible("product_catalog_card_image"));
    }

    public void verifyCatalogTag(String keyword) {
        String catalog_product_info = getTextFromElement("catalog_detail_product_recomendation_title", 0).toLowerCase();
        assertTrue(catalog_product_info.contains(Objects.requireNonNull(dotenv.get(keyword.toUpperCase())).toLowerCase()), "Product catalog recommendation not contain related catalog tag keyword");
    }

    public void verifyCatalogListing() {
        waitForVisibilityOf("catalog_listing_catalog_cell");
        assertTrue(isElementVisible("catalog_listing_catalog_title"));
        assertTrue(isElementVisible("catalog_listing_catalog_image"));
    }

    public void verifyIsFavorited(Integer index, String state) {
        Boolean status = Boolean.parseBoolean(
                getElementValue(
                        constructLocator("product_card_favorite_button", index)
                )
        );

        switch (state) {
            case "active":
                assertTrue(status, "Item is not marked as favorite");
                break;
            case "not active":
                assertFalse(status, "Item is already marked as favorite");
                break;
            default:
                Assert.fail("Invalid state: \"" + state + "\"");
                break;
        }

        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void tapFavoriteButton(Integer index) {
        validateDisplayed(
            constructLocator("product_card_favorite_button", index)
        );
        tapElement(
            constructLocator("product_card_favorite_button", index)
        );

        // wait for element to finish shimmering
        waitFor(5);
    }

    public void sort(String by) {
        ProductDetailData.setProductName(constructLocator("product_card_title", 1));

        if(by.equals("Termahal")) {
            for(int i=0; i<2; i++) {
                if(isElementExist("product_listing_sort_termurah")) {
                    tapElement("product_listing_sort_termurah");
                }
            }
        }
        else if(by.equals("Termurah")) {
            if(isElementExist("product_listing_sort_termahal")) {
                tapElement("product_listing_sort_termahal");
            }
            else {
                tapElement("product_listing_sort_termurah");
            }
        }
        else {
            validateExist(constructLocator("product_listing_sort", by));
            tapElement(constructLocator("product_listing_sort", by));
        }

        // wait for result to refresh
        waitFor(3);
    }

    public void verifySortTabActive(String sortType) {
        okOnboarding();

        // close kotak harta karun popup
        if(isElementExist("product_listing_harta_karun_btn", 3)) {
            tapElement("product_listing_harta_karun_btn");
        }

        validateDisplayed(constructLocator("product_listing_sort", sortType));

        IOSElement target_tab = (IOSElement) getElement(constructLocator("product_listing_sort_container", sortType));
        IOSElement active_tab = (IOSElement) getElement("product_listing_active_indicator");

        assertEquals(target_tab.getLocation().x, active_tab.getLocation().x, "Tab \"" + sortType + "\" is not active!");
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void verifyProductPrice(Integer index, String state, Float limitPrice) {
        int multiplier = 1;

        String productPrice = getElementValue(
            constructLocator("product_card_price", index)
        );

        if(productPrice.contains("rb")) {
            multiplier = 1000;
        }
        else if(productPrice.contains("jt")) {
            multiplier = 1000000;
        }
        else if(productPrice.contains("mil")) {
            multiplier = 1000000000;
        }

        productPrice = productPrice.replaceAll("(Rp|rb|jt|mil|\\.)", "");
        productPrice = productPrice.replace(",", ".");

        Float convertedPrice = Float.parseFloat(productPrice) * multiplier;

        switch(state) {
            case "less than":
                assertTrue(convertedPrice < limitPrice, convertedPrice + " is greater than " + limitPrice);
            break;
            case "less than or equal":
                assertTrue(convertedPrice <= limitPrice, convertedPrice + " is greater than " + limitPrice);
            break;
            case "greater than":
                assertTrue(convertedPrice > limitPrice, convertedPrice + " is less than " + limitPrice);
            break;
            case "greater than or equal":
                assertTrue(convertedPrice >= limitPrice, convertedPrice + " is less than " + limitPrice );
            break;
            default:
                Assert.fail("Invalid state: " + state);
            break;
        }

        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void verifyPantauSainganSection() {
        validateDisplayed("product_listing_pantau_saingan_text");
        validateDisplayed("product_listing_pantau_saingan_desc_text");
    }

    public void verifyCardComponent(Integer index, String state, String component, String type) {
        swipeUpToElement(constructLocator("product_card_price", index));

        String locator = "product_card_tag";
        String tmpComponent = component;

        if(type.equals("badge")) {
            locator = "product_card_badge";

            switch(component) {
                case "BukaMall":
                    tmpComponent = "buka_mall";
                break;
                case "Super Seller":
                    tmpComponent = "super_seller";
                break;
                default:
                    Assert.fail("Invalid badge: " + component + ". Expecting \"BukaMall\" or \"Super Seller\"");
                break;
            }
        }

        switch(state) {
            case "has no":
                validateNotDisplayed(
                    constructLocator(locator, index, tmpComponent),
                    "" + type + ": " + tmpComponent + " is found on product card index " + index + "!"
                );
            break;
            default:
                validateDisplayed(
                    constructLocator(locator, index, tmpComponent),
                    "" + type + ": " + tmpComponent + " is not found on product card index " + index + "!"
                );
        }

        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void verifyLabel(Integer index) {
        swipeUpToElement(constructLocator("product_card_price", index));
        validateDisplayed(constructLocator("product_card_terjual", index));
    }

    public void verifyOrderChange() {
        String productNameBefore = ProductDetailData.getProductName();
        String productNameAfter = getElementValue(constructLocator("product_card_title", 1));

        assertTrue(!productNameBefore.equals(productNameAfter));
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void validateCategoryApplied(){
        if (isElementVisible("onboarding_lewati_button")) {
            tapElement("onboarding_lewati_button");
        }
        verifyElementExist("product_listing_by_category");
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void clearOmnisearch() {
        tapElement("search_result_omni_clear_btn");
    }

    public void verifySelectAddressMenu() {
        verifyElementExist("instant_courier_address_modal");
    }

    public void selectAddress(Integer index) {
        tapElement(constructLocator("instant_courier_address_radio", index));

        if(isElementExist("instant_courier_access_permission_text")) {
            tapElement("instant_courier_access_permission_btn");

            // above code will trigger Location Permission Request
            // waitFor is needed for overriding appium AutoAcceptAlert: true
            // if not using this, appium will immediately reject the permission request
            waitFor(2);
            tapElement("instant_courier_access_permission_allow");

            tapElement(constructLocator("instant_courier_address_radio", index));
        }
    }

    public void verifyQuickFilterActive(String filter) {
        switch(filter) {
            case "Kurir Instan":
                waitForVisibilityOf("instant_courier_applied_address", 3);
                SearchData.setAddress(
                    getElementValue("instant_courier_applied_address_value")
                );
            break;
            default:
                // there is no unique identifier for active or inactive quickfilter for now
                // in the mean time, will only check if selected quickfilter is displayed
                validateDisplayed(constructLocator("quick_filter_button", filter));
            break;
        }

        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void verifyCategoryFilter(){
        String categoryName = CategoryData.getCategoryName();
        tapElement("filter_buttton");

        if (!isElementExist("minimal_field_filter") || !isElementExist("maksimal_field_filter")) {
            verifyElementExist("category_icon_active");
        } else {
            validateDisplayed(constructLocator("category_page_product_name", categoryName));
        }
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void verifyNoFilter() {
        validateNotDisplayed("active_filter_icon");
    }

    public void verifySendFrom(Integer index, String location) {
        swipeUpToElement(constructLocator("product_card_price", index));

        String sendFrom = getElementValue(constructLocator("product_card_send_from", index));

        assertEquals(sendFrom, location, "Location doesn't match: " + sendFrom);
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    // checking if card is shown and is in correct position
    public void verifySpecialCardPosition(String type) {
        String tmpType = type.replace(" ", "_").toLowerCase();

        Integer index = getSpecialCardIndex(tmpType);
        String prevIndex = getElementValue("special_card_" + tmpType + "_card_prev");

        validateDisplayed(
            "special_card_" + tmpType + "_card",
            "Card " + tmpType + " is not displayed"
        );

        // check if the position is correct
        assertEquals(
            (Integer.parseInt(prevIndex) + 1),
            index
        );
    }

    public Integer getSpecialCardIndex(String type) {
        Integer index = -1; // default index, -1 = not shown
        Integer swipe = 15;

        // get position set in neo config
        String config = API_CALL.getNeoConfigByID("search-result-card-positions-config");
        JsonObject ob = new JsonParser().parse(config).getAsJsonObject();
        JsonArray arr = ob.getAsJsonArray("special-cards-config");

        for(int i=0; i<arr.size(); i++) {
            if(arr.get(i).getAsJsonObject().get("type").getAsString().equals(type)) {
                index = arr.get(i).getAsJsonObject().get("position").getAsInt();
                break;
            }
        }

        for(int i=0; i<swipe; i++) {
            if(isElementVisible("special_card_" + type + "_title")) {
                swipeUpToElement("special_card_" + type + "_card");
                break;
            }

            nativeSwipeUp();
        }

        return index;
    }

    public void tapOnSpecialCardOption(String flag, String type) {
        String card = type.replace(" ", "_").toLowerCase();
        String locator = "special_card_" + card + "_card";

        if(card.equals("continuation")) {
            int index = randomize().number(5) | 1;
            String keyword = getElementValue("search_result_search_field");
            String continuation = getElementValue(
                constructLocator("special_card_continuation_suggestion_text", index)
            );

            locator = constructLocator("special_card_" + card + "_suggestion_btn", index);
            SearchData.setSearchKeywords(keyword + " " + continuation);
        }

        tapElement(locator);
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void verifyResultMessage(String type) {
        String locator = type.toLowerCase().replace(" ", "_");
        waitForVisibilityOf(constructLocator(locator + "_title"), 10);
    }

    public void verifyRecommendation(Boolean isActive) {
        if(isActive) {
            validateDisplayed("search_result_recommendation_title");
            validateDisplayed(constructLocator("search_result_product_card", 1));
        }
        else {
            validateNotDisplayed("search_result_recommendation_title");
            validateNotDisplayed(constructLocator("search_result_product_card", 1));
        }
    }

    public void verifyRelatedKeywords() {
        validateDisplayed("search_result_related_keyword_title");
        validateDisplayed("search_result_related_keyword_options");
        validateDisplayed(constructLocator("search_result_product_card", 1));
    }

    public void tapRelatedKeyword() {
        String keyword = getElementValue("search_result_related_keyword_options", 0);
        SearchData.setSearchKeywords(keyword);
        tapElement("search_result_related_keyword_options");
    }

    public void tapButtonOnWarningPage(String label) {
        tapElement(constructLocator("product_listing_label", label));
    }

    public void backFromSubCategoryPage(){
        if(isElementExist("button_back")){
            tapElement("button_back");
        }
    }

    public void verifyInBestSellingPage() {
        validateDisplayed("best_selling_page_title");
    }

    public void tapcardInBestSelling(Integer position) {
        tapElement(constructLocator("best_selling_page_card", position));
    }

    public void redirectCampaignDeeplink(String keyword) {
        if (!UserData.isLoggedIn()) {
            // need to delay due to weird behaviour,
            // when previous step is using deeplink, this step won't run
            waitFor(3);
        }

        String campaignID = API_CALL.getActiveCampaignID(0);
        SearchData.setSearchKeywords(keyword);
        openDeepLink("/products?search%5Bkeywords%5D=" + keyword + "&search%5Bcampaign_ids%5D%5B%5D=" + campaignID);
    }

    public void verifyCampaignBannerProductCard(Integer position) {
        validateDisplayed("product_card_campaign_banner");
        HelperData.setLastActionPage(new ProductListingPage(iosDriver));
    }

    public void tapCampaignFilter(Integer position) {
        tapElement("product_listing_campaign_filter");
    }

    public void verifyProductChange() {
        List<String> previousTitles = SearchData.getProductsTitles();
        List<IOSElement> elements = getElements("product_card_titles");
        List<String> currentTitles = new ArrayList<>();

        for (IOSElement element : elements) {
            currentTitles.add(element.getAttribute("value"));
        }

        LogUtil.info("currentTitles = " + currentTitles);
        LogUtil.info("previousTitles = " + previousTitles);

        assertTrue(!currentTitles.equals(previousTitles), "Result is not changed");
    }
}
