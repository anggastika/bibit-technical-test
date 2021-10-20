package bukalapak.pageObject;

import bukalapak.data.APIData;
import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.data.vp.postpaid.BpjsData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebDriverException;

public class BpjsKesehatanPage extends PostpaidBasePage {

    public BpjsKesehatanPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToPage() {
        try {
            openDeepLink("/bpjs-kesehatan");
        } catch (WebDriverException ex) {
            //Assume.assumeTrue("Product toggled off!", false);
        }
    }

    public void verifyPageDisplayed() {
        isPageDisplayed("postpaid_bpjs_title_text");
        skipScenarioIfToggledOff("postpaid_bpjs_customer_number_field");
        HelperData.setLastActionPage(new BpjsKesehatanPage(iosDriver));
    }

    public void skipOnBoarding() {
        skipOnboarding("postpaid_bpjs_onboarding_button_next", "postpaid_bpjs_onboarding_button_done");
    }

    public void chooseMonth() {
        tapElement(constructLocator("postpaid_bpjs_month_dynamic","6"));
    }

    public void validateInquiryResult() {
        verifyElementExist("postpaid_bpjs_customer_number_text", 15, "Inquiry can't displayed");
    }

    public void validateCustomerNumber() {
        String info = "Yaaaahhh tagihan " + PostpaidData.getCustomerNumber() + " sudah dibayar. Saatnya mencari nomor BPJS testing yang lain";
        String element = constructLocator("postpaid_bpjs_customer_number_prefilled", PostpaidData.getCustomerNumber());

        if (!isElementVisible(element)) {
            APIData.setLogMessage(info);
        } else {
            verifyElementDisplayed(element);
        }
        HelperData.setLastActionPage(new BpjsKesehatanPage(iosDriver));
    }

    public void tapOnBayarButton() {
        swipeUpToElement("postpaid_bpjs_pay_button");
        tapElement("postpaid_bpjs_pay_button");
    }

    public void typeCustomerNumber(String custNumber) {
        String number;

        if (custNumber.equals("valid")) {
            number = generatePostpaidNumber("BPJS_CONTRACT_NUMBER");
        } else {
            number = fakerRangkaNumber();
        }

        PostpaidData.setCustomerNumber(number);
        typeAndEnterValue("postpaid_bpjs_customer_number_field", PostpaidData.getCustomerNumber());
    }

    public void setInquireData() {
        swipeUpToElement("postpaid_bpjs_pay_button");
        BpjsData.setCustomerNumber(getText("postpaid_bpjs_info_customer_number"));
        BpjsData.setCustomerName(getText("postpaid_bpjs_info_customer_name"));
        BpjsData.setCustomerPeriod(getText("postpaid_bpjs_info_customer_period"));
    }

    public void tapOnIconTransactionHistory() {
        tapElement("postpaid_bpjs_history_button");
    }

    public void validateTransactionHistoryPage() {
        verifyElementExist("postpaid_bpjs_history_page_title_text");
        HelperData.setLastActionPage(new BpjsKesehatanPage(iosDriver));
    }

    public void showAlertMessage() {
        waitForVisibilityOf("postpaid_bpjs_alert_message", 5000);
        verifyElementExist("postpaid_bpjs_alert_message");
        HelperData.setLastActionPage(new BpjsKesehatanPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
