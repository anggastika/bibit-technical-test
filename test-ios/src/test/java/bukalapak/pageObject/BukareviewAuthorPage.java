package bukalapak.pageObject;

import bukalapak.data.CeoData;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukareviewAuthorPage extends BasePage {

    public BukareviewAuthorPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnAuthorPage() {
        assertTrue(isElementVisible("bukareview_page_title"), "bukareview page title not display");
        assertTrue(isElementVisible("bukareview_search_icon"), "bukareview search icon not display");
        assertTrue(isElementVisible("bukareview_author_image", 20), "bukareview author image not display");
    }

    public void verifyAuthorRole() {
        assertTrue(isElementVisible("bukareview_author_role", 3), "Author role not display");
    }

    public void verifyAuthorName() {
        assertTrue(isElementVisible("bukareview_author_name", 5), "Author name not display");
        assertTrue(getElementValue("bukareview_author_name").equalsIgnoreCase(CeoData.getAuthorName()), "Author name not same as expected");
    }

    public void verifyAuthorDesc() {
        assertTrue(isElementVisible("bukareview_author_desciption", 3), "Author description not display");
    }

    public void verifyAuthorAmountArticle() {
        assertTrue(isElementVisible("bukareview_author_amount_article", 3), "Author amount of article not display");
    }

    public void verifyListArticle() {
        assertTrue(isElementVisible("author_article_title", 15), "Article title not display in author page");
        assertTrue(isElementVisible("author_article_image", 5), "Article image not display in author page");
        assertTrue(isElementVisible("author_article_author", 5), "Article author not display in author page");
    }

    public void tapArticleTitle() {
        IOSElement titleList = (IOSElement) getElementsPresent("author_article_title").get(0);
        assertTrue(titleList.isDisplayed(), "Article title not display");
        CeoData.setTitleArticle(titleList.getText());
        titleList.click();
    }

    public void verifyAuthorSectionAfterScroll() {
        swipeToDirection(Direction.DOWN);
        swipeToDirection(Direction.DOWN);
        // because visibility element is false so need time for wait element
        waitFor(1);
        assertTrue(isElementVisible("author_name"), "Author name not display after scroll down page");
        assertTrue(isElementVisible("author_role"), "Author name not display after scroll down page");
    }
}
