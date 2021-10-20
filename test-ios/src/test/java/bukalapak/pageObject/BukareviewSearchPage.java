package bukalapak.pageObject;

import bukalapak.data.CeoData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukareviewSearchPage extends BasePage {

    public BukareviewSearchPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnBukareviewSearchPage() {
        verifyElementExist("bukareview_page_title");
        verifyElementExist("bukareview_search_icon");
        assertTrue(getElementValue("bukareview_search_result_text").contains("pencarian"), "Search result text not display");
        assertTrue(isElementVisible("bukareview_search_article_title", 15), "Article title not display");
        HelperData.setLastActionPage(new BukareviewSearchPage(iosDriver));
    }

    public void verifyResultSeachArticledisplay() {
        assertTrue(getElementValue("bukareview_search_article_title").contains(CeoData.getSearchResultArticle()), "Article search result not same");
    }

    public void verifyImageArticleDisplay() {
        assertTrue(isElementVisible("bukareview_search_image_article", 10), "Image article not display in search result");
    }

    public void verifyAuthorArticleDisplay() {
        assertTrue(isElementVisible("bukareview_search_article_author"), "Author article not display");
    }

    public void tapOneOfArticleSearchResult() {
        CeoData.setTitleArticle(getElementValue("bukareview_search_article_title"));
        tapElement("bukareview_search_article_title");
    }
}
