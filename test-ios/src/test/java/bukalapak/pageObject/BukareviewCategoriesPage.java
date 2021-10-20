package bukalapak.pageObject;

import bukalapak.data.CeoData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukareviewCategoriesPage extends BasePage {
    
    public BukareviewCategoriesPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukareviewCategoriesPage() {
        verifyElementExist("bukareview_page_title");
        verifyElementExist("bukareview_search_icon");
        verifyElementExist("bukareview_all_button");
        verifyElementExist("bukareview_subcategories_text");
        HelperData.setLastActionPage(new BukareviewCategoriesPage(iosDriver));
    }

    public void verifySubCategoryDisplay() {
        assertTrue(isElementVisible("bukareview_subcategories_text"), "Bukareview gadget categories not display");
        assertTrue(isElementVisible("bukareview_subcategories_button"), "Bukareview categories button not display");
    }

    public void tapSubCategoryButton() {
        CeoData.setSubCategoryTitle(getText("bukareview_subcategories_button", 0));
        tapElement("bukareview_subcategories_button", 0);
    }

    public void verifyGadgetReviewDisplay() {
        swipeDownToElement("bukareview_gadget_rev_disp", 3);
        assertTrue(isElementVisible("bukareview_categories_gadget_review_text"), "Bukareview categories gadget review text not display");
        assertTrue(isElementVisible("bukareview_categories_gadget_review_view_more"), "Bukareview categories gadget view more text not display");
        assertTrue(isElementVisible("bukareview_categories_gadget_review_article"), "Bukareview categories gadget review article not display");
    }

    public void tapOneArticleGadgetReview() {
        CeoData.setTitleArticle(getElementValue("bukareview_categories_gadget_review_article"));
        tapElement("bukareview_categories_gadget_review_article");
    }

    public void verifyGadgetTipsDisplay() {
        swipeDownToElement("bukareview_categories_gadget_tips_text", 10);
        assertTrue(isElementVisible("bukareview_categories_gadget_tips_text"), "Gadget tips section is not display");
        assertTrue(isElementVisible("bukareview_categories_gadget_tips_view_more"), "View more in gadget tips section is not display");
    }

    public void verifyGadgetRecommendationsDisplay() {
        swipeDownToElement("bukareview_categories_gadget_recommendation_text", 5);
        assertTrue(isElementVisible("bukareview_categories_gadget_recommendation_text"), "Gadget recommendation section is not display");
        assertTrue(isElementVisible("bukareview_categories_gadget_recommendation_view_more"), "View more in gadget recommendation not display");
    }

    public void verifyGadgetHowToDisplay() {
        swipeDownToElement("bukareview_categories_gadget_how_to_text", 5);
        assertTrue(isElementVisible("bukareview_categories_gadget_how_to_text"), "Gadget how to section is not display");
        assertTrue(isElementVisible("bukareview_categories_gadget_how_to_view_more"), "View more in gadget how to not display");
    }

    public void verifyGadgetInfoDisplay() {
        swipeDownToElement("bukareview_categories_gadget_info_text", 5);
        assertTrue(isElementVisible("bukareview_categories_gadget_info_text"), "Gadget info section is not display");
        assertTrue(isElementVisible("bukareview_categories_gadget_info_view_more"), "View more in gadget info not display");
    }

    public void verifyGadgetInfographicDisplay() {
        swipeDownToElement("bukareview_categories_gadget_infographic_text", 5);
        assertTrue(isElementVisible("bukareview_categories_gadget_infographic_text"), "Gadget infographic section is not display");
        assertTrue(isElementVisible("bukareview_categories_gadget_infographic_view_more"), "View more in gadget infographic not display");
    }
}
