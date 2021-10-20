package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PostpaidElectricityNonBillPage extends PostpaidBasePage {

    public PostpaidElectricityNonBillPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyPageDisplayed() {
        isPageDisplayed("postpaid_electricity_nonbill_home_title_text");
        skipScenarioIfToggledOff("postpaid_electricity_nonbill_field_input_customer_number");
        HelperData.setLastActionPage(new PostpaidElectricityNonBillPage(iosDriver));
    }

    public void skipOnBoarding() {
        skipOnboarding("postpaid_electricity_nonbill_onboarding_button_next", "postpaid_electricity_nonbill_onboarding_button_done");
    }

    public void typeCustomerNumber(String customerNumber) {
        String custnumber;

        if (customerNumber.equals("valid")) {
            custnumber = generateElectricityPostpaidNonBillNumber("NONTAGLIS_VALID_NUMBER_LIST");
        } else {
            custnumber = fakerCustNumber();
        }

        PostpaidData.setCustomerNumber(custnumber);
        typeAndEnterValueWithTimeOut("postpaid_electricity_nonbill_field_input_customer_number",custnumber);
    }

    public void tapOnIconTransactionHistory() {
        tapElement("postpaid_electricity_nonbill_history_button");
    }

    public void verifyTransactionHistoyPage() {
        verifyElementExist("postpaid_electricity_nonbill_history_page_title_text");
    }

    public void validateItemLoaded(String isValid) {
        verifyElementExist("postpaid_electricity_nonbill_history_page_title_text");
        if (isValid != null) {
            assertEquals("Belum ada transaksi", "postpaid_electricity_nonbill_history_page_empty");
            verifyElementDisplayed("postpaid_electricity_nonbill_history_page_empty");
        }
    }

    public void validateErrorMessage() {
        waitForVisibilityOf("postpaid_electricity_nonbill_error_message_text", 20);
        verifyElementExist("postpaid_electricity_nonbill_error_message_text");
        HelperData.setLastActionPage(new PostpaidElectricityNonBillPage(iosDriver));
    }
}
