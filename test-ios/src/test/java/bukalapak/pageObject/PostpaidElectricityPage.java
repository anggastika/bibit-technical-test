package bukalapak.pageObject;

import bukalapak.data.APIData;
import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.data.vp.postpaid.ListrikPascabayarData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebDriverException;

public class PostpaidElectricityPage extends PostpaidBasePage {

    public PostpaidElectricityPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToPage() {
        try {
            openDeepLink("/listrik-pln/tagihan-listrik");
        } catch (WebDriverException ex) {
            //Assume.assumeTrue("Product toggled off!", false);
        }
    }

    public void validateOnPage() {
        skipOnboarding("postpaid_electricity_onboarding_button_next", "postpaid_electricity_onboarding_button_done");
        verifyElementExist("postpaid_electricity_home_title_text");
        skipScenarioIfToggledOff("postpaid_electricity_field_input_customer_number");
        HelperData.setLastActionPage(new PostpaidElectricityPage(iosDriver));
    }

    public void validateLandingPage() {
        verifyElementExist("postpaid_electricity_home_title_text");
    }

    public void tapOnBayarButton() {
        swipeToLocator("postpaid_electricity_bayar_button");
        tapElement("postpaid_electricity_bayar_button");
    }


    public void validateInquiryResult() {
        waitForVisibilityOf("postpaid_electricity_home_informasi_tagihan_title_text");
        verifyElementExist("postpaid_electricity_home_informasi_tagihan_title_text");
        verifyElementExist("postpaid_electricity_customer_number_text");
        PostpaidData.setCustomerName(getElementValue("postpaid_electricity_customer_name_text"));
        PostpaidData.setCost(getElementValue("postpaid_electricity_tarif_text"));
        PostpaidData.setPeriod(getElementValue("postpaid_electricity_periode_text"));
        PostpaidData.setTotalTagihan(getElementValue("postpaid_electricity_total_tagihan_text"));
        waitForVisibilityOf("postpaid_electricity_bayar_button");
    }

    public void tapOnIconTransactionHistory() {
        tapElement("postpaid_electricity_history_button");
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
            verifyElementDisplayed("postpaid_electricity_history_page_empty");
        }
        HelperData.setLastActionPage(new PostpaidElectricityPage(iosDriver));
    }

    public void typeCustomerNumber(String customerNumber) {
        String number = generatePostpaidNumber("ELECTRICITY_POSTPAID_NUMBER");;

        if (customerNumber.equals("valid")) {
            PostpaidData.setPostpaidNumber(number);
            typeAndEnterValueWithTimeOut("postpaid_electricity_field_input_customer_number", number);
        } else if (customerNumber.equals("invalid")) {
            typeAndEnterValueWithTimeOut("postpaid_pdam_customer_number_field", fakerPhoneNumber("0812", 8));
        }
    }

    public void setInquireData() {
        swipeUpToElement("postpaid_electricity_bayar_button");
        ListrikPascabayarData.setCustomerNumber(getText("postpaid_electricity_info_customer_number"));
        ListrikPascabayarData.setCustomerName(getText("postpaid_electricity_info_customer_name"));
        ListrikPascabayarData.setCustomerPeriod(getText("postpaid_electricity_info_customer_period"));
    }

    public void verifyCustomerNumberField() {
        String info = "Yaaaahhh tagihan " + PostpaidData.getPostpaidNumber() + " sudah dibayar. Saatnya mencari nomor Listrik Pascabayar testing yang lain";
        String element = constructLocator("postpaid_electricity_customer_number_prefilled", PostpaidData.getPostpaidNumber());

        if (!isElementVisible(element)) {
            APIData.setLogMessage(info);
        } else {
            verifyElementDisplayed(element);
        }
        HelperData.setLastActionPage(new PostpaidElectricityPage(iosDriver));
    }

    public void validateErrorMessage() {
        waitForVisibilityOf("postpaid_electricity_error_message_text", 20);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
