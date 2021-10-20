package bukalapak.pageObject;

import bukalapak.data.CeoData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukareviewArticleDetailPage extends BasePage {

    public BukareviewArticleDetailPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnBukaReviewArticleDetail() {
        verifyElementExist("bukareview_page_title");
        verifyElementExist("bukareview_search_icon");
        verifyElementExist("bukareview_article_title");
        HelperData.setLastActionPage(new BukareviewArticleDetailPage(iosDriver));
    }

    public void verifyArticleTitleDisplayAsExpected() {
        assertTrue(isElementVisible("bukareview_article_title", 15));
        String titleList = getText("bukareview_article_title", 0);
        assertEquals(CeoData.getTitleArticle(), titleList,"The article title not display as expected");
    }

    public void verifyAuthorDisplay() {
        assertTrue(isElementVisible("bukareview_article_author", 5), "Author name not display");
    }

    public void verifyCategoryDisplay() {
        assertTrue(isElementVisible("bukareview_article_category", 5), "Category not display in article detail");
    }

    public void verifyArticleImageDisplay() {
        assertTrue(isElementVisible("bukareview_article_image", 10), "Article image no display");
    }

    public void verifyRecommendationProductDisplay() {
        assertTrue(isElementVisible("bukareview_article_recommendation_product_text"), "Product recommendation text not display");
    }

    public void verifyTagDisplay() {
        swipeDownToElement("bukareview_article_tag_button_list", 40);
        assertTrue(isElementVisible("bukareview_article_tag_button_list", 5), "Tag not display in article detail");
    }

    public void tapTagsButton() {
        CeoData.setTagTitle(getText("bukareview_article_tag_button_list", 0));
        tapElement("bukareview_article_tag_button_list", 0);
    }

    public void verifyReviewRecommendationDisplay() {
        swipeToLocator("bukareview_article_review_recommendation_text");
        assertTrue(isElementVisible("bukareview_article_review_recommendation_text"), "Review recommendation nit display");
    }

    // For article detail indepth
    public void verifyProductTitleAndPriceDisplay() {
        assertTrue(isElementVisible("bukareview_article_indepth_product_title"), "Name of product article indepth not display");
        assertTrue(isElementVisible("bukareview_article_indepth_product_price"), "Price of product article indepth not display");
    }

    public void verifyRatingStarDisplay() {
        assertTrue(isElementVisible("bukareview_article_indepth_rating_star_text"), "Rating star text not display in article indepth");
        assertTrue(isElementVisible("bukareview_article_indepth_rating_star_icon"), "Rating star icon not display in article indepth");
        assertTrue(isElementVisible("bukareview_article_indepth_dari_penulis_text"), "dari penulis text not  display in article indepth");
    }

    public void verifyRatingDetailDisplay() {
        assertTrue(isElementVisible("bukareview_article_indepth_rating_detail_title"), "Rating detail text not display in article indepth");
        assertTrue(isElementVisible("bukareview_article_indepth_rating_detail_bar"), "Rating detail progress bar not display in article indepth");
        assertTrue(isElementVisible("bukareview_article_indepth_rating_detail_rate"), "Rating detail rate not display in article indepth");
    }

    public void tapLihatBarangButton() {
        swipeToLocator("bukareview_article_indepth_lihat_barang_button_list");
        tapElement("bukareview_article_indepth_lihat_barang_button_list");
        waitFor(2);
        tapBackButton();
        waitFor(2);
    }

    public void verifyProsDisplay() {
        swipeToLocator("bukareview_article_indepth_pros_text");
        assertTrue(isElementVisible("bukareview_article_indepth_pros_icon"), "Pros icon is not display");
        assertTrue(isElementVisible("bukareview_article_indepth_pros_text"), "Pros text is not display");
    }

    public void verifyContDisplay() {
        swipeToLocator("bukareview_article_indepth_cons_text");
        assertTrue(isElementVisible("bukareview_article_indepth_cons_icon"), "Cons icon is not display");
        assertTrue(isElementVisible("bukareview_article_indepth_cons_text"), "Cons text is not display");
    }

    // For article detail interactive
    public void verifyTagInteractiveDisplay() {
        assertTrue(isElementVisible("bukareview_article_interactive_tag_interactive"), "Tag interactive not display");
    }

    public void tapTagInteractiveIcon() {
        tapElement("bukareview_article_interactive_tag_interactive");
    }

    public void verifyProductListInteractiveDisplay() {
        assertTrue(isElementVisible("bukareview_article_interactive_product_tag_list"), "Product interactive not display");
    }

    public void tapProductListInteractive() {
        tapElement("bukareview_article_interactive_product_tag_list", 0);
    }

    //Product detail article interactive
    public void verifyProductDetailInteractiveDisplay() {
        assertTrue(isElementVisible("bukareview_article_interactive_detail_product_page_title", 3), "Detail product title page not display");
        assertTrue(isElementVisible("bukareview_article_interactive_detail_product_title", 3), "Title product not display");
        assertTrue(isElementVisible("bukareview_article_interactive_detail_product_main_image_list1", 5), "Image product not display");
        assertTrue(isElementVisible("bukareview_article_interactive_detail_product_image_list2", 3), "Image product list interactive not display");
        assertTrue(isElementVisible("bukareview_article_interactive_detail_product_price", 3), "Price product not display");
    }

    public void verifyLihatBarangButtonDisplay() {
        swipeToLocator("bukareview_article_interactive_detail_product_lihat_semua_button");
        assertTrue(isElementVisible("bukareview_article_interactive_detail_product_lihat_semua_button", 3), "Lihat Barang button is not display");
    }

    public void tapLihatBarangInteractiveButton() {
        tapElement("bukareview_article_interactive_detail_product_lihat_semua_button");
    }

    public void verifyProductDetailDisplay() {
        if (isElementVisible("bukareview_article_interactive_pop_up_ok_product_detail", 20)) {
            tapElement("bukareview_article_interactive_pop_up_ok_product_detail");
        }
        assertTrue(isElementVisible("bukareview_product_detail_interactive", 20), "Product detail is not display");
    }
}
