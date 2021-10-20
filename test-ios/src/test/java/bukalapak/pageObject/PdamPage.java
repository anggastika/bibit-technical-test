package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.data.vp.postpaid.AirPdamData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebDriverException;

public class PdamPage extends PostpaidBasePage {

    public PdamPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToPage() {
        try {
            openDeepLink("/air-pdam");
        } catch (WebDriverException ex) {
            //Assume.assumeTrue("Product toggled off!", false);
        }
    }

    public void isAreaScreenDisplayed() {
        assertFalse(isElementVisible("postpaid_pdam_title_text"), "Still on landing page, area screen is not opened yet!");
        assertTrue(isElementVisible("postpaid_pdam_area_search_field"), "Area search field is not existed!");
    }

    public void isDisplayed() {
        skipOnboarding("postpaid_pdam_onboarding_button_text", "postpaid_pdam_onboarding_button_done");
        isPageDisplayed("postpaid_pdam_title_text");
        skipScenarioIfToggledOff("postpaid_pdam_customer_number_field");
        HelperData.setLastActionPage(new PdamPage(iosDriver));
    }

    public void pickArea(String area) {
        tapElement("postpaid_pdam_area_field");

        isAreaScreenDisplayed();
        typeAndEnterValueWithTimeOut("postpaid_pdam_area_search_field", area);
        AirPdamData.setCustomerArea(area);
        tapElement("postpaid_pdam_area_option");
        swipeUpToElement("postpaid_pdam_pay_button");
    }

    public void tapOnBayarButton() {
        swipeUpToElement("postpaid_pdam_pay_button");
        tapElement("postpaid_pdam_pay_button");
    }

    public void typeCustomerNumber(String customerNumber) {
        String number = generatePostpaidNumber("PDAM_CONTRACT_NUMBER");
        waitForVisibilityOf("postpaid_pdam_customer_number_field", 10);
        if(customerNumber.equals("valid")) {
            PostpaidData.setCustomerNumber(number);
            typeAndEnterValueWithTimeOut("postpaid_pdam_customer_number_field", number);
        } else if (customerNumber.equals("invalid")) {
            typeAndEnterValueWithTimeOut("postpaid_pdam_customer_number_field", fakerCustNumber());
        }
    }

    public void setInquireData() {
        swipeUpToElement("postpaid_pdam_pay_button");
        AirPdamData.setCustomerNumber(getText("postpaid_pdam_info_customer_number"));
        AirPdamData.setCustomerName(getText("postpaid_pdam_info_customer_name"));
        AirPdamData.setCustomerPeriod(getText("postpaid_pdam_info_customer_period"));
    }

    public void tapOnIconTransactionHistory() {
        tapElement("postpaid_pdam_history_button");
    }

    public void validateErrorMessage() {
        verifyElementExist("postpaid_pdam_error_message_text");
        HelperData.setLastActionPage(new PdamPage(iosDriver));
    }

    public void validateHistoryPage() {
        verifyElementExist("postpaid_pdam_history_page_title_text");
    }

    public void validateItemLoaded(boolean isValid) {
        if (isValid) {
            verifyElementExist("postpaid_pdam_history_page_title_text");
        } else {
            verifyElementExist("postpaid_pdam_history_page_title_text");
            assertEquals( "Belum ada transaksi", "postpaid_pdam_history_page_empty");
            verifyElementDisplayed("postpaid_pdam_history_page_empty");
        }
        HelperData.setLastActionPage(new PdamPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
