package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class FaqPage extends BasePage {

    public FaqPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnFaqPage() {
        waitForVisibilityOf("faq_title_page", 10);
        waitForVisibilityOf("faq_question_title", 10);
        String faqTitle = getElementValue("faq_title_page");
        validateValue().contains("Tanya Jawab", faqTitle);
        changeContext().toWebview();
        verifyElementExist("faq_search_textfield");
        HelperData.setLastActionPage(new FaqPage(iosDriver));
    }

    public void searchArticle(String keywords) {
        IOSElement searchField = iosDriver.findElement(getLocator("faq_search_textfield"));
        searchField.sendKeys(keywords + "\n");
    }

    public void validateAutoSuggest() {
        verifyElementExist("faq_auto_suggest_item");
    }

    public void tapAutoSuggest() {
        tapElement("faq_auto_suggest_item", 0);
    }

    public void validateArticle(String keywords) {
        // handle while searching from non webview
        if (iosDriver.getContext().contains("NATIVE_APP")) changeContext().toWebview();
        verifyElementExist(constructLocator("faq_article_title", keywords), 10, "element not locatedd after 10s");
    }

    public void tapOnCategory(String category) {
        tapElement(constructLocator("faq_category_text", category));
    }

    public void validateCategoryPage(String category) {
        verifyElementExist(constructLocator("faq_category_title", category), 10, "category page not shown after 10s");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
