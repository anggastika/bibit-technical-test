package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.RAGEData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import static bukalapak.TestInstrument.dotenv;

public class ProductReviewPage extends BasePage {

    public ProductReviewPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void filterImage(){
        waitForVisibilityOf("dengan_foto_filter");
        verifyElementExist("dengan_foto_filter");
        tapElement("dengan_foto_filter");
    }

    public void filterDesc(){
        waitForVisibilityOf("dengan_desc_filter");
        verifyElementExist("dengan_desc_filter");
        tapElement("dengan_desc_filter");
    }

    public void filterRating(){
        verifyElementExist("3star_filter");
        tapElement("3star_filter");
    }

    public void allFilter(){
        waitForVisibilityOf("semua_filter");
        verifyElementExist("semua_filter");
        tapElement("semua_filter");
    }

    public void verifyReviewFilterName(String name){
        switch (name) {
            case "list of all reviews":
                verifyElementExist("all_review_filtername");
                break;
            case "review with at least one image":
                verifyElementExist("with_image_filtername");
                break;
            case "review with description":
                verifyElementExist("with_image_and_desc_filtername");
                break;
            case "combined filtered review":
                verifyElementExist("combined_3_filtername");
                break;
            default:
                break;
        }
    }

    public void verifyReviewCount(){
        verifyElementExist("jumlah_ulasan");
    }

    public void selectFilterReview(String filterName) {
        switch (filterName) {
            case "5 Star":
                waitForVisibilityOf("5star_filter");
                tapElement("5star_filter");
                break;
            case "4 Star":
                waitForVisibilityOf("4star_filter");
                tapElement("4star_filter");
                break;
            case "3 Star":
                waitForVisibilityOf("3star_filter");
                tapElement("3star_filter");
                break;
            case "2 Star":
                waitForVisibilityOf("2star_filter");
                tapElement("2star_filter");
                break;
            case "1 Star":
                waitForVisibilityOf("1star_filter");
                tapElement("1star_filter");
                break;
            default:
                break;
        }
    }

    public void verifyContentReviewFilter(String expected) {
        verifyReviewCount();
        verifyElementExist("review_filter_name");
        switch (expected) {
            case "not see":
                waitForVisibilityOf("empty_state_review");
                verifyElementExist("empty_state_review");
                break;
            case "see":
                verifyElementExist("user_profile_image");
                verifyElementExist("review_username");
                verifyElementExist("user_rating");
                verifyElementExist("like_icon");
                break;
            default:
                break;
        }
    }

    public void clickOnReviewerName() {
        waitForVisibilityOf("reviewer_username",10);
        swipeUpToElement("reviewer_username",5);
        tapElement("reviewer_username");
    }

    public void userOnProductReviewPage() {
        waitForVisibilityOf("ulasan_barang_page_title", 10);
        verifyElementExist("ulasan_barang_page_title");
        verifyElementExist("review_product_name");
        verifyElementExist("total_star_rating");
        verifyElementExist("ulasan_header");
        verifyElementExist("review_username");
        verifyElementExist("user_profile_image");
        HelperData.setLastActionPage(new ProductReviewPage(iosDriver));
    }

    public void getCurrentLikes(){
        RAGEData.setCurrentLikes(getElementValue("like_count").replace("(","").replace(")",""));
    }

    public void getCurrentDislikes(){
        RAGEData.setCurrentDislikes(getElementValue("dislike_count").replace("(","").replace(")",""));
    }

    public void clickLikeIcon(){
        waitForVisibilityOf("like_icon",10);
        tapElement("like_icon");
        waitFor(3);
    }

    public void clickDislikeIcon(){
        waitForVisibilityOf("dislike_icon");
        verifyElementExist("dislike_icon");
        tapElement("dislike_icon");
        waitFor(3);
    }

    public void validateTotalLikesIsChanged(){
        int totalLikesAfter = Integer.parseInt(getElementValue("like_count").replace("(","").replace(")",""));
        Assert.assertNotEquals(Integer.parseInt(RAGEData.getCurrentLikes()), totalLikesAfter);
        HelperData.setLastActionPage(new ProductReviewPage(iosDriver));
    }

    public void validateTotalDislikesIsChanged(){
        int totalDislikesAfter = Integer.parseInt(getElementValue("dislike_count").replace("(","").replace(")",""));
        Assert.assertNotEquals(Integer.parseInt(RAGEData.getCurrentDislikes()), totalDislikesAfter);
        HelperData.setLastActionPage(new ProductReviewPage(iosDriver));
    }
}
