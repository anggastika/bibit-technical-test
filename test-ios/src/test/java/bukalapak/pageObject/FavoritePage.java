package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.MtxData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class FavoritePage extends BasePage {
    public FavoritePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void checkRecommendationPanelAppear() {
        waitForVisibilityOf("favorite_title_text", 6);
        swipeDownToElement("favorite_reco_panel", 7);
        assertTrue(isElementVisible("favorite_reco_panel"), "Recommendation panel is not appear");
    }

    public void checkLogoutStateOfFavoritePage() {
        waitForVisibilityOf("favorite_login", 5);
        assertTrue(isElementVisible("favorite_no_item_text"), "Favorite page should be in Logout state");
    }

    public void clearSearchonFavoritePage() {
        //clear product search for handle stuck product search on favorite list
        clickOnFavoriteSearchField();
        tapElement("favorite_search_box");
        getElement("favorite_search_box").clear();
        tapElement("search_keyboard");
        HelperData.setLastActionPage(new FavoritePage(iosDriver));
    }

    public void checkProductNameIsExist() {
        if (isElementVisible("favorite_product_card", 30)) {
            verifyElementExist("favorite_page_elm_productName1");
        } else {
            verifyElementExist("favorite_page_elm_productName2");
        }
        clearSearchonFavoritePage();
        HelperData.setLastActionPage(new FavoritePage(iosDriver));
    }

    public void checkProductNameIsNotExist() {
        clickOnFavoriteSearchField();
        if (isElementVisible("favorite_page_elm_productName1", 30)) {
            tapFavoriteIcon();
            verifyElementNotExist("favorite_page_elm_productName1");
        }
        else if (isElementVisible("favorite_page_elm_productName2",30)){
            tapFavoriteIcon();
            verifyElementNotExist("favorite_page_elm_productName2");
        }
        clearSearchonFavoritePage();
        HelperData.setLastActionPage(new FavoritePage(iosDriver));
    }

    public void verifyIsOnFavoritePage() {
        waitForVisibilityOf("favorite_title_text", 15);
        HelperData.setLastActionPage(new FavoritePage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapProductName(String product) {
        waitForVisibilityOf(product, 30);
        tapElement(product);
    }

    public void tapFavoriteIcon() {
        if (isElementVisible("favorite_icon_list_revamp", 30)) {
            tapElement("favorite_icon_list_revamp");
        } else if (isElementVisible("favorite_icon_list", 30)) {
            tapElement("favorite_icon_list");
        }
    }

    public void searchProductOnFavorite(String keyword) {
        if (isElementVisible("favorite_search_box", 3)) {
            clearSearchonFavoritePage();
        } else {
            LogUtil.info("Search keyword doesn't appear");
        }
        waitForVisibilityOf("favorite_search_box", 30);
        clickOnFavoriteSearchField();
        typeValue("favorite_search_box", keyword);
        tapElement("search_keyboard");
        HelperData.setLastActionPage(new FavoritePage(iosDriver));
    }

    public void clickOnFavoriteSearchField() {
        waitForVisibilityOf("favorite_search_box", 30);
        tapElement("favorite_search_box");
    }

    public void filterProductByKeyword(String keywordType) {
        if (isElementVisible("favorite_clear_search_button", 3)) {
            clearSearchonFavoritePage();
        } else {
            LogUtil.info("Search keyword doesn't appear");
        }
        verifyElementExist("favorite_filter_button");
        tapElement("favorite_filter_button");
        switch (keywordType) {
            case "Sudah Dibeli":
                verifyElementExist("favorite_filter_option_sudah_dibeli");
                tapElement("favorite_filter_option_sudah_dibeli");
                break;
            case "Belum Dibeli":
                verifyElementExist("favorite_filter_option_belum_dibeli");
                tapElement("favorite_filter_option_belum_dibeli");
                break;
            case "Lagi Diskon":
                verifyElementExist("favorite_filter_option_lagi_diskon");
                tapElement("favorite_filter_option_lagi_diskon");
                break;
            case "Semua":
                verifyElementExist("favorite_filter_option_semua");
                tapElement("favorite_filter_option_semua");
                break;
            default:
                LogUtil.info("Sorry, you made an invalid choice.");
                break;
        }
    }

    public void verifyFilterLabel(String keywordType) {
        switch (keywordType) {
            case "Sudah Dibeli":
                waitForVisibilityOf("favorite_empty_state", 30);
                break;
            case "Belum Dibeli":
                waitForVisibilityOf("favorite_label_keyword_belum_dibeli", 30);
                break;
            case "Lagi Diskon":
                waitForVisibilityOf("favorite_label_keyword_lagi_diskon", 30);
                break;
            case "Semua":
                verifyElementNotExist("favorite_empty_state");
                break;
            default:
                LogUtil.info("Sorry, you made an invalid choice.");
                break;
        }
        HelperData.setLastActionPage(new FavoritePage(iosDriver));
    }

    public void tapFavoritMenu(){
        waitForVisibilityOf("favorite_menu", 30);
        tapElement("favorite_menu");
    }

    public void verifyPage(){
        waitForVisibilityOf("asked_to_login",10);
        verifyElementExist("title_empty_state");
        verifyElementExist("asked_to_login");
    }

    public void clickButtonLogin(){
        if(isElementExist("button_login_page",10)) {
            waitForVisibilityOf("button_login_page");
            tapElement("button_login_page");
        }else if (isElementExist("button_login_asked", 10)){
            waitForVisibilityOf("button_login_asked",10);
            tapElement("button_login_asked");
        }else{
            waitForVisibilityOf("click_login", 10);
            tapElement("click_login");
        }
    }

    public void assertProductFavorite(){
        if (isElementVisible("product_favorit", 40)){
            validateDisplayed("product_favorit");
            swipeDownToElement("title_reco");
        }else {
            validateDisplayed("empty_favorite");
            swipeDownToElement("title_reco");
        }
    }

    public void assertSectionReco(){
        nativeSwipeUpToElement("title_reco");
        validateDisplayed("title_reco");
        swipeUpToElement("product_reco");
        validateDisplayed("product_reco");
    }

    public void validateOutOfStockLabel(){
        verifyElementExist("favorite_out_of_stock_label",60,"out of stock label not displayed");
    }

    public void validateOutOfStockRevampLabel() {
        verifyElementExist("favorite_out_of_stock_revamp_label", 60, "out of stock label not displayed");
    }

    public void validateBuyButtonDisable(){
        verifyElementExist("favorite_buy_button");
        assertTrue(!isElementClickable("favorite_buy_button"));
    }

    public void validateBuyButtonNotExist() {
        verifyElementNotExist("favorite_buy_button");
    }

    public void validateFavouriteProductExist() {
        verifyElementExist("favorite_product_name");
    }

    public void tapFavouriteProductExist() {
        tapElement("favorite_product_name");
    }

    public void validateRecommendationGrid(){
        swipeUpToElement("title_reco");
        if (!isElementVisible("product_reco_grid_row", 5)){
            swipeUpToElement("product_reco_grid_row");
        }
        verifyElementExist("product_reco_grid_row", 20, "product recommendation is not found");
    }

    public void clickUpButton(){
        swipeDownToElement("title_reco", 3);
        verifyElementExist("button_up", 20, "button up is not found");
        tapElement("button_up");
    }

    public void tapButtonTambah(){
        swipeDownToClickableElement("button_tambah");
        verifyElementExist("button_tambah", 10, "button beli is not found");
        tapElement("button_tambah");
    }

    public void validatePopUpAtc(){
        verifyElementExist("pop_up_atc", 10, "pop up atc not showing");
        tapElement("close_pop_up_atc");
    }

    public void tapProductRecommendation(){
        verifyElementExist("product_reco_name", 20, "product recommendation not found");
        tapElement("product_reco_name");
    }

    public void checkTitleText(){
        swipeDownToElement("product_favorit", 5);
        verifyElementExist("product_favorit", 10, "product favorite not found");
    }

    public void validateRecommendationNotExist(){
        verifyElementNotExist("product_reco_grid_row");
    }

    public void validateGratisOngkirIcon() {
        verifyElementExist("gratis_ongkir_icon");
        verifyElementExist("gratis_ongkir_label");
    }

    public void validateNewlyAddedProduct() {
        doPullRefresh(2);
        String productNameExpected = MtxData.getProductName().replaceAll("\n" ,"");
        String productNameActual = getElementValue("favorite_first_product_card").replaceAll("\n", "");
        assertEquals(productNameExpected, productNameActual , "product failed to favorite");
        HelperData.setLastActionPage(new FavoritePage(iosDriver));
    }

    public void validateSectionRecoCarousel() {
        swipeUpToElement("title_reco_carousel", 10);
        verifyElementExist("title_reco_carousel");
        swipeUpToElement("section_reco_carousel", 1);
        verifyElementExist("section_reco_carousel");
    }

    public void swipeSectionRecoCarousel(String swipe) {
        switch (swipe) {
            case "right":
                swipeRightAtSpecifiedLocator("image_reco_carousel");
                break;
            case "left":
                swipeLeftAtSpecifiedLocator("image_reco_carousel");
                break;
            default:
                LogUtil.info("Swipe argument not found on Recommendation Carousel");
                break;
        }
    }

    public void validateProductCardRecoCarousel(String product_card) {
        swipeUpToElement("price_reco_carousel", 3);
        switch (product_card) {
            case "image":
                verifyElementExist("image_reco_carousel");
                break;
            case "description":
                verifyElementExist("label_reco_carousel");
                break;
            case "price":
                verifyElementExist("price_reco_carousel");
                break;
            default:
                LogUtil.info("Product Card argument not found on Recommendation Carousel");
                break;
        }
    }

    public void tapProductRecoCarousel() {
        verifyElementExist("first_product_reco_carousel", 10, "product recommendation not found");
        tapElement("first_product_reco_carousel");
    }
}
