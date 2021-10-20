package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.APIData;
import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.data.TransactionData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebDriverException;

public class CableTvPage extends PostpaidBasePage {

    public CableTvPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToPage() {
        try {
            openDeepLink("/tv-kabel");
        } catch (WebDriverException ex) {
            //Assume.assumeTrue("Product toggled off!", false);
        }
    }

    public void isBillerScreenDisplayed() {
        assertFalse(isElementVisible("postpaid_cable_tv_title_text"), "Still on landing page, biller screen is not opened yet!");
        assertTrue(isElementVisible("postpaid_cable_tv_biller_search_field"), "Biller search field is not existed!");
    }

    public void validateonpage() {
        skipOnboarding("postpaid_cable_tv_onboarding_button_next", "postpaid_cable_tv_onboarding_button_done");
        waitForVisibilityOf("postpaid_cable_tv_title_text", 10);
        skipScenarioIfToggledOff("postpaid_cable_tv_biller_field");
        HelperData.setLastActionPage(new CableTvPage(iosDriver));
    }

    public void setInquireData() {
        waitForVisibilityOf("postpaid_cable_tv_inquire_data", 15);
        PostpaidData.setCustomerName(getTextFromElement("postpaid_cable_tv_inquire_name_text"));
    }

    public void pickBiller(String biller) {
        tapElement("postpaid_cable_tv_biller_field");
        isBillerScreenDisplayed();
        PostpaidData.setBiller(biller);
        typeAndEnterValueWithTimeOut("postpaid_cable_tv_biller_search_field", biller);
        tapElement("postpaid_cable_tv_biller_option");
    }

    public void tapOnBayarButton() {
        String info = "Yaaaahhh tagihan " + PostpaidData.getCustomerNumber() + " sudah dibayar. Saatnya mencari nomor TV KABEL testing yang lain";

        if (isElementExist("postpaid_cable_tv_paid_number", 10)) {
            APIData.setLogMessage(info);
            APIData.setLogUserID(TestInstrument.dotenv.get("WEBHOOK_USER"));
        } else {
            swipeToLocator("postpaid_cable_tv_pay_button");
            tapElement("postpaid_cable_tv_pay_button");
        }
    }

    public void typeCustomerNumber(String customerNumber) {
        String number =  generatePostpaidNumber("TVKABEL_CUSTOMER_NUMBER_LIST");

        waitForVisibilityOf("postpaid_cable_tv_customer_number_field", 10);
        switch (customerNumber) {
            case "valid":
                PostpaidData.setCustomerNumber(number);
                typeAndEnterValueWithTimeOut("postpaid_cable_tv_customer_number_field", number);
                break;
            case "invalid":
                typeAndEnterValueWithTimeOut("postpaid_cable_tv_customer_number_field", fakerCustNumber());
                break;
            default:
                LogUtil.error("customerNumber is not found");
        }
    }

    public void verifyTvKabelNumberHasBeenPaid() {
        verifyElementDisplayed("postpaid_cable_tv_paid_number");
        TransactionData.setPaymentMethod("");
    }

    public void verifyCustomerNumberField() {
        String info = "Yaaaahhh tagihan " + PostpaidData.getPostpaidNumber() + " sudah dibayar. Saatnya mencari nomor Multifinance testing yang lain";
        String element = constructLocator("postpaid_cable_tv_customer_number_prefilled", PostpaidData.getPostpaidNumber());

        if (!isElementVisible(element)) {
            APIData.setLogMessage(info);
        } else {
            verifyElementDisplayed(element);
        }
    }

    public void validateErrorMessage() {
        waitForVisibilityOf("postpaid_cable_tv_error_message_text", 20);
    }

    public void tapOnIconTransactionHistory() {
        tapElement("postpaid_cable_tv_history_button");
    }

    public void validateHistoryPage() {
        verifyElementExist("postpaid_cable_tv_history_page_title_text");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
