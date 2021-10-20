package bukalapak.pageObject;

import bukalapak.data.CeoData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaReviewTagTypeSubCategoryPage extends BasePage {

    public BukaReviewTagTypeSubCategoryPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnBukaReviewTagTypeSubCategoryPage() {
        assertTrue(isElementVisible("bukareview_tag_article_image_list", 20), "Article image not display in tag/type/subcategory page");
        assertTrue(isElementVisible("bukareview_tag_article_title_list", 20), "Article title not display in tag/type/subcategory page");
        assertTrue(isElementVisible("bukareview_tag_article_author_list", 20), "Article author not display in tag/type/subcategory page");
    }

    public void verifyTitleTagDisplayAsExpected() {
        assertTrue(isElementVisible("bukareview_tag_title", 20));
        assertTrue(getTextFromElement("bukareview_tag_title").equalsIgnoreCase(CeoData.getTagTitle()), "Tag title not same as expected");
    }

    public void verifyTitleTypeDisplayAsExpected() {
        assertTrue(isElementVisible("bukareview_tag_title", 20));
        assertTrue(getTextFromElement("bukareview_tag_title").equalsIgnoreCase(CeoData.getTypeTitle()), "Type title not same as expected");
    }

    public void verifyTitleSubCategoryDisplayAsExpected() {
        assertTrue(isElementVisible("bukareview_tag_title", 20));
        assertTrue(getTextFromElement("bukareview_tag_title").equalsIgnoreCase(CeoData.getSubCategoryTitle()), "Type title not same as expected");
    }

    public void tapOneOfArticle() {
        IOSElement listTitle = getElements("bukareview_tag_article_title_list").get(0);
        CeoData.setTitleArticle(listTitle.getText());
        listTitle.click();
    }
}
