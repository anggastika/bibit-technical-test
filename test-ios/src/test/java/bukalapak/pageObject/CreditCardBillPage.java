package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebDriverException;

public class CreditCardBillPage extends PostpaidBasePage {

    public CreditCardBillPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToPage() {
        try {
            openDeepLink("/tagihan-kartu-kredit");
        } catch (WebDriverException ex) {
            //Assume.assumeTrue("Product toggled off!", false);
        }
    }

    public void isBankScreenDisplayed() {
        assertFalse(isElementVisible("postpaid_credit_card_bill_title_text"), "Still on landing page, bank screen is not opened yet!");
        assertTrue(isElementVisible("postpaid_credit_card_bill_bank_search_field"), "Bank search field is not existed!");
    }

    public void isDisplayed() {
        skipOnboarding("postpaid_credit_card_bill_onboarding_button_next", "postpaid_credit_card_bill_onboarding_button_done");
        isPageDisplayed("postpaid_credit_card_bill_title_text");
        skipScenarioIfToggledOff("postpaid_credit_card_bill_bank_field");
        HelperData.setLastActionPage(new CreditCardBillPage(iosDriver));
    }

    public void pickBank(String bank) {
        tapElement("postpaid_credit_card_bill_bank_field");
        isBankScreenDisplayed();
        typeAndEnterValueWithTimeOut("postpaid_credit_card_bill_bank_search_field", bank);
        tapElement("postpaid_credit_card_bill_bank_option");
        isDisplayed();
    }

    public void tapOnBayarButton() {
        swipeUpToElement("postpaid_credit_card_bill_pay_button");
        tapElement("postpaid_credit_card_bill_pay_button");
    }

    public void typeCustomerNumber(String customerNumber) {
        typeAndEnterValueWithTimeOut("postpaid_credit_card_bill_customer_number_field", customerNumber);
    }

    public void typeNominalPayment(String nominalPayment) {
        typeAndEnterValueWithTimeOut("postpaid_credit_card_bill_nominal_payment_field", nominalPayment);
    }

    public void tapOnPromoTerbaru() {
        if(isElementVisible("postpaid_credit_card_promo_terbaru_label")) {
            tapElement("postpaid_credit_card_promo_terbaru_label");
        }
    }

    public void tapOnSalinButton() {
            tapElement("postpaid_credit_card_copy_promo_code_label");
        }

    public void validatePromoCopied() {
        if(isElementVisible("postpaid_credit_card_promo_terbaru_label")) {
            assertEquals("Tersalin", getElementValue("postpaid_credit_card_copy_promo_code_label"));
        }
    }

    public void validateErrorMessage() {
        waitForVisibilityOf("postpaid_credit_card_bill_error_message_text", 20);
    }

    public void tapOnIconTransactionHistory() {
        tapElement("postpaid_credit_card_history_button");
    }

    public void validateTransactionHistoryPage() {
        verifyElementExist("postpaid_credit_card_page_title_text");
    }

    public void validateItemLoaded(String type) {
        if (type == null) {
            verifyElementExist("postpaid_credit_card_page_title_text");
        } else {
            verifyElementExist("postpaid_credit_card_page_title_text");
            assertTextContains("Belum ada transaksi", getTextFromElement("postpaid_credit_card_history_page_empty"));
        }

        HelperData.setLastActionPage(new CreditCardBillPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
