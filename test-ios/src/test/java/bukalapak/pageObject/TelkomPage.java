package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.data.vp.postpaid.TelkomData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebDriverException;

import static bukalapak.TestInstrument.dotenv;

public class TelkomPage extends PostpaidBasePage {

    public TelkomPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToPage() {
        try {
            openDeepLink("/telkom-indihome");
        } catch (WebDriverException ex) {
            //Assume.assumeTrue("Product toggled off!", false);
        }
    }

    public void isDisplayed() {
        skipOnboarding("postpaid_telkom_onboarding_button_next", "postpaid_telkom_onboarding_button_done");
        isPageDisplayed("postpaid_telkom_title_text");
        skipScenarioIfToggledOff("postpaid_telkom_field_input_customer_number");
        HelperData.setLastActionPage(new TelkomPage(iosDriver));
    }

    public void tapOnBayarButton() {
        waitForVisibilityOf("postpaid_telkom_bayar_button");
        tapElement("postpaid_telkom_bayar_button");
    }

    public void typeCustomerNumber(String customerNumber) {
        typeAndEnterValueWithTimeOut("postpaid_telkom_field_input_customer_number", customerNumber);
    }

    public void validateInquiryResult() {
        waitForVisibilityOf("postpaid_telkom_home_informasi_tagihan_title_text");
        verifyElementExist("postpaid_telkom_home_informasi_tagihan_title_text");
        PostpaidData.setCustomerName(getElementValue("postpaid_telkom_customer_name_text"));
        PostpaidData.setPeriod(getElementValue("postpaid_telkom_periode_text"));
        PostpaidData.setTotalTagihan(getElementValue("postpaid_telkom_total_tagihan_text"));
        waitForVisibilityOf("postpaid_telkom_bayar_button");
    }

    public void validateCheckOutData() {
        verifyElementExist("postpaid_telkom_checkout_page_title_text");
        assertEquals(PostpaidData.getCustomerNumber(),"postpaid_telkom_checkout_page_customer_number_text");
        assertEquals(PostpaidData.getCustomerName(),"postpaid_telkom_checkout_page_customer_name_text");
        assertEquals(PostpaidData.getPeriod(),"postpaid_telkom_checkout_page_periode_text");
    }

    public void tapOnIconTransactionHistory() {
        tapElement("postpaid_telkom_history_button");
    }

    public void validateTransactionHistoryPage() {
        verifyElementExist("postpaid_telkom_history_page_title_text");
    }

    public void autoFillCustomerNumber(String last_transaction) {
        if (last_transaction.equals("customer_number")) {
            assertEquals(PostpaidData.getCustomerNumber(),"postpaid_telkom_field_input_customer_number");
        } else {
            assertTextContains(dotenv.get("TELKOM_CUSTOMER_NUMBER"), getTextFromElement("postpaid_telkom_field_input_customer_number"));
        }
        HelperData.setLastActionPage(new TelkomPage(iosDriver));
    }

    public void setInquireData() {
        swipeUpToElement("postpaid_telkom_bayar_button");
        TelkomData.setCustomerNumber(getText("postpaid_telkom_info_customer_number"));
        TelkomData.setCustomerName(getText("postpaid_telkom_info_customer_name"));
        TelkomData.setCustomerPeriod(getText("postpaid_telkom_info_customer_period"));
    }

    public void validateErrorMessage() {
        waitForVisibilityOf("postpaid_telkom_error_message_text", 20);
    }

    public void validateItemLoaded(boolean isValid) {
        if (isValid) {
            verifyElementExist("postpaid_telkom_history_page_title_text");
        } else {
            verifyElementExist("postpaid_telkom_history_page_title_text");
            assertTextContains("Belum ada transaksi", getTextFromElement("postpaid_telkom_history_page_empty"));
        }
        HelperData.setLastActionPage(new TelkomPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
