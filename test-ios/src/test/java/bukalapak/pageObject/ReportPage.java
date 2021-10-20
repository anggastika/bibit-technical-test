package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.STRIPEData;

import com.bukalapak.salad.util.Direction;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import org.junit.Assert;

public class ReportPage extends BasePage {

    private final static String DEFAULT_DESCRIPTION = "Automation testing Test Engineer, jangan di-report ya min!";

    public ReportPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    /***
    * validations
    ***/
    public void isDisplayed() {
        verifyElementExist("report_page_title");
        HelperData.setLastActionPage(new ReportPage(iosDriver));
    }

    public void validateReportSuccess() {
        verifyElementNotExist("report_page_title");
    }

    public void validateReportFailed() {
        isDisplayed();
        Assert.assertTrue(STRIPEData.getMultipleValidation());
    }

    public boolean isSnackbarDisplayed() {
        return isElementExist("report_page_snackbar", 3);
    }

    public boolean isBazaarSnackbarDisplayed() {
        return isElementExist("report_page_bazaar_snackbar", 3);
    }

    /***
    * single actions
    ***/
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void closePage() {
        if (isElementExist("report_page_title", 3)) {
            tapElement("report_page_close_button");
            verifyElementNotExist("report_page_title");
        }
    }

    public void pickTopReason() {
        validateDisplayed("report_page_category_title");
        tapElement("report_page_category_radio_button");
    }

    public void fillDescription() {
        fillDescription(DEFAULT_DESCRIPTION);
    }

    public void fillDescription(String reason) {
        validateDisplayed("report_page_description_field");
        typeAndEnterValue("report_page_description_field", reason);
    }

    public void submit() {
        if (!isElementClickable("report_page_submit_button")) {
            swipeToDirection(Direction.UP);
        }
        validateDisplayed("report_page_submit_button");
        tapElement("report_page_submit_button");
    }

    /***
    * action groups
    ***/
    public void fillFormWithDefaultValue() {
        pickTopReason();
        fillDescription();
        submit();
        waitFor(5);
    }
}
