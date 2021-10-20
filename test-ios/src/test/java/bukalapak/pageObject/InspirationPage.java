package bukalapak.pageObject;

import bukalapak.data.APIData;
import bukalapak.data.HelperData;
import bukalapak.data.InspirationData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

/**
 * Created by Prasetyo Nugroho 2018
 */
public class InspirationPage extends BasePage {

    public InspirationPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnInspirationPage() {
        verifyElementExist("inspiration_navbar_title");
        verifyElementExist("inspiration_name_text");
        HelperData.setLastActionPage(new InspirationPage(iosDriver));
    }

    public void clickInspirationNameAtIndex(int index) {
        List<WebElement> elements = getElements("inspiration_name_text", 5);
        // first post inspiration in list start from index 0
        elements.get(index - 1).click();
    }

    public void likeAtPost(int index) {
        List<WebElement> elements = getElements("inspiration_like_icon", 5);
        elements.get(index - 1).click();
        waitFor(2);
    }

    /**
     * To POST an inspiration based on how much list of datas on DataTable
     *
     * @param arg0 contains : TYPE, INFLUENCER, TITLE
     */
    public void publishAnInspiration(DataTable arg0) {
        List<Map<String, String>> inspiration = arg0.asMaps();

        for (int i = 0; i < inspiration.size(); i++) {
            API_CALL.postCustomInspiration("53", APIData.getAccessTokenExclusiveStaging(), inspiration.get(i).get("TYPE"), inspiration.get(i).get("INFLUENCER"), inspiration.get(i).get("TITLE"));
        }
    }

    public void clickTagDetail(Integer tagIndex) {
        List<WebElement> tagsDetail = getElements("inspiration_tag_detail_icon", 6);

        waitFor(3);
        tagsDetail.get(tagIndex - 1).click();
        waitFor(3);
    }

    public void checkExpandableButton() {
        tapElement("inspiration_expandable_button");
        swipeDownToElement("sembunyikan_text");
        assertTrue(isElementVisible("sembunyikan_text"));
        tapElement("inspiration_expandable_button");
        swipeUpToElement("selengkapnya_text");
        assertTrue(isElementVisible("selengkapnya_text"));
    }

    public void filteringByCategoryInspirationPost() {
        tapElement("inspiration_navbar_filter_text");
        List<WebElement> categoriesElement = getElements("inspiration_categories_radiobutton", 6);
        // click category Elektronik
        categoriesElement.get(1).click();
    }

    public void checkInspirationPostFilterdByCategory() {
        swipeDownToElement("inspiration_post_title_electronic_1_text");
        assertTrue(isElementVisible("inspiration_post_title_electronic_1_text"), "Specific post related to category is not exist");
    }

    public void checkSortingInspirationPost() {
        waitFor(3);
        List<WebElement> totalLikeElement = getElements("inspiration_total_like_text", 6);

        Double totaLikeFirstPost = Double.parseDouble(totalLikeElement.get(0).getText().replace("k", "")) * 1000;
        Double totalLikeSecondPost = Double.parseDouble(totalLikeElement.get(1).getText().replace("k", "")) * 1000;

        assertTrue(totaLikeFirstPost >= totalLikeSecondPost, "Total like first posts is not greater than second posts");
    }

    public void getTotalLikeCountOnSpecificPost(Integer index) {
        List<WebElement> totalLikeElement = getElements("inspiration_total_like_text", 6);

        InspirationData.setTotalLikePost(index, Double.parseDouble(totalLikeElement.get(index - 1).getText().replace("k", "")) * 1000);
    }

    public void checkTotalLikeCount(Integer index, String status) {
        List<WebElement> totalLikeElement = getElements("inspiration_total_like_text", 6);

        Double totaLikePost = Double.parseDouble(totalLikeElement.get(index - 1).getText().replace("k", "")) * 1000;

        switch (status) {
            case "increases":
                assertTrue(totaLikePost >= InspirationData.getTotalLikes().get(index - 1), "Total like is not increases");
                break;
            case "decreases":
                assertTrue(totaLikePost <= InspirationData.getTotalLikes().get(index - 1), "Total like is not decreases");
                break;
            default:
                // default is equals
                assertEquals(InspirationData.getTotalLikes().get(index - 1), totaLikePost, "Total like is not equals");
        }
    }

    public void checkRelatedPageToInspirationPost() {
        if (isElementVisible("berhasil_masuk_product_detail")) {
            // product_details
            new ProductListingPage(iosDriver).checkIsOnProductDetail();
        } else {
            // brandpage
            new BukamallBrandPage(iosDriver).userOnBukamallBrandPage();
        }
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
