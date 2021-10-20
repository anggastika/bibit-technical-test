package bukalapak.pageObject;

import bukalapak.data.APIData;
import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.data.vp.postpaid.PulsaPascabayarData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebDriverException;

public class PostpaidPhoneCreditPage extends PostpaidBasePage {

    public PostpaidPhoneCreditPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToPage() {
        try {
            openDeepLink("/pulsa/pascabayar");
        } catch (WebDriverException ex) {
            //Assume.assumeTrue("Product toggled off!", false);
        }
    }

    public void isDisplayed() {
        skipOnboarding("postpaid_phone_credit_onboarding_button_text", "postpaid_phone_credit_onboarding_button_done");
        isPageDisplayed("postpaid_phone_credit_title_text");
        skipScenarioIfToggledOff("postpaid_phone_credit_customer_number_field");
        HelperData.setLastActionPage(new PostpaidElectricityPage(iosDriver));
    }

    public void tapOnBayarButton() {
        waitForVisibilityOf("postpaid_phone_credit_pay_button", 10);
        setInquireData();
        tapElement("postpaid_phone_credit_pay_button");
    }

    public void typeCustomerNumber(String customerNumber) {
        String number;

        if(customerNumber.equals("valid")) {
            number = generatePostpaidNumber("POSTPAID_PHONE_NUMBER");
        } else {
            number = fakerPhoneNumber("0812", 8);
        }
        PulsaPascabayarData.setCustomerNumber(number);
        typeAndEnterValueWithTimeOut("postpaid_phone_credit_customer_number_field", number);
    }

    private void setInquireData() {
        PulsaPascabayarData.setCustomerName(getTextFromElement("postpaid_phone_credit_inquire_customer_name_text"));
        PulsaPascabayarData.setBillPeriod(getTextFromElement("postpaid_phone_credit_inquire_bill_period_text"));
    }

    public void verifyCustomerNumberField() {
        String info = "Yaaaahhh tagihan " + PostpaidData.getPostpaidNumber() + " sudah dibayar. Saatnya mencari nomor Pulsa Pascabayar testing yang lain";
        String element = constructLocator("postpaid_phone_credit_customer_number_field", PostpaidData.getPostpaidNumber());

        if (!isElementVisible(element)) {
            APIData.setLogMessage(info);
        } else {
            verifyElementDisplayed(element);
        }
        HelperData.setLastActionPage(new PostpaidPhoneCreditPage(iosDriver));
    }

    public void validateErrorMessage() {
        waitForVisibilityOf("postpaid_phone_error_message_text", 20);
    }

    public void tapOnIconTransactionHistory() {
        tapElement("postpaid_phone_credit_history_button");
    }

    public void validateHistoryPage() {
        verifyElementExist("postpaid_electricity_history_page_title_text");
    }

    public void validateItemLoaded(boolean isValid) {
        if (isValid) {
            verifyElementExist("postpaid_electricity_history_page_title_text");
        } else {
            verifyElementExist("postpaid_electricity_history_page_title_text");
            assertEquals( "Belum ada transaksi", "postpaid_electricity_history_page_empty");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
