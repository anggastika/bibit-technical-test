package bukalapak.pageObject;

import bukalapak.data.CategoryData;
import bukalapak.data.DiscoveryData;
import bukalapak.data.HelperData;
import bukalapak.data.SearchData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.util.ArrayList;

public class CategoryPage extends BasePage {

    public CategoryPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void checkIconNavbarCategory() {
        verifyElementExist("icon_search_category");
        verifyElementExist("icon_chat_category");
        verifyElementExist("icon_cart_category");
    }

    public void checkIconSearchNavbar(String iconName) {
        CategoryData.setCategoryName(getTextFromElement("category_name"));
        switch (iconName) {
            case "search":
                clickIconSearchNavbar();
                break;
            case "chat":
                clickIconChatNavbar();
                break;
            case "cart":
                clickIconCartNavbar();
                break;
            default:
                break;
        }
        waitFor(5);
    }

    public void clickIconSearchNavbar() {
        tapElements("icon_search_category", 0);
    }

    public void clickIconChatNavbar() {
        tapElements("icon_chat_category", 1);
    }

    public void clickIconCartNavbar() {
        tapElements("icon_cart_category", 2);
    }

    public void checkListCategoryCM() {
        assertTrue(isElementVisible("icon_category_cm"), "Tidak Ada list Kategori");
    }

    public void clickShowAllCategoryCM() {
        tapElement("icon_lihat_semua_category_cm");
    }

    public void checkAllCategoryCM() {
        swipeDownToElement("icon_sembunyikan_semya_category_cm");
        tapElement("icon_sembunyikan_semya_category_cm");
        assertTrue(isElementVisible("icon_lihat_semua_category_cm"), "Gagal Sembunyikan Kategori");
    }

    public void clickCategoryOnCM(String contentType) {
        switch (contentType) {
            case "category":
                CategoryData.setCategoryName(getTextFromElement("icon_category_cm"));
                clickCategoryJilbab();
                break;
            default:
                LogUtil.info("User in Page Category Landing Page");
                break;
        }
    }

    public void clickCategoryJilbab() {
        tapElement("icon_category_cm");
        tapElement("icon_semua_category_cm");
    }

    public void checkBukaMallOnCM() {
        assertTrue(isElementVisible("bukamall_title"), "BukaMall Tidak Ditemukan");
    }

    public void clickBukaMallOnCM() {
        swipeDownToElement("bukamall_title");
        tapElement("bukamall_content");
    }

    public void checkTopPicksOnCM() {
        waitFor(5);
        assertTrue(isElementVisible("top_picks_cm_title"), "Top Picks tidak ada di Landing Page");
    }

    public void isContentTopPicksExist() {
        swipeDownToElement("top_picks_cm_title");
        assertTrue(isElementVisible("top_picks_cm"), "Konten Top Picks Tidak Ada");
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void checkIsNonCMPageExist() {
        assertTrue(isElementVisible("non_category_manager"), "Gagal Redirect ke Non CM Landing Page");
    }

    public void checkIsTrendingProductNonCMExist() {
        assertTrue(isElementVisible("trending_product_non_cm"), "Trending Product Tidak Ditemukan");
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void clickTrendingProductNonCM() {
        swipeDownToElement("trending_product_non_cm");
        tapElement("trending_product_list");
    }

    public void checkKesehatanLandingPage(String kategori) {
        switch (kategori) {
            case "Kesehatan":
                assertTrue(isElementVisible("kesehatan_category_title"), "Nama Kategori Landing Page tidak Ditemukan");
                break;
            case "Fashion Wanita":
                assertTrue(isElementVisible("fashion_wanita_category_title"), "Nama Kategori Landing Page tidak Ditemukan");
                HelperData.setLastActionPage(new CategoryPage(iosDriver));
                break;
            default:
                break;
        }
    }

    public void isRedirectToNonCMPage() {
        waitFor(5);
        checkIsTrendingProductNonCMExist();
    }

    public void backFromCategoryLandingPage() {
        if (isElementVisible("back_category_alchemy")) {
            tapElement("back_category_alchemy");
        }
    }

    public void isRedirectToCMPage() {
        waitFor(5);
        checkBukaMallOnCM();
        checkTopPicksOnCM();
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void goToPopularSection() {
        swipeDownToElement("title_popular_section");
    }

    public void checkAmountOfPopularSection() {
        assertTrue(isElementVisible("product_name_popular"), "Product Popular Section is Exist");
        swipeDownToElement("product_name_popular");
    }

    public void clickPopularCardProduct() {
        CategoryData.setProductNamePopular(getElementValue("product_name_popular"));
        String productName = CategoryData.setProductNamePopular(getElementValue("product_name_popular"));
        LogUtil.info("PRODUCT NAME1" + productName);
        tapElement(constructLocator("category_page_product_name", productName));
    }

    public void verifyProductRecommendation() {
        swipeDownToElement("product_recommendation_card");
        assertTrue(isElementVisible("product_recommendation_card"), "Product doesnt exist");
    }

    public void clickPorductRecommendationCard() {
        CategoryData.setProductNameRecommendation(getElementValue("product_name_recommendation"));
        tapElement("product_name_recommendation");
    }

    public void goToRecommendationSection() {
        swipeDownToElement("rekomendasi_untukmu_title");
    }

    public void tapOnCategoryOrProduct(String label) {
        swipeUpToElement(constructLocator("category_page_product_name", label));
        tapElement(constructLocator("category_page_product_name", label));
    }

    public void validateOnEvoucherCategoryPage() {
        validateDisplayed("category_page_e_voucher_and_ticket");
    }

    public void verifyOnCategoryLandingPage() {
        validateDisplayed("category_page_navbar_title");
        validateDisplayed("alchemy_navbar_back_button");
        validateDisplayed("category_page_category_list");

        assertEquals(
                SearchData.getSearchKeywords(), getElementValue("category_page_navbar_title"),
                "\"" + SearchData.getSearchKeywords() + "\" is not equal \"" + getElementValue("category_page_navbar_title") + "\""
        );

        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void verifyOnProductTerlarisPage() {
        waitForVisibilityOf("produk_terlaris_title", 5);
        verifyElementExist("button_beli_terlaris");
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void scrollToTerlarisCategoryListing() {
        swipeUpToElement("section_terlaris_listing");
        swipeUpToElement("lihat_semua_terlaris_category");
    }

    public void verifyIsRedirectToCategoryPage() {
        doPullRefresh(1);
        swipeDownToElement("banner_category");
        waitForVisibilityOf("banner_category", 10);
    }

    public void scrollToRekomendasiCategory() {
        swipeUpToElement("rekomendasi_category_title_section");
        swipeUpToElement("product_price");
    }

    public void clickSubCategoryOnCategoryLanding() {
        CategoryData.setCategoryName(getTextFromElement("subcategory_name"));
        tapElement("subcategory_section");
        if (isElementExist("pilih_kategori_title")) {
            tapElement("pilih_subcategory");
        }
    }

    public void clickSpecificSubCategory(String subcategoryName) {
        CategoryData.setCategoryName(subcategoryName);
        tapElement(constructLocator("subcategory_title", subcategoryName));
        if (isElementExist("pilih_kategori_title")) {
            tapElement("pilih_subcategory");
        }
    }

    public void verifyPopularProductCategory() {
        swipeUpToElement("popular_category_title");
        verifyElementExist("ganti_barang_button");
    }

    public void clickSubCategoryLihatSemua() {
        if (isElementVisible("lihat_selengkapnya_category")) {
            tapElement("lihat_selengkapnya_category");
            waitForVisibilityOf("sembunyikan_category", 3);
            tapElement("sembunyikan_category");
        } else {
            LogUtil.info("All Category have been shown");
        }
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void clickCategoryImage() {
        CategoryData.setCategoryName(getTextFromElement("category_icon_name"));
        tapElement("category_icon_image");
    }

    public void verifyCategoryNameLandingPage() {
        validateValue().equals(CategoryData.getCategoryName(), getTextFromElement("category_landing_page_title"));
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void clickSearchFieldCategoryNavigation() {
        tapElement("search_field_icon");
    }

    public void verifyCategorySearchedIsShowed() {
        verifyElementExist(constructLocator("category_search_name_result", CategoryData.getCategoryName()));
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void searchKeywordCategory(String categoryName) {
        CategoryData.setCategoryName(categoryName);
        typeAndEnterValue("search_field_category", categoryName);
    }

    public void verifyOnCategoryNavigationPage() {
        verifyElementExist("categories_page_navbar");
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void scrollToBrandSection() {
        swipeUp();
        swipeUpToElement("popular_brand_product");
    }

    public void verifyPopularBrandProducts() {
        verifyElementExist("popular_brand_product");
    }

    public void swipeLeftOnPupularBrandproduct() {
        int maxSwipe = 4;
        int loop;

        for (loop = 0; loop <= maxSwipe; loop++) {
            nativeSwipeLeft("popular_brand_product");
            maxSwipe--;
        }
    }

    public void verifyLihatSemuaPopularBrand() {
        swipeUpToElement("popular_brand_lihat_semua");
        verifyElementExist("popular_brand_lihat_semua");
    }

    public void verifyCategoryKewordSuggestion() {
        DiscoveryData.setHomeKeywordSuggestion(getTextFromElement("category_tab_search_field").replace("Cari ", "").toLowerCase());
        verifyElementExist("category_tab_search_field");
        validateValue().notEquals(DiscoveryData.getCategoryKeywordSuggestion(), DiscoveryData.getHomeKeywordSuggestion());
    }

    public void isOnCategoryTabScreen() {
        verifyElementExist("category_popular_section");
    }

    public void typeCategoryKeywordSuggestion() {
        typeAndEnterValue("category_tab_search_field", "");
    }

    public void validateCategoryRecoTitle() {
        verifyElementExist("reco_product_title");
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void validateCategoryRecoSection(String productInfo) {
        switch (productInfo) {
            case "Product Name":
                verifyElementExist("reco_product_name");
                break;
            case "Product Price":
                verifyElementExist("reco_product_price");
                break;
            case "Free Shipping label":
                verifyElementExist("reco_product_free_shipping");
                break;
            case "Discount label":
                verifyElementExist("reco_product_discount");
                break;
            case "Product Rating":
                verifyElementExist("reco_product_ratting_product");
                break;
            case "Sold Product Information":
                verifyElementExist("reco_product_sold_product");
                break;
            case "Super Seller Icon":
                verifyElementExist("reco_product_super_seller_bukamall_icon");
                break;
            case "Bukamall Icon":
                verifyElementExist("reco_product_super_seller_bukamall_icon");
                break;
            default:
                Assert.fail("Need valid product information");
        }
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void validateProductOnCategoryRecoSection() {
        int swapCount = 0;
        while (swapCount < 10) {
            ArrayList<String> listProduct = CategoryData.getRecoProductList();
            int recoProduct = getMultipleElement().withLocator("reco_product_name").size();

            for (int i = 0; i < recoProduct; i++) {
                String productName = getText("reco_product_name", i);
                if (listProduct.size() < 4) {
                    CategoryData.setRecoProductList(productName);
                } else {
                    setProductList(listProduct, productName);
                }
            }
            swipeLeftAtSpecifiedLocator("reco_product_section");
            swapCount++;
        }

        int recoProduct = CategoryData.getRecoProductList().size();
        validateValue().equalsTrue(recoProduct <= 18 && recoProduct >= 6);
    }

    private void setProductList(ArrayList<String> list, String product) {
        for (String productOnList : list) {
            if (productOnList.equals(product)) {
                CategoryData.getRecoProductList().remove(productOnList);
                break;
            }
        }
        CategoryData.setRecoProductList(product);
    }

    public void tapProductOnCategoryRecoSection() {
        tapElement("reco_product_name");
    }

    public void tapOnLihatSemuaTerlarisCategory() {
        waitForVisibilityOf("lihat_semua_terlaris_category", 3);
        tapElement("lihat_semua_terlaris_category");
    }

    public void tapOnProductTerlarisCategory() {
        waitForVisibilityOf("produk_terlaris_listing", 3);
        tapElement("produk_terlaris_listing");
    }

    public void validatePopularProducts() {
        swipeUpToElement("popular_section_icon_super_seller");
        verifyElementExist("popular_section_product_name");
        verifyElementExist("popular_section_rating");
        swipeUpToElement("popular_section_icon_gratis_ongkir");
        verifyElementExist("popular_section_number_of_sold_product");
        verifyElementExist("popular_section_icon_super_seller");
    }

    public void validateNoOngkirInProductCard() {
        swipeUpToElement("popular_section_icon_gratis_ongkir");
        verifyElementExist("popular_section_icon_gratis_ongkir");
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }

    public void validateNoOngkirProductReco() {
        swipeUpToElement("reco_product_icon_gratis_ongkir");
        verifyElementExist("reco_product_icon_gratis_ongkir");
        HelperData.setLastActionPage(new CategoryPage(iosDriver));
    }
}
