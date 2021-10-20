package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class FeedbackPage extends BasePage {

    public FeedbackPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnFeedbackPage() {
        waitForVisibilityOf("feedback_title", 5);
        verifyElementExist("feedback_filter_btn");
        verifyElementExist("feedback_penjualan_btn");
        HelperData.setLastActionPage(new FeedbackPage(iosDriver));
    }

    public void verifyOfferingSuperSeller() {
        waitForVisibilityOf("feedback_offering_super_seller_desc", 20);
        verifyElementExist("feedback_offering_super_seller_link");
    }

    public void clickFilterOptions(String menuFilter) {
        waitForVisibilityOf(constructLocator("feedback_filter_menu", menuFilter), 20);
        tapElement(constructLocator("feedback_filter_menu", menuFilter));
    }

    @Override
    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
