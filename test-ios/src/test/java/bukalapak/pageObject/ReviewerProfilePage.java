package bukalapak.pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ReviewerProfilePage extends BasePage {

    public ReviewerProfilePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void assertReviewerPage() {
        waitForVisibilityOf("review_section_profile",30);
        waitForVisibilityOf("user_rating_icon",30);
        waitForVisibilityOf("review_usernamelabel", 30);
        waitForVisibilityOf("user_profile_image_review", 30);
    }

    public void lihatSemuaLink() {
        tapElement("lihat_semua_ulasan_reviewer");
    }

    public void assertSemuaUlasanPage() {
        waitForVisibilityOf("semua_ulasan_page_title",30);
        waitForVisibilityOf("user_rating_icon",30);
        assertTrue(isElementVisible("user_profile_image_review"));
        waitForVisibilityOf("like_icon",10);
    }

    public void backToProfilePage() {
        if (isElementVisible("alchemy_navbar_back_button")) {
            tapElement("alchemy_navbar_back_button");
        } else {
            tapBackButton();
        }
    }

    public void tapOnKunjungiLapaknyaBtn() {
        swipeUpToElement("kunjungi_lapaknya_button");
        waitForVisibilityOf("kunjungi_lapaknya_button", 30);
        tapElement("kunjungi_lapaknya_button");
    }

    public void assertPelapakPage() {
        assertTrue(isElementVisible("reviewer_name", 10));
    }

    public void tapProductDetailCard() {
        waitForVisibilityOf("product_card",10);
        swipeUpToElement("product_card",5);
        tapElement("product_card");
    }
}
