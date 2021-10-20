package bukalapak.pageObject;

import bukalapak.data.CeoData;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class BukareviewHomePage extends BasePage {

    public BukareviewHomePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnBukareviewHomePage() {
        verifyElementExist("bukareview_page_title");
        verifyElementExist("bukareview_search_icon");
        verifyElementExist("bukareview_all_button");
        HelperData.setLastActionPage(new BukareviewHomePage(iosDriver));
    }

    public void enterSearchTextField(String articleSearch) {
        tapElement("bukareview_search_icon");
        waitFor(1);
        CeoData.setSearchResultArticle(articleSearch);
        typeAndEnterValueWithTimeOut("bukareview_search_text_field", articleSearch);
        waitFor(3);
    }

    public void tapGadgetCategory() {
        tapElement("bukareview_gadget_button");
    }

    public void verifyTopStoriesDisplay() {
        verifyElementExist("bukareview_home_top_stories");
        verifyElementExist("bukareview_home_top_stories_image");
        verifyElementExist("bukareview_home_top_stories_category_texte");
        verifyElementExist("bukareview_home_top_stories_article_title_text");
        verifyElementExist("bukareview_home_top_stories_article_author_text");
    }

    public void tapOneArticle() {
        CeoData.setTitleArticle(getElementValue("bukareview_home_top_stories_article_title_text"));
        tapElement("bukareview_home_top_stories_article_title_text");
    }

    public void tapAuthor() {
        CeoData.setAuthorName(getElementValue("bukareview_home_top_stories_article_author_text"));
        tapElement("bukareview_home_top_stories_article_author_text");
    }

    public void verifyLatestStoriesDisplay() {
        swipeDownToElement("bukareview_home_latest_stories_text", 5);
        verifyElementExist("bukareview_home_latest_stories_view_more");
    }

    public void tapLatestStoriesViewMore() {
        CeoData.setTypeTitle(getTextFromElement("bukareview_home_latest_stories_text"));
        tapElement("bukareview_home_latest_stories_view_more");
    }

    public void verifyTopReviewDisplay() {
        swipeDownToElement("bukareview_home_top_review_text", 5);
        verifyElementExist("bukareview_home_top_review_view_more");
    }

    public void verifyTopTipsDisplay() {
        swipeDownToElement("bukareview_home_top_tips_text", 10);
        verifyElementExist("bukareview_home_top_tips_view_more");
    }

    public void verifyTopRecommendationDisplay() {
        swipeDownToElement("bukareview_home_top_recommendation_text", 5);
        verifyElementExist("bukareview_home_top_recommendation_view_more");
    }

    public void verifyTopHowToDisplay() {
        swipeDownToElement("bukareview_home_top_how_to_text", 5);
        verifyElementExist("bukareview_home_top_how_to_view_more");
    }

    public void verifyTopInfoDisplay() {
        swipeDownToElement("bukareview_home_top_info_text", 5);
        verifyElementExist("bukareview_home_top_info_view_more");
    }

    public void footerBukaReviewLogoDisplay() {
        swipeDownToElement("bukareview_home_footer_bukareviewlogo", 5);
        swipeToDirection(Direction.DOWN);
    }

    public void verifySocialMediaFooterDisplay() {
        assertTrue(isElementVisible("bukareview_home_footer_fb_button"), "FB icon in footer not display");
        assertTrue(isElementVisible("bukareview_home_footer_twitter_button"), "Twitter icon in footer not display");
        assertTrue(isElementVisible("bukareview_home_footer_youtube_button"), "Youtube icon in footer not display");
        assertTrue(isElementVisible("bukareview_home_footer_ig_button"), "Instagram icon in footer not display");
        assertTrue(isElementVisible("bukareview_home_footer_linkedin_button"), "LinkedIn icon in footer not display");
    }
}
